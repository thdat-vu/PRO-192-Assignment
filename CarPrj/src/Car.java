/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class Car implements Comparable<Car>{
    String carID;
    Brand brand;
    String color;
    String frameID;
    String engineID;

    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String getCarID() {
        return carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getFrameID() {
        return frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public String toString() {
        return "carID=" + carID + ", brand=" + brand + ", color=" + color + ", frameID=" + frameID + ", engineID=" + engineID;
    }
    
     public String screenString() {
        return "brand" + "\n" + brand+"\n" + "car_ID " + carID + ", color " + color + ", frameID " + frameID + ", engineID " + engineID;
    }
    @Override
    public int compareTo(Car o) {
        int ss1 = this.brand.brandName.compareTo(o.brand.brandName);
        if(ss1!=0)  return ss1;
        int ss2 =this.carID.compareTo(o.carID);
        return ss2;
    }
    
}
