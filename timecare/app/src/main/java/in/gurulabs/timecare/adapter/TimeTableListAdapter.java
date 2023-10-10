/*
 * *
 *  * Created by guruLabs on 12/31/20 6:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/31/20 5:58 PM
 *
 */

package in.gurulabs.timecare.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import in.gurulabs.timecare.R;
import in.gurulabs.timecare.activity.UpdateActivity;
import in.gurulabs.timecare.database.TimeTable;
import in.gurulabs.timecare.database.TimeTableRoomDatabase;
import in.gurulabs.timecare.utils.PrefManager;
import in.gurulabs.timecare.utils.Tools;


public class TimeTableListAdapter extends RecyclerView.Adapter<TimeTableListAdapter.CustomViewHolder>{

    List<TimeTable> timeTables;
    Context mContext;
    PrefManager prefManager;
    View itemView;



    public TimeTableListAdapter(Context context, List<TimeTable> timeTables) {
        this.mContext = context;
        this.timeTables = timeTables;
    }


    public void updateList(List<TimeTable> list) {
        this.timeTables=list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_list_item, parent, false);

        prefManager = new PrefManager(mContext);

        return new CustomViewHolder(itemView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        TimeTable timeTable=getItem(position);


        if(timeTable.getNotificationChecked() && timeTable.getNotificationPriority()==1){
            holder.notificationIcon.setImageResource(R.drawable.ic_baseline_notifications_active);
        }
        else if (timeTable.getNotificationChecked() && timeTable.getNotificationPriority()==0){
            holder.notificationIcon.setImageResource(R.drawable.ic_baseline_notifications_none);
        }
        else {
            holder.notificationIcon.setImageResource(R.drawable.ic_baseline_notifications_off);
        }




        if(prefManager.get24HourFormat()){

            if (timeTable.getToTime() != null && !timeTable.getToTime().isEmpty()) {
                holder.timeTextView.setText("• "+Tools.getTimeIn24Format(timeTable.getFromTime())+" - "+Tools.getTimeIn24Format(timeTable.getToTime()));

            }

            else {
                holder.timeTextView.setText("• "+Tools.getTimeIn24Format(timeTable.getFromTime()));
            }

        }
        else {

            if (timeTable.getToTime() != null && !timeTable.getToTime().isEmpty()) {
                holder.timeTextView.setText("• "+Tools.getTimeIn12Format(timeTable.getFromTime())+" - "+Tools.getTimeIn12Format(timeTable.getToTime()));
            }

            else {
                holder.timeTextView.setText("• "+Tools.getTimeIn12Format(timeTable.getFromTime()));
            }

        }




        holder.subjectNameTextView.setText(timeTable.getTitle());
        holder.subjectDescTextView.setText(timeTable.getDescription());

        holder.lytParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UpdateActivity.startActivity(mContext,timeTable);
                Tools.cancelNotification(mContext,timeTable.getUid());
            }
        });


        holder.moreAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(mContext, holder.moreAction);
                setForceShowIcon(popup);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getItemId()==R.id.action_share){

                                if(prefManager.get24HourFormat()){

                                    String shareWithToTime;
                                    if (timeTable.getToTime() != null) {
                                        shareWithToTime = mContext.getResources().getStringArray(R.array.days)[timeTable.getDay()] + " " + Tools.getTimeIn24Format(timeTable.getFromTime()) + " - " + Tools.getTimeIn24Format(timeTable.getToTime()) + "\n" + timeTable.getTitle();

                                    }

                                    else {
                                        shareWithToTime = mContext.getResources().getStringArray(R.array.days)[timeTable.getDay()] + " " + Tools.getTimeIn24Format(timeTable.getFromTime()) + "\n" + timeTable.getTitle();
                                    }


                                }
                                else {
                                    String shareWithToTime;
                                    if (timeTable.getToTime() != null) {
                                        shareWithToTime = mContext.getResources().getStringArray(R.array.days)[timeTable.getDay()] + " " + Tools.getTimeIn12Format(timeTable.getFromTime()) + " - " + Tools.getTimeIn12Format(timeTable.getToTime()) + "\n" + timeTable.getTitle();

                                    }

                                    else {
                                        shareWithToTime = mContext.getResources().getStringArray(R.array.days)[timeTable.getDay()] + " " + Tools.getTimeIn12Format(timeTable.getFromTime()) + "\n" + timeTable.getTitle();
                                    }

                                }


                        }

                        if(item.getItemId()==R.id.action_delete){


                            MaterialAlertDialogBuilder deleteDialog = new MaterialAlertDialogBuilder(mContext);
                            deleteDialog.setTitle(mContext.getString(R.string.are_you_sure))
                                    .setMessage(mContext.getString(R.string.want_delete_task))
                                    .setPositiveButton(mContext.getString(R.string.yes), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            deleteTimeTable(mContext,timeTable);
                                            Tools.cancelNotification(mContext,timeTable.getUid());
                                            Tools.showShortLengthSnack(itemView,mContext.getString(R.string.task_deleted));
                                        }
                                    })
                                    .setNegativeButton(mContext.getString(R.string.no), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    }).show();

                        }


                        return true;
                    }
                });

                popup.show();//showing popup menu


            }
        });


    }

    @Override
    public int getItemCount() {
        return timeTables.size();
    }

    public TimeTable getItem(int position) {
        return timeTables.get(position);
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout lytParent;
        ImageView notificationIcon,moreAction;
        MaterialTextView timeTextView,subjectNameTextView,subjectDescTextView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            lytParent=itemView.findViewById(R.id.lytParent);
            notificationIcon=itemView.findViewById(R.id.notificationIcon);
            moreAction=itemView.findViewById(R.id.moreAction);
            timeTextView=itemView.findViewById(R.id.timeTextView);
            subjectNameTextView=itemView.findViewById(R.id.subjectNameTextView);
            subjectDescTextView=itemView.findViewById(R.id.subjectDescTextView);

        }
    }


    private void deleteTimeTable(Context context,TimeTable timeTable){


        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                TimeTableRoomDatabase.getInstance(context).timeTableDao().deleteTimeTable(timeTable);
                Tools.cancelAlarm(mContext,timeTable.getUid());


            }
        });
        thread.start();

    }




    public static void setForceShowIcon(PopupMenu popupMenu) {
        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper
                            .getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod(
                            "setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


}
