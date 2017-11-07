/*
Name: John Miller
Date: 5 September 2017
Class: CS 335-001
Assignment: Exercise-1
*/

import java.util.Vector;

// create a class for finding perfect numbers
class Perfect_Num {

    // method isPerfect returns an int and takes in a vector of factors
    int isPerfect(Vector factors_list){

        int sum = 0;
        int perfNum = 0;

        // loop through the factors vector adding each factor to itself except
        // the last number in the vector
        for(int i = 0; i < factors_list.size() - 1; i++){

            sum += (int)factors_list.get(i);
        }

        // if the sum of all factors equals the number itself
        // return it as a perfect number
        if (sum == (int)factors_list.get(factors_list.size() - 1)){

            perfNum = (int) factors_list.get(factors_list.size() - 1);
            return perfNum;
        }

        // else return not a perfect number
        return perfNum;
    }

    // create a method that takes in an int and outputs a vector of factors
    Vector<Integer> find_factors(int num){

        Vector<Integer> factors = new Vector<>();

        // get all factors of a number and return the vector of factors
        for(int i = 1; i <= num; i++){

            if(num % i == 0) {

                factors.add(i);
            }
        }

        return factors;
    }
}
