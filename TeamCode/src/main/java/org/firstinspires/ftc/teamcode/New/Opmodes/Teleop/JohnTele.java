package org.firstinspires.ftc.teamcode.New.Opmodes.Teleop;

import android.media.tv.TableRequest;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.New.Actions.Attachments;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.Intake;

import java.util.ArrayList;

@TeleOp(name = "Tele", group = "Linear OpMode")
public class JohnTele extends LinearOpMode {
    //note: All gamepad inputs are not final, they are placeholders for the actual input or other booleans

    ElapsedTime elapsedTime = new ElapsedTime();

    Attachments attachments = new Attachments(hardwareMap);


    FtcDashboard dashboard = FtcDashboard.getInstance();
    TelemetryPacket packet;
    ArrayList<Action> runningActions = new ArrayList<>();

    boolean Intaking = false;
    boolean Depositing = false;

    @Override
    public void runOpMode() {
        attachments.verticalSlides.resetValue = 0;

        waitForStart();

        elapsedTime.reset();

        while (opModeIsActive()) {

            //actions:

            if (gamepad2.y && !Depositing) {
                runningActions.add(attachments.manualDeposit());
                Depositing = true;
            }
            if (gamepad2.a && Depositing) {
                Depositing = false;
            }

            if (gamepad2.y && !Intaking) {
                runningActions.add(attachments.manualIntake());
                Intaking = true;
            }
            if (gamepad2.a && Intaking) {
                Intaking = false;
            }

            attachments.update(gamepad2);

            ArrayList<Action> newActions = new ArrayList<>();

            runningActions.forEach((Action) -> {
                Action.preview(packet.fieldOverlay());
                if (Action.run(packet)) {
                    newActions.add(Action);
                }
            });

            runningActions = newActions;

            dashboard.sendTelemetryPacket(packet);




            telemetry.addData("rightEncoder", attachments.verticalSlides.rightSlide.rightEncoder);
            telemetry.addData("leftEncoder", attachments.verticalSlides.leftSlide.leftEncoder);
            telemetry.update();

        }
    }
}


    enum RobotState {
        HANG,
        BASKET,
        CLIP,
        SUBMERSIBLE,
        GROUND,
        STATIONARY
    }


    /*
    OLD update system, too long
    private void update(){
        switch(state){
            case HANG:
                verticalSlides.state = LinearSlides.State.HANG;
                horizontalSlides.state = LinearSlides.State.HANG;
                intake.intakeState = Intake.IntakeState.STOPPED;
                intake.flipperState = Intake.FlipperState.STATIONARY;
                bucket.state = Bucket.State.STATIONARY;
                verticalTargets[0] += (int) -gamepad2.left_stick_y;

                break;
            case BASKET:
                verticalSlides.state = LinearSlides.State.BASKET;
                horizontalSlides.state = LinearSlides.State.BASKET;
                intake.intakeState = Intake.IntakeState.STOPPED;
                intake.flipperState = Intake.FlipperState.STATIONARY;
                bucket.state = Bucket.State.STATIONARY;


                if (gamepad2.x) {
                    tranferring = true;
                    timeMarker = elapsedTime.seconds();
                }
                if (tranferring) {
                    bucket.state = Bucket.State.DROPPING;
                    if ((elapsedTime.seconds() - timeMarker) >= 0.2) {
                        state = RobotState.STATIONARY;
                        tranferring = false;
                    }
                }
                break;
            case CLIP:
                verticalSlides.state = LinearSlides.State.CLIP;
                horizontalSlides.state = LinearSlides.State.CLIP;
                intake.intakeState = Intake.IntakeState.STOPPED;
                intake.flipperState = Intake.FlipperState.STATIONARY;
                bucket.state = Bucket.State.STATIONARY;
                break;
            case SUBMERSIBLE:
                verticalSlides.state = LinearSlides.State.SUBMERSIBLE;
                horizontalSlides.state = LinearSlides.State.SUBMERSIBLE;
                horizontalTargets[3] = (int) -gamepad2.right_stick_y;
                intake.intakeState = Intake.IntakeState.IN;
                intake.flipperState = Intake.FlipperState.COLLECTING;
                bucket.state = Bucket.State.STATIONARY;

                //would put if color sensor sees the opposite alliance color
                if(gamepad2.y && !tranferring){
                    intake.intakeState = Intake.IntakeState.OUT;
                }

                //if color sensor sees the right color
                if (gamepad2.a){
                    tranferring = true;
                    timeMarker = elapsedTime.seconds();
                }

                if (tranferring) {
                    intake.flipperState = Intake.FlipperState.TRANSFERRING;
                    if ((elapsedTime.seconds() - timeMarker) >= 0.1) {
                        intake.intakeState = Intake.IntakeState.OUT;
                        if ((elapsedTime.seconds() - timeMarker) >= 0.2) {
                            state = RobotState.STATIONARY;
                            tranferring = false;
                        }
                    }
                }
                break;
            case GROUND:
                verticalSlides.state = LinearSlides.State.GROUND;
                horizontalSlides.state = LinearSlides.State.GROUND;
                horizontalTargets[4] = (int) -gamepad2.right_stick_y;
                intake.intakeState = Intake.IntakeState.IN;
                intake.flipperState = Intake.FlipperState.COLLECTING;
                bucket.state = Bucket.State.STATIONARY;

                //would put if color sensor sees the opposite alliance color
                if(gamepad2.y && !tranferring){
                    intake.intakeState = Intake.IntakeState.OUT;
                }

                //if color sensor sees the right color
                if (gamepad2.a){
                    tranferring = true;
                    timeMarker = elapsedTime.seconds();
                }

                if (tranferring) {
                    intake.flipperState = Intake.FlipperState.TRANSFERRING;
                    if ((elapsedTime.seconds() - timeMarker) >= 0.1) {
                        intake.intakeState = Intake.IntakeState.OUT;
                        if ((elapsedTime.seconds() - timeMarker) >= 0.2) {
                            state = RobotState.STATIONARY;
                            tranferring = false;
                        }
                    }
                }
                break;
            case STATIONARY:
                verticalSlides.state = LinearSlides.State.STATIONARY;
                horizontalSlides.state = LinearSlides.State.STATIONARY;
                intake.intakeState = Intake.IntakeState.STOPPED;
                intake.flipperState = Intake.FlipperState.STATIONARY;
                bucket.state = Bucket.State.STATIONARY;
                break;
        }

        verticalSlides.update(verticalTargets);
        horizontalSlides.update(horizontalTargets);
        intake.update();
        bucket.update();
        TelemetryUpdate();
    }

    private void TelemetryUpdate(){
        telemetry.addData("Robot State", state);
        telemetry.addData("Vertical Slides State", verticalSlides.state);
        telemetry.addData("Horizontal Slides State", horizontalSlides.state);
        telemetry.addData("Intake State", intake.intakeState);
        telemetry.addData("Intake Flipper State", intake.flipperState);
        telemetry.addData("Bucket State", bucket.state);

        telemetry.update();
    }

     */

