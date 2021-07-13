package com.example.azureapp.ui.resource;

import java.io.Serializable;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-13 19:48
 **/
public class Resource implements Serializable {

    public String name;
    public String type;
    public String status;

    public Resource(String name, String type, String status) {
        this.name = name;
        this.type = type;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
