package com.example.patcharaponjoksamut.intentlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textView2 = (TextView) findViewById(R.id.textView2);

        TestParcelable testParcelable = getIntent().getParcelableExtra("object");
        String output = testParcelable.getName() + " " + testParcelable.getNickname() + " " + testParcelable.getAge();

        textView2.setText(output);
    }

    public void backBtnPressed(View view) {
        this.finish();
    }
}
