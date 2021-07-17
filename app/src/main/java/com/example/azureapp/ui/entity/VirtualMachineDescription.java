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
    public String resourceGroup;
    public String os;
    public String subscriptionName;
    public String name;
    public String location;
    public String publicIP;
    public String vmSize;
    public String subscriptionId;
    public String status;
    public String diskName;

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

    public VirtualMachineDescription(String resourceGroup, String os, String name, String location, String vmSize) {
        this.resourceGroup = resourceGroup;
        this.os = os;
        this.name = name;
        this.location = location;
        this.vmSize = vmSize;
    }

    public VirtualMachineDescription() {
    }

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
