package com.team2994.frc.test;

import com.team2994.frc.Subsystems;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Relay;

//RELAY Menu

// The relay menu looks like this (not including the 1st 2 columns):
// 1:RELAY
// 2: Channel: #
// 3: Set: $$$
// 4: Back
// 5:
// 6:
public class RelayMenu extends BaseMenu {

	public static final int MIN_RELAY_CHANNEL = 0;
	public static final int MAX_RELAY_CHANNEL = 6; // 7 - 1 

	private int currentChannelNum_m = 0;
	private Relay channel_mp[] = {
		Subsystems.relay1, Subsystems.relay3,
		Subsystems.relay4, Subsystems.relay5, Subsystems.relay6,
		Subsystems.relay7, Subsystems.relay8
	};
	
	public RelayMenu() {
		super(2, 4);
	}

	private Relay.Value incrementChannelValue(Relay.Value currentChannelValue_m) {
		if (currentChannelValue_m == Relay.Value.kOff) {
			currentChannelValue_m =  Relay.Value.kOn;
		} else if (currentChannelValue_m == Relay.Value.kOn) {
			currentChannelValue_m =  Relay.Value.kForward;
		} else if (currentChannelValue_m == Relay.Value.kForward) {
			currentChannelValue_m =  Relay.Value.kReverse;
		} else if (currentChannelValue_m == Relay.Value.kReverse) {
			currentChannelValue_m =  Relay.Value.kOff;
		} else {
			// Should never get here. A swerr here WBN
			currentChannelValue_m =  Relay.Value.kOff;
		}
		return currentChannelValue_m;
	}

	private Relay.Value decrementChannelValue(Relay.Value currentChannelValue_m) {
		if (currentChannelValue_m == Relay.Value.kOff) {
			currentChannelValue_m =  Relay.Value.kReverse;
		} else if (currentChannelValue_m == Relay.Value.kOn) {
			currentChannelValue_m =  Relay.Value.kOff;
		} else if (currentChannelValue_m == Relay.Value.kForward) {
			currentChannelValue_m =  Relay.Value.kOn;
		} else if (currentChannelValue_m == Relay.Value.kReverse) {
			currentChannelValue_m =  Relay.Value.kForward;
		} else {
			// Should never get here. A swerr here WBN
			currentChannelValue_m =  Relay.Value.kOff;
		}
		return currentChannelValue_m;
	}

	public int handleSelectLeft () {
		switch (index_m) {
			case 2: // Decrement channel pointer
				stopRelay();
				currentChannelNum_m--;
				if (currentChannelNum_m < MIN_RELAY_CHANNEL) {
					currentChannelNum_m = MAX_RELAY_CHANNEL;
				}
				break;
			case 3: // Decrement channel value
				channel_mp[currentChannelNum_m].set(decrementChannelValue(channel_mp[currentChannelNum_m].get()));
				break;
			case 4: // Return to previous menu
				return callingMenu_m;
			default:
				break;
		};
		return DIGITAL_RELAY;
	}
	
	public int handleSelectRight() {
		switch (index_m) {
			case 2: // Increment channel pointer 
				stopRelay();
				currentChannelNum_m++;
				if (currentChannelNum_m > MAX_RELAY_CHANNEL) {
					currentChannelNum_m = MIN_RELAY_CHANNEL;
				}
				break;
			case 3: // Increment channel value
				channel_mp[currentChannelNum_m].set(incrementChannelValue(channel_mp[currentChannelNum_m].get()));
			default:
				break;
		};
		return DIGITAL_RELAY;
	}

	public void updateDisplay() {
		Subsystems.lcd.clear();
		Subsystems.lcd.println(DriverStationLCD.Line.kUser1, 1, "Relay");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser2, 1, " Channel: " + currentChannelNum_m + 1);
		if (channel_mp[currentChannelNum_m].get() == Relay.Value.kOff) {
			Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 1, " Set: Off");
		} else if (channel_mp[currentChannelNum_m].get() == Relay.Value.kOn) {
			Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 1, " Set: On");
		} else if (channel_mp[currentChannelNum_m].get() == Relay.Value.kForward) {
			Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 1, " Set: Forward");
		} else if (channel_mp[currentChannelNum_m].get() == Relay.Value.kReverse) {
			Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 1, " Set: Reverse");
		}
		Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 1, " Backy");
		Subsystems.lcd.println(indexToLCDLine(index_m), 1, "*");
		Subsystems.lcd.updateLCD();
	}

	public void stopRelay() {
		channel_mp[currentChannelNum_m].set(Relay.Value.kOff);
	}
	
	public void cleanup() {
		stopRelay();
	}
}
