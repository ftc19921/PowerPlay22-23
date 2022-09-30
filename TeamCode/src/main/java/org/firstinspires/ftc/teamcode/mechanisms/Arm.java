package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    DcMotor armMotor;
    CRServo gripperServo;
    double ARM_SPEED = 0.5;
    double  GRIPPER_SPEED =0.4;
    public void init(HardwareMap hardwareMap){
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        gripperServo= hardwareMap.get(CRServo.class,"gripperServo");
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void raise(){
        armMotor.setPower(-ARM_SPEED);

    }
    public void lower(){
        armMotor.setPower(ARM_SPEED);

    }
    public void stopArm(){
        armMotor.setPower(0);
    }
    public void grip(){
        gripperServo.setPower(-GRIPPER_SPEED);

    }
    public void release(){
        gripperServo.setPower(GRIPPER_SPEED);
    }
    public void stopGripper(){
        gripperServo.setPower(0);
    }
}
