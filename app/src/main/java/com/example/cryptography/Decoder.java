package com.example.cryptography;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Decoder extends AppCompatActivity {


    EditText edt2;
    TextView tv2;
    Button btn3,btn4;
    ClipboardManager clipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoder);

        edt2 = (EditText)findViewById(R.id.decedt);
        tv2 = (TextView)findViewById(R.id.dectv);
        btn3 = (Button)findViewById(R.id.btndec);
        btn4 = (Button)findViewById(R.id.btncpy2);
        clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get text from EditText
                String temp = edt2.getText().toString();
                Log.e("dec", "text - " + temp);

                String rv = Decoder.dec(temp);

                tv2.setText(rv);
                Log.e("dec","text - " + rv);


            }
        });



        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = tv2.getText().toString().trim();
                if(!data.isEmpty())
                {
                    ClipData temp = ClipData.newPlainText("text",data); //for copy the data from text View.
                    Toast.makeText(Decoder.this, "Text Copied", Toast.LENGTH_SHORT).show();
                }
                
            }
        });


    }

    public static String dec(String s)
    {
        String invalid = "Invalid Code";

        // create the same initial
        // string as in encode class
        String ini = "11111111";
        Boolean flag = true;

        // run a loop of size 8
        for (int i = 0; i < 8; i++) {
            // check if the initial value is same
            if (ini.charAt(i) != s.charAt(i)) {
                flag = false;
                break;
            }
        }
        String val = "";

        // reverse the encrypted code
        for (int i = 8; i < s.length(); i++) {
            char ch = s.charAt(i);
            val = val.concat(String.valueOf(ch));
        }

        // create a 2 dimensional array
        int arr[][] = new int[11101][8];
        int ind1 = -1;
        int ind2 = 0;

        // run a loop of size of the encrypted code
        for (int i = 0; i < val.length(); i++) {

            // check if the position of the
            // string if divisible by 7
            if (i % 7 == 0) {
                // start the value in other
                // column of the 2D array
                ind1++;
                ind2 = 0;
                char ch = val.charAt(i);
                arr[ind1][ind2] = ch - '0';
                ind2++;
            } else {
                // otherwise store the value
                // in the same column
                char ch = val.charAt(i);
                arr[ind1][ind2] = ch - '0';
                ind2++;
            }
        }
        // create an array
        int num[] = new int[11111];
        int nind = 0;
        int tem = 0;
        int cu = 0;

        // run a loop of size of the column
        for (int i = 0; i <= ind1; i++) {
            cu = 0;
            tem = 0;
            // convert binary to decimal and add them
            // from each column and store in the array
            for (int j = 6; j >= 0; j--) {
                int tem1 = (int) Math.pow(2, cu);
                tem += (arr[i][j] * tem1);
                cu++;
            }
            num[nind++] = tem;
        }
        String ret = "";
        char ch;
        // convert the decimal ascii number to its
        // char value and add them to form a decrypted
        // string using conception function
        for (int i = 0; i < nind; i++) {
            ch = (char) num[i];
            ret = ret.concat(String.valueOf(ch));
        }
        Log.e("dec", "text 11 - " + ret);

        // check if the encrypted code was
        // generated for this algorithm
        if (val.length() % 7 == 0 && flag == true) {
            // return the decrypted code
            return ret;
        } else {
            // otherwise return an invalid message
            return invalid;
        }
    }



}