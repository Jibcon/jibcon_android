package com.sm_arts.jibcon.Login.user.service;

import com.sm_arts.jibcon.Login.user.domain.User;

/**
 * Created by jaeyoung on 2017. 5. 1..
 */

public interface UserService {
    void getSampleUserAsynchronisely(UserService.onSuccessListener listener);
    User getSampleUserSynchronisely();

    interface onSuccessListener {
        void onSuccessGetSampleUserAsynchronisely(User sampleUser);
    }
}
