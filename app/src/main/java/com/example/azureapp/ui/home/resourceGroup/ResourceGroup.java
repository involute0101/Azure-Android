package com.example.azureapp.ui.home.resourceGroup;

import com.example.azureapp.ui.resource.Resource;

import java.io.Serializable;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-15 17:21
 **/
public class ResourceGroup implements Serializable {
    //虚拟机名字
    public String name;
    //虚拟机位置
    public String location;

    /**
     * 构造函数
     * @param name
     * @param location
     */
    public ResourceGroup(String name, String location) {
        this.name = name;
        this.location = location;
    }

    /**
     * 转换成字符串
     * @return 描述虚拟机信息的字符串
     */
    @Override
    public String toString() {
        return "ResourceGroup{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
