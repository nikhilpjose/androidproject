package com.example.albi.contactbook;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    TextView e1,e2,e3,e4;
    Button call1;
    SQLiteDatabase db;
    Context mc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        e1=(TextView)findViewById(R.id.edit1);
        e2=(TextView)findViewById(R.id.edit2);
        e3=(TextView)findViewById(R.id.edit3);
        e4=(TextView)findViewById(R.id.edit4);
        call1=(Button)findViewById(R.id.buttonCall);
        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phno=e3.getText().toString();
                Intent in2=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phno));
                startActivity(in2);
            }
        });
        mc=this;
        Intent in=getIntent();
        String vnm=in.getStringExtra("name");
        db=openOrCreateDatabase("LockItDB2", Context.MODE_PRIVATE,null);
        String query1="CREATE TABLE IF NOT EXISTS contacts5(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,number VARCHAR,email VARCHAR);";
        db.execSQL(query1);
        Cursor cursor1 = db.rawQuery("SELECT * FROM contacts5 where name='"+vnm+"'", null);
        // Toast.makeText(mc,cursor1.getCount() +"",Toast.LENGTH_LONG).show();
        cursor1.moveToFirst();

        String num=cursor1.getString(cursor1.getColumnIndex("id"));
        String name=cursor1.getString(cursor1.getColumnIndex("name"));
        String sr=cursor1.getString(cursor1.getColumnIndex("number"));
        String dn=cursor1.getString(cursor1.getColumnIndex("email"));

//        Toast.makeText(mc,"passed"+mid,Toast.LENGTH_LONG).show();

        e1.setText(num);
        e2.setText(name);
        e3.setText(sr);
        e4.setText(dn);
    }
}
