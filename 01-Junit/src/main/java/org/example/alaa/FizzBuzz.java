package org.example.alaa;


public class FizzBuzz {

    //If a number is divisible by 3, print "Fizz" instead of the number.
    //If a number is divisible by 5 (and not 3), print "Buzz" instead of the number.
    //If a number is divisible by both 3 and 5, print "FizzBuzz" instead of the number.

    public String compute(int number){
        StringBuilder result = new StringBuilder();

        if (number % 3 == 0){
            result.append("Fizz");
        }
        if (number % 5 == 0){
            result.append("Buzz");
        }
        if (result.isEmpty()){
            return String.valueOf(number);
        }else{
            return result.toString();
        }
    }
}
