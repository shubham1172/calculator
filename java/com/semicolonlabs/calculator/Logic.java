package com.semicolonlabs.calculator;
/**
 * Handles the calculator logic of the class
 */
public class Logic {
    private static float add(float a, float b){
        return (a+b);
    }
    private static float subtract(float a, float b){
        return (a-b);
    }
    private static float multiply(float a, float b){
        return (a*b);
    }
    private static float divide(float a, float b){
        return (a/b);
    }
    private static float modulus(float a, float b){
        return (a%b);
    }
    public static float calculate(float a, float b, char operator){
        float answer = 0;
        switch (operator) {
            case '+':
                answer = add(a, b);
            break;
            case '-':
                answer = subtract(a, b);
            break;
            case '*':
                answer = multiply(a, b);
            break;
            case '/':
                answer = divide(a, b);
            break;
            case '%':
                answer = modulus(a, b);
            break;
            default:
                answer = 0;
        }
        return answer;
    }
}
