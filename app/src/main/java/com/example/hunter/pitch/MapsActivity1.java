package com.example.hunter.pitch;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

public class MapsActivity1 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng optimum = new LatLng(52.404162, 9.727106);
        LatLng park1 = new LatLng(52.405000, 9.727803);
        LatLng park2 = new LatLng(52.405144, 9.728394);
        LatLng car = new LatLng(52.404816, 9.728597);
        LatLng focus = new LatLng(52.404629, 9.727718);

/*        MarkerOptions marker = new MarkerOptions().position(new LatLng(52.399281, 9.713555)).title("Hello Maps");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.stop_watch));
        googleMap.addMarker(marker);*/

        IconGenerator factory = new IconGenerator(this);
        Bitmap parkPlace1 = factory.makeIcon("P");
        Bitmap parkPlace2 = factory.makeIcon("P");
        Bitmap parkPlace3 = factory.makeIcon("P");

        mMap.addMarker(new MarkerOptions().position(optimum).icon(BitmapDescriptorFactory.fromBitmap(parkPlace1)));
        mMap.addMarker(new MarkerOptions().position(park1).icon(BitmapDescriptorFactory.fromBitmap(parkPlace2)));
        mMap.addMarker(new MarkerOptions().position(park2).icon(BitmapDescriptorFactory.fromBitmap(parkPlace3)));
        mMap.addMarker(new MarkerOptions().position(car).title("Your car"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(focus, 18));
    }
}
