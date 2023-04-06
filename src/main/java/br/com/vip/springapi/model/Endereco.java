package br.com.vip.springapi.model;


import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Access(AccessType.FIELD)
@Data
public class Endereco {
    
    private String logradouro;

    private String bairro;

    private String cep;
}
