
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class CarList extends ArrayList<Car> {

    BrandList brandList;
    public static final String fileCars = "cars.txt";
    //khoa chet cai ten Filename di
    Scanner sc = new Scanner(System.in);

    //constructor hay sao ay'
    public CarList(BrandList blist) {
        this.brandList = blist;
    }

    //cai ham loadFromFile y chang thang ben BrandList
    public boolean loadFromFile(String filename) {
        File f = new File(filename);
        FileReader fr = null;
        BufferedReader r = null;
        if (!f.exists()) {
            System.out.println("FILE NOT FOUND");
            return false;
        } else {
            try {
                fr = new FileReader(filename);
                r = new BufferedReader(fr);
                while (r.ready()) {
                    String s = r.readLine();
                    String[] arr = s.split(",");
                    if (arr.length == 5) {
                        int pos = brandList.searchID(arr[1].trim());//arr[1]: brandID
                        Brand b = brandList.get(pos);
                        Car c = new Car(arr[0].trim(), b, arr[2].trim(), arr[3].trim(), arr[4].trim());
                        add(c);
                    }
                }
            } catch (Exception ex) {
                System.out.println("ERROR");
            } finally {
                try {
                    if (fr != null) {
                        fr.close();
                    }
                    if (r != null) {
                        r.close();
                    }
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
            }
        }
        return true;
    }
    //ham saveToFile y chang ben BrandList
    public boolean saveToFile(String filename){
        File f= new File(filename);
        PrintWriter w= null;
        try {
            if(f.exists()){
                System.out.println("Do you want to overwrite it(Y/N)?");
                String ans= sc.nextLine();
                if (ans.equalsIgnoreCase("n")) return false;
                //ghi file
                w= new PrintWriter(filename);
                for (Car c : this) {
                    w.println(c.getCarID()+","+c.getBrand().getBrandID()+","+c.getColor()+","+c.getFrameID()+","+c.getEngineID());
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        } finally {
            try {
                if(w!=null) w.close();
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }
        return true;
    }
    //kiem cai xe dua tren cai ID
    public int searchID(String carID){
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCarID().equals(carID)) return i;
        }
        return -1;
    }
    //kiem cai xe dua tren so khung xe
    public int searchFrame(String fID){
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getFrameID().equals(fID)) return i;
        }
        return -1;
    }
    //kiem cai xe dua tren so dong co
    public int searchEngine(String eID){
        for (int i = 0; i < size(); i++) {
            if(get(i).getEngineID().equals(eID)) return i;
        }
        return -1;
    }
    //them 1 chiec xe
    //met moi, rat met moi
    //boi vi ban phai kiem tra 1 so dieu kien xem coi cai ID xe, so khung, so Dong
    //co co ton tai chua, neu chua thi add, ko thi phai bao ve la da co xe ton tai.
    //khai bao cac gia tri can thiet
    public void addCar(){
        String newCarID, newColor, newframeID, newEngineID;
        //sua lai thanh Inputter thi con kho hon nua
        //Dung co toi uu code ngay cuoi cung truoc khi nop bai
        do {
            newCarID = Inputter.inputStr("Input CarID:");
            if (searchID(newCarID) >= 0) {
                System.out.println("The CarID already existed!");
            }
        } while (searchID(newCarID) >= 0);
        Brand b = (Brand)Menu.ref_getChoice(brandList);
        newColor = Inputter.inputNonBlankStr("Input color:");
        do {
            newframeID = Inputter.inputPattern("Input FrameID:", "[F]\\d{4}");
            if (searchFrame(newframeID) >= 0) {
                System.out.println("The FrameID already existed!");
            }
        } while (searchFrame(newframeID) >= 0);
        do {
            newEngineID = Inputter.inputPattern("Input EngineID:", "[E]\\d{4}");
            if (searchEngine(newEngineID) >= 0) {
                System.out.println("The EngineID already existed!");
            }
        } while (searchEngine(newEngineID) >= 0);
        Car c = new Car(newCarID, b, newColor, newframeID, newEngineID);
        add(c);

    }
    //kiem tra xem cai chuoi nay no co phai chuoi con cua 1 chuoi bu khac khong?
    
    public int isSubString(String substring, String string){
        int m= substring.length();
        int n= string.length();
        for (int i = 0; i <= n-m; i++) {
            //For current index i, check for pattern match
            int j;
            for (j = 0; j < m; j++) {
                if(substring.charAt(j)!=string.charAt(i+j)) break;//does not match in a char then break
            }
            if (j==m) { //nested loop terminate at j==m means matching completely
                return i;
            }
            
        }
        return -1;
    }
    public void printBasedBrandName(){
        String aPartOfBrandName= Inputter.inputStr("Input a part of brand name:");
        int count=0;
        for (Car c : this) {
            if (isSubString(aPartOfBrandName, c.getBrand().getBrandName())>=0){
                System.out.println(c.screenString());
                count++;
            }
        }
        if (count==0) System.out.println("No car is detected");
    }
    //xoa xe dua tren ID
    //y tuong la dua vao ham searchcarID de xoa
    public boolean removeCar(){
        String removedID= Inputter.inputStr("Input the CarID of removed Car:");
        int pos= searchID(removedID);
        if(pos<0){
            System.out.println("Not found!");
            return false;
        } else {
            remove(pos);
        }
        return true;
    }
    //update gia tri cua 1 chiec xe dua tren ID chiec xe
    //dung toi uu code nua, lam on
    public boolean updateCar(){
        String updatedID= Inputter.inputStr("Input the CarID of updated car:");
        int pos= searchID(updatedID);
        if(pos<0){
            System.out.println("Not found!");
            return false;
        } else{
            String color, frameID, engineID;
            Brand b = (Brand) Menu.ref_getChoice(brandList);
            color = Inputter.inputStr("Input new color:");
            do {
                frameID = Inputter.inputPattern("Input new FrameID", "[F]\\d{4}");
                if (searchFrame(frameID) >= 0) {
                    System.out.println("The FrameID already existed!");
                }
            } while (searchFrame(frameID) >= 0);
            do {
                engineID = Inputter.inputPattern("Input new EngineID:", "[E]\\d{4}");
                if (searchEngine(engineID) >= 0) {
                    System.out.println("The EngineID already existed!");
                }
            } while (searchEngine(engineID) >= 0);
            get(pos).setBrand(b);
            get(pos).setColor(color);
            get(pos).setFrameID(frameID);
            get(pos).setEngineID(engineID);
        }
        return true;
    }
        //sap xep xe theo thu tu tang dan cua ten brand
    public void listCar(){
        Collections.sort(this);
        //kieu gi khuc nay cung se phai sai 1 lan vu overide cai ham compareTo a`
        //nho kiem tra lai 
        for (Car c : this) {
            System.out.println(c.screenString());
        }
    }
    
        
    }
    
