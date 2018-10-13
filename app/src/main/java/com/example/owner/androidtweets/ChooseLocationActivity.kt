package com.example.owner.androidtweets

import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.owner.example.androidtweets.R
import java.util.*

class ChooseLocationActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.)
        // Gets a reference to the Google Maps fragment
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        // Load the map
        mapFragment.getMapAsync(this)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener { latLng: LatLng ->
            // I'm executing on the UI / Main thread
            // We're going to put the UI / Main thread to sleep
//            sleep(10000)
            // We want to run all this code on a separate thread
//            val geocoder = Geocoder(this, Locale.getDefault())
//            val results: List<Address> =
//            geocoder.getFromLocationName("White House", 10)
//                geocoder.getFromLocation(latLng.latitude, latLng.longitude,10)
            val reverseGeocodingTask = ReverseGeocodingTask(
                    context = this,
                    resultListener = { address: Address? ->
                        if (address == null) {
                            Toast.makeText(
                                    this,
                                    "No address found for location!",
                                    Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                    this,
                                    "Found: ${address.getAddressLine(0)}",
                                    Toast.LENGTH_SHORT
                            ).show()
                            // Update the button text & color
                        }
                    }
            )
            reverseGeocodingTask.execute(latLng)
        }
        //        // Add a marker in Sydney and move the camera
//        val gwuLatLng = LatLng(38.898365, -77.046753)
//        val dupontLatLng = LatLng(38.9091152, -77.0453387)
//        val gwuTitle = "GWU"
//        val dupontTitle = "Dupont Circle"
//
//        val latLngBounds = LatLngBounds.Builder()
//            .include(gwuLatLng)
//            .include(dupontLatLng)
//            .build()
//
//        val padding = 175
//
//        mMap.addMarker(MarkerOptions().position(gwuLatLng).title(gwuTitle))
//        mMap.addMarker(MarkerOptions().position(dupontLatLng).title(dupontTitle))
//
//        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, padding))
//
//        googleMap.addPolyline(
//            PolylineOptions()
//                .add(gwuLatLng, dupontLatLng)
//                .width(8f)
//                .color(Color.rgb(218, 135, 7))
//        )
    }
}
