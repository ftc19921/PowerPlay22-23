package org.firstinspires.ftc.teamcode;

import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class MacanumDrive extends OpMode {
    double forward;
    double sideways;
    double rotate;
    double ForbarPosition;
    double speed;

    DcMotor FRM;
    DcMotor FLM;
    DcMotor BRM;
    DcMotor BLM;
    DcMotor ForBarMotor;
    DcMotor SlideMotor;
    TouchSensor touchSensor;
    TouchSensor touchSensor2;
    CRServo virticleServo;
    CRServo horizontalServo;
    @Override
    public void init() {
        FRM=hardwareMap.get(DcMotor.class,"motorFrontRight");
        BRM=hardwareMap.get(DcMotor.class,"motorBackRight");
        FLM=hardwareMap.get(DcMotor.class,"motorFrontLeft");
        BLM=hardwareMap.get(DcMotor.class,"motorBackLeft");
        ForBarMotor=hardwareMap.get(DcMotor.class,"forBar");
        SlideMotor=hardwareMap.get(DcMotor.class,"armMotor");
        virticleServo=hardwareMap.get(CRServo.class,"virtical");
        horizontalServo=hardwareMap.get(CRServo.class,"gripperServo");
        touchSensor=hardwareMap.get(TouchSensor.class,"touchsensor");
        touchSensor2=hardwareMap.get(TouchSensor.class,"touchsensor2");
        FRM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        SlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ForBarMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    @Override
    public void loop() {
        telemetry.addData("speed",speed);

        forward =gamepad1.left_stick_y;
        sideways=gamepad1.right_stick_x;
        FRM.setPower((forward - sideways + rotate)*speed);
        FLM.setPower((-forward - sideways + rotate)*speed);
        BRM.setPower((forward + sideways + rotate)*speed);
        BLM.setPower((-forward + sideways + rotate)*speed);

        if (gamepad1.right_trigger>gamepad1.left_trigger){
            rotate=-gamepad1.right_trigger*speed;
        }else{
            rotate=gamepad1.left_trigger*speed;
        }
        if(gamepad2.y){
            ForBarMotor.setPower(0.6);
            ForbarPosition=2;
        }else if(gamepad2.a){
            ForBarMotor.setPower(-0.3);
            ForbarPosition=1;
        }else if(!gamepad2.a&& ForbarPosition==1) {
            ForBarMotor.setPower(0);
        }
        if (!gamepad2.y&&ForbarPosition==2){
            ForBarMotor.setPower(0.3);
        }
        if(gamepad2.dpad_down&&!touchSensor.isPressed()){
            SlideMotor.setPower(1);
        }else if(gamepad2.dpad_up&&!touchSensor2.isPressed()){
            SlideMotor.setPower(-1);
        }else if(touchSensor.isPressed()){
            SlideMotor.setPower(-0.3);
        }else{
            SlideMotor.setPower(0);
        }
        telemetry.addData("pressed",touchSensor.isPressed());
        telemetry.addData("pressed2",touchSensor2.isPressed());
        //Servos
        if(gamepad2.right_trigger>0){
            virticleServo.setPower(1);
            horizontalServo.setPower(-1);
        }else if(gamepad2.left_trigger>0){
            virticleServo.setPower(-1);
            horizontalServo.setPower(1);
        }else{
            virticleServo.setPower(0);
            horizontalServo.setPower(0);

        }


        if(gamepad1.right_bumper){
            speed=0.5;
        }else if(gamepad1.left_bumper){
            speed=2;
        }else{
            speed=1;

        }








    }
}
