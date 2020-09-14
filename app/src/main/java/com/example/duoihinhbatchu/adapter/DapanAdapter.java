package com.example.duoihinhbatchu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duoihinhbatchu.R;

import java.util.ArrayList;
import java.util.List;

public class DapanAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> arr;

    public DapanAdapter(@NonNull Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arr = new ArrayList<String>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_dapan, null);
        }
        TextView txvCauTraLoi = convertView.findViewById(R.id.txvCauTraLoi);
        txvCauTraLoi.setText(this.arr.get(position));
        return convertView;
    }
}
