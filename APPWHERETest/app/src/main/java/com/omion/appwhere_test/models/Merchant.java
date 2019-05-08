package com.omion.appwhere_test.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Merchant implements Parcelable {
    String id;
    float latitude;
    float longitude;
    String merchantAddress;
    String merchantName;
    String merchantTelephone;
    String registrationDate;

    public Merchant() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantTelephone() {
        return merchantTelephone;
    }

    public void setMerchantTelephone(String merchantTelephone) {
        this.merchantTelephone = merchantTelephone;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Merchant(Parcel in) {
        id = in.readString();
        latitude = in.readFloat();
        longitude = in.readFloat();
        merchantAddress = in.readString();
        merchantName = in.readString();
        merchantTelephone = in.readString();
        registrationDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
        dest.writeString(merchantAddress);
        dest.writeString(merchantName);
        dest.writeString(merchantTelephone);
        dest.writeString(registrationDate);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Merchant> CREATOR = new Parcelable.Creator<Merchant>() {
        @Override
        public Merchant createFromParcel(Parcel in) {
            return new Merchant(in);
        }

        @Override
        public Merchant[] newArray(int size) {
            return new Merchant[size];
        }
    };
}