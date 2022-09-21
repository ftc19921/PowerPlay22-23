package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "EncoderTest")
public class EncoderTest extends OpMode {
    DcMotor EncoderMotor;
    DcMotor EncoderMotor2;
    DcMotor EncoderMotor3;
    DcMotor EncoderMotor4;
    @Override
    public void init() {
        EncoderMotor = hardwareMap.get(DcMotor.class,"motorFrontRight");
        EncoderMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        EncoderMotor2 = hardwareMap.get(DcMotor.class,"motorFrontLeft");
        EncoderMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        EncoderMotor3 = hardwareMap.get(DcMotor.class,"motorBackRight");
        EncoderMotor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        EncoderMotor4 = hardwareMap.get(DcMotor.class,"motorBackLeft");
        EncoderMotor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    @Override
    public void loop() {

        telemetry.addData("motor_position" ,EncoderMotor.getCurrentPosition() );
        telemetry.addData("motor2_position" ,EncoderMotor2.getCurrentPosition() );
        telemetry.addData("motor3_position" ,EncoderMotor3.getCurrentPosition() );
        telemetry.addData("motor4_position" ,EncoderMotor4.getCurrentPosition() );

        EncoderMotor.setPower(1);


        EncoderMotor2.setPower(1);



        EncoderMotor3.setPower(1);



        EncoderMotor4.setPower(1);




    }
}
