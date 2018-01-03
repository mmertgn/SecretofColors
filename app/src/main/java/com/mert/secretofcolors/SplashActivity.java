package com.mert.secretofcolors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class SplashActivity extends AppCompatActivity {

    static int [] renkdiziint = new int[25];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
        renkdiziint = setRenkdiziint();


    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    public static int[] getRenkdiziint() {
        return renkdiziint;
    }

    public static int[] setRenkdiziint() {
        int[] mylist = new int[]{
                16711680, //kirmizi-0
                0, //siyah-1
                8421504, //gri-2
                255, //mavi-3
                139, //lacivert-4
                2003199, //acikmavi-5
                49151,//dahaacikmavi-6
                4251856,// turkuaz-7
                16777215, //beyaz-8
                3050327, //kkyesil-9
                65280, //acikyesil-10
                32768, //yesil-11
                25600, //koyuyesil-12
                16767673, //ten rengi-13
                8421376, //zeytinyesili-14
                16753920, //turuncu-15
                8388736, //mor-16
                10824234, //kahverengi-17
                9127187, //acikkahve-18
                16776960, //sari-19
                14423100, //acik kirmizi-20
                12624836, //acikmor-21
                16761035, //pembe-22
                8388608, //bordo-23
                4915330,//koyumor-24
        };
        return mylist;
    }

}
