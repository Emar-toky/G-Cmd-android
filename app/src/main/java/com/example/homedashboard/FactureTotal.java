package com.example.homedashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FactureTotal {
    @SerializedName("total")
    @Expose
    private String total;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
