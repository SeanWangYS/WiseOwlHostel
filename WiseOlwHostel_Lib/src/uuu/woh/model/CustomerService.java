/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.model;

import java.sql.SQLException;
import uuu.woh.entity.Customer;
import uuu.woh.entity.WOHException;

/**
 *
 * @author Sean
 */
public class CustomerService {

    CustomersDAO dao = new CustomersDAO();

    public void register(Customer c) throws WOHException {
        try {
            dao.insert(c);
        }catch(WOHException ex){
            if(ex.getCause() instanceof SQLException){
                SQLException cause  = (SQLException)ex.getCause();
                if(cause.getErrorCode()==1062){
                    throw new WOHException("主鍵值重複註冊");
                }
            }else{
                throw ex;
            }
        }
    }
    
    public Customer login(String eamil, String password) throws WOHException{
        Customer c =  dao.selectByEmail(eamil);
        if(c!=null && password!= null && password.equals(c.getPassword())){
        return  c;
        }else{
            throw new WOHException("登入失敗，帳號或密碼不正確");
        }
    }

    public void update(Customer c) throws WOHException {
        dao.update(c);
    }
    
    
}
