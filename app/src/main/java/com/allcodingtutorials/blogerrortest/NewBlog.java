package com.allcodingtutorials.blogerrortest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class NewBlog extends AppCompatActivity {
    private Button button,button2;
    private EditText editText,editText2;
    private TextView textView;
    private FloatingActionButton fab;

    private ImageView imageView;
    int SELECT_IMAGE_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_blog);
        button=findViewById(R.id.buttonone);
        imageView=findViewById(R.id.nimg);
        button2=findViewById(R.id.buttontwo);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(Intent.createChooser(intent,"Title"),SELECT_IMAGE_CODE);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText=findViewById(R.id.editTextone);
                editText2=findViewById(R.id.editTexttwo);
                textView= findViewById(R.id.nblog2);

                processinsert(editText.getEditableText().toString(),editText2.getEditableText().toString(),textView.getText().toString());
                startActivity(new Intent(getApplicationContext(),fetchdata.class));
            }
        });

    }

    private void processinsert(String title, String desc, String imgbyte ) {
        String res= new DBHelper(this).insertuserdata(title,desc,imgbyte);
        editText.setText("");
        editText2.setText("");
        textView.setText("");
        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            Uri uri= data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                byte[] inputData = getBytes(inputStream);

            } catch (Exception e) {
                e.printStackTrace();
            }
            String test=uri.toString();


            imageView.setImageURI(uri);
            button2.setText(test);

        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
//    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
//        return outputStream.toByteArray();
//    }
}