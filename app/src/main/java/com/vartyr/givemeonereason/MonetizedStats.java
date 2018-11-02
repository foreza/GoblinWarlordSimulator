package com.vartyr.givemeonereason;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MonetizedStats extends Fragment {


    MonetizationManager mm;         // Singleton instance that will allow us to sync the value and increment globally


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

        View fragmentView = inflater.inflate(R.layout.fragment_monetized_stats, container, false);



         updateView(fragmentView);

        return fragmentView;
    }



    // Method to update the view
    public void updateView(View view){



        // UPDATE THE ITEMS

        mm = MonetizationManager.getInstance();


        TextView tv1 = (TextView)view.findViewById(R.id.val_num_banner_swiped);
        tv1.setText(mm.getNumBannerSwiped());

        TextView tv2 = (TextView)view.findViewById(R.id.val_num_videos_watched);
        tv2.setText(mm.getNumVideosWatched());

        TextView tv3 = (TextView)view.findViewById(R.id.val_num_endless_scrolled);
        tv3.setText(mm.getNumEncountersScrolled());

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
