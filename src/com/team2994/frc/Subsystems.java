package com.team2994.frc;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class Subsystems {
	
//	/**
//	 * Singleton pattern recommended in Effective Java by Joshua Bloch.
//	 */
//	public static Subsystems getInstance() {
//		return Singleton.INSTANCE;
//	}
//	private static class Singleton {
//		private static final Subsystems INSTANCE = new Subsystems();
//	}
	
	public static int ROBOT_TYPE;
	
	static {
		init();
	}
	// this method initializes the robot type but is also required to combat Java's lazy loading of static classes.
	private static void init() {
		// Robot in use
		ROBOT_TYPE = Constants.PLY_BOY;
	}

	// Motor controllers
	public static final Motor leftFrontDrive = new Motor(Constants.LEFT_FRONT_DRIVE_PWM, ROBOT_TYPE);
	public static final Motor leftRearDrive = new Motor(Constants.LEFT_REAR_DRIVE_PWM, ROBOT_TYPE);
	public static final Motor rightFrontDrive = new Motor(Constants.RIGHT_FRONT_DRIVE_PWM, ROBOT_TYPE);
	public static final Motor rightRearDrive = new Motor(Constants.RIGHT_REAR_DRIVE_PWM, ROBOT_TYPE);
	public static final Motor leftCenterDrive = new Motor(Constants.CENTER_LEFT_DRIVE_PWM, ROBOT_TYPE);
	public static final Motor rightCenterDrive = new Motor(Constants.CENTER_RIGHT_DRIVE_PWM, ROBOT_TYPE);
	public static final Motor intake = new Motor(Constants.INTAKE_MOTOR_PWM, ROBOT_TYPE);
	public static final Motor rightWinch = new Motor(Constants.RIGHT_WINCH_MOTOR_PWM, ROBOT_TYPE);
	public static final Motor leftWinch = new Motor(Constants.LEFT_WINCH_MOTOR_PWM, ROBOT_TYPE);

	// Robot drive
	public static final ERobotDrive robotDrive = new ERobotDrive(leftFrontDrive, leftRearDrive, leftCenterDrive, rightCenterDrive, rightFrontDrive, rightRearDrive);

	// USB devices
	public static final EJoystick rightStick = new EJoystick(Constants.RIGHT_DRIVE_STICK);
	public static final EJoystick leftStick = new EJoystick(Constants.LEFT_DRIVE_STICK);
	public static final EGamepad gamepad = new EGamepad(Constants.GAMEPAD_PORT);

	// Solenoids
	public static final DoubleSolenoid shifters = new DoubleSolenoid(Constants.SHIFTER_A, Constants.SHIFTER_B);
	public static final DoubleSolenoid arm = new DoubleSolenoid(Constants.ARM_A, Constants.ARM_B);
	public static final DoubleSolenoid eject = new DoubleSolenoid(Constants.EJECT_A, Constants.EJECT_B);
	
	// Digital IOs
	public static final EDigitalInput winchSwitch = new EDigitalInput(Constants.WINCH_SWITCH);
	public static Encoder leftDriveEncoder;
	public static final Encoder rightDriveEncoder = new Encoder(Constants.RIGHT_ENCODER_A, Constants.RIGHT_ENCODER_B);
	
	// Misc
	public static final DriverStationLCD lcd = DriverStationLCD.getInstance();
	public static final Timer ejectTimer = new Timer();
	public static final Timer loadingTimer = new Timer();
	public static final Compressor compressor = new Compressor(Constants.COMPRESSOR_PRESSURE_SW, Constants.COMPRESSOR_SPIKE);

	// ---- testing only ----
	// Analog Channels
	// (note that analog channels are numbered 1->8 in the WPILib API and on the module
	// The last channel is always used for batt voltage so we do not have 
	// access to it (hence our max channel is 7 not 8)
	public static AnalogChannel analog1;
	public static AnalogChannel analog2;
	public static AnalogChannel analog3;
	public static AnalogChannel analog4;
	public static AnalogChannel analog5;
	public static AnalogChannel analog6;
	public static AnalogChannel analog7;
	// Solenoids
	// (note that solenoid channels are numbered 1->8)
	public static Solenoid solenoid7;
	public static Solenoid solenoid8;
	// Relays - Relay 2 is used for the solenoid spike
	public static Relay relay1;
	public static Relay relay3;
	public static Relay relay4;
	public static Relay relay5;
	public static Relay relay6;
	public static Relay relay7;
	public static Relay relay8;
	
	public static void initRobot() {
		// Add anything here that needs to be initialized during robot.init();
		if (ROBOT_TYPE == Constants.PLY_BOY) {
			leftDriveEncoder = new Encoder(Constants.LEFT_ENCODER_A_PLYBOY, Constants.LEFT_ENCODER_B_PLYBOY);
			analog1 = new AnalogChannel(1);
			analog2 = new AnalogChannel(2);
			analog3 = new AnalogChannel(3);
			analog4 = new AnalogChannel(4);
			analog5 = new AnalogChannel(5);
			analog6 = new AnalogChannel(6);
			analog7 = new AnalogChannel(7);
			// Solenoids
			// (note that solenoid channels are numbered 1->8)
			solenoid7 = new Solenoid(7);
			solenoid8 = new Solenoid(8);	

			relay1 = new Relay(1);
			relay3 = new Relay(3);
			relay4 = new Relay(4);
			relay5 = new Relay(5);
			relay6 = new Relay(6);
			relay7 = new Relay(7);
			relay8 = new Relay(8);
		} else {
			leftDriveEncoder = new Encoder(Constants.LEFT_ENCODER_A, Constants.LEFT_ENCODER_B);
		}
	}
}
