package com.omion.appwhere_test.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MerchantResponse implements Parcelable {
    String description;
    int status;
    List<Merchant> merchants;

    public MerchantResponse() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Merchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<Merchant> merchants) {
        this.merchants = merchants;
    }

    public MerchantResponse(Parcel in) {
        description = in.readString();
        status = in.readInt();
        if (in.readByte() == 0x01) {
            merchants = new ArrayList<Merchant>();
            in.readList(merchants, Merchant.class.getClassLoader());
        } else {
            merchants = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeInt(status);
        if (merchants == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(merchants);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MerchantResponse> CREATOR = new Parcelable.Creator<MerchantResponse>() {
        @Override
        public MerchantResponse createFromParcel(Parcel in) {
            return new MerchantResponse(in);
        }

        @Override
        public MerchantResponse[] newArray(int size) {
            return new MerchantResponse[size];
        }
    };
}
