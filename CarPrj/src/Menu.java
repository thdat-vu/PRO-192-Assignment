
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author LENOVO
 */
public class Menu {
   
   public static int int_getChoice(ArrayList options){
        int response;
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i+1)+ "-"+ options.get(i));
        }
        System.out.println("Please choose an option 1.."+options.size()+":");
        Scanner sc= new Scanner(System.in);
        response= sc.nextInt();
        return response;
    }
   //khuc nay chua xong
   //nha nha nha anh minh
   //thu phan tich tai sao no lai ra nhu nay
   //tham chieu dua tren object.
   
    public static Object ref_getChoice (ArrayList options){
        int response;
        do{
            response = int_getChoice(options);
        }while(response <0||response >options.size());
        //chay den khi lua chon be hon 0 va nho hon 11
        return options.get(response);
        //tra ve lua chon
    }
    
}
