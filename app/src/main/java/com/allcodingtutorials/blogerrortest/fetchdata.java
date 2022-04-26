package com.allcodingtutorials.blogerrortest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class fetchdata extends AppCompatActivity implements myadapter.OnNoteListener {
    RecyclerView recyclerView;
    ArrayList<model> dataholder=new ArrayList<>();
    private Button buttonadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);
        buttonadd=findViewById(R.id.buttonadd);
        recyclerView=findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor= new DBHelper(this).getdata();
        while (cursor.moveToNext())
        {
            model obj= new model(cursor.getString(1),cursor.getString(2),cursor.getString(3));
            dataholder.add(obj);
        }
        myadapter myadapter= new myadapter(dataholder,this);
        recyclerView.setAdapter(myadapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NewBlog.class);
                startActivity(intent);
            }
        });

//        buttondetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (myadapter.getSelected() != null){
//                    ShowToast(myadapter.getSelected().getName());
//                }
//                else
//                    ShowToast("No Selection");
//            }
//        });
    }

    private void ShowToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoteClick(int position) {
        model movie = dataholder.get(position);
        Intent intent= new Intent(this,DetailActivity.class);
        intent.putExtra("key_blog", (Serializable) movie);
        startActivity(intent);
    }
}