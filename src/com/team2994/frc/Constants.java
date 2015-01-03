package com.team2994.frc;

public class Constants {
	// Robot types
	public static final int COMPETITION_ROBOT = 0;
	public static final int PRACTICE_ROBOT = 1;
	public static final int PLY_BOY = 2;
	public static final String ROBOT_TYPES[] = {"Competition", "Practice", "Ply Boy"};
	
	// PWMs
	public static final int LEFT_FRONT_DRIVE_PWM = 4;   // cable 0 / 28
	public static final int LEFT_REAR_DRIVE_PWM = 3;    // cable 44 / 26
	public static final int RIGHT_FRONT_DRIVE_PWM = 2;  // cable 43 / 22
	public static final int RIGHT_REAR_DRIVE_PWM = 1;   // cable 41 / 23
	public static final int CENTER_LEFT_DRIVE_PWM = 7;  // UNKNOWN CABLE
	public static final int CENTER_RIGHT_DRIVE_PWM = 8; // UNKNOWN CABLE
	public static final int INTAKE_MOTOR_PWM = 5;       // cable 34 / 21
	public static final int RIGHT_WINCH_MOTOR_PWM = 6;  // cable 40 / 27
	public static final int LEFT_WINCH_MOTOR_PWM = 9;

	// Solenoids
	public static final int SHIFTER_A = 1; 				// cable 0
	public static final int SHIFTER_B = 2; 				// cable 00
	public static final int ARM_A = 3;     				// cable 22
	public static final int ARM_B = 4;     				// cable 23
	public static final int EJECT_A = 5;   				// cable 7
	public static final int EJECT_B = 6;   				// cable 12

	// Digital I/O
	public static final int LEFT_ENCODER_A = 2;         // cable 11
	public static final int LEFT_ENCODER_B = 3;         // cable 12
	public static final int RIGHT_ENCODER_A = 4;        // cable 39
	public static final int RIGHT_ENCODER_B = 5;        // cable 40
	public static final int COMPRESSOR_PRESSURE_SW = 8; //
	public static final int WINCH_SWITCH = 11;          //

	// Relays
	public static final int COMPRESSOR_SPIKE = 2;		// cable 7

	// USB ports
	public static final int RIGHT_DRIVE_STICK = 1;
	public static final int LEFT_DRIVE_STICK = 2;
	public static final int GAMEPAD_PORT = 3;

	// Left Joystick
	public static final int BUTTON_SHIFT = 7;

	// Gamepad
	public static final int BUTTON_SHOOT = 1;
	public static final int BUTTON_INTAKE_COLLECT = Gamepad.DPAD_DIRECTION_UP;
	public static final int BUTTON_INTAKE_EJECT = Gamepad.DPAD_DIRECTION_DOWN;
	// LOAD THE WINCH
	public static final int BUTTON_LOAD = 4;
	public static final int BUTTON_ARM = 5;
	public static final int BUTTON_PASS = 7;

	// Motor speeds
	public static final double WINCH_FWD = 1.0;

	// Intake speeds
	public static final double INTAKE_COLLECT = -0.95;
	public static final double INTAKE_EJECT = 0.95;

	// Misc.
	public static final double LOOP_PERIOD = 0.01;

	// # of encoder ticks to drive for in auto
	// TODO: Determine empirically.
	public static final int INTO_ZONE_DIST = 2385;
	public static final int SHOT_POSN_DIST = 1431;
	// Backwards drive for drivers
	public static final int ENCODER_BACK_DIST = 5500;
	// Time to wait after running motor to launch shooter before turning motor off
	public static final double CATAPULT_SHOOT_WAIT = 0.1;
	// Wait before turning off the eject pistons to ensure the ball is out.
	public static final double EJECT_WAIT = 2.0;
	// Speed to drive at in autonomous
	public static final double AUTO_DRIVE_SPEED = 0.75;
	// Amount of time to wait until we shoot after collecting second ball
	public static final double AUTO_PICKUP_WAIT = 2.0;
	// Amount of time to wait until we move after shooting second ball.
	public static final double AUTO_SHOOT_WAIT = 0.25;

}
