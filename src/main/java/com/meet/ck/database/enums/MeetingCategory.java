package com.meet.ck.database.enums;

public enum MeetingCategory {
    LEARN("Nauka"),
    TRAVEL("Podróż"),
    FOOD("Jedzenie"),
    BOARD_GAMES("Gry planszowe"),
    CINEMA("Kino"),
    PARTY("Impreza"),
    COFFEE_OR_TEA("Kawa lub herbata"),
    ALCOHOL("Alkohol"),
    TALK("Rozmowa"),
    THEATER("Teatr lub komedia"),
    ACTIVITY("Aktywność fizyczna"),
    OTHER("Coś innego"),
    ANYTHING("Wszystko jedno");

    private String key;

    MeetingCategory(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
