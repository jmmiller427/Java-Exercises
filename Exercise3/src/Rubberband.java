import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Rubberband extends JFrame{
    public class DrawRectangle extends JPanel implements MouseListener, MouseMotionListener{

        Polygon bigRect, bottomLeft, bottomRight, topLeft, topRight;
        int bigX[] = {175, 325, 325, 175};
        int bigY[] = {150, 150, 250, 250};

        int bottomRightX[] = {320, 330, 330, 320};
        int bottomRightY[] = {245, 245, 255, 255};

        int bottomLeftX[] = {170, 180, 180, 170};
        int bottomLeftY[] = {245, 245, 255, 255};

        int topRightX[] = {320, 330, 330, 320};
        int topRightY[] = {145, 145, 155, 155};

        int topLeftX[] = {170, 180, 180, 170};
        int topLeftY[] = {145, 145, 155, 155};

        int x1[], y1[], x2[], y2[];

        int TLx = 175; int TLy = 150;
        int TRx = 325; int TRy = 150;
        int BRx = 325; int BRy = 250;
        int BLx = 175; int BLy = 250;

        boolean draggingBottomRight = false;
        boolean draggingBottomLeft = false;
        boolean draggingTopRight = false;
        boolean draggingTopLeft = false;

        private DrawRectangle(){

            bigRect = new Polygon(bigX, bigY, 4);
            bottomRight = new Polygon(bottomRightX, bottomRightY, 4);
            bottomLeft = new Polygon(bottomLeftX, bottomLeftY, 4);
            topRight = new Polygon(topRightX, topRightY, 4);
            topLeft = new Polygon(topLeftX, topLeftY, 4);
            super.addMouseListener(this);
            super.addMouseMotionListener(this);
        }

        public void paintComponent(Graphics g){

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;

            g2d.drawPolygon(bigRect);
            g2d.drawPolygon(bottomLeft);
            g2d.drawPolygon(bottomRight);
            g2d.drawPolygon(topLeft);
            g2d.drawPolygon(topRight);
        }

        private void resetSquare(){

            bigRect = new Polygon(bigX, bigY, 4);
            bottomRight = new Polygon(bottomRightX, bottomRightY, 4);
            bottomLeft = new Polygon(bottomLeftX, bottomLeftY, 4);
            topRight = new Polygon(topRightX, topRightY, 4);
            topLeft = new Polygon(topLeftX, topLeftY, 4);

            TLx = 175; TLy = 150;
            TRx = 325; TRy = 150;
            BRx = 325; BRy = 250;
            BLx = 175; BLy = 250;

            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e){

            if (bottomRight.contains(e.getPoint())) { draggingBottomRight = true; }
            else if (bottomLeft.contains(e.getPoint())) { draggingBottomLeft = true; }
            else if (topRight.contains(e.getPoint())) { draggingTopRight = true; }
            else if (topLeft.contains(e.getPoint())) { draggingTopLeft = true; }
        }

        @Override
        public void mouseReleased(MouseEvent e){

            draggingBottomRight = false;
            draggingBottomLeft = false;
            draggingTopLeft = false;
            draggingTopRight = false;
        }

        @Override
        public void mouseDragged(MouseEvent e){

            x2 = new int[]{e.getX() - 5, e.getX() + 5, e.getX() + 5, e.getX() - 5};
            y2 = new int[]{e.getY() - 5, e.getY() - 5, e.getY() + 5, e.getY() + 5};

            if (draggingBottomRight) {

                BRx = e.getX();
                BRy = e.getY();
                bottomRight = new Polygon(x2, y2, 4);

            } else if (draggingBottomLeft) {

                BLx = e.getX();
                BLy = e.getY();
                bottomLeft = new Polygon(x2, y2, 4);

            } else if (draggingTopRight) {

                TRx = e.getX();
                TRy = e.getY();
                topRight = new Polygon(x2, y2, 4);

            } else if (draggingTopLeft) {

                TLx = e.getX();
                TLy = e.getY();
                topLeft = new Polygon(x2, y2, 4);

            }

            x1 = new int[]{TLx, TRx, BRx, BLx};
            y1 = new int[]{TLy, TRy, BRy, BLy};
            bigRect = new Polygon(x1, y1, 4);
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e){

        }
        @Override
        public void mouseEntered(MouseEvent e){

        }
        @Override
        public void mouseExited(MouseEvent e){

        }
        @Override
        public void mouseMoved(MouseEvent e){

        }
    }

    private Rubberband(){

        super("Rubber Band");

        // Create reset button and Panels for the button and rectangle
        JButton reset = new JButton("Reset");
        JPanel buttons = new JPanel();
        DrawRectangle rectangle = new DrawRectangle();

        // Add an action listener to the reset button
        // Add the button to the JPanel
        reset.addActionListener(e -> rectangle.resetSquare());
        buttons.add(reset);

        rectangle.setDoubleBuffered(true);

        Container c = getContentPane();

        // Add both panels to the content pane
        c.add(buttons, BorderLayout.SOUTH);
        c.add(rectangle, BorderLayout.CENTER);

        // Set JFrame
        setSize(500, 500);
        setVisible(true);
        setResizable(false);
    }

    // Create main function to call rubber band class
    // Exit JFrame on close button
    public static void main(String args[]){
        Rubberband rec = new Rubberband();
        rec.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){ System.exit(0); }
        });
    }
}

