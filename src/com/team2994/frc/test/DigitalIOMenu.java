package com.team2994.frc.test;

import com.team2994.frc.Subsystems;

import edu.wpi.first.wpilibj.DriverStationLCD;

// The Digital IO menu looks like this (not including the 1st 2 columns):
// 1:Digital I/O
// 2: State
// 3: Clock
// 4: Encoder
// 5: Back
// 6:
public class DigitalIOMenu extends BaseMenu {
	
	public DigitalIOMenu() {
		super(2,5);
	}

	public int handleSelectLeft() {
		switch (index_m) {
			// These cases can be removed because only back has meaning for a left select
			case 5:
				return callingMenu_m;
			default:
				break;
		}
		return DIGITAL_IO;
	}
	
	public int handleSelectRight() {
		switch (index_m) {
			case 2:
				return DIGITAL_IO_STATE;
			case 3:
				return DIGITAL_IO_CLOCK;
			case 4:
				return DIGITAL_IO_ENCODER;
			default:
				break;
		}
		return DIGITAL_IO;
	}

	public void updateDisplay() {
		Subsystems.lcd.clear();
		Subsystems.lcd.println(DriverStationLCD.Line.kUser1, 0, "Digital I/O");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser2, 0, " State");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 0, " Clock");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 0, " Encoder");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser5, 0, " Back");
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < DigitalIO.FREE_DIO_CHANNELS.length; i++) {
			strBuf.append(Integer.toString(DigitalIO.FREE_DIO_CHANNELS[i]));
			if (i != (DigitalIO.FREE_DIO_CHANNELS.length - 1)) {
				strBuf.append(",");
			}
		}
		Subsystems.lcd.println(DriverStationLCD.Line.kUser6, 0, "[" + strBuf.toString() + "]");
		Subsystems.lcd.println(indexToLCDLine(index_m), 1, "*");
		Subsystems.lcd.updateLCD();
	}

	public void cleanup() {
	}	
}
