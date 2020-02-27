package frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.team2412.robot.commands.drive.DriveCommand;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.commands.indexer.IndexSpitCommand;
import frc.team2412.robot.commands.intake.IntakeBothInCommandGroup;
import frc.team2412.robot.commands.intake.IntakeBothOffCommandGroup;
import frc.team2412.robot.commands.intake.IntakeBothOutCommandGroup;
import frc.team2412.robot.commands.intake.IntakeBothUpCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackDownCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackUpCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontDownCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontUpCommand;

//This is the class in charge of all the buttons and joysticks that the drivers will use to control the robot
public class OI {

	public enum Joysticks {
		DRIVER_RIGHT(0), DRIVER_LEFT(1), CODRIVER(2), CODRIVER_MANUAL(3);

		public int id;

		private Joysticks(int id) {
			this.id = id;
		}
	}

	public enum DriverControls {
		SHOOT(Joysticks.DRIVER_RIGHT, 1), SHIFT(Joysticks.DRIVER_RIGHT, 2), SPIT(Joysticks.DRIVER_LEFT, 1),
		ALIGN_STICKS(Joysticks.DRIVER_LEFT, 3), //
		// temp testing controls
		FRONT_INTAKE_DOWN(Joysticks.DRIVER_RIGHT, 6), BACK_INTAKE_DOWN(Joysticks.DRIVER_RIGHT, 5),
		INTAKE_IN(Joysticks.DRIVER_RIGHT, 3), INTAKE_OUT(Joysticks.DRIVER_RIGHT, 4);

		public Joysticks stick;
		public int buttonID;

		private DriverControls(Joysticks stick, int buttonID) {
			this.stick = stick;
			this.buttonID = buttonID;
		}

		public Button createFrom(Joystick stick) {
			if (stick.getPort() != this.stick.id) {
				System.err.println("Warning! Binding button to the wrong stick!");
			}
			return new JoystickButton(stick, this.buttonID);
		}
	}

	public enum CodriverControls {
		SOMETHING(0);

		public int buttonID;

		private CodriverControls(int buttonID) {
			this.buttonID = buttonID;
		}

		public Button createFrom(Joystick stick) {
			if (stick.getPort() != Joysticks.CODRIVER.id) {
				System.err.println("Warning! Binding button to the wrong stick!");
			}
			return new JoystickButton(stick, this.buttonID);
		}
	}

	Joystick driverRightStick = new Joystick(Joysticks.DRIVER_RIGHT.id);
	Joystick driverLeftStick = new Joystick(Joysticks.DRIVER_LEFT.id);
	Joystick codriverStick = new Joystick(Joysticks.CODRIVER.id);
	Joystick codriverManualStick = new Joystick(Joysticks.CODRIVER_MANUAL.id);

	// Buttons
	public Button indexerShootButton = DriverControls.SHOOT.createFrom(driverRightStick);
	public Button indexerSpitButton = DriverControls.SPIT.createFrom(driverLeftStick);

	public Button intakeFrontDownButton = DriverControls.FRONT_INTAKE_DOWN.createFrom(driverRightStick);
	public Button intakeBackDownButton = DriverControls.BACK_INTAKE_DOWN.createFrom(driverRightStick);
	public Button intakeInButton = DriverControls.INTAKE_IN.createFrom(driverRightStick);
	public Button intakeOutButton = DriverControls.INTAKE_OUT.createFrom(driverRightStick);

