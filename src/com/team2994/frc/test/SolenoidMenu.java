package com.team2994.frc.test;

import com.team2994.frc.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Solenoid;

// The solenoid menu looks like this (not including the 1st 2 columns):
// 1: Solenoid: [Single|Dual]
// 2: Channel A: #
// 3: Channel B: #
// 4: Set: $$$
// 5: Back
// 6:
public class SolenoidMenu extends BaseMenu {

	public static final int MIN_SOLENOID_PAIR = 0;
	public static final int MAX_SOLENOID_PAIR = 3;
	public static final int SINGLE_SOLENOID_PAIR_START = 3;
	private int currentChannelPair;
	private DoubleSolenoid doubleSolenoid[] = {
			Subsystems.shifters, Subsystems.arm, Subsystems.eject
			};
	private Solenoid singleSolenoid[] = {
			Subsystems.solenoid7, Subsystems.solenoid8
			};

	public SolenoidMenu() {
		super(2,4);
		currentChannelPair   = 0;
	}
	
	public int handleSelectLeft() {
		switch (index_m) {
			case 2: // Decrement channel A pointer
				disableSolenoid();
				currentChannelPair--;
				if (currentChannelPair < MIN_SOLENOID_PAIR) {
					currentChannelPair = MAX_SOLENOID_PAIR;
				}
				break;
			case 3: // Set ...
				if (currentChannelPair >= SINGLE_SOLENOID_PAIR_START) {
					// Toggle channel value (it can only be true of false)
					int channel = currentChannelPair - SINGLE_SOLENOID_PAIR_START;
					singleSolenoid[channel].set(!singleSolenoid[channel].get());
					singleSolenoid[channel+1].set(!singleSolenoid[channel+1].get());
				} else {
					switch (doubleSolenoid[currentChannelPair].get().value) {
						case DoubleSolenoid.Value.kOff_val:
							doubleSolenoid[currentChannelPair].set(DoubleSolenoid.Value.kReverse);
							break;
						case DoubleSolenoid.Value.kForward_val:
							doubleSolenoid[currentChannelPair].set(DoubleSolenoid.Value.kOff);
							break;
						case DoubleSolenoid.Value.kReverse_val:
							doubleSolenoid[currentChannelPair].set(DoubleSolenoid.Value.kForward);
							break;
						default:
							break;
					}
				}
				break;
			case 4: // Return to previous menu
				return callingMenu_m;
			default:
				break;
		}
		return SOLENOID;
	}

	public int handleSelectRight() {
		switch (index_m) {
			case 2: // Increment channel A pointer
				disableSolenoid();
				currentChannelPair++;
				if (currentChannelPair > MAX_SOLENOID_PAIR) {
					currentChannelPair = MIN_SOLENOID_PAIR;
				}
				break;
			case 3: // Set ...
				if (currentChannelPair >= SINGLE_SOLENOID_PAIR_START) {
					// Toggle channel value (it can only be true of false)
					int channel = currentChannelPair - SINGLE_SOLENOID_PAIR_START;
					singleSolenoid[channel].set(!singleSolenoid[channel].get());
					singleSolenoid[channel+1].set(!singleSolenoid[channel+1].get());
				} else {
					switch (doubleSolenoid[currentChannelPair].get().value) {
						case DoubleSolenoid.Value.kOff_val:
							doubleSolenoid[currentChannelPair].set(DoubleSolenoid.Value.kForward);
							break;
						case DoubleSolenoid.Value.kForward_val:
							doubleSolenoid[currentChannelPair].set(DoubleSolenoid.Value.kReverse);
							break;
						case DoubleSolenoid.Value.kReverse_val:
							doubleSolenoid[currentChannelPair].set(DoubleSolenoid.Value.kOff);
							break;
						default:
							break;
					}
				}
				break;
			case 4: // Return to previous menu
				return callingMenu_m;
			default:
				break;
		}
		return SOLENOID;
	}
	
	public void updateDisplay() {
		Subsystems.lcd.clear();
		Subsystems.lcd.println(DriverStationLCD.Line.kUser1, 1, " Solenoid Pair");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser2, 1, " Channel A: " + (currentChannelPair*2));
		Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 1, " Channel B: " + ((currentChannelPair*2) + 1));
		if (currentChannelPair >= SINGLE_SOLENOID_PAIR_START) {
			Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 1, " Set Pair: " + 
					(singleSolenoid[currentChannelPair - SINGLE_SOLENOID_PAIR_START].get() ? "ON" : "OFF"));
		} else {
			switch (doubleSolenoid[currentChannelPair].get().value) {
				case DoubleSolenoid.Value.kOff_val:
					Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 1, " Set: Off");
					break;
				case DoubleSolenoid.Value.kForward_val:
					Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 1, " Set: Forward");
					break;
				case DoubleSolenoid.Value.kReverse_val:
					Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 1, " Set: Reverse");
					break;
				default:
					Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 1, " Set: Error");
					break;
			}
		}
		Subsystems.lcd.println(DriverStationLCD.Line.kUser5, 1, " Back");
		Subsystems.lcd.println(indexToLCDLine(index_m), 1, "*");
		Subsystems.lcd.updateLCD();
	}

	public void disableSolenoid() {
		if (currentChannelPair >= SINGLE_SOLENOID_PAIR_START) {
			// Toggle channel value (it can only be true of false)
			int channel = currentChannelPair - SINGLE_SOLENOID_PAIR_START;
			singleSolenoid[channel].set(false);
			singleSolenoid[channel+1].set(false);
		} else {
			doubleSolenoid[currentChannelPair].set(DoubleSolenoid.Value.kOff);
		}
	}
	
	public void cleanup() {
		disableSolenoid();
	}
}
