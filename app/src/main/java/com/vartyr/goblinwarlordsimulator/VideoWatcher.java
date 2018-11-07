package com.vartyr.goblinwarlordsimulator;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class VideoWatcher extends AppCompatActivity implements FragmentWarlordMonetizedStats.OnFragmentInteractionListener {

    public FragmentManager fragmentManager;
    public AdManager adManager;
    public String LOG_TAG = "[GMOR]";
    public String refTag = "VidWatcherStat";

    Object interstitialObject;          // Generic object to hold any type of interstitial we plan to show.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_watcher);


        adManager = AdManager.getInstance();

        fragmentManager = getSupportFragmentManager();
        loadStatsFragment();

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
    }


    public void showInterstitial(View view){
        adManager.showInterstitial(interstitialObject);

    }

    public void onFragmentInteraction(){

//        fragmentManager
//                .beginTransaction()
//                .remove(getSupportFragmentManager().findFragmentById(R.id.addAF))
//                .commit();
    }




}
