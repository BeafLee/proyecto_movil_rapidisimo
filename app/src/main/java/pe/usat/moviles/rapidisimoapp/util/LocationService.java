package pe.usat.moviles.rapidisimoapp.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.List;

import pe.usat.moviles.rapidisimoapp.R;

public class LocationService extends Service implements LocationListener {
    private Context context;
    private Activity activity;
    private boolean gpsActivo;
    public double latitud, longitud;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location location;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    public LocationService() {
        super();
        Log.i("LOGS", "Intento por constructor");
        this.context = this.getApplicationContext();
    }

    public LocationService(Context context, Activity activity) {
        super();
        this.context = context;
        this.activity = activity;

        Log.i("LOGS", "Inicio constructor " + latitud + " - " + longitud);
        getLocation();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("LOGS", "INCIO on create " + latitud + " - " + longitud);
        getLocation();
    }

    public void getLocation() {
        try {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            gpsActivo = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            Log.i("LOGS", "gps " + gpsActivo);

        } catch (Exception e) {
            Log.e("LOGS", e.getMessage());
        }

        if (gpsActivo) {
            try {
                if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions( this.activity,
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            LOCATION_PERMISSION_REQUEST_CODE);
                }
                locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER,
                        (long) 50 * 60, 0, this);

                location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

                latitud = location.getLatitude();
                longitud = location.getLongitude();
                Log.i("LOGS", "primera ubi " + latitud + " - " + longitud);
            } catch (Exception e) {
                Log.e("Error con la ubicacion", e.getMessage());
            }

        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitud = location.getLatitude();
        longitud = location.getLongitude();
        Log.i("LOGS", latitud + " - " + longitud);
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
        Log.i("LOGS", "onLocationChanged " );

    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
        Log.e("LOGS", "onFlushComplete " );

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
        Log.e("LOGS", "onStatusChanged " );
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
        Log.e("LOGS", "onProviderEnabled " );
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
        Log.e("LOGS", "onProviderDisabled " );
    }
}
