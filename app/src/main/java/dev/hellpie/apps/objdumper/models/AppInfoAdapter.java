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

package dev.hellpie.apps.objdumper.models;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dev.hellpie.apps.objdumper.R;

/**
 * AppInfoAdapter class. This class contains code relative to the adapter of the RecyclerView
 * containing the list of apps supporting JNI.
 */
public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoViewHolder> {

	// The list of apps supporting JNI as in form of a AppInfoHolder's List
	private List<AppInfoHolder> mData = new ArrayList<>();

	@Override
	public AppInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		// Build a new view based on the template and assign it to a ViewHolder immediately
		return new AppInfoViewHolder(
				LayoutInflater.from(parent.getContext())
						.inflate(R.layout.template_list_package, parent, false)
		);
	}

	@Override
	public void onBindViewHolder(AppInfoViewHolder holder, int position) {

		// Set the AppInfoHolder for a given AppInfoViewHolder
		holder.bindAppInfo(mData.get(position));
	}

	@Override
	public int getItemCount() {

		// Return the dimensions of the list in the RecyclerView
		return mData.size();
	}

	public AppInfoAdapter addAppInfo(AppInfoHolder holder) {

		// Add an AppInfoHolder to the list, update the list, done
		mData.add(holder);
		notifyItemInserted(mData.size() - 1);
		return this;
	}
}
