package com.example.lab08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    EditText txtctl;
     public int  nid ;
    String ntxt;

    ArrayAdapter<mynote> adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtctl=findViewById(R.id.txt_content);
        Intent i =getIntent();
        nid =i.getIntExtra( "note-id", 0);
        ntxt= i.getStringExtra("note-txt");
        txtctl.setText(ntxt);

    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return  true;


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        if( id == R.id.itm_save)
        {

            EditText txt= findViewById(R.id.txt_content);
            String TXT=txt.getText().toString();
            g.notes.alterNote(this.nid,TXT);
            finish();
            Toast.makeText(this, "Заметка сохранена", Toast.LENGTH_SHORT).show();



            //получить текст из text box , изменить заметку, показать сообщение "заметка сохранена" и выйти
        }
        if (id == R.id.itm_delete)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Вы ходитет удалить данную запись?");
            builder.setMessage("Вы уверены?");

            builder.setPositiveButton("Да", (DialogInterface.OnClickListener) (dialog, which) -> {
                g.notes.deleteNote(this.nid);
                Toast.makeText(this, "Запись удалена", Toast.LENGTH_SHORT).show();
                finish();
            });
            builder.setNegativeButton("Нет", null );
            builder.show();


        }


        return super.onOptionsItemSelected(item);

    }
    ArrayList<mynote> lst = new ArrayList<>();

}