package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Gripper_test extends OpMode {
    CRServo Horizontal;
    CRServo Vertical;
    DcMotor Slide;
    @Override
    public void init () {
        Horizontal=hardwareMap.get(CRServo.class,"Horizontal");
        Vertical=hardwareMap.get(CRServo.class,"Vertical");
        Slide=hardwareMap.get(DcMotor.class,"Slide");


    }
    @Override
    public void loop () {
        if (gamepad1.a) {
            Horizontal.setPower(1);
            Vertical.setPower(1);
        }
        if (gamepad1.b) {
            Horizontal.setPower(-1);
            Vertical.setPower(-1);
    }
        if (gamepad1.dpad_up) {
            Slide.setPower(0.4);

        }
        if (gamepad1.dpad_down) {
            Slide.setPower(-0.4);
        }

}
    }
