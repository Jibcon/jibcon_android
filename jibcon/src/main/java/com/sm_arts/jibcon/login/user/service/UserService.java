package com.sm_arts.jibcon.login.user.service;

import com.sm_arts.jibcon.login.user.domain.User;

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
