package com.alert.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhaotao on 2018/1/14.
 */

public class CardReaderEntity implements Parcelable {

    /**
     * address : nnn
     * cardId : b2804678923146dfb792d27171f95e62
     * cardReaderAreaCode : bbb
     * cardReaderNumber : vvvv
     * cardReaderStatus : 1
     * city : hbh
     * installStatus : 1
     * managerUserId : bb
     * managerUserMobile : null
     * managerUserName : null
     * privince : hb
     * town : bb
     */

    public String address;
    public String cardId;
    public String cardReaderAreaCode;
    public String cardReaderNumber;
    public String cardReaderStatus;
    public String city;
    public String installStatus;
    public String managerUserId;
    public String managerUserMobile;
    public String managerUserName;
    public String privince;
    public String town;

    @Override
    public String toString() {
        return "CardReaderEntity{" +
            "address='" + address + '\'' +
            ", cardId='" + cardId + '\'' +
            ", cardReaderAreaCode='" + cardReaderAreaCode + '\'' +
            ", cardReaderNumber='" + cardReaderNumber + '\'' +
            ", cardReaderStatus='" + cardReaderStatus + '\'' +
            ", city='" + city + '\'' +
            ", installStatus='" + installStatus + '\'' +
            ", managerUserId='" + managerUserId + '\'' +
            ", managerUserMobile=" + managerUserMobile +
            ", managerUserName=" + managerUserName +
            ", privince='" + privince + '\'' +
            ", town='" + town + '\'' +
            '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.cardId);
        dest.writeString(this.cardReaderAreaCode);
        dest.writeString(this.cardReaderNumber);
        dest.writeString(this.cardReaderStatus);
        dest.writeString(this.city);
        dest.writeString(this.installStatus);
        dest.writeString(this.managerUserId);
        dest.writeString(this.managerUserMobile);
        dest.writeString(this.managerUserName);
        dest.writeString(this.privince);
        dest.writeString(this.town);
    }

    public CardReaderEntity() {
    }

    protected CardReaderEntity(Parcel in) {
        this.address = in.readString();
        this.cardId = in.readString();
        this.cardReaderAreaCode = in.readString();
        this.cardReaderNumber = in.readString();
        this.cardReaderStatus = in.readString();
        this.city = in.readString();
        this.installStatus = in.readString();
        this.managerUserId = in.readString();
        this.managerUserMobile = in.readString();
        this.managerUserName = in.readString();
        this.privince = in.readString();
        this.town = in.readString();
    }

    public static final Parcelable.Creator<CardReaderEntity> CREATOR = new Parcelable.Creator<CardReaderEntity>() {
        @Override
        public CardReaderEntity createFromParcel(Parcel source) {
            return new CardReaderEntity(source);
        }

        @Override
        public CardReaderEntity[] newArray(int size) {
            return new CardReaderEntity[size];
        }
    };
}
