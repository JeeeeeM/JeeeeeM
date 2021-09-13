package com.example.mob_viewer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView get_response_text,post_response_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button get_request_button=findViewById(R.id.get_data);
        Button post_request_button=findViewById(R.id.post_data);

        get_response_text=findViewById(R.id.get_respone_data);
        post_response_text=findViewById(R.id.post_respone_data);


        get_request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendGetRequest();
            }
        });

        post_request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    postRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void postRequest() throws IOException {
        RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
        //RequestBody body = RequestBody.create( "{\r\n \r\n    \"plate_no1\":\"CAY2696\"\r\n  \r\n}");
        String vaspUrl="https://pmvic.api.lto.direct/ords/dl_interfaces/interface_PMVIC/v3/get_vehicleinfo";
        URL url = new URL(vaspUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(5000);
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Content-Length", "0");
        conn.setRequestProperty("Accept", "application/json");

        StringRequest stringRequest=new StringRequest(Request.Method.POST, vaspUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                   // System.out.println(response);
                    JSONObject jsonObject = new JSONObject(response);

                    post_response_text.setText( jsonObject.getString(response));

                }
                catch (Exception e){
                    e.printStackTrace();
                    post_response_text.setText("POST DATA : unable to Parse Json");
                }
            }
        }
        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                post_response_text.setText("Post Data : Response Failed");
            }
        })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params=new HashMap<String, String>();
                   // params.put("{plate_no","CAY2696}");
                params.put("{plate_no","CAY2696}");
               return params;
            }
            //"plate_no","CAY2696"
            @Override
            public Map<String,String> getHeaders(){
                Map<String,String> params=new HashMap<String, String>();
                params.put("Content-Type", "application/json");
              //  params.put("Content-Length", "0");
                params.put("Accept", "application/json");
               // params.put("{plate_no","CAY2696}");
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }

    private void sendGetRequest() {

        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        String url="http://api.larntech.net/";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                  get_response_text.setText("Data : "+ response);
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    get_response_text.setText("Vehicle info :"+jsonObject.getString(response)+"\n");

                }
                catch (Exception e){
                    e.printStackTrace();
                    get_response_text.setText("Failed to Parse Json");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                get_response_text.setText("Data : Response Failed");
            }
        });

        queue.add(stringRequest);
    }

//    private void postdata() throws MalformedURLException, JSONException {
//       RequestQueue queue = Volley.newRequestQueue(this);
//        String vaspUrl="https://pmvic.api.lto.direct/ords/dl_interfaces/interface_PMVIC/v3/get_vehicleinfo";
//        URL url = new URL(vaspUrl);
//       JsonObjectRequest request_json = new JsonObjectRequest(url,new JSONObject("plate_no","CAY2696")),

  //  }


}
