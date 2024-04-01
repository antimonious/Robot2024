package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Robot;

@TeleOp
public class Catapult extends LinearOpMode {

    public Robot robot;



    @Override
    public void runOpMode() throws InterruptedException {

        robot = new Robot(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {

            telemetry.addData("catapult", robot.Catapult.getPosition());
            telemetry.update();

            if (gamepad1.a) {
                robot.Catapult.setPosition(.5);
            }
            if (gamepad1.b) {
                robot.Catapult.setPosition(0);
            }
        }

    }
}
