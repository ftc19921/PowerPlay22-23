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
public class AutoCone extends OpMode {
    DcMotor RFM;
    DcMotor RBM;
    DcMotor LFM;
    DcMotor LBM;
    DcMotor forbar;
    DcMotor lift;
    CRServo intake1;
    CRServo intake2;
    TouchSensor touchSensor;
    Double coneColor=0.0;
    Double limit=1.0;
    OpenCvWebcam webcam;
    ColorSensor colorSensor;
    int isStarted;
    int stage=1;
    int step=1;
    @Override
    public void init() {
        touchSensor=hardwareMap.get(TouchSensor.class,"touchsensor2");
        colorSensor=hardwareMap.get(ColorSensor.class,"color");
        RFM=hardwareMap.get(DcMotor.class,"motorFrontRight");
        RBM=hardwareMap.get(DcMotor.class,"motorBackRight");
        LFM=hardwareMap.get(DcMotor.class,"motorFrontLeft");
        LBM=hardwareMap.get(DcMotor.class,"motorBackLeft");
        lift=hardwareMap.get(DcMotor.class,"armMotor");
        forbar=hardwareMap.get(DcMotor.class,"forBar");
        intake1=hardwareMap.get(CRServo.class,"gripperServo");
        intake2=hardwareMap.get(CRServo.class,"virtical");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam1"), cameraMonitorViewId);

        webcam.setPipeline(new Pipeline());
        webcam.setMillisecondsPermissionTimeout(2500);
        RFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RBM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LBM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RBM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LBM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


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


    public void loop(){

        if (!touchSensor.isPressed()){
            limit=2.0;
        }else{
            limit=1.0;
        }
        isStarted=1;
        telemetry.addData("RFM",RFM.getCurrentPosition());
        telemetry.addData("stage=",stage);
        telemetry.addData("blue",colorSensor.blue());
        telemetry.addData("red",colorSensor.red());

        if(stage==1){
            if(RFM.getCurrentPosition()>=-6000){
                RFM.setPower(-0.2);
                RBM.setPower(-0.2);
                LFM.setPower(0.2);
                LBM.setPower(0.2);
                if(limit==1.0){
                    lift.setPower(-0.7);
                }else{
                    lift.setPower(0);
                }
            }else{

                lift.setPower(-0.1);
                stage=2;
                RFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        }
        if(stage==2){
            if(RFM.getCurrentPosition()>=-1200){
                RFM.setPower(-0.2);
                RBM.setPower(0.2);
                LFM.setPower(-0.2);
                LBM.setPower(0.2);


                forbar.setPower(0.4);

            }else{
                stage=5;
                forbar.setPower(0.2);
                RFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        }
        if(stage==5){
            if(RFM.getCurrentPosition()>=-1000) {
                RFM.setPower(-0.4);
                RBM.setPower(-0.4);
                LFM.setPower(0.4);
                LBM.setPower(0.4);

                intake1.setPower(-0.2);

                intake2.setPower(0.2);


            }else {


                stage=3;
                RFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

        }
        if(stage==3){
            if(RFM.getCurrentPosition()<=1000){
                RFM.setPower(0.2);
                RBM.setPower(0.2);
                LFM.setPower(-0.2);
                LBM.setPower(-0.2);


                lift.setPower(0.1);
            }else{
                stage=7;
                RFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

        }






        if(stage==7){
            if(coneColor==2){
                if(RFM.getCurrentPosition()>=-1500){
                    RFM.setPower(-0.2);
                    RBM.setPower(0.2);
                    LFM.setPower(-0.2);
                    LBM.setPower(0.2);
                }else{
                    stage = 8;
                    RFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    RFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    RFM.setPower(0);
                    RBM.setPower(0);
                    LFM.setPower(0);
                    LBM.setPower(0);
                    forbar.setPower(-0.2);
                }
            }
            if(coneColor==3){
                if(RFM.getCurrentPosition()<4400) {
                    RFM.setPower(0.5);
                    RBM.setPower(-0.5);

                    LFM.setPower(0.5);
                    LBM.setPower(-0.5);
                }else{
                    stage = 8;
                    RFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    RFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    RFM.setPower(0);
                    RBM.setPower(0);
                    LFM.setPower(0);
                    LBM.setPower(0);
                    forbar.setPower(-0.2);
                }
            }if(coneColor==4){
                if(RFM.getCurrentPosition()<1900) {

                    RFM.setPower(0.2);
                    RBM.setPower(-0.2);
                    LFM.setPower(0.2);
                    LBM.setPower(-0.2);
                }else {
                    RFM.setPower(0);
                    RBM.setPower(0);
                    LFM.setPower(0);
                    LBM.setPower(0);
                    forbar.setPower(-0.2);
                }
            }
        }



    }

    class Pipeline extends OpenCvPipeline{


        Mat YCBCr= new Mat();
        Mat GreenValue;
        Mat RedValue;
        Mat BlueValue;
        Mat finalBlue= new Mat();
        Mat finalRed= new Mat();
        Mat finalGreen= new Mat();
        double BlueAvgfin;
        double RedAvgfin;
        double GreenAvgfin;
        Mat outPut = new Mat();
        Scalar blue=new Scalar(0.0,0.0,255.0);
        Scalar green=new Scalar(0.0,255.0,0.0);
        Scalar red=new Scalar(255.0,0.0,0.0);

        @Override
        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input,YCBCr,Imgproc.COLOR_RGBA2RGB);




            Rect MiddleRectBlue =new Rect(195,107,50,25);
            Rect MiddleRectGreen =new Rect(195,107,50,25);
            Rect MiddleRectRed =new Rect(195,107,50,25);


            input.copyTo(outPut);
            Imgproc.rectangle(outPut,MiddleRectBlue,blue,2);
            Imgproc.rectangle(outPut,MiddleRectRed,green,2);
            Imgproc.rectangle(outPut,MiddleRectGreen,red,2);

            BlueValue=YCBCr.submat(MiddleRectBlue);
            GreenValue=YCBCr.submat(MiddleRectGreen);
            RedValue=YCBCr.submat(MiddleRectRed);




            Core.extractChannel(BlueValue,finalBlue,2);
            Core.extractChannel(GreenValue,finalGreen,0);
            Core.extractChannel(RedValue,finalRed,1);
            Scalar BlueAvg= Core.mean(finalBlue);
            Scalar RedAvg= Core.mean(finalGreen);
            Scalar GreenAvg= Core.mean(finalRed);

            BlueAvgfin=BlueAvg.val[0];
            RedAvgfin=RedAvg.val[0];
            GreenAvgfin=GreenAvg.val[0];


            if(BlueAvgfin>GreenAvgfin&&BlueAvgfin>RedAvgfin&&isStarted==0){
                coneColor=2.0;
                telemetry.addData("blue",BlueAvgfin);
            }else if(RedAvgfin>GreenAvgfin&&RedAvgfin>BlueAvgfin&&isStarted==0){
                coneColor=3.0;
                telemetry.addData("red",RedAvgfin);
            }else if(GreenAvgfin>BlueAvgfin&&GreenAvgfin>RedAvgfin&&isStarted==0){
                coneColor=4.0;
                telemetry.addData("green",GreenAvgfin);
            }else{

                telemetry.addData("color", coneColor);
            }


            return (outPut);



        }
    }
}
