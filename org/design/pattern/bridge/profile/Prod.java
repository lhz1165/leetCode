package org.design.pattern.bridge.profile;

/**
 * @author: lhz
 * @date: 2021/1/28
 **/
public class Prod implements Profile{
    @Override
    public void setIdAddr(String idAddr) {

    }

    @Override
    public String getIdAddr() {
        return "192.168.1.2";
    }
}
