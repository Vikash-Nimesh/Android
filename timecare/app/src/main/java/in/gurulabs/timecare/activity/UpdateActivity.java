package in.gurulabs.timecare.activity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;
import java.util.List;

import in.gurulabs.timecare.R;
import in.gurulabs.timecare.database.TimeTable;
import in.gurulabs.timecare.database.TimeTableRoomDatabase;
import in.gurulabs.timecare.utils.PrefManager;
import in.gurulabs.timecare.utils.Tools;

public class UpdateActivity extends AppCompatActivity {


    private static final String TAG = "UpdateActivity";



    private TextInputEditText subjectEditText,descEditText;
    private MaterialButtonToggleGroup firstDayGroup;
    private MaterialButton scheduleFromTimeButton,scheduleToTimeButton;
    private MaterialCheckBox enableNotificationCheckBox;
    private MaterialButtonToggleGroup priorityButtonGroup;
    private MaterialButton updateButton;
    private ImageView backButton,deleteButton;

    private String subjectTitle;
    private String subjectDesc;
    private int notificationPriority;


    private String finalFromTime, finalToTime;

    private int  mHourFrom, mMinuteFrom;
    private  long alarmNotifyTime;
    private String timeIn24;

    private final boolean[] dayOfWeekList = new boolean[7];
    int dayOfWeek = 0;




    private View lytParent;


    static TimeTable timeTable;

    PrefManager prefManager;

