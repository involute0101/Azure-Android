package com.example.azureapp.ui.home.resourceGroup;

import com.example.azureapp.ui.resource.Resource;

import java.io.Serializable;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-15 17:21
 **/
public class ResourceGroup implements Serializable {
    public String name;
    public String location;

    public ResourceGroup(String name, String location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public String toString() {
        return "ResourceGroup{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
