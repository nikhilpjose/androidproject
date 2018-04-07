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

public class AddContacts extends AppCompatActivity {
    Button btnAdd;
    EditText editName,editNumber,editEmail;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        btnAdd = (Button) findViewById(R.id.btnSave);
        editName = (EditText) findViewById(R.id.editName);
        editNumber = (EditText) findViewById(R.id.editNumber);
        editEmail = (EditText) findViewById(R.id.editEmail);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add1();
            }
        });
        db=openOrCreateDatabase("LockItDB2", Context.MODE_PRIVATE,null);
//        if (db!=null){
//            Toast.makeText(this,"Created", Toast.LENGTH_SHORT).show();
//        }
        String query3="CREATE TABLE IF NOT EXISTS contacts5(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,number VARCHAR,email VARCHAR);";
        db.execSQL(query3);
    }
    public void add1(){
        if(editName.getText().toString().trim().length()==0||editNumber.getText().toString().trim().length()==0){
            showMessage("Error","Please Enter All Values");
            return;
        }
        db.execSQL("INSERT INTO contacts5 (name,number,email) VALUES('"+editName.getText()+"','"+editNumber.getText()+"','"+editEmail.getText()+"');");
        //showMessage("Success","Record Added");
        clearText();
        Intent inte= new Intent(AddContacts.this,Searchlist.class);
        startActivity(inte);
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText(){
        editName.setText("");
        editNumber.setText("");
        editEmail.setText("");
    }
}
