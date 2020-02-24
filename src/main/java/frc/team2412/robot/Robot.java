/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2412.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team2412.robot.subsystems.constants.ControlPanelConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.Logger;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot implements Loggable {

	// Have instances of robot container and OI for easy access
	private RobotContainer m_robotContainer = RobotMap.m_robotContainer;
	@SuppressWarnings("unused")
	private OI m_OI = RobotMap.m_OI;
	
	public DriverStation driverStation = DriverStation.getInstance();
	
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		m_robotContainer.m_turretSubsystem.initTurretEncoder();
		Logger.configureLoggingAndConfig(this, false);
		Shuffleboard.startRecording();
		
		RobotState.eventName = driverStation.getEventName();
		RobotState.matchType = driverStation.getMatchType();
		RobotState.matchNumber = driverStation.getMatchNumber();
		RobotState.alliance = driverStation.getAlliance();
		RobotState.location = driverStation.getLocation();
		

	}

	/**
	 * This function is called every robot packet, no matter the mode. Use this for
	 * items like diagnostics that you want ran during disabled, autonomous,
	 * teleoperated and test.
	 *
	 * <p>
	 * This runs after the mode specific periodic functions, but before LiveWindow
	 * and SmartDashboard integrated updating.
	 */

	@Override
	public void robotPeriodic() {
		CommandScheduler.getInstance().run();
		Logger.updateEntries();
	}

	/**
	 * This function is called once when autonomous is started
	 */
	@Override
	public void autonomousInit() {

		/*
		 * Limelight Spin up turret Shoot command
		 * 
		 * Move towards tranch
		 * 
		 * 
		 * 
		 * 
		 * *
		 */

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
	}
	
	/**
	 * This function is called once when autonomous is started
	 */
	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		String color = driverStation.getGameSpecificMessage();
		if(color != null) {
			if(color.equalsIgnoreCase("R")) {
				ControlPanelConstants.TargetColor = ControlPanelConstants.redTarget;
			} else if(color.equalsIgnoreCase("G")) {
				ControlPanelConstants.TargetColor = ControlPanelConstants.greenTarget;
			} if(color.equalsIgnoreCase("B")) {
				ControlPanelConstants.TargetColor = ControlPanelConstants.blueTarget;
			}
		}
		
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
