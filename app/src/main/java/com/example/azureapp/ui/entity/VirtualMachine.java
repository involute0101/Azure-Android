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
    //虚拟机订阅ID
    public String subscribeId;
    //虚拟机网络名
    public String vnetName;
    //虚拟机名
    public String vmName;
    //用户名
    public String username;
    //密码
    public String password;
    //虚拟机大小
    public String vmSize;
    //资源组名
    public String resGroupName;

    /**
     * 虚拟机名构造函数
     * @param vmName
     */
    public VirtualMachine(String vmName) {
        this.vmName = vmName;
    }

    /**
     * 虚拟机全部参数构造函数
     * @param subscribeId
     * @param vnetName
     * @param vmName
     * @param username
     * @param password
     * @param vmSize
     * @param resGroupName
     */
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
