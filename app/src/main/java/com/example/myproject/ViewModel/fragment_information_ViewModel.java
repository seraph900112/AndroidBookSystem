package com.example.myproject.ViewModel;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class fragment_information_ViewModel extends AndroidViewModel {
    private MutableLiveData<Bitmap> photo;


    public fragment_information_ViewModel(@NonNull Application application) {
        super(application);

            photo = new MutableLiveData<>();

    }

    public void setPhoto(Bitmap photo) {
        this.photo.postValue(photo);
    }

    public MutableLiveData<Bitmap> getPhoto() {
        return photo;
    }
}
