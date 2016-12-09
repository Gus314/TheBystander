package test.unit.graphs.rules;

import main.enums.Colour;
import main.graphs.faces.interfaces.IFace;
import main.graphs.faces.interfaces.ISquareFace;
import main.graphs.interfaces.IArea;
import main.graphs.rules.SquareFacesRule;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Gus on 09/12/2016.
 */
public class SquareFacesRuleTest {
    private Collection<IArea> areas = new ArrayList<IArea>();
    private SquareFacesRule squareFacesRule = new SquareFacesRule(); // Object should have no state, it is only an object to allow implementing interfaces.
    private IArea area = mock(IArea.class);
    private Collection<IFace> squareFaces = new ArrayList<IFace>();

    @Before
    public void setUp() {
        when(area.getFaces()).thenReturn(squareFaces);
        squareFaces.clear();
        areas.clear();
        areas.add(area);
    }

    @Test
    public void onlyOneThenPass() throws Exception {
        ISquareFace blackFace = mock(ISquareFace.class);
        when(blackFace.getColour()).thenReturn(Colour.BLACK);
        squareFaces.add(blackFace);

        int expected = 0;
        int actual = squareFacesRule.ruleFailures(areas, null, null);
        assertEquals(expected, actual);
    }


    @Test
    public void allHaveSameColourThenPass() throws Exception {
        ISquareFace blackFace = mock(ISquareFace.class);
        when(blackFace.getColour()).thenReturn(Colour.BLACK);
        squareFaces.add(blackFace);
        ISquareFace blackFace2 = mock(ISquareFace.class);
        when(blackFace2.getColour()).thenReturn(Colour.BLACK);
        squareFaces.add(blackFace2);

        int expected = 0;
        int actual = squareFacesRule.ruleFailures(areas, null, null);
        assertEquals(expected, actual);
    }

    @Test
    public void twoHaveDifferentColourThenTwoFail() throws Exception {
        ISquareFace blackFace = mock(ISquareFace.class);
        when(blackFace.getColour()).thenReturn(Colour.BLACK);
        squareFaces.add(blackFace);

        ISquareFace whiteFace = mock(ISquareFace.class);
        when(whiteFace.getColour()).thenReturn(Colour.WHITE);
        squareFaces.add(whiteFace);

        int expected = 2;
        int actual = squareFacesRule.ruleFailures(areas, null, null);
        assertEquals(expected, actual);
    }

    @Test
    public void threeHaveDifferentColourThenThreeFail() throws Exception {
        ISquareFace blackFace = mock(ISquareFace.class);
        when(blackFace.getColour()).thenReturn(Colour.BLACK);
        squareFaces.add(blackFace);

        ISquareFace whiteFace = mock(ISquareFace.class);
        when(whiteFace.getColour()).thenReturn(Colour.WHITE);
        squareFaces.add(whiteFace);

        ISquareFace blueFace = mock(ISquareFace.class);
        when(blueFace.getColour()).thenReturn(Colour.BLUE);
        squareFaces.add(blueFace);

        int expected = 3;
        int actual = squareFacesRule.ruleFailures(areas, null, null);
        assertEquals(expected, actual);
    }

    @Test
    public void noFacesThenPass() {
        int expected = 0;
        int actual = squareFacesRule.ruleFailures(areas, null, null);
        assertEquals(expected, actual);
    }

}