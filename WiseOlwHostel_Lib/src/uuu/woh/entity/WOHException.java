/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.entity;

/**
 *
 * @author Sean
 */
public class WOHException extends Exception{

    public WOHException() {
    }

    public WOHException(String message) {
        super(message);
    }

    public WOHException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
