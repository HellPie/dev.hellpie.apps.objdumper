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

package dev.hellpie.apps.objdumper.dumper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dev.hellpie.apps.objdumper.R;
import dev.hellpie.apps.objdumper.models.AppInfoAdapter;
import dev.hellpie.apps.objdumper.models.AppInfoHolder;

public class AsyncDumper extends AsyncTask<Void, AppInfoHolder, Void> {

    private ProgressDialog dialog;
    private AppInfoAdapter adapter;

    public AsyncDumper(Activity activity, AppInfoAdapter appInfoAdapter) {
        adapter = appInfoAdapter;
        dialog = new ProgressDialog(activity);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setTitle(activity.getString(R.string.progress_dialog_loading_apps));
        dialog.setMessage(activity.getString(R.string.progress_dialog_loading_apps));
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        final Context context = dialog.getContext();
        final PackageManager pm = context.getPackageManager();

        List<PackageInfo> packages = pm.getInstalledPackages(0);
        Collections.sort(packages, new Comparator<PackageInfo>() {
            @Override
            public int compare(PackageInfo lhs, PackageInfo rhs) {
                String labell = lhs.applicationInfo.loadLabel(pm).toString().toLowerCase();
                String labelr = rhs.applicationInfo.loadLabel(pm).toString().toLowerCase();

                return labell.compareTo(labelr);
            }
        });

        for (PackageInfo pkgInfo : packages) {
            ApplicationInfo appInfo = pkgInfo.applicationInfo;
            if (appInfo == null || (appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) continue;

            File libs[] = new File(appInfo.nativeLibraryDir).listFiles();
            if (libs == null || libs.length == 0) continue;

            AppInfoHolder holder = new AppInfoHolder();
            holder.name = appInfo.loadLabel(pm).toString();
            holder.id = pkgInfo.packageName;
            holder.version = pkgInfo.versionName;
            holder.path = appInfo.dataDir;
            holder.icon = appInfo.loadIcon(pm);

            holder.libs = new ArrayList<>(Arrays.asList(libs));
            Collections.sort(holder.libs, new Comparator<File>() {
                @Override
                public int compare(File lhs, File rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });

            publishProgress(holder);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(AppInfoHolder... values) {
        super.onProgressUpdate(values);

        String message = dialog.getContext().getString(R.string.progress_dialog_loading);
        dialog.setMessage(String.format(message, values[0].name));

        adapter.addAppInfo(values[0]);
    }
}
