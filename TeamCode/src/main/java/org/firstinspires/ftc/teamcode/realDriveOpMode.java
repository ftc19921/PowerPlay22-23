package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class realDriveOpMode extends OpMode {
    DcMotor FrontRightMotor;
    DcMotor BackRightMotor;
    DcMotor FrontLeftMotor;
    DcMotor BackLeftMotor;
    DcMotor ForBarMotor;
    DcMotor SlideMotor;
    @Override
    public void init() {
        FrontLeftMotor=hardwareMap.get(DcMotor.class,"Motorfrontright");
        FrontRightMotor=hardwareMap.get(DcMotor.class,"Motorbackright");
        BackLeftMotor=hardwareMap.get(DcMotor.class,"Motorfrontleft");
        BackRightMotor=hardwareMap.get(DcMotor.class,"Motorbackleft");
        ForBarMotor=hardwareMap.get(DcMotor.class,"forbar");
        SlideMotor=hardwareMap.get(DcMotor.class,"armMotor");
        FrontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ForBarMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        double forbarPosition = 0;
        double forward= gamepad1.left_stick_y;
        double sideways= gamepad1.right_stick_x;
        double rotate =0;
        FrontRightMotor.setPower(forward-sideways-rotate);
        FrontLeftMotor.setPower(forward+sideways+rotate);
        BackRightMotor.setPower(forward-sideways+rotate);
        BackLeftMotor.setPower(forward+sideways-rotate);
        if(gamepad1.right_trigger>gamepad1.left_trigger){rotate=gamepad1.right_trigger;}else{rotate=gamepad1.left_trigger;}



        if(gamepad1.dpad_up){
            ForBarMotor.setPower(0.6);
            forbarPosition=1;
        }else if(gamepad1.dpad_down){
            ForBarMotor.setPower(-0.4);
            forbarPosition=2;
        }else if(!gamepad1.dpad_down&&forbarPosition==2){
            ForBarMotor.setPower(0);
        }


    }
}
