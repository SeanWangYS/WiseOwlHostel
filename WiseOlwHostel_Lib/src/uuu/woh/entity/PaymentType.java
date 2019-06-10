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
public enum PaymentType {
    CARD("Credit Card"),
    ATM("ATM");
    
    private final String description;

    private PaymentType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "PaymentType{" + "description=" + description + '}';
    }
    
    
}
