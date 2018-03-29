package com.coba.ginanjar_1202150068_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by ginanjarwidya on 25/03/18.
 */

public class Setting extends AppCompatActivity {

    //deklarasi variable yang akan digunakan
    TextView shapeclr;
    int colorid;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Settings");

        //membuat alert dialog baru bernama alert
        alert = new AlertDialog.Builder(this);

        //menginisialisasi shared preference
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        spe = sharedP.edit();
        colorid = sharedP.getInt("Colourground", R.color.white);
    }

    //apabila tombol back di tekan
    @Override
    public void onBackPressed() {
        //intent baru dari pengaturan menuju list to do
        Intent intent = new Intent(Setting.this, MainActivity.class);
        //memulai intent
        startActivity(intent);
        //menutup aktivity setelah intent di jalanlan
        finish();
    }

    //method yang dijalankan ketika pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            //menjalankan method onbackpressed
            this.onBackPressed();
        }
        return true;
    }

//    //mendapatkan string warna yang akan digunakan untuk mengubah shape color
//    public String getShapeColor(int i){
//        if (i==R.color.red){
//            return "Red";
//        }else if (i==R.color.green){
//            return "Green";
//        }else if (i==R.color.blue){
//            return "Blue";
//        }else{
//            return "Default";
//        }
//    }

    //mendapatkan id dari warna yang akan digunakan
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.yellow){
            return R.id.yellow;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else{
            return R.id.white;
        }
    }

    public void choosecolor(View view) {
        //set title dari alert dialog menjadi Shape Color
        alert.setTitle("Card Color");
        //membuat view baru
        View view1 = getLayoutInflater().inflate(R.layout.color, null);
        //menampilkan view yang telah dibuat
        alert.setView(view1);

        //mengakses radio group pada layout
        final RadioGroup radG = view1.findViewById(R.id.radiocolor);
        radG.check(getColorid(colorid));

        //ketika menekan Ok pada alert dialog
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan id radio button yang di pilih
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.red:
                        colorid = R.color.red;
                        break;
                    case R.id.yellow:
                        colorid = R.color.yellow;
                        break;
                    case R.id.blue:
                        colorid = R.color.blue;
                        break;
                    case R.id.white:
                        colorid = R.color.white;
                        break;
                }
                //menaruh shared preference
                spe.putInt("Colourground", colorid);
                //commit shared preference
                spe.commit();
            }
        });

        //ketika menekan Cancel pada alert dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //membuat dan menampilkan alert dialog
        alert.create().show();
    }
}
