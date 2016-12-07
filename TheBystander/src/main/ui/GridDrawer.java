package main.ui;

import main.graphs.faces.Face;
import main.graphs.faces.interfaces.IFace;
import main.graphs.grids.IGrid;
import main.ui.Interfaces.IGridDrawer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by James on 05/12/2016.
 */
public class GridDrawer implements IGridDrawer {

    private final static String UI_RESOURCES_IMAGE_PATH_PREFIX = "TheBystander/src/resources/ui_resources/images/";

    private ImageIcon edgeVerticalBlank;
    private ImageIcon edgeHorizontalBlank;
    private ImageIcon edgeMiddleBlank;
    private ImageIcon faceBlank;
    private ImageIcon faceSquareBlack;
    private ImageIcon faceSquareWhite;
    private ImageIcon faceSquareRed;
    private ImageIcon faceSquareBlue;
    private ImageIcon faceSquareGreen;

    private IGrid grid;


    public GridDrawer(IGrid grid) {
        edgeVerticalBlank = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "edge_vertical_blank.png");
        edgeHorizontalBlank = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "edge_horizontal_blank.png");
        edgeMiddleBlank = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "edge_middle_blank.png");
        faceBlank = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "face_blank.png");
        faceSquareBlack = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "face_square_black.png");
        faceSquareWhite = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "face_square_white.png");
        faceSquareRed = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "face_square_red.png");
        faceSquareBlue = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "face_square_blue.png");
        faceSquareGreen = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "face_square_green.png");

        this.grid = grid;
    }

    public void drawGrid()  {
        final JFrame frame = new JFrame("Witness Bystander");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        Collection<IFace> facesOfInterest = getFacesofInterest();

        int xPos = 0;
        int yPos = 0;

        //TODO pull this from grid object
        int columns = 5;
        int rows = 5;

        //DRAW FIRST HORIZONTAL EDGE AT TOP OF GRID
        drawFullHorizontalEdge(yPos, columns, panel, c);

        yPos++;
        //DRAWS FACES AND VERTICAL EDGES
        for (int y = 0; y < rows; y++) {
            //DRAWS LEFTMOST EDGE
            xPos = 0;
            addImageToPanel(this.edgeVerticalBlank, xPos, yPos, panel, c);
            xPos++;
            for (int x = 0; x < columns; x++) {
                // DRAWS FACE RIGHT EDGE, FACE, RIGHT EDGE ETC UNTIL END OF ROW
                addImageToPanel(this.faceBlank, xPos, yPos, panel, c);
                System.out.println("FACE POSITION IS (" + x + "," + y + ")");
                xPos++;
                addImageToPanel(this.edgeVerticalBlank, xPos, yPos, panel, c);
                xPos++;
            }
            //DRAWS LOWER HORIZONTAL EDGE
            yPos++;
            drawFullHorizontalEdge(yPos, columns, panel, c);
            yPos++;
        }

        renderGrid(frame, panel);
    }

    private void drawFullHorizontalEdge(int yPos, int columns, JPanel panel, GridBagConstraints c) {
        int xPos = 0;
        for (int x = 0; x < columns; x++) {
            addImageToPanel(this.edgeMiddleBlank, xPos, yPos, panel, c);

            xPos++;
            addImageToPanel(this.edgeHorizontalBlank, xPos, yPos, panel, c);

            xPos++;
            addImageToPanel(this.edgeMiddleBlank, xPos, yPos, panel, c);
        }
    }

    private void addImageToPanel(ImageIcon image, int xPos, int yPos, JPanel panel, GridBagConstraints c) {
        c.gridx = xPos;
        c.gridy = yPos;
        JLabel edgeMidLeft = new JLabel(image);
        panel.add(edgeMidLeft, c);
    }

    private void renderGrid(JFrame frame, JPanel panel) {
        frame.setContentPane(panel);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private Collection<IFace> getFacesofInterest() {
        Collection<IFace> faces = new ArrayList<IFace>();
        this.grid.getFaces().stream().forEach( face ->{
            System.out.println(face.getClass().getSimpleName());
            System.out.println(Face.class.getSimpleName());
            if (!face.getClass().getSimpleName().equals(Face.class.getSimpleName())) {
                faces.add(face);
            }
        });
        return faces;
    }
}
