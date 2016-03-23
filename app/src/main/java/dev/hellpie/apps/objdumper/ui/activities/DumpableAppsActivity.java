/*
 * Copyright 2016 Diego Rossi (@_HellPie)
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


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dev.hellpie.apps.objdumper.R;
import dev.hellpie.apps.objdumper.dumper.AsyncAppDetector;
import dev.hellpie.apps.objdumper.models.AppInfoAdapter;
import dev.hellpie.apps.objdumper.ui.views.DividerItemDecoration;

/**
 * DumpableAppsActivity class. This is the main activity of the application, it holds basic code
 * to initialize the other classes of this app.
 */
public class DumpableAppsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dumpable_apps);

		// TODO: Marshmallow (6.0) new Permissions Model support

		// Create a new adapter for the RecyclerView
		AppInfoAdapter adapter = new AppInfoAdapter();

		// Get the RecyclerView from XML, assign it adapter, manager and decorators
		RecyclerView view = (RecyclerView) findViewById(R.id.window_content_scrollable);
		if(view != null) {
			view.setLayoutManager(new LinearLayoutManager(this));
			view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
			view.setAdapter(adapter);
		}

		// Create a new AsyncAppDetector to load all the apps with JNI into the RecyclerView
		new AsyncAppDetector(this, adapter).execute();
	}
}
