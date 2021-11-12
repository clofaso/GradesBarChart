package com.example.takehomeexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.totalEdit)   EditText total;
    @BindView(R.id.grpAEdit)    EditText grpA;
    @BindView(R.id.grpBEdit)    EditText grpB;
    @BindView(R.id.grpCEdit)    EditText grpC;
    @BindView(R.id.grpDEdit)    EditText grpD;
    @BindView(R.id.grpFEdit)    EditText grpF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void calculate(View view) {
        //If input not provided for all fields, display error message
        if(total.getText().toString().equals("") ||
        grpA.getText().toString().equals("") ||
        grpB.getText().toString().equals("") ||
        grpC.getText().toString().equals("") ||
        grpD.getText().toString().equals("") ||
        grpF.getText().toString().equals("")){
            Toast msg = Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG);
            msg.setGravity(Gravity.CENTER, 0, 700);
            msg.show();
        }
        else{
            //Getting EditText Inputs
            Editable editableTotal = total.getText(),editableGrpA = grpA.getText(),
                    editableGrpB = grpB.getText(), editableGrpC = grpC.getText(),
                    editableGrpD = grpD.getText(), editableGrpF = grpF.getText();

            Double totalStd, valA, valB, valC, valD, valF,
            resA, resB, resC, resD, resF = 0.0;

            //Parsing strings and storing as Doubles
            totalStd = Double.parseDouble(editableTotal.toString());
            valA = Double.parseDouble(editableGrpA.toString());
            valB = Double.parseDouble(editableGrpB.toString());
            valC = Double.parseDouble(editableGrpC.toString());
            valD = Double.parseDouble(editableGrpD.toString());
            valF = Double.parseDouble(editableGrpF.toString());

            //Calculating percentages of each
            resA = ((valA/totalStd)*100);
            resB = ((valB/totalStd)*100);
            resC = ((valC/totalStd)*100);
            resD = ((valD/totalStd)*100);
            resF = ((valF/totalStd)*100);

            //Create a dialog box with calculations
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            //Add message on alert
            alert.setMessage("Grade Distribution Percentages:\n" +
                    "A's = " + resA + "%\n" +
                    "B's = " + resB + "%\n" +
                    "C's = " + resC + "%\n" +
                    "D's = " + resD + "%\n" +
                    "F's = " + resF + "%");
            alert.create().show();

            //Using Intent to push data over to display in a bar graph
            Intent intent = new Intent(MainActivity.this, BarGraph.class);
            intent.putExtra("A", resA);
            intent.putExtra("B", resB);
            intent.putExtra("C", resC);
            intent.putExtra("D", resD);
            intent.putExtra("F", resF);
            startActivity(intent);
        }
    }
}