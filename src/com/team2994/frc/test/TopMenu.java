package com.team2994.frc.test;

import com.team2994.frc.Subsystems;

import edu.wpi.first.wpilibj.DriverStationLCD;

public class TopMenu extends BaseMenu {

	public TopMenu() {
		super(2, 5);
	}

	public int handleSelectLeft() {
		// We've decided that selecting a submenu should be right
		// button, exiting a submenu (returning to the parent menu
		// via the back button) should be left button only
		return TOP;
	}
	
	public int handleSelectRight() {
		return handleSelect();
	}

	public int handleSelect() {
		switch (index_m) {
			case 2:
				return ANALOG;
			case 3:
				return DIGITAL_TOP;
			case 4:
				return SOLENOID;
			case 5:
				break;
			default:
				break;
		}
		return TOP;
	}

	public void updateDisplay() {
		Subsystems.lcd.println(DriverStationLCD.Line.kUser1, 1, "Top");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser2, 1, " Analog");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 1, " Digital");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 1, " Solenoid");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser5, 1, " Reset");
		Subsystems.lcd.println(indexToLCDLine(index_m), 1, "*");
		Subsystems.lcd.updateLCD();
	}

	public void cleanup() {
	}

}
