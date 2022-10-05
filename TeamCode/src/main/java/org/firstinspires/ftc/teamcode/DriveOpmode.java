package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Arm;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

@TeleOp()
public class DriveOpmode extends OpMode {
    MecanumDrive mecanumDrive = new MecanumDrive();
    Arm arm = new Arm();


    @Override
    public void init() {
        mecanumDrive.init(hardwareMap);
        arm.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad2.left_bumper) {
            arm.release();
        } else if (gamepad2.right_bumper) {
            arm.grip();
        } else {
            arm.stopGripper();
        }
        if (gamepad2.a) {
            arm.lower();
        } else if (gamepad2.b) {
            arm.raise();
        } else {
            arm.stopArm();
        }
        double rotateAmount;
        if(gamepad1.left_trigger > gamepad1.right_trigger){
            rotateAmount = -gamepad1.left_trigger;
        }
        else{
            rotateAmount = gamepad1.right_trigger;
        }

        mecanumDrive.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, rotateAmount);
    }
}