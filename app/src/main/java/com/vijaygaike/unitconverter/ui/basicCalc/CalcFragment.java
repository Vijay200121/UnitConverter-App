package com.vijaygaike.unitconverter.ui.basicCalc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.vijaygaike.unitconverter.MainActivity;
import com.vijaygaike.unitconverter.R;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vijaygaike.unitconverter.databinding.FragmentCalcBinding;
import com.vijaygaike.unitconverter.DBHandler;
//import com.vijaygaike.unitconverter.DBHandler;

public class CalcFragment extends Fragment {

    Button ac, cut, add, sub, multiply, div, power, equal, decimal, zero, one, two, three, four, five, six, seven, eight, nine, change_sign;
    TextView input, output;
    String text;
    double result;
    boolean sum, minus, division,mult,pow;
    String s;
    double temp;
    int i=0;



    private FragmentCalcBinding binding;
    private DBHandler dbHandler;


    public static double eval(String str) {
        str = str.replaceAll("x","*");
        String finalStr = str;
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < finalStr.length()) ? finalStr.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < finalStr.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(finalStr.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = finalStr.substring(startPos, this.pos);
                    x = parseFactor();
                    switch (func) {
                        case "sqrt":
                            x = Math.sqrt(x);
                            break;
                        case "sin":
                            x = Math.sin(Math.toRadians(x));
                            break;
                        case "cos":
                            x = Math.cos(Math.toRadians(x));
                            break;
                        case "tan":
                            x = Math.tan(Math.toRadians(x));
                            break;
                        default:
                            throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalcBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //code here

        ac = root.findViewById(R.id.ac);
        cut = root.findViewById(R.id.cut);
        add = root.findViewById(R.id.add);
        sub = root.findViewById(R.id.sub);
        multiply = root.findViewById(R.id.multiply);
        div = root.findViewById(R.id.div);
        power = root.findViewById(R.id.power);
        equal = root.findViewById(R.id.equal);
        decimal = root.findViewById(R.id.decimal);
        zero = root.findViewById(R.id.zero);
        one = root.findViewById(R.id.one);
        two = root.findViewById(R.id.two);
        three = root.findViewById(R.id.three);
        four = root.findViewById(R.id.four);
        five = root.findViewById(R.id.five);
        six = root.findViewById(R.id.six);
        seven = root.findViewById(R.id.seven);
        eight = root.findViewById(R.id.eight);
        nine = root.findViewById(R.id.nine);
        change_sign = root.findViewById(R.id.change_sign);
        input = root.findViewById(R.id.userInput);
        output = root.findViewById(R.id.result);

        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) input.getLayoutParams();
        ViewGroup.LayoutParams params1 = (ViewGroup.LayoutParams) output.getLayoutParams();
        dbHandler = new DBHandler(getContext());

        //equal button
        equal.setOnClickListener(v -> {
            if (!sum && !minus && !mult && !division && !pow) {
                input.setTextSize(20);
                params.height = 20;
                params1.height = 140;
                input.setLayoutParams(params);
                output.setLayoutParams(params1);
                output.setTextSize(47);
                s = input.getText().toString();
                s = s.replaceAll("x","*");
                result = eval(s);
                output.setText(String.valueOf(result));
                String history_expression = input.getText().toString() + " = " + output.getText().toString();
                boolean i = dbHandler.addNewCourse(history_expression);
                if (i) Log.d("VIJAY", "add_expression: history added");
                else Log.d("VIJAY", "add_expression: error in adding history");
            } else {
                Toast.makeText(getContext(), "Math Error...", Toast.LENGTH_SHORT).show();
            }
        });

        //entering numbers and decimal
        one.setOnClickListener(v -> {
            if (input.getText().toString().equals("0")){
                input.setText("1");
                temp = eval(input.getText().toString());
                output.setText(String.valueOf(temp));
                params.height = 140;
                params1.height=60;
                input.setLayoutParams(params);
                output.setLayoutParams(params1);
                input.setTextSize(44);
                output.setTextSize(20);
                sum = false;
                minus = false;
                division = false;
                mult = false;pow=false;
            }
            else{
                if(input.getText().toString().length()<=26) {
                    text = input.getText().toString();
                    text = text + "1";
                    input.setText(text);
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    params.height = 140;
                    params1.height=60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = false;
                    minus = false;
                    division = false;
                    mult = false;pow=false;
                }
                else {
                    Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                }
            }
        });

        two.setOnClickListener(v -> {
            if (input.getText().toString().equals("0")){
                input.setText("2");
                temp = eval(input.getText().toString());
                output.setText(String.valueOf(temp));
                params.height = 140;
                params1.height=60;
                input.setLayoutParams(params);
                output.setLayoutParams(params1);
                input.setTextSize(44);
                output.setTextSize(20);
                sum = false;
                minus = false;
                division = false;
                mult = false;pow=false;
            }
            else {
                if (input.getText().toString().length() <= 26) {
                    text = input.getText().toString();
                    text = text + "2";
                    input.setText(text);
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    params.height = 140;
                    params1.height = 60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = false;
                    minus = false;
                    division = false;
                    mult = false;
                    pow = false;
                } else {
                    Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                }
            }
        });

        three.setOnClickListener(v -> {
            if (input.getText().toString().equals("0")){
                input.setText("3");
                temp = eval(input.getText().toString());
                output.setText(String.valueOf(temp));
                params.height = 140;
                params1.height=60;
                input.setLayoutParams(params);
                output.setLayoutParams(params1);
                input.setTextSize(44);
                output.setTextSize(20);
                sum = false;
                minus = false;
                division = false;
                mult = false;pow=false;
            }
            else{
                if(input.getText().toString().length()<=26) {
                    text = input.getText().toString();
                    text = text + "3";
                    input.setText(text);
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    params.height = 140;
                    params1.height=60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = false;
                    minus = false;
                    division = false;
                    mult = false;pow=false;
                }
                else {
                    Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                }
            }
        });

        four.setOnClickListener(v -> {
            if (input.getText().toString().equals("0")){
                input.setText("4");
                temp = eval(input.getText().toString());
                output.setText(String.valueOf(temp));
                params.height = 140;
                params1.height=60;
                input.setLayoutParams(params);
                output.setLayoutParams(params1);
                input.setTextSize(44);
                output.setTextSize(20);
                sum = false;
                minus = false;
                division = false;
                mult = false;pow=false;
            }
            else{
                if(input.getText().toString().length()<=26) {
                    text = input.getText().toString();
                    text = text + "4";
                    input.setText(text);
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    params.height = 140;
                    params1.height=60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = false;
                    minus = false;
                    division = false;
                    mult = false;pow=false;
                }
                else {
                    Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                }
            }
        });

        five.setOnClickListener(v -> {
            if (input.getText().toString().equals("0")){
                input.setText("5");
                temp = eval(input.getText().toString());
                output.setText(String.valueOf(temp));
                params.height = 140;
                params1.height=60;
                input.setLayoutParams(params);
                output.setLayoutParams(params1);
                input.setTextSize(44);
                output.setTextSize(20);
                sum = false;
                minus = false;
                division = false;
                mult = false;pow=false;
            }
            else{
                if(input.getText().toString().length()<=26) {
                    text = input.getText().toString();
                    text = text + "5";
                    input.setText(text);
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    params.height = 140;
                    params1.height=60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = false;
                    minus = false;
                    division = false;
                    mult = false;pow=false;
                }
                else {
                    Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                }
            }
        });

        six.setOnClickListener(v -> {
            if (input.getText().toString().equals("0")){
                input.setText("6");
                temp = eval(input.getText().toString());
                output.setText(String.valueOf(temp));
                params.height = 140;
                params1.height=60;
                input.setLayoutParams(params);
                output.setLayoutParams(params1);
                input.setTextSize(44);
                output.setTextSize(20);
                sum = false;
                minus = false;
                division = false;
                mult = false;pow=false;
            }
            else{
                if(input.getText().toString().length()<=26) {
                    text = input.getText().toString();
                    text = text + "6";
                    input.setText(text);
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    params.height = 140;
                    params1.height=60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = false;
                    minus = false;
                    division = false;
                    mult = false;pow=false;
                }
                else {
                    Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                }
            }
        });

        seven.setOnClickListener(v -> {
            if (input.getText().toString().equals("0")){
                input.setText("7");
                temp = eval(input.getText().toString());
                output.setText(String.valueOf(temp));
                params.height = 140;
                params1.height=60;
                input.setLayoutParams(params);
                output.setLayoutParams(params1);
                input.setTextSize(44);
                output.setTextSize(20);
                sum = false;
                minus = false;
                division = false;
                mult = false;pow=false;
            }
            else{
                if(input.getText().toString().length()<=26) {
                    text = input.getText().toString();
                    text = text + "7";
                    input.setText(text);
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    params.height = 140;
                    params1.height=60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = false;
                    minus = false;
                    division = false;
                    mult = false;pow=false;
                }
                else {
                    Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                }
            }
        });

        eight.setOnClickListener(v -> {
            if (input.getText().toString().equals("0")){
                input.setText("8");
                temp = eval(input.getText().toString());
                output.setText(String.valueOf(temp));
                params.height = 140;
                params1.height=60;
                input.setLayoutParams(params);
                output.setLayoutParams(params1);
                input.setTextSize(44);
                output.setTextSize(20);
                sum = false;
                minus = false;
                division = false;
                mult = false;pow=false;
            }
            else{
                if(input.getText().toString().length()<=26) {
                    text = input.getText().toString();
                    text = text + "8";
                    input.setText(text);
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    params.height = 140;
                    params1.height=60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = false;
                    minus = false;
                    division = false;
                    mult = false;pow=false;
                }
                else {
                    Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nine.setOnClickListener(v -> {
            if (input.getText().toString().equals("0")){
                input.setText("9");
                temp = eval(input.getText().toString());
                output.setText(String.valueOf(temp));
                params.height = 140;
                params1.height=60;
                input.setLayoutParams(params);
                output.setLayoutParams(params1);
                input.setTextSize(44);
                output.setTextSize(20);
                sum = false;
                minus = false;
                division = false;
                mult = false;pow=false;
            }
            else{
                if(input.getText().toString().length()<=26) {
                    text = input.getText().toString();
                    text = text + "9";
                    input.setText(text);
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    params.height = 140;
                    params1.height=60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = false;
                    minus = false;
                    division = false;
                    mult = false;pow=false;
                }
                else {
                    Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                }
            }
        });

        zero.setOnClickListener(v -> {
            if (input.getText().toString().equals("0")){
                input.setText("0");
                temp = eval(input.getText().toString());
                output.setText(String.valueOf(temp));
                params.height = 140;
                params1.height=60;
                input.setLayoutParams(params);
                output.setLayoutParams(params1);
                input.setTextSize(44);
                output.setTextSize(20);
                sum = false;
                minus = false;
                division = false;
                mult = false;pow=false;
            }
            else{
                if(input.getText().toString().length()<=26) {
                    text = input.getText().toString();
                    text = text + "0";
                    input.setText(text);
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    params.height = 140;
                    params1.height=60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = false;
                    minus = false;
                    division = false;
                    mult = false;pow=false;
                }
                else {
                    Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                }
            }
        });

        decimal.setOnClickListener(v -> {
            if (input.getText().toString().equals("0")){
                input.setText("0.");
                temp = eval(input.getText().toString());
                output.setText(String.valueOf(temp));
                params.height = 140;
                params1.height=60;
                input.setLayoutParams(params);
                output.setLayoutParams(params1);
                input.setTextSize(44);
                output.setTextSize(20);
                sum = false;
                minus = false;
                division = false;
                mult = false;pow=false;
            }
            else{
                if(input.getText().toString().length()<=26) {
                    text = input.getText().toString();
                    if (i==0) {
                        text = text + ".";
                        i=1;
                    }
                    input.setText(text);
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    params.height = 140;
                    params1.height=60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = false;
                    minus = false;
                    division = false;
                    mult = false;pow=false;
                }
                else {
                    Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //cut single character from input
        cut.setOnClickListener(v -> {
            if (!input.getText().toString().equals("0")) {
                String string = input.getText().toString();
                StringBuffer sb = new StringBuffer(string);
                if(sb.charAt(sb.length() - 1) != ' ') {
                    sb.deleteCharAt(sb.length() - 1);
                    input.setText(sb);
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    if (sb.charAt(sb.length() - 1) == ' '){
                        sum = true;
                        minus = true;
                        division = true;
                        mult = true;
                        pow=true;
                    }
                }
                else{
                    for (int j=0; j<3; j++) {
                        sb.deleteCharAt(sb.length() - 1);
                        input.setText(sb);
                        temp = eval(input.getText().toString());
                        output.setText(String.valueOf(temp));
                        sum = false;
                        minus = false;
                        division = false;
                        mult = false;pow=false;
                    }
                }
            }
            else {
                Log.d("VIJAY", "onCutClick: empty input");
            }
        });

        //All clear button
        ac.setOnClickListener(v -> {
            input.setText("0");
            temp = eval(input.getText().toString());
            output.setText(String.valueOf(temp));
            output.setText("0");
            input.setTextSize(44);
            output.setTextSize(20);
            params.height = 140;
            params1.height=60;
            input.setLayoutParams(params);
            output.setLayoutParams(params1);
            i=0;
            sum = false;
            minus = false;
            division = false;
            mult = false;pow=false;
        });

        //add button
        add.setOnClickListener(v -> {
            if (!minus && !mult && !division && !pow) {
                if (input.getText().toString().equals("0")) {
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    input.setText("0 + ");
                    params.height = 140;
                    params1.height = 60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = true;
                    minus = true;
                    division = true;
                    mult = true;
                    pow=true;
                }
                else {
                    if (input.getText().toString().length() <= 26) {
                        text = input.getText().toString();
                        text = text + " + ";
                        i = 1;
                        temp = eval(input.getText().toString());
                        output.setText(String.valueOf(temp));
                        input.setText(text);
                        params.height = 140;
                        params1.height = 60;
                        input.setLayoutParams(params);
                        output.setLayoutParams(params1);
                        input.setTextSize(44);
                        output.setTextSize(20);
                        sum = true;
                        minus = true;
                        division = true;
                        mult = true;
                        pow =true;
                    } else {
                        Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(getContext(), "Math Error...", Toast.LENGTH_SHORT).show();
            }
        });

        //sub button
        sub.setOnClickListener(v -> {
            if (!sum && !mult && !division && !pow) {
                if (input.getText().toString().equals("0")) {
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    input.setText("0 - ");
                    params.height = 140;
                    params1.height = 60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = true;
                    minus = true;
                    division = true;
                    mult = true;
                    pow = true;
                }
                else {
                    if (input.getText().toString().length() <= 26) {
                        text = input.getText().toString();
                        text = text + " - ";
                        i = 1;
                        temp = eval(input.getText().toString());
                        output.setText(String.valueOf(temp));
                        input.setText(text);
                        params.height = 140;
                        params1.height = 60;
                        input.setLayoutParams(params);
                        output.setLayoutParams(params1);
                        input.setTextSize(44);
                        output.setTextSize(20);
                        sum = true;
                        minus = true;
                        division = true;
                        mult = true;
                        pow = true;
                    } else {
                        Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(getContext(), "Math Error...", Toast.LENGTH_SHORT).show();
            }
        });

        //multiply button
        multiply.setOnClickListener(v -> {
            if (!minus && !sum && !division && !pow) {
                if (input.getText().toString().equals("0")) {
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    input.setText("0 x ");
                    params.height = 140;
                    params1.height = 60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = true;
                    minus = true;
                    division = true;
                    mult = true;
                    pow = true;
                }
                else {
                    if (input.getText().toString().length() <= 26) {
                        text = input.getText().toString();
                        text = text + " x ";
                        i = 1;
                        temp = eval(input.getText().toString());
                        output.setText(String.valueOf(temp));
                        input.setText(text);
                        params.height = 140;
                        params1.height = 60;
                        input.setLayoutParams(params);
                        output.setLayoutParams(params1);
                        input.setTextSize(44);
                        output.setTextSize(20);
                        sum = true;
                        minus = true;
                        division = true;
                        mult = true;
                        pow = true;
                    } else {
                        Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(getContext(), "Math Error...", Toast.LENGTH_SHORT).show();
            }
        });

        //divide button
        div.setOnClickListener(v -> {
            if (!minus && !mult && !sum && !pow) {
                if (input.getText().toString().equals("0")) {
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    input.setText("0 / ");
                    params.height = 140;
                    params1.height = 60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = true;
                    minus = true;
                    division = true;
                    mult = true;
                    pow = true;
                }
                else {
                    if (input.getText().toString().length() <= 26) {
                        text = input.getText().toString();
                        text = text + " / ";
                        i = 1;
                        temp = eval(input.getText().toString());
                        output.setText(String.valueOf(temp));
                        input.setText(text);
                        params.height = 140;
                        params1.height = 60;
                        input.setLayoutParams(params);
                        output.setLayoutParams(params1);
                        input.setTextSize(44);
                        output.setTextSize(20);
                        sum = true;
                        minus = true;
                        division = true;
                        mult = true;
                        pow = true;
                    } else {
                        Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(getContext(), "Math Error...", Toast.LENGTH_SHORT).show();
            }
        });

        power.setOnClickListener(v -> {
            if (!minus && !mult && !sum && !division) {
                if (input.getText().toString().equals("0")) {
                    temp = eval(input.getText().toString());
                    output.setText(String.valueOf(temp));
                    input.setText("0 ^ ");
                    params.height = 140;
                    params1.height = 60;
                    input.setLayoutParams(params);
                    output.setLayoutParams(params1);
                    input.setTextSize(44);
                    output.setTextSize(20);
                    sum = true;
                    minus = true;
                    division = true;
                    mult = true;
                    pow = true;
                }
                else {
                    if (input.getText().toString().length() <= 26) {
                        text = input.getText().toString();
                        text = text + " ^ ";
                        i = 1;
                        temp = eval(input.getText().toString());
                        output.setText(String.valueOf(temp));
                        input.setText(text);
                        params.height = 140;
                        params1.height = 60;
                        input.setLayoutParams(params);
                        output.setLayoutParams(params1);
                        input.setTextSize(44);
                        output.setTextSize(20);
                        sum = true;
                        minus = true;
                        division = true;
                        mult = true;
                        pow = true;
                    } else {
                        Toast.makeText(getContext(), "only 26 characters at time....", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(getContext(), "Math Error...", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}