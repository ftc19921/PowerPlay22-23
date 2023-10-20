package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@TeleOp
public class TestAuto extends OpMode {
    DcMotor RFM;
    DcMotor RBM;
    DcMotor LFM;
    DcMotor LBM;
    DcMotor forbar;
    DcMotor lift;
    CRServo intake1;
    Gamepad armGamepad;

    Double A = 0.0;
    float B = 0.0f;
    Double C = 0.0;
    Double D = 0.0;
    TouchSensor topSensor;
    TouchSensor bottemSensor;



    @Override
    public void init() {

        topSensor=hardwareMap.get(TouchSensor.class,"touchsensor");
        bottemSensor=hardwareMap.get(TouchSensor.class,"touchsensor2");
        RFM = hardwareMap.get(DcMotor.class, "motorFrontRight");
        RBM = hardwareMap.get(DcMotor.class, "motorBackRight");
        LFM = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        LBM = hardwareMap.get(DcMotor.class, "motorBackLeft");
        lift = hardwareMap.get(DcMotor.class, "armMotor");
        forbar = hardwareMap.get(DcMotor.class, "forBar");
        intake1 = hardwareMap.get(CRServo.class, "gripperServo");
//        intake2 = hardwareMap.get(CRServo.class, "virtical");
        armGamepad=gamepad1;


        RFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RBM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LBM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RBM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LBM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }


    public void loop() {
        //this will run repeatedly


        //RFM = the motor (in this case its the RightFrontMotor)       .setPower(1); = the speed of that
        // motor 1=full power 0=no power -1=full reverse power

        //RFM.setPower(1);


        //RFM = the motor     .getCurrentPosition(); = the current position of the motor starts at 0
        // increases when it turns forward decreases when it turns backwards

        //RFM.getCurrentPosition();


        //RFM = the motor    .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); = resets the current position of the motor to 0
        // .setMode(DcMotor.RunMode.RUN_USING_ENCODER); = Use after resetting the current position

        //RFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //RFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


//        if (RFM.getCurrentPosition() > -1300 && A == 0.0) {
//            RFM.setPower(-1);
//            RBM.setPower(1);
//            LFM.setPower(-1);
//            LBM.setPower(1);
//        } else if (RFM.getCurrentPosition() <= -1300 && A == 0.0) {
//            A = 1.0;
//        } else {
//            RFM.setPower(1);
//            RBM.setPower(-1);
//            LFM.setPower(1);
//            LBM.setPower(-1);
//            if (RFM.getCurrentPosition() >= 1300) {
//                A = 0.0;
//            }
//        }
//        if(gamepad1.left_stick_x) {
        armGamepad = C == 1.0 ? gamepad2 : gamepad1;
        if(gamepad1.left_trigger > 0 || gamepad1.right_trigger > 0) {
            if (gamepad1.left_trigger > 0) {
                RFM.setPower(gamepad1.left_trigger);
                RBM.setPower(gamepad1.left_trigger);
                LFM.setPower(gamepad1.left_trigger);
                LBM.setPower(gamepad1.left_trigger);
            } else{
                RFM.setPower(-gamepad1.right_trigger);
                RBM.setPower(-gamepad1.right_trigger);
                LFM.setPower(-gamepad1.right_trigger);
                LBM.setPower(-gamepad1.right_trigger);
            }
        }else{
            RFM.setPower(-gamepad1.left_stick_x + gamepad1.left_stick_y);
            RBM.setPower(gamepad1.left_stick_x + gamepad1.left_stick_y);
            LFM.setPower(-gamepad1.left_stick_x + -gamepad1.left_stick_y);
            LBM.setPower(gamepad1.left_stick_x + -gamepad1.left_stick_y);
        }
        if (armGamepad.right_stick_y > 0) {
            B = armGamepad.right_stick_y;
        } else if (armGamepad.b) {
            B = 0.0f;
        }
        forbar.setPower(-B/2);
        lift.setPower((armGamepad.a && !topSensor.isPressed()? 1: 0) + (armGamepad.b ? -1 : 0));
        intake1.setPower((armGamepad.x ? 1 : 0) + (armGamepad.y ? -1 : 0));
        if (gamepad2.a && gamepad2.b) {
            C = 1.0;
        }
//        }
    }
}