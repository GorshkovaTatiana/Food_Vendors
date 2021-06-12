package com.example.food_vendors;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter< JSONObject> {
    int listLayout;
    ArrayList<JSONObject> list;
    Context context;


    public ListViewAdapter(Context context, int listLayout, int field ,ArrayList< JSONObject> list){
        super(context,listLayout,field,list);
        this.context=context;
        this.listLayout=listLayout;
        this.list=list;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(listLayout,parent,false);
        TextView id = itemView.findViewById(R.id.id);
        TextView name = itemView.findViewById(R.id.name);
        TextView price = itemView.findViewById(R.id.price);
        try {
            id.setText(list.get(position).getString("id"));
            name.setText(list.get(position).getString("name"));
            price.setText(list.get(position).getString("price"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        return itemView;
    }

}