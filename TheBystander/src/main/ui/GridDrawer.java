package main.ui;

import main.graphs.faces.Face;
import main.graphs.faces.interfaces.IFace;
import main.graphs.grids.IGrid;
import main.ui.Interfaces.IGridDrawer;
import main.ui.Interfaces.ObjectToImageMapper;

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
    private ImageIcon exitRight;

    private IGrid grid;


    public GridDrawer(IGrid grid) {
        edgeVerticalBlank = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "edge_vertical_blank.png");
        edgeHorizontalBlank = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "edge_horizontal_blank.png");
        edgeMiddleBlank = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "edge_middle_blank.png");
        faceBlank = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "face_blank.png");
        exitRight = new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + "exit_right.png");

        this.grid = grid;
    }

    public void drawGrid()  {
        final JFrame frame = new JFrame("Witness Bystander");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        Collection<IFace> specialFaces = getSpecialFaces();

        int xPos = 0;
        int yPos = 0;

        //TODO pull this from grid object
        int columns = 7;
        int rows = 7;

        //DRAW FIRST HORIZONTAL EDGE AT TOP OF GRID
        drawFullHorizontalEdge(yPos, columns, panel, c);

        yPos++;
        //DRAWS FACES AND VERTICAL EDGES
        for (int row = 0; row < rows; row++) {
            //DRAWS LEFTMOST EDGE
            xPos = 0;
            addImageToPanel(this.edgeVerticalBlank, xPos, yPos, panel, c);
            xPos++;
            for (int column = 0; column < columns; column++) {
                // DRAWS FACE RIGHT EDGE, FACE, RIGHT EDGE ETC UNTIL END OF ROW
                IFace specialFace = getSpecialFaceAtVertex(specialFaces, column, row);
                if (specialFace != null) {
                    addImageToPanel(ObjectToImageMapper.getImageForFace(specialFace), xPos, yPos, panel, c);
                }
                else {
                    addImageToPanel(this.faceBlank, xPos, yPos, panel, c);
                }
                System.out.println("FACE POSITION IS (" + column + "," + row + ")");
                xPos++;
                addImageToPanel(this.edgeVerticalBlank, xPos, yPos, panel, c);
                xPos++;
            }
            //DRAWS LOWER HORIZONTAL EDGE
            yPos++;
            drawFullHorizontalEdge(yPos, columns, panel, c);
            addImageToPanel(this.exitRight, xPos, yPos, panel, c);
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

    private Collection<IFace> getSpecialFaces() {
        Collection<IFace> faces = new ArrayList<IFace>();
        this.grid.getFaces().stream().forEach( face ->{
            if (!face.getClass().getSimpleName().equals(Face.class.getSimpleName())) {
                faces.add(face);
            }
        });
        return faces;
    }

    private IFace getSpecialFaceAtVertex(Collection<IFace> faces, int xPos, int yPos) {
        for (IFace face : faces) {
            if (face.getBottomLeftVertex().getColumn() == xPos && face.getBottomLeftVertex().getRow() == yPos) {
                return face;
            }
        }
        return null;
    }
}
