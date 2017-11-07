import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// Create Rotate Rectangle class with JFrame extension
// Create a Draw Rectangle class that extends JPanel and adds a mouse listener
public class RotateRectangle extends JFrame{
    public class DrawRectangle extends JPanel implements MouseListener{

        // Create an initial angle, a rotating boolean
        // Create rectangle and its initial sizes and a timer
        int angle = 0;
        boolean rotate_isTrue = false;
        Rectangle rect;
        int x = 175;
        int y = 150;
        private Timer redraw;

        // Set initial rectangle position and add a mouse listener
        private DrawRectangle(){

            rect = new Rectangle(x, y, 150, 100 );
            super.addMouseListener(this);
        }

        // paint the rectangle onto the JPanel
        // If rotating boolean is true, then rotate the object
        public void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;

            if (rotate_isTrue){
                g2d.rotate(Math.toRadians(angle), x, y);
            }

            g2d.draw(rect);
        }

        // Create a timer to add one to the angle of rotation each time it is fired
        // start the timer
        private void startRotation(){

            redraw = new Timer(10, e -> {
                angle++;
                repaint();
            });
            redraw.setRepeats(true);
            redraw.start();
        }

        // Restart the rotation by setting angle back to 0, rotating boolean to false
        // and adding the mouse listener back
        private void restartGame(){

            rotate_isTrue = false;
            angle = 0;
            super.addMouseListener(this);
            repaint();
        }

        // Stop the timer to stop the rotation
        private void stopRotate(){

            redraw.stop();
            repaint();
        }

        // Get a click within the rectangle bounds
        // If it is within these bounds set x and y to rotate around and start the timer
        // Remove the mouse listener so it cannot be clicked again
        @Override
        public void mouseClicked(MouseEvent e){

            if (rect.contains(e.getPoint())){

                rotate_isTrue = true;
                startRotation();
                x = e.getX();
                y = e.getY();
                super.removeMouseListener(this);
            }
        }
        @Override
        public void mousePressed(MouseEvent e){

        }
        @Override
        public void mouseReleased(MouseEvent e){

        }
        @Override
        public void mouseEntered(MouseEvent e){

        }
        @Override
        public void mouseExited(MouseEvent e){

        }
    }

    // Rotate Rectangle method
    private RotateRectangle(){

        super("Rotate Rectangle");

        // set up buttons and Panels for buttons to go on and for the rectangle
        JPanel buttons = new JPanel();
        DrawRectangle rectangle = new DrawRectangle();
        JButton stop = new JButton("Stop");
        JButton restart = new JButton("Restart");

        // Add action listeners to each button
        stop.addActionListener(e -> rectangle.stopRotate());
        restart.addActionListener(e -> rectangle.restartGame());

        // add the buttons to the button panel
        buttons.add(stop);
        buttons.add(restart);

        Container c = getContentPane();

        // add all panels to the content pane
        c.add(buttons, BorderLayout.SOUTH);
        c.add(rectangle, BorderLayout.CENTER);

        setSize(500, 500);
        setVisible(true);
        setResizable(false);
    }

    // Create the main function that calls Rotate Rectangle
    // Have it stay open until the window close button is hit
    public static void main(String args[]){
        RotateRectangle rec = new RotateRectangle();
        rec.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){ System.exit(0); }
        });
    }
}
