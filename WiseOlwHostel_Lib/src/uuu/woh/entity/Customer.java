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
public class Customer {

    private String email; //Pkey //required
    private String password; //required
    private String surname; //required
    private String name; //required
    private String phone; //required
    private char gender; //required
    
    public static final char MALE = 'M'; 
    public static final char FEMALE = 'F';
    
    public Customer(){}

    public Customer(String email, String password, String surname, String name) {
        this.email = email;
        this.password = password;
        this.surname = surname;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws WOHException {
        if (email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            this.email = email;
        }else{
            System.out.println("必須輸入格式正確的客戶Email");
            throw new WOHException("必須輸入格式正確的客戶Email");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws WOHException {
        if(password!=null && password.length()>=6 && password.length()<=12){
        this.password = password;
        }else{
            System.out.println("請輸入6~12碼的密碼");
            throw new WOHException("請輸入6~12碼的密碼");
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws WOHException{
        if(phone!=null && phone.matches("[0-9]{10}")){
        this.phone = phone;
        }else{
            System.out.println("電話號碼格式不符");
            throw new WOHException("電話號碼格式不符");
        }
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) throws WOHException {
        if(gender==MALE || gender==FEMALE){
        this.gender = gender;
        }else{
            System.out.println("請輸入正確性別，M = male，F = female");
            throw new WOHException("請輸入正確性別，M = male，F = female");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "email=" + email + ", password=" + password + ", surname=" + surname + ", name=" + name + ", phone=" + phone + ", gender=" + gender + '}';
    }
    
    

}
