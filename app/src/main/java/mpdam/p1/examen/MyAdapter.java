package mpdam.p1.examen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NoteViewHolder> {
    public List<Note> items;
    public Context context;
    MyAdapter(Context context,List<Note> notes){
        this.context=context;
        this.items=notes;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_note,parent,false);
        return new NoteViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note n =items.get(position);
        if(n != null){
        holder.title.setText(n.getTitle());
        holder.categorie.setText(n.getCat());
        holder.note.setText(n.getNote());
        Intent i =new Intent(context, detailsnote.class);
        i.putExtra("Title",n.getTitle());
        i.putExtra("note",n.getNote());
        i.putExtra("cat",n.getCat());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(i);
            }
        });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        public TextView title,categorie,note;
        public  NoteViewHolder(@NonNull View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.itemTitle);
            categorie=itemView.findViewById(R.id.ItemCat);
            note=itemView.findViewById(R.id.noteItem);
        }

    }
}
