package main.ui.Interfaces;

import main.graphs.faces.SquareFace;
import main.graphs.faces.interfaces.IFace;

import javax.swing.*;

/**
 * Created by James on 07/12/2016.
 */
public class ObjectToImageMapper {

    private final static String UI_RESOURCES_IMAGE_PATH_PREFIX = "TheBystander/src/resources/ui_resources/images/";

    public static ImageIcon getImageForFace(IFace face) {
        String imageFilename = "face_";
      if (face instanceof SquareFace) {
          imageFilename = imageFilename + "square_" + ((SquareFace) face).getColour().toString() + ".png";
      }
        return new ImageIcon(UI_RESOURCES_IMAGE_PATH_PREFIX + imageFilename);
    }
}
