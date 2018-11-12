package com.vartyr.goblinwarlordsimulator;

import android.content.Context;
import android.view.View;

public interface CustomAdManager {
    void initCustomSDK(Context ctx);

    /*
    This method sets the listener so we have a reference to bubble back up to the AdManager
    OnPreloadCallbackHandler is another interface that will be implemented by the application to listen.
     */
    void setCustomListener(OnPreloadCallbackHandler handler);

    /*
    Banner methods. banner implemementations will typically subclass View.
    We take in a 'mode' which is more of a 'number' mapping you can use for different placements/zones.
    IE: if you get custom banner (1), you can keep a mapping of the fact that banner 1 = the one on your landing page.
    The implementer is responsible for keeping track of all of these separate instances at this time.

     */
    View getCustomBanner(Context ctx, int mode);
    void showCustomBanner(View v);
    void pauseCustomBanner(View v);
    void resumeCustomBanner(View v);
    void destroyCustomBanner(View v);


    /*
    Interstitial methods. Interstitials typically subclass Object since they aren't really part of a view and are usually a separate activity.

     */

    Object getInterstitial(Context ctx);
    void preloadInterstitial(Object o);
    void showInterstitial(Object o);
    void pauseInterstitial(Object o);
    void resumeInterstitial(Object o);
    void cleanupInterstitial(Object o);


}
