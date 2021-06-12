package com.example.food_vendors;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    JSONArray jsonArray;
    String id_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        try {
            JSONObject jsonObject = new JSONObject(getJSonData("salads.json"));
            jsonArray = jsonObject.getJSONArray("salads");
        }catch (JSONException e){
            e.printStackTrace();
        }
        ArrayList< JSONObject> listItems = getArrayListFromJSONArray(jsonArray);
        ListAdapter adapter = new ListViewAdapter(this,R.layout.row,R.id.name,listItems);
        listView.setAdapter(adapter);
    }

    //private JSONArray getJSonData(String filename){
    private String getJSonData(String filename){
        //JSONArray jsonArray = null;
        String json = null;
        try {
            InputStream inputStream = getResources().getAssets().open(filename);
            int size = inputStream.available();
            byte[] data = new byte[size];
            inputStream.read(data);
            inputStream.close();
            json = new String(data,"UTF-8");
            //jsonArray = new JSONArray(json);
        }catch (IOException e){
            e.printStackTrace();
        }
        //return jsonArray;
        return json;
    }
    private ArrayList< JSONObject> getArrayListFromJSONArray(JSONArray jsonArray){
        ArrayList< JSONObject> aList = new ArrayList<JSONObject>();
        try {
            if(jsonArray!= null){
                for(int i = 0; i< jsonArray.length();i++){
                    aList.add(jsonArray.getJSONObject(i));
                }
            }
        }catch (JSONException js){
            js.printStackTrace();
        }
        return aList;
    }
    public void onButtonClick(View view) throws IOException {
        //URL myurl=new URL("http://localhost:8080/orders");
        //HttpURLConnection con = (HttpURLConnection)myurl.openConnection();
        //con.setRequestMethod("POST");
        //con.setUseCaches(false); con.connect();
        //OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        TextView id = findViewById(R.id.id);
        id_=id.getText().toString();
        //wr.write(id_); // data is the post data to send
        //wr.flush();

        Toast.makeText(this, id_, Toast.LENGTH_SHORT).show();

    }
}