package com.testsensor.pc;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StillActivity extends FragmentActivity implements OnMapReadyCallback {

    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;
    private ImageView c_img;
    private GoogleMap map;
    LocationManager locationManager;
    LocationListener locationListener;
    Marker marker;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private Global PreActivity;

    private AssetManager assetManager;
    MediaPlayer m;
    String content="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("停下来","停了");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.still_activity);

        c_img = (ImageView) findViewById(R.id.c_img);


        Bitmap bitmap = Loadpic("Still");
        c_img.setImageBitmap(bitmap);

        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();

        addDB();

    }


    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        processExtraData();
         }

    private void processExtraData(){
        addDB();
    }

    public void addDB(){
        Intent intent2 = getIntent();
        String thiss=intent2.getStringExtra("PreAct");
        Log.d("PreAct",thiss);
        ContentValues cv = new ContentValues();
        if(!thiss.equals("Still")) {
            cv.put(NotesDB.STIME, getTime());
        }
        cv.put(NotesDB.TIME, getTime());
        cv.put(NotesDB.CURRENTAC,"Still");
        dbWriter.insert(NotesDB.TABLE_NAME,null,cv);

    }


    public Bitmap Loadpic(String Activity){
        Bitmap bitmap = null;
        AssetManager assetManager = this.getAssets();
        try {
            InputStream inputStream = assetManager.open(Activity+".jpg");
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }


    private String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String str = format.format(date);
        return  str;
    }

}
