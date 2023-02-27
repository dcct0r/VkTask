package com.example.vktask;

import java.io.Serializable;

public class Cusr implements Serializable {
    public final String nickname;
    public final int userTag;
    public final int userIcon;

    public Cusr(String nickname, int userTag, int userIcon){
        this.nickname = nickname;
        this.userTag = userTag;
        this.userIcon = userIcon;
    }
}
