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

    public Resource(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
