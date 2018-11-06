package com.vartyr.goblinwarlordsimulator;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.ads.listeners.BannerAdEventListener;
import com.inmobi.ads.listeners.InterstitialAdEventListener;
import com.inmobi.sdk.InMobiSdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class AdManager{


    public String LOG_TAG = "[ADMANAGER]";
    public GameStateManager mm;

    public String IM_ACCOUNTID = "d49db34c0ba345adb369335a51aadb7e";
    public Long IM_BANNER = 1540966827839L;
    public Long IM_SWIPER = 1540834523205L;     // Don't use this for now.
    public Long IM_VIDEO = 1542489410655L;      // Placeholder to use later


    // static variable single_instance of type Singleton
    private static AdManager instance = null;

    // private constructor restricted to this class itself
    private AdManager()
    { }

    // static method to create instance of Singleton class
    public static AdManager getInstance()
    {
        if (instance == null)
            instance = new AdManager();

        return instance;
    }


    // AD METHODS



    // Initialize the Ad SDKs
    public void initAdSDK(Context ctx) {

        initInMobi(ctx);        // Init the InMobi network
        // initAerServ(ctx);       // Init the AerServ network

    }



    /*

    SDK INIT

     */

    // Init InMobi
    private void initInMobi(Context ctx) {

            JSONObject consentObject = new JSONObject();
            try {
                // Provide correct consent value to sdk which is obtained by User
                consentObject.put(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE, true);
                // Provide 0 if GDPR is not applicable and 1 if applicable
                consentObject.put("gdpr", "0");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            InMobiSdk.init(ctx, IM_ACCOUNTID, consentObject);

            Log.d(LOG_TAG, "initInMobi called");

            // DEBUG MODE TOGGLED ON
            InMobiSdk.setLogLevel(InMobiSdk.LogLevel.DEBUG);
    }


    private void initAerServ(Context ctx) {

        // TODO: Init the AerServ SDK

        Log.d(LOG_TAG, "initAerServ called");

    }




    /*

    GDPR handling

     */


    // TODO: plugin in this method when applicable
    public void updateGDPRConsent_InMobi(JSONObject consentObject){
        InMobiSdk.updateGDPRConsent(consentObject);
    }


    /* BANNER METHODS */



    // TODO: Extend and improve these methods to support other SDKs



    public View getBanner(Context ctx, int mode){
        Log.d(LOG_TAG, "getBanner called");
        return createInMobiBanner(ctx, mode);
    }


    public void showBanner(View v){
        Log.d(LOG_TAG, "showBanner called");
        loadInMobiBanner(v);
    }

    public void pauseBanner(View v) {
        Log.d(LOG_TAG, "pauseBanner called");
        pauseInMobiBanner(v);
    }

    public void resumeBanner(View v){
        Log.d(LOG_TAG, "resumeBanner called");
        resumeInMobiBanner(v);
    }

    public void destroyBanner(View v){
        Log.d(LOG_TAG, "destroyBanner called");
        destroyInMobiBanner(v);
    }



    /* BANNER METHODS */





    public Object getInterstitial(Context ctx){
        Log.d(LOG_TAG, "getInterstitial called");
        return createInMobiInterstitial(ctx);
    }

    public void preloadInterstitial(Object o){
        Log.d(LOG_TAG, "preloadInterstitial called");
        preloadInMobiInterstitial(o);
    }

    public void showInterstitial(Object o){
        Log.d(LOG_TAG, "showInterstitial called");
        showInMobiInterstitial(o);
    }

    public void pauseInterstitial(Object o){
        Log.d(LOG_TAG, "pauseInterstitial called");
        // Doesn't do anything right now
        pauseInMobiInterstitial(o);

    }

    public void resumeInterstitial(Object o){
        Log.d(LOG_TAG, "resumeInterstitial called");
        // Doesn't do anything right now
        resumeInMobiInterstitial(o);
    }

    public void cleanupInterstitial(Object o){
        Log.d(LOG_TAG, "cleanupInterstitial called");
        destroyInMobiInterstitial(o);

    }



    //   inmobi banner ad section

    private View createInMobiBanner(Context ctx, int mode){
        Log.d(LOG_TAG, "createInMobiBanner called");

        InMobiBanner banner;

        if (mode == 0){
            banner = new InMobiBanner(ctx,IM_BANNER);
            Log.d(LOG_TAG, "createInMobiBanner IM_BANNER: " + IM_BANNER.toString());
        } else if (mode == 1) {
            banner = new InMobiBanner(ctx,IM_SWIPER);
            Log.d(LOG_TAG, "createInMobiBanner IM_SWIPER: " + IM_SWIPER.toString());
        } else {
            banner = new InMobiBanner(ctx,IM_BANNER);
        }

        banner.setListener(new BannerAdEventListener() {
            @Override
            public void onAdLoadSucceeded(InMobiBanner inMobiBanner) {
                super.onAdLoadSucceeded(inMobiBanner);
                    mm = GameStateManager.getInstance();
                    mm.incrementNumBannerSwiped();

            }

            @Override
            public void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus) {
                super.onAdLoadFailed(inMobiBanner, inMobiAdRequestStatus);
                Log.d(LOG_TAG, "Banner ad failed to load with error: " +
                        inMobiAdRequestStatus.getMessage());
            }

            @Override
            public void onAdClicked(InMobiBanner inMobiBanner, Map<Object, Object> map) {
                super.onAdClicked(inMobiBanner, map);
                Log.d(LOG_TAG, "onAdClicked");
            }

            @Override
            public void onAdDisplayed(InMobiBanner inMobiBanner) {
                super.onAdDisplayed(inMobiBanner);
                Log.d(LOG_TAG, "onAdDisplayed");
            }

            @Override
            public void onAdDismissed(InMobiBanner inMobiBanner) {
                super.onAdDismissed(inMobiBanner);
                Log.d(LOG_TAG, "onAdDismissed");
            }

            @Override
            public void onUserLeftApplication(InMobiBanner inMobiBanner) {
                super.onUserLeftApplication(inMobiBanner);
                Log.d(LOG_TAG, "onUserLeftApplication");
            }

            @Override
            public void onRewardsUnlocked(InMobiBanner inMobiBanner, Map<Object, Object> map) {
                super.onRewardsUnlocked(inMobiBanner, map);
                Log.d(LOG_TAG, "onRewardsUnlocked");
            }
        });
        banner.setRefreshInterval(0);
        return banner;
    }

    private void loadInMobiBanner(View v){
        Log.d(LOG_TAG, "showInMobiBanner called");

        if (v != null) {
            InMobiBanner banner = (InMobiBanner) v;
            banner.load();
        } else {
            Log.e(LOG_TAG, "loadInMobiBanner called, but the banner was null");
        }


    }

    private void pauseInMobiBanner(View v){
        Log.d(LOG_TAG, "pauseInMobiBanner called");

        if (v != null) {
            InMobiBanner banner = (InMobiBanner) v;
            banner.pause();
        } else {
            Log.e(LOG_TAG, "pauseInMobiBanner called, but the banner was null");

        }
    }

    private void resumeInMobiBanner(View v){
        Log.d(LOG_TAG, "resumeInMobiBanner called");
        if (v != null) {
            InMobiBanner banner = (InMobiBanner) v;
            banner.resume();
        } else {
            Log.e(LOG_TAG, "resumeInMobiBanner called, but the banner was null");
        }
    }

    private void destroyInMobiBanner(View v){
        Log.d(LOG_TAG, "destroyInMobiBanner called");
        if (v != null) {
            InMobiBanner banner = (InMobiBanner) v;
            banner = null; // Clean up the InMobi Banner
        } else {
            Log.e(LOG_TAG, "destroyInMobiBanner called, but the banner was null");
        }

    }


    private Object createInMobiInterstitial(Context ctx) {

        Log.d(LOG_TAG, "createInMobiInterstitial called");

        InMobiInterstitial interstitialAd = new InMobiInterstitial(ctx, IM_VIDEO, new InterstitialAdEventListener() {
            @Override
            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
                super.onAdLoadSucceeded(inMobiInterstitial);
            }

            @Override
            public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
            }

            @Override
            public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
                super.onAdReceived(inMobiInterstitial);
            }

            @Override
            public void onAdClicked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                super.onAdClicked(inMobiInterstitial, map);
            }

            @Override
            public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {
                super.onAdWillDisplay(inMobiInterstitial);

            }

            @Override
            public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
                super.onAdDisplayed(inMobiInterstitial);
                mm = GameStateManager.getInstance();
                mm.incrementNumVideosWatched();
            }

            @Override
            public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
                super.onAdDisplayFailed(inMobiInterstitial);
            }

            @Override
            public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
                super.onAdDismissed(inMobiInterstitial);
            }

            @Override
            public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {
                super.onUserLeftApplication(inMobiInterstitial);
            }

            @Override
            public void onRewardsUnlocked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                super.onRewardsUnlocked(inMobiInterstitial, map);
            }

            @Override
            public void onRequestPayloadCreated(byte[] bytes) {
                super.onRequestPayloadCreated(bytes);
            }

            @Override
            public void onRequestPayloadCreationFailed(InMobiAdRequestStatus inMobiAdRequestStatus) {
                super.onRequestPayloadCreationFailed(inMobiAdRequestStatus);
            }
        });

        return interstitialAd;
    }


    private void preloadInMobiInterstitial(Object o){
        Log.d(LOG_TAG, "preloadInMobiInterstitial called");
        if (o != null) {
            InMobiInterstitial interstitial = (InMobiInterstitial) o;
            interstitial.load();
        }
    }


    private void showInMobiInterstitial(Object o){
        Log.d(LOG_TAG, "showInMobiInterstitial called");

        if (o != null) {
            InMobiInterstitial interstitial = (InMobiInterstitial) o;
            if (interstitial.isReady()){
                interstitial.show();
            } else {
                Log.e(LOG_TAG, "showInMobiInterstitial , but interstitial was not ready!");
            }
        }

    }


    // JC: Unused method, we cannot control inmobi vids
    private void pauseInMobiInterstitial(Object o){
        Log.d(LOG_TAG, "pauseInMobiInterstitial called");
        if (o != null) {
            InMobiInterstitial interstitial = (InMobiInterstitial) o;

        }


    }

    // JC: Unused method, we cannot control inmobi vids
    private void resumeInMobiInterstitial(Object o){
        Log.d(LOG_TAG, "resumeInMobiInterstitial called");
        if (o != null) {
            InMobiInterstitial interstitial = (InMobiInterstitial) o;

        }
    }

    private void destroyInMobiInterstitial(Object o){
        Log.d(LOG_TAG, "destroyInMobiInterstitial called");
        if (o != null) {
            InMobiInterstitial interstitial = (InMobiInterstitial) o;
            interstitial = null;
        }
    }





}
