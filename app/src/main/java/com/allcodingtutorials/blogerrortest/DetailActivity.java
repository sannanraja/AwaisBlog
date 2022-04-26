package com.allcodingtutorials.blogerrortest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
TextView tvblogtitle,tvblogdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        model movie = (model) getIntent().getSerializableExtra("key_blog");
        tvblogtitle=findViewById(R.id.textViewblogtitle);
        tvblogdesc=findViewById(R.id.tvblogtitledesc);
        tvblogtitle.setText(movie.getTitle());
        tvblogdesc.setText(movie.getDescription());
    }
}