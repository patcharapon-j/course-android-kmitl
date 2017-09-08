package com.example.patcharaponjoksamut.intentlab;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by patcharaponjoksamut on 8/9/2017 AD.
 */

public class TestParcelable implements Parcelable {

    private String name;
    private int age;
    private String nickname;

    protected TestParcelable(Parcel in) {
        name = in.readString();
        age = in.readInt();
        nickname = in.readString();
    }

    public static final Creator<TestParcelable> CREATOR = new Creator<TestParcelable>() {
        @Override
        public TestParcelable createFromParcel(Parcel in) {
            return new TestParcelable(in);
        }

        @Override
        public TestParcelable[] newArray(int size) {
            return new TestParcelable[size];
        }
    };

    public TestParcelable() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeInt(this.age);
        parcel.writeString(this.nickname);
    }
}
