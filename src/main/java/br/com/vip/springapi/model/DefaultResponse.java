package br.com.vip.springapi.model;

public class DefaultResponse {
    public int status;
    public String message;
    public Object body;

    public DefaultResponse(int status, String message, Object body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }
}
