package com.sm_arts.jibcon.ui.additional.sidebar.housermember;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.User;
import com.sm_arts.jibcon.data.repository.network.api.UserService;
import com.sm_arts.jibcon.utils.housemanager.JibconHouseManager;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HouseMembersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    HouseMemberAdapter adapter;
    List<User> mUsers;
    private static final String TAG = "HouseMembersActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additional_sidebar_housemembers_activity);
        initLayout();


    }

    private void initLayout() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout_housemembers);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_additional_sidebar_housemembers);

        mSwipeRefreshLayout.
                setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                         @Override
                                         public void onRefresh() {
                                            getHouseMembers();
                                         }
                                     }
                );

        mUsers = new ArrayList<>();
        adapter = new HouseMemberAdapter();
        adapter.setmUserListInHouse(mUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void getHouseMembers() {

        if(JibconHouseManager.getInstance().getmCurrentHouse() == null)
        {
            Toast.makeText(getApplicationContext(),"집을 먼저 추가해 주세요",Toast.LENGTH_SHORT).show();
            return;
        }
        UserService service = RetrofitClients.getInstance().getService(UserService.class);
        Call<List<User>> c = service.getHouseMembers(JibconHouseManager.getInstance().getmCurrentHouse().house_id);
        c.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if(!response.isSuccessful())
                    return;
                mUsers.clear();
                for(int i=0;i<response.body().size();i++)
                {
                    mUsers.add(response.body().get(i));

                }
                adapter.setmUserListInHouse(mUsers);
                onDataDownloadFinished();

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                t.printStackTrace();
                onDataDownloadFinished();

            }
        });
    }

    private void onDataDownloadFinished() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


}
