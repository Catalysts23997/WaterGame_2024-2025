package org.firstinspires.ftc.teamcode.New.testing;

import static org.firstinspires.ftc.teamcode.New.UtilKt.linearSlideExtension;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class ExtensionTest {

    double maxExtension = 37.7716535;
    double minExtension= maxExtension - 33;

    /**
     * Testing extensions outside of the maximum extension - 42 inches
     */
    @Test
    public void TestExtensions(){

        Random random = new Random();
        double min = 42.0;
        double max = Double.MAX_VALUE;

        //make sure goal is coerced in range

        for(int i = 0; i<1000; i++){
            double randomValue = min + (random.nextDouble() * (max - min));
            double randomValue2 = min + (random.nextDouble() * (max - min));
            double claw =linearSlideExtension(randomValue).clawAngle;
            double claw2 =linearSlideExtension(randomValue2).clawAngle;
            Assert.assertEquals(claw,claw2,0.0);
        }

    }

    @Test
    public void Test(){

        double length = linearSlideExtension(11).slideLength;
        System.out.print(length);
        Assert.assertEquals(length, minExtension,0);

    }
}
