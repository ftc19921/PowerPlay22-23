package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    DcMotor armMotor;
    DcMotor forBar;
    CRServo gripperServo;
    CRServo virtical_Servo;

    double  GRIPPER_SPEED =0.4;

    public void init(HardwareMap hardwareMap){
        forBar=hardwareMap.get(DcMotor.class,"forbar");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        gripperServo= hardwareMap.get(CRServo.class,"gripperServo");
        virtical_Servo= hardwareMap.get(CRServo.class,"virtical");
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        forBar.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void foreBarUp(){
        forBar.setPower(0.6);

    }
    public void foreBarDown(){
        forBar.setPower(-0.6);

    }
    public void foreBarStop(){
        forBar.setPower(0);
    }
    public void raise(){
        armMotor.setPower(-0.8);

    }
    public void lower(){
        armMotor.setPower(0.8);

    }
    public void stopArm(){
        armMotor.setPower(0);
    }
    public void grip(){
        gripperServo.setPower(1);


    }
    public void release(){

        gripperServo.setPower(-1);
    }
    public void virticalgrip(){
        virtical_Servo.setPower(1);
    }
    public void virticlrelease (){
        virtical_Servo.setPower(-1);
    }
    public void stopGripper(){

        gripperServo.setPower(0);
    }
    public void stopvirticle(){
        virtical_Servo.setPower(0);
    }
}
