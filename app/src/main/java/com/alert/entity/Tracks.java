package com.alert.entity;

import java.util.List;

/**
 * Created by zhaotao on 2018/1/14.
 */

public class Tracks {

    /**
     * vehsId : abc8989898xsssxdfe
     * rfidNumber : 039985
     * vehsNumber : FA086105
     * beginTime : 2016-07-01 00:00:00
     * endTime : 2016-07-01 23:59:59
     * vehsTracks : [{"trackDateTime":"2016-07-01 12:33:211","longitude":"343.9093859","latitude":"34.3323241","address":"34.3323241","area":"隆回县"}]
     */

    public String vehsId;
    public String rfidNumber;
    public String vehsNumber;
    public String beginTime;
    public String endTime;
    public List<VehsTracksBean> vehsTracks;

    public static class VehsTracksBean {
        /**
         * trackDateTime : 2016-07-01 12:33:211
         * longitude : 343.9093859
         * latitude : 34.3323241
         * address : 34.3323241
         * area : 隆回县
         */

        public String trackDateTime;
        public double longitude;
        public double latitude;
        public String address;
        public String area;

        @Override
        public String toString() {
            return "VehsTracksBean{" +
                "trackDateTime='" + trackDateTime + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", address='" + address + '\'' +
                ", area='" + area + '\'' +
                '}';
        }
    }

    @Override
    public String toString() {
        return "Tracks{" +
            "vehsId='" + vehsId + '\'' +
            ", rfidNumber='" + rfidNumber + '\'' +
            ", vehsNumber='" + vehsNumber + '\'' +
            ", beginTime='" + beginTime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", vehsTracks=" + vehsTracks +
            '}';
    }
}
