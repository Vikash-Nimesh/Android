package in.gurulabs.timecare.activity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.content.Context;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import in.gurulabs.timecare.R;
import in.gurulabs.timecare.database.TimeTable;
import in.gurulabs.timecare.database.TimeTableRoomDatabase;
import in.gurulabs.timecare.utils.PrefManager;
import in.gurulabs.timecare.utils.Tools;

public class CreateActivity extends AppCompatActivity {


    private static final String TAG = "CreateActivity";


    private TextInputEditText subjectEditText, descEditText;
    private MaterialButtonToggleGroup firstDayGroup;
    private MaterialButton scheduleFromTimeButton, scheduleToTimeButton;
    private MaterialCheckBox enableNotificationCheckBox;
    private MaterialButtonToggleGroup priorityButtonGroup;
    private MaterialButton createButton;
    private ImageView backButton;

    private String subjectTitle;
    private String subjectDesc;

    private int notificationPriority = 0;


    private String finalFromTime, finalToTime;

    private int mHourFrom, mMinuteFrom;
    private long alarmNotifyTime;
    private String timeIn24;

    private final boolean[] dayOfWeekList = new boolean[7];
    int dayOfWeek = 0;

    private View lytParent;

    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Tools.setSystemBarColor(this, R.color.colorPrimary);

        prefManager = new PrefManager(this);

        lytParent = findViewById(R.id.lytParent);
        subjectEditText = findViewById(R.id.subjectEditText);
        descEditText = findViewById(R.id.descEditText);
        firstDayGroup = findViewById(R.id.firstDayGroup);
        scheduleFromTimeButton = findViewById(R.id.scheduleFromTimeButton);
        scheduleToTimeButton = findViewById(R.id.scheduleToTimeButton);
        enableNotificationCheckBox = findViewById(R.id.enableNotificationCheckBox);
        priorityButtonGroup = findViewById(R.id.priorityButtonGroup);
        createButton = findViewById(R.id.createButton);
        backButton = findViewById(R.id.backButton);


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
                if (b) {
                    priorityButtonGroup.setVisibility(View.VISIBLE);
                    priorityButtonGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
                        @Override
                        public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                            if (isChecked) {
                                if (checkedId == R.id.normalPriorityButton) {
                                    notificationPriority = 0;
                                }

                                if (checkedId == R.id.highPriorityButton) {
                                    notificationPriority = 1;
                                }

                            }
                        }
                    });
                } else {
                    priorityButtonGroup.setVisibility(View.GONE);
                }
            }
        });


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subjectTitle = subjectEditText.getText().toString();
                subjectDesc = descEditText.getText().toString();

                firstDayGroup.check(R.id.thur);


                List<Integer> ids = firstDayGroup.getCheckedButtonIds();
                if (TextUtils.isEmpty(subjectTitle)) {
                   Tools.showSnackWithButton(view,getString(R.string.subject_name_required));
                }
                else {

                    if (ids.isEmpty()) {
                        Tools.showSnackWithButton(view,getString(R.string.select_day));
                    }


                    else if ((scheduleFromTimeButton.getText().equals("START"))) {
                        Tools.showSnackWithButton(view,getString(R.string.select_time));
                    } else {

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
                                customCalendar.set(Calendar.DAY_OF_WEEK, dayOfWeek + 1);

                                alarmNotifyTime = customCalendar.getTimeInMillis();

                                if (customCalendar.before(currentCalendar))
                                    alarmNotifyTime += AlarmManager.INTERVAL_DAY * 7;

                                Log.e(TAG, "arr: " + Arrays.toString(dayOfWeekList));

                                insertTimeTable(CreateActivity.this, subjectTitle, subjectDesc, dayOfWeek, finalFromTime, finalToTime, enableNotificationCheckBox.isChecked(), notificationPriority, alarmNotifyTime,Integer.parseInt(timeIn24));

                                Tools.showSnackWithFinish(CreateActivity.this,lytParent,getString(R.string.task_added));

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

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //  finishAfterTransition();
    }


    @SuppressLint("DefaultLocale")
    private void showFromTimePicker(int timeFormat) {

        Calendar calendar = Calendar.getInstance();
        int hour12hrs = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);


        MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(timeFormat)
                .setHour(hour12hrs)
                .setMinute(minutes)
                .build();

        materialTimePicker.show(getSupportFragmentManager(), "MaterialTimePicker");

        materialTimePicker.addOnPositiveButtonClickListener(dialog -> {
            mHourFrom = materialTimePicker.getHour();
            mMinuteFrom = materialTimePicker.getMinute();

            timeIn24= String.format("%02d",mHourFrom)+String.format("%02d",mMinuteFrom);

            //24hr time format
            finalFromTime = String.format("%02d",mHourFrom) + ":" + String.format("%02d",mMinuteFrom);

            //12hr time format
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


        Calendar calendar = Calendar.getInstance();
        int hour12hrs = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);

        MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(timeFormat)
                .setHour(hour12hrs + 1)
                .setMinute(minutes)
                .build();


        materialTimePicker.show(getSupportFragmentManager(), "MaterialTimePicker");

        materialTimePicker.addOnPositiveButtonClickListener(dialog -> {
            int hour = materialTimePicker.getHour();
            int minute = materialTimePicker.getMinute();

            //24hr time format
            finalToTime = String.format("%02d",hour) + ":" + String.format("%02d",minute);

            //12hr time format
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


    private void insertTimeTable(Context context, String subjectName, String subjectDescription, int day, String fromTime, String toTime, Boolean isNotificationChecked, int notificationPriority, long alarmNotifyTime,long timeIn24) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                TimeTable timeTable = new TimeTable();
                timeTable.setTitle(subjectName);
                timeTable.setDescription(subjectDescription);
                timeTable.setDay(day);
                timeTable.setFromTime(fromTime);
                timeTable.setToTime(toTime);
                timeTable.setNotificationChecked(isNotificationChecked);
                timeTable.setNotificationPriority(notificationPriority);
                timeTable.setAlarm_notify_time(alarmNotifyTime);
                timeTable.setTime_in_24(timeIn24);
                TimeTableRoomDatabase.getInstance(context).timeTableDao().insertTimeTable(timeTable);


                List<TimeTable> l = TimeTableRoomDatabase.getInstance(context).timeTableDao().getAllTimeTables();

                timeTable = l.get(l.size() - 1);

                Log.e(TAG, timeTable.getUid() + "");

                Tools.setRepeatingAlarm(context, timeTable.getUid(),alarmNotifyTime);



            }
        });
        thread.start();

    }



}