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
        Rightfrontmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Rightbackmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Leftfrontmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Leftbackmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Rightfrontmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Rightbackmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Leftfrontmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Leftbackmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }


    public void loop() {


        telemetry.addData("rightbackposition" ,Rightbackmotor.getCurrentPosition());
        telemetry.addData("rightfrontposition" ,Rightfrontmotor.getCurrentPosition());
        telemetry.addData("leftbackposition" ,Leftbackmotor.getCurrentPosition());
        telemetry.addData("leftfrontposition" ,Leftfrontmotor.getCurrentPosition());
        telemetry.addData("red",colorSensor.red());
        telemetry.addData("blue",colorSensor.blue());



        if(colorSensor.red()<1000 && colorSensor.blue()<1000&&Leftbackmotor.getCurrentPosition()<1000) {
            Rightfrontmotor.setPower(-1);
            Leftfrontmotor.setPower(1);
            Rightbackmotor.setPower(-1);
            Leftbackmotor.setPower(1);
        }
        if(colorSensor.red()>1000&&Leftbackmotor.getCurrentPosition()<10000){
            Rightfrontmotor.setPower(1);
            Leftfrontmotor.setPower(1);
            Rightbackmotor.setPower(-1);
            Leftbackmotor.setPower(-1);


        }else if(colorSensor.blue()>1000){
            Rightfrontmotor.setPower(-1);
            Leftfrontmotor.setPower(-1);
            Rightbackmotor.setPower(1);
            Leftbackmotor.setPower(1);

        }
        if(Leftbackmotor.getCurrentPosition()>10000){
            Rightfrontmotor.setPower(0);
            Leftfrontmotor.setPower(0);
            Rightbackmotor.setPower(0);
            Leftbackmotor.setPower(0);
        }




    }
}
