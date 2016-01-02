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

import android.graphics.drawable.Drawable;

import java.io.File;
import java.util.ArrayList;

/**
 * AppInfoHolder class. This class holds data relative to a single app.
 */
public class AppInfoHolder {

    // Data used of the app this AppInfoHolder is referencing
    public String name;
    public String id;
    public String version;
    public String path;
    public ArrayList<File> libs;
    public Drawable icon;

    @Override
    public boolean equals(Object o) {

        // An AppInfoViewHolder is the same as another one if it has same name or version
        return !(o == null || !o.getClass().equals(getClass()))
                && name.equals(((AppInfoHolder) o).name)
                && version.equals(((AppInfoHolder) o).version);
    }

    @Override
    public int hashCode() {

        // An AppInfoHolder is useless unless it has a name of an app bound to it, which also
        // assumes all the other necessary data from that app is also stored in this holder
        return (name == null ? 0 : name.hashCode());
    }
}
