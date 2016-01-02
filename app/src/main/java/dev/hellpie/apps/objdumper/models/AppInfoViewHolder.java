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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.hellpie.apps.objdumper.R;
import dev.hellpie.apps.objdumper.ui.listeners.OnAppInfoClickListener;

/**
 * AppInfoViewHolder class. This class holds the UI elements for each element in the RecyclerView.
 */
public class AppInfoViewHolder extends RecyclerView.ViewHolder {

    // UI Elements in each view into the libraries list
    public final TextView name;
    public final TextView version;
    public final ImageView icon;

    // Holds a reference to the infos of the App this ViewHolder is bound to
    private AppInfoHolder holder;

    /**
     * Constructor for AppInfoViewHolder.
     *
     * @param view The view to which this ViewHolder is bound to
     */
    public AppInfoViewHolder(View view) {
        super(view);

        // Get UI elements from view and store references
        name = (TextView) view.findViewById(R.id.list_package_item_name);
        version = (TextView) view.findViewById(R.id.list_package_item_version);
        icon = (ImageView) view.findViewById(R.id.list_package_item_icon);

        // Apply listener to view so that we know when user clicks on it in the list
        view.setOnClickListener(new OnAppInfoClickListener(this));
    }

    /**
     * Binds an AppInfoHolder to this AppInfoViewHolder and loads its data into the UI elements.
     *
     * @param holder The holder to bind to this AppInfoViewHolder
     * @return Returns itself
     */
    public AppInfoViewHolder bindAppInfo(AppInfoHolder holder) {

        // Store holder
        this.holder = holder;

        // Set data on UI Elements and done
        name.setText(holder.name);
        version.setText(String.format(version.getContext().getString(R.string.apps_list_tag_version), holder.version));
        icon.setImageDrawable(holder.icon);
        return this;
    }

    /**
     * Returns this AppInfoHolder this AppInfoViewHolder is bound to.
     *
     * @return The AppInfoHolder this AppInfoViewHolder is bound to
     */
    public AppInfoHolder getHolder() {
        return holder;
    }
}
