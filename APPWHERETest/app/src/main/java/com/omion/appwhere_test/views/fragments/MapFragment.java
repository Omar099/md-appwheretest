package com.omion.appwhere_test.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.omion.appwhere_test.R;
import com.omion.appwhere_test.models.Merchant;

import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    View v;
    GoogleMap googleMap;
    MapView mapView;
    List<Merchant> offices;
    LatLng latLng;

    public MapFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_map, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) v.findViewById(R.id.map);
        if (mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        googleMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //List<Marker> markers;

        LatLng latLng = new LatLng(19.382005,-99.175174);

        MarkerOptions marker = new MarkerOptions().position(latLng).title("Suc");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_localization_red));
       /* googleMap.addMarker(new MarkerOptions().position(new LatLng(-99.29,19.40)).title("Marker"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-100.29,19.40)).title("Marker"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-130.29,19.40)).title("Marker"));
        //CameraPosition Liberty = CameraPosition.builder().target(new LatLng(-99.29,19.40)).zoom(16).bearing(0).tilt(45).build();
        //googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-99.29,19.40)));*/

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.addMarker(marker);

    }

    public void setMarker(float lat,float lon) {
        this.latLng = new LatLng(lat,lon);
    }
}
