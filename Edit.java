package com.example.albi.contactbook;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit extends AppCompatActivity {
    EditText editName1,editNumber1,editEmail1;
    Button btnEdit;
    TextView textId;
    SQLiteDatabase db;
    Context mc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editName1=(EditText)findViewById(R.id.editName1);
        editNumber1=(EditText)findViewById(R.id.editNumber1);
        editEmail1=(EditText)findViewById(R.id.editEmail1);
        btnEdit=(Button)findViewById(R.id.btnSave1);
        textId=(TextView)findViewById(R.id.textId);
        db=openOrCreateDatabase("LockItDB2", Context.MODE_PRIVATE,null);
        String query1="CREATE TABLE IF NOT EXISTS contacts5(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,number VARCHAR,email VARCHAR);";
        db.execSQL(query1);

        mc=this;
        Intent in3=getIntent();
        String vnm1=in3.getStringExtra("editId");
        Cursor cursor2 = db.rawQuery("SELECT * FROM contacts5 where id='"+vnm1+"'", null);
        // Toast.makeText(mc,cursor1.getCount() +"",Toast.LENGTH_LONG).show();
        cursor2.moveToFirst();

        String num=cursor2.getString(cursor2.getColumnIndex("id"));
        String name=cursor2.getString(cursor2.getColumnIndex("name"));
        String sr=cursor2.getString(cursor2.getColumnIndex("number"));
        String dn=cursor2.getString(cursor2.getColumnIndex("email"));

//        Toast.makeText(mc,"passed"+mid,Toast.LENGTH_LONG).show();

        textId.setText(num);
        editName1.setText(name);
        editNumber1.setText(sr);
        editEmail1.setText(dn);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in3=getIntent();
                String vnm1=in3.getStringExtra("editId");
                Cursor cu1 = db.rawQuery("SELECT * FROM contacts5 where id='"+vnm1+"'", null);

                if (cu1.moveToFirst())

                {

                    db.execSQL("UPDATE contacts5 SET name='" + editName1.getText() + "',number='" + editNumber1.getText() +"',email='" + editEmail1.getText() + "' WHERE id='" + textId.getText() + "'");
                    Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
                    Intent inte2=new Intent(Edit.this,Searchlist.class);
                    startActivity(inte2);
                }

            }
        });

    }
}

