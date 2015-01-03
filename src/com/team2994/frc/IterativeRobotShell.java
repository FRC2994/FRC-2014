package com.team2994.frc;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;


public class IterativeRobotShell extends IterativeRobot {
	
	
    /* ----------- Overridable initialization code -----------------*/
	
    /**
     * Robot-wide initialization code should go here.
     *
     * Users should override this method for default Robot-wide initialization which will
     * be called when the robot is first powered on.  It will be called exactly 1 time.
     */
	public void robotInit() {
        System.out.println("Default IterativeRobot.robotInit() method... Overload me!");
    }

    /**
     * Initialization code for disabled mode should go here.
     *
     * Users should override this method for initialization code which will be called each time
     * the robot enters disabled mode.
     */
	public void disabledInit() {
        System.out.println("Default IterativeRobot.disabledInit() method... Overload me!");
    }

    /**
     * Initialization code for autonomous mode should go here.
     *
     * Users should override this method for initialization code which will be called each time
     * the robot enters autonomous mode.
     */
	public void autonomousInit() {
        System.out.println("Default IterativeRobot.autonomousInit() method... Overload me!");
    }

    /**
     * Initialization code for teleop mode should go here.
     *
     * Users should override this method for initialization code which will be called each time
     * the robot enters teleop mode.
     */
	public void teleopInit() {
        System.out.println("Default IterativeRobot.teleopInit() method... Overload me!");
    }
    
    /**
     * Initialization code for test mode should go here.
     * 
     * Users should override this method for initialization code which will be called each time
     * the robot enters test mode.
     */
	public void testInit() {
        System.out.println("Default IterativeRobot.testInit() method... Overload me!");
    }

   /* ----------- Overridable periodic code -----------------*/

    private boolean dpFirstRun = true;
    /**
     * Periodic code for disabled mode should go here.
     *
     * Users should override this method for code which will be called periodically at a regular
     * rate while the robot is in disabled mode.
     */
	public void disabledPeriodic() {
        if (dpFirstRun) {
            System.out.println("Default IterativeRobot.disabledPeriodic() method... Overload me!");
            dpFirstRun = false;
        }
        Timer.delay(0.001);
    }

    private boolean apFirstRun = true;

    /**
     * Periodic code for autonomous mode should go here.
     *
     * Users should override this method for code which will be called periodically at a regular
     * rate while the robot is in autonomous mode.
     */
	public void autonomousPeriodic() {
        if (apFirstRun) {
            System.out.println("Default IterativeRobot.autonomousPeriodic() method... Overload me!");
            apFirstRun = false;
        }
        Timer.delay(0.001);
    }

    private boolean tpFirstRun = true;

    /**
     * Periodic code for teleop mode should go here.
     *
     * Users should override this method for code which will be called periodically at a regular
     * rate while the robot is in teleop mode.
     */
	public void teleopPeriodic() {
        if (tpFirstRun) {
            System.out.println("Default IterativeRobot.teleopPeriodic() method... Overload me!");
            tpFirstRun = false;
        }
        Timer.delay(0.001);
    }
    
    private boolean tmpFirstRun = true;
    
    /**
     * Periodic code for test mode should go here
     * 
     * Users should override this method for code which will be called periodically at a regular rate
     * while the robot is in test mode.
     */
	public void testPeriodic() {
        if (tmpFirstRun) {
            System.out.println("Default IterativeRobot.testPeriodic() method... Overload me!");
            tmpFirstRun = false;
        }
    }
}
