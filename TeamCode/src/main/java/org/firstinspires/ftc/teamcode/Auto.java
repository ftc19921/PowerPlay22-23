package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Auto extends OpMode {
   DcMotor rightfrontmotor;
   DcMotor leftfrontmotor;
   DcMotor rightbackmotor;
   DcMotor leftbackmotor;
   DcMotor Armmotor;

    @Override
    public void init() {
        rightfrontmotor=hardwareMap.get(DcMotor.class,"motorFrontRight");
        leftfrontmotor=hardwareMap.get(DcMotor.class,"motorFrontLeft");
        rightbackmotor=hardwareMap.get(DcMotor.class,"motorBackRight");
        leftbackmotor=hardwareMap.get(DcMotor.class,"motorBackLeft");
        Armmotor=hardwareMap.get(DcMotor.class,"Arm");

        leftbackmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void loop() {

    }
}
