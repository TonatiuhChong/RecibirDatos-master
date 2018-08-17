package com.example.hombr.recibirdatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String nombre;
    private ListView prro;
    private EditText clase, valor;
    private DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Prueba");
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> rooms= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prro=(ListView)findViewById(R.id.lista);
        clase= (EditText)findViewById(R.id.editText);
        valor= (EditText)findViewById(R.id.editText2);

        findViewById(R.id.BtnConectar).setOnClickListener(this);
        findViewById(R.id.Btn2).setOnClickListener(this);

        arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,rooms);
        prro.setAdapter(arrayAdapter);

        request();


    }

    private void request() {
       /* AlertDialog.Builder builer= new AlertDialog.Builder(this);
        builer.setTitle("Introduce algo");

        final EditText entrada= new EditText(this);
        builer.setView(entrada);
        builer.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            nombre= entrada.getText().toString();

            }
        });
        builer.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                request();
            }
        });
        builer.show();*/
    }

    private void conexion() {
        Map<String,Object> map= new HashMap<String, Object>();
        map.put(clase.getText().toString(),valor.getText().toString());
        ref.updateChildren(map);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                rooms.clear();
                rooms.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.BtnConectar:
                conexion();
                break;
            case R.id.Btn2:
                finish();
                startActivity(new Intent(this, ValoresActivity.class)) ;
                break;
        }
    }


}
