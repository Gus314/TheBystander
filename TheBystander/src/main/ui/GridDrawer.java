package main.ui;

import main.graphs.grids.IGrid;

import javax.swing.*;
import java.awt.*;

/**
 * Created by James on 05/12/2016.
 */
public class GridDrawer {

    private final static String UI_RESOURCES_IMAGE_PATH_PREFIX = "TheBystander/src/resources/ui_resources/images/";

    public static void drawGrid(IGrid grid) {
        final JFrame frame = new JFrame("Witness Bystander");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        int xPos = 0;
        int yPos = 0;

        //TODO pull this from grid object
        int columns = 5;
        int rows = 5;

        drawHorizontalEdge(xPos, yPos, columns, panel, c);

        xPos = 0;
        yPos++;
        //DRAWS FACES AND VERTICAL EDGES
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                c.gridx = xPos;
                c.gridy = yPos;
                JLabel edgeLeft = new JLabel(new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "edge_vertical_blank.png"));
                panel.add(edgeLeft, c);
                xPos++;
                c.gridx = xPos;
                JLabel face = new JLabel(new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "face_blank.png"));
                panel.add(face, c);
                xPos++;
                c.gridx = xPos;
                JLabel edgeRight = new JLabel(new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX +  "edge_vertical_blank.png"));
                panel.add(edgeRight, c);
            }
            //DRAWS LOWER HORIZONTAL EDGE
            xPos = 0;
            yPos++;
            drawHorizontalEdge(xPos, yPos, columns, panel, c);
            xPos = 0;
            yPos++;
        }

        renderGrid(frame, panel);
    }

    private static void drawHorizontalEdge(int xPos, int yPos, int columns, JPanel panel, GridBagConstraints c) {
        for (int x = 0; x < columns; x++) {
            c.gridx = xPos;
            c.gridy = yPos;
            JLabel edgeMidLeft = new JLabel(new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX +  "edge_middle_blank.png"));
            panel.add(edgeMidLeft, c);

            xPos++;
            c.gridx = xPos;

            JLabel edgeTop = new JLabel(new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX +  "edge_horizontal_blank.png"));
            panel.add(edgeTop, c);

            xPos++;
            c.gridx = xPos;

            JLabel edgeMidRight = new JLabel(new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX +  "edge_middle_blank.png"));
            panel.add(edgeMidRight, c);
        }
    }

    private static void renderGrid(JFrame frame, JPanel panel) {
        frame.setContentPane(panel);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
