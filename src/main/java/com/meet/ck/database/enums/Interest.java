package com.meet.ck.database.enums;

public enum Interest {

    LANGUAGES("Języki"),
    TRAVELLING("Podróże"),
    AUTOMOTIVE("Motoryzacja"),
    ART("Sztuka"),
    COOKING("Gotowanie"),
    MUSIC("Muzyka"),
    DANCE("Taniec"),
    GYM("Siłownia"),
    CYCLING("Przejażdżki rowerowe"),
    HIKING("Wspinaczka"),
    BOARD_GAMES("Gry planszowe"),
    MOVIES("Filmy"),
    PARTIES("Imprezy"),
    GAMES("Gry komputerowe"),
    WALKING("Spacery"),
    THEATER("Teatr lub komedia"),
    TEAM_GAMES("Gry zespołowe"),
    READING("Czytanie"),
    WINTER_SPORTS("Sporty zimowe"),
    WATER_SPORTS("Sporty wodne");

    private String key;

    Interest(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
