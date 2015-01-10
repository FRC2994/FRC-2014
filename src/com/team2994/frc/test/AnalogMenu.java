package com.team2994.frc.test;

import com.team2994.frc.Subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStationLCD;

//Analog Menu

//The analog menu looks like this (not including the 1st 2 columns):
//1:Analog
//2: Channel: # #.##
//3: Back
//4:
//5:
//6:
public class AnalogMenu extends BaseMenu {

	public static final int MIN_ANALOG_CHANNEL = 0;
	// The last channel is always used for batt voltage so we do not have 
	// access to it (hence our max channel is 6 not 7)
	public static final int MAX_ANALOG_CHANNEL = 6; 

	// Always start pointing at Channel 0 and analog channel values initialize to zero
	private int currentChannelNum_m = 0;
	private AnalogChannel channel_mp[] = {
		Subsystems.analog1, Subsystems.analog2, Subsystems.analog3,
		Subsystems.analog4, Subsystems.analog5, Subsystems.analog6,
		Subsystems.analog7
	};
	
	public AnalogMenu() {
		super(2, 3);
	}

	public int handleSelectLeft () {
		switch (index_m) {
			case 2: // Decrement channel pointer 
				currentChannelNum_m--;
				if (currentChannelNum_m < MIN_ANALOG_CHANNEL) {
					currentChannelNum_m = MAX_ANALOG_CHANNEL;
				}
				break;
			case 3: // Return to previous menu
				return callingMenu_m;
			default:
				break;
		};
		return ANALOG;
	}
	
	public int handleSelectRight() {
		switch (index_m) {
			case 2: // Increment channel pointer 
				currentChannelNum_m++;
				if (currentChannelNum_m > MAX_ANALOG_CHANNEL) {
					currentChannelNum_m = MIN_ANALOG_CHANNEL;
				}
				break;
			default:
				break;
		};
		return ANALOG;
	}

	public void updateDisplay() {
		Subsystems.lcd.clear();
		Subsystems.lcd.println(DriverStationLCD.Line.kUser1, 1, "Analog");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser2, 1, " Channel: " + currentChannelNum_m + 1 + " " + channel_mp[currentChannelNum_m].getVoltage());
		Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 1, " Back");
		Subsystems.lcd.println(indexToLCDLine(index_m), 1, "*");
		Subsystems.lcd.updateLCD();
	}

	public void cleanup() {
	}
}
