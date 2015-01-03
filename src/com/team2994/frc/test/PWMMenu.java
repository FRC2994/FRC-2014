package com.team2994.frc.test;

import com.team2994.frc.Motor;
import com.team2994.frc.Subsystems;

import edu.wpi.first.wpilibj.DriverStationLCD;

// The PWM menu looks like this (not including the 1st 2 columns):
// 1:PWM
// 2: Channel A: #
// 3: Channel B: #
// 4: Enabled:
// 5: Back
// 6:
public class PWMMenu extends BaseMenu {
	public static final int MIN_PWM_CHANNEL = 0;
	public static final int MAX_PWM_CHANNEL = 9;

	public static final int ENABLED_NEITHER = 0;
	public static final int	ENABLED_A_ONLY = 1;
	public static final int ENABLED_B_ONLY = 2;
	public static final int ENABLED_BOTH = 3;

	public static final String enabledStrings[] = {
		"Neither", "A Only", "B Only", "Both"
		};

	private int currentChannelNumA_m;
	private int currentChannelNumB_m;
	private int enabled_m;
	
	private Motor channel_mp[] = { 
			Subsystems.rightRearDrive, Subsystems.rightFrontDrive, Subsystems.leftRearDrive, 
			Subsystems.leftFrontDrive, Subsystems.intake, Subsystems.rightWinch,
			Subsystems.leftCenterDrive, Subsystems.rightCenterDrive, Subsystems.leftWinch
	};
	boolean activeChannels[] = new boolean[MAX_PWM_CHANNEL + 1];

	public PWMMenu() {
		super(2,5);
		// Start out with channel 0 for each
		currentChannelNumA_m = 0;
		currentChannelNumB_m = 1;
		enabled_m = ENABLED_NEITHER;
		
		for (int i = MIN_PWM_CHANNEL; i <= MAX_PWM_CHANNEL; i++) {
			channel_mp[i].setExpiration(0.2);
			activeChannels[i] = false;
		}
	}
	
	public int handleSelectLeft() {
		switch (index_m) {
			case 2: // Decrement channel A number
				disablePWM(currentChannelNumA_m);
				currentChannelNumA_m--;
				// Make sure channels A and B are never the same
				if (currentChannelNumA_m == currentChannelNumB_m) {
					currentChannelNumA_m--;
				}
				if (currentChannelNumA_m < MIN_PWM_CHANNEL) {
					currentChannelNumA_m = MAX_PWM_CHANNEL;
					// Make sure channels A and B are never the same
					if (currentChannelNumA_m == currentChannelNumB_m) {
						currentChannelNumA_m--;
					}
				}
				break;
			case 3: // Decrement channel B number
				disablePWM(currentChannelNumB_m);
				currentChannelNumB_m--;
				// Make sure channels A and B are never the same
				if (currentChannelNumB_m == currentChannelNumA_m) {
					currentChannelNumB_m--;
				}
				if (currentChannelNumB_m < MIN_PWM_CHANNEL)	{
					currentChannelNumB_m = MAX_PWM_CHANNEL;
					// Make sure channels A and B are never the same
					if (currentChannelNumB_m == currentChannelNumA_m) {
						currentChannelNumB_m--;
					}
				}
				break;
			case 4: // Decrement enabled flag
				enabled_m--;
				if (enabled_m < ENABLED_NEITHER) {
					enabled_m = ENABLED_BOTH;
				}
				break;
			case 5: // Return to previous menu
				return callingMenu_m;
			default:
				break;
		}
		return DIGITAL_PWM;
	}
	
	public int handleSelectRight() {
		switch (index_m) {
			case 2: // Increment channel A number 
				disablePWM(currentChannelNumA_m);
				currentChannelNumA_m++;
				// Make sure channels A and B are never the same
				if (currentChannelNumA_m == currentChannelNumB_m) {
					currentChannelNumA_m++;
				}
				if (currentChannelNumA_m > MAX_PWM_CHANNEL)	{
					currentChannelNumA_m = MIN_PWM_CHANNEL;
					// Make sure channels A and B are never the same
					if (currentChannelNumA_m == currentChannelNumB_m) {
						currentChannelNumA_m++;
					}
				}
				break;
			case 3: // Decrement channel value
				disablePWM(currentChannelNumB_m);
				currentChannelNumB_m++;
				// Make sure channels A and B are never the same
				if (currentChannelNumB_m == currentChannelNumA_m) {
					currentChannelNumB_m++;
				}
				if (currentChannelNumB_m > MAX_PWM_CHANNEL)	{
					currentChannelNumB_m = MIN_PWM_CHANNEL;
					// Make sure channels A and B are never the same
					if (currentChannelNumB_m == currentChannelNumA_m) {
						currentChannelNumB_m++;
					}
				}
				break;
			case 4: // Increment the enabled flag
				enabled_m++;
				if (enabled_m > ENABLED_BOTH) {
					enabled_m = ENABLED_NEITHER;
				}
			default:
				break;
		}
		return DIGITAL_PWM;
	}
	
	private void setSpeed(int channel, float speed) {
		if(activeChannels[channel] == false) {
			channel_mp[channel].set(speed);
			activeChannels[channel] = true;
		} else {
			activeChannels[channel] = false;
		}
	}

	public void setSpeed(float speed) {
		// To keep motor safety tmeouts from occuring we need to set all of the motor
		// controllers each pass of the main program loop (and this method is called 
		// in each pass of the main program loop)

		if (enabled_m == ENABLED_BOTH) { // Reverse order cuz of glitchy stuff to make sure you dont goof up
			setSpeed(currentChannelNumA_m, speed);
			setSpeed(currentChannelNumB_m, speed);
		} else if (enabled_m == ENABLED_A_ONLY) {
			setSpeed(currentChannelNumA_m, speed);
		} else if (enabled_m == ENABLED_B_ONLY) {
			setSpeed(currentChannelNumB_m, speed);
		}
	}

	public void updateDisplay() {
		int chanA = currentChannelNumA_m + 1;
		int chanB = currentChannelNumB_m + 1;

		Subsystems.lcd.clear();
		Subsystems.lcd.println(DriverStationLCD.Line.kUser1, 0, "PWM");
		Subsystems.lcd.println(DriverStationLCD.Line.kUser2, 0, " Channel A: "+ chanA  + " " + channel_mp[currentChannelNumA_m].get());
		Subsystems.lcd.println(DriverStationLCD.Line.kUser3, 0, " Channel B: "+ chanB  + " " + channel_mp[currentChannelNumB_m].get());
		Subsystems.lcd.println(DriverStationLCD.Line.kUser4, 0, " Enabled: " + enabledStrings[enabled_m]);
		Subsystems.lcd.println(DriverStationLCD.Line.kUser5, 0, " Back");
		Subsystems.lcd.println(indexToLCDLine(index_m), 1, "*");
		Subsystems.lcd.updateLCD();
	}

	public void disablePWM(int channel) {
		setSpeed(channel, 0);
	}
	
	public void cleanup() {
		disablePWM(currentChannelNumA_m);
		disablePWM(currentChannelNumB_m);
	}
}
