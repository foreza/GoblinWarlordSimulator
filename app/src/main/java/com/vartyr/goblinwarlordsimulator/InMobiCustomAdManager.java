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

public class InMobiCustomAdManager implements CustomAdManager {


    public String LOG_TAG = "[ADMANAGER]";

    public GameStateManager gameStateManager;

    public String IM_ACCOUNTID = "d49db34c0ba345adb369335a51aadb7e";
    public Long IM_BANNER = 1540966827839L;
    public Long IM_SWIPER = 1540834523205L;     // TODO: Implement this later.
    public Long IM_VIDEO = 1542489410655L;

    private OnPreloadCallbackHandler listener;      // Get a reference to the a preloadCBHandler object


    @Override
    public void setCustomListener(OnPreloadCallbackHandler handler) {
        Log.d(LOG_TAG, "setCustomListener to handler");
        this.listener = handler;
    }

    @Override
    public void initCustomSDK(Context ctx) {

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

    @Override
    public View getCustomBanner(Context ctx, int mode) {
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
                Log.d(LOG_TAG, "onAdLoadSucceeded");
                gameStateManager = GameStateManager.getInstance();
                gameStateManager.incrementNumBannerSwiped();
                gameStateManager.increaseTotalCurrencyByProvidedAmount(gameStateManager.BANNER_SWIPED_CURRENCY_AMT);


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
        banner.setRefreshInterval(10000);
        return banner;
    }

    @Override
    public void showCustomBanner(View v) {

        Log.d(LOG_TAG, "showInMobiBanner called");

        if (v != null) {
            InMobiBanner banner = (InMobiBanner) v;
            banner.load();
        } else {
            Log.e(LOG_TAG, "loadInMobiBanner called, but the banner was null");
        }

    }

    @Override
    public void pauseCustomBanner(View v) {
        Log.d(LOG_TAG, "pauseInMobiBanner called");

        if (v != null) {
            InMobiBanner banner = (InMobiBanner) v;
            banner.pause();
        } else {
            Log.e(LOG_TAG, "pauseInMobiBanner called, but the banner was null");

        }
    }

    @Override
    public void resumeCustomBanner(View v) {
        Log.d(LOG_TAG, "resumeInMobiBanner called");
        if (v != null) {
            InMobiBanner banner = (InMobiBanner) v;
            banner.resume();
        } else {
            Log.e(LOG_TAG, "resumeInMobiBanner called, but the banner was null");
        }
    }

    @Override
    public void destroyCustomBanner(View v) {
        Log.d(LOG_TAG, "destroyInMobiBanner called");
        if (v != null) {
            InMobiBanner banner = (InMobiBanner) v;
            banner = null; // Clean up the InMobi Banner
        } else {
            Log.e(LOG_TAG, "destroyInMobiBanner called, but the banner was null");
        }
    }

    @Override
    public Object getInterstitial(Context ctx) {
        Log.d(LOG_TAG, "createInMobiInterstitial called");

        InMobiInterstitial interstitialAd = new InMobiInterstitial(ctx, IM_VIDEO, new InterstitialAdEventListener() {
            @Override
            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
                super.onAdLoadSucceeded(inMobiInterstitial);
                Log.d(LOG_TAG, "onAdLoadSucceeded called, called listener on preload ready");
                listener.onInterstitialPreloadReady();
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
                gameStateManager = GameStateManager.getInstance();
                gameStateManager.incrementNumVideosWatched();
                gameStateManager.increaseTotalCurrencyByProvidedAmount(gameStateManager.VIDEO_REWARD_CURRENCY_AMT);
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

    @Override
    public void preloadInterstitial(Object o) {
        Log.d(LOG_TAG, "preloadInMobiInterstitial called");
        if (o != null) {
            InMobiInterstitial interstitial = (InMobiInterstitial) o;
            interstitial.load();
        }
    }

    @Override
    public void showInterstitial(Object o) {
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

    @Override
    public void pauseInterstitial(Object o) {
        Log.d(LOG_TAG, "pauseInMobiInterstitial called");
        if (o != null) {
            InMobiInterstitial interstitial = (InMobiInterstitial) o;

        }
    }

    @Override
    public void resumeInterstitial(Object o) {
        Log.d(LOG_TAG, "resumeInMobiInterstitial called");
        if (o != null) {
            InMobiInterstitial interstitial = (InMobiInterstitial) o;

        }
    }

    @Override
    public void cleanupInterstitial(Object o) {
        Log.d(LOG_TAG, "destroyInMobiInterstitial called");
        if (o != null) {
            InMobiInterstitial interstitial = (InMobiInterstitial) o;
            interstitial = null;
        }
    }
}
