
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class BrandList extends ArrayList<Brand>{
    public static final String fileBrands="brands.txt";
    //khoa chet ten file
    //cai nay la default constructor
    Scanner sc = new Scanner(System.in);
    public BrandList(){
    }
    public boolean loadFromFile(String filename){
        File f = new File(filename);
        BufferedReader br = null;
        if(!f.exists()) return false;
        else{
            FileReader fr = null;
            try {
                fr = new FileReader(filename);//lenh new so 1
                br = new BufferedReader(fr);//lenh new so 2 
                while (br.ready()) {
                    String s=br.readLine();
                    String[] list = s.split("[,:]");
                    //that ra khuc nay theo dieu kien vao la co du 4 thanh phan hay khong cung duoc
                    if(list.length==4){
                        Brand b = new Brand(list[0],list[1], list[2],Double.parseDouble(list[3]));
                        add(b);
                    }
                    
                }
            } catch (Exception e) {
                System.out.println("Error");
            } finally {
                try {
                    if(fr!=null) fr.close();
                    if(br!=null) br.close();
                } catch (Exception e) {
                    System.out.println("Error happened!");
                }
            }
        }
        return true;
    }
    public boolean saveToFile(String filename){
        //thu do che^' lai khuc nay` ti'
        //coi nhu cho thang user them lua chon co muon ghi de ko
        //tao con tro de viet file di
        PrintWriter pw = null;
        try {
            //tao moi 1 file nao`
            File f = new File(filename);
            if(f.exists()){
                System.out.println("Do you want to overwrite it (Y/N)?");
                //tao buffer nhan gia tri
                Scanner sc = new Scanner(System.in);
                //ten cua quyet dinh nay la ans
                String ans = sc.nextLine();
                if(ans.equalsIgnoreCase("N"))   return false;
                //thoi khac cua su that
                //bat dau ghi de lai cai file
                pw = new PrintWriter(filename);
                //ghi de lai tung thu 1 trong do' co' ID, brandname, sound brand, price
                for (Brand br : this) {
                    pw.println(br.getBrandID()+","+br.getBrandName()+","+br.getSoundBrand()+":"+br.getPrice());
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        } finally {
            //dong printwriter lai.
            try {
                if(pw!=null)    pw.close();
            } catch (Exception e) {
                System.out.println("ERROR!");
            }
        }
        return true;
    }
    public int searchID(String bID){
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrandID().equals(bID)) return i;
        }
        return -1;
    }
    //lay lua chon cua ng dung tu ham ref_getchoice o menu
    public Brand getUserChoice(){
        Menu mnu = new Menu();
        return (Brand)mnu.ref_getChoice(this);
    }
    public void addBrand(){
        //khai bao nhung gia tri can thiet
        String ID, brandName, soundBrand;
        double price;
        //phai search ra ID truoc da
        do{
            ID=Inputter.inputStr("Input Brand ID:");
            //khoan da, cai Inputter nay dau ra??
            //no o ben class Inputter cua workshop 6 lay qua
            if (searchID(ID)>=0){
                System.out.println("Brand ID is duplicated");
            }
        }while(searchID(ID)>=0);
        brandName=Inputter.inputNonBlankStr("Input Brand's Name:").toUpperCase();
        soundBrand=Inputter.inputNonBlankStr("Input sound of Brand:");
        price=Inputter.inputNonNegative("Input price:");
        
        Brand newBrand= new Brand(ID, brandName.trim(), soundBrand.trim(), price);
        add(newBrand);
    }
    public void updateBrand(){
        //tim kiem dua tren BrandID;
        String brandID;
        Scanner sc = new Scanner(System.in);
        System.out.println("Input Brand's ID to update:");
        brandID= sc.nextLine().trim().toUpperCase();
        int position=searchID(brandID);
        if(position<0) System.out.println("NOT FOUND!");
        else{
            String brandName = Inputter.inputNonBlankStr("Input Brand's Name:").toUpperCase();
            String soundBrand = Inputter.inputNonBlankStr("Input sound of Brand:");
            double price = Inputter.inputNonNegative("Input price:");
            this.get(position).setBrandName(brandName);
            this.get(position).setSoundBrand(soundBrand);
            this.get(position).setPrice(price);
        }
    }
    public void listBrands(){
       //in ra thoi thi xai fore cho le
        for (Brand b : this) {
            System.out.println(b);
        }
       
        
    }
    //tim kiem dua tren ID
    public Brand searchBrandByID(String bID){
        //goi ham search ID ra, neu co la toi cong chuyen
        if (searchID(bID)>=0) return get(searchID(bID));
        return null;//thi dong nay nghia la khong co thi bao rong^~
    }
}
