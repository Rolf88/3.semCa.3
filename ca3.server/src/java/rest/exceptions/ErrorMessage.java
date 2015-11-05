/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.exceptions;

/**
 *
 * @author RolfMoikjær
 */
public class ErrorMessage {

    private int code;
    private String message;

    public ErrorMessage(Throwable ex, int code) {
        this.code = code;
        this.message = ex.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
