package com.team2994.frc;

import edu.wpi.first.wpilibj.DigitalInput;

public class EDigitalInput extends DigitalInput {

	private ButtonEntry buttonEntry;
	
	public EDigitalInput(int channel) {
		super(channel);
		initialize();
	}

	public EDigitalInput(int moduleNumber, int channel) {
		super(moduleNumber, channel);
		initialize();
	}
	
	public void initialize() {
		buttonEntry.setEnabled(false);
		buttonEntry.setEvent(ButtonEntry.EVENT_ERR);
		buttonEntry.setState(ButtonEntry.STATE_ERR);
	}
	
	public int getEvent() {
		return buttonEntry.getEvent();
	}
	
	public int getState() {
		return buttonEntry.getState();
	}
	
	public void update() {
		int newState;
		
		newState = get() ? ButtonEntry.STATE_OPEN: ButtonEntry.STATE_CLOSED;
		if (newState == buttonEntry.getState())	{
			buttonEntry.setEvent(ButtonEntry.EVENT_NONE);
		} else {
			if (newState == ButtonEntry.STATE_CLOSED) {
				buttonEntry.setEvent(ButtonEntry.EVENT_CLOSED);
			} else {
				buttonEntry.setEvent(ButtonEntry.EVENT_OPENED);
			}
			buttonEntry.setState(newState);
		}
	}
}
