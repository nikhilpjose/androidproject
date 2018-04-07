package com.example.albi.contactbook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class Searchlist extends AppCompatActivity {
    MaterialSearchView searchView;
    ListView lstView1;
    SQLiteDatabase db;
    Context mContext;
    String nm;
    ArrayAdapter<String> ada;
    ArrayList<String> list=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlist);

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent in1=new Intent(Intent.ACTION_SEND);
//                in1.setType("text/plain");
//                in1.putExtra(Intent.EXTRA_TEXT,"Hai... Hellow.. How are you??");
//                startActivity(in1);
                startActivity(new Intent(Searchlist.this,AddContacts.class));


            }
        });
        setTitle("Search");
        mContext = this;
        lstView1=(ListView)findViewById(R.id.lstView1);
        db = openOrCreateDatabase("LockItDB2", Context.MODE_PRIVATE, null);
        String query = "CREATE TABLE IF NOT EXISTS contacts5(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,number VARCHAR,email VARCHAR);";
        db.execSQL(query);
        Cursor cur = db.rawQuery("SELECT DISTINCT(name) FROM contacts5 ORDER BY name ASC", null);
        if (cur.getCount() != 0) {
            cur.moveToFirst();
            int c = cur.getCount();
            for (int i = 0; i < c; i++) {
                nm = cur.getString((cur.getColumnIndex("name")));
                list.add(nm);
                cur.moveToNext();
            }
            ada = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, list);
            lstView1.setAdapter(ada);
            lstView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent in=new Intent(Searchlist.this,DetailsBasic.class);
                    in.putExtra("name", (String) parent.getItemAtPosition(position));
                    // Toast.makeText(getApplicationContext(),(String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                    startActivity(in);
                }
            });

        }
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        searchView =(MaterialSearchView)findViewById(R.id.search_view);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
//                lstView1=(ListView)findViewById(R.id.lstView1);
//                db = openOrCreateDatabase("LockItDB2", Context.MODE_PRIVATE, null);
//                String query = "CREATE TABLE IF NOT EXISTS contacts5(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,number VARCHAR,email VARCHAR);";
//                db.execSQL(query);
//                Cursor cur = db.rawQuery("SELECT DISTINCT(name) FROM contacts5 ORDER BY name ASC", null);
//                if (cur.getCount() != 0) {
//                    cur.moveToFirst();
//                    int c = cur.getCount();
//                    for (int i = 0; i < c; i++) {
//                        nm = cur.getString((cur.getColumnIndex("name")));
//                        list.add(nm);
//                        cur.moveToNext();
//                    }
                ada = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, list);
                lstView1.setAdapter(ada);
//                    lstView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Intent in=new Intent(Searchlist.this,DetailsBasic.class);
//                            in.putExtra("name", (String) parent.getItemAtPosition(position));
//                            // Toast.makeText(getApplicationContext(),(String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
//                            startActivity(in);
//                        }
//                    });
//
//                }
            }
        });


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText !=null && !newText.isEmpty()){
                    List<String> lstFound = new ArrayList<String>();
                    for (String item:list) {
                        if(item.contains(newText))
                            lstFound.add(item);
                    }
                    ada = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, lstFound);
                    lstView1.setAdapter(ada);
                }
                else{
                    ada = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, list);
                    lstView1.setAdapter(ada);
                }
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
}

