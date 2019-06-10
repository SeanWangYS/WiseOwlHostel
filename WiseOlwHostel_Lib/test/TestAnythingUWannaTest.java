
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import sun.security.util.Length;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sean
 */
public class TestAnythingUWannaTest {

    public static void main(String[] args) {

//        LocalDate dateFrom = LocalDate.of(2019, 4, 2);
//        LocalDate dateTo = LocalDate.parse("2019-04-05");
//
//===========================================================
//        System.out.println("dateFrom= " + dateFrom);
//        System.out.println("dateTo= " + dateTo);
//
//        Long period = dateFrom.until(dateTo, ChronoUnit.DAYS);
//        System.out.println("period= " + period);
//            double d = 5.45;
//            int i = 2;
//            System.out.println(i*d);
//============================================================
//        int x = 0;
//        int y = 1;
//        do{
//            y += ++x;
//            System.out.println("y = " + y);
//        }while(x<3);
//        
//        System.out.println("y = " + y);
//===================================================================
//        String s = "[I've tried]";
//        CharSequence res = s.subSequence(1, s.length()-1);
//        System.out.println("res = " + res);
//        String res2 = String.valueOf(res);
//        System.out.println("res2 = " + res2);
//       
//        
//        String tes3 = s.substring(1,s.length()-1);
//        System.out.println("tes3 = " + tes3);
//=====================================================================
        Foo f = new Bar();
//         Bar bf=(Bar)f;
//         bf.print_a();
        System.out.println(f.a);
//        f.print_a();
        
        
        Bar b = new Bar();
//        System.out.println(b.a);
        Foo fb = (Foo)b;
        System.out.println(fb.a);
        
//        Bar bf = (Bar)f;
//        System.out.println(bf.a);
    


    }

}
