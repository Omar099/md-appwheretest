package com.omion.appwhere_test.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omion.appwhere_test.R;
import com.omion.appwhere_test.controllers.adapters.OfficeAdapter;
import com.omion.appwhere_test.models.Merchant;
import com.omion.appwhere_test.models.MerchantResponse;

import java.util.List;

public class OfficesFragment extends Fragment {

    private List<Merchant> offices;

    private RecyclerView rvOffices;
    View v;

    public OfficesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_offices, container, false);
        return v;
    }

    private void startAdapter() {
        rvOffices = (RecyclerView) v.findViewById(R.id.rv_offices);
        rvOffices.setLayoutManager(new LinearLayoutManager(getActivity()));
        OfficeAdapter mAdapter = new OfficeAdapter(offices);
        rvOffices.setAdapter(mAdapter);
        rvOffices.setItemAnimator(new DefaultItemAnimator());
        rvOffices.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    public void setOffices(List<Merchant> offices) {
        this.offices = offices;
        startAdapter();
    }

}
