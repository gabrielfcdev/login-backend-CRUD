package br.com.gfc.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gfc.projeto.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>  {
	
}
