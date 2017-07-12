/*
 * Created by Armando Jimenez on 7/11/17 4:18 PM
 * Copyright (c) 2017. All rights reserved.
 *
 * Last modified 7/11/17 4:17 PM
 *
 *
 */

package com.example.arman.mensajessecretos;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //set up variables for our GUI
    EditText txtIn;
    EditText txtOut;
    EditText txtKey;
    SeekBar sb;
    Button button;
    TextView lblDecode;

    public String encode(String message, int k) {

        String out = "";
        char key = (char) k;

        for (int x = 0; x < message.length(); x++) {

            char in = message.charAt(x);

            if (in >= 'A' && in <= 'Z') {

                in += key;
                if (in > 'Z')
                    in -= 26;
                if (in < 'A')
                    in += 26;

            }

            if (in >= 'a' && in <= 'z') {

                in += key;
                if (in > 'z')
                    in -= 26;
                if (in < 'a')
                    in += 26;

            }

            if (in >= '0' && in <= '9') {
                in += (k % 10);

                if (in > '9')
                    in -= 10;
                if (in < '0')
                    in += 10;
            }

            out += in;

        }

        return out;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txtIn = (EditText) findViewById(R.id.txtIn);
        txtOut = (EditText) findViewById(R.id.txtOut);
        txtKey = (EditText) findViewById(R.id.txtKey);
        sb = (SeekBar) findViewById(R.id.seekBar);
        button = (Button) findViewById(R.id.btnEncode);
        lblDecode = (TextView) findViewById(R.id.lblDecode);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int key = Integer.parseInt(txtKey.getText().toString());
                int progress = key + 13;
                int keyDecode = key - (key * 2);

                lblDecode.setText("Llave para Decodificar: " + keyDecode + ".");

                String message = txtIn.getText().toString();
                String out = encode(message, key);

                txtOut.setText(out);
                sb.setProgress(progress);


            }
        });


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int key = i - 13;

                String message = txtIn.getText().toString();
                String out = encode(message, key);

                txtOut.setText(out);

                txtKey.setText("" + key);

                int keyDecode = key - (key * 2);

                lblDecode.setText("Llave para Decodificar: " + keyDecode + ".");


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
