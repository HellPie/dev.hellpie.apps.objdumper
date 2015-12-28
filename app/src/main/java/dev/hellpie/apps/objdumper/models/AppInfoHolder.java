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

import android.graphics.drawable.Drawable;

import java.io.File;
import java.util.ArrayList;

public class AppInfoHolder {

    public String name;
    public String id;
    public String version;
    public String path;
    public ArrayList<File> libs;

    public Drawable icon;

    @Override
    public boolean equals(Object o) {
        return !(o == null || !o.getClass().equals(getClass()))
                && name.equals(((AppInfoHolder) o).name)
                && version.equals(((AppInfoHolder) o).version);
    }

    @Override
    public int hashCode() {
        return (name == null ? 0 : name.hashCode());
    }
}
