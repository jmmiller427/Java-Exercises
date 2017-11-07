/* 
Name: John Miller
Class: CS 335
Date: 29 August 2017
*/

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

public class Exercise0{

    // create the main function
    public static void main(String args[]){
        System.out.println("Please enter a 1 for morse code and a 2 for evaluating pi");

        // Get user input to do problem 1 or 2
        Scanner scanner = new Scanner(System.in);
        System.out.print("Problem 1 or 2? 0 to quit: ");
        int get_problem = scanner.nextInt();

        boolean run = true;

        while(run = true)
        {   // run each problem given the user input
            if (get_problem == 1) {
                problem1();
            }
            else if (get_problem == 2) {
                problem2();
            }
            else if (get_problem == 0)
            {
                System.exit(0);
            }
            else {
                System.out.println("Not a correct number");
            }

            Scanner scanner_2 = new Scanner(System.in);
            System.out.print("Problem 1 or 2? 0 to quit: ");
            get_problem = scanner_2.nextInt();
        }
    }

    // create the problem one function
    private static void problem1(){
        // get user input to enter their first name
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String get_name = scanner.next().toUpperCase();

        // Map every letter in the alphabet to Morse Code
        // using a HashMap 
        Map<String, String> convert = new HashMap<>();
        convert.put("A", ".-");
        convert.put("B", "-...");
        convert.put("C", "-.-.");
        convert.put("D", "-..");
        convert.put("E", ".");
        convert.put("F", "..-.");
        convert.put("G", "--.");
        convert.put("H", "....");
        convert.put("I", "..");
        convert.put("J", ".---");
        convert.put("K", "-.-");
        convert.put("L", ".-..");
        convert.put("M", "--");
        convert.put("N", "-.");
        convert.put("O", "---");
        convert.put("P", ".--.");
        convert.put("Q", "--.-");
        convert.put("R", ".-.");
        convert.put("S", "...");
        convert.put("T", "-");
        convert.put("U", "..-");
        convert.put("V", "...-");
        convert.put("W", ".--");
        convert.put("X", "-..-");
        convert.put("Y", "-.--");
        convert.put("Z", "--..");

        for (int i = 0; i < get_name.length(); i++)
        {
            // Get each character from the name input
            // and convert it to its respective Morse Code representation.
            char letter = get_name.charAt(i);

            String s = String.valueOf(letter);

            System.out.print(convert.get(s) + " ");
        }

        System.out.print("\n");
    }

    // Create the function for the second problem
    private static void problem2(){
        double sum = 0.0;
        int i = 1;
        boolean runs = true;

        // run a while loop to create the sequence of pi
        while(runs = true)
        {
            double temp = 0.0;
            
            // add and subtract given the index of the sequence
            // Each time add it to the total sum of pi
            if(i % 2.0 == 0.0)
            {
                temp -= 1.0 / (2.0 * i - 1.0);
                temp *= 4.0;
            }
            else
            {
                temp += 1.0 / (2.0 * i - 1.0);
                temp *= 4.0;
            }

            i += 1;
            sum += temp;

            // Round the sum of pi to six decimal places
            // If the rounded sum equals what pi actually is then exit the loop
            double new_sum = (double)Math.round(sum * 10000000) / 10000000;

            if(new_sum == 3.141592)
            {
                System.out.println(new_sum + " was reached in " + i + " iterations.");
                break;
            }
        }
    }
}
