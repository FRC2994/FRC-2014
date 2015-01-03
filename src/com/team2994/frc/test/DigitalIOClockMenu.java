package com.team2994.frc.test;

import com.team2994.frc.Subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.parsing.IInputOutput;

//Digitial IO Clock Menu

//Notes:
//1. This menu allows you to change the direction of a digital IO port created
// elsewhere in the program so be careful
//2. Any change to the ports being used for the clock (or leaving this menu)
// causes the clock object to be disabled and deleted to isolate this menu
// from the other digital IO menus

// The Digitial IO clock menu looks like this (not including the 1st 2 columns):
// 1:Digitial IO Clock
// 2: Channel: #
// 3: Enable: $$    
// 4: Count: ####
// 5: Back
// 6:Count: ##
public class DigitalIOClockMenu extends BaseMenu {

	private int currentChannelNum_m;
	private Counter counter_mp;

	public DigitalIOClockMenu() {
		super(2,5);
		// Start out with channel 0
		currentChannelNum_m   = 0;
		counter_mp = null;
	}
	
	private void createAndStartCounter() {
		if (counter_mp == null) {
			DigitalIO.setToInput(currentChannelNum_m, true);
			IInputOutput io = DigitalIO.getInputOutput(currentChannelNum_m);
			if (io != null) {
				counter_mp = new Counter((DigitalSource) io);
				counter_mp.start();
			}
		}
	}

	public int handleSelectLeft() {
		switch (index_m) {
			case 2: // Decrement channel port number 
				stopAndDestroyCounter();
				currentChannelNum_m--;
				if (currentChannelNum_m < DigitalIO.MIN_DIO_CHANNEL) {
					currentChannelNum_m = DigitalIO.NUM_DIO_CHANNELS - 1;
				}
				break;
			case 3: //  Toggle the enabled state
				if (counter_mp == null) {
					createAndStartCounter();
				} else {
					stopAndDestroyCounter();
				}
				break;
			// These cases can be removed because only back has meaning for a left select
			case 5:
				stopAndDestroyCounter();
				return callingMenu_m;
			default:
				break;
		}
		return DIGITAL_IO_CLOCK;
	}
	
	public int handleSelectRight() {
		switch (index_m) {
			case 2:
				stopAndDestroyCounter();
				currentChannelNum_m++;
				if (currentChannelNum_m >= DigitalIO.NUM_DIO_CHANNELS) {
					currentChannelNum_m = DigitalIO.MIN_DIO_CHANNEL;
				}
				break;
			case 3:
				if (counter_mp == null) {
					createAndStartCounter();
				} else {
					stopAndDestroyCounter();
				}
				break;
			default:
				break;
		}
		return DIGITAL_IO_CLOCK;
	}

	public void updateDisplay() {
		Subsystems.lcd.clear();
		Subsystems.lcd.println(DriverStationLCD.Line.kUser1, 0, "Digital I/O");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser2, 0, " State");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 0, " Clock");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 0, " Encoder");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser5, 0, " Back");
		Subsystems.lcd.println(indexToLCDLine(index_m), 1, "*");
		Subsystems.lcd.updateLCD();
	}	
	
	public void stopAndDestroyCounter() {
		DigitalIO.clearInputOutput(currentChannelNum_m);
		if (counter_mp != null) {
			counter_mp.stop();
			counter_mp = null;
		}		
	}
	
	public void cleanup() {
		stopAndDestroyCounter();
	}
}
