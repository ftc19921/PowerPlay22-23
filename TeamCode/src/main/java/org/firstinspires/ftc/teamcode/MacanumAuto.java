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
    DcMotor armMotor;
    ColorSensor colorSensor;
    double direction=0;

    public void init() {


        Rightfrontmotor=hardwareMap.get(DcMotor.class,"motorFrontRight");
        Rightbackmotor=hardwareMap.get(DcMotor.class,"motorBackRight");
        Leftfrontmotor=hardwareMap.get(DcMotor.class,"motorFrontLeft");
        Leftbackmotor=hardwareMap.get(DcMotor.class,"motorBackLeft");
        armMotor=hardwareMap.get(DcMotor.class,"armMotor");
        colorSensor=hardwareMap.get(ColorSensor.class, "color");
        Rightfrontmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Rightbackmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Leftfrontmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Leftbackmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Rightfrontmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Rightbackmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Leftfrontmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Leftbackmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }


    public void loop() {
        telemetry.addData("leftbackposition", -Leftbackmotor.getCurrentPosition());
        telemetry.addData("direction",direction);
        telemetry.addData("red", colorSensor.red());
        telemetry.addData("blue", colorSensor.blue());
        if(-Leftbackmotor.getCurrentPosition()<2000){
            armMotor.setPower(0.2);
            Rightfrontmotor.setPower(0.4);
            Rightbackmotor.setPower(0.4);
            Leftbackmotor.setPower(-0.4);
            Leftfrontmotor.setPower(-0.4);
        }else{
            armMotor.setPower(0);
            if (colorSensor.red() > 1000&&direction!=2) {
                direction = 1;
            }else if (colorSensor.blue() > 1000&&direction!=1) {
                direction = 2;
            } else {
                direction = 3;
            }
        }
    if(direction==1&&-Leftbackmotor.getCurrentPosition()>500){
        Rightfrontmotor.setPower(-1);
        Rightbackmotor.setPower(1);
        Leftbackmotor.setPower(1);
        Leftfrontmotor.setPower(-1);
    }else if(direction==2&&-Leftbackmotor.getCurrentPosition()<4000){
        Rightfrontmotor.setPower(1);
        Rightbackmotor.setPower(-1);
        Leftbackmotor.setPower(-1);
        Leftfrontmotor.setPower(1);
    }else{
        Rightfrontmotor.setPower(0);
        Rightbackmotor.setPower(0);
        Leftbackmotor.setPower(0);
        Leftfrontmotor.setPower(0);
    }



    }
}
