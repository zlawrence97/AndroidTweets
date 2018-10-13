package com.example.owner.androidtweets

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.AsyncTask
import android.widget.Toast
import com.google.android.gms.maps.model.LatLng
import java.util.*
class ReverseGeocodingTask(
        private val context: Context,
        private val resultListener: (Address?) -> Unit
) : AsyncTask<LatLng, Void, Address?>() {
    // Step 2: Runs this on a background thread
    override fun doInBackground(vararg params: LatLng): Address? {
        val firstLatLng = params[0]
        // Invoke the geocoder
        val geocoder = Geocoder(context, Locale.getDefault())
        val results: List<Address> = geocoder.getFromLocation(
                firstLatLng.latitude,
                firstLatLng.longitude,
                10
        )
        if (results.isEmpty()) {
            return null
        } else {
            return results[0]
        }
    }
    override fun onPostExecute(result: Address?) {
        super.onPostExecute(result)
        resultListener.invoke(result)
    }
}