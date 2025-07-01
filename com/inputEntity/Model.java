package com.inputEntity;

public class Model {
    private int item_count;
    private double item_value;
    private String item_name;
    private String item_category;
    private boolean isImported;
    private double total_tax;

    public boolean isImported() {
        return isImported;
    }

    @Override
    public String toString() {
        return "Input{" +
                "isImported=" + isImported +
                ", item_count=" + item_count +
                ", item_value=" + item_value +
                ", item_name='" + item_name + '\'' +
                ", item_category='" + item_category + '\'' +
                ", total_tax=" + total_tax +
                '}';
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    public String getItem_category() {
        return item_category;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_value() {
        return item_value;
    }

    public void setItem_value(double item_value) {
        this.item_value = item_value;
    }

    public double getTotal_tax() {
        return total_tax;
    }

    public void setTotal_tax(double total_tax) {
        this.total_tax = total_tax;
    }

    public Model(boolean isImported, String item_category, int item_count, String item_name, double item_value, double total_tax) {
        this.isImported = isImported;
        this.item_category = item_category;
        this.item_count = item_count;
        this.item_name = item_name;
        this.item_value = item_value;
        this.total_tax = total_tax;
    }
}
