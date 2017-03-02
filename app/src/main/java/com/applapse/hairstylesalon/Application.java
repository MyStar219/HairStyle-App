package com.applapse.hairstylesalon;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.HashMap;

/**
 * Created by Administrator on 09/11/2016.
 */
public class Application extends android.app.Application {
    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public synchronized Tracker getTracker(TrackerName trackerId) {

        if (!mTrackers.containsKey(trackerId)) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = analytics.newTracker(R.xml.app_tracker);
            mTrackers.put(trackerId, t);
        }
        return mTrackers.get(trackerId);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }
    public static void initImageLoader(Context context) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .displayer(new FadeInBitmapDisplayer(600)) // default
                .build();
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
                context);
        config.defaultDisplayImageOptions(options);
        ImageLoader.getInstance().init(config.build());
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    public enum TrackerName {
        APP_TRACKER,
        GLOBAL_TRACKER,
        E_COMMERCE_TRACKER,
    }
}
