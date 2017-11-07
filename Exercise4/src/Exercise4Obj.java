/* Name: John Miller
 * Date: 6 November 2017
 * Class: CS 335 - 001
 * Project: Exercise 4
 *
 * Starter code provided by Dr. Seales.
 */

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Exercise4Obj extends JLabel
{
    // instance variable to hold the buffered image
    private BufferedImage bim = null;
    private BufferedImage filteredBImage = null;

    //  tell the paint component method what to draw
    private boolean showFiltered=false;

    // 3x3 Blur Kernel
    private final float[] MATRIX3 =
            { 0.1f, 0.1f, 0.1f,
                    0.1f, 0.2f, 0.1f,
                    0.1f, 0.1f, 0.1f };

    // 5x5 Blur Kernel
    private final float[] MATRIX5 =
            { 0.004f, 0.015f,	0.024f,	0.015f,	0.004f,
                    0.015f, 0.060f,	0.095f,	0.060f,	0.015f,
                    0.024f, 0.095f,	0.150f,	0.095f,	0.024f,
                    0.015f, 0.060f,	0.095f,	0.060f,	0.015f,
                    0.004f, 0.015f,	0.024f,	0.015f,	0.004f };

    // 7x7 Blur Kernel
    private final float[] MATRIX7 =
            { 0.000036f, 0.000363f,	0.001446f,	0.002291f,	0.001446f,	0.000363f,	0.000036f,
                    0.000363f, 0.003676f,	0.014662f,	0.023226f,	0.014662f,	0.003676f,	0.000363f,
                    0.001446f, 0.014662f,	0.058488f,	0.092651f,	0.058488f,	0.014662f,	0.001446f,
                    0.002291f, 0.023226f,	0.092651f,	0.146768f,	0.092651f,	0.023226f,	0.002291f,
                    0.001446f, 0.014662f,	0.058488f,	0.092651f,	0.058488f,	0.014662f,	0.001446f,
                    0.000363f, 0.003676f,	0.014662f,	0.023226f,	0.014662f,	0.003676f,	0.000363f,
                    0.000036f, 0.000363f,	0.001446f,	0.002291f,	0.001446f,	0.000363f,	0.000036f };

    // 9x9 Blur Kernel
    private final float[] MATRIX9 =
            { 0f       , 0.000001f,	0.000014f,	0.000055f,	0.000088f,	0.000055f,	0.000014f,	0.000001f,	0f       ,
                    0.000001f, 0.000036f,	0.000362f,	0.001445f,	0.002289f,	0.001445f,	0.000362f,	0.000036f,	0.000001f,
                    0.000014f, 0.000362f,	0.003672f,	0.014648f,	0.023205f,	0.014648f,	0.003672f,	0.000362f,	0.000014f,
                    0.000055f, 0.001445f,	0.014648f,	0.058434f,	0.092566f,	0.058434f,	0.014648f,	0.001445f,	0.000055f,
                    0.000088f, 0.002289f,	0.023205f,	0.092566f,	0.146634f,	0.092566f,	0.023205f,	0.002289f,	0.000088f,
                    0.000055f, 0.001445f,	0.014648f,	0.058434f,	0.092566f,	0.058434f,	0.014648f,	0.001445f,	0.000055f,
                    0.000014f, 0.000362f,	0.003672f,	0.014648f,	0.023205f,	0.014648f,	0.003672f,	0.000362f,	0.000014f,
                    0.000001f, 0.000036f,	0.000362f,	0.001445f,	0.002289f,	0.001445f,	0.000362f,	0.000036f,	0.000001f,
                    0f       , 0.000001f,	0.000014f,	0.000055f,	0.000088f,	0.000055f,	0.000014f,	0.000001f,	0f        };

    // 11x11 Blur Kernel
    private final float[] MATRIX11 =
            { 0f       , 0f       ,	0f       ,	0f       ,	0.000001f,	0.000001f,	0.000001f,	0f       ,	0f       ,	0f       ,	0f       ,
                    0f       , 0f       ,	0.000001f,	0.000014f,	0.000055f,	0.000088f,	0.000055f,	0.000014f,	0.000001f,	0f       ,	0f       ,
                    0f       , 0.000001f,	0.000036f,	0.000362f,	0.001445f,	0.002289f,	0.001445f,	0.000362f,	0.000036f,	0.000001f,	0f       ,
                    0f       , 0.000014f,	0.000362f,	0.003672f,	0.014648f,	0.023204f,	0.014648f,	0.003672f,	0.000362f,	0.000014f,	0f       ,
                    0.000001f, 0.000055f,	0.001445f,	0.014648f,	0.058433f,	0.092564f,	0.058433f,	0.014648f,	0.001445f,	0.000055f,	0.000001f,
                    0.000001f, 0.000088f,	0.002289f,	0.023204f,	0.092564f,	0.146632f,	0.092564f,	0.023204f,	0.002289f,	0.000088f,	0.000001f,
                    0.000001f, 0.000055f,	0.001445f,	0.014648f,	0.058433f,	0.092564f,	0.058433f,	0.014648f,	0.001445f,	0.000055f,	0.000001f,
                    0f       , 0.000014f,	0.000362f,	0.003672f,	0.014648f,	0.023204f,	0.014648f,	0.003672f,	0.000362f,	0.000014f,	0f       ,
                    0f       , 0.000001f,	0.000036f,	0.000362f,	0.001445f,	0.002289f,	0.001445f,	0.000362f,	0.000036f,	0.000001f,	0f       ,
                    0f       , 0f       ,	0.000001f,	0.000014f,	0.000055f,	0.000088f,	0.000055f,	0.000014f,	0.000001f,	0f       ,	0f       ,
                    0f       , 0f       ,	0f       ,	0f       ,	0.000001f,	0.000001f,	0.000001f,	0f       ,	0f       ,	0f       ,	0f        };

    // This constructor stores a buffered image passed in as a parameter
    Exercise4Obj(BufferedImage img) {
        bim = img;
        filteredBImage = new BufferedImage(bim.getWidth(), bim.getHeight(), BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(bim.getWidth(), bim.getHeight()));

        this.repaint();
    }

    // This mutator changes the image by resetting what is stored
    // The input parameter img is the new image;  it gets stored as an
    //     instance variable
    void setImage(BufferedImage img) {
        if (img == null) return;
        bim = img;
        filteredBImage = new BufferedImage(bim.getWidth(), bim.getHeight(), BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(bim.getWidth(), bim.getHeight()));
        showFiltered=false;
        this.repaint();
    }

    //  apply the blur operator
    void BlurImage(int size) {
        if (bim == null) return;

        // 3x3 Kernel is default,
        Kernel kernel;// = new Kernel(0, 0, MATRIX0);

        // Check the kernel size and set it to matrix
        if      (size == 3)  { kernel = new Kernel(3, 3, MATRIX3)   ; }
        else if (size == 5)  { kernel = new Kernel(5, 5, MATRIX5)   ; }
        else if (size == 7)  { kernel = new Kernel(7, 7, MATRIX7)   ; }
        else if (size == 9)  { kernel = new Kernel(9, 9, MATRIX9)   ; }
        else                 { kernel = new Kernel(11, 11, MATRIX11); }

        ConvolveOp cop = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

        // make a copy of the buffered image
        BufferedImage newBImage = new BufferedImage(bim.getWidth(), bim.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D big = newBImage.createGraphics();
        big.drawImage (bim, 0, 0, null);

        // apply the filter the copied image
        // result goes to a filtered copy
        cop.filter(newBImage, filteredBImage);
        showFiltered=true;
        this.repaint();
    }

    //  show current image by a scheduled call to paint()
    void showImage() {
        if (bim == null) return;
        showFiltered=false;
        this.repaint();
    }

    //  get a graphics context and show either filtered image or
    //  regular image
    public void paintComponent(Graphics g) {
        Graphics2D big = (Graphics2D) g;
        if (showFiltered)
            big.drawImage(filteredBImage, 0, 0, this);
        else
            big.drawImage(bim, 0, 0, this);
    }
}

