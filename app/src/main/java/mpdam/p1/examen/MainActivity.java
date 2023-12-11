package mpdam.p1.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Note> noteList=new ArrayList<>();
    MyAdapter adapter;
    FloatingActionButton AddButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//recycle view logic
        recyclerView=findViewById(R.id.recycle);
        Gson gson = new Gson();
        SharedPreferences mPrefs = getSharedPreferences("sharedPrefs",MODE_PRIVATE);
        String json = mPrefs.getString("ListOfNotes", "");
        Note[] noteArray = gson.fromJson(json,  Note[].class);
        if (noteArray != null) {
            noteList = Arrays.asList(noteArray);
        }
        System.out.println("aaaaaaaaaaaaaa");
        System.out.println(noteList);
        System.out.println(noteList.get(0));
         adapter =new MyAdapter(this,noteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


// buttom navigation
        AddButton=findViewById(R.id.floatingActionButton2);
        Intent intentAdd = new Intent(this, AddNote.class);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentAdd);

            }
        });
    }
}