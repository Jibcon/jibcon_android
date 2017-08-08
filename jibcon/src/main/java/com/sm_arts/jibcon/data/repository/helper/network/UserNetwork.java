package com.sm_arts.jibcon.data.repository.helper.network;

import com.sm_arts.jibcon.data.models.api.dto.User;

/**
 * Created by jaeyoung on 2017. 5. 1..
 */

public interface UserNetwork {
    void getSampleUserInfoFromServerAsynchronisely(UserNetwork.onSuccessListener listener);
    User getSampleUserInfoFromServerSynchronisely();

    interface onSuccessListener {
        void onSuccessGetSampleUserInfoFromServerAsynchronisely(User sampleUser);
    }
}
