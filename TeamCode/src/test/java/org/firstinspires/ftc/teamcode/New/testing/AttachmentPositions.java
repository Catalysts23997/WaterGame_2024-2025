package org.firstinspires.ftc.teamcode.New.testing;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.CollectionOfAttachments;
import org.firstinspires.ftc.teamcode.New.Slides;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class AttachmentPositions {

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
            double claw =Slides.INSTANCE.linearSlideExtension(randomValue).clawServoRot;
            double claw2 =Slides.INSTANCE.linearSlideExtension(randomValue2).clawServoRot;
            Assert.assertEquals(claw,claw2,0.0);
        }

    }

    @Test
    public void Test(){

        CollectionOfAttachments.slideDegree = 0.0;

        //make sure goal is coerced in range



        double length = Slides.INSTANCE.linearSlideExtension(11).slideLength;
        System.out.print(length);
        Assert.assertEquals(length, CollectionOfAttachments.minExtension,0);

    }
}
