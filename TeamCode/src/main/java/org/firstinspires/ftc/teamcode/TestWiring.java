package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.Arm;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

@TeleOp(name = "TestWiring")
public class TestWiring extends OpMode {
    MecanumDrive mecDrive = new MecanumDrive();
        Arm arm =new Arm();

    @Override
    public void init() {
        mecDrive.init(hardwareMap);
        arm.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            mecDrive.setMotorSpeeds(0,0.3,0,0,telemetry);

        }else if(gamepad1.b){
            mecDrive.setMotorSpeeds(0.3,0,0,0,telemetry);
        }else if(gamepad1.y){
            mecDrive.setMotorSpeeds(0,0,0.3,0,telemetry);

        }else if(gamepad1.x){
            mecDrive.setMotorSpeeds(0,0,0,0.3,telemetry);

        }else{
            mecDrive.setMotorSpeeds(0,0,0,0,telemetry);
        }
        if(gamepad1.dpad_up){
            arm.raise();

        }else if(gamepad1.dpad_down){
            arm.lower();
        }else{
            arm.stopArm();
        }
        if(gamepad1.dpad_left){
            arm.release();
        }else if(gamepad1.dpad_right){
            arm.grip();
        }else{
            arm.stopGripper();
        }


        telemetry.addLine("A = front right");
        telemetry.addLine("b = front left");
        telemetry.addLine("y = back left");
        telemetry.addLine("x = back right");
        telemetry.addLine("left/right = gripper");
        telemetry.addLine("up/down = arm");
    }
}
