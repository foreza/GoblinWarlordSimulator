package com.vartyr.goblinwarlordsimulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inmobi.sdk.InMobiSdk;
import java.util.HashMap;
import java.util.Map;

public class InfoActivity extends AppCompatActivity {

    public Map<String, String> infoParams;     // InfoActivity map
    public ViewGroup.LayoutParams lparams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        lparams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        generateSettingInfo();
        addInfoItemToView();
    }



    // JC: TODO: Do my method calls here for the various version of SDK
    public void generateSettingInfo(){
        infoParams = new HashMap<String, String>();

        infoParams.put("InMobi Ads SDK Version", InMobiSdk.getVersion().toString());
        infoParams.put("Application Version",getString(R.string.APPLICATION_VERSION));

    }


    // JC: This will add the settings to the view as dynamic elements
    public void addInfoItemToView() {

        LinearLayout sv = findViewById(R.id.settingInfoList);

        // Iterate through all the list items and add them top the view.
        for (Map.Entry<String, String> entry : infoParams.entrySet()) {
            sv.addView(util_configureInfoListItem(entry.getKey(), entry.getValue(), lparams));
        }
    }
    



    // JC: Util function that will return a TextView to be appended into the view
    public TextView util_configureInfoListItem(String key, String val, ViewGroup.LayoutParams lp) {

        TextView tv = new TextView(this);
        tv.setClickable(true);
        tv.setLayoutParams(lp);
        tv.setText(key + " : " + val);

        // TODO: MAKE this responsive / do sommething

        return tv;

    }




}
