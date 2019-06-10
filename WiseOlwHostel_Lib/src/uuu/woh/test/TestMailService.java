package uuu.woh.test;
import uuu.woh.model.MailService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class TestMailService {

    public static void main(String[] args) {
        MailService.sendHelloMailWithLogo("musheng1986@gmail.com","測試看看內容有沒有東西出現");
        //MailService.sendHelloMailWithLogo("musheng1986@gapp.nthu.edu.tw");
    }
}
