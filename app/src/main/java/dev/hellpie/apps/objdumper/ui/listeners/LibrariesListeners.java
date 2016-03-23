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
import android.content.DialogInterface;

import java.io.File;
import java.util.ArrayList;

import dev.hellpie.apps.objdumper.models.AppInfoHolder;

/**
 * LibrariesListeners class. This class holds all the code relative to the
 * "choose a library" AlertDialog.
 */
public class LibrariesListeners {

	// Holds the holder of the infos of the libraries and app of the libraries dialog
	private AppInfoHolder holder;
	private Activity activity;

	// Holds the selected items in the libraries dialog
	private ArrayList<File> selected = new ArrayList<>();

	/**
	 * Constructor for LibrariesListeners.
	 *
	 * @param activity The Activity to which this class is bound to
	 * @param holder   The AppInfoHolder of the selected item in the RecyclerView
	 */
	public LibrariesListeners(Activity activity, AppInfoHolder holder) {
		this.activity = activity;
		this.holder = holder;
	}

	/**
	 * Adds an element to the ArrayList representing the selected items in a dialog.
	 *
	 * @param which The index of the item to add into the ArrayList.
	 */
	private void onSelect(int which) {
		selected.add(holder.libs.get(which));
	}

	/**
	 * Removes an element to the ArrayList representing the selected items in a dialog.
	 *
	 * @param which The index of the item to remove from the ArrayList.
	 */
	private void onUnselect(int which) {
		selected.remove(holder.libs.get(which));
	}

	/**
	 * Given a dialog, this method dismisses it, then it creates a new AsyncDumper and starts
	 * executing it passing all the selected items in the given dialog.
	 *
	 * @param dialog The DialogInterface to dismiss
	 */
	private void onConfirm(DialogInterface dialog) {
		dialog.dismiss();

		// If no items selected, no need to continue
		if(selected.isEmpty()) return;

		// TODO: Run a new AsyncDumper
	}

	/**
	 * Dismisses a Dialog.
	 *
	 * @param dialog The DialogInterface to dismiss
	 */
	private void onCancel(DialogInterface dialog) {
		dialog.dismiss();
	}

	/**
	 * OnUpdate class. This class provides a OnMultiChoiceClickListener for a dialog.
	 * <p/>
	 * This class must be binded to a LibrariesListener as it works only as a
	 * bridge between a Dialog and the LibrariesListeners class.
	 */
	public static class OnUpdate implements DialogInterface.OnMultiChoiceClickListener {

		// The LibrariesListener this listener is bound to
		private final LibrariesListeners listener;

		/**
		 * Constructor for OnUpdate.
		 *
		 * @param listener The LibrariesListener to which this listener should be bound to
		 */
		public OnUpdate(LibrariesListeners listener) {
			this.listener = listener;
		}

		@Override
		public void onClick(DialogInterface dialog, int which, boolean isChecked) {
			if(isChecked) {
				listener.onSelect(which);
			} else {
				listener.onUnselect(which);
			}
		}
	}

	/**
	 * OnConfirm class. This class provides a OnClickListener for a dialog's positive button.
	 * <p/>
	 * This class must be binded to a LibrariesListener as it works only as a
	 * bridge between a Dialog and the LibrariesListeners class.
	 */
	public static class OnConfirm implements DialogInterface.OnClickListener {

		// The LibrariesListener this listener is bound to
		private final LibrariesListeners listener;

		/**
		 * Constructor for OnConfirm.
		 *
		 * @param listener The LibrariesListener to which this listener should be bound to
		 */
		public OnConfirm(LibrariesListeners listener) {
			this.listener = listener;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			listener.onConfirm(dialog);
		}
	}

	/**
	 * OnCancel class. This class provides a OnClickListener for a dialog's negative button.
	 * <p/>
	 * This class must be binded to a LibrariesListener as it works only as a
	 * bridge between a Dialog and the LibrariesListeners class.
	 */
	public static class OnCancel implements DialogInterface.OnClickListener {

		// The LibrariesListener this listener is bound to
		private final LibrariesListeners listener;

		/**
		 * Constructor for OnCancel.
		 *
		 * @param listener The LibrariesListener to which this listener should be bound to
		 */
		public OnCancel(LibrariesListeners listener) {
			this.listener = listener;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			listener.onCancel(dialog);
		}
	}
}
