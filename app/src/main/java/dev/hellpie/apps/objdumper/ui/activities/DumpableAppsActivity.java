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


import android.Manifest;
import android.app.ActivityManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import dev.hellpie.apps.objdumper.R;
import dev.hellpie.apps.objdumper.dumper.AsyncAppDetector;
import dev.hellpie.apps.objdumper.models.AppInfoAdapter;
import dev.hellpie.apps.objdumper.ui.views.DividerItemDecoration;
import dev.hellpie.libs.utils.piemissions.PiemissionRequest;
import dev.hellpie.libs.utils.piemissions.PiemissionsUtils;
import dev.hellpie.libs.utils.piemissions.models.BasePiemissionsCallback;

/**
 * DumpableAppsActivity class. This is the main activity of the application, it holds basic code
 * to initialize the other classes of this app.
 */
public class DumpableAppsActivity extends AppCompatActivity {

	@SuppressWarnings("deprecation") // .getColor(int) is @Deprecated, but only after M (API23+)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dumpable_apps);

		// Fix the color in the recent apps list not being the dark shade
		setSupportActionBar((Toolbar) findViewById(R.id.window_appbar_toolbar));
		setTaskDescription(new ActivityManager.TaskDescription(
				getString(R.string.app_name),
				BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher),
				getResources().getColor(R.color.colorPrimaryDark)
		));

		PiemissionsUtils.init(this);

		PiemissionRequest request = new PiemissionRequest(19,
				Manifest.permission.READ_EXTERNAL_STORAGE,
				Manifest.permission.WRITE_EXTERNAL_STORAGE);
		request.setCallback(new BasePiemissionsCallback());

		PiemissionsUtils.requestPermission(request);
		// TODO: Make Marshmallow (6.0+/API23+) Permissions Model ask on-context and not right away

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

	@Override
	public void onRequestPermissionsResult(int code, @NonNull String[] perms, @NonNull int[] res) {
		PiemissionsUtils.onRequestResult(code, perms, res);
	}
}
