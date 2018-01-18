package com.alert.entity;

/**
 * Created by zhaotao on 2018/1/18.
 */

public class Manager {

    /**
     * area : 安源区
     * cardReaderAreaCode : 0799
     * city : 萍乡市
     * department : 萍乡市
     * id : 3e6865fd529e4f8aa904055dda9886ef
     * managerMan : 施俊强
     * mobilePhone : 17707997678
     * privince : 江西省
     */

    public String area;
    public String cardReaderAreaCode;
    public String city;
    public String department;
    public String id;
    public String managerMan;
    public String mobilePhone;
    public String privince;

    @Override
    public String toString() {
        return "Manager{" +
            "area='" + area + '\'' +
            ", cardReaderAreaCode='" + cardReaderAreaCode + '\'' +
            ", city='" + city + '\'' +
            ", department='" + department + '\'' +
            ", id='" + id + '\'' +
            ", managerMan='" + managerMan + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", privince='" + privince + '\'' +
            '}';
    }
}
