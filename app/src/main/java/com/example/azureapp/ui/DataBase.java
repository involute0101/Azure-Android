package com.example.azureapp.ui;

import java.io.Serializable;

/**
 * fileDesc
 * Created by wzk on 2021/7/12.
 * Email 1403235458@qq.com
 */
public class DataBase implements Serializable {
    public String dataBaseName;

    public DataBase(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }
}
