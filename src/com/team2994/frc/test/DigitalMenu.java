package com.team2994.frc.test;

import com.team2994.frc.Subsystems;

import edu.wpi.first.wpilibj.DriverStationLCD;

// The Digital menu looks like this (not including the 1st 2 columns):
// 1:Digital
// 2: I/O
// 3: PWM
// 4: Relay
// 5: Back
// 6:
public class DigitalMenu extends BaseMenu {

	public DigitalMenu() {
		super(2,5);
	}
	
	public int handleSelectLeft() {
		switch (index_m) {
			case 5:
				return callingMenu_m;
			default:
				break;
		}
		return DIGITAL_TOP;
	}

	public int handleSelectRight() {
		switch (index_m) {
			case 2:
				return DIGITAL_IO;
			case 3:
				return DIGITAL_PWM;
			case 4:
				return DIGITAL_RELAY;
			default:
				break;
		}
		return DIGITAL_TOP;
	}
	
	public void updateDisplay() {
		Subsystems.lcd.println(DriverStationLCD.Line.kUser1, 1, "Digital");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser2, 1, " I/O");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 1, " PWM");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 1, " Relay");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser5, 1, " Back");
		Subsystems.lcd.println(indexToLCDLine(index_m), 1, "*");
		Subsystems.lcd.updateLCD();
	}

	public void cleanup() {
	}

}
