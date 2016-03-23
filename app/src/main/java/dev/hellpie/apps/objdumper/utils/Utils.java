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

package dev.hellpie.apps.objdumper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Offers utils for generic purpouses.
 */
public class Utils {

	/**
	 * Constructor for Utils.
	 * <p/>
	 * This constructor is private: this class should never be instantiated.
	 * All methods in this class should be declared as static.
	 */
	private Utils() {
	}

	/**
	 * Returns the Activity that contains the given view if it exists, or null if the View
	 * isn't an Activity's child.
	 *
	 * @param view The View to which get the parent Activity from
	 *
	 * @return The container Activity of view, or null
	 */
	@Nullable
	public static Activity getActivity(@NonNull View view) {
		Context context = view.getContext();

		while(context instanceof ContextWrapper) {
			if(context instanceof Activity) return (Activity) context;
			context = ((ContextWrapper) context).getBaseContext();
		}

		return null;
	}
}
