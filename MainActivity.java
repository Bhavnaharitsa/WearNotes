package com.example.wearapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.speech.RecognizerIntent;
import android.support.constraint.solver.widgets.Helper;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.*;

public class MainActivity extends WearableActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    displaySpeech();
                }else{
                    Note note=(Note) parent.getItemAtPosition(position);
                    Intent intent= new Intent(getApplicationContext(),delete_activity.class);
                    intent.putExtra("id",note.getClass());
                    startActivity(intent);
                }
            }
        });

        upDateUI();


    }
    @Override
    public void onResume(){
        super.onResume();
        upDateUI();
    }
    public void upDateUI(){
        ArrayList<Note> notes= Helper.getAllNotes(this);
        notes.add(0,new Note("",""));
        listView.setAdapter(new ListViewAdapter(this,0,notes));
    }
    public void displaySpeech(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent,1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1001 && resultCode==RESULT_OK){
            List<String> results=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String message = results.get(0);
            Note note=new Note(null,message);
            Helper.saveNote(note,this);
            Helper.displayConfirmation("Note saved !",this);
            upDateUI();
        }
    }
}
