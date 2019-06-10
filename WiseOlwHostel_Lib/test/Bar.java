/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sean
 */
public class Bar extends Foo {

    public int a =8;

    public void print_a() {
        System.out.println(a);
        System.out.println(this.a);
        System.out.println(super.a);
        System.out.println("b");
    }
    
        public void addFive(){
        System.out.println("a:" + a);
        a +=5;
        
        System.out.println("a +=5 :" + a);
        System.out.println("f");
        
    }

}

