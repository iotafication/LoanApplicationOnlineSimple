package com.fakharssolutions.simpleloanapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Variable Declaration
    int alreadyToasted = 0; //Toasty MK Reference, Jokes asides, This is used to handle Toast Functions
    double monthlyInterest = 0; // Same as PMT FUNCTION in Excel but we will implement in onclick
    double monthlyPayment = 0 ; // MontlyPayment = ( Amount / term ) + MonthlyInterest
    double totalInterest  = 0 ; // MontlyInterst * Term
    double totalPayment = 0 ; // MonthlyPayment * Term


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //EditText FindViewByID
        EditText rateInput = findViewById(R.id.rateInput);
        EditText termMonthInput = findViewById(R.id.termMonthInput);
        EditText termYearInput = findViewById(R.id.termYearInput);
        EditText amountInput = findViewById(R.id.amountInput);


        //  Button FindViewById
        Button btnOutput = findViewById(R.id.btnOutput);


        // TextView Find ViewByID
        TextView txtOutputTotalAmount = findViewById(R.id.txtOutputTotalAmount);
        TextView txtOutputTotalInterest = findViewById(R.id.txtOutputTotalInterest);
        TextView txtOutputMontlyPayment = findViewById(R.id.txtOutputMontlyPayment);
        TextView txtOutputMontlyInterest = findViewById(R.id.txtOutputMontlyInterest);

        /*
        * Main Logic is mentioned below
        * When the button is clicked it will calculate Interest
        * I'm going to check all the editText wheather they are empty
        * After that I'm going to perform formula and for that I have to change into Double data type
        * Not Checking the negative because the front end is easily handling it.
        * */

        // The main logic starts here
        btnOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // Checking if the editText empty or not, if empty

                    if(termYearInput.getText().toString().length() == 0
                        && termMonthInput.getText().toString().length() ==0
                        && amountInput.getText().toString().length() ==0
                        && rateInput.getText().toString().length() ==0
                    ){
                        IncompleteInput();
                    }

                    if(alreadyToasted == 0) {
                        // converting into Double
                        double parseAmount = Double.parseDouble(amountInput.getText().toString());
                        double parseRate = Double.parseDouble(rateInput.getText().toString());
                        double parseTermYearly = Double.parseDouble(termYearInput.getText().toString());
                        double parseTermMonthly = Double.parseDouble(termMonthInput.getText().toString());

                        parseTermMonthly = (parseTermYearly * 12) + parseTermMonthly;

                        // Here's comes the MontlyInterestRate
                        monthlyInterest = (parseAmount * parseRate / 12) / Math.pow((1 - (1 + parseRate / 12)), (-(parseTermMonthly / 12) * 12));
                        monthlyPayment = (parseAmount / parseTermMonthly) + monthlyInterest;
                        totalInterest = monthlyInterest * parseTermMonthly;
                        totalPayment = monthlyPayment * parseTermMonthly;

                        //Setting all the Variables to txtOutput
                        txtOutputMontlyInterest.setText(String.valueOf(monthlyInterest));
                        txtOutputMontlyPayment.setText(String.valueOf(monthlyPayment));
                        txtOutputTotalInterest.setText(String.valueOf(totalInterest));
                        txtOutputTotalAmount.setText(String.valueOf(totalPayment));


                    }
                }

        });
    }

    public void IncompleteInput(){
        Context context = getApplicationContext();
        alreadyToasted++;
        CharSequence text = "Incomplete Input";
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}