package br.com.gfc.projeto.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gfc.projeto.repository.UsuarioRepository;
import br.com.gfc.projeto.repository.UsuarioVerificadorRepository;
import br.com.gfc.projeto.dto.UsuarioDTO;
import br.com.gfc.projeto.entity.UsuarioEntity;
import br.com.gfc.projeto.entity.UsuarioVerificadorEntity;
import br.com.gfc.projeto.entity.enums.TipoSituacaoUsuario;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioVerificadorRepository usuarioVerificadorRepository;
	
	@Autowired(required = true)
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	public List<UsuarioDTO> listarTodos(){
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		return usuarios.stream() .map(UsuarioDTO::new).toList();
	}
	
	public void inserir(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuarioRepository.save(usuarioEntity);
		

	}
	
	public void inserirNovoUsuario(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		usuarioEntity. setSituacao(TipoSituacaoUsuario.PENDENTE); 
		usuarioEntity.setId(null);
		usuarioRepository.save(usuarioEntity);
		
		UsuarioVerificadorEntity verificador = new UsuarioVerificadorEntity();
		verificador.setUsuario(usuarioEntity);
		verificador.setUuid(UUID.randomUUID());
		verificador.setDataExpiracao(Instant.now().plusMillis(900000));
		usuarioVerificadorRepository.save(verificador);
		
		emailService.enviarEmailTexto(usuario.getEmail(),
				"Novo usuário cadastrado",
				"Você está recebendo um email de cadastro o número para validação é " + verificador.getUuid());
	}
		
	public String verificarCadastro(String uuid) {
		System.out.println(uuid);
		UsuarioVerificadorEntity usuarioVerificacao =  usuarioVerificadorRepository.findByUuid(UUID.fromString(uuid)).get();
		
		
		if(usuarioVerificacao != null)
		System.out.println(usuarioVerificacao.getId());
		
		return null;
	}

	public UsuarioDTO alterar(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		return new UsuarioDTO(usuarioRepository.save(usuarioEntity));
	}
	
	public void excluir(Long id) {
		UsuarioEntity usuario = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuario);
	}
	
	public UsuarioDTO buscarPorId(Long id) {
		return new UsuarioDTO(usuarioRepository.findById(id).get());
	}
}
