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
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
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
    List<Bitmap> list2 = new ArrayList<Bitmap>();
    ImageOnListviewAdapter adapter;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        button = findViewById(R.id.button_bai2);
        listView = findViewById(R.id.listview_image);
        adapter = new ImageOnListviewAdapter(this,R.layout.item_image,list2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // xin quyen truy cap storage
                requestPermission();

                // lay duong dan cua tat ca cac anh
                getImageFilePath();

                // chuyen duong dan sang bitmap
                setImage();

                // vi khi click button no se setAdapter lien tuc
                if (count == 0){
                    listView.setAdapter(adapter);   // them dieu kien de chi setAdapter 1 lan tranh trung du lieu
                    count++;
                }else {

                }
            }
        });
    }

    private void setImage() {
        for (int i = 0; i < filePath.size(); i++){
            File imgFile = new  File(filePath.get(i) + "");
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            list2.add(myBitmap);
        }



//        File imgFile = new  File(filePath.get(0) + "");
//
//        if(imgFile.exists()){
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//            ImageView myImage = (ImageView) findViewById(R.id.imageView);
//            myImage.setImageBitmap(myBitmap);
//        }
    }


    private void getImageFilePath() {
        // tao con tro doc duong dan trong bo nho ngoai
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
            for (int i = 0;i<cursor.getColumnCount();i++){
                s = cursor.getString(1);
            }
            filePath.add(s);
            cursor.moveToNext();
        }

        String a = "";
        for (int i = 0;i<filePath.size();i++){
            a+= filePath.get(i);
            a+= "\n---------\n";
        }
        Toast.makeText(this, a, Toast.LENGTH_SHORT).show();

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