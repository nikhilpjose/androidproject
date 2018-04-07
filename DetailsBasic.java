package com.example.albi.contactbook;

        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.telephony.SmsManager;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

public class DetailsBasic extends AppCompatActivity {
    TextView e1,e2,e3,e4;
    Button call1;
    SQLiteDatabase db;
    Context mc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_basic);
        e1=(TextView)findViewById(R.id.edit1);
        e2=(TextView)findViewById(R.id.edit2);
        e3=(TextView)findViewById(R.id.edit3);
        e4=(TextView)findViewById(R.id.edit4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button fab = (Button) findViewById(R.id.buttonCall);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phno=e3.getText().toString();
                Intent in2=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phno));
                //in2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in2);

            }
        });
        Button fab1 = (Button) findViewById(R.id.buttonMsg);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent in1=new Intent(Intent.ACTION_SEND);
//                in1.setType("text/plain");
//                in1.putExtra(Intent.EXTRA_TEXT,"Hai... Hellow.. How are you??");
//                startActivity(in1);


                String num1=e3.getText().toString();
                Intent int3=new Intent(DetailsBasic.this,Message.class);
                int3.putExtra("name", num1);
                // Toast.makeText(getApplicationContext(),(String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                startActivity(int3);
//                Toast.makeText(mc,num1, Toast.LENGTH_SHORT).show();
//                   String mes="Important Message";
//                    Intent intent3=new Intent(getApplicationContext(),LiatAll1. class);
//                    PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0,intent3,0);
//                    SmsManager sms=SmsManager.getDefault();
//                    sms.sendTextMessage(num1, null,mes,pi,null);
//                Toast.makeText(getApplicationContext(),"message send successfully",Toast.LENGTH_SHORT).show();


            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.buttonDel);
        db=openOrCreateDatabase("LockItDB2", Context.MODE_PRIVATE,null);
        String query1="CREATE TABLE IF NOT EXISTS contacts5(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,number VARCHAR,email VARCHAR);";
        db.execSQL(query1);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c2 = db.rawQuery("SELECT * FROM contacts5 where id='" + e1.getText() + "'", null);
                if (c2.moveToFirst()) {
                    db.execSQL("DELETE FROM contacts5 WHERE id='" + e1.getText() + "'");
                    Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                    Intent myintent= new Intent(DetailsBasic.this,Login.class);
                    myintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(myintent);
                } else {
                    Toast.makeText(getApplicationContext(),"Not Deleted",Toast.LENGTH_SHORT).show();
                }
                clearText();
            }
        });

        FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.buttonEdit);
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editId=e1.getText().toString();
                Intent inte5=new Intent(DetailsBasic.this,Edit.class);
                inte5.putExtra("editId", editId);
                //Toast.makeText(getApplicationContext(),e1.getText().toString(),Toast.LENGTH_SHORT).show();
                startActivity(inte5);

            }
        });





        mc=this;
        Intent in=getIntent();
        String vnm=in.getStringExtra("name");
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
    public  void clearText(){
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e1.requestFocus();

    }

}

