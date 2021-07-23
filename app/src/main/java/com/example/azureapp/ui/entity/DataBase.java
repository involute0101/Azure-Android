package com.example.azureapp.ui.entity;

import java.io.Serializable;

/**
 * fileDesc
 * Created by wzk on 2021/7/12.
 * Email 1403235458@qq.com
 */

/**
 * 数据库类
 */
public class DataBase implements Serializable {
    //数据库用户名
    public String dataBaseUsername;
    //数据库密码
    public String dataBasePassword;
    //数据库名
    public String dataBaseName;
    //数据库资源组
    public String resourceGroup;

    /**
     * 数据库构造方法
     * @param dataBaseName
     */
    public DataBase(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    /**
     * 数据库构造方法
     * @param dataBaseUsername 数据库用户名
     * @param dataBasePassword 数据库密码
     * @param dataBaseName 数据库名
     * @param resourceGroup 数据库资源组
     */
    public DataBase(String dataBaseUsername, String dataBasePassword, String dataBaseName, String resourceGroup) {
        this.dataBaseUsername = dataBaseUsername;
        this.dataBasePassword = dataBasePassword;
        this.dataBaseName = dataBaseName;
        this.resourceGroup = resourceGroup;
    }
}
