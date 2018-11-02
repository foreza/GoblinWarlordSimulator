package com.vartyr.givemeonereason;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.sdk.InMobiSdk;

import org.json.JSONException;
import org.json.JSONObject;

public class AdManager {

    public String LOG_TAG = "[ADMANAGER]";


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


    /*


    BANNER METHODS


     */



    // TODO: Extend and improve these methods to support other SDKs



    public View getBanner(Context ctx){
        Log.d(LOG_TAG, "getBanner called");
        return createInMobiBanner(ctx);
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





    //   inmobi banner ad section

    private View createInMobiBanner(Context ctx){
        Log.d(LOG_TAG, "createInMobiBanner called");
        InMobiBanner banner = new InMobiBanner(ctx,IM_BANNER);
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





    /*


    LISTENER METHODS


     */


//
//    /**
//     * A listener for receiving notifications during the lifecycle of a banner ad.
//     */
//    public abstract class BannerAdEventListener {
//        /**
//         * Called to notify that an ad was successfully loaded.
//         * @param ad Represents the {@link InMobiBanner} ad which was loaded
//         */
//        public void onAdLoadSucceeded(InMobiBanner ad) {}
//
//        /**
//         * Called to notify that a request to load an ad failed.
//         * @param ad Represents the {@link InMobiBanner} ad which failed to load
//         * @param status Represents the {@link InMobiAdRequestStatus} status containing error reason
//         */
//        public void onAdLoadFailed(InMobiBanner ad, InMobiAdRequestStatus status) {}
//
//        /**
//         * Called to notify that the user interacted with the ad.
//         * @param ad Represents the {@link InMobiBanner} ad on which user clicked
//         * @param params Represents the click parameters
//         */
//        public void onAdClicked(InMobiBanner ad, Map params) {}
//
//        /**
//         * Called to notify that the banner ad was displayed
//         * @param ad Represents the {@link InMobiBanner} ad which was displayed
//         */
//        public void onAdDisplayed(InMobiBanner ad) {}
//
//        /**
//         * Called to notify that the User is about to return to the application after closing the ad.
//         * @param ad Represents the {@link InMobiBanner} ad which was closed
//         */
//        public void onAdDismissed(InMobiBanner ad) {}
//
//        /**
//         * Called to notify that the user is about to leave the application as a result of interacting with the ad.
//         * @param ad Represents the {@link InMobiBanner} ad
//         */
//        public void onUserLeftApplication(InMobiBanner ad) {
//
//
//
//        }
//
//        /**
//         * Called to notify that a reward was unlocked.
//         * @param ad Represents the {@link InMobiBanner} ad for which rewards was unlocked
//         * @param rewards Represents the rewards unlocked
//         */
//        public void onRewardsUnlocked(InMobiBanner ad, Map rewards) {}
//    }

}
