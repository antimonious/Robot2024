package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {

    public Servo Catapult;
    public DcMotorEx Flinger;

    public Robot(HardwareMap hardwareMap) {

        Catapult = hardwareMap.get(Servo.class, "Catapult");
        Flinger = hardwareMap.get(DcMotorEx.class, "Flinger");
        Flinger.setDirection(DcMotorSimple.Direction.REVERSE);

    }



}
