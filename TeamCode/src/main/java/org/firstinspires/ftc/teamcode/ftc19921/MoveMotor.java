package org.firstinspires.ftc.teamcode.ftc19921;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp()
public class MoveMotor extends OpMode {
    DcMotor flagMotor;
    Servo servo;

    @Override
    public void init() {
        telemetry.addData("Programmer", "alan");
        flagMotor = hardwareMap.get(DcMotor.class, "motor_motoring");
        servo = hardwareMap.get(Servo.class, "servo_serving");
    }

    @Override
    public void loop() {
        double real_left_stick_y = -gamepad1.left_stick_y / 2;
        telemetry.addData("Gamepad x", gamepad1.left_stick_x);
        telemetry.addData("Gamepad y", real_left_stick_y);

        if(gamepad1.a){
            real_left_stick_y = real_left_stick_y * 2;
        }
        flagMotor.setPower(real_left_stick_y);

        if(gamepad1.b){
            servo.setPosition(0.5);
        }
        else{
            servo.setPosition(0.0);
        }
    }
}
