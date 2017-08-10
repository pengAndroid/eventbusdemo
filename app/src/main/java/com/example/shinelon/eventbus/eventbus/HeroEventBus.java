package com.example.shinelon.eventbus.eventbus;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TaeYang on 2017/8/7.
 */

public class HeroEventBus {
    private String name;
    private String age;
    private String card;
    private HeroEventBus heroEventBus;

    public HeroEventBus() {
    }

    public HeroEventBus(String name, String age, String card) {
        this.name = name;
        this.age = age;
        this.card = card;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }


}
