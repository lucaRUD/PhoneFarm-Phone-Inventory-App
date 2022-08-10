package com.example.phonestore2.Homepage;

public class phones {
    int id;
    String model,cond,accessories,price;

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCondition(String cond) {
        this.cond = cond;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getCond() {
        return cond;
    }

    public String getAccessories() {
        return accessories;
    }

    public String getPrice() {
        return price;
    }

    public phones(int id,String model, String condition, String accessories, String price) {
        this.id= id;
        this.model = model;
        this.cond = condition;
        this.accessories = accessories;
        this.price = price;
    }
}
