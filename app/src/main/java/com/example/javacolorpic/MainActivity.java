package com.example.javacolorpic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnFocusChangeListener {

    private SeekBar sk_red,sk_green,sk_blue;
    private ImageView img;
    private EditText et_red,et_green,et_blue;
    private TextView tv_hex;
    private TextWatcher redWatcher,blueWatcher,greenWatcher;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        sk_red.setOnSeekBarChangeListener(this);
        sk_blue.setOnSeekBarChangeListener(this);
        sk_green.setOnSeekBarChangeListener(this);

        et_red.setOnFocusChangeListener(this);
        et_blue.setOnFocusChangeListener(this);
        et_green.setOnFocusChangeListener(this);

        et_red.addTextChangedListener(redWatcher);
        et_blue.addTextChangedListener(blueWatcher);
        et_green.addTextChangedListener(greenWatcher);
    }

    private void init(){
        sk_red = findViewById(R.id.red_bar);
        sk_green = findViewById(R.id.green_bar);
        sk_blue = findViewById(R.id.blue_bar);
        img = findViewById(R.id.img);
        et_red = findViewById(R.id.red);
        et_green = findViewById(R.id.green);
        et_blue = findViewById(R.id.blue);
        tv_hex = findViewById(R.id.hex);

        redWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if(s.equals("")){
                        sk_red.setProgress(0);
                    }else{
                        sk_red.setProgress(Integer.parseInt(s.toString()));
                        et_red.setSelection(s.length());
                    }
                }catch (Exception e){
                    // Toast.makeText(MainActivity.this,"请输入正确的数字",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        blueWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if(s.equals("")){
                        sk_blue.setProgress(0);
                    }else{
                        sk_blue.setProgress(Integer.parseInt(s.toString()));
                        et_blue.setSelection(s.length());
                    }
                }catch (Exception e){
                    // Toast.makeText(MainActivity.this,"请输入正确的数字",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        greenWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if(s.equals("")){
                        sk_green.setProgress(0);
                    }else{
                        sk_green.setProgress(Integer.parseInt(s.toString()));
                        et_green.setSelection(s.length());
                    }
                }catch (Exception e){
                    // Toast.makeText(MainActivity.this,"请输入正确的数字",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    private void changeColor(){
        img.setColorFilter(Color.rgb(sk_red.getProgress(),sk_green.getProgress(),sk_blue.getProgress()));
        String hex = "#" +
                (
                        (0xFF & sk_red.getProgress()) < 16
                        ? "0"+(Integer.toHexString(0xFF & sk_red.getProgress()))
                        :(Integer.toHexString(0xFF & sk_red.getProgress()))
                ) +
                (
                        (0xFF & sk_green.getProgress()) < 16
                        ?"0"+(Integer.toHexString(0xFF & sk_green.getProgress()))
                        :(Integer.toHexString(0xFF & sk_green.getProgress()))
                ) +
                (
                        (0xFF & sk_blue.getProgress()) < 16
                        ?"0"+(Integer.toHexString(0xFF & sk_blue.getProgress()))
                        :(Integer.toHexString(0xFF & sk_blue.getProgress()))
                );
        tv_hex.setText(hex.toUpperCase());
        et_red.setText(sk_red.getProgress()+"");
        et_green.setText(sk_green.getProgress()+"");
        et_blue.setText(sk_blue.getProgress()+"");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        changeColor();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() ==  MotionEvent.ACTION_DOWN){
            View v = getCurrentFocus();
            if(!(v instanceof EditText)){
                hideKeyboard();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private void hideKeyboard(){
        et_red.clearFocus();
        et_blue.clearFocus();
        et_green.clearFocus();
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(),0);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v instanceof EditText && hasFocus){
            EditText et = (EditText) v;
            et.setText("");
        }
        else if(v instanceof EditText && !hasFocus && ((EditText) v).getText().toString().equals("")){
            switch (v.getId()){
                case R.id.red:
                    sk_red.setProgress(0);
                    ((EditText) v).setText("0");
                    break;
                case R.id.blue:
                    ((EditText) v).setText("0");
                    sk_blue.setProgress(0);
                    break;
                case R.id.green:
                    ((EditText) v).setText("0");
                    sk_green.setProgress(0);
                    break;
            }
        }
    }
}