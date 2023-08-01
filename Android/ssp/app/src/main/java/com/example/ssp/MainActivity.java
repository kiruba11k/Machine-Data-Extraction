package com.example.ssp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    Spinner spinner;
    static String selectedValue;
    ArrayList<String>modellist=new ArrayList<>();
    ArrayAdapter<String>modeladapter;
    RequestQueue requestQueue;

    EditText efromdate,etodate,efromtime,etotime;

    Button button;

    private Calendar myCalendar;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        efromdate=findViewById(R.id.fromd);
        etodate=findViewById(R.id.tod);
        etotime=findViewById(R.id.tot);
        efromtime=findViewById(R.id.fromt);
        button=findViewById(R.id.okbt);
        myCalendar = Calendar.getInstance();


        requestQueue = Volley.newRequestQueue(this);
        spinner=findViewById(R.id.spin);



        String url = "http://192.168.67.179/product/Dbconfig.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Model_name");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String Modelname = jsonObject.optString("Model_name");
                                modellist.add(Modelname);}
                            modellist.add("ALL");
                            modeladapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, modellist);
                            modeladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(modeladapter);
                            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    selectedValue = modellist.get(position);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });


                            // }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);
        efromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Format selected date and set EditText text
                                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy", Locale.getDefault());
                                calendar.set(year, month, dayOfMonth);
                                String selectedDate = sdf.format(calendar.getTime());
                                efromdate.setText(selectedDate);
                            }
                        },
                        year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        etodate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Format selected date and set EditText text
                                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy", Locale.getDefault());
                                calendar.set(year, month, dayOfMonth);
                                String selectedDate = sdf.format(calendar.getTime());
                                etodate.setText(selectedDate);
                            }
                        },
                        year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        efromtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Step 5: Format selected time and set EditText text
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                String selectedTime = sdf.format(calendar.getTime());
                                efromtime.setText(selectedTime);
                            }
                        },
                        hour, minute, true);
                timePickerDialog.show();
            }
        });

        etotime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Step 5: Format selected time and set EditText text
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                String selectedTime = sdf.format(calendar.getTime());
                                etotime.setText(selectedTime);
                            }
                        },
                        hour, minute, true);
                timePickerDialog.show();
            }
        });






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromodate=efromdate.getText().toString();
                String toodate=etodate.getText().toString();
                String fromootime=efromtime.getText().toString();
                String tootime=etotime.getText().toString();
                // spinner = findViewById(R.id.spin);
                String selectedItem=MainActivity.selectedValue;

                Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("Keyfromd",fromodate);

                //if(fromodate !=null && !fromodate.isEmpty()){}
                intent.putExtra("Keytod",toodate);

                //if(toodate !=null && !toodate.isEmpty()){}
                intent.putExtra("Keyfromt",fromootime);

                //if(fromootime !=null && !fromootime.isEmpty()){}
                intent.putExtra("Keytot",tootime);

                //if(tootime !=null && !tootime.isEmpty()){}
                intent.putExtra("Keyspin",selectedItem);


                startActivity(intent);
            }
        });

    }

}