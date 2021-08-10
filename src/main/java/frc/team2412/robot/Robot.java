/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2412.robot;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team2412.robot.autonomous.Autonomous;
import frc.team2412.robot.commands.hood.HoodAdjustCommand;
import frc.team2412.robot.commands.hood.HoodWithdrawCommand;
import frc.team2412.robot.commands.indexer.IndexBitmapCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontDownCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontInCommand;
import frc.team2412.robot.commands.turret.TurretFollowLimelightCommand;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

	public double timeRemaining;

	private RobotContainer robotContainer = RobotMap.m_robotContainer;
	private OI OI = RobotMap.m_OI;

	Command autoCommand;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.driveRightFrontMotor.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveRightBackMotor.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveLeftBackMotor.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveLeftBackMotor.setNeutralMode(NeutralMode.Brake);
		System.out.println("gyro connected: " + RobotMap.driveGyro.isConnected());
		if (RobotMap.driveGyro.isConnected()) {
			while (RobotMap.driveGyro.isCalibrating()) {
				try {
					System.out.println("Waiting for gyro calibration");
					Thread.sleep(50);
				} catch (InterruptedException ignored) {
				}
			}
		}

		RobotMap.intakeBackMotor.setSmartCurrentLimit(30);
		RobotMap.indexBackMotor.setSmartCurrentLimit(30);

		RobotMap.turretMotor.enableCurrentLimit(true);
		RobotMap.turretMotor.configPeakCurrentLimit(5, 100);
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
		timeRemaining = Timer.getMatchTime();
		// System.out.print("shooter: "+robotContainer.m_flywheelSubsystem.currentLeftSpeed());
		// System.out.println("   hood: "+robotContainer.m_hoodSubsystem.getServo());

	}

	/**
	 * This function is called once when autonomous is started
	 */
	@Override
	public void autonomousInit() {
		/*
		 * Limelight Spin up turret Shoot command
		 *
		 * Move towards trench
		 *
		 *
		 *
		 *
		 * *
		 */

		// autoCommand = new HoodWithdrawCommand(robotContainer.m_hoodSubsystem)
		// 		.andThen(new HoodAdjustCommand(robotContainer.m_hoodSubsystem, .300))
		// 		.andThen(new InstantCommand(() -> robotContainer.m_flywheelSubsystem.setSpeed(-0.9)))
		// 		.andThen(new WaitCommand(2))
		// 		// Add index shooting back in here
		// 		.andThen(new WaitCommand(8))
		// 		.andThen(new InstantCommand(() -> robotContainer.m_driveBaseSubsystem.tankDriveVolts(-12, -12)))
		// 		.andThen(new WaitCommand(1))
		// 		.andThen(new InstantCommand(() -> robotContainer.m_driveBaseSubsystem.tankDriveVolts(0, 0)));
		autoCommand = Autonomous.getBouncePathCommand();
		//autoCommand = Autonomous.getBarrelPathCommand();

		//autoCommand = new ParallelCommandGroup(Autonomous.getSearchPathCommand(),
		//	new IntakeFrontDownCommand(robotContainer.m_intakeUpDownSubsystem, false),
		//	new IntakeFrontInCommand(robotContainer.m_intakeMotorOnOffSubsystem));
		CommandScheduler.getInstance().schedule(autoCommand);
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
		System.out.println(Arrays.stream(NetworkTableInstance.getDefault().getEntries("", 0)).map(NetworkTableEntry::getName).collect(Collectors.toList()));

		NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

		for(String key : table.getKeys()) {
			System.out.println(table.getEntry(key).getName());
		}

		CommandScheduler.getInstance().cancel(autoCommand);
		robotContainer.m_flywheelSubsystem.setSpeed(0.0);
		RobotMap.driveLeftFrontMotor.setSelectedSensorPosition(0);
		RobotMap.driveRightFrontMotor.setSelectedSensorPosition(0);
		// m_robotContainer.m_flywheelSubsystem.setSpeed(-0.25);

		// robotContainer.m_indexerMotorSubsystem.setDefaultCommand(new IndexBitmapCommand(
		// 		robotContainer.m_indexerMotorSubsystem, robotContainer.m_intakeMotorOnOffSubsystem));



				// m_robotContainer.m_hoodSubsystem.setDefaultCommand(
		// new HoodJoystickCommand(m_robotContainer.m_hoodSubsystem, () ->
		// m_OI.codriverStick.getY() * 0.5 + 0.5));

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		robotContainer.m_limelightSubsystem.getValues();
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
