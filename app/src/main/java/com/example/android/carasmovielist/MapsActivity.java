package com.example.android.carasmovielist;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private static final float DEFAULT_ZOOM = 15f;
    private GoogleMap mMap;
    private String setLocation;
    private String text;
    private Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        TextView textView = (TextView)findViewById(R.id.text);
        text = setDetails();
        textView.setText(text);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;
        getIncomingIntentandMap();
        try {
            if (address != null) {
                LatLng movieMarker = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(movieMarker).title("Movie Film Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(movieMarker));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(movieMarker, DEFAULT_ZOOM));
            } else {
                //default map
                LatLng defaultMap = new LatLng(37.744992, -122.710125);
                mMap.addMarker(new MarkerOptions().position(defaultMap).title("Location Not Available"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(defaultMap));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultMap, DEFAULT_ZOOM));
            }
        } catch (IllegalStateException e){
            Log.e(TAG, "address has not been assigned to a latitude or longitude" + e.getMessage());
        }

    }


    private void getIncomingIntentandMap() {
        Log.d(TAG, "checking incoming intent for location");
        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();

        if(getIntent().hasExtra("location") && !Objects.equals(getIntent().getStringExtra("location"), "N/A")){
            setLocation = getIntent().getStringExtra("location") + " San Francisco, CA";

            try {
                list = geocoder.getFromLocationName(setLocation, 1);
            } catch (IOException e) {
                Log.e(TAG, "geolocate: IOException: " + e.getMessage());
            } catch (IllegalArgumentException e){
                Log.e(TAG, "geolocate: Illegal Argument Exception" + e.getMessage());
            }
        }

        if(list.size() > 0){
            address = list.get(0);

            Log.d(TAG, "geolocate: found a location: " + address.toString());
        }
       else{
            address = null;
            Log.d(TAG, "no address found, use default map");
        }
    }

    private String setDetails(){
        String location = getIntent().getStringExtra("location");
        String title = getIntent().getStringExtra("title");
        String actor1 = getIntent().getStringExtra("actor1");
        String actor2 = getIntent().getStringExtra("actor2");
        String actor3 = getIntent().getStringExtra("actor3");
        String director = getIntent().getStringExtra("director");
        String writer = getIntent().getStringExtra("writer");
        String fun_fact = getIntent().getStringExtra("fun_fact");

        String details = "Set location: " + location + "\n" +
                title + " stars " + actor1 + " " + actor2 + " " + actor3 + "\n" +
                "directed by: " + director + "\n" + "written by: " + writer + "\n" +
                "Fun Fact: " + fun_fact;

        return details;

    }


}
