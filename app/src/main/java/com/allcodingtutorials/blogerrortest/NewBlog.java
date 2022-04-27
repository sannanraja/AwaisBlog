package com.allcodingtutorials.blogerrortest;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewBlog extends AppCompatActivity {
    private Button buttonsubmit, buttonaddpic;
    private EditText blogtitleedt, blogdescedx;
    private TextView nblogone;

    private ImageView imgageuseradd;
    int SELECT_IMAGE_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_blog);
        buttonsubmit =findViewById(R.id.buttonsubmit);
        imgageuseradd =findViewById(R.id.imageuserad);
        buttonaddpic =findViewById(R.id.buttonaddpic);

        buttonaddpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(Intent.createChooser(intent,"Title"),SELECT_IMAGE_CODE);
            }
        });
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                blogtitleedt =findViewById(R.id.editTexttitle);
                blogdescedx =findViewById(R.id.editTextdesc);
                nblogone = findViewById(R.id.nblog2);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                Date date = new Date();

                String frmtdDate = dateFormat.format(date);

                processinsert(blogtitleedt.getEditableText().toString(), blogdescedx.getEditableText().toString(),frmtdDate);
                startActivity(new Intent(getApplicationContext(),fetchdata.class));
            }
        });

    }

    private void processinsert(String title, String desc, String datercv) {
        String res= new DBHelper(this).insertuserdata(title,datercv,desc);
        blogtitleedt.setText("");
        blogdescedx.setText("");
        nblogone.setText("");
        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            Uri uri= data.getData();

            String test=uri.toString();


            imgageuseradd.setImageURI(uri);
            buttonaddpic.setText(test);

        }
    }


    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}