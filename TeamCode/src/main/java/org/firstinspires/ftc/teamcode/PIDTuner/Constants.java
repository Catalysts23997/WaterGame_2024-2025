package org.firstinspires.ftc.teamcode.PIDTuner;

import static java.lang.Math.PI;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.ArrayList;
import java.util.Arrays;

import ArmSpecific.ArmAngle;
import ArmSpecific.GravityModelConstants;
import ArmSpecific.Hardware;
import ArmSpecific.SystemConstants;
import ArmSpecific.pso4Arms;
import CommonUtilities.AngleRange;
import CommonUtilities.PIDFParams;
import CommonUtilities.PIDFcontroller;

@Config
public class Constants {

    //CONFIGURATIONS - do before running anything

    //todo Assign Your Motor Specs, direction, and CONFIG Name
    public final Hardware.Motor motor = Hardware.YellowJacket.RPM84; // for geared motors new Hardware.Motor(RPM,EncTicksPerRev,StallTorque,GearRatio);
    public final DcMotorSimple.Direction motorDirection = DcMotorSimple.Direction.REVERSE;
    public final String motorName = "shoulder";

    //todo provide the angles (in radians) that your arm can run to when testing (larger range the better)
    public static double stationaryAngle = Math.toRadians(3.0);
    public final AngleRange testingAngle = new AngleRange(stationaryAngle, PI/2);
    //todo provide angles (in radians) that present as obstacles to the system. If none set to null
    public final AngleRange obstacle = new AngleRange(-0.1 * PI, -0.3 * PI); // = null;

    //TESTING
    //todo change from FRICTION OPMODE results
    public static double frictionRPM = 83.28061527367797;
    public static double inertiaValue = 0.6934848493799478;


    //todo change from Gravity OPMODE & Desmos Graph
    public static double gravityA = -7.41624;
    public static double gravityB = 1.5607;
    public static double gravityK = 18.4051;


    // intake ground 44, drop it -11, deposit sub 120, drag down sub 110
    public static final ArrayList<AngleRange> angleRanges = new ArrayList<AngleRange>() {{
        add(new AngleRange(Math.toRadians(44), Math.toRadians(-73))); // hpdrop
        add(new AngleRange(Math.toRadians(44), Math.toRadians(164))); // specimen deposit prep
        add(new AngleRange(Math.toRadians(164), Math.toRadians(145))); // specimen deposit
        add(new AngleRange(Math.toRadians(-73), Math.toRadians(-115))); // specimen intake
        add(new AngleRange(Math.toRadians(-73), Math.toRadians(44))); // submersible intake
        add(new AngleRange(Math.toRadians(44), Math.toRadians(83))); //basketDeposit
    }};

    //todo LAST STEP - RUN the test in the TEST MODULE -> TeamCode/src/test/java/org.firstinspires.ftc.teamcode/FindConstants.java
    public final static ArrayList<PIDFParams> params = new ArrayList<>(Arrays.asList(
            new PIDFParams(2.9252676562028044, 0.16761731878419656, 0.30307595379293695, 0.34629155548360996),
            new PIDFParams(2.746700990507151, 0.06230741750046855, 0.24915423829040687, 0.15887057461759865),
            new PIDFParams(3.5, .2, 0.1, 0.26147307797320996),
            new PIDFParams(2.8793911439504765, 0.12309978749649493, 0.24546210947786626, 0.19121180023489823),
            new PIDFParams(2.397822872341661, 0.6577491404990158, 0.26586801954018013, 1.0110702104320892),
            new PIDFParams(2.8957738782082596, 1.533179501735688, 0.24382085193959094, 0.09573179996911822)
    ));

    SystemConstants constant = new SystemConstants(
            frictionRPM,
            motor,
            new GravityModelConstants(gravityA, gravityB, gravityK),
            inertiaValue
    );
    public pso4Arms sim = new pso4Arms(constant, angleRanges, 1.2, obstacle, 3.3);
    public static boolean gravityRecord = false;
    public static boolean gravityDisplayDataPoints = false;
    public static double gravityMotorPower = 0.0;
    public PIDFcontroller pidfController = new PIDFcontroller(
            new PIDFParams(0.0, 0.0, 0.0, 0.0),
            motor,
            obstacle,
            Math.toRadians(stationaryAngle)
    );

    //for custom usage and finding of arm angle ->
    public ArmAngle armAngle = new ArmAngle(motor,stationaryAngle);

}

