package com.team2994.frc.test;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;

public abstract class BaseMenu {
//	#define NUM_DIO_CHANNELS 14
//	#define MIN_DIO_CHANNEL  0

// The menus shown on the LCD are organized as follows:
//
//	                          +---------------------+   
//	                          +         Top         +   
//	                          +---------------------+   
//	                                |    |    |
//	            +-------------------+    |    +--------------------+
//	            |                        |                         |
	// +---------------------+  +---------------------+   +---------------------+
	// +       Analog        +  +       Digital       +   +       Solenoid      +
	// +---------------------+  +---------------------+   +---------------------+
//	                                |    |    |
//	            +-------------------+    |    +--------------------+
//	            |                        |                         |
	// +---------------------+  +---------------------+   +---------------------+
	// +     Digital PWM     +  +     Digital IO      +   +    Digital Relay    +
	// +---------------------+  +---------------------+   +---------------------+
//	                                |    |    |
//	            +-------------------+    |    +--------------------+
//	            |                        |                         |
	// +---------------------+  +---------------------+   +---------------------+
	// +  Digital IO State   +  +  Digital IO Clock   +   + Digital IO Encoder  +
	// +---------------------+  +---------------------+   +---------------------+

	// The complete list of menus in the test program
	public static final int TOP = 0;				// Points to analog, digitalTop, and solenoid
	public static final int ANALOG = 1; 			// Handles analog module inputs
	public static final int DIGITAL_TOP = 2; 		// Points to digitalPWM, digitalIO, and digitalRelay
	public static final int SOLENOID = 3;			// Handles solenoid module outputs
	public static final int DIGITAL_PWM = 4; 		// Handles digital module PWM outputs
	public static final int DIGITAL_IO = 5;			// Points to digitalIOState, digitalIOClock, and digitalIOEncoder
	public static final int DIGITAL_RELAY = 6;		// Handles digital module relay outputs
	public static final int DIGITAL_IO_STATE = 7;	// Handles digital IOs as ON or OFF
	public static final int DIGITAL_IO_CLOCK = 8;	// Handles digital IOs and clocks
	public static final int DIGITAL_IO_ENCODER = 9;	// Handles a pair of digital IOs as an encoder input
	public static final int NUM_MENU_TYPE = 10;

	// ----------------------------------------------------------------------------
	// Base class for all of the other menus in the system
	// ----------------------------------------------------------------------------

	// The menu base class contains all of the basic operations of a menu
	// with up to six lines of 20 characters (the LCD display on the driver
	// station. Each menu must handle four buttons for navigation and 
	// selection:
	//   Index Up - move the menu selection asterisk up one line of the menu
	//   Index Down - move the menu selection asterisk down one line of the menu
	//   Select Right - select the next (incrementally higher) option or
	//	                  value for a given menu line
	//   Select Left - delect the previous (incrementally lower) option or
	//	                 value for a given menu line.
	// The methods provided by this base class are meant to be overriden by
	// menu-specific versions in all derived classes. We are creating the base 
	// class to allow us to use a base-class pointer to refer to any derived
	// class instance.

	protected int index_m;
	protected int maxIndex_m;
	protected int minIndex_m;
	protected int callingMenu_m;

	public BaseMenu(int index, int maxIndex) {
		// The top menu looks like this (not including the first two columns
		// 1:Top
		// 2: Analog
		// 3: Digital
		// 4: Solenoid
		// 5: Reset
		// 6:
		index_m = index;
		maxIndex_m = maxIndex;
		minIndex_m = 2;
		callingMenu_m = TOP;
	}

	public BaseMenu(int index, int maxIndex, int minIndex) {
		index_m = index;
		maxIndex_m = maxIndex;
		minIndex_m = minIndex;
		callingMenu_m = TOP;
	}
	
	public void handleIndexDown() {
		index_m++;
		if (index_m > maxIndex_m)
		{
			index_m = minIndex_m;
		}
	}

	public void handleIndexUp() {
		index_m--;
		if (index_m < minIndex_m)
		{
			index_m = maxIndex_m;
		}
	}

	public int handleSelectLeft() {
		return TOP;
	}

	public int handleSelectRight() {
		return TOP;
	}

	public void setCallingMenu(int callingMenu)	{
		callingMenu_m = callingMenu;
	}

	protected Line indexToLCDLine(int lineNum) {
		Line line = DriverStationLCD.Line.kUser1;
		switch(lineNum) {
			case 1:
				line = DriverStationLCD.Line.kUser1;
				break;
			case 2:
				line = DriverStationLCD.Line.kUser2;
				break;
			case 3:
				line = DriverStationLCD.Line.kUser3;
				break;
			case 4:
				line = DriverStationLCD.Line.kUser4;
				break;
			case 5:
				line = DriverStationLCD.Line.kUser5;
				break;				
		}
		return line;
	}
		
	public void setSpeed (double speed) {
	}

	public abstract void updateDisplay();
	
	public abstract void cleanup();
}
