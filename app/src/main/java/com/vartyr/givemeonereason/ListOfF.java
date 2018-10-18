package com.vartyr.givemeonereason;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_f);

        generateListOfF();

        fragmentManager = getSupportFragmentManager();


    }


    // JC: This will dynamically generate the list of F and add them to the the scroll view.
    private void generateListOfF() {

        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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
        Log.d("[CC", "Created frag");

    }

    // JC: Fragment listener
    public void onFragmentInteraction(String text){

        fragmentManager
                .beginTransaction()
                .remove(getSupportFragmentManager().findFragmentById(R.id.addAF))
                .commit();

        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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
