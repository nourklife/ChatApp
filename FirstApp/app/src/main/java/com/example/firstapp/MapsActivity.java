package com.example.firstapp;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<MapesDesc> arrayList=new ArrayList<>();
        MapesDesc mapesDesc1=new MapesDesc(30.353135,31.1979,"nour 1","Here in Bransh 1",R.drawable.hulke);
        MapesDesc mapesDesc2=new MapesDesc(30.353135,31.1979,"Nour2","Here in Bransh 2",R.drawable.hulk);
        MapesDesc mapesDesc3=new MapesDesc(30.353135,31.1979,"Nour 3","Here in Bransh 3",R.drawable.hulk);
        arrayList.add(mapesDesc1);
        arrayList.add(mapesDesc2);
        arrayList.add(mapesDesc3);
        for (int i=0;i<arrayList.size();i++){
            creatmapes(arrayList.get(i).getLatitude(),arrayList.get(i).getLongitude(),arrayList.get(i).getTital(),arrayList.get(i).snaptuite,arrayList.get(i).getIconRsID());
        }
    }

    protected  GoogleMap creatmapes(double latitude,double longitude,String tital,String snaptuite,int image){
mMap.addMarker(new MarkerOptions()
.position(new LatLng(latitude,longitude))
.title(tital)
.snippet(snaptuite)
.icon(BitmapDescriptorFactory.fromResource(image)));
mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),15));
return mMap;
    }
}