package ru.netology.rest;

public class Request {

    int id;
    String manufacturer;
    String model;
    String color;
    String stateSign;
    int productionYear;
    int[] accidentYears;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStateSign() {
        return stateSign;
    }

    public void setStateSign(String stateSign) {
        this.stateSign = stateSign;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int[] getAccidentYears() {
        return accidentYears;
    }

    public void setAccidentYears(int[] accidentYears) {
        this.accidentYears = accidentYears;
    }

}
