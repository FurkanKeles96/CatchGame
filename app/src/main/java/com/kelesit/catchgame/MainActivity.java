package com.kelesit.catchgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView  imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9;
    TextView txtSkor;
    TextView txtZaman;
    ImageView resimDizisi[];
    int skor;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView1);
        imageView3 = findViewById(R.id.imageView2);
        imageView4 = findViewById(R.id.imageView3);
        imageView5 = findViewById(R.id.imageView4);
        imageView6 = findViewById(R.id.imageView5);
        imageView7 = findViewById(R.id.imageView6);
        imageView8 = findViewById(R.id.imageView7);
        imageView9 = findViewById(R.id.imageView8);

        resimDizisi = new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView5,imageView6,imageView7,imageView8, imageView9};

        txtSkor = findViewById(R.id.textSkor);
        txtZaman = findViewById(R.id.textZaman);

        skor = 0;
        resimGizle();

        new CountDownTimer(22000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                txtZaman.setText("ZAMAN: " + (-1+millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                for(ImageView image:resimDizisi){
                    image.setVisibility(View.INVISIBLE);
                }

                txtZaman.setText("SÜRE DOLDU");
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Süre Doldu");
                alert.setMessage("Yeniden başlamak ister misiniz?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Oyun Bitti", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();

            }
        }.start();

    }

    public void skorArttir(View view){

        skor++;
        txtSkor.setText("SKOR: "+skor);
    }

    public void resimGizle(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image:resimDizisi){
                    image.setVisibility(View.INVISIBLE);
                }

                Random rnd = new Random();
                int i = rnd.nextInt(9);
                resimDizisi[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);

    }
}
