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

package dev.hellpie.apps.objdumper.listeners;

import android.view.View;

public class FABListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        /* Animation:
         * A) Fab moves to position:
         *    - Quad Bezier curve:
         *      - Starts From:
         *        - X: FAB Center X
         *        - Y: FAB Center Y
         *      - Ends At:
         *        - X: Display Middle
         *        - Y: R.id.activity_content Center Y
         * B) Expanded Toolbar retracts:
         *    - Default animation
         * C) Circular reveal:
         *    - From FAB position
         * D) New Activity Starts (Out of animation end)
         *
         * Time: 300ms
         *
         * Timeline (1 char = 10ms):
         *
         * A) |=================            |
         * B) |   ==========                |
         * C) |              ===============|
         * D) |                          =======---...| (30ms before end, continues afterwards)
         */

//        animateFAB(v);

    }

//    private void animateFAB(View fab) {
//
//    }

//    @Deprecated
//    private void _animateFAB(View view) {
//        Activity mainActivity = AppUtils.getUtils().getActivity(view);
//        if(mainActivity == null) return;
//
//        // Retract AppBarLayout
//        AppUtils.getUtils().toggleAppBar(mainActivity, R.id.appbarlayout, AppUtils.State.COLLAPSED);
//
//        // Check if FAB, we should be worried if it's not...
//        if(!(view instanceof FloatingActionButton)) return;
//
//        // Move FAB into position (center of Activity's content)
//        DisplayMetrics metrics = new DisplayMetrics();
//        mainActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//
//        int xCenter = metrics.heightPixels / 2;
//        int yCenter = metrics.widthPixels / 2;
//
//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
//        int xFabOffset = params.getMarginEnd();
//        Log.d("LOG-DEBUG", "Margin Start: " + xFabOffset);
//
//        // _TODO: Continue: Point A) of the Animation :D Suicide!
//    }
}
