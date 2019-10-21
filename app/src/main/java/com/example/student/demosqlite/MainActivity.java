package com.example.student.demosqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBClass database;
    ListView listView;
    ArrayList<CongViec> congViecs;
    CongViecAdapter congViecAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listviewCongViec);
        congViecs = new ArrayList<>();
        congViecAdapter = new CongViecAdapter(this, R.layout.dong_cong_viec,congViecs);
        listView.setAdapter(congViecAdapter);

        //Tao Database
        database = new DBClass(this,"ghichu.sqlite",null,1 );
        //Tao Table
        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(200))");
        //insert DATA
        //database.QueryData("INSERT INTO CongViec VALUES(null, 'DO YOUR FREAKING HOMEWORK DUDE!')");
        //database.QueryData("INSERT INTO CongViec VALUES(null, 'WASHING YOUR MOTORCYCLE DUDE!')");
        //database.QueryData("INSERT INTO CongViec VALUES(null, 'CODE YOUR PROJECT!')");

        //CURSOR
        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");
        while (dataCongViec.moveToNext()){
            String ten = dataCongViec.getString(1);
           // Toast.makeText(this, ten, Toast.LENGTH_SHORT).show();
            int id = dataCongViec.getInt(0);
            congViecs.add(new CongViec(id,ten));
        }
        congViecAdapter.notifyDataSetChanged();
    }
}
