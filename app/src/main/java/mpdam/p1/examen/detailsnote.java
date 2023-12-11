package mpdam.p1.examen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class detailsnote extends AppCompatActivity {
    TextView title;
    TextView note,cat;
    String titleFromIntent,noteFromIntent,catFromIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsnote);
        Intent intent = getIntent();
        titleFromIntent = intent.getStringExtra("Title");
        noteFromIntent = intent.getStringExtra("note");
        catFromIntent = intent.getStringExtra("cat");

        title=findViewById(R.id.Title);
        note=findViewById(R.id.note);
        cat=findViewById(R.id.category);
        title.setText(titleFromIntent);
        note.setText(noteFromIntent);
        cat.setText(catFromIntent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return  true;
    }
    public void sendMail(String subject,String content){
        Intent intent =new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,content);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"chose email client : "));
    }
    public void addToCalendar(String title, String description) {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.DESCRIPTION, description);

            startActivity(intent);

    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            Intent intent = getIntent();
            titleFromIntent = intent.getStringExtra("Title");
            noteFromIntent = intent.getStringExtra("note");
            sendMail(titleFromIntent, noteFromIntent);}
        else if (item.getItemId() == R.id.item2){
            addToCalendar(titleFromIntent, noteFromIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}