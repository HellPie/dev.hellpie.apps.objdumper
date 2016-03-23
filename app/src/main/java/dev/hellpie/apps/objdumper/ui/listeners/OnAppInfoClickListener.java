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

package dev.hellpie.apps.objdumper.ui.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;

import dev.hellpie.apps.objdumper.R;
import dev.hellpie.apps.objdumper.models.AppInfoHolder;
import dev.hellpie.apps.objdumper.models.AppInfoViewHolder;
import dev.hellpie.apps.objdumper.utils.Utils;

/**
 * OnAppInfoClickListener class. This class holds code relative to the OnClickListener present
 * in each item in the libraries RecyclerView.
 */
public class OnAppInfoClickListener implements View.OnClickListener {

	// Holds the informations relative to the clicked view
	private AppInfoViewHolder holder;

	/**
	 * Constructor for OnAppInfoClickListener.
	 *
	 * @param holder The AppInfoViewHolder to which this listener is bound
	 */
	public OnAppInfoClickListener(AppInfoViewHolder holder) {
		this.holder = holder;
	}

	@Override
	public void onClick(View v) {

		// Only run if holder, it's holded AppInfoHolder and the container activities are not null
		if(holder == null || holder.getHolder() == null) return;
		AppInfoHolder holder = this.holder.getHolder();

		Activity activity = Utils.getActivity(v);
		if(activity == null) return;

		// Convert libraries in the AppInfoHolder into a String array because that's what the
		// AlertDialog.Builder class expects as items
		String[] libs = new String[holder.libs.size()];
		for(int i = 0; i < libs.length; i++) {
			libs[i] = holder.libs.get(i).getName();
		}

		// Create a new listener, build a new AlertDialog, assign items, sub-listeners and show it
		LibrariesListeners listener = new LibrariesListeners(activity, holder);
		new AlertDialog.Builder(activity)
				.setTitle(R.string.libs_list_select)
				.setMultiChoiceItems(libs, null, new LibrariesListeners.OnUpdate(listener))
				.setPositiveButton(R.string.libs_list_confirm, new LibrariesListeners.OnConfirm(listener))
				.setNegativeButton(R.string.libs_list_cancel, new LibrariesListeners.OnCancel(listener))
				.setCancelable(false)
				.show();
	}
}
