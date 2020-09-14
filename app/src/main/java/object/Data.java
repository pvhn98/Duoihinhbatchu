package object;

import java.util.ArrayList;

public class Data {
    private static Data data;

    static {
        data = new Data();
    }

    public static Data getData() {
        return data;
    }
    public ArrayList<CauDo> arrCauDo= new ArrayList<>();

}
