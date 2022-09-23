package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous
public class MacanumAuto extends OpMode {
    DcMotor Rightfrontmotor;
    DcMotor Rightbackmotor;
    DcMotor Leftfrontmotor;
    DcMotor Leftbackmotor;
    public void init() {


        Rightfrontmotor=hardwareMap.get(DcMotor.class,"motorFrontRight");
        Rightbackmotor=hardwareMap.get(DcMotor.class,"motorBackRight");
        Leftfrontmotor=hardwareMap.get(DcMotor.class,"motorFrontLeft");
        Leftbackmotor=hardwareMap.get(DcMotor.class,"motorBackLeft");

        Rightfrontmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Rightbackmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Leftfrontmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Leftbackmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void loop() {
        while (Leftfrontmotor.getCurrentPosition()<1000) {
            telemetry.addData("Encoderposition",Rightfrontmotor.getCurrentPosition());
            telemetry.addData("Encoderposition",Rightbackmotor.getCurrentPosition());
            telemetry.addData("Encoderposition",Leftfrontmotor.getCurrentPosition());
            telemetry.addData("Encoderposition",Leftbackmotor.getCurrentPosition());

            Rightfrontmotor.setPower(-1);
            Rightbackmotor.setPower(1);
            Leftfrontmotor.setPower(1);
            Leftbackmotor.setPower(-1);
        }

    }
}
