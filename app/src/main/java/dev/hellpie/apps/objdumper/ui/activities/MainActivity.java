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

package dev.hellpie.apps.objdumper.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

import dev.hellpie.apps.objdumper.R;
import dev.hellpie.apps.objdumper.models.AppInfoHolder;

public class MainActivity extends AppCompatActivity {

    public static final int GET_DUMPABLE_APP = 1;
    public static final String LIB_PATH = "LIB_PATH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.layout_appbar_toolbar));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK || requestCode != GET_DUMPABLE_APP) return;

        AppInfoHolder holder = new AppInfoHolder();
        holder.name = data.getStringExtra(AppInfoHolder.NAME);
        holder.id = data.getStringExtra(AppInfoHolder.ID);
        holder.version = data.getStringExtra(AppInfoHolder.VERSION);
        holder.path = data.getStringExtra(AppInfoHolder.PATH);

        holder.libs = new ArrayList<>();
        holder.libs.add(new File(data.getStringExtra(LIB_PATH)));
    }

    public void onFABClick(View view) {
        if (!(view instanceof FloatingActionButton)) return;

        Intent intent = new Intent(this, DumpableAppsActivity.class);
        startActivityForResult(intent, GET_DUMPABLE_APP);
    }
}
