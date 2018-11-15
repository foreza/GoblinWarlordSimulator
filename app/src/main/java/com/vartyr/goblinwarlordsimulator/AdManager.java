package com.vartyr.goblinwarlordsimulator;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.inmobi.sdk.InMobiSdk;
import org.json.JSONObject;



public class AdManager{

    public String LOG_TAG = "[ADMANAGER]";

    public CustomAdManager customAdManager;         // The current active ad manager

    // TODO: All the various different admanagers should be declared here
    public InMobiCustomAdManager IMADManager;       // Reference to our InMobi Custom Ad manager

    public AdMobCustomAdManager AMADManager;        // Reference to our AdMob Custom Ad Manager


    // Method for people to call to invoke
    public void setPreloadCallbackListener(OnPreloadCallbackHandler listener) {
    Log.d(LOG_TAG, "setPreloadCallbackListener, using the IMAdManager");

        // TODO: Each AdManager should be subscribed to the listener
        IMADManager.setCustomListener(listener);

        AMADManager.setCustomListener(listener);
    }


    // static variable single_instance of type Singleton
    private static AdManager instance = null;

    // private constructor restricted to this class itself
    private AdManager()
    { }

    // static method to create instance of Singleton class
    public static AdManager getInstance()
    {
        if (instance == null){
            instance = new AdManager();
        }

        return instance;
    }


    // Initialize the Ad SDKs
    public void initAdSDK(Context ctx) {



        // TODO: Each AdManager should be instantiated and initialized here

        // Init InMobi
        IMADManager = new InMobiCustomAdManager();
        IMADManager.initCustomSDK(ctx);

        AMADManager = new AdMobCustomAdManager();
        AMADManager.initCustomSDK(ctx);

        // TODO: Implement logic to let us dynamically swap out different ad managers.

        setCurrentCustomAdManager();

    }

    // Private method to let us set the current custom ad manager
    private void setCurrentCustomAdManager(){

        // Phase 1: We currently only support InMobi
//        customAdManager = (CustomAdManager) IMADManager;


        customAdManager = (CustomAdManager) AMADManager;

    }


    /* BANNER METHODS */

    // TODO: Extend and improve these methods to support other SDKs

    public View getBanner(Context ctx, int mode){
        Log.d(LOG_TAG, "getBanner called");
        return customAdManager.getCustomBanner(ctx, mode);
    }


    public void showBanner(View v){
        Log.d(LOG_TAG, "showBanner called");
        customAdManager.showCustomBanner(v);
    }

    public void pauseBanner(View v) {
        Log.d(LOG_TAG, "pauseBanner called");
        customAdManager.pauseCustomBanner(v);
    }

    public void resumeBanner(View v){
        Log.d(LOG_TAG, "resumeBanner called");
        customAdManager.resumeCustomBanner(v);
    }

    public void destroyBanner(View v){
        Log.d(LOG_TAG, "destroyBanner called");
        customAdManager.destroyCustomBanner(v);
    }



    /* Interstitial METHODS */


    public Object getInterstitial(Context ctx){
        Log.d(LOG_TAG, "getInterstitial called");
        return customAdManager.getInterstitial(ctx);
    }

    public void preloadInterstitial(Object o){
        Log.d(LOG_TAG, "preloadInterstitial called");
        customAdManager.preloadInterstitial(o);
    }

    public void showInterstitial(Object o){
        Log.d(LOG_TAG, "showInterstitial called");
        customAdManager.showInterstitial(o);
    }

    public void pauseInterstitial(Object o){
        Log.d(LOG_TAG, "pauseInterstitial called");
        // Doesn't do anything right now
    }

    public void resumeInterstitial(Object o){
        Log.d(LOG_TAG, "resumeInterstitial called");
        // Doesn't do anything right now
    }

    public void cleanupInterstitial(Object o){
        Log.d(LOG_TAG, "cleanupInterstitial called");
        customAdManager.cleanupInterstitial(o);
    }



}
