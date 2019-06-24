package com.zup.testezuplucas.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListOperationsPOJO {
    @SerializedName("statementList")
    private ArrayList<Operation> operations;

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Operation> operations) {
        this.operations = operations;
    }
}
