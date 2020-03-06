package frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team2412.robot.RobotState.UnbalancedSide;
import frc.team2412.robot.commands.climb.ClimbDeployRailsCommand;
import frc.team2412.robot.commands.climb.ClimbJoystickCommand;
import frc.team2412.robot.commands.climb.ClimbRetractRailsCommand;
import frc.team2412.robot.commands.drive.DriveCommand;
import frc.team2412.robot.commands.drive.DriveShiftToHighGearCommand;
import frc.team2412.robot.commands.drive.DriveShiftToLowGearCommand;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.commands.indexer.IndexSwitchFourCommand;
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

	public static interface ButtonEnumInterface {

		public int getButtonID();

		public int getJoystickID();

		public default Button createFrom(Joystick stick) {
			if (stick.getPort() != getJoystickID()) {
				System.err.println("Warning! Binding button to the wrong stick!");
			}
			return new JoystickButton(stick, getButtonID());
		}

		public default Button createFrom(XboxController controller) {
			if (controller.getPort() != getJoystickID()) {
				System.err.println("Warning! Binding button to the wrong stick!");
			}
			return new Button(() -> controller.getRawButton(getButtonID()));
		}

		public default Button createFromPOV(XboxController controller) {
			if (controller.getPort() != getJoystickID()) {
				System.err.println("Warning! Binding button to the wrong stick!");
			}
			return new Button(() -> controller.getPOV() == getButtonID());
		}
	}

	public enum Joysticks {
		DRIVER_RIGHT(0), DRIVER_LEFT(1), CODRIVER(2), CODRIVER_MANUAL(3);

		public int id;

		private Joysticks(int id) {
			this.id = id;
		}
	}

	public enum DriverControls implements ButtonEnumInterface {
		SHOOT(Joysticks.DRIVER_RIGHT, 1), SHIFT(Joysticks.DRIVER_RIGHT, 2), SPIT(Joysticks.DRIVER_LEFT, 1),
		ALIGN_STICKS(Joysticks.DRIVER_LEFT, 3);

		public Joysticks stick;
		public int buttonID;

		private DriverControls(Joysticks stick, int buttonID) {
			this.stick = stick;
			this.buttonID = buttonID;
		}

		@Override
		public int getButtonID() {
			return this.buttonID;
		}

		@Override
		public int getJoystickID() {
			return stick.id;
		}

	}

	public enum CodriverControls implements ButtonEnumInterface {
		LIFT(7), FRONT_INTAKE_DOWN(2), BACK_INTAKE_DOWN(1), INTAKE_BACK_IN(4), INTAKE_BACK_OUT(3), INTAKE_FRONT_IN(6),
		INTAKE_FRONT_OUT(5);

		public int buttonID;

		private CodriverControls(int buttonID) {
			this.buttonID = buttonID;
		}

		@Override
		public int getButtonID() {
			return this.buttonID;
		}

		@Override
		public int getJoystickID() {
			return Joysticks.CODRIVER.id;
		}

	}

	public enum CodriverManualControls implements ButtonEnumInterface {
		CLIMB_MODE(5);

		public int buttonID;

		private CodriverManualControls(int buttonID) {
			this.buttonID = buttonID;
		}

		@Override
		public int getButtonID() {
			return this.buttonID;
		}

		@Override
		public int getJoystickID() {
			return Joysticks.CODRIVER_MANUAL.id;
		}

	}

	// Joysticks
	public final Joystick driverRightStick = new Joystick(Joysticks.DRIVER_RIGHT.id);
	public final Joystick driverLeftStick = new Joystick(Joysticks.DRIVER_LEFT.id);
	public final Joystick codriverStick = new Joystick(Joysticks.CODRIVER.id);
	public final Joystick codriverManualStick = new Joystick(Joysticks.CODRIVER_MANUAL.id);

	// Driver Controls
	public final Button shifter = DriverControls.SHIFT.createFrom(driverRightStick);
	public final Button indexerShootButton = DriverControls.SHOOT.createFrom(driverRightStick);
	public final Button indexerSpitButton = DriverControls.SPIT.createFrom(driverLeftStick);

	// Lift Controls
	public final Button liftButton = CodriverControls.LIFT.createFrom(codriverStick);

	// Intake Controls
	public final Button frontIntakeUpDown = CodriverControls.FRONT_INTAKE_DOWN.createFrom(codriverStick);
	public final Button backIntakeUpDown = CodriverControls.BACK_INTAKE_DOWN.createFrom(codriverStick);
	public final Button intakeFrontIn = CodriverControls.INTAKE_FRONT_IN.createFrom(codriverStick);
	public final Button intakeFrontOut = CodriverControls.INTAKE_FRONT_OUT.createFrom(codriverStick);
	public final Button intakeBackIn = CodriverControls.INTAKE_BACK_IN.createFrom(codriverStick);
	public final Button intakeBackOut = CodriverControls.INTAKE_BACK_OUT.createFrom(codriverStick);

	// Climb Controls
	public final Button climbModeButton = CodriverManualControls.CLIMB_MODE.createFrom(codriverManualStick);

	// Constructor to set all of the commands and buttons
	public OI(RobotContainer robotContainer) {
		bindClimbControls(robotContainer);
		bindDriverControls(robotContainer);
		bindIntakeControls(robotContainer);
		bindLiftControls(robotContainer);
		// bindIndexControls(robotContainer);
	}

	public void bindIntakeControls(RobotContainer robotContainer) {
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

			frontIntakeUpDown.whenReleased(new IntakeFrontDownCommand(robotContainer.m_intakeLiftSubsystem));
			frontIntakeUpDown.whenPressed(new IntakeFrontUpCommand(robotContainer.m_intakeLiftSubsystem));

			backIntakeUpDown.whenReleased(new IntakeBackDownCommand(robotContainer.m_intakeLiftSubsystem));
			backIntakeUpDown.whenPressed(new IntakeBackUpCommand(robotContainer.m_intakeLiftSubsystem));

			// intakeFrontIn.whenPressed(new
			// IntakeFrontInCommand(robotContainer.m_intakeMotorOnOffSubsystem)
			// .andThen(new InstantCommand(() ->
			// robotContainer.m_indexerMotorSubsystem.setFrontMotor(-1))));
			intakeFrontIn.whenReleased(new IntakeFrontOffCommand(robotContainer.m_intakeMotorSubsystem));
			// .andThen(new InstantCommand(() ->
			// robotContainer.m_indexerMotorSubsystem.stopAllMotors())));

			intakeFrontOut.whenPressed(new IntakeFrontOutCommand(robotContainer.m_intakeMotorSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setFrontMotor(1))));
			intakeFrontOut.whenReleased(new IntakeFrontOffCommand(robotContainer.m_intakeMotorSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setFrontMotor(0))));

			// intakeBackIn.whenPressed(new
			// IntakeBackInCommand(robotContainer.m_intakeMotorOnOffSubsystem)
			// .andThen(new InstantCommand(() ->
			// robotContainer.m_indexerMotorSubsystem.setBackMotor(-1))));
			intakeBackIn.whenReleased(new IntakeBackOffCommand(robotContainer.m_intakeMotorSubsystem));
			// .andThen(new InstantCommand(() ->
			// robotContainer.m_indexerMotorSubsystem.stopAllMotors())));

			intakeBackOut.whenPressed(new IntakeBackOutCommand(robotContainer.m_intakeMotorSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setBackMotor(1))));
			intakeBackOut.whenReleased(new IntakeBackOffCommand(robotContainer.m_intakeMotorSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setBackMotor(0))));
			intakeFrontIn.whenPressed(new IntakeFrontInCommand(robotContainer.m_intakeMotorSubsystem, false)
					.andThen(new InstantCommand(() -> RobotState.m_unbalancedSide = UnbalancedSide.FRONT)
							.andThen(new ConditionalCommand(
									new IndexSwitchFourCommand(robotContainer.m_indexerSensorSubsystem,
											robotContainer.m_indexerMotorSubsystem),
									new InstantCommand(() -> System.out.println("hi")),
									(() -> RobotState.m_ballCount == 4)))));
			// .andThen(new IndexSwitchFourCommand(robotContainer.m_indexerSensorSubsystem,
			// robotContainer.m_indexerMotorSubsystem)));
			intakeBackIn.whenPressed(new IntakeBackInCommand(robotContainer.m_intakeMotorSubsystem));
			// .andThen(new InstantCommand(() -> RobotState.m_unbalancedSide =
			// RobotState.UnbalancedSide.BACK)));

			// Command indexShootCommand = new
			// IndexShootCommand(robotContainer.m_indexerSensorSubsystem,
			// robotContainer.m_indexerMotorSubsystem,
			// robotContainer.m_intakeMotorOnOffSubsystem);

			indexerShootButton.whenPressed(new IndexShootCommand(robotContainer.m_indexerSensorSubsystem,
					robotContainer.m_indexerMotorSubsystem, robotContainer.m_indexerMidMotorSubsystem));

			indexerShootButton.whenReleased(new InstantCommand(() -> CommandScheduler.getInstance()
					.cancel(new IndexShootCommand(robotContainer.m_indexerSensorSubsystem,
							robotContainer.m_indexerMotorSubsystem, robotContainer.m_indexerMidMotorSubsystem))));

			intakeFrontIn.whenReleased(new IntakeFrontOffCommand(robotContainer.m_intakeMotorSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.stopAllMotors())));

			intakeFrontOut.whenPressed(new IntakeFrontOutCommand(robotContainer.m_intakeMotorSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setFrontMotor(1))));
			intakeFrontOut.whenReleased(new IntakeFrontOffCommand(robotContainer.m_intakeMotorSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setFrontMotor(0))));

			intakeBackIn.whenReleased(new IntakeBackOffCommand(robotContainer.m_intakeMotorSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.stopAllMotors())));

			intakeBackOut.whenPressed(new IntakeBackOutCommand(robotContainer.m_intakeMotorSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setBackMotor(1))));
			intakeBackOut.whenReleased(new IntakeBackOffCommand(robotContainer.m_intakeMotorSubsystem)
					.andThen(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.setBackMotor(0))));
			intakeFrontIn.whileHeld(new IntakeFrontInCommand(robotContainer.m_intakeMotorSubsystem, false)
					.andThen(new InstantCommand(() -> {
						robotContainer.m_indexerMidMotorSubsystem.setDown();
						if (robotContainer.m_indexerSensorSubsystem.getIndexFrontSensorValue())
							robotContainer.m_indexerMotorSubsystem.setFrontMotor(-1);
						else
							robotContainer.m_indexerMotorSubsystem.setFrontMotor(0);
						if (!robotContainer.m_indexerSensorSubsystem.getIndexBackSensorValue())
							robotContainer.m_indexerMotorSubsystem.setBackMotor(1);
						else
							robotContainer.m_indexerMotorSubsystem.setBackMotor(0);
					})));
			intakeBackIn.whileHeld(
					new IntakeBackInCommand(robotContainer.m_intakeMotorSubsystem).andThen(new InstantCommand(() -> {
						robotContainer.m_indexerMidMotorSubsystem.setDown();
						if (robotContainer.m_indexerSensorSubsystem.getIndexBackSensorValue())
							robotContainer.m_indexerMotorSubsystem.setBackMotor(-1);
						else
							robotContainer.m_indexerMotorSubsystem.setBackMotor(0);
						if (!robotContainer.m_indexerSensorSubsystem.getIndexFrontSensorValue())
							robotContainer.m_indexerMotorSubsystem.setFrontMotor(1);
						else
							robotContainer.m_indexerMotorSubsystem.setFrontMotor(0);
					})));

		}
	}

	public void bindClimbControls(RobotContainer robotContainer) {
		if (!RobotMap.CLIMB_CONNECTED) {
			return;
		}

		climbModeButton.whileHeld(new ClimbJoystickCommand(codriverManualStick, robotContainer.m_climbMotorSubsystem));
		climbModeButton.whenPressed(new ClimbDeployRailsCommand(robotContainer.m_climbLiftSubsystem));
		climbModeButton.whenReleased(new ClimbRetractRailsCommand(robotContainer.m_climbLiftSubsystem));
	}

	public void bindLiftControls(RobotContainer robotContainer) {
		if (!RobotMap.LIFT_CONNECTED) {
			return;
		}

		liftButton.whenPressed(new LiftUpCommand(robotContainer.m_liftSubsystem));
		liftButton.whenReleased(new LiftDownCommand(robotContainer.m_liftSubsystem));
	}

	public void bindDriverControls(RobotContainer robotContainer) {
		if (RobotMap.DRIVE_BASE_CONNECTED) {
			return;
		}

		robotContainer.m_driveBaseSubsystem.setDefaultCommand(new DriveCommand(robotContainer.m_driveBaseSubsystem,
				driverRightStick, driverLeftStick, DriverControls.ALIGN_STICKS.createFrom(driverLeftStick)));

		shifter.whenPressed(new DriveShiftToHighGearCommand(robotContainer.m_driveBaseSubsystem));
		shifter.whenReleased(new DriveShiftToLowGearCommand(robotContainer.m_driveBaseSubsystem));
	}
}