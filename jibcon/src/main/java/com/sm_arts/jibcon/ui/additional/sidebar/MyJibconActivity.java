package com.sm_arts.jibcon.ui.additional.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.Invitation;
import com.sm_arts.jibcon.data.repository.network.api.UserService;
import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.additional.dialogs.HouseChangeDialog;
import com.sm_arts.jibcon.ui.additional.sidebar.housermember.HouseMembersActivity;
import com.sm_arts.jibcon.ui.splash.makecon.MakeconMainActivity;
import com.sm_arts.jibcon.utils.housemanager.JibconHouseManager;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyJibconActivity extends BaseActivity {
    @BindView(R.id.lv_my_jibcon)
    ListView mSidebarMyJibconLv;

    private static final String TAG = "MyJibconActivity";

    @OnClick(R.id.imageview_sidebar_myjibcon)
    void imageview_sidebar_myjibcon() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_myjibconactivity_activity);
        ButterKnife.bind(this);

        final String[] myjibconOptionList = getResources().getStringArray(R.array.sidebar_myjibcon_option_array);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myjibconOptionList);
        mSidebarMyJibconLv.setAdapter(adapter);

        mSidebarMyJibconLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String settingClickedItem = (String) mSidebarMyJibconLv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), settingClickedItem, Toast.LENGTH_LONG).show();
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        Intent intent = new Intent(getApplicationContext(), MakeconMainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 4:
                        HouseChangeDialog houseChangeDialog = new HouseChangeDialog(MyJibconActivity.this);
                        houseChangeDialog.show();
                        break;
                    case 5:
                        getInvitationLink();
                        break;
                    case 6:
                        Intent getHouseMembersIntent = new Intent(getApplicationContext(),HouseMembersActivity.class);
                        startActivity(getHouseMembersIntent);
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private void getInvitationLink()
    {
        Invitation invitation = new Invitation();

        if(JibconHouseManager.getInstance().getmCurrentHouse()==null)
        {
            Toast.makeText(getApplicationContext(),"집을 먼저 등록해 주세요",Toast.LENGTH_SHORT).show();
            return;
        }

        invitation.house_id = JibconHouseManager.getInstance().getmCurrentHouse().house_id;
        invitation.user_id = JibconLoginManager.getInstance().getUserId();


        UserService service = RetrofitClients.getInstance().getService(UserService.class);
        Call<String> c = service.makeInvitation(invitation);
        c.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                shareKakako(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void shareKakako(String url) {
        try{

            final KakaoLink kakaoLink = KakaoLink.getKakaoLink(this);
            final KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

            kakaoTalkLinkMessageBuilder.addText(url);
            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder,this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
