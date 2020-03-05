/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2412.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team2412.robot.commands.hood.HoodAdjustCommand;
import frc.team2412.robot.commands.hood.HoodWithdrawCommand;
import frc.team2412.robot.commands.indexer.IndexCommand;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.Logger;
import io.github.oblarg.oblog.annotations.Log;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot implements Loggable {

	public double timeRemaining;

	// Have instances of robot container and OI for easy access
	private RobotContainer m_robotContainer = RobotMap.m_robotContainer;
	@SuppressWarnings("unused")
	private OI m_OI = RobotMap.m_OI;

	Command autoCommand;

	@Log(tabName = "Robot stuff")
	public double voltage = RobotController.getBatteryVoltage();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		Logger.configureLoggingAndConfig(m_robotContainer, false);
		// Shuffleboard.startRecording();

		DriverStation driverStation = DriverStation.getInstance();

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
		//CommandScheduler.getInstance().schedule(new IndexCommand(RobotMap.m_robotContainer.m_indexerMotorSubsystem, RobotMap.m_robotContainer.m_indexerSensorSubsystem));

		CommandScheduler.getInstance().run();
		Logger.updateEntries();
		m_robotContainer.logger.periodic();
		//System.out.println(new DigitalInput(8).get());
		
	//	System.out.println(RobotController.getBatteryVoltage());
	}

	/**
	 * This function is called once when autonomous is started
	 */
	@Override
	public void autonomousInit() {
		timeRemaining = 150.0;
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

		autoCommand = new HoodWithdrawCommand(m_robotContainer.m_hoodSubsystem)
				.andThen(new HoodAdjustCommand(m_robotContainer.m_hoodSubsystem, .300))
				.andThen(new InstantCommand(() -> m_robotContainer.m_flywheelSubsystem.setSpeed(-0.9)))
				.andThen(new WaitCommand(2))
				.andThen(new InstantCommand(() -> m_robotContainer.m_indexerMotorSubsystem.setMidMotor(1)))
				.andThen(new InstantCommand(() -> m_robotContainer.m_indexerMotorSubsystem.setBackMotor(-1)))
				.andThen(new InstantCommand(() -> m_robotContainer.m_indexerMotorSubsystem.setFrontMotor(-1)))
				.andThen(new WaitCommand(8))
				.andThen(new InstantCommand(() -> m_robotContainer.m_driveBaseSubsystem.tankDriveVolts(-12, -12)))
				.andThen(new WaitCommand(1))
				.andThen(new InstantCommand(() -> m_robotContainer.m_driveBaseSubsystem.tankDriveVolts(0, 0)));
		CommandScheduler.getInstance().schedule(autoCommand);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		timeRemaining -= 0.02;
	}

	/**
	 * This function is called once when autonomous is started
	 */
	@Override
	public void teleopInit() {
		timeRemaining = 135.0;
		//CommandScheduler.getInstance().cancel(autoCommand);
		CommandScheduler.getInstance()
				.schedule(new InstantCommand(() -> m_robotContainer.m_indexerMotorSubsystem.stopAllMotors()));
		//m_robotContainer.m_flywheelSubsystem.setSpeed(-0.25);
		//m_robotContainer.m_LimelightSubsystem.stopLimelight();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		timeRemaining -= 0.02;

		double val = m_OI.codriverStick.getY() * 0.5 + 0.5;
		//m_robotContainer.m_hoodSubsystem.setServo(val);

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
