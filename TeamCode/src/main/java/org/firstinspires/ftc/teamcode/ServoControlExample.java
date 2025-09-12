package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Servo Control Example", group="Linear Opmode")
public class ServoControlExample extends OpMode {

    // Declare servo variable
    private Servo myServo;

    // Runtime timer
    private ElapsedTime runtime = new ElapsedTime();

    // Servo position variables
    private double servoPosition = 0.5; // Start at middle position (0.0 to 1.0)
    private final double SERVO_SPEED = 0.01; // How fast servo moves per loop

    @Override
    public void init() {
        // Initialize the servo
        // Replace "servo_name" with the actual name configured in your robot configuration
        myServo = hardwareMap.get(Servo.class, "servo_name");

        // Set initial servo position
        myServo.setPosition(servoPosition);

        // Tell the driver that initialization is complete
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Servo Position", "%.2f", servoPosition);
        telemetry.update();
    }

    @Override
    public void init_loop() {
        // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
        telemetry.addData("Status", "Initialized - Waiting for start");
        telemetry.addData("Servo Position", "%.2f", servoPosition);
        telemetry.update();
    }

    @Override
    public void start() {
        // Code to run ONCE when the driver hits PLAY
        runtime.reset();
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_up) {
            servoPosition += SERVO_SPEED;
        }

        if (gamepad1.dpad_down) {
            servoPosition -= SERVO_SPEED;
        }

        if (gamepad1.x) {
            servoPosition = 0.0;
        }

        if (gamepad1.b) {
            servoPosition = 1.0;
        }

        if (gamepad1.a) {
            servoPosition = 0.5;
        }

        servoPosition = Math.max(0.0, Math.min(1.0, servoPosition));
        myServo.setPosition(servoPosition);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Servo Position", "%.2f", servoPosition);
        telemetry.addData("Controls", "DPad Up/Down: Fine movement");
        telemetry.addData("Controls", "X: Min (0.0), B: Max (1.0), A: Center (0.5)");
        telemetry.update();
    }

    @Override
    public void stop() {
        myServo.setPosition(0.5);
    }
}

