package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Robot;

@TeleOp
public class Flinger extends LinearOpMode {

    public Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new Robot(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {

            if (gamepad1.a) {

                robot.Flinger.setPower(1);

            }
        }
    }
}
