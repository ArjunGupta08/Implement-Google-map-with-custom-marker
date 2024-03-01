package com.arjungupta08.googlemaps;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.arjungupta08.googlemaps.databinding.ActivityMapsBinding;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // initialise geocoder
        geocoder = new Geocoder(this);

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

        // Set Map Type (Optional)
//        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Initialise LatLng
//        LatLng latLng = new LatLng(27.1751, 78.0421);
//        // Add a marker
//        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Taj Mahal").snippet("Wonder of the world");
//        mMap.addMarker(markerOptions);
//        // move the camera
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
//        mMap.animateCamera(cameraUpdate);

        try {
            // Get LatLng from a place Name
            List<Address> addresses = geocoder.getFromLocationName("Kanpur", 1);
            Address address = addresses.get(0);

            // Initialise LatLng
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            // Add a marker
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(address.getLocality()).snippet("Wonder of the world");
            mMap.addMarker(markerOptions);
            // move the camera
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
            mMap.animateCamera(cameraUpdate);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}