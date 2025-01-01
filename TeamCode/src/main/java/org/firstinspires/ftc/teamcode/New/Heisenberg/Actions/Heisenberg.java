package org.firstinspires.ftc.teamcode.New.Heisenberg.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.MoveArmToPoint;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.AttachmentPositions;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ClawRotater;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ColorSensor;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Linkage;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.SystemAngles;
import org.firstinspires.ftc.teamcode.New.SlidesEncoderConv;

public class Heisenberg {
    ArmServos armServos;
    LinearSlides linearSlides;
    Linkage linkage;
    Claw claw;
    ClawRotater clawRotater;
    ColorSensor colorSensor;

    public final double circumferenceOfSpool = 24*Math.PI;
    AttachmentPositions attachmentPositons;
    SlidesEncoderConv conv = new SlidesEncoderConv(circumferenceOfSpool);

    public void update() {
        armServos.update(attachmentPositons.armAngle, attachmentPositons.clawAngle);
        linearSlides.update();
        linkage.update(attachmentPositons.linkageDegree);
        claw.update();
        clawRotater.update();
    }

    public void update(double leftStickY) {
        armServos.update(attachmentPositons.armAngle, attachmentPositons.clawAngle);
        linearSlides.update();
        linkage.update(attachmentPositons.linkageDegree);
        claw.update();
        clawRotater.update(leftStickY);
    }

    public Heisenberg(HardwareMap hardwareMap) {
        armServos = new ArmServos(hardwareMap);
        linearSlides = new LinearSlides(hardwareMap);
        linkage = new Linkage(hardwareMap);
        claw = new Claw(hardwareMap);
        clawRotater = new ClawRotater(hardwareMap);
        colorSensor = new ColorSensor(hardwareMap);

    }

    public Action template = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            linearSlides.setState(LinearSlides.State.CLIP);
            attachmentPositons = MoveArmToPoint.INSTANCE.moveArmToPoint(10.0, 10.0, Math.PI/4, conv.toIn(linearSlides.getState().getEncoder()));
            armServos.armState = ArmServos.ArmState.INPUT;
            linkage.linkageState = Linkage.LinkageState.INPUT;
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotater.state = ClawRotater.State.HalfWay;
            return false;
        }
    };

}