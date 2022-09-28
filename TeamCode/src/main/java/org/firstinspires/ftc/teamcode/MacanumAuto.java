package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous
public class MacanumAuto extends OpMode {
    DcMotor Rightfrontmotor;
    DcMotor Rightbackmotor;
    DcMotor Leftfrontmotor;
    DcMotor Leftbackmotor;
    ColorSensor colorSensor;
    public void init() {


        Rightfrontmotor=hardwareMap.get(DcMotor.class,"motorFrontRight");
        Rightbackmotor=hardwareMap.get(DcMotor.class,"motorBackRight");
        Leftfrontmotor=hardwareMap.get(DcMotor.class,"motorFrontLeft");
        Leftbackmotor=hardwareMap.get(DcMotor.class,"motorBackLeft");
        colorSensor=hardwareMap.get(ColorSensor.class, "color");

        Rightfrontmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Rightbackmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Leftfrontmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Leftbackmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void loop() {
        telemetry.addData("Green",colorSensor.green());

        Rightfrontmotor.setPower(0);

        if(colorSensor.blue()>4700) {
            Leftfrontmotor.setPower(1);
        }else if(colorSensor.red()>4700){
            Rightbackmotor.setPower(1);
        }else{
            Rightfrontmotor.setPower(1);
        }


    }
}
