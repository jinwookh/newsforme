package com.example.minkyung.newsforme.data;

import android.provider.BaseColumns;

public final class SettingContract {

    private SettingContract() {}

    public static final class SettingEntry implements BaseColumns {
        /** table name: setting */
        public final static String TABLE_NAME = "setting";

        /**
         * Unique ID number for the uri (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * uri content corresponding to ID number
         *
         * Type: TEXT
         */
        public final static String COLUMN_URL="url";
        public final static String COLUMN_NAME="name";

    }

}
