package com.example.azureapp.ui.resource;

import java.io.Serializable;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-13 19:48
 **/
public class Resource implements Serializable {

    //资源名称
    public String name;
    //资源类型
    public String type;

    /**
     * 构造函数
     * @param name
     * @param type
     */
    public Resource(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * 返回类的字符串类型
     * @return 描述资源详细的字符串
     */
    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
