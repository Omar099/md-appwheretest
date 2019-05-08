package com.omion.appwhere_test.controllers.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.omion.appwhere_test.R;


public class OfficeHolder extends RecyclerView.ViewHolder {
    public TextView tvName,tvAddress, tvPhone;
    public OfficeHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
        tvAddress = itemView.findViewById(R.id.tv_address);
        tvPhone = itemView.findViewById(R.id.tv_phone);
    }
}
