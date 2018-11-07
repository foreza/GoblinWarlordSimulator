package com.vartyr.goblinwarlordsimulator;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class WarlordMainActivity extends AppCompatActivity implements AddAF.OnFragmentInteractionListener{

//    public String [] listOfFToGive = {"Job", "Food", "Good old fashioned Android Dev"};
    public FragmentManager fragmentManager;
    public GameStateManager gsm;
    public String LOG_TAG = "[WarlordMainActivity]";
    public ViewGroup.LayoutParams lparams;
    public AdManager adManager;
    public View bannerView;


    public ImageView [] explosionViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.warlord_main);
        initApp();
        generateListOfF();
        loadAndShowBanner();
        updateLabels();
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
        updateLabels();

    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        adManager.destroyBanner(bannerView);

    }


    // JC: A function that will do variable assignment for the scope of this class and init all ad sdks from adManager
    public void initApp() {

        gsm = GameStateManager.getInstance();           // Get the game state manager instance on init
        adManager = AdManager.getInstance();
        adManager.initAdSDK(this);
        fragmentManager = getSupportFragmentManager();
        lparams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);




        // Set up our explosions

        ImageView iv1 = findViewById(R.id.explosion_1);
        ImageView iv2 = findViewById(R.id.explosion_2);
        ImageView iv3 = findViewById(R.id.explosion_3);

        explosionViews = new ImageView[3];
        explosionViews[0] = iv1;
        explosionViews[1] = iv2;
        explosionViews[2] = iv3;

        TextView tv_ce = findViewById(R.id.label_currency_earned);
        String tv_cetext = String.valueOf(tv_ce.getText());
        tv_ce.setText(gsm.currencyName + " " + tv_cetext);

    }


    public void updateGameLoop(){

        // TODO: do more of these!
        updateLabels();
    }

    public void updateLabels(){

        TextView tv1 = findViewById(R.id.damage_done);
        tv1.setText(gsm.getDisplay_NumDamageDealt());
        TextView tv2 = findViewById(R.id.currency_earned);
        tv2.setText(gsm.getDisplay_NumCurrency());

    }


    // JC: This will dynamically generate the list of F and add them to the the scroll view.
    public void generateListOfF() {

//        LinearLayout sv = findViewById(R.id.listOfF);

        // Iterate through all the list items and add them top the view.
//        for (int i = 0; i < listOfFToGive.length; ++i) {
//            sv.addView(util_configureFListItem(listOfFToGive[i], lparams));
//        }


    }


    // JC: This function will be triggered by pressing the button on the lower right.
    // It will start a fragment to allow you to add something.
//    public void addNewFToListOfF(View view) {
//
//        fragmentManager
//                .beginTransaction()
//                .add(R.id.addAF, AddAF.newInstance())
//                .commit();
//        Log.d(LOG_TAG, "Created a fragment");
//
//    }


    public void clickInteraction(View view){
        Log.d(LOG_TAG, "Click detected on image");

        // TEST: For alpha, we'll just do this in the main view.
        gsm.incrementTotalDamage();
        gsm.incrementTotalCurrency();
        showRandomExplosionInView();
        updateGameLoop();
    }


    public void openInfoPage(View view) {

        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Going to info page");


    }

    public void openSettingPage(View view) {

        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Going to setting page");
    }


    public void openContactPage(View view) {

        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Going to contact page");


    }

    public void openDetailedStatsPage(View view) {

        Intent intent = new Intent(this, DetailedStatsActivity.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Going to detailed stats page");


    }

    public void openUpgradePage(View view) {

        Intent intent = new Intent(this, UpgradesActivity.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Going to upgrade page");


    }

        public void openHyperMonetization(View view) {

        Intent intent = new Intent(this, WarlordMonetize.class);
        startActivity(intent);
        Log.d(LOG_TAG, "Going to monetize page");


    }





    // JC: Fragment listener
    public void onFragmentInteraction(String text){
//
//        fragmentManager
//                .beginTransaction()
//                .remove(getSupportFragmentManager().findFragmentById(R.id.addAF))
//                .commit();

      //   LinearLayout sv = findViewById(R.id.listOfF);

//        TextView tv=new TextView(this);
//        tv.setLayoutParams(lparams);
//        tv.setText(text);
//        sv.addView(tv);

        util_hideKeyboard(this);
    }



    public void showRandomExplosionInView(){

        int i = util_genRandomNumWithRange(0, 3);

        ImageView iv = explosionViews[i];

        if (iv.getVisibility() == View.VISIBLE){
            iv.setVisibility(View.INVISIBLE);
        } else {
            iv.setVisibility(View.VISIBLE);
        }

    }





    public int util_genRandomNumWithRange(int min, int range){
        return (int)(Math.random() * range) + min;
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

    public static void util_hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    //region BANNER management from the admanager

    // JC: Method to load and show a banner from the ad manager
    public void loadAndShowBanner(){

        RelativeLayout adContainer = (RelativeLayout) findViewById(R.id.listFAdView);
        Resources r = getResources();
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, r.getDisplayMetrics());
        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
        RelativeLayout.LayoutParams bannerLp = new RelativeLayout.LayoutParams(Math.round(width), Math.round(height));
        bannerLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bannerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bannerView = adManager.getBanner(this, 0);      // Mode 0 will be the bottom banner
        adContainer.addView(bannerView, bannerLp);
        adManager.showBanner(bannerView);

    }
    

}
