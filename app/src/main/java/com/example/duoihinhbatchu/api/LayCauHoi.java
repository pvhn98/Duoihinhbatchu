package com.example.duoihinhbatchu.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import object.CauDo;
import object.Data;

public class LayCauHoi extends AsyncTask<Void, Void, Void> {
    String data;

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://172.20.10.7/DuoiHinhBatChu/layCauHoi.php")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            data = responseBody.string();

        } catch (IOException e) {
            data = null;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data != null) {
            try {
                Data.getData().arrCauDo.clear();
                JSONArray array = new JSONArray(data);
                for (int i = 0; i < array.length(); i ++) {
                    JSONObject o = array.getJSONObject(i);
                    CauDo c = new CauDo();
                    c.img = o.getString("anh");
                    c.ten = o.getString("ten");
                    c.dapAn = o.getString("dapan");
                    Data.getData().arrCauDo.add(c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }
    }
}
;