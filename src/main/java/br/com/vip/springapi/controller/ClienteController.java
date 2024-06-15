package br.com.vip.springapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vip.springapi.model.Cliente;
import br.com.vip.springapi.model.DefaultResponse;
import br.com.vip.springapi.service.ClienteService;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    public List<Cliente> clientes = new ArrayList<Cliente>();
    public Long geradorId = 0L;

    @Autowired
    private ClienteService clienteService;



    @PostMapping
    public ResponseEntity<DefaultResponse> adicionar(@RequestBody Cliente cliente) {
        try {
            Cliente clienteNovo = clienteService.saveCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(new DefaultResponse(201, "Cliente adicionado com sucesso!", clienteNovo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponse(400, "Cliente com dados inválidos!", null));
        }
    }

    @GetMapping
    public ResponseEntity<DefaultResponse> buscarTodos() {
        List<Cliente> clientes = clienteService.getClientes();
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(200, "Lista encontrada!", clientes));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponse(400, "Lista não encontrada", null));
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DefaultResponse> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteRetorno = clienteService.updateCliente(id, cliente);
            return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(200, "Cliente atualizado com sucesso!", clienteRetorno));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponse(400, "Não foi possível atualizar", null));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DefaultResponse> excluir(@PathVariable Long id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(200, "Cliente deletado com sucesso", null));            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponse(400, "Não foi possível deletar o cliente", null));
        }
    }
}
