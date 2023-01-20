package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


public class pathFindingTest extends OpMode {
    double playerY;
    double playerX;
    double enemyY;
    double enemyX;
    double turn=1;
    double GoalX;
    double GoalY;
    double dead;
    @Override
    public void init() {
        playerX=10.0;
        playerY=10.0;
        enemyX=0.0;
        enemyY=0.0;
        GoalY=1.0;
        GoalX=1.0;
        dead=0;

    }

    @Override
    public void loop() {
        telemetry.addData("playerX",playerX);
        telemetry.addData("playerY",playerY);
        telemetry.addData("enemyX",enemyX);
        telemetry.addData("enemyY",enemyY);
        if (dead==1) {
            telemetry.addLine("GAME OVER!");
            turn=4;

        } else if (playerY == GoalY && playerX == GoalX) {
            telemetry.addLine("YOU WIN!");

        }
        if(gamepad1.a&&turn==2){
           turn=1;
        }
        if(turn==1&& gamepad1.dpad_down||turn==1&& gamepad1.dpad_up||turn==1&& gamepad1.dpad_left||turn==1&& gamepad1.dpad_right||turn==1&& gamepad1.b) {


            if (enemyX > playerX) {
                enemyX = enemyX - 2;
            } else if (enemyX < playerX) {
                enemyX = enemyX + 2;
            }
            if (enemyY > playerY) {
                enemyY = enemyY - 2;
            } else if (enemyY < playerY) {
                enemyY = enemyY + 2;

            }
            if (playerY == enemyY && playerX == enemyX) {
                dead=1;


            }

            if (gamepad1.dpad_up) {
                playerY = playerY + 1;
                turn=2;
            } else if (gamepad1.dpad_down) {
                playerY = playerY - 1;
                turn=2;
            }
            if (gamepad1.dpad_right) {
                playerX = playerX + 1;
                turn=2;
            } else if (gamepad1.dpad_left) {
                playerX = playerX - 1;
                turn=2;
            }else if(gamepad1.b){
                turn=2;
            }
        }





    }
}