    public static void startActivity(Context context,TimeTable timeTableFromAdapter) {
        timeTable = timeTableFromAdapter;

        // Create and start intent for this activity
        Intent intent = new Intent(context, UpdateActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Tools.setSystemBarColor(this,R.color.colorPrimary);

        prefManager = new PrefManager(this);

        lytParent=findViewById(R.id.lytParent);
        subjectEditText =findViewById(R.id.subjectEditText);
        descEditText =findViewById(R.id.descEditText);
        firstDayGroup =findViewById(R.id.firstDayGroup);
        scheduleFromTimeButton= findViewById(R.id.scheduleFromTimeButton);
        scheduleToTimeButton= findViewById(R.id.scheduleToTimeButton);
        enableNotificationCheckBox= findViewById(R.id.enableNotificationCheckBox);
        priorityButtonGroup= findViewById(R.id.priorityButtonGroup);
        updateButton= findViewById(R.id.updateButton);
        backButton= findViewById(R.id.backButton);
        deleteButton= findViewById(R.id.deleteButton);

        //set data in update layout
        subjectEditText.setText(timeTable.getTitle());
        descEditText.setText(timeTable.getDescription());




        if(prefManager.get24HourFormat()){

            if (timeTable.getToTime() != null && !timeTable.getToTime().isEmpty()) {

                scheduleFromTimeButton.setText(Tools.getTimeIn24Format(timeTable.getFromTime()));
                scheduleToTimeButton.setText(Tools.getTimeIn24Format(timeTable.getToTime()));


            }

            else {
                scheduleFromTimeButton.setText(Tools.getTimeIn24Format(timeTable.getFromTime()));
                scheduleToTimeButton.setText(getString(R.string.end));
            }

        }
        else {

            if (timeTable.getToTime() != null && !timeTable.getToTime().isEmpty()) {
                scheduleFromTimeButton.setText(Tools.getTimeIn12Format(timeTable.getFromTime()));
                scheduleToTimeButton.setText(Tools.getTimeIn12Format(timeTable.getToTime()));
            }

            else {
                scheduleFromTimeButton.setText(Tools.getTimeIn12Format(timeTable.getFromTime()));
                scheduleToTimeButton.setText(getString(R.string.end));
            }

        }






        finalFromTime = timeTable.getFromTime();
        finalToTime = timeTable.getToTime();

        alarmNotifyTime =timeTable.getAlarm_notify_time();

        timeIn24=String.valueOf(timeTable.getTime_in_24());

        int day=timeTable.getDay();


        switch (day){
            case 0:
                firstDayGroup.check(R.id.sun);
                dayOfWeekList[0] = true;
                break;
            case 1:
                firstDayGroup.check(R.id.mon);
                dayOfWeekList[1] = true;
                break;
            case 2:
                firstDayGroup.check(R.id.tue);
                dayOfWeekList[2] = true;
                break;
            case 3:
                firstDayGroup.check(R.id.wed);
                dayOfWeekList[3] = true;
                break;
            case 4:
                firstDayGroup.check(R.id.thur);
                dayOfWeekList[4] = true;
                break;
            case 5:
                firstDayGroup.check(R.id.fri);
                dayOfWeekList[5] = true;
                break;
            case 6:
                firstDayGroup.check(R.id.sat);
                dayOfWeekList[6] = true;
                break;



        }



        notificationPriority=timeTable.getNotificationPriority();

        enableNotificationCheckBox.setChecked(timeTable.getNotificationChecked());
        if(enableNotificationCheckBox.isChecked()){
            priorityButtonGroup.setVisibility(View.VISIBLE);

            if(timeTable.getNotificationPriority()==0){
                priorityButtonGroup.check(R.id.normalPriorityButton);
            }
            else if(timeTable.getNotificationPriority()==1) {
                priorityButtonGroup.check(R.id.highPriorityButton);
            }

            priorityButtonGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                @Override
                public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                    if (isChecked) {
                        if (checkedId == R.id.normalPriorityButton) {
                            notificationPriority=0;
                        }

                        if (checkedId == R.id.highPriorityButton) {
                            notificationPriority=1;
                        }

                    }
                }
            });
        }




        firstDayGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {

                switch (checkedId) {

                    case R.id.sun:
                        dayOfWeekList[0] = isChecked;
                        break;
                    case R.id.mon:
                        dayOfWeekList[1] = isChecked;
                        break;
                    case R.id.tue:
                        dayOfWeekList[2] = isChecked;
                        break;
                    case R.id.wed:
                        dayOfWeekList[3] = isChecked;
                        break;
                    case R.id.thur:
                        dayOfWeekList[4] = isChecked;
                        break;
                    case R.id.fri:
                        dayOfWeekList[5] = isChecked;
                        break;
                    case R.id.sat:
                        dayOfWeekList[6] = isChecked;
                        break;


                }

            }
        });






        scheduleFromTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prefManager.get24HourFormat()){
                    showFromTimePicker(TimeFormat.CLOCK_24H);

                }
                else {
                    showFromTimePicker(TimeFormat.CLOCK_12H);
                }

            }
        });


        scheduleToTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prefManager.get24HourFormat()){
                    showToTimePicker(TimeFormat.CLOCK_24H);

                }
                else {
                    showToTimePicker(TimeFormat.CLOCK_12H);
                }

            }
        });



        enableNotificationCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    priorityButtonGroup.setVisibility(View.VISIBLE);
                    priorityButtonGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                        @Override
                        public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                            if (isChecked) {
                                if (checkedId == R.id.normalPriorityButton) {
                                    notificationPriority=0;
                                }

                                if (checkedId == R.id.highPriorityButton) {
                                    notificationPriority=1;
                                }

                            }
                        }
                    });
                }
                else {
                    priorityButtonGroup.setVisibility(View.GONE);
                }
            }
        });


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subjectTitle=subjectEditText.getText().toString();
                subjectDesc=descEditText.getText().toString();


                Log.e(TAG,subjectTitle+subjectDesc);

                List<Integer> ids = firstDayGroup.getCheckedButtonIds();
                if(TextUtils.isEmpty(subjectTitle)){
                    Tools.showSnackWithButton(view,getString(R.string.subject_name_required));
                }
                else {

                    if (ids.isEmpty() ) {
                        Tools.showSnackWithButton(view,getString(R.string.select_day));
                    }


                    else {

                        for (int i = 0; i < 7; i++) {
                            if (dayOfWeekList[i]) {

                                dayOfWeek = i;

                                Calendar currentCalendar = Calendar.getInstance();

                                Calendar customCalendar = Calendar.getInstance();
                                customCalendar.setTimeInMillis(System.currentTimeMillis());
                                customCalendar.set(Calendar.HOUR_OF_DAY, mHourFrom);
                                customCalendar.set(Calendar.MINUTE, mMinuteFrom);
                                customCalendar.set(Calendar.SECOND, 0);
                                customCalendar.set(Calendar.MILLISECOND, 0);
                                customCalendar.set(Calendar.DAY_OF_WEEK,dayOfWeek+1);

                                alarmNotifyTime = customCalendar.getTimeInMillis();

                                if (customCalendar.before(currentCalendar))
                                    alarmNotifyTime += AlarmManager.INTERVAL_DAY * 7;




                                Tools.cancelAlarm(UpdateActivity.this,timeTable.getUid());
                                Tools.setRepeatingAlarm(UpdateActivity.this,timeTable.getUid(),alarmNotifyTime);



                                updateTimeTable(UpdateActivity.this, subjectTitle, subjectDesc, dayOfWeek, finalFromTime, finalToTime, enableNotificationCheckBox.isChecked(), notificationPriority,alarmNotifyTime,Integer.parseInt(timeIn24));

                                Tools.showSnackWithFinish(UpdateActivity.this,lytParent,getString(R.string.task_updated));


                            }
                        }


                    }

                }







            }


        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MaterialAlertDialogBuilder deleteDialog = new MaterialAlertDialogBuilder(UpdateActivity.this);
                deleteDialog.setTitle(getString(R.string.are_you_sure))
                        .setMessage(getString(R.string.want_delete_task))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteTimeTable(UpdateActivity.this,timeTable);
                                Tools.cancelNotification(UpdateActivity.this,timeTable.getUid());
                                Tools.showSnackWithFinish(UpdateActivity.this,lytParent,getString(R.string.task_deleted));
                            }
                        })
                        .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();


            }
        });

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @SuppressLint("DefaultLocale")
    private void showFromTimePicker(int timeFormat) {

       /* Calendar calendar = Calendar.getInstance();
        int hour12hrs = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
*/
        String [] fromTimeFromDatabase = timeTable.getFromTime().split(":");

        int fromhourIn24 = Integer.parseInt(fromTimeFromDatabase[0]);
        int fromminute = Integer.parseInt(fromTimeFromDatabase[1]);


        MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(timeFormat)
                .setHour(fromhourIn24)
                .setMinute(fromminute)
                .build();

        materialTimePicker.show(getSupportFragmentManager(), "MaterialTimePicker");

        materialTimePicker.addOnPositiveButtonClickListener(dialog -> {
            mHourFrom = materialTimePicker.getHour();
            mMinuteFrom = materialTimePicker.getMinute();


          /*  Calendar timeIn24Calender = Calendar.getInstance();
            timeIn24Calender.set(Calendar.HOUR_OF_DAY, mHourFrom);
            timeIn24Calender.set(Calendar.MINUTE, mMinuteFrom);

            timeIn24= timeIn24Calender.getTimeInMillis();*/


            timeIn24= String.format("%02d",mHourFrom)+String.format("%02d",mMinuteFrom);

            //time in 24 format
            finalFromTime = String.format("%02d",mHourFrom) + ":" + String.format("%02d",mMinuteFrom);

            int hour = mHourFrom % 12;
            String convertedTimeFor12Hr = String.format("%02d:%02d %s", hour == 0 ? 12 : hour,
                    mMinuteFrom, mHourFrom < 12 ? "am" : "pm");

            if(prefManager.get24HourFormat()){
                scheduleFromTimeButton.setText(finalFromTime);
            }
            else {
                scheduleFromTimeButton.setText(convertedTimeFor12Hr);
            }




        });

    }


    @SuppressLint("DefaultLocale")
    private void showToTimePicker(int timeFormat) {

        int fromhourIn24;
        int fromminute;

        if(timeTable.getToTime() != null && !timeTable.getToTime().isEmpty()){
            String [] toTimeFromDatabase = timeTable.getToTime().split(":");

             fromhourIn24 = Integer.parseInt(toTimeFromDatabase[0]);
             fromminute = Integer.parseInt(toTimeFromDatabase[1]);
        }

        else {
            Calendar calendar = Calendar.getInstance();
            fromhourIn24 = calendar.get(Calendar.HOUR_OF_DAY);
            fromminute = calendar.get(Calendar.MINUTE);

        }


        MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(timeFormat)
                .setHour(fromhourIn24)
                .setMinute(fromminute)
                .build();


        materialTimePicker.show(getSupportFragmentManager(), "MaterialTimePicker");

        materialTimePicker.addOnPositiveButtonClickListener(dialog -> {
            int hour = materialTimePicker.getHour();
            int minute = materialTimePicker.getMinute();


            finalToTime = hour + ":" +minute;

            int hourIn12 = hour % 12;
            String convertedTimeFor12Hr = String.format("%02d:%02d %s", hourIn12 == 0 ? 12 : hourIn12,
                    minute, hour < 12 ? "am" : "pm");

            if(prefManager.get24HourFormat()){
                scheduleToTimeButton.setText(finalToTime);
            }
            else {
                scheduleToTimeButton.setText(convertedTimeFor12Hr);
            }


        });

    }


    private void updateTimeTable(Context context, String subjectName, String subjectDescription, int day, String fromTime, String toTime, Boolean isNotificationChecked, int notificationPriority,long alarmNotifyTime,long timeIn24){


        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                timeTable.setTitle(subjectName);
                timeTable.setDescription(subjectDescription);
                timeTable.setDay(day);
                timeTable.setFromTime(fromTime);
                timeTable.setToTime(toTime);
                timeTable.setNotificationChecked(isNotificationChecked);
                timeTable.setNotificationPriority(notificationPriority);
                timeTable.setAlarm_notify_time(alarmNotifyTime);
                timeTable.setTime_in_24(timeIn24);
                TimeTableRoomDatabase.getInstance(context).timeTableDao().updateTimeTable(timeTable);



            }
        });
        thread.start();

    }




    private void deleteTimeTable(Context context,TimeTable timeTable){


        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                TimeTableRoomDatabase.getInstance(context).timeTableDao().deleteTimeTable(timeTable);
                Tools.cancelAlarm(UpdateActivity.this,timeTable.getUid());


            }
        });
        thread.start();

    }


}