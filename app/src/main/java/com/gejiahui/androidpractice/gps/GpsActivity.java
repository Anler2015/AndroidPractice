package com.gejiahui.androidpractice.gps;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gejiahui.androidpractice.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/6/27.
 */
public class GpsActivity extends AppCompatActivity {
    final static String Provider = LocationManager.GPS_PROVIDER;
    final static String TAG = "GpsActivity";

    @Bind(R.id.list)
    ListView mList;
    @Bind(R.id.location)
    TextView mTextLocation;
    GpsAdapter mAdapter;
    List<GpsSatellite> mSatelliteList = new ArrayList<>();
    LocationManager locationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        ButterKnife.bind(this);
        mAdapter = new GpsAdapter(mSatelliteList);
        mList.setAdapter(mAdapter);
        initGpsSetting();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeGpsStatusListener(statusListener);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(locationListener);
    }

    private void initGpsSetting() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "请开启GPS！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 0); // 此为设置完成后返回到获取界面
        }

        Toast.makeText(this, "GPS模块正常", Toast.LENGTH_SHORT).show();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(Provider, 1000, 1, locationListener);

        locationManager.addGpsStatusListener(statusListener);
    };

    private final GpsStatus.Listener statusListener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) { // GPS状态变化时的回调，如卫星数
            GpsStatus status = locationManager.getGpsStatus(null); //取当前状态
            if(event == GpsStatus.GPS_EVENT_SATELLITE_STATUS){
                int maxSatellites = status.getMaxSatellites();
                Iterator<GpsSatellite> it = status.getSatellites().iterator();
                mSatelliteList.clear();
                int count = 0;
                while (it.hasNext() && count <= maxSatellites) {
                    GpsSatellite s = it.next();
//                Log.i("gjh","~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
//                Log.i("gjh","信号：" + s.getSnr());
//                Log.i("gjh","序号：" + s.getPrn());
//                Log.i("gjh","卫星仰角：" + s.getElevation());
//                Log.i("gjh","卫星方位角 ：" + s.getAzimuth());
//                Log.i("gjh","^^^^^^^^^^^^^^^^^^^^^^^^^^^^" );
                    mSatelliteList.add(s);
                    count++;
                }
            }
            mAdapter.notifyDataSetChanged();
            Log.i(TAG,mSatelliteList.size()+"" );
        }
    };

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            mTextLocation.setText(gpsTransform(lat) + "N   " + gpsTransform(lng) + "E");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private String gpsTransform(double in){
        String res = "";
        res += (int)(in) + "°";
        res += (int)(in%1*100) + ".";
        res += (int)(in*100%1*10000);
        return res;
    }

}
