package com.example.ssp;

public class ProductModel {
    String date;
    String time;
    String stweight;
    float prweight;
    String sttime;
    String prtime;
    String tem;
    float inac;
    int mdcnt;
    int bdmd;
    String mlnam;
    String hetcde;

    public ProductModel(String date, String time, String stweight, float prweight, String sttime, String prtime, String tem, float inac, int mdcnt, int bdmd, String mlnam, String hetcde) {
        this.date = date;
        this.time = time;
        this.stweight = stweight;
        this.prweight = prweight;
        this.sttime = sttime;
        this.prtime = prtime;
        this.tem = tem;
        this.inac = inac;
        this.mdcnt = mdcnt;
        this.bdmd = bdmd;
        this.mlnam = mlnam;
        this.hetcde = hetcde;
    }



    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStweight() {
        return stweight;
    }

    public float getPrweight() {
        return prweight;
    }

    public String getSttime() {
        return sttime;
    }

    public String getPrtime() {
        return prtime;
    }

    public String getTem() {
        return tem;
    }

    public float getInac() {
        return inac;
    }

    public int getMdcnt() {
        return mdcnt;
    }

    public int getBdmd() {
        return bdmd;
    }

    public String getMlnam() {
        return mlnam;
    }

    public String getHetcde() {
        return hetcde;
    }
}
