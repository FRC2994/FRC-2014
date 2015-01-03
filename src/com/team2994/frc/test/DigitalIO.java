package com.team2994.frc.test;

import com.team2994.frc.Constants;
import com.team2994.frc.Subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.parsing.IInputOutput;

public class DigitalIO {

	public static final int NUM_DIO_CHANNELS = 14;
	public static final int MIN_DIO_CHANNEL = 0;

	public static final int DEDICATED_INPUT_ENCODER_CHANNELS[] =  {
		Constants.LEFT_ENCODER_A, Constants.LEFT_ENCODER_B, 
		Constants.RIGHT_ENCODER_A, Constants.RIGHT_ENCODER_B
	};
	public static Encoder DEDICATED_INPUT_ENCODER[] = {
		Subsystems.leftDriveEncoder, Subsystems.rightDriveEncoder
	};
	public static final int DEDICATED_INPUT_OTHER_CHANNELS[] = {
		Constants.COMPRESSOR_PRESSURE_SW, Constants.WINCH_SWITCH
	};
	public static final int DEDICATED_OUTPUTS[] =  {};
	public static final int FREE_DIO_CHANNELS[] = {1, 6, 7, 9, 10, 12, 13, 14};
	public static IInputOutput FREE_DIO[] = new IInputOutput[NUM_DIO_CHANNELS];
	

	public static boolean isFreeChannel(int channel) {
		for (int i = 0; i < FREE_DIO_CHANNELS.length; i++) {
			if (FREE_DIO_CHANNELS[i] == channel) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isInput (int channel) {
		if (FREE_DIO[channel] != null) {
			IInputOutput io = FREE_DIO[channel];
			return DigitalInput.class.equals(io);
		}
		return false;
	}
	
	public static void setToInput (int channel, boolean input) {
		if (!isFreeChannel(channel)) {
			return;
		}
		
		if ((input) && (!isInput(channel))) {
			DigitalInput dInput = new DigitalInput(channel);
			FREE_DIO[channel] = dInput;
		} else if ((!input) && (isInput(channel))) {
			DigitalOutput dOutput = new DigitalOutput(channel);
			FREE_DIO[channel] = dOutput;
		}
	}
	
	public static boolean getValue (int channel) {
		if (isInput(channel)) {
			return ((DigitalInput)FREE_DIO[channel]).get();
		}
		return false;
	}
	
	public static void setValue (int channel, boolean value) {
		if (isInput(channel)) {
			return;
		} else {
			((DigitalOutput)FREE_DIO[channel]).set(value); 
		}
	} 
	
	public static IInputOutput getInputOutput(int channel) {
		if (isFreeChannel(channel)) {
			return FREE_DIO[channel];
		}
		return null;
	}
	
	public static void clearInputOutput(int channel) {
		FREE_DIO[channel] = null;
	}

	public static void cleanup() {
		for (int i = 0; i < FREE_DIO.length; i++) {
			FREE_DIO[i] = null;
		}
	}
	
	public static Encoder getDedicatedEncoder(int channel) {
		Encoder encoder = null;
		for (int i = 0; i < DEDICATED_INPUT_ENCODER_CHANNELS.length; i++) {
			if (DEDICATED_INPUT_ENCODER_CHANNELS[i] == channel) {
				// will round down because it is integer division
				return DEDICATED_INPUT_ENCODER[i/2];
			}
		}
		return encoder;
	}
}
