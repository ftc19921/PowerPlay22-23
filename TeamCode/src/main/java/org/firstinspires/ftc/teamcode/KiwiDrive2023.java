package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class KiwiDrive2023 extends OpMode {
    DcMotor F;
    DcMotor BL;
    DcMotor BR;
    DcMotor Arm;
    Servo Claw;
    double ArmUp;
    double BRPower;
    double BLPower;
    double FPower;
    double JoyY;
    double JoyX;
    double rJoyX;
    double maxPower;


    public void init() {
        Arm =  hardwareMap.get(DcMotor.class,"ArmMotor");
        Claw = hardwareMap.get(Servo.class,"Claw");
        F = hardwareMap.get(DcMotor.class, "Motor3");
        BL = hardwareMap.get(DcMotor.class, "Motor2");
        BR = hardwareMap.get(DcMotor.class, "Motor1");


    }

    @Override
    public void loop() {
        rJoyX = gamepad1.right_stick_x;
        JoyY = -gamepad1.left_stick_y;
        JoyX = gamepad1.left_stick_x;
        FPower = -JoyX;
        BRPower = JoyX / 2;
        BLPower = JoyX / 2;
        JoyY = Math.sqrt((3) / 2) * gamepad1.left_stick_y;
        BRPower += JoyY;
        BLPower += -JoyY;
        FPower += rJoyX;
        BRPower += rJoyX;
        BLPower += rJoyX;


        if (Math.abs(BRPower) > 1 || Math.abs(FPower) > 1 || Math.abs(BLPower) > 1) {
            maxPower = findAbsoluteMax(FPower, BRPower, BLPower);
            FPower /= maxPower;
            BLPower /= maxPower;
            BRPower /= maxPower;

        }
        F.setPower(FPower);
        BR.setPower(BRPower);
        BL.setPower(BLPower);
        telemetry.addData("BRpower", BRPower);
        telemetry.addData("BLpower", BLPower);
        telemetry.addData("Fpower", FPower);

        if(gamepad2.left_trigger>gamepad2.right_trigger){
            Arm.setPower(1);
            ArmUp=2;
        }
        if(gamepad2.right_trigger>gamepad2.left_trigger){
            Arm.setPower(-1);
            ArmUp=1;
        }else if(ArmUp==1){
            Arm.setPower(1);
        }



}

    double findAbsoluteMax(double power1,double power2,double power3){


        double max;
        max=Math.max(Math.abs(power1),Math.abs(power2));
        max=Math.max(max, Math.abs(power3));
        return max;

    }







}
