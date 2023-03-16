
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
public class CarManager {

    public static void main(String[] args) {
        ArrayList<String> options = new ArrayList<String>();
        options.add(0, "List all brands");
        options.add(1, "Add a new brands");
        options.add(2, "Search a brand based on it's ID");
        options.add(3, "Update a brand");
        options.add(4, "Save brands to file, named brands.txt");
        options.add(5, "List all cars in ascending order of brand names");
        options.add(6, "List cars based on a part of an input brand name");
        options.add(7, "Add a car");
        options.add(8, "Remove a car based on it's ID");
        options.add(9, "Update a car based on it's ID");
        options.add(10, "Save cars to file, named cars.txt");
        BrandList brandList = new BrandList();
        brandList.loadFromFile(BrandList.fileBrands);
        CarList carList = new CarList(brandList);
        carList.loadFromFile(CarList.fileCars);
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            choice = Menu.int_getChoice(options);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    System.out.println("Input the brandID:");
                    String brandID = sc.nextLine();
                    Brand b = brandList.searchBrandByID(brandID);
                    if (b != null) {
                        System.out.println(b);
                    } else {
                        System.out.println("Notfound!");
                    }
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile(BrandList.fileBrands);
                    break;
                case 6:
                    carList.listCar();
                    break;
                case 7:
                    carList.printBasedBrandName();
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    carList.removeCar();
                    break;
                case 10:
                    carList.updateCar();
                    break;
                case 11:
                    carList.saveToFile(CarList.fileCars);

            }
        } while (choice > 0 && choice <= options.size());

    }
}
