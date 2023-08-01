package com.example.ssp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


     public class ResultActivity extends AppCompatActivity {

         List<ProductModel> product_list;
         RecyclerView recyclerView;
         RecyclerView.LayoutManager manager;
         RecyclerView.Adapter adapter;

         @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_result);

             recyclerView = findViewById(R.id.recycler_view);
             product_list = new ArrayList<>();
             Button back=findViewById(R.id.toolback);
             back.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                     startActivity(intent);


                 }
             });
             Button button = findViewById(R.id.toolbut);
             button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     int sum3 = 0;
                     for(ProductModel model : product_list) {

                         if (model.mdcnt != 0) {
                             sum3++;
                         }
                     }
                     int sum = 0;
                     for (ProductModel model : product_list) {
                         sum += model.getBdmd();
                     }
                     Toast.makeText(ResultActivity.this, "Count: " + product_list.size() + ", Sum: " + sum, Toast.LENGTH_SHORT).show();

                     double sum1 = 0;
                     double sum2 = 0;
                     for (ProductModel model : product_list) {
                         sum1 += model.getPrweight();
                         sum2 += model.getInac();
                     }
                     double average1 = sum1 / product_list.size();
                     double average2 = sum2 / product_list.size();
                     Toast.makeText(ResultActivity.this, "Average1: " + average1 + ", Average2: " + average2, Toast.LENGTH_SHORT).show();

                     // Create intent to start another activity
                     Intent intent = new Intent(ResultActivity.this, TotalActivity.class);
                     intent.putExtra("average1", average1);
                     intent.putExtra("average2", average2);
                     intent.putExtra("Count",sum);
                     intent.putExtra("CountM",sum3);
                     startActivity(intent);
                 }
             });


             //Data from MainClass
             Intent intent = getIntent();
             String fromDate = intent.getStringExtra("Keyfromd");
             String toDate = intent.getStringExtra("Keytod");
             String fromTime = intent.getStringExtra("Keyfromt");
             String toTime = intent.getStringExtra("Keytot");
             String spin = intent.getStringExtra("Keyspin");

             //Send data to PHP
             String url = "http://192.168.67.179/Product/phpdb.php";
             StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                     new Response.Listener<String>() {
                         @Override
                         public void onResponse(String response) {
                             try {
                                 JSONObject jsonObject = new JSONObject(response);
                                 JSONArray jsonArray = jsonObject.getJSONArray("data");
                                 for (int i = 0; i < jsonArray.length(); i++) {
                                     JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                     String date = jsonObject1.getString("Date_of_Pouring");
                                     String time = jsonObject1.getString("Time");
                                     String stweight = jsonObject1.getString("Set_weight");
                                     float prweight = Float.parseFloat(jsonObject1.getString("Pour_weight"));
                                     String sttime = jsonObject1.getString("Set_time");
                                     String prtime = jsonObject1.getString("Pour_time");
                                     String tem = jsonObject1.getString("Temperature");
                                     float inac = Float.parseFloat(jsonObject1.getString("Inaculant"));
                                     int mdcnt = Integer.parseInt(jsonObject1.getString("Mould_count"));
                                     int bdmd = Integer.parseInt(jsonObject1.getString("Bad_Mould"));
                                     String mlnam = jsonObject1.getString("Model_name");
                                     String hetcde = jsonObject1.getString("Heat_code");
                                     ProductModel productModel = new ProductModel(date, time, stweight, prweight, sttime, prtime, tem, inac, mdcnt, bdmd, mlnam, hetcde);
                                     product_list.add(productModel);
                                 }

                                 recyclerView.setLayoutManager(new LinearLayoutManager(ResultActivity.this));
                                 recyclerView.setHasFixedSize(true);
                                 adapter = new ProductAdapter(ResultActivity.this, product_list);
                                 recyclerView.setAdapter(adapter);
                             } catch (JSONException e) {
                                 Toast.makeText(ResultActivity.this, "Response not received", Toast.LENGTH_SHORT).show();
                                 e.printStackTrace();
                             }
                         }
                     },
                     new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             Toast.makeText(ResultActivity.this, "Failed toconnect to server", Toast.LENGTH_SHORT).show();
                             error.printStackTrace();
                         }
                     }) {
                 @Override
                 protected Map<String, String> getParams() throws AuthFailureError {
                     Map<String, String> params = new HashMap<>();
                     params.put("fromDate", fromDate);
                     params.put("toDate", toDate);
                     params.put("fromTime", fromTime);
                     params.put("toTime", toTime);
                     params.put("spin", spin);
                     return params;
                 }
             };
             RequestQueue requestQueue = Volley.newRequestQueue(ResultActivity.this);
             requestQueue.add(stringRequest);
         }
     }






