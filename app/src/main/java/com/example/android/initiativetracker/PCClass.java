package com.example.android.initiativetracker;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cpalomares on 5/3/2017.
 */

public class PCClass implements Parcelable {
    public String name;
    public int initiative;
    public int AC;

    public PCClass(String name, int initiative, int AC){
        this.name = name;
        this.initiative = initiative;
        this.AC = AC;
    }

    private PCClass(Parcel in){
        name = in.readString();
        initiative = in.readInt();
        AC = in.readInt();
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags){
        out.writeString(name);
        out.writeInt(initiative);
        out.writeInt(AC);
    }

    public static final Parcelable.Creator<PCClass> CREATOR = new Parcelable.Creator<PCClass>(){
        public PCClass createFromParcel(Parcel in){
            return new PCClass(in);
        }

        public PCClass[] newArray(int size){
            return new PCClass[size];
        }
    };
}
