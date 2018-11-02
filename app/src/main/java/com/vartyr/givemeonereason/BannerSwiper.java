package com.vartyr.givemeonereason;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class BannerSwiper extends AppCompatActivity implements MonetizedStats.OnFragmentInteractionListener {

    public FragmentManager fragmentManager;
    public MonetizationManager mm;         // Singleton instance that will allow us to sync the value and increment globally
    public AdManager adManager;
    public String LOG_TAG = "[GMOR]";

    public View bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_swiper);

        mm = MonetizationManager.getInstance();
        fragmentManager = getSupportFragmentManager();      // Get the fragment manager
        loadStatsFragment();                                // Load the stats fragment
        doSomeUpdateTest();
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


    public void doSomeUpdateTest(){
        mm.incrementNumBannerSwiped();
    }

}
