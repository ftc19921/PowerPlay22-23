package org.firstinspires.ftc.teamcode.ftc1921;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Showname extends OpMode {
    DcMotor FlightMotor;
    @Override
    public void init() {
    telemetry.addData( "Programmer", "Mark");

    }

    @Override
    public void loop() {
        telemetry.addData("Gamepad X", gamepad1.left_stick_x);
        telemetry.addData("Gamepad y", gamepad1.left_stick_y);
    }
}
