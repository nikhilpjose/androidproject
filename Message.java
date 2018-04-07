package com.example.albi.contactbook;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Message extends AppCompatActivity {
    Button btnMsg;
    EditText editMsg;
    String mes;
    String vnm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent in=getIntent();
        vnm=in.getStringExtra("name");
        editMsg=(EditText)findViewById(R.id.messageBox);

        btnMsg=(Button) findViewById(R.id.btnMessage);

        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mes=editMsg.getText().toString();
                Intent intent3=new Intent(getApplicationContext(),Searchlist. class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0,intent3,0);
                SmsManager sms=SmsManager.getDefault();
                // sms.sendTextMessage(vnm, null,mes,pi,null);
                Toast.makeText(getApplicationContext(),"message send successfully",Toast.LENGTH_SHORT).show();
                clearText();

            }
        });

    }
    public void clearText(){
        editMsg.setText("");
    }
}
