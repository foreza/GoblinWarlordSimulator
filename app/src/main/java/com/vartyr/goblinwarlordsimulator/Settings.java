package com.vartyr.goblinwarlordsimulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inmobi.sdk.InMobiSdk;
import java.util.HashMap;
import java.util.Map;

public class Settings extends AppCompatActivity {

    public Map<String, String> settingInfo;     // Settings map
    public ViewGroup.LayoutParams lparams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        lparams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        generateSettingInfo();
        addSettingToView();
    }



    // JC: TODO: Do my method calls here for the various version of SDK
    public void generateSettingInfo(){
        settingInfo = new HashMap<String, String>();

        settingInfo.put("InMobi SDK Version", InMobiSdk.getVersion().toString());
        settingInfo.put("Application Version",getString(R.string.APPLICATION_VERSION));

    }


    // JC: This will add the settings to the view as dynamic elements
    public void addSettingToView() {

        LinearLayout sv = findViewById(R.id.settingInfoList);

        // Iterate through all the list items and add them top the view.
        for (Map.Entry<String, String> entry : settingInfo.entrySet()) {
            sv.addView(util_configureSettingListItem(entry.getKey(), entry.getValue(), lparams));
        }
    }
    



    // JC: Util function that will return a TextView to be appended into the view
    public TextView util_configureSettingListItem(String key, String val, ViewGroup.LayoutParams lp) {

        TextView tv = new TextView(this);
        tv.setClickable(true);
        tv.setLayoutParams(lp);
        tv.setText(key + " : " + val);

        // TODO: MAKE this responsive / do sommething

        return tv;

    }




}
