package org.design.pattern.bridge.profile;

/**
 * @author: lhz
 * @date: 2021/1/28
 **/
public class Dev implements Profile{

    String idAddr = "192.168.1.1";

    @Override
    public void setIdAddr(String idAddr) {
        this.idAddr = idAddr;
    }

    @Override
    public String getIdAddr() {
        return idAddr;
    }
}
