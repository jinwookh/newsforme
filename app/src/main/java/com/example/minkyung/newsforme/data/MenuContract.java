package com.example.minkyung.newsforme.data;

import android.provider.BaseColumns;

public final class MenuContract {

    private MenuContract() {}

    public static final class MenuEntry implements BaseColumns {
        /** table name: setting */
        public final static String TABLE_NAME = "menu";

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
        public final static String COLUMN_URI="uri";

    }

}
