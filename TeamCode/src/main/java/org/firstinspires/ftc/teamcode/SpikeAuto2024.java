package org.firstinspires.ftc.teamcode;

import static org.opencv.core.Core.inRange;

import android.graphics.MaskFilter;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.sun.tools.javac.resources.compiler;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
@Autonomous
public class SpikeAuto2024 extends OpMode {
    DcMotor F;
    DcMotor BL;
    DcMotor BR;
    DcMotor Arm;
    CRServo Claw;
    OpenCvWebcam webcam;

    Double coneColor=0.0;
    boolean isRedAlliance;

    double Phase;
    double ForwardPower;
    double SidewaysPower;
    double FPower;
    double BLPower;
    double BRPower;
    double XMovement;
    double YMovement;
    double ZMovement;
    double Rightcol;
    double leftcol;
    double midcol;

    float midlleMovement;

    public void init() {
        Arm = hardwareMap.get(DcMotor.class, "ArmMotor");
        Claw = hardwareMap.get(CRServo.class, "Claw");
        F = hardwareMap.get(DcMotor.class, "Motor3");
        BL = hardwareMap.get(DcMotor.class, "Motor2");
        BR = hardwareMap.get(DcMotor.class, "Motor1");

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam1"), cameraMonitorViewId);

        webcam.setPipeline(new Pipeline());
        webcam.setMillisecondsPermissionTimeout(2500);
        Arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        F.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Phase = 1;
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener(){


            @Override
                public void onOpened()
                {
                    telemetry.addLine("cam online");
                    webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                }
                @Override
                public void onError(int errorCode)
                {

                }
            });
        }

    public void loop() {


        ForwardPower = XMovement;
        SidewaysPower = Math.sqrt(3 / 2) * YMovement;
        FPower = SidewaysPower + ZMovement;
        BLPower = (SidewaysPower / 2) + ZMovement;
        BLPower += ForwardPower;
        BRPower = (SidewaysPower / 2) + ZMovement;
        BRPower += -ForwardPower;

        telemetry.addData("BRpower", BRPower);
        telemetry.addData("BLpower", BLPower);
        telemetry.addData("Fpower", FPower);

        if (Phase == 1) {
            if (BR.getCurrentPosition() < 1000) {
                YMovement = 1;
            } else {
                YMovement = 0;
                Phase = 2;
            }
        }
        if (Phase == 2) {
            if (BR.getCurrentPosition() < 500) {
                ZMovement = 1;
            } else {
                Phase = 3;
            }
        }
        if (Phase == 3) {
            Claw.setPower(1);
        } else {
            Claw.setPower(0);
        }

        if (midlleMovement == 1){
            if (BR.getCurrentPosition() >= 12000){
                ForwardPower = 0;
            }else{
                ForwardPower = 1;
            }
        }
    }


        class Pipeline extends OpenCvPipeline {

        Mat YCBCr = new Mat();

        Mat MiddleRedValue;
        Mat MiddleBlueValue;
        Mat RightRedValue;
        Mat RightBlueValue;
        Mat LeftRedValue;
        Mat LeftBlueValue;
        Mat finalBlue = new Mat();
        Mat finalRed = new Mat();
        Mat LeftfinalBlue = new Mat();
        Mat LeftfinalRed = new Mat();
        Mat RightfinalBlue = new Mat();
        Mat RightfinalRed = new Mat();

        double BlueAvgfin;
        double RedAvgfin;
        double RightBlueAvgfin;
        double RightRedAvgfin;
        double LeftBlueAvgfin;
        double LeftRedAvgfin;

        Mat outPut = new Mat();


        Scalar Middleblue = new Scalar(0.0, 0.0, 255.0);

        Scalar Middlered = new Scalar(255.0, 0.0, 0.0);
        Scalar Rightblue = new Scalar(0.0, 0.0, 255.0);

        Scalar Rightred = new Scalar(255.0, 0.0, 0.0);
        Scalar Leftblue = new Scalar(0.0, 0.0, 255.0);

        Scalar Leftred = new Scalar(255.0, 0.0, 0.0);



        @Override
        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input, YCBCr, Imgproc.COLOR_RGBA2RGB);

            Rect MiddleRectBlue = new Rect(115, 60, 45, 45);
            Rect MiddleRectRed = new Rect(115, 60, 45, 45);

            Rect LeftRectBlue = new Rect(225, 60, 45, 45);
            Rect LeftRectRed = new Rect(225, 60, 45, 45);

            Rect RightRectBlue = new Rect(25, 60, 45, 45);
            Rect RightRectRed = new Rect(25, 60, 45, 45);


            input.copyTo(outPut);
            Imgproc.rectangle(outPut, MiddleRectBlue, Middleblue, 2);
            Imgproc.rectangle(outPut, MiddleRectRed, Middlered, 2);

            Imgproc.rectangle(outPut, RightRectBlue, Rightblue, 2);
            Imgproc.rectangle(outPut,RightRectRed,Rightred, 2);

            Imgproc.rectangle(outPut, LeftRectBlue, Leftblue, 2);
            Imgproc.rectangle(outPut, LeftRectRed, Leftred, 2);


            MiddleBlueValue = YCBCr.submat(MiddleRectBlue);
            MiddleRedValue = YCBCr.submat(MiddleRectRed);

            RightBlueValue = YCBCr.submat(RightRectBlue);
            RightRedValue = YCBCr.submat(RightRectRed);

            LeftBlueValue = YCBCr.submat(LeftRectBlue);
            LeftRedValue = YCBCr.submat(LeftRectRed);

            Core.extractChannel(MiddleBlueValue, finalBlue, 2);
            Core.extractChannel(MiddleRedValue, finalRed, 0);
            Core.extractChannel(RightBlueValue, LeftfinalBlue, 2);
            Core.extractChannel(RightRedValue, LeftfinalRed, 0);
            Core.extractChannel(LeftBlueValue, RightfinalBlue, 2);
            Core.extractChannel(LeftRedValue, RightfinalRed, 0);

            Scalar BlueAvg = Core.mean(finalBlue);
            Scalar RedAvg = Core.mean(finalRed);
            Scalar RightBlueAvg = Core.mean(RightfinalBlue);
            Scalar RightRedAvg = Core.mean(RightfinalRed);
            Scalar LeftBlueAvg = Core.mean(LeftfinalBlue);
            Scalar LeftRedAvg = Core.mean(LeftfinalRed);

            RightBlueAvgfin = RightBlueAvg.val[0];
            RightRedAvgfin = RightRedAvg.val[0];
            LeftBlueAvgfin = LeftBlueAvg.val[0];
            LeftRedAvgfin = LeftRedAvg.val[0];
            BlueAvgfin = BlueAvg.val[0];
            RedAvgfin = RedAvg.val[0];
            telemetry.addData("RB",RightBlueAvgfin);
            telemetry.addData("RR",RightRedAvgfin);
            telemetry.addData("MB",BlueAvgfin);
            telemetry.addData("MR",RedAvgfin);
            telemetry.addData("LB",LeftBlueAvgfin);
            telemetry.addData("LR",LeftRedAvgfin);
            if(RightBlueAvgfin > RightRedAvgfin) {
                Rightcol = RightBlueAvgfin;
            }else{
                Rightcol = RightRedAvgfin;

            }
            if(BlueAvgfin > RedAvgfin) {
                midcol = BlueAvgfin;
            }else{
                midcol = RedAvgfin;

            }
            if(LeftBlueAvgfin > LeftRedAvgfin) {
                leftcol = LeftBlueAvgfin;
            }else{
                leftcol = LeftRedAvgfin;

            }
            if(Rightcol > leftcol && Rightcol > midcol){
                telemetry.addLine("Team prop is on the Left");
            }else if(midcol > leftcol && Rightcol < midcol){
            telemetry.addLine("Team prop is on the Right");
            }else if(midcol < leftcol && Rightcol < leftcol){
            telemetry.addLine("Team prop is on the Middle");
            midlleMovement = 1;
        }




            return (outPut);



        }



    }


}
