package com.vartyr.givemeonereason;

import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;

import java.io.UnsupportedEncodingException;

public class ListOfF extends AppCompatActivity implements AddAF.OnFragmentInteractionListener{


    // TODO: Write the backend. We'll add/remove locally for now for the 1st prototype.
    /*
    // The amount of fucks we give. We'll keep it simple and give you 5 to start with, because let's face it.
    You consider yourself an adult, but you've probably never done this excercise before, yeah?

    For every fuck you identify that you cannot bring yourself to give..
     we'll give you an additional fuck to give.
     Of course, If you tell us what you can't be bothered to give a fuck about.
     */
    int baseCounterOfF = 10;        // Base counter. Modify and balance this.

    /*
    The list of fucks that we actually give and a list of fucks we don't.
    The idea behind this is:
    1) Open the app because you're struggling and don't know if you give a fuck
    2) See a list of fucks that you give?
    3) It's not on the list?
    4) Don't give a fuck. Or if it really means that much to you, * add it as a fuck you give.

    * Do remember you only have a limited amount of fucks to give.

    As for the list of fucks we don't give..
    Sometimes, it's hard. And we know what we give a fuck about.
    But there are certain things that you need to REMIND yourself to not give a fuck about.

    1) Open the app because you seek validation and want to remember your reason for living
    2) See the list of fucks you swore not to give
    3) It's on the list!
    4) Don't give a fuck. And pat yourself on the back.

     */

    public String [] listOfFToGive = {"Job", "Food", "Roommate", "Gym and gym buddies", "Immediate Family", "EX-idtech groupies", "Legal Portal", "Car", "Good fuckin music"};     // List of things we care about.
    public FragmentManager fragmentManager;     // For any fragments we need to call / add
    public String LOG_TAG = "[GMOR]";
    public ViewGroup.LayoutParams lparams;

    String appKey = "610ecf278118c472df8ee14fdf793d9cbc1d883804730296"; // give me one reason


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_f);
        initApp();
        generateListOfF();


        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        String deviceId = MD5(android_id).toUpperCase();

        Log.d(LOG_TAG + "deviceId: ", deviceId);




    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException ex){
        }
        return null;
    }


    @Override
    public void onResume() {
        super.onResume();

        // Resume the AppoDeal banner
        Appodeal.onResume(this, Appodeal.BANNER);
        Appodeal.onResume(this, Appodeal.MREC);
    }


    // JC: A function that will do variable assignment for the scope of this class and call required SDK inits
    public void initApp() {

        fragmentManager = getSupportFragmentManager();
        lparams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // AppoDeal
//        Appodeal.setTesting(true);
        Appodeal.setLogLevel(com.appodeal.ads.utils.Log.LogLevel.debug);

        Appodeal.disableLocationPermissionCheck();
        Appodeal.setBannerViewId(R.id.appodealBannerView);
//        Appodeal.initialize(this, appKey, Appodeal.INTERSTITIAL | Appodeal.REWARDED_VIDEO | Appodeal.BANNER | Appodeal.NATIVE | Appodeal.MREC);
        Appodeal.initialize(this, appKey, Appodeal.BANNER);
        Appodeal.show(this, Appodeal.BANNER_VIEW);

        Appodeal.setBannerCallbacks(new BannerCallbacks() {
            @Override
            public void onBannerLoaded(int height, boolean isPrecache) {
                Log.d(LOG_TAG + "Appodeal", "onBannerLoaded");
            }
            @Override
            public void onBannerFailedToLoad() {
                Log.d(LOG_TAG+ "Appodeal", "onBannerFailedToLoad");
            }
            @Override
            public void onBannerShown() {
                Log.d(LOG_TAG + "Appodeal", "onBannerShown");
            }
            @Override
            public void onBannerClicked() {
                Log.d(LOG_TAG+ "Appodeal", "onBannerClicked");
            }
        });




    }


    // JC: This will dynamically generate the list of F and add them to the the scroll view.
    public void generateListOfF() {

        LinearLayout sv = findViewById(R.id.listOfF);

        // Iterate through all the list items and add them top the view.
        for (int i = 0; i < listOfFToGive.length; ++i) {
            sv.addView(util_configureFListItem(listOfFToGive[i], lparams));
        }
    }


    // JC: This function will be triggered by pressing the button on the lower right.
    // It will start a fragment to allow you to add something.
    public void addNewFToListOfF(View view) {

        fragmentManager
                .beginTransaction()
                .add(R.id.addAF, AddAF.newInstance())
                .commit();
        Log.d(LOG_TAG, "Created a fragment");

    }


    // JC: Fragment listener
    public void onFragmentInteraction(String text){

        fragmentManager
                .beginTransaction()
                .remove(getSupportFragmentManager().findFragmentById(R.id.addAF))
                .commit();

        LinearLayout sv = findViewById(R.id.listOfF);

        TextView tv=new TextView(this);
        tv.setLayoutParams(lparams);
        tv.setText(text);
        sv.addView(tv);
    }


    // JC: Util function that will return a TextView to be appended into the view
    public TextView util_configureFListItem(String text, ViewGroup.LayoutParams lp) {

        TextView tv = new TextView(this);
        tv.setClickable(true);
        tv.setLayoutParams(lp);
        tv.setText(text);

        // TODO: MAKE this responsive / do sommething

        return tv;

    }
}
