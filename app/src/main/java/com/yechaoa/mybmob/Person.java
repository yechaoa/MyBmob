package com.yechaoa.mybmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by yechao on 2018/2/9.
 * Describe :
 */
public class Person extends BmobObject {

    private String name;
    private String address;

    //这时候表名就是T_a_b，而不是Person
    //当然了，除了在构造函数中直接调用setTableName方法之外，你还可以在GameScore的实例中动态调用setTableName方法。
    //需要注意的是查询的结果是JSONArray,需要自行解析JSONArray中的数据。
//    public Person() {
//        this.setTableName("T_a_b");
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
