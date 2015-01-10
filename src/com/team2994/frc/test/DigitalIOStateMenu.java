package com.team2994.frc.test;

import com.team2994.frc.Subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.parsing.IInputOutput;

// The relay menu looks like this (not including the 1st 2 columns):
// 1:Digital IO State
// 2: Channel: #
// 3: Direction: $$$
// 4: Set|Value: #
// 5: Back
// 6:
public class DigitalIOStateMenu extends BaseMenu {

	private int currentChannelNum_m;
	private boolean currentChannelValue_m;

	public DigitalIOStateMenu() {
		super(2,5);
		// Start out with channel 0
		currentChannelNum_m   = 0;
		currentChannelValue_m   = false;
	}
	
	private void setupChannel(boolean setToInput) {
		currentChannelValue_m = false;
		DigitalIO.setToInput(currentChannelNum_m, setToInput);
		IInputOutput io = DigitalIO.getInputOutput(currentChannelNum_m);
		if ((io != null) && (!setToInput)) {
				((DigitalOutput)io).set(currentChannelValue_m);
		}
	}

	public int handleSelectLeft() {
		switch (index_m) {
			case 2: // Decrement channel port number
				teardownChannel();
				currentChannelNum_m--;
				if (currentChannelNum_m < DigitalIO.MIN_DIO_CHANNEL) {
					currentChannelNum_m = DigitalIO.NUM_DIO_CHANNELS - 1;
				}
				break;
			case 3: // Toggle channel direction
				setupChannel(!DigitalIO.isInput(currentChannelNum_m));
				break;
			case 4: // Toggle channel value (it can only be true or false)
				if (!DigitalIO.isInput(currentChannelNum_m)) {
					currentChannelValue_m = !currentChannelValue_m;
					((DigitalOutput)DigitalIO.getInputOutput(currentChannelNum_m)).set(currentChannelValue_m);
				}
				break;
			case 5:
				teardownChannel();
				return callingMenu_m;
			default:
				break;
		}
		return DIGITAL_IO_STATE;
	}
	
	public int handleSelectRight() {
		switch (index_m) {
			case 2:
				teardownChannel();
				currentChannelNum_m++;
				if (currentChannelNum_m >= DigitalIO.NUM_DIO_CHANNELS) {
					currentChannelNum_m = DigitalIO.MIN_DIO_CHANNEL;
				}
				break;
			case 3: // Toggle channel direction
				setupChannel(!DigitalIO.isInput(currentChannelNum_m));
				break;
			case 4: // Toggle channel value (it can only be true or false)
				if (!DigitalIO.isInput(currentChannelNum_m)) {
					currentChannelValue_m = !currentChannelValue_m;
					((DigitalOutput)DigitalIO.getInputOutput(currentChannelNum_m)).set(currentChannelValue_m);
				}
				break;
			default:
				break;
		}
		return DIGITAL_IO_STATE;
	}

	public void updateDisplay() {
		Subsystems.lcd.clear();
		Subsystems.lcd.println(DriverStationLCD.Line.kUser1, 1, "Digital IO State");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser2, 1, " Channel " + currentChannelValue_m);
		if (DigitalIO.isInput(currentChannelNum_m)) {
			Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 1, " Direction: IN");	
		} else {
			Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 1, " Direction: OUT");
		}
		Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 1, " Back");
		Subsystems.lcd.println(indexToLCDLine(index_m), 1, "*");
		Subsystems.lcd.updateLCD();
	}	
	
	public void teardownChannel() {
		DigitalIO.clearInputOutput(currentChannelNum_m);
	}
	
	public void cleanup() {
		teardownChannel();
	}
}
