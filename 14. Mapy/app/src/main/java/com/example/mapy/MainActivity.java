package com.example.mapy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback
        ,GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener
        ,GoogleMap.OnMapClickListener, GoogleMap.OnInfoWindowClickListener {

    GoogleMap googleMap;
    FrameLayout map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = findViewById(R.id.map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        Button currentLocationBtn = findViewById(R.id.button);
        currentLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CurrentLocationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng india = new LatLng(20.5937,78.9629);


        //polylines
        LatLng lt1 = new LatLng(20,30);
        LatLng lt2 = new LatLng(40,50);
        LatLng lt3 = new LatLng(-30,-40);
        LatLng lt4 = new LatLng(-50,-60);

        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(lt1,lt2,lt3,lt4);
        this.googleMap.addPolyline(polylineOptions);

        //circle
        CircleOptions circleOptions = new CircleOptions().center(india).radius(100000);
        this.googleMap.addCircle(circleOptions);





        googleMap.addMarker(new MarkerOptions()
                .position(india)
                .title("INDIA")
                .snippet("I love my country")
                .draggable(true));

        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(india));





        this.googleMap.setOnMarkerClickListener(this);
        this.googleMap.setOnMarkerDragListener(this);
        this.googleMap.setOnMapClickListener(this);
        this.googleMap.setOnInfoWindowClickListener(this);


    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(this, " "+marker.getPosition(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {
        Toast.makeText(this, " "+marker.getPosition(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        Toast.makeText(this, " "+latLng.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        Toast.makeText(this, " "+marker.getSnippet(), Toast.LENGTH_SHORT).show();
    }
}