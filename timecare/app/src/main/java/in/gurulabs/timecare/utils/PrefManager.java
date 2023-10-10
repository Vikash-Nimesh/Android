/*
 * *
 *  * Created by guruLabs on 12/31/20 6:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/31/20 5:53 PM
 *
 */

package in.gurulabs.timecare.utils;

import android.content.Context;
import android.content.SharedPreferences;

import in.gurulabs.timecare.R;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "timetableplus";

    private static final String ALL_NOTIFICATION = "all_notification";
    private static final String TWENTY_FOUR_HOUR_FORMAT = "24_hour_format";

    public PrefManager(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }




    public Boolean getNotification() {
        return pref.getBoolean(ALL_NOTIFICATION, true);
    }



    public Boolean get24HourFormat() {
        return pref.getBoolean(TWENTY_FOUR_HOUR_FORMAT, false);
    }





}
