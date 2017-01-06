package com.semicolonlabs.calculator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * On click handler for buttons
 */
public class Handler  implements View.OnClickListener {
    float num1, num2;
    char operator;
    String error = "ERROR";
    EditText result;
    Button equalTo;
    public Handler(View result, View equalTo){
        this.result = (EditText)result;
        this.equalTo = (Button)equalTo;
        num1 = num2 = 0;
        operator = ' ';
    }
    private String compute(String query){
        Pattern equation = Pattern.compile("^[\\d.]+[+\\-%*/][\\d.]+$");
        Matcher m_eq = equation.matcher(query);
        int index;
        if(m_eq.matches()){
            Pattern operator_ = Pattern.compile("[+\\-%*/]");
            Matcher m_op = operator_.matcher(query);
            if(m_op.find()){
                index = m_op.start();
                num1 = Float.parseFloat(query.substring(0,index));
                num2 = Float.parseFloat(query.substring(index+1));
                operator = query.charAt(index);
                float ans =  Logic.calculate(num1, num2, operator);
                return Float.toString(ans);
            }
        }
        return error;
    }
    @Override
    public void onClick(View v) {
        Button btn = (Button)v;
        if(btn.getText().equals(" "))
            equalTo.performClick();
        String operator = btn.getText().toString();
        String text = result.getText().toString();
        if(text.length()==0)text="0";
        switch (operator){
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                Pattern p = Pattern.compile("^[\\d.]+[+\\-%*/][\\d.]+$");
                if(!Character.isDigit(text.charAt(0)))
                    result.setText(error);
                else if(p.matcher(text).matches())
                    result.setText(compute(text)+operator);
                else
                    result.append(operator);
            break;
            case "<-":
                if(text.length()>0)
                    result.setText(text.substring(0,text.length()-1));
                break;
            case "C":
                result.setText("");
                break;
            case "=":
                Pattern p_ = Pattern.compile("^[\\d.]*$");
                if(!p_.matcher(text).matches())
                    result.setText(compute(text));
                break;
            case ".":
                Pattern p__ = Pattern.compile("([\\d]*[.]+[\\d]*)|(^[\\d.]+[+\\-%*/][\\d]*[.]+[\\d]*$)");
                if(!p__.matcher(text).matches())
                    result.append(".");
                break;
            default:
                result.append(btn.getText());
        }
    }
}
