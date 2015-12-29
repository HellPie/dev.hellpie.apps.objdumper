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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import dev.hellpie.apps.objdumper.R;
import dev.hellpie.apps.objdumper.dumper.AsyncAppDetector;
import dev.hellpie.apps.objdumper.models.AppInfoAdapter;
import dev.hellpie.apps.objdumper.ui.views.DividerItemDecoration;

public class DumpableAppsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dumpable_apps);
        setSupportActionBar((Toolbar) findViewById(R.id.layout_appbar_toolbar));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        AppInfoAdapter adapter = new AppInfoAdapter();

        RecyclerView view = (RecyclerView) findViewById(R.id.window_content_scrollable);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        view.setAdapter(adapter);

        new AsyncAppDetector(this, adapter).execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
