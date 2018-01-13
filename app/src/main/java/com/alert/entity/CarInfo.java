package com.alert.entity;

/**
 * Created by zhaotao on 2018/1/13.
 */

public class CarInfo {

    /**
     * id : 0061db0c93384971a55b40970a8db829
     * mobilePhone : 13879481115
     * idCardNumber :  362502198211022032
     * idName : 黄睿
     * idAddress : 抚州市临川区青云峰路399号
     * homePhone :
     * homeAdddress : 七里岗山口村
     * vehsNumber :  FA031260
     * vehsModel :  04569
     * vehsColor : 银灰色
     * rfidAreaCode :  0794
     * rfidNumber :  10029
     * rfidRegistTime :  2016-09-15 02:51:40
     * remark : 备注
     * vehsBrand : 台力
     * vehsMotoNumber :  130503722
     * vehsArchNumber :  201306040000086
     * buyDate :  2014-07-15 15:34:00
     * installDate :  2016-07-11 15:35:00
     * buyAddress : 上顿渡河冰广场超威专卖店
     * price : 2700
     * vehsImgPath : /aaa/bb/cc.png
     * idCardImgPath : /aaa/bb/cc.png
     */

    public String id;
    public String mobilePhone;
    public String idCardNumber;
    public String idName;
    public String idAddress;
    public String homePhone;
    public String homeAdddress;
    public String vehsNumber;
    public String vehsModel;
    public String vehsColor;
    public String rfidAreaCode;
    public String rfidNumber;
    public String rfidRegistTime;
    public String remark;
    public String vehsBrand;
    public String vehsMotoNumber;
    public String vehsArchNumber;
    public String buyDate;
    public String installDate;
    public String buyAddress;
    public int price;
    public String vehsImgPath;
    public String idCardImgPath;

    @Override
    public String toString() {
        return "CarInfo{" +
            "id='" + id + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", idCardNumber='" + idCardNumber + '\'' +
            ", idName='" + idName + '\'' +
            ", idAddress='" + idAddress + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", homeAdddress='" + homeAdddress + '\'' +
            ", vehsNumber='" + vehsNumber + '\'' +
            ", vehsModel='" + vehsModel + '\'' +
            ", vehsColor='" + vehsColor + '\'' +
            ", rfidAreaCode='" + rfidAreaCode + '\'' +
            ", rfidNumber='" + rfidNumber + '\'' +
            ", rfidRegistTime='" + rfidRegistTime + '\'' +
            ", remark='" + remark + '\'' +
            ", vehsBrand='" + vehsBrand + '\'' +
            ", vehsMotoNumber='" + vehsMotoNumber + '\'' +
            ", vehsArchNumber='" + vehsArchNumber + '\'' +
            ", buyDate='" + buyDate + '\'' +
            ", installDate='" + installDate + '\'' +
            ", buyAddress='" + buyAddress + '\'' +
            ", price=" + price +
            ", vehsImgPath='" + vehsImgPath + '\'' +
            ", idCardImgPath='" + idCardImgPath + '\'' +
            '}';
    }
}
