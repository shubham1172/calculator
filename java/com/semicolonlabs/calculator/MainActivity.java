package com.semicolonlabs.calculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    /**
     * This is a calculator app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadButtons();
    }
    /**
     * Handles onClick event for button 'About'
     */
    public void clickAbout(View view){
        Intent AboutIntent = new Intent(this, DisplayAboutActivity.class);
        startActivity(AboutIntent);
    }
    /**
     * loads buttons into grid area - buttonArea
     */
    public void loadButtons(){
        GridLayout buttonGrid = (GridLayout)findViewById(R.id.buttonArea);
        final String[] buttons = new String[] {
                "C","<-","/","*",
                "7","8","9","-",
                "4","5","6","+",
                "1","2","3"," ",
                "%","0",".","="
        };
        EditText result = (EditText)findViewById(R.id.result);
        Button equalTo = (Button)findViewById(R.id.equalTo);
        Button button[] = new Button[buttons.length];
        if(buttonGrid!=null){
            buttonGrid.removeAllViews();
            buttonGrid.setColumnCount(4);
            buttonGrid.setRowCount(5);
        }
        for(int i=0;i<buttons.length;i++){
            if(buttons[i].equals("="))
                button[i]=equalTo;
            else
                button[i] = new Button(this);
            button[i].setText(buttons[i]);
            button[i].setBackgroundColor(Color.WHITE);
            button[i].setOnClickListener(new Handler(result, equalTo));
            if(buttons[i].equals("=")||buttons[i].equals(" "))
                button[i].setBackgroundColor(Color.GREEN);
            if(buttonGrid!=null)
                buttonGrid.addView(button[i]);

        }
    }
}
