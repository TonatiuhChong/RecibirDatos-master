package com.example.hombr.recibirdatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ValoresActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText Child,Value;
    private TextView Resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valores);

        Child= (EditText) findViewById(R.id.Variable);
        Value= (EditText) findViewById(R.id.Valor);
        Resultado=(TextView)findViewById(R.id.Mirror);

        findViewById(R.id.BtnActualizar).setOnClickListener(this);
        findViewById(R.id.Btnvolver).setOnClickListener(this);


    }

    private void actualizar() {
        String hijo=Child.getText().toString();
        String valor=Value.getText().toString();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Prueba");
        Map<String,Object> map= new HashMap<String, Object>();
        map.put(hijo,valor);
        ref.updateChildren(map);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Resultado.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BtnActualizar:
                actualizar();
                break;
            case R.id.Btnvolver:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;

        }

    }


}
