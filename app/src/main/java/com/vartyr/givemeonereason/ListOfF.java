package com.vartyr.givemeonereason;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ListOfF extends AppCompatActivity {




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

    String [] listOfFToGive = {"Job", "Food", "Roommate", "Gym and gym buddies", "Immediate Family", "EX-idtech groupies", "Legal Portal", "Car", "Good fuckin music"};     // List of things we care about.
    String [] listOfIDGAF = {""};       // List of things we actually don't give a flying f about.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_f);

        generateListOfF();

    }


    // This will dynamically generate the list of F and add them to the the scroll view.
    private void generateListOfF() {

        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout sv = findViewById(R.id.listOfF);


        for (int i = 0; i < listOfFToGive.length; ++i) {
            TextView tv=new TextView(this);
            tv.setLayoutParams(lparams);
            tv.setText(listOfFToGive[i]);
            sv.addView(tv);
        }
    }

    public void addNewFToListOfF(View view) {

        // Trigger a pop-up


        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout sv = findViewById(R.id.listOfF);

        TextView tv=new TextView(this);
        tv.setLayoutParams(lparams);
        tv.setText("test");
        sv.addView(tv);


    }
}
