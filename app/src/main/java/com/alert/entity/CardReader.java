package com.alert.entity;

/**
 * Created by zhaotao on 2018/1/13.
 */

public class CardReader {

    /**
     * privince : 湖南省
     * city : 邵阳市
     * area : 大祥区
     * town :
     * address : 电机路
     * longitude : 111.474995
     * latitude : 27.231034
     * cardReaderNumber : 111111
     * cardReaderAreaCode : 0769
     * cardReaderId : 111111
     * managerUserId : 85ccbfd0bcb54715a5ca990c4a1419f2
     * department : 4343gfgfg
     * remark :
     */

    public String cardId;
    public String privince;
    public String city;
    public String area;
    public String town;
    public String address;
    public String longitude;
    public String latitude;
    public String cardReaderNumber;
    public String cardReaderAreaCode;
    public String cardReaderId;
    public String managerUserId;
    public String department;
    public String remark;

    @Override
    public String toString() {
        return "CardReader{" +
            "privince='" + privince + '\'' +
            ", city='" + city + '\'' +
            ", area='" + area + '\'' +
            ", town='" + town + '\'' +
            ", address='" + address + '\'' +
            ", longitude='" + longitude + '\'' +
            ", latitude='" + latitude + '\'' +
            ", cardReaderNumber='" + cardReaderNumber + '\'' +
            ", cardReaderAreaCode='" + cardReaderAreaCode + '\'' +
            ", cardReaderId='" + cardReaderId + '\'' +
            ", managerUserId='" + managerUserId + '\'' +
            ", department='" + department + '\'' +
            ", remark='" + remark + '\'' +
            '}';
    }
}
