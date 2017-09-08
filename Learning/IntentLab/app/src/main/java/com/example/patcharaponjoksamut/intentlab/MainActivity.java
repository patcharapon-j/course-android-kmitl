package com.example.patcharaponjoksamut.intentlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        TestParcelable testParcelable = new TestParcelable();
        testParcelable.setName("Test");
        testParcelable.setAge(21);
        testParcelable.setNickname("Feem");
        intent.putExtra("object", testParcelable);
        startActivity(intent);
    }
}
