package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {

    public DcMotorEx slideGrabber;
    public DcMotorEx Flinger;
    public DcMotorEx leftDrive;
    public DcMotorEx rightDrive;

    public Robot(HardwareMap hardwareMap) {

        Flinger = hardwareMap.get(DcMotorEx.class, "Flinger");
        slideGrabber = hardwareMap.get(DcMotorEx.class, "slideGrabber");
        leftDrive = hardwareMap.get(DcMotorEx.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotorEx.class, "rightDrive");
        leftDrive.setDirection(DcMotorEx.Direction.REVERSE);
        rightDrive.setDirection(DcMotorEx.Direction.FORWARD);



    }



}
