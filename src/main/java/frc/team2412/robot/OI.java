package frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.team2412.robot.commands.drive.DriveCommand;
import frc.team2412.robot.commands.drive.DriveShiftToHighGearCommand;
import frc.team2412.robot.commands.drive.DriveShiftToLowGearCommand;
import frc.team2412.robot.commands.hood.HoodAdjustCommand;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.commands.indexer.IndexSpitCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackDownCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackInCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackOffCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackUpCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontDownCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontInCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontOffCommand;
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
		SOMETHING(0), LIFT_UP(XboxController.Button.kY.value), LIFT_DOWN(XboxController.Button.kA.value),
		FRONT_INTAKE_DOWN(XboxController.Button.kBumperRight.value),
		BACK_INTAKE_DOWN(XboxController.Button.kBumperLeft.value), HOOD_UP(0), HOOD_DOWN(180),
		INDEX_FOWARD(XboxController.Button.kB.value), INDEX_BACKWARD(XboxController.Button.kX.value);

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

	Joystick driverRightStick = new Joystick(Joysticks.DRIVER_RIGHT.id);
	Joystick driverLeftStick = new Joystick(Joysticks.DRIVER_LEFT.id);
	XboxController codriverStick = new XboxController(Joysticks.CODRIVER.id);
	// Joystick codriverManualStick = new Joystick(Joysticks.CODRIVER_MANUAL.id);

	public Button shifter = DriverControls.SHIFT.createFrom(driverRightStick);

	// Buttons
	public Button indexerShootButton = DriverControls.SHOOT.createFrom(driverRightStick);
	public Button indexerSpitButton = DriverControls.SPIT.createFrom(driverLeftStick);

	public Button intakeFrontDownButton = CodriverControls.FRONT_INTAKE_DOWN.createFrom(codriverStick);
	public Button intakeBackDownButton = CodriverControls.BACK_INTAKE_DOWN.createFrom(codriverStick);

	public Button liftUpButton = CodriverControls.LIFT_UP.createFrom(codriverStick);
	public Button liftDownButton = CodriverControls.LIFT_DOWN.createFrom(codriverStick);

	public Button hoodUpButton = CodriverControls.HOOD_UP.createFromPOV(codriverStick);
	public Button hoodDownButton = CodriverControls.HOOD_DOWN.createFromPOV(codriverStick);

	public Button indexFowardButton = CodriverControls.INDEX_FOWARD.createFrom(codriverStick);
	public Button indexBackwardButton = CodriverControls.INDEX_BACKWARD.createFrom(codriverStick);

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
			// IntakeBackBothOffCommandGroup(robotContainer.m_intakeUpDownSubsystem,\][]
			// robotContainer.m_intakeMotorOnOffSubsystem));

			intakeFrontDownButton.whenPressed(new IntakeFrontDownCommand(robotContainer.m_intakeUpDownSubsystem)
					.andThen(new IntakeFrontInCommand(robotContainer.m_intakeMotorOnOffSubsystem))
					.andThen(new InstantCommand(() -> System.out.println("Front Down"))));
			intakeFrontDownButton.whenReleased(new IntakeFrontUpCommand(robotContainer.m_intakeUpDownSubsystem)
					.andThen(new IntakeFrontOffCommand(robotContainer.m_intakeMotorOnOffSubsystem))
					.andThen(new InstantCommand(() -> System.out.println("Front Up"))));

			intakeBackDownButton.whenPressed(new IntakeBackDownCommand(robotContainer.m_intakeUpDownSubsystem)
					.andThen(new IntakeBackInCommand(robotContainer.m_intakeMotorOnOffSubsystem)));
			intakeBackDownButton.whenReleased(new IntakeBackUpCommand(robotContainer.m_intakeUpDownSubsystem)
					.andThen(new IntakeBackOffCommand(robotContainer.m_intakeMotorOnOffSubsystem)));

		}

		if (RobotMap.SHOOTER_CONNECTED) {
			hoodDownButton.whileHeld(new HoodAdjustCommand(robotContainer.m_hoodSubsystem, -0.05));
			hoodUpButton.whileHeld(new HoodAdjustCommand(robotContainer.m_hoodSubsystem, 0.05));
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
		}

		if (RobotMap.INDEX_CONNECTED) {
			indexerSpitButton.whenPressed(new IndexSpitCommand(robotContainer.m_indexerSensorSubsystem,
					robotContainer.m_indexerMotorSubsystem, robotContainer.m_intakeMotorOnOffSubsystem));

			indexerShootButton.whenPressed(new IndexShootCommand(robotContainer.m_indexerSensorSubsystem,
					robotContainer.m_indexerMotorSubsystem));

			indexFowardButton.whenPressed(new InstantCommand(() -> {
				robotContainer.m_indexerMotorSubsystem.setFrontMotor(0.5);
				robotContainer.m_indexerMotorSubsystem.setBackMotor(-0.5);
				robotContainer.m_indexerMotorSubsystem.setMidMotor(-0.2);
			}));
			indexBackwardButton.whenPressed(new InstantCommand(() -> {
				robotContainer.m_indexerMotorSubsystem.setFrontMotor(-0.5);
				robotContainer.m_indexerMotorSubsystem.setBackMotor(0.5);
				robotContainer.m_indexerMotorSubsystem.setMidMotor(-0.2);
			}));

			indexBackwardButton.or(indexFowardButton)
					.whenInactive(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.stopAllMotors()));

		}

		if (RobotMap.DRIVE_BASE_CONNECTED) {
			robotContainer.m_driveBaseSubsystem.setDefaultCommand(new DriveCommand(robotContainer.m_driveBaseSubsystem,
					driverRightStick, driverLeftStick, DriverControls.ALIGN_STICKS.createFrom(driverLeftStick)));

			shifter.whenPressed(new DriveShiftToHighGearCommand(robotContainer.m_driveBaseSubsystem));
			shifter.whenReleased(new DriveShiftToLowGearCommand(robotContainer.m_driveBaseSubsystem));
		}

		// LIFT
		if (RobotMap.LIFT_CONNECTED) {
			liftUpButton.whenPressed(
					new LiftUpCommand(robotContainer.m_liftSubsystem, robotContainer.m_indexerMotorSubsystem));
			liftDownButton.whenPressed(
					new LiftDownCommand(robotContainer.m_liftSubsystem, robotContainer.m_indexerMotorSubsystem));
		}

		Trigger intakeUpWhenFiveBalls = new Trigger(RobotState::hasFiveBalls);
		if (RobotMap.INTAKE_CONNECTED) {
			// intakeUpWhenFiveBalls.whenActive(new
			// IntakeBothUpCommand(robotContainer.m_intakeUpDownSubsystem));
		}

	}
}
