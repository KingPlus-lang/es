package com.wjf.elasticsearch.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author KingPlus
 * @date 2021年06月14日  22:58
 */
public class NewCar {
    @JSONField(name = "CarNum")
    private String CarNum;
    @JSONField(name = "CarBrand")
    private String CarBrand;
    @JSONField(name="CarColor")
    private String CarColor;
    @JSONField(name="CarType")
    private String CarType;
    @JSONField(name="CarPrice")
    private double CarPrice;
    @JSONField(name="Displacement")
    private double Displacement;
    @JSONField(name="Changes")
    private String Changes;
    @JSONField(name="Pimag")
    private String Pimag;

    public String getCarNum() {
        return CarNum;
    }

    public void setCarNum(String carNum) {
        CarNum = carNum;
    }

    public String getCarBrand() {
        return CarBrand;
    }

    public void setCarBrand(String carBrand) {
        CarBrand = carBrand;
    }

    public String getCarColor() {
        return CarColor;
    }

    public void setCarColor(String carColor) {
        CarColor = carColor;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public double getCarPrice() {
        return CarPrice;
    }

    public void setCarPrice(double carPrice) {
        CarPrice = carPrice;
    }

    public double getDisplacement() {
        return Displacement;
    }

    public void setDisplacement(double displacement) {
        Displacement = displacement;
    }

    public String getChanges() {
        return Changes;
    }

    public void setChanges(String changes) {
        Changes = changes;
    }

    public String getPimag() {
        return Pimag;
    }

    public void setPimag(String pimag) {
        Pimag = pimag;
    }

    @Override
    public String toString() {
        return "NewCar{" +
                "CarNum='" + CarNum + '\'' +
                ", CarBrand='" + CarBrand + '\'' +
                ", CarColor='" + CarColor + '\'' +
                ", CarType='" + CarType + '\'' +
                ", CarPrice=" + CarPrice +
                ", Displacement=" + Displacement +
                ", Changes='" + Changes + '\'' +
                ", Pimag='" + Pimag + '\'' +
                '}';
    }
}
