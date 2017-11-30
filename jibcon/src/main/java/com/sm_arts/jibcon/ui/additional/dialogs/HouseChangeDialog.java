package com.sm_arts.jibcon.ui.additional.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.HouseInfo;
import com.sm_arts.jibcon.data.repository.helper.HouseNetworkManager;
import com.sm_arts.jibcon.utils.housemanager.JibconHouseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-11-30.
 */

public class HouseChangeDialog extends Dialog {

    private static final String TAG = "HouseChangeDialog";
    ArrayList<String> houseArray;
    List<HouseInfo> houselist;
    ListView listView;
    Context context;

    public HouseChangeDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        setContentView(R.layout.ui_housechange_dialog);
        houseArray = new ArrayList<>();
        listView = (ListView) findViewById(R.id.ui_housechange_dialog_listview);
        HouseNetworkManager.getInstance().getMyHouse();
        houselist = JibconHouseManager.getInstance().getMyHouseList();
        if (houselist == null)
            return;
        for (int i = 0; i < houselist.size(); i++) {
            houseArray.add(houselist.get(i).houseName);
        }

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, houseArray);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemSelected() called with: parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
                if (position < 0)
                    return;
                HouseInfo nextHouse = JibconHouseManager.getInstance().getMyHouseList().get(position);
                JibconHouseManager.getInstance().changeCurrentHouse(nextHouse,context);


            }
        });


    }

}
