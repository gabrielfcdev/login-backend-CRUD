package br.com.gfc.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gfc.projeto.entity.PerfilUsuarioEntity;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuarioEntity, Long> {

}
