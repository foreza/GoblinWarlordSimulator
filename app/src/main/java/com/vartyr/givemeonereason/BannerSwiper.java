package com.vartyr.givemeonereason;

import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class BannerSwiper extends AppCompatActivity implements MonetizedStats.OnFragmentInteractionListener {

    public FragmentManager fragmentManager;
    public AdManager adManager;
    public String LOG_TAG = "[GMOR]";
    public String refTag = "BannerSwiperStat";
    private GestureDetectorCompat mDetector;    // Detect flings

    public View bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_swiper);

        adManager = AdManager.getInstance();

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        fragmentManager = getSupportFragmentManager();      // Get the fragment manager
        loadStatsFragment();                                // Load the stats fragment
        loadBanner();
    }


    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(LOG_TAG,"onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(LOG_TAG, "onFling: " + event1.toString() + event2.toString());
            loadBanner();
            return true;
        }
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
                .add(R.id.statAF, MonetizedStats.newInstance(), refTag)
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
        bannerView = adManager.getBanner(this, 1);      // Mode == 1 is the MREC
        adContainer.addView(bannerView, bannerLp);
        adManager.showBanner(bannerView);

        MonetizedStats f = (MonetizedStats)getSupportFragmentManager().findFragmentByTag(refTag);
        if (f != null) {
            f.updateView();
        }


    }

}
