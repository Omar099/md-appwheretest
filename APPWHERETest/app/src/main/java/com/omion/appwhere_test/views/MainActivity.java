package com.omion.appwhere_test.views;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.omion.appwhere_test.R;
import com.omion.appwhere_test.models.Merchant;
import com.omion.appwhere_test.models.MerchantResponse;
import com.omion.appwhere_test.service.EndPointsRestApi;
import com.omion.appwhere_test.service.RestApiAdapter;
import com.omion.appwhere_test.views.fragments.AddOfficeFragment;
import com.omion.appwhere_test.views.fragments.MapFragment;
import com.omion.appwhere_test.views.fragments.OfficesFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Merchant> offices;
    MerchantResponse officeResponse = new MerchantResponse();
    final Fragment fragment1 = new MapFragment();
    final Fragment fragment2 = new OfficesFragment();
    final Fragment fragment3 = new AddOfficeFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMerchants();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_map:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

                case R.id.navigation_offices:
                    //fragment2 = new OfficesFragment();
                    ((OfficesFragment) fragment2).setOffices(officeResponse.getMerchants());
                    /*Bundle args = new Bundle();
                    args.putParcelable("offices", officeResponse);
                    fragment2.setArguments(args);*/
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;

                case R.id.navigation_add:
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;
            }
            return false;
        }
    };

    private void getMerchants() {
        RestApiAdapter restAPIAdapter = new RestApiAdapter();
        EndPointsRestApi puntoAccesoGetUser = restAPIAdapter.establecerConexionServidor();

        Call<MerchantResponse> merchantsCall = puntoAccesoGetUser.getMerchants();

        merchantsCall.enqueue(new Callback<MerchantResponse>() {
            @Override
            public void onResponse(Call<MerchantResponse> call, Response<MerchantResponse> response) {
                if (response.isSuccessful()) {
                    MerchantResponse merchantResponse = response.body();
                    try {
                        if (merchantResponse != null) {
                            if (merchantResponse.getStatus() == 1) {
                                officeResponse.setMerchants(merchantResponse.getMerchants());
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
