package com.example.nandu.cool;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private ProgressBar pbar;

    private class Mytask extends AsyncTask<String,Void,String>
    {
        String s = "not working";
        TextView te = (TextView)findViewById(R.id.textView);
        int progress;
        Handler m = new Handler();

        protected  void onPreExecute()
        {
            pbar.setProgress(0);
            progress=0;
           te.setText("ready");
        }

        protected String doInBackground(String... params){
            String url = "okay so";
            s = "it worked";


            for(int i=0;i<100;i++)
            {
                progress=progress+1;
                SystemClock.sleep(1000);

                m.post(new Runnable() {
                    @Override
                    public void run() {
                        pbar.setProgress(progress,true);
                        te.setText(progress+" ");
                    }
                });
            }
            return url;
        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);

            //do something
            te.setText(result);
            te.setText(s);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner s1=(Spinner)findViewById(R.id.spinner);
        pbar = findViewById(R.id.progressBar);

        pbar.setProgress(0);

        ArrayList<String> list = new ArrayList<String>();
        list.add("new item1");
        list.add("another item");
        list.add("ok so this");

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);

        list.add("oksfp");
        list.add("jkdisfjsio");

        ImageButton b1 = findViewById(R.id.imageButton);

        registerForContextMenu(b1);
/*
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

 */
    }

    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu,v,menuInfo);

        menu.setHeaderTitle("Context menu");
        menu.add(0,v.getId(),0,"Upload");
        menu.add(0,v.getId(),0,"Search");
    }

    public boolean onContextItemSelected(MenuItem item)
    {
        if(item.getTitle()=="Search")
        {
            Toast.makeText(getApplicationContext(),"oy oy oy",Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.my_options_menu,menu);
        return true;
    }

    public void onClick(View v){
        Toast toast = Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
        toast.show();
        EditText textbox =(EditText)findViewById(R.id.textbox);
        textbox.setText("hello??");

        TextView t1= (TextView)findViewById(R.id.textView);

    }

    public void onClick2(View v) {
        Toast toast = Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT);
        toast.show();
        EditText textbox = (EditText) findViewById(R.id.textbox);
        textbox.setText("ok sorry");
        new Mytask().execute();

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.about)
            Toast.makeText(getApplicationContext(),"hiiiii",Toast.LENGTH_SHORT).show();

        if(item.getItemId()==R.id.help)
            Toast.makeText(getApplicationContext(),"HELP!!!!",Toast.LENGTH_LONG).show();

        return true;
    }

}
