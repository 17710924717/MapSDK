package com.xyz.amap;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.xyz.maplib.location.BaseMapILocation;
import com.xyz.maplib.location.MapLocation;


/**
 * Created by ZP on 2018/1/15.
 */

public class AMapLocationAdapter extends BaseMapILocation {
    private static final String TAG = "AMapLocationAdapter";
    private AMapLocationClient mAMapLocationClient;


    private AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(com.amap.api.location.AMapLocation aMapLocation) {
            Log.d(TAG, "onLocationChanged: " + aMapLocation.toString());
            MapLocation location = convert(aMapLocation);
            mMapLocationListener.onLocationChanged(location);
        }
    };

    public AMapLocationAdapter(Context context) {
        super(context);
        mAMapLocationClient = new AMapLocationClient(mContext);
        mAMapLocationClient.setLocationListener(mAMapLocationListener);
    }

    @Override
    public void startLocation() {
        Log.d(TAG, "startLocation: ");
        mAMapLocationClient.startLocation();
    }

    @Override
    public void stopLocation() {
        Log.d(TAG, "stopLocation: ");
        mAMapLocationClient.stopLocation();
    }

    private MapLocation convert(com.amap.api.location.AMapLocation aMapLocation) {
        MapLocation location = new MapLocation();
        location.setCountry(aMapLocation.getCountry());
//        location.setCountryCode(baiduLocation.getCountryCode());
        location.setProvince(aMapLocation.getProvince());
        location.setCity(aMapLocation.getCity());
        location.setCityCode(aMapLocation.getCityCode());
        location.setDistrict(aMapLocation.getDistrict());
        location.setLatitude(aMapLocation.getLatitude());
        location.setLongitude(aMapLocation.getLongitude());
        location.setAdCode(aMapLocation.getAdCode());
        location.setRadius(aMapLocation.getAccuracy());
        location.setHasSpeed(aMapLocation.hasSpeed());
        location.setSpeed(aMapLocation.getSpeed());
        location.setLocationDescribe(aMapLocation.getDescription());
        return location;
    }
}
