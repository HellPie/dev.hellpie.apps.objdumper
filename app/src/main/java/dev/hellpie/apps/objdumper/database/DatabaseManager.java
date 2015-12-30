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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseManager {

    private static DatabaseManager SELF;

    private DumpedAppsSQLiteHelper helper;

    private DatabaseManager() {
        SELF = this;
    }

    @NonNull
    public static DatabaseManager getManager() {
        if (SELF == null) {
            return new DatabaseManager();
        } else {
            return SELF;
        }
    }

    public DatabaseManager setup(Context context) {
        if (helper != null) {
            helper = new DumpedAppsSQLiteHelper(context);
        }

        return this;
    }

    public DatabaseManager add(String id, String... libs) {
        if (hasAlready(id)) return edit(id, false, libs);

        SQLiteDatabase writableDatabase = helper.getWritableDatabase();

        String finalLibs = TextUtils.join(DumpedAppsContract.LIBS_SEPARATOR, libs);

        ContentValues values = new ContentValues();
        values.put(DumpedAppsContract.COLUMN_ITEM, id);
        values.put(DumpedAppsContract.COLUMN_LIBS, finalLibs);

        writableDatabase.insert(
                DumpedAppsContract.TABLE_NAME,
                null,
                values
        );

        return this;
    }

    public DatabaseManager edit(String id, boolean overwrite, String... libs) {
        if (overwrite || !hasAlready(id)) return add(id, libs);

        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(
                "SELECT " + DumpedAppsContract.COLUMN_LIBS
                        + " FROM " + DumpedAppsContract.TABLE_NAME
                        + " WHERE " + DumpedAppsContract.COLUMN_ITEM
                        + " = ?",
                new String[]{id}
        );

        String currLibs = null;
        if (cursor.moveToFirst()) {
            currLibs = cursor.getString(cursor.getColumnIndex(DumpedAppsContract.COLUMN_LIBS));
        }

        String newLibs = TextUtils.join(DumpedAppsContract.LIBS_SEPARATOR, libs);

        if (currLibs != null && !currLibs.isEmpty()) {
            newLibs += DumpedAppsContract.LIBS_SEPARATOR + currLibs;
        }

        SQLiteDatabase writableDatabase = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DumpedAppsContract.COLUMN_ITEM, id);
        values.put(DumpedAppsContract.LIBS_SEPARATOR, newLibs);

        writableDatabase.replace(
                DumpedAppsContract.TABLE_NAME,
                null,
                values
        );

        cursor.close();
        return this;
    }

    public DatabaseManager remove(String id, String... libs) {
        if (!hasAlready(id)) return this;

        if (Arrays.equals(libs, new String[]{})) {
            ArrayList<String> newLibs = new ArrayList<>(Arrays.asList(getLibs(id)));
            for (String lib : libs) {
                newLibs.remove(lib);
            }

            return edit(id, true, newLibs.toArray(new String[newLibs.size()]));

        } else {
            SQLiteDatabase writeableDatabase = helper.getWritableDatabase();

            writeableDatabase.delete(
                    DumpedAppsContract.TABLE_NAME,
                    DumpedAppsContract.COLUMN_ITEM + " LIKE ?",
                    new String[]{id}
            );

            return this;
        }
    }

    public DatabaseManager remove(String id) {
        return remove(id, new String[]{});
    }

    public String[] getLibs(String id) {
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        String[] libs = new String[]{};

        Cursor cursor = readableDatabase.rawQuery(
                "SELECT " + DumpedAppsContract.COLUMN_ITEM
                        + " FROM " + DumpedAppsContract.TABLE_NAME
                        + " WHERE " + DumpedAppsContract.COLUMN_ITEM
                        + " = ?",
                new String[]{id}
        );

        if (cursor.moveToFirst()) {
            libs = TextUtils.split(
                    cursor.getString(cursor.getColumnIndex(DumpedAppsContract.COLUMN_LIBS)),
                    DumpedAppsContract.LIBS_SEPARATOR
            );
        }

        cursor.close();
        return libs;
    }

    private boolean hasAlready(String id) {
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();

        Cursor cursor = readableDatabase.rawQuery(
                "SELECT " + DumpedAppsContract.COLUMN_ITEM
                        + " FROM " + DumpedAppsContract.TABLE_NAME
                        + " WHERE " + DumpedAppsContract.COLUMN_ITEM
                        + " = ?",
                new String[]{id}
        );

        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }

}
