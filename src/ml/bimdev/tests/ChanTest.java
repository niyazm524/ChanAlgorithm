package ml.bimdev.tests;

import ml.bimdev.chan.Chan;
import ml.bimdev.chan.Point;
import ml.bimdev.chan.Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.Serializable;
import java.util.List;

public class ChanTest implements Serializable {

    private List<Point> input;
    private List<Point> shouldBe;

    @Before
    public void prepareData() {
        input = Util.deserializePoints("assets/tests/input.txt");
        shouldBe = Util.deserializePoints("assets/tests/output.txt");
    }

    @Test
    public void testChan() {
        List<Point> toAssert = Chan.convexHull(input);
        Assert.assertEquals(toAssert, shouldBe);
    }
}
