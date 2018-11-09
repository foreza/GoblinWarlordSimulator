package com.vartyr.goblinwarlordsimulator;

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

import java.util.Timer;
import java.util.TimerTask;

public class BannerSwiper extends AppCompatActivity implements FragmentWarlordMonetizedStats.OnFragmentInteractionListener {

    public FragmentManager fragmentManager;
    public AdManager adManager;
    public String LOG_TAG = "[BannerSwiperStat]";
    public String refTag = "BannerSwiperStat";
    private GestureDetectorCompat mDetector;    // Detect flings

    public View bannerView;
    public boolean canSwipe = true;

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


            FragmentWarlordMonetizedStats f = (FragmentWarlordMonetizedStats)getSupportFragmentManager().findFragmentByTag(refTag);
            if (f != null) {
                f.updateView();
            }


            if (canSwipe) {
                loadBanner();
                return true;
            } else {
                Log.d(LOG_TAG, "Not loading banner, waiting for timer");
                return false;
            }
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
                .add(R.id.statAF, FragmentWarlordMonetizedStats.newInstance(), refTag)
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

            adManager.destroyBanner(bannerView);
            Log.d(LOG_TAG, "Loading banner");

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

            canSwipe = false;
            updateSwipeIndicatorStateInView();

            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    // do something;
                    Log.d(LOG_TAG, "Doing the timer task to delay stupid spamming");
                    canSwipe = true;

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            updateSwipeIndicatorStateInView();
                        }
                    });

                }
            };

            timer.schedule(timerTask, 5000);
    }


    public void updateSwipeIndicatorStateInView(){
        if (canSwipe){
            findViewById(R.id.swipe_bottom).setVisibility(View.VISIBLE);
            findViewById(R.id.swipe_top).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.swipe_bottom).setVisibility(View.INVISIBLE);
            findViewById(R.id.swipe_top).setVisibility(View.INVISIBLE);
        }
    }

}
