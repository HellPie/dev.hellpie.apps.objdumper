/*
 * Copyright 2015 Diego Rossi (@_HellPie)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.hellpie.apps.objdumper.database;

import android.provider.BaseColumns;

public class DumpedAppsContract implements BaseColumns {

    public static final String TABLE_NAME = "dumped_apps";

    //public static final String COLUMN_UNID = "app_id";
    public static final String COLUMN_ITEM = "app_name";
    public static final String COLUMN_LIBS = "app_libs";

    public static final String LIBS_SEPARATOR = "|";


    public static final String SQL_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    //+ COLUMN_UNID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_ITEM + " TEXT NOT NULL,"
                    + COLUMN_LIBS + " TEXT NOT NULL"
                    + ")";

    public static final String SQL_REMOVE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    // This class only contains static stuff, no instances should be created
    private DumpedAppsContract() {
    }

}
