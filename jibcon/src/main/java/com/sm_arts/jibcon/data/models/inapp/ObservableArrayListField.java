package com.sm_arts.jibcon.data.models.inapp;

import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 8/13/17.
 */

public class ObservableArrayListField<T> extends ObservableField<List<T>> {
    public ObservableArrayListField() {
        // null safe ObservableField
        super(new ArrayList<>());
    }
}
