/*
Name: John Miller
Date: 5 September 2017
Class: CS 335-001
Assignment: Exercise-1
*/

//import java.util.Scanner;

import java.util.Vector;
import java.util.Arrays;

// Create class Exercise 1
public class Exercise1 {

    // Create main function
    public static void main(String args[]){

        System.out.println("Problem 1, Perfect Numbers between 1-1000: \n");
        Perfect_Num pn = new Perfect_Num();

        // run a for loop 1000 times to find all perfect numbers between 1-1000
        // get a vector full of factors for each number
        // then call isPerfect to get perfect numbers
        for(int perfect = 1; perfect <= 1000; perfect++){

            Vector<Integer> factors = pn.find_factors(perfect);

            int perfect_number = pn.isPerfect(factors);

            if(perfect_number == 0){continue;}

            System.out.println(perfect_number + " is a perfect number.");
        }

        System.out.println("\nProblem 2, Prime Numbers between 1-1000: \n");

        // create a boolean array of size 1000 and set all values to true
        boolean[] not_primes = new boolean[1000];

        Arrays.fill(not_primes, true);

        // loop through the length of the array skipping values 0 and 1
        // if a value is true then loop through the rest of the array
        // setting all of the multiples of that number to false
        // ex. 2 is the first prime number, then it goes through the rest of the array
        //      and sets all multiples of 2 to false
        for(int i = 2; i < not_primes.length; i++){

            if(not_primes[i]){

                for(int j = 2; i * j < not_primes.length; j++){

                    not_primes[i * j] = false;
                }
            }
        }

        // print out all the prime numbers
        for(int k = 2; k < not_primes.length; k++){

            if(not_primes[k]){

                System.out.println(k + " is a prime.");
            }
        }
    }
}
