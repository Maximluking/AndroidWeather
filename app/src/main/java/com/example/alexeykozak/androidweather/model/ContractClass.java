package com.example.alexeykozak.androidweather.model;


public class ContractClass {
    public static final class Weather {
        public static final String TABLE_NAME = "weather";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_CURRENT_TEMP = "current_temp";
        public static final String COLUMN_NAME_MAX_TEMP = "max_temp";
        public static final String COLUMN_NAME_MIN_TEMP = "min_temp";
        public static final String COLUMN_NAME_ICON = "icon";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_CITY_FK_ID = "city_fk_id";

        public static final String[] DEFAULT_PROJECTION = new String[]{
                Weather.COLUMN_NAME_ID,
                Weather.COLUMN_NAME_CURRENT_TEMP,
                Weather.COLUMN_NAME_MAX_TEMP,
                Weather.COLUMN_NAME_MIN_TEMP,
                Weather.COLUMN_NAME_DESCRIPTION,
                Weather.COLUMN_NAME_ICON,
                Weather.COLUMN_NAME_CITY_FK_ID
        };
    }

    public static final class City {

        public static final String TABLE_NAME = "cities";

        public static final String COLUMN_NAME_ID = "id";

        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_WEATHER_FK_ID = "weather_fk_id";


        public static final String[] DEFAULT_PROJECTION = new String[]{
                City.COLUMN_NAME_ID,
                City.COLUMN_NAME_NAME,
                City.COLUMN_NAME_WEATHER_FK_ID
        };

    }
}
