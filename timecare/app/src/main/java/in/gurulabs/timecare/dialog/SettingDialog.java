/*
 * *
 *  * Created by guruLabs on 12/31/20 6:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/31/20 5:54 PM
 *
 */

package in.gurulabs.timecare.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;
import com.judemanutd.autostarter.AutoStartPermissionHelper;

import java.util.List;

import in.gurulabs.timecare.R;
import in.gurulabs.timecare.database.TimeTable;
import in.gurulabs.timecare.database.TimeTableRoomDatabase;
import in.gurulabs.timecare.utils.PrefManager;
import in.gurulabs.timecare.utils.Tools;


public class SettingDialog extends BottomSheetDialogFragment {

    PrefManager sharedPref;
    Context mContext;
    String currentAppLanguageName;


    public SettingDialog(Context context) {
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_setting,
                container, false);


        RelativeLayout delete_all_tasks = view.findViewById(R.id.delete_all_tasks);
        delete_all_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder deleteDialog = new MaterialAlertDialogBuilder(mContext);
                deleteDialog.setTitle(getString(R.string.are_you_sure))
                        .setMessage(getString(R.string.want_all_delete_task))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteTimeTable(mContext);
                                Tools.showShortLengthSnack(view,mContext.getString(R.string.all_tasks_deleted));
                            }
                        })
                        .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

            }
        });




        return view;
    }

    private void deleteTimeTable(Context context){


        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                List<TimeTable> allTimeTables = TimeTableRoomDatabase.getInstance(context).timeTableDao().getAllTimeTables();
                for (TimeTable timetable : allTimeTables){
                    TimeTableRoomDatabase.getInstance(context).timeTableDao().deleteTimeTable(timetable);
                    Tools.cancelAlarm(context,timetable.getUid());

                }





            }
        });
        thread.start();

    }


}
