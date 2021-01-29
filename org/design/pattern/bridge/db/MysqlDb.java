package org.design.pattern.bridge.db;

/**
 * @author: lhz
 * @date: 2021/1/28
 **/
public class MysqlDb extends Db{
    String username = "root";
    String password = "456879";

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
