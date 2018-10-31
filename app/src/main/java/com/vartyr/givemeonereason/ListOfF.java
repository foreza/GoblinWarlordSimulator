package com.vartyr.givemeonereason;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ListOfF extends AppCompatActivity implements AddAF.OnFragmentInteractionListener{

    public String [] listOfFToGive = {"Job", "Food", "Good old fashioned Android Dev"};
    public FragmentManager fragmentManager;
    public String LOG_TAG = "[GMOR]";
    public ViewGroup.LayoutParams lparams;
    public AdManager adManager;
    public View bannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_f);
        initApp();
        generateListOfF();
        loadAndShowBanner();
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


    // JC: A function that will do variable assignment for the scope of this class and init all ad sdks from adManager
    public void initApp() {

        adManager = AdManager.getInstance();
        adManager.initAdSDK(this);
        fragmentManager = getSupportFragmentManager();
        lparams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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

        util_hideKeyboard(this);
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

        RelativeLayout adContainer = (RelativeLayout) findViewById(R.id.ad_container);
        Resources r = getResources();
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, r.getDisplayMetrics());
        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
        RelativeLayout.LayoutParams bannerLp = new RelativeLayout.LayoutParams(Math.round(width), Math.round(height));
        bannerLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bannerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bannerView = adManager.getBanner(this);
        adContainer.addView(bannerView, bannerLp);
        adManager.showBanner(bannerView);

    }


}
