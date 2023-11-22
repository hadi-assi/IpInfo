package org.example;

import org.example.entities.IpInfo;
import org.example.util.IpUtil;


public class Main {
    public static void main(String[] args) throws  Exception{

        IpUtil.GetCurrentIpInfo();

        IpUtil.GetAllIpDB();
    }
}
