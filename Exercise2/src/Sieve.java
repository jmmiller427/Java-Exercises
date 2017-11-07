import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;

public class Sieve extends JFrame{
    private Sieve(){

        // Make a panel for the input and output
        JPanel outputView = new JPanel();
        JPanel inputView = new JPanel();

        // Create a label for output, an input textfield and an execute button
        JLabel answer = new JLabel("Enter a number from 0 - 999");
        JTextField input = new JTextField();
        JButton execute = new JButton("Execute");

        // Create an action listener for the execute button
        execute.addActionListener(e -> {

            // on the click get the input text
            // set up the boolean array for checking primes
            String number = input.getText();
            boolean[] not_primes = new boolean[1000];
            Arrays.fill(not_primes, true);

            // run a loop to get all prime numbers
            for(int i = 2; i < not_primes.length; i++){
                if(not_primes[i]){
                    for(int j = 2; i * j < not_primes.length; j++){
                        not_primes[i * j] = false;
                    }
                }
            }

            Integer result = Integer.valueOf(number);

            // If the number inputted is not in range, output
            if (result > 999 || result < 0){
                answer.setText("Invalid range!");
            }
            else{
                if (not_primes[result]){
                    // if it is in range but a 0 or 1 it is not prime
                    // else it is prime
                    if (result == 0 || result == 1){
                        answer.setText(result + " Is Not Prime");
                    }
                    else {
                        answer.setText(result + " Is Prime");
                    }
                }
                else{
                    answer.setText(result + " Is Not Prime");
                }

            }
        });

        Container c = getContentPane();

        // add each element to its panel
        inputView.add(input);
        inputView.add(execute);
        outputView.add(answer);

        outputView.setLayout(new GridLayout(4, 4, 5, 1));
        inputView.setLayout(new GridLayout(2, 4, 1, 1));

        // add the panels to the JFrame
        c.add(inputView, BorderLayout.NORTH);
        c.add(outputView, BorderLayout.CENTER);

        // set the size
        setSize(220, 300);
        setVisible(true);
        setTitle("Prime Numbers");
        setResizable(false);
    }

    // Wait for the exit button to be hit
    public static void main(String args[]){
        Sieve prime = new Sieve();
        prime.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }
}

