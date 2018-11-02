package com.vartyr.givemeonereason;

import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

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

        adManager = AdManager.getInstance();
        mm = MonetizationManager.getInstance();
        fragmentManager = getSupportFragmentManager();      // Get the fragment manager
        loadStatsFragment();                                // Load the stats fragment
        doSomeUpdateTest();
        loadBanner();
    }

    @Override
    protected void onPause(){
        super.onPause();
        adManager.pauseBanner(bannerView);

    }


    @Override
    protected void onResume(){
        super.onResume();
        adManager.resumeBanner(bannerView);

    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        adManager.destroyBanner(bannerView);

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


    public void loadBanner(){

        RelativeLayout adContainer = (RelativeLayout) findViewById(R.id.banner_swipe_region);
        Resources r = getResources();
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, r.getDisplayMetrics());
        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, r.getDisplayMetrics());
        float margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());

        RelativeLayout.LayoutParams bannerLp = new RelativeLayout.LayoutParams(Math.round(width), Math.round(height));
        bannerLp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        bannerLp.setMargins(0,Math.round(margin),0, Math.round(margin));
        bannerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bannerView = adManager.getBanner(this);
        adContainer.addView(bannerView, bannerLp);
        adManager.showBanner(bannerView);

    }



    public void doSomeUpdateTest(){
//        mm.incrementNumBannerSwiped();
    }

}
