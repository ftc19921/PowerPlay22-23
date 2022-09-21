package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "macanumDrive")
public class MacanumDrive extends OpMode {
    DcMotor frontRightMotor;
    DcMotor backRightMotor;
    DcMotor frontLeftMotor;
    DcMotor backLeftMotor;
    @Override
    public void init() {
        frontLeftMotor = hardwareMap.get(DcMotor.class,"motorFrontLeft");
        frontRightMotor = hardwareMap.get(DcMotor.class,"motorFrontRight");
        backRightMotor = hardwareMap.get(DcMotor.class,"motorBackRight");
        backLeftMotor = hardwareMap.get(DcMotor.class,"motorBackLeft");
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    @Override
    public void loop() {
        double controllerY = -gamepad1.left_stick_y;
        double controllerX= gamepad2.left_stick_x;
        double turn = gamepad1.right_stick_y;
        frontRightMotor.setPower(controllerX + controllerY + turn);
        frontLeftMotor.setPower(controllerX - controllerY - turn);
        backRightMotor.setPower(controllerX - controllerY+turn);
        backLeftMotor.setPower(controllerX + controllerY-turn);


    }
}
