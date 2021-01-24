package com.example.homedashboard;

public class Produit {
    private int id;
    private String Designation;
    private int Pu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        this.Designation = designation;
    }

    public int getPu() {
        return Pu;
    }

    public void setPu(int pu) {
        Pu = pu;
    }

    @Override
    public String toString() {
        return ""+id +" - "+ Designation;
    }

}
