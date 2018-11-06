package com.vartyr.goblinwarlordsimulator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MonetizedStats extends Fragment {


    GameStateManager mm;         // Singleton instance that will allow us to sync the value and increment globally
    View currentView;

    private OnFragmentInteractionListener mListener;

    public MonetizedStats() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MonetizedStats newInstance() {
        MonetizedStats fragment = new MonetizedStats();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        currentView = inflater.inflate(R.layout.fragment_monetized_stats, container, false);
        updateView();
        return currentView;
    }

    // Method to update the view
    public void updateView(){

        // UPDATE THE ITEMS

        mm = GameStateManager.getInstance();

        TextView tv1 = (TextView)currentView.findViewById(R.id.val_num_banner_swiped);
        tv1.setText(mm.getDisplay_NumBannerSwiped());

        TextView tv2 = (TextView)currentView.findViewById(R.id.val_num_videos_watched);
        tv2.setText(mm.getDisplay_NumVideosWatched());

        TextView tv3 = (TextView)currentView.findViewById(R.id.val_num_endless_scrolled);
        tv3.setText(mm.getDisplay_NumEncountersScrolled());

    }

    public static void doTheThing(){

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }
}
