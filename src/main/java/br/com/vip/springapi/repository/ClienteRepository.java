package br.com.vip.springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vip.springapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
