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

/**
 * AsyncAppDetector class. This class holds code for asynchronously load each app with JNI
 * libraries into the RecyclerView that contains said app's list.
 */
public class AsyncAppDetector extends AsyncTask<Void, AppInfoHolder, Void> {

    // Elements shared with other functions in this AsyncTask
    private final ProgressDialog dialog;
    private final AppInfoAdapter adapter;

    /**
     * Constructor for AsyncAppDetector.
     *
     * @param activity       Activity to which the shown dialog should be bound
     * @param appInfoAdapter AppInfoAdapter to which items should be added
     */
    public AsyncAppDetector(Activity activity, AppInfoAdapter appInfoAdapter) {

        // Setup dialog and save adapter for later use
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

        // Show dialog, fun begins!
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        // Get context, then from it get package manager
        final Context context = dialog.getContext();
        final PackageManager pm = context.getPackageManager();

        // Store the list of apps from the package manager and sort them alphabetically
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        Collections.sort(packages, new Comparator<PackageInfo>() {
            @Override
            public int compare(PackageInfo lhs, PackageInfo rhs) {
                String labell = lhs.applicationInfo.loadLabel(pm).toString().toLowerCase();
                String labelr = rhs.applicationInfo.loadLabel(pm).toString().toLowerCase();

                return labell.compareTo(labelr);
            }
        });

        // Run this for each app in the list
        for (PackageInfo pkgInfo : packages) {

            // Get ApplicationInfos for the app and skip it if it's null or if system app
            ApplicationInfo appInfo = pkgInfo.applicationInfo;
            if (appInfo == null || (appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) continue;

            // Get libraries in the native directory, since we only want to elaborate apps
            // using JNI, skip processing of the app if no libraries are found
            File libs[] = new File(appInfo.nativeLibraryDir).listFiles();
            if (libs == null || libs.length == 0) continue;

            // Create a new AppInfoHolder and save the data from the app into it
            AppInfoHolder holder = new AppInfoHolder();
            holder.name = appInfo.loadLabel(pm).toString();
            holder.id = pkgInfo.packageName;
            holder.version = pkgInfo.versionName;
            holder.path = appInfo.dataDir;
            holder.icon = appInfo.loadIcon(pm);

            // Save libs and sort them alphabetically
            holder.libs = new ArrayList<>(Arrays.asList(libs));
            Collections.sort(holder.libs, new Comparator<File>() {
                @Override
                public int compare(File lhs, File rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });

            // Update the dialog with the parsed infos
            publishProgress(holder);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        // All done? Then remove the dialog
        dialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(AppInfoHolder... values) {
        super.onProgressUpdate(values);

        // Build message using app's name in the given holder
        String message = dialog.getContext().getString(R.string.progress_dialog_loading);
        dialog.setMessage(String.format(message, values[0].name));

        // Add the parsed app into the adapter
        adapter.addAppInfo(values[0]);
    }
}
