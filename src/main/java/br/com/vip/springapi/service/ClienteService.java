package br.com.vip.springapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.vip.springapi.exception.InvalidRequestException;
import br.com.vip.springapi.model.Cliente;
import br.com.vip.springapi.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    // @Autowired
    // BCryptPasswordEncoder crypt;
    
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }


    public Cliente saveCliente(Cliente cliente) {
        try {
            // cliente.getUsuario().setPassword(crypt.encode(cliente.getUsuario().getPassword()));
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new InvalidRequestException("Dados inválidos", e);
        }
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteDB = clienteRepository.findById(id);

        if (clienteDB.isPresent()) {
            cliente.setId(id);
           return clienteRepository.save(cliente);
        } else {
            throw new InvalidRequestException("Não foi possível atualizar o cliente", null);
        }
    }

    public void deleteCliente(Long id) {
        Optional<Cliente> clienteDB = clienteRepository.findById(id);

        if (clienteDB.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new InvalidRequestException("Não foi possível deletar o cliente", null);
        }
    }
}
