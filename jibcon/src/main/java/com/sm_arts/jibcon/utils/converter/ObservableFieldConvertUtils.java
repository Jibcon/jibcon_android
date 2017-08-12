package com.sm_arts.jibcon.utils.converter;

import android.databinding.ObservableField;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * Created by jaeyoung on 8/12/17.
 */

public class ObservableFieldConvertUtils {
    @NonNull
    public static <T> Observable<T> toObservable(@NonNull final ObservableField<T> field) {
        return Observable.fromPublisher(asyncEmitter -> {
            final android.databinding.Observable.OnPropertyChangedCallback callback = new android.databinding.Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable dataBindingObservable, int propertyId) {
                    if (dataBindingObservable == field) {
                        asyncEmitter.onNext(field.get());
                    }
                }
            };
            field.addOnPropertyChangedCallback(callback);
        });
    }
}
