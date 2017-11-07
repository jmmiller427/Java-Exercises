import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Calculator extends JFrame{
    private Calculator(){

        // Make two panels, One for holding the buttons and one
        // for the text field
        JPanel outputView = new JPanel();
        JPanel buttonLayout = new JPanel();

        // Create the text field and the JButtons for each button
        JTextField output = new JTextField();

        JButton num1 = new JButton("1");
        JButton num2 = new JButton("2");
        JButton num3 = new JButton("3");
        JButton num4 = new JButton("4");
        JButton num5 = new JButton("5");
        JButton num6 = new JButton("6");
        JButton num7 = new JButton("7");
        JButton num8 = new JButton("8");
        JButton num9 = new JButton("9");
        JButton num0 = new JButton("0");
        JButton add = new JButton("+");
        JButton div = new JButton("/");
        JButton mult = new JButton("*");
        JButton sub = new JButton("-");
        JButton deci = new JButton(".");
        JButton equals = new JButton("=");

        Container c = getContentPane();

        // Add each element to its specific panel
        // For the buttons, Add them in order from top left to bottom right
        // in the order you want them to display
        outputView.add(output);
        buttonLayout.add(num9);
        buttonLayout.add(num8);
        buttonLayout.add(num7);
        buttonLayout.add(div);
        buttonLayout.add(num6);
        buttonLayout.add(num5);
        buttonLayout.add(num4);
        buttonLayout.add(mult);
        buttonLayout.add(num3);
        buttonLayout.add(num2);
        buttonLayout.add(num1);
        buttonLayout.add(sub);
        buttonLayout.add(deci);
        buttonLayout.add(num0);
        buttonLayout.add(equals);
        buttonLayout.add(add);

        // set the panel layouts and add them to the the content pane
        outputView.setLayout(new GridLayout(1, 1, 1, 1));
        buttonLayout.setLayout(new GridLayout(4, 4, 1, 1));

        c.add(outputView, BorderLayout.NORTH);
        c.add(buttonLayout, BorderLayout.CENTER);

        // Edit the JFrame to size you want
        setSize(220, 300);
        setVisible(true);
        setTitle("Calculator");
        setResizable(false);
    }

    // Wait until the exit button is clicked to exit the program
    public static void main(String args[]){
        Calculator calc = new Calculator();
        calc.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }
}
