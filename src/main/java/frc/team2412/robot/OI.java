package frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team2412.robot.commands.climb.ClimbDeployRailsCommand;
import frc.team2412.robot.commands.climb.ClimbJoystickCommand;
import frc.team2412.robot.commands.climb.ClimbRetractRailsCommand;
import frc.team2412.robot.commands.drive.DriveCommand;
import frc.team2412.robot.commands.drive.DriveShiftToHighGearCommand;
import frc.team2412.robot.commands.drive.DriveShiftToLowGearCommand;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.commands.indexer.IndexSpitCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackDownCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackInCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackOffCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackOutCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackUpCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontDownCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontInCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontOffCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontOutCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontUpCommand;
import frc.team2412.robot.commands.lift.LiftDownCommand;
import frc.team2412.robot.commands.lift.LiftUpCommand;

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
		ALIGN_STICKS(Joysticks.DRIVER_LEFT, 3);

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
		LIFT(7), FRONT_INTAKE_DOWN(2), BACK_INTAKE_DOWN(1), INTAKE_BACK_IN(4), INTAKE_BACK_OUT(3), INTAKE_FRONT_IN(6),
		INTAKE_FRONT_OUT(5);

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

		public Button createFrom(XboxController controller) {
			if (controller.getPort() != Joysticks.CODRIVER.id) {
				System.err.println("Warning! Binding button to the wrong stick!");
			}
			return new Button(() -> controller.getRawButton(this.buttonID));
		}

		public Button createFromPOV(XboxController controller) {
			if (controller.getPort() != Joysticks.CODRIVER.id) {
				System.err.println("Warning! Binding button to the wrong stick!");
			}
			return new Button(() -> controller.getPOV() == this.buttonID);
		}
	}

	public enum CodriverManualControls {
		CLIMB_MODE(5);

		public int buttonID;

		private CodriverManualControls(int buttonID) {
			this.buttonID = buttonID;
		}

		public Button createFrom(Joystick stick) {
			if (stick.getPort() != Joysticks.CODRIVER_MANUAL.id) {
				System.err.println("Warning! Binding button to the wrong stick!");
			}
			return new JoystickButton(stick, this.buttonID);
		}

		public Button createFrom(XboxController controller) {
			if (controller.getPort() != Joysticks.CODRIVER_MANUAL.id) {
				System.err.println("Warning! Binding button to the wrong stick!");
			}
			return new Button(() -> controller.getRawButton(this.buttonID));
		}

		public Button createFromPOV(XboxController controller) {
			if (controller.getPort() != Joysticks.CODRIVER_MANUAL.id) {
				System.err.println("Warning! Binding button to the wrong stick!");
			}
			return new Button(() -> controller.getPOV() == this.buttonID);
		}
	}

	Joystick driverRightStick = new Joystick(Joysticks.DRIVER_RIGHT.id);
	Joystick driverLeftStick = new Joystick(Joysticks.DRIVER_LEFT.id);
	Joystick codriverStick = new Joystick(Joysticks.CODRIVER.id);
	Joystick codriverManualStick = new Joystick(Joysticks.CODRIVER_MANUAL.id);

	public Button shifter = DriverControls.SHIFT.createFrom(driverRightStick);

	// Buttons
	public Button indexerShootButton = DriverControls.SHOOT.createFrom(driverRightStick);
	public Button indexerSpitButton = DriverControls.SPIT.createFrom(driverLeftStick);

	public Button liftButton = CodriverControls.LIFT.createFrom(codriverStick);

	public Button frontIntakeUpDown = CodriverControls.FRONT_INTAKE_DOWN.createFrom(codriverStick);
	public Button backIntakeUpDown = CodriverControls.BACK_INTAKE_DOWN.createFrom(codriverStick);

	public Button intakeFrontIn = CodriverControls.INTAKE_FRONT_IN.createFrom(codriverStick);
	public Button intakeFrontOut = CodriverControls.INTAKE_FRONT_OUT.createFrom(codriverStick);
	public Button intakeBackIn = CodriverControls.INTAKE_BACK_IN.createFrom(codriverStick);
	public Button intakeBackOut = CodriverControls.INTAKE_BACK_OUT.createFrom(codriverStick);

	public Button climbModeButton = CodriverManualControls.CLIMB_MODE.createFrom(codriverManualStick);

	// Constructor to set all of the commands and buttons
	public OI(RobotContainer robotContainer) {

		if (RobotMap.INTAKE_CONNECTED) {
			// INTAKE front
			// intakeFrontOnButton.whenPressed(new
			// IntakeFrontBothOnCommandGroup(robotContainer.m_intakeUpDownSubsystem,
			// robotContainer.m_intakeMotorOnOffSubsystem));
			// intakeFrontOffButton.whenPressed(new
			// IntakeFrontBothOffCommandGroup(robotContainer.m_intakeUpDownSubsystem,
			// robotContainer.m_intakeMotorOnOffSubsystem));
			//
			// INTAKE back
			// intakeBackOnButton.whenPressed(new
			// IntakeBackBothOnCommandGroup(robotContainer.m_intakeUpDownSubsystem,
			// robotContainer.m_intakeMotorOnOffSubsystem));
			// intakeBackOffButton.whenPressed(new
			// IntakeBackBothOffCommandGroup(robotContainer.m_intakeUpDownSubsystem,
			// robotContainer.m_intakeMotorOnOffSubsystem));

			frontIntakeUpDown.whenReleased(new IntakeFrontDownCommand(robotContainer.m_intakeUpDownSubsystem));
			frontIntakeUpDown.whenPressed(new IntakeFrontUpCommand(robotContainer.m_intakeUpDownSubsystem));

			backIntakeUpDown.whenReleased(new IntakeBackDownCommand(robotContainer.m_intakeUpDownSubsystem));
			backIntakeUpDown.whenPressed(new IntakeBackUpCommand(robotContainer.m_intakeUpDownSubsystem));

			// intakeFrontIn.whenPressed(new
			// IntakeFrontInCommand(robotContainer.m_intakeMotorOnOffSubsystem)
			// .andThen(new InstantCommand(() ->
			// robotContainer.m_indexerMotorSubsystem.setFrontMotor(-1))));
			intakeFrontIn.whenReleased(new IntakeFrontOffCommand(robotContainer.m_intakeMotorOnOffSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.stopAllMotors())));

			intakeFrontOut.whenPressed(new IntakeFrontOutCommand(robotContainer.m_intakeMotorOnOffSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setFrontMotor(1))));
			intakeFrontOut.whenReleased(new IntakeFrontOffCommand(robotContainer.m_intakeMotorOnOffSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setFrontMotor(0))));

			// intakeBackIn.whenPressed(new
			// IntakeBackInCommand(robotContainer.m_intakeMotorOnOffSubsystem)
			// .andThen(new InstantCommand(() ->
			// robotContainer.m_indexerMotorSubsystem.setBackMotor(-1))));
			intakeBackIn.whenReleased(new IntakeBackOffCommand(robotContainer.m_intakeMotorOnOffSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.stopAllMotors())));

			intakeBackOut.whenPressed(new IntakeBackOutCommand(robotContainer.m_intakeMotorOnOffSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setBackMotor(1))));
			intakeBackOut.whenReleased(new IntakeBackOffCommand(robotContainer.m_intakeMotorOnOffSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setBackMotor(0))));
					intakeFrontIn.whenPressed(new IntakeFrontInCommand(robotContainer.m_intakeMotorOnOffSubsystem)
					.andThen(new InstantCommand(() -> RobotState.m_unbalancedSide = RobotState.UnbalancedSide.FRONT)));
				   intakeBackIn.whenPressed(new IntakeBackInCommand(robotContainer.m_intakeMotorOnOffSubsystem)
					.andThen(new InstantCommand(() -> RobotState.m_unbalancedSide = RobotState.UnbalancedSide.BACK)));
			
		}

		if (RobotMap.CONTROL_PANEL_CONNECTED) {
			// CONTROL PANEL
			// controlPanelSpinThreeTimesButton
			// .whenPressed(new
			// RotateControlPanelCommand(robotContainer.m_controlPanelColorSubsystem));
			// controlPanelSetToTargetButton
			// .whenPressed(new
			// SetToTargetColorCommand(robotContainer.m_controlPanelColorSubsystem));
		}

		if (RobotMap.CLIMB_CONNECTED) {
			// climbDeployRailsButton.whenActive(new
			// ClimbDeployRailsCommand(robotContainer.m_climbLiftSubsystem));
			// climbExtendArmButton.whenActive(new
			// ClimbExtendArmCommand(robotContainer.m_climbMotorSubsystem));
			// climbRetractArmButton.whenActive(new
			// ClimbExtendArmCommand(robotContainer.m_climbMotorSubsystem));
			// climbRetractRailsButton.whenActive(new
			// ClimbRetractRailsCommand(robotContainer.m_climbLiftSubsystem));
			// climbStopArmButton.whenActive(new
			// ClimbStopArmCommand(robotContainer.m_climbMotorSubsystem));
			climbModeButton
					.whileHeld(new ClimbJoystickCommand(codriverManualStick, robotContainer.m_climbMotorSubsystem));
			climbModeButton.whenPressed(new ClimbDeployRailsCommand(robotContainer.m_climbLiftSubsystem));
			climbModeButton.whenReleased(new ClimbRetractRailsCommand(robotContainer.m_climbLiftSubsystem));
		}

		if (RobotMap.INDEX_CONNECTED) {
			indexerSpitButton.whileHeld(new IndexSpitCommand(robotContainer.m_indexerSensorSubsystem,
					robotContainer.m_indexerMotorSubsystem, robotContainer.m_intakeMotorOnOffSubsystem));

		//	Command indexShootCommand = new IndexShootCommand(robotContainer.m_indexerSensorSubsystem,
		//			robotContainer.m_indexerMotorSubsystem, robotContainer.m_intakeMotorOnOffSubsystem);

			indexerShootButton.whenPressed(new IndexShootCommand(robotContainer.m_indexerSensorSubsystem,
			robotContainer.m_indexerMotorSubsystem, robotContainer.m_intakeMotorOnOffSubsystem));

			indexerShootButton
					.whenReleased(new InstantCommand(() -> CommandScheduler.getInstance().cancel(new IndexShootCommand(robotContainer.m_indexerSensorSubsystem,
					robotContainer.m_indexerMotorSubsystem, robotContainer.m_intakeMotorOnOffSubsystem))));

		}

		if (RobotMap.DRIVE_BASE_CONNECTED) {
			robotContainer.m_driveBaseSubsystem.setDefaultCommand(new DriveCommand(robotContainer.m_driveBaseSubsystem,
					driverRightStick, driverLeftStick, DriverControls.ALIGN_STICKS.createFrom(driverLeftStick)));

			shifter.whenPressed(new DriveShiftToHighGearCommand(robotContainer.m_driveBaseSubsystem));
			shifter.whenReleased(new DriveShiftToLowGearCommand(robotContainer.m_driveBaseSubsystem));
		}

		// LIFT
		if (RobotMap.LIFT_CONNECTED) {
			liftButton.whenPressed(
					new LiftUpCommand(robotContainer.m_liftSubsystem, robotContainer.m_indexerMotorSubsystem));
			liftButton.whenReleased(
					new LiftDownCommand(robotContainer.m_liftSubsystem, robotContainer.m_indexerMotorSubsystem));
		}

		// Trigger intakeUpWhenFiveBalls = new Trigger(RobotState::hasFiveBalls);
		if (RobotMap.INTAKE_CONNECTED) {
			// intakeUpWhenFiveBalls.whenActive(new
			// IntakeBothUpCommand(robotContainer.m_intakeUpDownSubsystem));
		}

	}
}
