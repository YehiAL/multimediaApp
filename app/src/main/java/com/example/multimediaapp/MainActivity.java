package com.example.multimediaapp;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import android.Manifest;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();

        Button reproducirAudioButton = findViewById(R.id.playAudioButton);
        reproducirAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.personaspecialistaudio);
                mediaPlayer.start();
            }
        });

        VideoView videoView = findViewById(R.id.videoView);
        Button reproducirVideoButton = findViewById(R.id.playvideoButton);
        reproducirVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/"+R.raw.personaspecialistvideo);
                videoView.setVideoURI(videoUri);
                videoView.start();
            }
        });

        Button animarButton = findViewById(R.id.playAnimButton);
        final ImageView imageView = findViewById(R.id.imageVIew);
        animarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation fadeInAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
                imageView.startAnimation(fadeInAnimation);
            }
        });




    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permiso aceptado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}