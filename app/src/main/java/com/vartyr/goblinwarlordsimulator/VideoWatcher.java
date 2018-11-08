package com.vartyr.goblinwarlordsimulator;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class VideoWatcher extends AppCompatActivity implements FragmentWarlordMonetizedStats.OnFragmentInteractionListener {





    public FragmentManager fragmentManager;
    public AdManager adManager;
    AdManager.OnPreloadCallbackHandler adListener;
    public String LOG_TAG = "[VidWatcherStat]";
    public String refTag = "VidWatcherStat";

    public boolean preloadReady = false;
    public boolean isPreloading = false;






    Object interstitialObject;          // Generic object to hold any type of interstitial we plan to show.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_watcher);


        adManager = AdManager.getInstance();
        adManager.setPreloadCallbackListener(new AdManager.OnPreloadCallbackHandler() {
            @Override
            public void onPreloadReady() {
                // do the thing
                Log.d(LOG_TAG, "onPreloadReady received!!");
                preloadReady = true;
                isPreloading = false;
                updateButtonStateInView();
            }
        });

        fragmentManager = getSupportFragmentManager();
        loadStatsFragment();

        // Begin preloading an interstitial on view creation.

        interstitialObject = adManager.getInterstitial(this);
        adManager.preloadInterstitial(interstitialObject);

        // Make sure the view is up to date.
        updateButtonStateInView();



    }

    @Override
    protected void onResume(){
        super.onResume();

        FragmentWarlordMonetizedStats f = (FragmentWarlordMonetizedStats)getSupportFragmentManager().findFragmentByTag(refTag);
        if (f != null) {
            f.updateView();
        }
    }

    public void loadStatsFragment(){
        fragmentManager
                .beginTransaction()
                .add(R.id.statAF, FragmentWarlordMonetizedStats.newInstance(), refTag)
                .commit();
        Log.d(LOG_TAG, "Created stats fragment");
    }


    public void preloadInterstitial(View view){
        interstitialObject = adManager.getInterstitial(this);
        adManager.preloadInterstitial(interstitialObject);
        isPreloading = true;
        updateButtonStateInView();
    }


    public void showInterstitial(View view){
        if (preloadReady){
            adManager.showInterstitial(interstitialObject);
            preloadReady = false;
            isPreloading = false;
            updateButtonStateInView();
        }
    }

    public void updateButtonStateInView(){
        if (preloadReady && !isPreloading){
            findViewById(R.id.video_play).setVisibility(View.VISIBLE);
            findViewById(R.id.video_load).setVisibility(View.INVISIBLE);
            findViewById(R.id.loadingSpinner).setVisibility(View.INVISIBLE);
        } else if (!preloadReady && isPreloading) { // NOTE: The IM SDK precaches, so there's no way to see this unless we wipe app data
            findViewById(R.id.video_play).setVisibility(View.INVISIBLE);
            findViewById(R.id.video_load).setVisibility(View.INVISIBLE);
            findViewById(R.id.loadingSpinner).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.video_play).setVisibility(View.INVISIBLE);
            findViewById(R.id.video_load).setVisibility(View.VISIBLE);
            findViewById(R.id.loadingSpinner).setVisibility(View.INVISIBLE);
        }
    }


    public void onFragmentInteraction(){ }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }



}
