package com.example.albi.contactbook;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    Button b1;
    EditText ePass,cPass;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        b1=(Button)findViewById(R.id.btnReg);
        ePass=(EditText)findViewById(R.id.enterPass);
        cPass=(EditText)findViewById(R.id.confirmPass);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add();
            }
        });
        db=openOrCreateDatabase("LockItDB2", Context.MODE_PRIVATE,null);
//        if (db!=null){
//            Toast.makeText(this,"Created", Toast.LENGTH_SHORT).show();
//        }
        String query="CREATE TABLE IF NOT EXISTS pass(ePass VARCHAR,cPass VARCHAR);";
        db.execSQL(query);
    }

    public void add(){
        if(ePass.getText().toString().trim().length()==0||cPass.getText().toString().trim().length()==0){
            Toast.makeText(this,"Please Enter The Values", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!ePass.getText().toString().equals(cPass.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            db.execSQL("INSERT INTO pass VALUES('" + ePass.getText() + "','" + cPass.getText() + "');");
            showMessage("Success", "Record Added");
            Intent abc=new Intent(Registration.this,Login.class);
            startActivity(abc);
            clearText();
        }
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText(){
        ePass.setText("");
        cPass.setText("");
    }
}

