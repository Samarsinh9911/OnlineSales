package com.example.onlinesales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    public EditText editText;
    private DBHandler dbHandler;
    private RecyclerView historyRecyclerView;
    private HistoryAdapter historyAdapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= ( EditText ) findViewById( R.id.equationEditText );

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        historyRecyclerView = findViewById(R.id.historyRecyclerView);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        List<HistoryItem> historyItems = new ArrayList<>(); // Get your data here

        historyAdapter = new HistoryAdapter(historyItems);
        historyRecyclerView.setAdapter(historyAdapter);
    }

    //We will add the public float applyOp( char operatorX, float a, float b) as shown below
    public  float applyOp( char operatorX,  float a,  float b)
    {
        switch ( operatorX)
        {
            case '+':
                return b+a;
            case '-':
                return b-a;
            case '*':
                return b*a;
            case '/':
                if ( a != 0)
                    return b/a;
        }
        return 0;
    }

    //Next we create a function public boolean checkP( char operatorA, char operatorB) as shown below
    public  boolean checkP( char operatorA,  char operatorB)
    {
        if ( operatorB == '(' || operatorB == ')') {
            return false;
        }
        if (  ( operatorA == '+' || operatorA == '-') && ( operatorB == '*' || operatorB == '/') ) {
            return false;
        }
        else {
            return true;
        }
    }

    //Now we write the best part of the program, which is the brain of our program public float letsCalculate( String mS) as shown below
    public  float letsCalculate( String mS)
    {
        char[] myChar = mS.toCharArray( );

        Stack<Float> myNumStack = new
                Stack<Float>( );

        Stack<Character> operatorStack = new
                Stack<Character>( );


        for ( int i = 0; i < myChar.length; i++)
        {

            if ( myChar[i] >= '0' &&
                    myChar[i] <= '9')
            {
                StringBuffer stringBuffer = new
                        StringBuffer( );


                while ( i < myChar.length && myChar[i] >= '0' && myChar[i] <= '9') {
                    stringBuffer.append( myChar[i++]);
                }
                myNumStack.push( Float.parseFloat( stringBuffer.toString( )));


                i--;
            }


            else if ( myChar[i] == '(')
                operatorStack.push( myChar[i]);


            else if ( myChar[i] == ')')
            {
                while ( operatorStack.peek( ) != '(') {
                    myNumStack.push( applyOp( operatorStack.pop( ),  myNumStack.pop( ),  myNumStack.pop( )));
                }
                operatorStack.pop( );
            }


            else if ( myChar[i] == '/' || myChar[i] == '*' || myChar[i] == '+' || myChar[i] == '-')
            {

                while ( !operatorStack.empty( ) && checkP( myChar[i],  operatorStack.peek( ))) {
                    myNumStack.push( applyOp( operatorStack.pop( ),
                            myNumStack.pop( ),
                            myNumStack.pop( )));
                }


                operatorStack.push( myChar[i]);
            }

        }


        while ( !operatorStack.empty( )) {
            myNumStack.push( applyOp( operatorStack.pop( ),  myNumStack.pop( ),  myNumStack.pop( )));
        }


        return myNumStack.pop( );
    }

    public  void sol( View x)
    {

        try {
            ( ( TextView)findViewById( R.id.textViewResult)).setTextColor( Color.GREEN);

            ( ( TextView) findViewById( R.id.textViewResult)).setText( "Answer \n" + letsCalculate( editText.getText( ).toString( )));


            // below line is to get data from all edit text fields.
            String expression = editText.getText().toString();
            String result = ((TextView) findViewById(R.id.textViewResult)).getText().toString();

            // validating if the text fields are empty or not.
            if (expression.isEmpty() && result.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                return;
            }

            // on below line we are calling a method to add new
            // course to sqlite data and pass all our values to it.
            dbHandler.addNewExpressions(expression, result);

            // after adding the data we are displaying a toast message.
            Toast.makeText(MainActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
            editText.setText("");
        }
        catch ( Exception e)
        {
            ( ( TextView)findViewById( R.id.textViewResult)).setTextColor( Color.RED);
            ( ( TextView)findViewById( R.id.textViewResult)).setText( "Error");

        }

    }
}