package com.omion.appwhere_test.controllers.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omion.appwhere_test.R;
import com.omion.appwhere_test.controllers.viewholders.OfficeHolder;
import com.omion.appwhere_test.models.Merchant;

import java.util.List;

public class OfficeAdapter extends RecyclerView.Adapter<OfficeHolder> {
    List<Merchant> offices;
    View v;

    public OfficeAdapter(List<Merchant> offices) {
        this.offices = offices;
    }

    @NonNull
    @Override
    public OfficeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_office,viewGroup,false);
        return new OfficeHolder(this.v);
    }

    @Override
    public void onBindViewHolder(@NonNull OfficeHolder officeHolder, int i) {
        final Merchant localItem = offices.get(i);

        officeHolder.tvName.setText(localItem.getMerchantName());
        officeHolder.tvPhone.setText(localItem.getMerchantTelephone());
        officeHolder.tvAddress.setText(localItem.getMerchantAddress());
    }

    @Override
    public int getItemCount() {
        return null!=offices?offices.size():0;
    }
}
