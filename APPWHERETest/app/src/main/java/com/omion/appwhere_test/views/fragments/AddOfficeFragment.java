package com.omion.appwhere_test.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.omion.appwhere_test.R;
import com.omion.appwhere_test.models.Merchant;
import com.omion.appwhere_test.models.MerchantResponse;
import com.omion.appwhere_test.service.EndPointsRestApi;
import com.omion.appwhere_test.service.RestApiAdapter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOfficeFragment extends Fragment {

    EditText etName,etAddress,etPhone,etLat,etLong;
    Button btContinue;
    View v;
    Merchant merchant;
    public AddOfficeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_add_office, container, false);
        initialize();

        return v;
    }

    private void initialize() {
        etName = v.findViewById(R.id.et_name);
        etAddress = v.findViewById(R.id.et_address);
        etPhone = v.findViewById(R.id.et_phone);
        etLat = v.findViewById(R.id.et_lat);
        etLong = v.findViewById(R.id.et_long);
        btContinue = v.findViewById(R.id.bt_continue);
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    sendData();
                }
            }
        });
    }

    private boolean validate() {
        merchant = new Merchant();
        merchant.setMerchantName(etName.getText().toString());
        merchant.setMerchantAddress(etAddress.getText().toString());
        merchant.setMerchantTelephone(etPhone.getText().toString());
        merchant.setLongitude(Float.parseFloat(etLong.getText().toString()));
        merchant.setLatitude(Float.parseFloat(etLat.getText().toString()));

        if (merchant.getMerchantName().isEmpty()||merchant.getMerchantAddress().isEmpty()||merchant.getMerchantTelephone().isEmpty()){
            return false;
        }
        return true;
    }

    private void cleanForm(){
        etName.setText("");
        etAddress.setText("");
        etPhone.setText("");
        etLat.setText("");
        etLong.setText("");
    }

    private void sendData() {

        RestApiAdapter restAPIAdapter = new RestApiAdapter();
        EndPointsRestApi puntoAccesoGetUser = restAPIAdapter.establecerConexionServidor();

        Call<MerchantResponse> merchantsCall = puntoAccesoGetUser.postMerchant(merchant);

        merchantsCall.enqueue(new Callback<MerchantResponse>() {
            @Override
            public void onResponse(Call<MerchantResponse> call, Response<MerchantResponse> response) {
                if (response.isSuccessful()) {
                    MerchantResponse merchantResponse = response.body();
                    try {
                        if (merchantResponse != null) {
                            if (merchantResponse.getStatus() == 1) {
                                cleanForm();
                                Toast.makeText(getContext(),"Has agregado una sucursal",Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.e("error", "null");
                        }

                    } catch (Exception e) {
                        Log.e("error", e.getMessage());

                    }

                } else {
                    Log.e("error", "conexión");
                }
            }

            @Override
            public void onFailure(Call<MerchantResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e("error", t.getMessage());
                }
            }
        });
    }
}
