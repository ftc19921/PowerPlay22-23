package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

@TeleOp(name = "TestWiring")
public class TestWiring extends OpMode {
    MecanumDrive mecDrive = new MecanumDrive();

    @Override
    public void init() {
        mecDrive.init(hardwareMap);
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
        telemetry.addLine("A = front right");
        telemetry.addLine("b = front left");
        telemetry.addLine("y = back left");
        telemetry.addLine("x = back right");
    }
}
