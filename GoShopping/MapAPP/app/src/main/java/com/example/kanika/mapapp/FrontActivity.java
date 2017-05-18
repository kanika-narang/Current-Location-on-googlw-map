package com.example.kanika.mapapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class FrontActivity extends AppCompatActivity {
Button bt;
    Spinner spinner;
    Spinner spinner2;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);


         spinner = (Spinner) findViewById(R.id.Locations);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Locations, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);




         spinner2 = (Spinner) findViewById(R.id.Category);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Categories, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        rg=(RadioGroup) findViewById(R.id.radioSex);
        bt=(Button) findViewById(R.id.btnDisplay);
        bt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sendMsg(v);
            }
        });

    }

    public void sendMsg(View v)
    {
        Intent intent=new Intent(this,MapsActivity.class);
        String loctext=spinner.getSelectedItem().toString();
        String cattext=spinner2.getSelectedItem().toString();
        int radiobuttonId=rg.getCheckedRadioButtonId();
        View radiobutton=rg.findViewById(radiobuttonId);
        int idx=rg.indexOfChild(radiobutton);
        RadioButton rb=(RadioButton) findViewById(radiobuttonId);
        String sextext=rb.getText().toString();
        intent.putExtra("loctext",loctext);
        intent.putExtra("cattext",cattext);
        intent.putExtra("sex",sextext);
        startActivity(intent);



    }
}
