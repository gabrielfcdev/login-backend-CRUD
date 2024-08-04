package br.com.gfc.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.gfc.projeto.dto.AcessDTO;
import br.com.gfc.projeto.dto.AuthenticationDTO;
import br.com.gfc.projeto.secury.jwt.JwtUtils;

@Service
public class AuthService {

	private AuthenticationManager authenticationManager;
	private JwtUtils jwtUtils;
	
	@Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }
	
	public AcessDTO login (AuthenticationDTO authDto) {
		
		try {
		//cria mecanismo de credencial para o Spring
		
		UsernamePasswordAuthenticationToken userAuth = 
				new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
		
		//prepara o mecanismo para autentication
		
		Authentication authentication = authenticationManager.authenticate(userAuth);
		
		//busca o usuario logado
		UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();
		
		String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);
		
		AcessDTO accessDto = new AcessDTO(token);
		
		return accessDto;
		
		}catch(BadCredentialsException e) {
			//TODO Login ou Senha Invalida
		}
		
		return new AcessDTO("Acesso negado");
	}
}
