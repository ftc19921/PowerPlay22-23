package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

@TeleOp()
public class DriveOpmode extends OpMode {
    MecanumDrive mecanumDrive=new MecanumDrive();

    DcMotor armMotor;
    CRServo gripperServo;

    @Override
    public void init() {
        mecanumDrive.init(hardwareMap);
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        gripperServo= hardwareMap.get(CRServo.class,"gripperServo");
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    @Override
    public void loop() {

        double armMotorSpeed = 0;

        if(gamepad2.left_bumper) {

            gripperServo.setPower(0.4);

        }
        if(gamepad2.right_bumper){
            gripperServo.setPower(-0.4);

        }
        if (gamepad2.a) {
            armMotorSpeed = 1;
            armMotor.setPower(armMotorSpeed*0.02);
        }
        if (gamepad2.b) {
            armMotorSpeed = -1;
            armMotor.setPower(armMotorSpeed*0.02);
        }
        double rotateAmount;
        if(gamepad1.right_bumper){
            rotateAmount = 1;
        }
        else if(gamepad1.left_bumper){
            rotateAmount = -1;
        }else{
            rotateAmount=0;
        }
        mecanumDrive.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, rotateAmount);
        armMotor.setPower(armMotorSpeed);
        gripperServo.setPower(0);
    }
}