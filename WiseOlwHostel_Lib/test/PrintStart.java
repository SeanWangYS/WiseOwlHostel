/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sean
 */
public class PrintStart {

    public static void main(String[] args) {
        //step1 上半部
        int r = 1;
        while (r <= 5) {
            int c = 1;
            int condition = 5 - r;
            while (c <= 5) {
                if (c <= condition) {
                    System.out.print(" "); //一個空白鍵
                } else {
                    System.out.print("* ");//一個星號+一個空白鍵
                }
                c++;
            }
            System.out.println("");
            r++;
        }

        //step2 下半部
        r = 4;
        while (r > 0) {
            int c = 1;
            int condition = 5 - r;
            while (c <= 5) {
                if (c <= condition) {
                    System.out.print(" "); //一個空白鍵
                } else {
                    System.out.print("* ");//一個星號+一個空白鍵
                }
                c++;
            }
            System.out.println("");
            r--;
        }

        //上半部
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < (5 - i); j++) {
                System.out.print("* ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }

//下半部
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < (5 - i); j++) {
                System.out.print("* ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }

    }
}
