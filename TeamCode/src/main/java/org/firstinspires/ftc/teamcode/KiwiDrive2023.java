package org.firstinspires.ftc.teamcode;

import android.os.Debug;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp

public class KiwiDrive2023 extends OpMode {
    DcMotor F;
    DcMotor BL;
    DcMotor BR;
    DcMotorEx Arm;
    CRServo Claw;

    double BRPower;
    double BLPower;
    double FPower;
    double JoyY;
    double JoyX;
    double rJoyX;
    double maxPower;
    int armPos;
    boolean isPressed;


    public void init() {
        Arm =  hardwareMap.get(DcMotorEx.class,"ArmMotor");
        Claw = hardwareMap.get(CRServo.class,"Claw");
        F = hardwareMap.get(DcMotor.class, "Motor3");
        BL = hardwareMap.get(DcMotor.class, "Motor2");
        BR = hardwareMap.get(DcMotor.class, "Motor1");
        Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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
        F.setPower(-FPower);
        BR.setPower(-BRPower);
        BL.setPower(-BLPower);
        telemetry.addData("BRpower", BRPower);
        telemetry.addData("BLpower", BLPower);
        telemetry.addData("Fpower", FPower);

        if(gamepad2.left_trigger>gamepad2.right_trigger&&armPos<2&&isPressed==false){
            armPos++;
        }else if(gamepad2.right_trigger>gamepad2.left_trigger&&armPos>0&&isPressed==false) {
            armPos--;
        }
        if(gamepad2.right_trigger==gamepad2.left_trigger){
            isPressed=false;
        }else{
            isPressed=true;
        }
        if(armPos==0){
            Arm.setVelocity(500);
            Arm.setTargetPosition(0);
            Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }else if(armPos==1) {
            Arm.setVelocity(500);
            Arm.setTargetPosition(400);
            Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

            telemetry.addData("arm position", Arm.getCurrentPosition());
            telemetry.addData("arm target position", Arm.getTargetPosition());
            if (gamepad2.a) {
                Claw.setPower(1);
            } else if (gamepad2.b) {
                Claw.setPower(-1);
            } else {
                Claw.setPower(0);
            }



}

    double findAbsoluteMax(double power1,double power2,double power3){


        double max;
        max=Math.max(Math.abs(power1),Math.abs(power2));
        max=Math.max(max, Math.abs(power3));
        return max;

    }







}
