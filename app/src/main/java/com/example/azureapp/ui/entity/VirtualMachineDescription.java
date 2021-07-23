package com.example.azureapp.ui.entity;

import java.io.Serializable;

/**
 * fileDesc
 * Created by wzk on 2021/7/12.
 * Email 1403235458@qq.com
 */

/**
 * 虚拟机详情信息类
 */
public class VirtualMachineDescription implements Serializable {
    //虚拟机资源组
    public String resourceGroup;
    //虚拟机操作系统
    public String os;
    //虚拟机订阅名
    public String subscriptionName;
    //虚拟机名
    public String name;
    //虚拟机位置
    public String location;
    //虚拟机公用IP
    public String publicIP;
    //虚拟机大小
    public String vmSize;
    //虚拟机订阅ID
    public String subscriptionId;
    //虚拟机状态
    public String status;
    //虚拟机硬盘
    public String diskName;

    /**
     * 虚拟机描述构造函数
     * @param resourceGroup
     * @param os
     * @param subscriptionName
     * @param name
     * @param location
     * @param publicIP
     * @param vmSize
     * @param subscriptionId
     * @param status
     */
    public VirtualMachineDescription(String resourceGroup, String os, String subscriptionName, String name, String location, String publicIP, String vmSize, String subscriptionId, String status) {
        this.resourceGroup = resourceGroup;
        this.os = os;
        this.subscriptionName = subscriptionName;
        this.name = name;
        this.location = location;
        this.publicIP = publicIP;
        this.vmSize = vmSize;
        this.subscriptionId = subscriptionId;
        this.status = status;
    }

    /**
     * 虚拟机部分参数构造函数
     * @param resourceGroup
     * @param os
     * @param name
     * @param location
     * @param vmSize
     */
    public VirtualMachineDescription(String resourceGroup, String os, String name, String location, String vmSize) {
        this.resourceGroup = resourceGroup;
        this.os = os;
        this.name = name;
        this.location = location;
        this.vmSize = vmSize;
    }

    /**
     * 虚拟机描述空参构造函数
     */
    public VirtualMachineDescription() {
    }

    /**
     *
     * @return 虚拟机描述信息
     */
    @Override
    public String toString() {
        return "VirtualMachineDescription{" +
                "resourceGroup='" + resourceGroup + '\'' +
                ", os='" + os + '\'' +
                ", subscriptionName='" + subscriptionName + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", publicIP='" + publicIP + '\'' +
                ", vmSize='" + vmSize + '\'' +
                ", subscriptionId='" + subscriptionId + '\'' +
                ", status='" + status + '\'' +
                ", diskName='" + diskName + '\'' +
                '}';
    }
}
