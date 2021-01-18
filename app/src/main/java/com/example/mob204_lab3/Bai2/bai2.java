package com.example.mob204_lab3.Bai2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Browser;
import android.provider.MediaStore;
import android.service.media.MediaBrowserService;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mob204_lab3.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class bai2 extends AppCompatActivity {
    Button button;
    ListView listView;
    int REQUEST_READ_EXTERNAL_STORAGE = 456;
    ArrayList<String> filePath = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        button = findViewById(R.id.button_bai2);
  //      listView = findViewById(R.id.listview_bai2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestPermission();

                loadImage();

                setImage();
            }
        });
    }

    private void setImage() {
        File imgFile = new  File(filePath.get(0) + "");

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = (ImageView) findViewById(R.id.imageView);

            myImage.setImageBitmap(myBitmap);

        }
    }


    private void loadImage() {
        String []anh = {
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATE_ADDED,
                MediaStore.MediaColumns.MIME_TYPE
        };

        CursorLoader cursorLoader = new CursorLoader(
                this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);

        Cursor cursor = cursorLoader.loadInBackground();
        cursor.moveToFirst();
        String s = "";
        while (!cursor.isAfterLast()){
            s = cursor.getString(1);
//            for (int i = 0;i<cursor.getColumnCount();i++){
//                s+= cursor.getString(i) + " - " ;
//            }
            filePath.add(s);
            cursor.moveToNext();
        }

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        cursor.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

        }else {
            ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE
            },REQUEST_READ_EXTERNAL_STORAGE);
        }
    }
}