	// Constructor to set all of the commands and buttons
	public OI(RobotContainer robotContainer) {
		if (RobotMap.INTAKE_CONNECTED) {
			// INTAKE front
//			intakeFrontOnButton.whenPressed(new IntakeFrontBothOnCommandGroup(robotContainer.m_intakeUpDownSubsystem,
//					robotContainer.m_intakeMotorOnOffSubsystem));
//			intakeFrontOffButton.whenPressed(new IntakeFrontBothOffCommandGroup(robotContainer.m_intakeUpDownSubsystem,
//					robotContainer.m_intakeMotorOnOffSubsystem));
//
			// INTAKE back
//			intakeBackOnButton.whenPressed(new IntakeBackBothOnCommandGroup(robotContainer.m_intakeUpDownSubsystem,
//					robotContainer.m_intakeMotorOnOffSubsystem));
//			intakeBackOffButton.whenPressed(new IntakeBackBothOffCommandGroup(robotContainer.m_intakeUpDownSubsystem,
//					robotContainer.m_intakeMotorOnOffSubsystem));

			intakeFrontDownButton.whenPressed(new IntakeFrontDownCommand(robotContainer.m_intakeLiftSubsystem));
			intakeFrontDownButton.whenReleased(new IntakeFrontUpCommand(robotContainer.m_intakeLiftSubsystem));

			intakeBackDownButton.whenPressed(new IntakeBackDownCommand(robotContainer.m_intakeLiftSubsystem));
			intakeBackDownButton.whenReleased(new IntakeBackUpCommand(robotContainer.m_intakeLiftSubsystem));

			intakeInButton.whenPressed(new IntakeBothInCommandGroup(robotContainer.m_intakeMotorSubsystem));
			intakeOutButton.whenPressed(new IntakeBothOutCommandGroup(robotContainer.m_intakeMotorSubsystem));

			intakeInButton.or(intakeOutButton)
					.whenInactive(new IntakeBothOffCommandGroup(robotContainer.m_intakeMotorSubsystem));
		}

		if (RobotMap.CONTROL_PANEL_CONNECTED) {
			// CONTROL PANEL
//			controlPanelSpinThreeTimesButton
//					.whenPressed(new RotateControlPanelCommand(robotContainer.m_controlPanelColorSubsystem));
//			controlPanelSetToTargetButton
//					.whenPressed(new SetToTargetColorCommand(robotContainer.m_controlPanelColorSubsystem));
		}

		if (RobotMap.CLIMB_CONNECTED) {
//			climbDeployRailsButton.whenActive(new ClimbDeployRailsCommand(robotContainer.m_climbLiftSubsystem));
//			climbExtendArmButton.whenActive(new ClimbExtendArmCommand(robotContainer.m_climbMotorSubsystem));
//			climbRetractArmButton.whenActive(new ClimbExtendArmCommand(robotContainer.m_climbMotorSubsystem));
//			climbRetractRailsButton.whenActive(new ClimbRetractRailsCommand(robotContainer.m_climbLiftSubsystem));
//			climbStopArmButton.whenActive(new ClimbStopArmCommand(robotContainer.m_climbMotorSubsystem));
		}

		if (RobotMap.INDEX_CONNECTED) {
			indexerSpitButton.whenPressed(new IndexSpitCommand(robotContainer.m_indexerSensorSubsystem,
					robotContainer.m_indexerMotorSubsystem, robotContainer.m_intakeMotorSubsystem));

			indexerShootButton.whenPressed(new IndexShootCommand(robotContainer.m_indexerSensorSubsystem,
					robotContainer.m_indexerMotorSubsystem)
							.andThen(new WaitCommand(2).alongWith(new InstantCommand(() -> {
								robotContainer.m_indexerMotorSubsystem.setMidMotor(1);
							})))
							.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setMidMotor(0))));
		}

		if (RobotMap.DRIVE_BASE_CONNECTED) {
			robotContainer.m_driveBaseSubsystem.setDefaultCommand(new DriveCommand(robotContainer.m_driveBaseSubsystem,
					driverRightStick, driverLeftStick, DriverControls.ALIGN_STICKS.createFrom(driverLeftStick)));
		}

		// LIFT
		if (RobotMap.LIFT_CONNECTED) {
//			liftUpButton.whenPressed(
//					new LiftUpCommand(robotContainer.m_liftSubsystem));
//			liftDownButton.whenPressed(
//					new LiftDownCommand(robotContainer.m_liftSubsystem));
		}

		Trigger intakeUpWhenFiveBalls = new Trigger(RobotState::hasFiveBalls);
		if (RobotMap.INTAKE_CONNECTED) {
			intakeUpWhenFiveBalls.whenActive(new IntakeBothUpCommand(robotContainer.m_intakeLiftSubsystem,
					robotContainer.m_intakeMotorSubsystem));
		}

	}
}
