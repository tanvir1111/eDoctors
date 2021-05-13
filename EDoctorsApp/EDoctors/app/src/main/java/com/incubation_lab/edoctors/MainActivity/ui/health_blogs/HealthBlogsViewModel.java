package com.incubation_lab.edoctors.MainActivity.ui.health_blogs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HealthBlogsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HealthBlogsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}