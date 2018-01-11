package com.alert.entity;

import java.util.Date;

/**
 * Created by zhaotao on 2018/1/11.
 */

public class Ad {

    /**
     * adsUrl : http://ad.gdrctd.com
     * areaCode : 0794
     * beginTime : 1448860830000
     * createTime : 1448860830000
     * createUserId : 1
     * endTime : 1448860830000
     * fullTitlePicPath : null
     * id : 1
     * position : 1
     * remark : 人创平安
     * sortNumber : 1
     * titlePicPath : 人创平安
     */

    public String adsUrl;
    public String areaCode;
    public long beginTime;
    public long createTime;
    public String createUserId;
    public long endTime;
    public String fullTitlePicPath;
    public String id;
    public String position;
    public String remark;
    public int sortNumber;
    public String titlePicPath;

    public Date getBeginTime() {
        return new Date(beginTime);
    }

    public Date getCreateTime() {
        return new Date(createTime);
    }

    public Date getEndTime() {
        return new Date(endTime);
    }

    @Override
    public String toString() {
        return "Ad{" +
            "adsUrl='" + adsUrl + '\'' +
            ", areaCode='" + areaCode + '\'' +
            ", beginTime=" + beginTime +
            ", createTime=" + createTime +
            ", createUserId='" + createUserId + '\'' +
            ", endTime=" + endTime +
            ", fullTitlePicPath='" + fullTitlePicPath + '\'' +
            ", id='" + id + '\'' +
            ", position='" + position + '\'' +
            ", remark='" + remark + '\'' +
            ", sortNumber=" + sortNumber +
            ", titlePicPath='" + titlePicPath + '\'' +
            '}';
    }
}
