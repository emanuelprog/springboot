package br.com.vip.springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vip.springapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
