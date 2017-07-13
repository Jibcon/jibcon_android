package com.sm_arts.jibcon.app.cheatkey;

/**
 * Created by woojinkim on 2017. 7. 13..
 */

public class PassiveCheatkeyData {

    // input 텍스트뷰와 output 텍스텍스트뷰트뷰 DataSet
    public int imgBtnID;
    public String inputTv;
    public String outputTv;

    public PassiveCheatkeyData(int imgBtnID, String inputTv, String outputTv) {
        this.imgBtnID = imgBtnID;
        this.inputTv = inputTv;
        this.outputTv = outputTv;
    }
}