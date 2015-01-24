package com.pabloc6.calculator6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainCalculatorActivity extends ActionBarActivity implements View.OnClickListener {

    Button buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive;
    Button buttonSix, buttonSeven, buttonEight, buttonNine, buttonPoint, buttonPlus;
    Button buttonMinus, buttonMul, buttonDiv, buttonEqual, buttonInvSign, buttonDel;
    Button buttonClear;
    TextView tvCalcView;
    EditText etCalcView;
    private static final String DIGITS = "0123456789.";
    private static final String OPERATORS = "+-*/";
    private static final String EQUAL = "=";
    private static final String POINT = ".";
    private static final String OTHERS = "!CD";
    private static final String UNKNOWN = "UNKNOWN";

    private static float operand1, operand2, result;
    private static String operator, history;
    private static boolean resultGiven;

    MyApplication app;
    private static final String TAG = "DEBUG->MainActivity";


/*
    private static final int STATE_OPERAND1 = 0b00_0001;
    private static final int STATE_OPERATOR = 0b00_0010;
    private static final int STATE_OPERAND2 = 0b00_0100;
    private static final int STATE_POINT    = 0b00_1000;
    private static final int STATE_EQUAL    = 0b01_0000;
    private static final int STATE_OTHERS   = 0b10_0000;
    private static int currentState = STATE_OPERAND1;
    private static int nextState    = STATE_OPERAND1;
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calculator);

        final String TAG_LOCAL = TAG + ".onCreate";
        Log.d(TAG_LOCAL, "IN");

        app = (MyApplication)getApplication();

//        buttonZero = (Button) findViewById(R.id.btn_zero);
        buttonOne  = (Button) findViewById(R.id.btn_one);
        buttonTwo = (Button) findViewById(R.id.btn_two);
        buttonThree = (Button) findViewById(R.id.btn_three);
        buttonFour= (Button) findViewById(R.id.btn_four);
        buttonFive  = (Button) findViewById(R.id.btn_five);
        buttonSix = (Button) findViewById(R.id.btn_six);
        buttonSeven = (Button) findViewById(R.id.btn_seven);
        buttonEight = (Button) findViewById(R.id.btn_eight);
        buttonNine = (Button) findViewById(R.id.btn_nine);

        buttonPlus = (Button) findViewById(R.id.btn_plus);
        buttonMinus = (Button) findViewById(R.id.btn_minus);
        buttonMul = (Button) findViewById(R.id.btn_mul);
        buttonDiv = (Button) findViewById(R.id.btn_div);
        buttonEqual = (Button) findViewById(R.id.btn_equal);
        buttonInvSign = (Button) findViewById(R.id.btn_invSign);
        buttonPoint = (Button) findViewById(R.id.btn_point);
        buttonClear = (Button) findViewById(R.id.btn_clear);
        buttonDel = (Button) findViewById(R.id.btn_del);

        tvCalcView = (TextView) findViewById(R.id.tv_calcTextView);
        etCalcView = (EditText) findViewById(R.id.et_calcEditText);

//        buttonZero = (Button) findViewById(R.id.btn_zero);
//        buttonZero.setOnClickListener(this);
        findViewById(R.id.btn_zero).setOnClickListener(this);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
        buttonSeven.setOnClickListener(this);
        buttonEight.setOnClickListener(this);
        buttonNine.setOnClickListener(this);

        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonInvSign.setOnClickListener(this);
        buttonPoint.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonDel.setOnClickListener(this);

        // TODO
        // 1. TAG - set tags from java
        // 2. FSM - implement fsm
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        final String TAG_LOCAL = TAG + ".onOptionsItemSelected";
        Log.d(TAG_LOCAL, "IN");

        // For handling M+, MC, M
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.i_showHistory) {
            Intent intentHistory = new Intent(getApplicationContext(), HistoryCalculatorActivity.class);
            startActivityForResult(intentHistory, 3); // id = 3 , History. TODO: Make parameter
            return true;
        }
        if (id == R.id.i_memorySave) {
            // Save the value
            String saveValue = etCalcView.getText().toString();
            if (!saveValue.isEmpty()) {
                editor.putString("memSave", saveValue);
                editor.commit();
                Log.d(TAG_LOCAL, "memorySave: " + saveValue);
            } else {
                Toast.makeText(this, "No value to save in Memory", Toast.LENGTH_SHORT).show();
                Log.d(TAG_LOCAL, "memorySave NO VALUE TO SAVE");
            }
            return true;
        }
        if (id == R.id.i_memoryRecall) {
            String recallValue = settings.getString("memSave", null);
            if (recallValue != null) {
                etCalcView.setText(recallValue);
                Log.d(TAG_LOCAL, "memoryRecall: " + recallValue);
            } else {
                Toast.makeText(this, "Memory is empty", Toast.LENGTH_SHORT).show();
                Log.d(TAG_LOCAL, "memoryRecall EMPTY ");
            }
            return true;
        }
        if (id == R.id.i_memoryClear) {
            editor.remove("memSave");
            editor.commit();
            Toast.makeText(this, "Memory is cleared", Toast.LENGTH_SHORT).show();
            Log.d(TAG_LOCAL, "memoryClear");
            return true;
        }
        if (id == R.id.i_about) {
            Intent intentAbout = new Intent(getApplicationContext(), AboutCalculatorActivity.class);
            startActivityForResult(intentAbout, 6); // id = 6 , About. TODO: Make parameter
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public float resultComputation(float op1, float op2, String oprt) {

        final String TAG_LOCAL = TAG + ".resultComputation";
        Log.d(TAG_LOCAL, "IN");

        float result = 0;

        switch (oprt) {
            case "+":
                Log.d(TAG, "It's a plus");
                result = op1 + op2;
                break;
            case "-":
                Log.d(TAG, "It's a minus");
                result = op1 - op2;
                break;
            case "*":
                Log.d(TAG, "It's a multiply");
                result = op1 * op2;
                break;
            case "/":
                Log.d(TAG, "It's a divide");
                result = op1/op2;
                break;
            default:
                Log.d(TAG, "Unrecognized operator");
                break;
        }

        return result;

    }

    @Override
    public void onClick(View v) {

        final String TAG_LOCAL = TAG + ".onClick";
        Log.d(TAG_LOCAL, "IN");

        // Extract what has been pressed
        // Cast view to Button and get Text field
        Button b = (Button) v;
        String buttonPressed = b.getText().toString();
        String buttonPressedType;


        // Can use v.getId() == R.id.btn_xxx
        if (DIGITS.contains(buttonPressed)) {
            buttonPressedType = DIGITS;
        } else if (OPERATORS.contains(buttonPressed)) {
            buttonPressedType = OPERATORS;
        } else if (POINT.contains(buttonPressed)) { // v.getId() == R.id.btn_point
            buttonPressedType = POINT;
        } else if (EQUAL.contains(buttonPressed)) { // v.getId() == R.id.btn_equal
            buttonPressedType = EQUAL;
        } else if (OTHERS.contains(buttonPressed)) {
            buttonPressedType = OTHERS;
        } else {
            buttonPressedType = UNKNOWN;
        }

        Log.d(TAG, "buttonPressed: " + buttonPressed + " buttonPressedType; " + buttonPressedType);

        if (v.getId() == R.id.btn_clear) {
            etCalcView.setText("");
            tvCalcView.setText("");
            operand1 = 0;
            operand2 = 0;
            operator = "";
            result = 0;
            resultGiven = false;
        } else if (v.getId() == R.id.btn_del) {
            if (etCalcView.length() > 0) {
                etCalcView.setText(etCalcView.getText().delete(etCalcView.length() - 1, etCalcView.length()));
            }
        } else if (v.getId() == R.id.btn_invSign) {
            etCalcView.setText("-" + etCalcView.getText());
            Log.d(TAG, "Negate recognized");
        } else {

            switch (buttonPressedType) {
                case DIGITS:
                    if (resultGiven) {
                        etCalcView.setText("");
                        operand1 = 0;
                        operand2 = 0;
                        operator = "";
                        result = 0;
                        resultGiven = false;
                    }
                    etCalcView.append(buttonPressed);
                    break;
                case POINT:
                    etCalcView.append(buttonPressed);
                    break;
                case OPERATORS:
                    operand1 = Float.parseFloat(etCalcView.getText().toString());
                    operator = buttonPressed;
                    tvCalcView.setText(buttonPressed);
                    etCalcView.setText("");
                    break;
                case EQUAL:
                    operand2 = Float.parseFloat(etCalcView.getText().toString());
                    etCalcView.setText("");
                    tvCalcView.setText("");
                    result = resultComputation(operand1, operand2, operator);
                    etCalcView.setText(String.valueOf( result));
//                    history = Float.toString(operand1) + operator + Float.toString(operand2) + "=" + Float.toString(result);
                    history = operand1 + operator + operand2 + "=" + result;
                    app.sqlUtils.insertData(history);
                    resultGiven = true;
                    Log.d(TAG, "result: " + result + " history: " + history);
                    break;
                default:
                    break;


            }
        }

        Log.d(TAG, "operand1: " + operand1 + " operand2: " + operand2 + " operator: " + operator);



        /*// TODO
        // 1. floating point - ADD. While in any OPERAND state allow only one POINT

        if (v.getId() == R.id.btn_clear)
        {
            etCalcView.setText("");
            currentState = STATE_OPERAND1;
            nextState = STATE_OPERAND1;
            pointSet = false;
        } else {
            switch (currentState) {
                case STATE_OPERAND1: // 1
                    if ((buttonPressedType == DIGITS) || (buttonPressedType == POINT)) {
                        nextState = STATE_OPERAND1;
                        tvCalcView.append(buttonPressed);
                        etCalcView.append(buttonPressed);

                        if (buttonPressedType == POINT) {
                            pointSet = true;
                        }
                    } else if (buttonPressedType == OPERATORS) {
                        nextState = STATE_OPERATOR;
                        tvCalcView.append(buttonPressed);
                        etCalcView.append(buttonPressed);
                    } else {
                        nextState = STATE_OPERAND1;
                    }
                    break;
                case STATE_OPERATOR: // 2
                    pointSet = false;
                    if (buttonPressedType == DIGITS) {
                        nextState = STATE_OPERAND2;
                        tvCalcView.append(buttonPressed);
                        etCalcView.append(buttonPressed);
                    } else if (buttonPressedType == OPERATORS) {
                        nextState = STATE_OPERATOR;
                        etCalcView.setText(etCalcView.getText().delete(etCalcView.length() - 1, etCalcView.length()));
                        etCalcView.append(buttonPressed);
                    } else {
                        nextState = STATE_OPERATOR;
                    }
                    break;
                case STATE_OPERAND2: // 4
                    if ((buttonPressedType == DIGITS) || (buttonPressedType == POINT)) {
                        nextState = STATE_OPERAND2;
                        tvCalcView.append(buttonPressed);
                        etCalcView.append(buttonPressed);
                        if (buttonPressedType == POINT) {
                            pointSet = true;
                        }
                    } else if (buttonPressedType == EQUAL) {
                        nextState = STATE_EQUAL;

                    }
                    break;
//                case POINT: // 8
//                    if (buttonPressedType == DIGITS) {
//                        nextState =
//                    }
                case STATE_EQUAL: // 16
                    // Calculate operation
                    nextState = STATE_OPERAND1;
                    break;
                default:
                        nextState = currentState;
                    etCalcView.setText("");
                    break;
            }

        }

//        etCalcView.setText("-" + etCalcView.getText());
//            iii = Integer.parseInt(etCalcView.getText().toString());


        // Update the currentState for next click
        currentState = nextState;


        Log.d(TAG, "currentState: " + currentState + " nextState; " + nextState + " pointSet: " + pointSet);

        CharSequence textInView = tvCalcView.getText();
        CharSequence textInEdit = etCalcView.getText();

        Log.d(TAG, "onClick textInView: " + textInView + " textInEdit: " + textInEdit);
*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        // Handle the result according to the parameters
        final String TAG_LOCAL = TAG + ".onActivityResult";
        Log.d(TAG_LOCAL, "IN");

        if (6 == resultCode) {
            Log.d(TAG_LOCAL, "Returning from About");
        } else if (3 == resultCode) {
            Log.d(TAG_LOCAL, "Returning from History");
        }

    }


}
