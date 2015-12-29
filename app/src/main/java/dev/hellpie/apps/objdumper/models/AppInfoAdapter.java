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
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dev.hellpie.apps.objdumper.R;

public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoViewHolder> {

    private List<AppInfoHolder> mData = new ArrayList<>();

    @Override
    public AppInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppInfoViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.template_list_package, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(AppInfoViewHolder holder, int position) {
        holder.bindAppInfo(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public AppInfoAdapter setAppInfos(List<AppInfoHolder> holders) {
        for (AppInfoHolder holder : holders) {
            addAppInfo(holder);
        }

        return this;
    }

    public AppInfoAdapter clearAppInfos() {
        for (int i = mData.size() - 1; i >= 0; i--) {
            removeAppInfo(i);
        }

        return this;
    }

    public AppInfoAdapter addAppInfo(AppInfoHolder holder) {
        mData.add(holder);
        notifyItemInserted(mData.size() - 1);
        return this;
    }

    public AppInfoAdapter removeAppInfo(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        return this;
    }

    public AppInfoAdapter removeAppInfo(AppInfoHolder holder) {
        return removeAppInfo(mData.indexOf(holder));
    }
}
