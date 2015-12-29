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

package dev.hellpie.apps.objdumper.ui.listeners;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import dev.hellpie.apps.objdumper.models.AppInfoHolder;
import dev.hellpie.apps.objdumper.ui.activities.MainActivity;

public class OnLibraryClickListener implements DialogInterface.OnClickListener {

    private AppInfoHolder holder;
    private Activity activity;

    public OnLibraryClickListener(Activity activity, AppInfoHolder holder) {
        this.activity = activity;
        this.holder = holder;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (activity == null || holder == null) return;

        Intent intent = new Intent()
                .putExtra(AppInfoHolder.NAME, holder.name)
                .putExtra(AppInfoHolder.ID, holder.id)
                .putExtra(AppInfoHolder.VERSION, holder.version)
                .putExtra(AppInfoHolder.PATH, holder.path)
                .putExtra(MainActivity.LIB_PATH, holder.libs.get(which).getAbsolutePath());

        activity.setResult(Activity.RESULT_OK, intent);
        activity.finish();
    }
}
