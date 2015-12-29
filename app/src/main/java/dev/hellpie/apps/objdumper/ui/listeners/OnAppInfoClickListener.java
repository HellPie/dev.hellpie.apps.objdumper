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
import android.app.AlertDialog;
import android.view.View;

import dev.hellpie.apps.objdumper.R;
import dev.hellpie.apps.objdumper.models.AppInfoHolder;
import dev.hellpie.apps.objdumper.models.AppInfoViewHolder;
import dev.hellpie.apps.objdumper.utils.AppUtils;

public class OnAppInfoClickListener implements View.OnClickListener {

    private AppInfoViewHolder holder;

    public OnAppInfoClickListener(AppInfoViewHolder holder) {
        this.holder = holder;
    }

    @Override
    public void onClick(View v) {
        if (holder == null || holder.getHolder() == null) return;
        AppInfoHolder holder = this.holder.getHolder();

        Activity activity = AppUtils.getUtils().getActivity(v);
        if (activity == null) return;

        String[] libs = new String[holder.libs.size()];
        for (int i = 0; i < libs.length; i++) {
            libs[i] = holder.libs.get(i).getName();
        }

        new AlertDialog.Builder(activity)
                .setTitle(R.string.libs_list_select_single)
                .setItems(libs, new OnLibraryClickListener(activity, holder))
                .show();
    }
}
