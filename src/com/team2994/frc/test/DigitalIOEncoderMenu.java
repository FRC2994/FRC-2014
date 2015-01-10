package com.team2994.frc.test;

import com.team2994.frc.Subsystems;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;

//Digitial IO encoder Menu

//Notes:
//1. This menu allows you to change the direction of a digital IO port created
// elsewhere in the program so be careful
//2. Any change to the ports being used for the encoder (or leaving this menu)
// causes the encoder object to be disabled and deleted to isolate this menu
// from the other digital IO menus

// The Digitial IO encoder menu looks like this (not including the 1st 2 columns):
// 1:Digitial IO encoder
// 2: Channel Pair: #
// 4: En: $$$ Cnt: ####
// 5: Back
// 6:
public class DigitalIOEncoderMenu extends BaseMenu {

	private int currentChannelNum1_m;
	private int currentChannelNum2_m;
	private Encoder encoder_mp;

	public DigitalIOEncoderMenu() {
		super(2,4);
		// Start out with first encoder channel
		currentChannelNum1_m = DigitalIO.DEDICATED_INPUT_ENCODER_CHANNELS[0];
		currentChannelNum2_m = DigitalIO.DEDICATED_INPUT_ENCODER_CHANNELS[1];
		encoder_mp = null;
	}
	
	private void createAndStartEncoder() {
		if (encoder_mp == null) {
			encoder_mp = DigitalIO.getDedicatedEncoder(currentChannelNum1_m);
			if (encoder_mp != null) {
				encoder_mp.start();	
			}
		}
	}

	public int handleSelectLeft() {
		switch (index_m) {
			case 2: // Decrement channel port number 
				stopAndDestroyEncoder();
				currentChannelNum1_m = currentChannelNum1_m - 2;
				if (currentChannelNum1_m < DigitalIO.MIN_DIO_CHANNEL) {
					currentChannelNum1_m = DigitalIO.NUM_DIO_CHANNELS - 2;
				}
				currentChannelNum2_m = currentChannelNum1_m + 1;
				break;
			case 3: //  Toggle the enabled state
				if (encoder_mp == null) {
					createAndStartEncoder();
				} else {
					stopAndDestroyEncoder();
				}
				break;
			// These cases can be removed because only back has meaning for a left select
			case 5:
				stopAndDestroyEncoder();
				return callingMenu_m;
			default:
				break;
		}
		return DIGITAL_IO_ENCODER;
	}
	
	public int handleSelectRight() {
		switch (index_m) {
			case 2:
				stopAndDestroyEncoder();
				currentChannelNum1_m = currentChannelNum1_m + 2;
				if (currentChannelNum1_m >= DigitalIO.NUM_DIO_CHANNELS) {
					currentChannelNum1_m = DigitalIO.MIN_DIO_CHANNEL;
				}
				currentChannelNum2_m = currentChannelNum1_m + 1;
				break;
			case 3:
				if (encoder_mp == null) {
					createAndStartEncoder();
				} else {
					stopAndDestroyEncoder();
				}
				break;
			default:
				break;
		}
		return DIGITAL_IO_ENCODER;
	}

	public void updateDisplay() {
		Subsystems.lcd.clear();
		Subsystems.lcd.println(DriverStationLCD.Line.kUser1, 1, "Digitial IO encoder");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser2, 1, " Channel Pair: " + currentChannelNum1_m + " " + currentChannelNum2_m);
		if (encoder_mp != null) {
			Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 1, " En: Yes Cnt: " + encoder_mp.get());			
		} else {
			Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 1, " En: No Cnt: --");
		}
		Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 1, " Back");
		Subsystems.lcd.println(indexToLCDLine(index_m), 1, "*");
		Subsystems.lcd.updateLCD();
	}	
	
	public void stopAndDestroyEncoder() {
		if (encoder_mp != null) {
			encoder_mp.stop();
			encoder_mp = null;
		}		
	}
	
	public void cleanup() {
		stopAndDestroyEncoder();
	}
}
