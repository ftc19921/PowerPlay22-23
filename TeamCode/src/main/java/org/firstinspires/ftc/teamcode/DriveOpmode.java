package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.mechanisms.Arm;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

@TeleOp()
public class DriveOpmode extends OpMode {
    MecanumDrive mecanumDrive = new MecanumDrive();
    Arm arm = new Arm();
    TouchSensor touchSensor;
    double armup;


    @Override
    public void init() {
        mecanumDrive.init(hardwareMap);
        arm.init(hardwareMap);
        touchSensor = hardwareMap.touchSensor.get("TouchSensor");
    }

    @Override
    public void loop() {
        if (gamepad2.dpad_left){
            arm.virticlrelease();

        }else if(gamepad2.dpad_right){
            arm.virticalgrip();
        }else{
            arm.stopvirticle();
        }
        if(gamepad2.dpad_down){
            arm.foreBarDown();
            armup=1;
        }

        if(gamepad2.dpad_up){
            armup=2;
            arm.foreBarUp();
        }
        if(armup==1 && !gamepad2.dpad_down){
            arm.foreBarStop();
        }

        if (gamepad2.left_bumper) {
            arm.release();
        } else if (gamepad2.right_bumper) {
            arm.grip();
        } else {
            arm.stopGripper();
        }
        if (gamepad2.a) {
            arm.lower();
        } else if (gamepad2.b && !touchSensor.isPressed()) {
            arm.raise();
        } else {
            arm.stopArm();
        }
        telemetry.addData("Button" ,touchSensor.isPressed());






        mecanumDrive.drive(-gamepad1.left_stick_y, 0, gamepad1.left_stick_x);
    }
}