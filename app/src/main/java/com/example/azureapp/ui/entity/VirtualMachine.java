package com.example.azureapp.ui.entity;

import java.io.Serializable;

/**
 * fileDesc
 * Created by wzk on 2021/7/8.
 * Email 1403235458@qq.com
 */

/**
 * 虚拟机类
 */
public class VirtualMachine implements Serializable{
    public String subscribeId;
    public String vnetName;
    public String vmName;
    public String username;
    public String password;
    public String vmSize;
    public String resGroupName;

    public VirtualMachine(String vmName) {
        this.vmName = vmName;
    }


    public VirtualMachine(String subscribeId, String vnetName, String vmName, String username, String password, String vmSize, String resGroupName) {
        this.subscribeId = subscribeId;
        this.vnetName = vnetName;
        this.vmName = vmName;
        this.username = username;
        this.password = password;
        this.vmSize = vmSize;
        this.resGroupName = resGroupName;
    }
}
