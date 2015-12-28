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

package dev.hellpie.apps.objdumper.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.hellpie.apps.objdumper.R;

public class AppInfoViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView version;
    public ImageView icon;
    private AppInfoHolder holder;

    public AppInfoViewHolder(View view) {
        super(view);

        name = (TextView) view.findViewById(R.id.list_package_item_name);
        version = (TextView) view.findViewById(R.id.list_package_item_version);
        icon = (ImageView) view.findViewById(R.id.list_package_item_icon);
    }

    public AppInfoViewHolder bindAppInfo(AppInfoHolder holder) {
        this.holder = holder;

        name.setText(holder.name);
        version.setText(String.format(version.getContext().getString(R.string.apps_list_tag_version), holder.version));
        icon.setImageDrawable(holder.icon);
        return this;
    }
}
