package com.meet.ck.database.enums;

public enum Interest {

    LANGUAGES("Języki obce", "mdi-translate"),
    TRAVELLING("Podróże", "mdi-earth"),
    AUTOMOTIVE("Motoryzacja", "mdi-car"),
    ART("Sztuka", "mdi-palette-outline"),
    COOKING("Gotowanie", "mdi-chef-hat"),
    MUSIC("Muzyka", "mdi-playlist-music"),
    DANCE("Taniec", "mdi-dance-ballroom"),
    GYM("Siłownia", "mdi-dumbbell"),
    CYCLING("Przejażdżki rowerowe", "mdi-bike"),
    HIKING("Wspinaczka", "mdi-hiking"),
    BOARD_GAMES("Gry planszowe", "mdi-cards-playing-outline"),
    MOVIES("Filmy", "mdi-movie-open-outline"),
    PARTIES("Imprezy", "mdi-party-popper"),
    GAMES("Gry komputerowe", "mdi-gamepad-variant-outline"),
    WALKING("Spacery", "mdi-walk"),
    THEATER("Teatr lub komedia", "mdi-drama-masks"),
    TEAM_GAMES("Gry zespołowe", "mdi-soccer"),
    READING("Czytanie", "mdi-book-open-page-variant-outline"),
    WINTER_SPORTS("Sporty zimowe", "mdi-snowboard"),
    WATER_SPORTS("Sporty wodne", "mdi-swim");

    private String key;
    private String iconName;

    Interest(String key, String iconName) {
        this.key = key;
        this.iconName = iconName;
    }

    public String getKey() {
        return key;
    }

    public String getIconName() {
        return iconName;
    }

}
