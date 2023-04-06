package br.com.vip.springapi.exception;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String mensagem, Exception e) {
        super(mensagem, e);
    }
}