package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp()
public class OutreachTank extends OpMode {
    DcMotor motorL;
    DcMotor motorR;
    double joyLY;
    double joyRY;

    @Override
    public void init() {
        motorL = hardwareMap.get(DcMotor.class, "Motor1");
        motorR = hardwareMap.get(DcMotor.class, "Motor2");
    }
    @Override
    public void loop() {
        joyLY = gamepad1.left_stick_y;
        joyRY = -gamepad1.right_stick_y;
        telemetry.addData("Left stick y", joyLY);
        telemetry.addData("Right stick y", joyRY);
        motorL.setPower(joyLY);
        motorR.setPower(joyRY);
    }
}