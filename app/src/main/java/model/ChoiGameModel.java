package model;

import com.example.duoihinhbatchu.PlayActivity;

import java.util.ArrayList;

import object.CauDo;
import object.Data;
import object.Nguoidung;

public class ChoiGameModel {
    PlayActivity c;
    ArrayList<CauDo> arr;
    int cauSo = -1;
    public Nguoidung nguoidung;


    public ChoiGameModel(PlayActivity c) {
        this.c = c;
        nguoidung = new Nguoidung();
        taoData();
    }

    private void taoData() {
        arr = new ArrayList<>(Data.getData().arrCauDo);

    }

    public CauDo layCauDo() {
        cauSo++;
        if (cauSo >= arr.size()) {
            cauSo = arr.size() - 1;
        }
        return arr.get(cauSo);
    }

    public void layTT() {
        nguoidung.getTT(c);
    }

    public void luuTT() {
        nguoidung.saveTT(c);
    }
}
