package com.example.duoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import com.bumptech.glide.Glide;
import com.example.duoihinhbatchu.adapter.DapanAdapter;

import model.ChoiGameModel;
import object.CauDo;

public class PlayActivity extends AppCompatActivity {
    ChoiGameModel model;
    CauDo cauDo;
    private String dapAn = "catinh";
    ArrayList<String> arrCauTraLoi;
    GridView gdvcautraloi;
    int index = 0;
    ArrayList<String> arrDapAn;
    GridView gdvDapAn;
    ImageView imgQuestion;
    TextView txvTienNguoiDung;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        init();
        anhXa();
        setOnClick();
        hienCauDo();
        bamData();
        hienThiCauTraLoi();
        hienThiDapan();
    }

    private void anhXa() {
        gdvcautraloi = findViewById(R.id.gdvcautraloi);
        gdvDapAn = findViewById(R.id.gdvDapAn);
        imgQuestion = findViewById(R.id.imgQuestion);
        txvTienNguoiDung = findViewById(R.id.txvTienNguoiDung);
    }

    private void init() {
        model = new ChoiGameModel(this);
        arrCauTraLoi = new ArrayList<>();
        arrDapAn = new ArrayList<>();

    }

    private void hienCauDo() {
        cauDo = model.layCauDo();
        dapAn = cauDo.dapAn;
        bamData();
        hienThiCauTraLoi();
        hienThiDapan();
        Glide.with(this)
                .load(cauDo.img)
                .into(imgQuestion);
        model.layTT();
        txvTienNguoiDung.setText(model.nguoidung.tien + "$");
    }


    private void hienThiCauTraLoi() {
        gdvcautraloi.setNumColumns(arrCauTraLoi.size());
        gdvcautraloi.setAdapter(new DapanAdapter(this, 0, arrCauTraLoi));
    }

    private void hienThiDapan() {
        gdvDapAn.setNumColumns(arrDapAn.size() / 2);
        gdvDapAn.setAdapter(new DapanAdapter(this, 0, arrDapAn));
    }

    private void setOnClick() {
        gdvDapAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                if (s.length() != 0 && index < arrCauTraLoi.size()) {
                    for (int i = 0; i < arrCauTraLoi.size(); i++) {
                        if (arrCauTraLoi.get(i).length() == 0) {
                            index = i;
                            break;
                        }
                    }
                    arrDapAn.set(position, "");
                    arrCauTraLoi.set(index, s);
                    index++;
                    hienThiCauTraLoi();
                    hienThiDapan();
                    checkWin();
                }
            }
        });
        gdvcautraloi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                if (s.length() != 0) {
                    index = position;
                    arrCauTraLoi.set(position, "");
                    for (int i = 0; i < arrDapAn.size(); i++) {
                        if (arrDapAn.get(i).length() == 0) {
                            arrDapAn.set(i, s);
                            break;
                        }
                    }
                }
            }
        });
    }


    private void bamData() {
        index = 0;
        arrCauTraLoi.clear();
        arrDapAn.clear();
        Random r = new Random();
        for (int i = 0; i < dapAn.length(); i++) {
            arrCauTraLoi.add("");
            String s = "" + (char) (r.nextInt(26) + 65);
            arrDapAn.add(s);
            String s1 = "" + (char) (r.nextInt(26) + 65);
            arrDapAn.add(s1);
        }
        for (int i = 0; i < dapAn.length(); i++) {
            String s = "" + dapAn.charAt(i);
            arrDapAn.set(i, s.toUpperCase());
        }
        for (int i = 0; i < arrDapAn.size(); i++) {
            String s = arrDapAn.get(i);
            int vt = r.nextInt(arrDapAn.size());
            arrDapAn.set(i, arrDapAn.get(vt));
            arrDapAn.set(vt, s);
        }

    }

    private void checkWin() {
        String s = "";
        for (String s1 : arrCauTraLoi) {
            s = s + s1;
        }
        s = s.toUpperCase();
        if (s.equals(dapAn.toUpperCase())) {
            Toast.makeText(this, "Bạn đã chiến thắng", Toast.LENGTH_SHORT).show();
            model.layTT();
            model.nguoidung.tien = model.nguoidung.tien + 10;
            model.luuTT();
            hienCauDo();
        }
    }

    public void moGoiY(View view) {
        model.layTT();
        if (model.nguoidung.tien < 5) {
            Toast.makeText(this, "bạn không đủ tiền", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = 1;
        for (int i = 0; i < arrCauTraLoi.size(); i++) {
            String s = dapAn.toUpperCase().charAt(i) + "";
            if (!arrCauTraLoi.get(i).toUpperCase().equals(s)) {
                id = i;
                break;
            }
        }
        if (id == -1) {
            for (int i = 0; i < arrCauTraLoi.size(); i++) {
                if (arrCauTraLoi.get(i).length() == 0) {
                    id = i;
                    break;
                }
            }
            for (int i = 0; i < arrDapAn.size(); i++) {
                if (arrDapAn.get(i).length() == 0) {
                    arrDapAn.set(i, arrCauTraLoi.get(id));
                    break;
                }
            }

        }
        String goiY = "" + dapAn.charAt(id);
        goiY = goiY.toUpperCase();
        for (int i = 0; i < arrCauTraLoi.size(); i++) {
            if (!arrCauTraLoi.get(i).toUpperCase().equals(goiY)) {
                arrCauTraLoi.set(i, "");
                break;
            }
        }

        for (int i = 0; i < arrDapAn.size(); i++) {
            if (goiY.equals(arrDapAn.get(i))) {
                arrDapAn.set(i, "");
                break;
            }
        }
        arrCauTraLoi.set(id, goiY);
        hienThiCauTraLoi();
        hienThiDapan();
        model.layTT();
        model.nguoidung.tien = model.nguoidung.tien - 5;
        model.luuTT();
        txvTienNguoiDung.setText(model.nguoidung.tien + "$");
    }


    public void doiCauHoi(View view) {
        model.layTT();
        if (model.nguoidung.tien < 10) {
            Toast.makeText(this, "bạn không đủ tiền", Toast.LENGTH_SHORT).show();
            return;
        }
        model.nguoidung.tien = model.nguoidung.tien - 10;
        model.luuTT();
        txvTienNguoiDung.setText(model.nguoidung.tien + "$");
        hienCauDo();
    }
}