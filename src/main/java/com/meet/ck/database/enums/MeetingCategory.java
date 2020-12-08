package com.meet.ck.database.enums;

public enum MeetingCategory {
    LEARN("Nauka"),
    TRAVEL("Podróż"),
    FOOD("Jedzenie"),
    BOARD_GAMES("Gry planszowe"),
    CINEMA("Kino"),
    PARTY("Imprezy"),
    COFFEE_OR_TEA("Kawa lub herbata"),
    ALCOHOL("Wyjście na alkohol"),
    TALK("Rozmowa"),
    THEATER("Teatr lub komedia"),
    ACTIVITY("Aktywność fizyczna"),
    TEAM_SPORT("Sporty zespołowe"),
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
