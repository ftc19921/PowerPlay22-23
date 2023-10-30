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
public class MacanumDrive2023 extends OpMode {
    double forward;
    double sideways;
    double rotate;

    double speed;

    DcMotor FRM;
    DcMotor FLM;
    DcMotor BRM;
    DcMotor BLM;

    @Override
    public void init() {
        FRM = hardwareMap.get(DcMotor.class, "motorFrontRight");
        BRM = hardwareMap.get(DcMotor.class, "motorBackRight");
        FLM = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        BLM = hardwareMap.get(DcMotor.class, "motorBackLeft");
        FRM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    @Override
    public void loop() {
        telemetry.addData("speed", speed);

        forward = gamepad1.left_stick_y;
        sideways = gamepad1.right_stick_x;
        FRM.setPower((forward - sideways + rotate) * speed);
        FLM.setPower((-forward - sideways + rotate) * speed);
        BRM.setPower((forward + sideways + rotate) * speed);
        BLM.setPower((-forward + sideways + rotate) * speed);

        if (gamepad1.right_trigger > gamepad1.left_trigger) {
            rotate = -gamepad1.right_trigger * speed;
        } else {
            rotate = gamepad1.left_trigger * speed;
        }


    }

    //Servos

}















