package com.example.examen.data;

import android.provider.BaseColumns;

public class RatingContract {
    public static final class RatingEntry implements BaseColumns {
        public static final String TABLE_NAME = "rating";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DATE_HEURE = "timestamp";
        public static final String COLUMN_RATING_DECO = "ratingDeco";
        public static final String COLUMN_RATING_FOOD = "ratingFood";
        public static final String COLUMN_RATING_SERVICE = "rating";
        public static final String COLUMN_DESCRIPTION = "description";
    }
}
