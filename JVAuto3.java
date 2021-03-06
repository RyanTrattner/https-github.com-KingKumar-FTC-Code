/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

/**
 * This file illustrates the concept of driving a path based on encoder counts.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code REQUIRES that you DO have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByTime;
 *
 *  This code ALSO requires that the drive Motors have been configured such that a positive
 *  power command moves them forwards, and causes the encoders to count UP.
 *
 *   The desired path in this example is:
 *   - Drive forward for 48 inches
 *   - Spin right for 12 Inches
 *   - Drive Backwards for 24 inches
 *   - Stop and close the claw.
 *
 *  The code is written using a method called: encoderDrive(speed, leftInches, rightInches, timeoutS)
 *  that performs the actual movement.
 *  This methods assumes that each movement is relative to the last stopping place.
 *  There are other ways to perform encoder based moves, but this method is probably the simplest.
 *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */


// Start perpendicular, Drive forward shoot, attempt to go on ramp

@Autonomous(name="Ramp Red", group="Pushbot")
public class JVAuto3 extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2 ;     // 2 because 1:2 gear ratio
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.3;
    static final double     TURN_SPEED              = 0.25;

    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    private DcMotor frontleftMotor = null;
    private DcMotor frontrightMotor = null;
    private DcMotor pewLeft= null;
    private DcMotor pewRight = null;
    private DcMotor grabby = null;
    private DcMotor lifty = null;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */

        leftMotor  = hardwareMap.dcMotor.get("left motor");
        rightMotor = hardwareMap.dcMotor.get("right motor");

        frontleftMotor  = hardwareMap.dcMotor.get("fleft motor");
        frontrightMotor = hardwareMap.dcMotor.get("fright motor");

        pewLeft  = hardwareMap.dcMotor.get("pewleft");
        pewRight = hardwareMap.dcMotor.get("pewright");

        grabby  = hardwareMap.dcMotor.get("grabby");
        lifty = hardwareMap.dcMotor.get("lifty");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //Go Forward
        //Shoots
        //Go right
        //Back onto the ramp all the way

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)

        driveForward(1,0.5);
        pewLeft.setPower(-0.6);
        pewRight.setPower(0.6);
        sleep(3000);
        lifty.setPower(.5);
        grabby.setPower(.5);
        sleep(800);
        lifty.setPower(0);
        grabby.setPower(0);
        sleep(800);
        lifty.setPower(.5);
        grabby.setPower(.5);
        sleep(800);
        lifty.setPower(0);
        grabby.setPower(0);
        sleep(800);
        lifty.setPower(.5);
        grabby.setPower(.5);
        sleep(800);
        lifty.setPower(0);
        grabby.setPower(0);
        sleep(800);
        lifty.setPower(.5);
        grabby.setPower(.5);
        sleep(800);
        lifty.setPower(0);
        grabby.setPower(0);
        sleep(800);
        lifty.setPower(.5);
        grabby.setPower(.5);
        sleep(800);
        lifty.setPower(0);
        grabby.setPower(0);
        sleep(800);
        lifty.setPower(.5);
        grabby.setPower(.5);
        sleep(800);
        lifty.setPower(0);
        grabby.setPower(0);
        sleep(800);
        sleep(2000);
        pewLeft.setPower(0);
        pewRight.setPower(0);

        turnRight(1,1.5);
        sleep(1000);
        driveBackward(1,1.85);
        sleep(1000);
        turnLeft(1,.85);
        sleep(1000);
        driveBackward(1,2);
        driveBackward(1,2);
        driveBackward(1,2);
        sleep(1000);


        telemetry.addData("Path", "Complete");
        telemetry.update();
    }


    public void shoot(double seconds)
    {
        pewLeft.setPower(-0.8);
        pewRight.setPower(0.8);
        runtime.reset();
        while(runtime.seconds() < seconds) {
            lifty.setPower(.5);
            grabby.setPower(.5);
            sleep(500);
            lifty.setPower(0);
            sleep(500);
        }
        pewLeft.setPower(0);
        pewRight.setPower(0);
    }





    //     Method to drive forward with speed and time
    public void driveBackward(double speed, double seconds) {
        runtime.reset();
        while(runtime.seconds() < seconds) {
            leftMotor.setPower(-speed);
            frontleftMotor.setPower(-speed);
            rightMotor.setPower(speed);
            frontrightMotor.setPower(speed);
        }
        leftMotor.setPower(0);
        frontleftMotor.setPower(0);
        rightMotor.setPower(0);
        frontrightMotor.setPower(0);
    }


    //     Method to drive backward with speed and time
    public void driveForward(double speed, double seconds) {
        runtime.reset();
        while(runtime.seconds() < seconds) {
            leftMotor.setPower(speed);
            frontleftMotor.setPower(speed);
            rightMotor.setPower(-speed);
            frontrightMotor.setPower(-speed);
        }
        leftMotor.setPower(0);
        frontleftMotor.setPower(0);
        rightMotor.setPower(0);
        frontrightMotor.setPower(0);
    }

    //     Method to turn left with speed and time
    public void turnRight(double speed, double seconds) {
        runtime.reset();
        while(runtime.seconds() < seconds) {
            leftMotor.setPower(speed);
            frontleftMotor.setPower(speed);
            rightMotor.setPower(speed);
            frontrightMotor.setPower(speed);
        }
        leftMotor.setPower(0);
        frontleftMotor.setPower(0);
        rightMotor.setPower(0);
        frontrightMotor.setPower(0);
    }

    //     Method to turn right with speed and time
    public void turnLeft(double speed, double seconds) {
        runtime.reset();
        while(runtime.seconds() < seconds) {
            leftMotor.setPower(-speed);
            frontleftMotor.setPower(-speed);
            rightMotor.setPower(-speed);
            frontrightMotor.setPower(-speed);
        }
        leftMotor.setPower(0);
        frontleftMotor.setPower(0);
        rightMotor.setPower(0);
        frontrightMotor.setPower(0);
    }









    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    //Deprecated
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = rightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset()  ;
            leftMotor.setPower(Math.abs(speed));
            rightMotor.setPower(Math.abs(speed));
            frontleftMotor.setPower(speed);
            frontrightMotor.setPower(-speed);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() && rightMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        leftMotor.getCurrentPosition(),
                        rightMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            frontleftMotor.setPower(0);
            frontrightMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
}
