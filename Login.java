package com.example.albi.contactbook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    SQLiteDatabase db;
    Button btn1;
    EditText enterPass;
    TextView tx1;
    int counter = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn1 = (Button) findViewById(R.id.btnLogin);
        tx1 = (TextView) findViewById(R.id.textView4);
        enterPass = (EditText) findViewById(R.id.loginPass);
        db = openOrCreateDatabase("LockItDB2", Context.MODE_PRIVATE, null);
        String query="CREATE TABLE IF NOT EXISTS pass(ePass VARCHAR,cPass VARCHAR);";
        db.execSQL(query);
        Cursor cur=db.rawQuery("SELECT * FROM pass", null);
        if(cur.getCount()==0) {
            Intent i = new Intent(Login.this,Registration.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("SELECT * FROM pass", null);
                c.moveToFirst();
                String str1=c.getString(1);
                if (enterPass.getText().toString().equals(str1.toString())) {
                    //if (enterPass.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Success", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Login.this,Searchlist.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));
                    if (counter == 0) {
                        btn1.setEnabled(false);
                    }
                }

            }
        });
    }
}
