package com.example.hashdash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SearchActivity extends AppCompatActivity {

    TextView textview;
    ArrayList<String> arrayList;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        getVehicleNumber("E20000001A0101022130444B");

        /*List<Map<String, Object>> rt = new ArrayList<Map<String, Object>>();

        // Dummy data
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("EPC", "EPC-" + i);
            map.put("ReadCount", i * 10);
            rt.add(map);
        }


        SearchableSpinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> searchAdapter = new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_list_item_1, getDestinationList());
        spinner.setAdapter(searchAdapter);




        getVehicleIdList();

        textview=findViewById(R.id.testView);
        arrayList=new ArrayList<>();

        arrayList.add("Activity Main");
        arrayList.add("Complete Activity");
        arrayList.add("Amazon Flipkart");
        arrayList.add("Counter Strike");
        arrayList.add("Git & Github");
        arrayList.add("Indian Zonal");
        arrayList.add("Indian Company PVt");
        arrayList.add("ZERO KA HERO");

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog=new Dialog(SearchActivity.this);


                dialog.setContentView(R.layout.dialog_searchable_spinner);


                dialog.getWindow().setLayout(650,800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText=dialog.findViewById(R.id.edit_text);
                ListView listView=dialog.findViewById(R.id.list_view);


                ArrayAdapter<String> adapter=new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_list_item_1,getDestinationList());

                // set adapter
                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        textview.setText(adapter.getItem(position));
                        // Dismiss dialog
                        dialog.dismiss();
                    }
                });
            }
        });*/
    }


    private ArrayList<HashMap<String,String>> getVehicleIdList(){


        ArrayList<HashMap<String,String>> vehicleIdList=new ArrayList<>();



        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        Request get = new Request.Builder()
                .url("https://elstockyard.tranzol.com/apiv1/vehftag?Tag=E20000001A0101022130444B")
                .method("get", body)
                .addHeader("clientId", "TRANZOL")
                .addHeader("clientSecret", "TRANZOLAPIRFID")
                .build();

        client.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = response.body();
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    String asd = responseBody.string();
                    String r = asd.replace("\\", "");

                    Log.i("data", "asd");
                    Log.i("data", r);

                    String org = r.substring(1, r.length()-1);
                    Log.i("data", org);

                    JSONArray jsonArray = new JSONArray(org);
                    /*r = r.replace("\\\"","'");
                    JSONObject jo = new JSONObject(r.substring(1,r.length()-1));*/


                    // Now you can work with the JSONArray
                    // For example, you can access the first element in the array
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    // Access individual values
                    int vehicleId = jsonObject.getInt("VehicleId");
                    String vehicleNo = jsonObject.getString("VehicleNo");
                    String rfid = jsonObject.getString("RFID");

                    // Do something with the extracted values
                    System.out.println("VehicleId: " + vehicleId);
                    System.out.println("VehicleNo: " + vehicleNo);
                    System.out.println("RFID: " + rfid);




                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return vehicleIdList;
    }

    private ArrayList<String> getDestinationList(){

        ArrayList<String> destinationArrayList = new ArrayList<>();
        /*destinationArrayList.add(0, "Select a destination");*/

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        Request get = new Request.Builder()
                .url("https://elstockyard.tranzol.com/apiv1/getdestinationlist")
                .method("get", body)
                .addHeader("clientId", "TRANZOL")
                .addHeader("clientSecret", "TRANZOLAPIRFID")
                .build();

        client.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = response.body();
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    JSONArray jsonArray = new JSONArray(responseBody.string());
                    int id;
                    String name;

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject row = jsonArray.getJSONObject(i);
                        id = row.getInt("Id");
                        name = row.getString("Name");
                        destinationArrayList.add(name);

                        /*Log.i("data", id + " " + name);*/
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return destinationArrayList;
    }

    private void getVehicleNumber(String epicNumber){


        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        Request get = new Request.Builder()
                .url("https://elstockyard.tranzol.com/apiv1/vehftag?Tag="+epicNumber)
                .method("get", body)
                .addHeader("clientId", "TRANZOL")
                .addHeader("clientSecret", "TRANZOLAPIRFID")
                .build();

        client.newCall(get).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = response.body();
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    String asd = responseBody.string();
                    String r = asd.replace("\\", "");

                    Log.i("data", "asd");
                    Log.i("data", r);

                    String org = r.substring(1, r.length()-1);
                    Log.i("data", org);

                    JSONArray jsonArray = new JSONArray(org);
                    /*r = r.replace("\\\"","'");
                    JSONObject jo = new JSONObject(r.substring(1,r.length()-1));*/


                    // Now you can work with the JSONArray
                    // For example, you can access the first element in the array
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    // Access individual values
                    int vehicleId = jsonObject.getInt("VehicleId");
                    String vehicleNo = jsonObject.getString("VehicleNo");
                    String rfid = jsonObject.getString("RFID");

                    // Do something with the extracted values
                    System.out.println("VehicleId: " + vehicleId);
                    System.out.println("VehicleNo: " + vehicleNo);
                    System.out.println("RFID: " + rfid);




                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    }

