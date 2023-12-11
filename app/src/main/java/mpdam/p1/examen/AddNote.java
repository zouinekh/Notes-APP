package mpdam.p1.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddNote extends AppCompatActivity {
    EditText title,note,cat;
    Button button;
    List<Note> notes;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        title=findViewById(R.id.inputTItleNote);
        note=findViewById(R.id.noteId);
        cat=findViewById(R.id.cat);
        button=findViewById(R.id.button);
        notes=new ArrayList<>();

        Intent i= new Intent(this,MainActivity.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note n=new Note(title.getText().toString(),note.getText().toString(),cat.getText().toString());
                notes.add(n);
                Note[] nArray=getNotesFromSharedPreferences();
                if(nArray.length>0){
                    notes.addAll(Arrays.asList(nArray));
                }
                Boolean done =saveData(notes);
                if(done){
                    startActivity(i);
                }else{
                    Toast.makeText(AddNote.this, "Failed to save data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Note[] getNotesFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("ListOfNotes", "");

        return gson.fromJson(json,Note[].class);
    }
    public boolean saveData(List<Note> note){
        SharedPreferences sharedPreferences=getSharedPreferences("sharedPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(note);
        editor.putString("ListOfNotes", json);
        editor.apply();
        return true;

    }
}