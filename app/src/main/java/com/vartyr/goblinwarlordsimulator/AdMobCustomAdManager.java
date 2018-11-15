package com.vartyr.goblinwarlordsimulator;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdMobCustomAdManager implements CustomAdManager {

    public String LOG_TAG = "[ADMOB ADMANAGER]";

    public GameStateManager gameStateManager;

    public String AM_ACCOUNTID = "ca-app-pub-6262016322799480~4052083088";
    public String AM_TESTBANNER = "ca-app-pub-3940256099942544/6300978111";
    public String AM_BANNER = "ca-app-pub-6262016322799480/3229956002";
    public String AM_SWIPER = "";     // TODO: Implement this later.
    public String AM_TESTVIDEO = "ca-app-pub-3940256099942544/1033173712";
    public String AM_VIDEO = "ca-app-pub-6262016322799480/3776750918";

    private OnPreloadCallbackHandler listener;      // Get a reference to the a preloadCBHandler object

    @Override
    public void initCustomSDK(Context ctx) {

        MobileAds.initialize(ctx, AM_ACCOUNTID);
        gameStateManager = GameStateManager.getInstance();
    }

    @Override
    public void setCustomListener(OnPreloadCallbackHandler handler) {
        Log.d(LOG_TAG, "setCustomListener to handler");
        this.listener = handler;
    }

    @Override
    public View getCustomBanner(Context ctx, int mode) {

        Log.d(LOG_TAG, "getCustomBanner called");

        AdView adView = new AdView(ctx);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AM_TESTBANNER);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                gameStateManager.incrementNumBannerSwiped();
                gameStateManager.increaseTotalCurrencyByProvidedAmount(gameStateManager.BANNER_SWIPED_CURRENCY_AMT);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        return adView;
    }

    @Override
    public void showCustomBanner(View v) {

        Log.d(LOG_TAG, "showCustomBanner called");


        if (v != null){
            AdView mAdView = (AdView) v;
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        } else {
            Log.e(LOG_TAG, "showCustomBanner called, but the banner was null");
        }



    }

    @Override
    public void pauseCustomBanner(View v) {
        // nothing for this
        Log.d(LOG_TAG, "pauseCustomBanner called, method not supported / implemented");
    }

    @Override
    public void resumeCustomBanner(View v) {
        Log.d(LOG_TAG, "resumeCustomBanner called, method not supported / implemented");
    }

    @Override
    public void destroyCustomBanner(View v) {
        Log.d(LOG_TAG, "destroyCustomBanner called, method not supported / implemented");
    }

    @Override
    public Object getInterstitial(Context ctx) {

        Log.d(LOG_TAG, "getInterstitial called");

        InterstitialAd mInterstitialAd = new InterstitialAd(ctx);
        mInterstitialAd.setAdUnitId(AM_TESTVIDEO);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d(LOG_TAG, "onAdLoadSucceeded called, called listener on preload ready");
                listener.onInterstitialPreloadReady();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.d(LOG_TAG, "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.d(LOG_TAG, "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.d(LOG_TAG, "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Log.d(LOG_TAG, "onAdClosed");
                gameStateManager.incrementNumVideosWatched();
                gameStateManager.increaseTotalCurrencyByProvidedAmount(gameStateManager.VIDEO_REWARD_CURRENCY_AMT);
            }
        });

        return mInterstitialAd;

    }

    @Override
    public void preloadInterstitial(Object o) {

        if (o != null){
            InterstitialAd mInterstitialAd = (InterstitialAd) o;
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        } else {
            Log.e(LOG_TAG, "preloadInterstitial called, but the interstitial was null");
        }

    }

    @Override
    public void showInterstitial(Object o) {

        Log.d(LOG_TAG, "getInterstitial called");

        if (o != null){
            InterstitialAd mInterstitialAd = (InterstitialAd) o;
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d(LOG_TAG, "mInterstitialAd is not yet loaded");
            }
        } else {
            Log.e(LOG_TAG, "showInterstitial called, but the interstitial was null");
        }

    }

    @Override
    public void pauseInterstitial(Object o) {

        Log.d(LOG_TAG, "pauseInterstitial called, method not supported / implemented");
    }

    @Override
    public void resumeInterstitial(Object o) {
        Log.d(LOG_TAG, "resumeInterstitial called, method not supported / implemented");
    }

    @Override
    public void cleanupInterstitial(Object o) {
        Log.d(LOG_TAG, "cleanupInterstitial called, method not supported / implemented");
    }
}
