/* Name: John Miller
 * Date: 6 November 2017
 * Class: CS 335 - 001
 * Project: Exercise 4
 *
 * Starter code provided by Dr. Seales.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;

public class Exercise4 extends JFrame {

    // This frame will hold the primary components:
    // 	An object to hold the buffered image and its associated operations
    //	Components to control the interface

    // Instance variables
    private BufferedImage image;   // the image
    private Exercise4Obj view;       // a component in which to display an image
    private JLabel infoLabel;      // an informative label for the simple GUI
    private JButton Blur3Button, Blur5Button, Blur7Button, Blur9Button, Blur11Button; // Button to trigger blur operator
    private JButton OriginalButton;// Button to restore original image

    // Constructor for the frame
    private Exercise4 () {

        super();				// call JFrame constructor

        this.buildMenus();		// helper method to build menus
        this.buildComponents();		// helper method to set up components
        this.buildDisplay();		// Lay out the components on the display
    }

    //  Builds the menus to be attached to this JFrame object
    //  Primary side effect:  menus are added via the setJMenuBar call
    //  		Action listeners for the menu items are anonymous inner
    //		classes here
    //  This helper method is called once by the constructor

    private void buildMenus () {

        final JFileChooser fc = new JFileChooser(".");
        JMenuBar bar = new JMenuBar();
        this.setJMenuBar (bar);
        JMenu fileMenu = new JMenu ("File");
        JMenuItem fileOpen = new JMenuItem ("Open");
        JMenuItem fileExit = new JMenuItem ("Exit");

        fileOpen.addActionListener(e -> {
                    int returnVal = fc.showOpenDialog(Exercise4.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        try {
                            image = ImageIO.read(file);
                        } catch (IOException ignored){}

                        view.setImage(image);
                        view.showImage();
                    }
                }
        );

        fileExit.addActionListener(e -> System.exit(0));

        fileMenu.add(fileOpen);
        fileMenu.add(fileExit);
        bar.add(fileMenu);
    }

    //  Allocate components (these are all instance vars of this frame object)
    //  and set up action listeners for each of them
    //  This is called once by the constructor

    private void buildComponents() {

        // Create components to in which to display image and GUI controls
        // reads a default image
        view = new Exercise4Obj(readImage());
        infoLabel = new JLabel("Original Image");
        OriginalButton = new JButton("Original");
        Blur3Button = new JButton("Blur 3x3");
        Blur5Button = new JButton("Blur 5x5");
        Blur7Button = new JButton("Blur 7x7");
        Blur9Button = new JButton("Blur 9x9");
        Blur11Button = new JButton("Blur 11x11");

        // Button listeners activate the buffered image object in order
        // to display appropriate function
        OriginalButton.addActionListener(e -> {
                    view.showImage();
                    infoLabel.setText("Original");
                }
        );
        Blur3Button.addActionListener(e -> {
                    view.BlurImage(3);
                    infoLabel.setText("Blur 3x3");
                }
        );
        Blur5Button.addActionListener(e -> {
                    view.BlurImage(5);
                    infoLabel.setText("Blur 5x5");
                }
        );
        Blur7Button.addActionListener(e -> {
                    view.BlurImage(7);
                    infoLabel.setText("Blur 7x7");
                }
        );
        Blur9Button.addActionListener(e -> {
                    view.BlurImage(9);
                    infoLabel.setText("Blur 9x9");
                }
        );
        Blur11Button.addActionListener(e -> {
                    view.BlurImage(11);
                    infoLabel.setText("Blur 11x11");
                }
        );
    }

    // This helper method adds all components to the content pane of the
    // JFrame object.  Specific layout of components is controlled here

    private void buildDisplay () {

        // Build first JPanel
        JPanel controlPanel = new JPanel();
        GridLayout grid = new GridLayout (1, 6);
        controlPanel.setLayout(grid);
        controlPanel.add(infoLabel);
        controlPanel.add(OriginalButton);
        controlPanel.add(Blur3Button);
        controlPanel.add(Blur5Button);
        controlPanel.add(Blur7Button);
        controlPanel.add(Blur9Button);
        controlPanel.add(Blur11Button);

        // Add panels and image data component to the JFrame
        Container c = this.getContentPane();
        c.add(view, BorderLayout.EAST);
        c.add(controlPanel, BorderLayout.SOUTH);
    }

    // This method reads an Image object from a file indicated by
    // the string provided as the parameter.  The image is converted
    // here to a BufferedImage object, and that new object is the returned
    // value of this method.
    // The mediatracker in this method can throw an exception

    private BufferedImage readImage() {

        Image image = Toolkit.getDefaultToolkit().getImage("boat.gif");
        MediaTracker tracker = new MediaTracker (new Component () {});
        tracker.addImage(image, 0);
        try { tracker.waitForID (0); }
        catch (InterruptedException ignored) {}
        BufferedImage bim = new BufferedImage
                (image.getWidth(this), image.getHeight(this), BufferedImage.TYPE_INT_RGB);
        Graphics2D big = bim.createGraphics();
        big.drawImage (image, 0, 0, this);
        return bim;
    }

    // The main method allocates the "window" and makes it visible.
    // The window closing event is handled by an anonymous inner (adapter)
    // class
    // Command line arguments are ignored.

    public static void main(String[] argv) {

        JFrame frame = new Exercise4();
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener (
                new WindowAdapter () {
                    public void windowClosing ( WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
    }
}
