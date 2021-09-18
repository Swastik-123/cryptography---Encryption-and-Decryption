package com.example.cryptography;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Encoder extends AppCompatActivity {

    EditText ed1;
    TextView tv1;
    Button btn1,btn2;
    ClipboardManager cpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encoder);

        ed1 = (EditText)findViewById(R.id.enced);
        tv1 = (TextView)findViewById(R.id.enctv);
        btn1 = (Button)findViewById(R.id.btnenc);
        btn2 = (Button)findViewById(R.id.btncpy);
        cpi = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set the Text from EditText
                String temp = ed1.getText().toString();

                //pass the String to the encoder class for Encryption
                String rv = encode.enc(temp);

                //ser the text to the text view
                tv1.setText(rv);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get the data from text view
                String data = tv1.getText().toString().trim();

                if(!data.isEmpty())
                {
                    // copy the text in the clip board
                    ClipData temp = ClipData.newPlainText("text",data);
                    cpi.setPrimaryClip(temp);
                }
                // display message that the text has been copied
                Toast.makeText(Encoder.this, "Text Copied", Toast.LENGTH_SHORT).show();


            }
        });

    }
}
