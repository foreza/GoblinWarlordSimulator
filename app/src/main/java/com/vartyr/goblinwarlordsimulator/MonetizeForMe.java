package com.vartyr.goblinwarlordsimulator;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

public class MonetizeForMe extends AppCompatActivity implements MonetizedStats.OnFragmentInteractionListener{

    public FragmentManager fragmentManager;
    public AdManager adManager;
    public View bannerView;
    public String LOG_TAG = "[GMOR]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monetize_for_me);
        fragmentManager = getSupportFragmentManager();      // Get the fragment manager
        loadStatsFragment();                                // Load the stat fragment
        adManager = AdManager.getInstance();                // Get the instance of the ad Manager
        loadAndShowBanner();                                // Show a banner on this view as well. Man's gotta eat!
    }


    // JC: Method to load and show a banner from the ad manager
    public void loadAndShowBanner(){

        RelativeLayout adContainer = (RelativeLayout) findViewById(R.id.monetizeAdView);
        Resources r = getResources();
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, r.getDisplayMetrics());
        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
        RelativeLayout.LayoutParams bannerLp = new RelativeLayout.LayoutParams(Math.round(width), Math.round(height));
        bannerLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bannerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bannerView = adManager.getBanner(this, 0); // bottom banner

        adContainer.addView(bannerView, bannerLp);
        adManager.showBanner(bannerView);

    }

    public void loadStatsFragment(){
        fragmentManager
                .beginTransaction()
                .add(R.id.statAF, MonetizedStats.newInstance())
                .commit();
        Log.d(LOG_TAG, "Created stats fragment");
    }

    // JC: Fragment listener does nothing here for now
    public void onFragmentInteraction(){

//        fragmentManager
//                .beginTransaction()
//                .remove(getSupportFragmentManager().findFragmentById(R.id.addAF))
//                .commit();
    }


    public void openBannerSwiper(View view) {

        Intent intent = new Intent(this, BannerSwiper.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Going to openBannerSwiper page");


    }

    public void openVideoWatcher(View view) {

        Intent intent = new Intent(this, VideoWatcher.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Going to openVideoWatcher page");


    }

    public void openEndlessScroller(View view) {

        Intent intent = new Intent(this, EndlessScroller.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Going to openEndlessScroller page");


    }



}
