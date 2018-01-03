package com.mert.secretofcolors;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import static com.mert.secretofcolors.SplashActivity.getRenkdiziint;


public class CameraActivity2 extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private CameraBridgeViewBase cameraManager;
    private Mat mRgba;
    Button b1,b2;
    private MediaPlayer mediaPlayer;
    private boolean durum = false;
    int count = 0;
    int ilk=0;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case LoaderCallbackInterface.SUCCESS:
                {
                    cameraManager.enableView();
                }
                break;
                default:{
                    super.onManagerConnected(status);
                }break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        b2= (Button) findViewById(R.id.button2);
        b1= (Button) findViewById(R.id.button);
        b2.setEnabled(true);
        b1.setEnabled(false);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2.setEnabled(true);
                b1.setEnabled(false);
                durum = false;

                mediaPlayer.stop();
            }
        });

        cameraManager = (CameraBridgeViewBase) findViewById(R.id.camera);
        cameraManager.setVisibility(SurfaceView.VISIBLE);
        //cameraManager.setAlpha(0); //gizlemek için
        cameraManager.setCvCameraViewListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraManager != null)
            cameraManager.disableView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cameraManager != null)
            cameraManager.disableView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mLoaderCallback);
        } else {
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC3);
        System.out.println("WH: "+width+"-"+height);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat mCamera = inputFrame.rgba();
        Imgproc.cvtColor(mCamera, mRgba, Imgproc.COLOR_RGBA2RGB);
        int rows = (int) (mRgba.rows()*0.1);
        int cols = (int) (mRgba.cols()*0.05);
        int centerrow=(int) (mRgba.rows()/2);
        int centercol=(int) (mRgba.cols()/2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                durum=true;
                b2.setEnabled(false);
                b1.setEnabled(true);
            }
        });
        if (durum){
            int size = rows*cols;
            final int[] ortalama = new int[3];
            double[] toplam = new double[3];
            for (int j = (centerrow-(rows/2)); j < centerrow+(rows/2); j++) {
                for (int i = (centercol-(cols/2)); i < centercol+(cols/2);i++) {
                    double [] rgb = mRgba.get(j, i);
                    for(int k=0;k<3;k++){
                        toplam[k] = (int) (toplam[k] + rgb[k]);
                    }
                }
            }

            for(int k=0;k<3;k++){
                ortalama[k] = (int) (toplam[k]/size);
            }
            int rgbint = 65536 * ortalama[0] + 256 * ortalama[1] + ortalama[2];
            Imgproc.rectangle(mCamera, new Point(centercol - (cols / 2), centerrow - (rows / 2)), new Point(centercol + (cols / 2), centerrow + (rows / 2)), new Scalar(ortalama[0], ortalama[1], ortalama[2]), 3);
            int[] myColorList = getRenkdiziint();
            FrameCounter(nearnum(rgbint, myColorList),ortalama);
        }

        return mCamera;
    }

    public int nearnum(int myNumber,int[] numbers)
    {
        int distance = Math.abs(numbers[0] - myNumber);
        int idx = 0;
        for(int c = 1; c < numbers.length; c++){
            int cdistance = Math.abs(numbers[c] - myNumber);
            if(cdistance < distance){
                idx = c;
                distance = cdistance;
            }
        }
        //numbers[idx] index değeri
        return idx; //bulunduğu index
    }

    public void FrameCounter(int colorListIndex, int[] ortalama){
        count++;

        if (count==17 && ilk==0){
            ilk++;
            count++;
            playProcess(colorListIndex);
        }
        else if(count%90==0){ //90 da iken 3,8 snde bir dönüyor 120 iken 4,5 sn bir dönüyor
            playProcess(colorListIndex);
            System.out.println("RGB : "+ortalama[0] +" "+ ortalama[1] +" "+ ortalama[2]);
            System.out.println("HangiIndex : "+colorListIndex);
            count=0;
        }

    }

    public void playProcess(int a){
        System.out.println("hangisarki: "+a);
        try {
            mediaPlayer = MediaPlayer.create(this, Uri.parse("android.resource://"+getPackageName()+"/raw/klasik"+a)); //sarki 1 kirmizi
            mediaPlayer.start();
        }
        catch (NullPointerException e) {
            // Set a breakpoint there to inspect the state of your app
            // Then rethrow the exception to have it logged, and why not
            // log extra info.
        }
    }
}
