package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
@Autonomous
public class ColorTest extends OpMode {
    ColorSensor colorSensor;
    @Override
    public void init() {
        colorSensor=hardwareMap.get(ColorSensor.class,"color");
    }

    @Override
    public void loop() {
        telemetry.addData("red",colorSensor.red());
        telemetry.addData("green",colorSensor.green());
        telemetry.addData("blue",colorSensor.blue());


    }
}
