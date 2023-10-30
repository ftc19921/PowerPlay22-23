package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class andrewsIntakeMotor extends OpMode {
    double motorSpeed;
    boolean motorOn;
    DcMotor intakeMotor;

    @Override
    public void init() {
        intakeMotor = hardwareMap.get(DcMotor.class, "wheelyMotor");
    }

    @Override
    public void loop() {

        motorSpeed = gamepad1.right_trigger;

        if (motorSpeed > 0) {
            motorOn = true;
        } else {
            motorOn = false;
        }
        telemetry.addData("motorSpeed",motorSpeed);
        intakeMotor.setPower(motorSpeed);
    }
}
