
import java.util.Scanner;



/**
 *
 * @author LENOVO
 * Day la noi bat nguon moi dau kho^~
 */
public class Inputter {
    public static Scanner sc= new Scanner(System.in);
    
    //get a string with no condition
    public static String inputStr(String msg){
        System.out.println(msg);
        String data= sc.nextLine().trim().toUpperCase();
        return data;
    }
    //get a non-blank String
    public static String inputNonBlankStr (String msg){
        String data;
        do{
            System.out.println(msg);
            data= sc.nextLine().trim();
        } while (data.length()==0);
        return data;
    }
    //get a non-negative number
    public static double inputNonNegative(String msg){
        double data;
        do{
            System.out.println(msg);
            data=sc.nextDouble();
        } while(data<0);
        return data;
    }
    
    //get a string following a pattern
    public static String inputPattern(String msg, String pattern){
        String data;
        do{
            System.out.println(msg);
            data= sc.nextLine().trim();
        } while(!data.matches(pattern));
        return data;
    }
}
