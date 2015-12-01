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

package dev.hellpie.apps.objdumper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;

public class AppUtils {

    private static AppUtils SELF = null;

    private AppUtils() {
        SELF = this;
    }

    @NonNull
    public static AppUtils getUtils() {
        if (SELF == null) {
            return new AppUtils();
        } else {
            return SELF;
        }
    }

    /**
     * Sets the given AppBarLayout (in the form of a Resource Layout ID) to the custom state
     * (Expanded/Collapsed), then returns itself.
     * If the AppBarLayout is already in the requested state, Magikarp will use Splash...
     *
     * @param activity       The Activity in which to find the AppBarLayout
     * @param appBarLayoutId The Resource ID of the AppBarLayout
     * @param newState       The requested State for the AppBarLayout (Expanded or Collapsed)
     * @return Returns itself
     */
    @NonNull
    public AppUtils toggleAppBar(@NonNull Activity activity, int appBarLayoutId, State newState) {
        View appBarLayout = activity.findViewById(appBarLayoutId);
        if (!(appBarLayout instanceof AppBarLayout)) return this;

        if (newState.equals(State.COLLAPSED)) {
            ((AppBarLayout) appBarLayout).setExpanded(false, true);
        } else if (newState.equals(State.EXPANDED)) {
            ((AppBarLayout) appBarLayout).setExpanded(true, true);
        }

        return this;
    }

    /**
     * Returns the Activity that contains the given view if it exists, or null if the View
     * isn't an Activity's child.
     *
     * @param view The View to which get the parent Activity from
     * @return The container Activity of view, or null
     */
    @Nullable
    public Activity getActivity(@NonNull View view) {
        Context context = view.getContext();

        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) return (Activity) context;
            context = ((ContextWrapper) context).getBaseContext();
        }

        return null;
    }

    public float[] getCenterOf(@NonNull View view) {
        return new float[]{view.getX() + view.getWidth() / 2, view.getY() + view.getHeight() / 2};
    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public enum State {
        COLLAPSED,
        EXPANDED,
    }
}
