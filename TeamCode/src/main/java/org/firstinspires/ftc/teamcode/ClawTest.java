package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
@TeleOp
public class ClawTest extends OpMode {

    CRServo Claw;
    @Override
    public void init() {
        Claw = hardwareMap.get(CRServo.class,"Claw");
    }

    @Override
    public void loop() {
        if (gamepad2.a) {
            Claw.setPower(1);
        } else if (gamepad2.b) {
            Claw.setPower(-1);
        } else {
            Claw.setPower(0);
        }

    }
}
