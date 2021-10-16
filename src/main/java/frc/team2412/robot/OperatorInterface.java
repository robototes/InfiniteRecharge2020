package frc.team2412.robot;

import java.util.Optional;

import com.robototes.units.Rotations;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team2412.robot.commands.climb.ClimbJoystickCommand;
import frc.team2412.robot.commands.climb.ClimbLiftDownCommand;
import frc.team2412.robot.commands.climb.ClimbLiftUpCommand;
import frc.team2412.robot.commands.drive.DriveCommand;
import frc.team2412.robot.commands.drive.DriveShiftToHighGearCommand;
import frc.team2412.robot.commands.drive.DriveShiftToLowGearCommand;
import frc.team2412.robot.commands.indexer.*;
import frc.team2412.robot.commands.intake.IntakeBothInCommandGroup;
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
import frc.team2412.robot.commands.limelight.LimelightReadCommand;
import frc.team2412.robot.commands.turret.TurretFollowLimelightCommand;
import frc.team2412.robot.commands.turret.TurretRotateCommand;
// import frc.team2412.robot.subsystems.constants.ShooterConstants;
import frc.team2412.robot.subsystems.constants.ShooterConstants.ShooterDistanceDataPoint;

import static frc.team2412.robot.RobotMapConstants.*;

//This is the class in charge of all the buttons and joysticks that the drivers will use to control the robot
public class OperatorInterface {

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

	public static enum Joysticks {
		DRIVER_RIGHT(1), DRIVER_LEFT(0), CODRIVER(2), CODRIVER_MANUAL(3);

		public int id;

		private Joysticks(int id) {
			this.id = id;
		}
	}

	public static enum DriverControls implements ButtonEnumInterface {
		START_SHOOT(Joysticks.DRIVER_RIGHT, 6), SHIFT(Joysticks.DRIVER_RIGHT, 5),RUN_LIFT(Joysticks.DRIVER_RIGHT, 4),
		LIFT_DOWN(Joysticks.DRIVER_RIGHT, 2), /*TURRET_AUTOAIM(Joysticks.DRIVER_RIGHT, 3),/*
		RESET_POSITION(Joysticks.DRIVER_RIGHT, 3), ,
		STOP_SHOOTER(Joysticks.DRIVER_RIGHT, 7),*/
		SPIT(Joysticks.DRIVER_LEFT, 1), ALIGN_STICKS(Joysticks.DRIVER_LEFT, 3);

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

	public static enum CodriverControls implements ButtonEnumInterface {
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

	public static enum CodriverManualControls implements ButtonEnumInterface {
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
	public final XboxController driverRightStick = new XboxController(Joysticks.DRIVER_RIGHT.id);
	public final Joystick driverLeftStick = new Joystick(Joysticks.DRIVER_LEFT.id);
	public final Joystick codriverStick = new Joystick(Joysticks.CODRIVER.id);
	public final Joystick codriverManualStick = new Joystick(Joysticks.CODRIVER_MANUAL.id);

	// Driver Controls
	public final Button shifter = DriverControls.SHIFT.createFrom(driverRightStick);
	public final Button indexerShootButton = DriverControls.START_SHOOT.createFrom(driverRightStick);

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
	public OperatorInterface(RobotContainer robotContainer) {

		bindClimbControls(robotContainer);
		bindDriverControls(robotContainer);
		bindIntakeControls(robotContainer);
		bindLiftControls(robotContainer);
		bindIndexControls(robotContainer);
		bindShooterControls(robotContainer);
	}
	public void bindIndexControls(RobotContainer robotContainer) {
		if (!INDEX_CONNECTED) return;

		DriverControls.RUN_LIFT.createFrom(driverRightStick)
				.whileHeld(new IntakeBothInCommandGroup(robotContainer.m_intakeMotorOnOffSubsystem))
				.whenReleased(new IntakeBackOffCommand(robotContainer.m_intakeMotorOnOffSubsystem, false))
				.whenReleased(new IntakeFrontOffCommand(robotContainer.m_intakeMotorOnOffSubsystem, false))
				.whenReleased(new IndexLiftStopCommand(robotContainer.m_indexerMotorSubsystem));

		Command smartShoot = new IndexNewShootCommand(robotContainer.m_indexerMotorSubsystem);
		Command oneButtonShoot = new IndexButtonShootCommand(robotContainer.m_indexerMotorSubsystem, intakeBackIn);
		Command twoButtonShoot = new IndexButtonShootCommand(robotContainer.m_indexerMotorSubsystem, intakeFrontIn, intakeBackIn);
		Command timeShoot = new IndexTimeShootCommand(robotContainer.m_indexerMotorSubsystem);

		DriverControls.RUN_LIFT.createFrom(driverRightStick).whileActiveContinuous(timeShoot);

		robotContainer.m_indexerMotorSubsystem.setDefaultCommand(new IndexCommand(robotContainer.m_indexerMotorSubsystem, robotContainer.m_intakeUpDownSubsystem));
	}

	public void bindIntakeControls(RobotContainer robotContainer) {
		if (!INTAKE_CONNECTED) return;

		frontIntakeUpDown.whenReleased(new IntakeFrontDownCommand(robotContainer.m_intakeUpDownSubsystem, false))
				.whenPressed(new IntakeFrontUpCommand(robotContainer.m_intakeUpDownSubsystem, false));

		backIntakeUpDown.whenReleased(new IntakeBackDownCommand(robotContainer.m_intakeUpDownSubsystem, false))
				.whenPressed(new IntakeBackUpCommand(robotContainer.m_intakeUpDownSubsystem, false));

		intakeFrontIn.whileHeld(new IntakeFrontInCommand(robotContainer.m_intakeMotorOnOffSubsystem))
				.whenReleased(new IntakeFrontOffCommand(robotContainer.m_intakeMotorOnOffSubsystem));

		intakeFrontOut.whileHeld(new IndexSpitFrontCommand(robotContainer.m_indexerMotorSubsystem, robotContainer.m_intakeMotorOnOffSubsystem, true));

		intakeBackIn.whileHeld(new IntakeBackInCommand(robotContainer.m_intakeMotorOnOffSubsystem))
				.whenReleased(new IntakeBackOffCommand(robotContainer.m_intakeMotorOnOffSubsystem));

		intakeBackOut.whileHeld(new IndexSpitBackCommand(robotContainer.m_indexerMotorSubsystem, robotContainer.m_intakeMotorOnOffSubsystem, true));
	}

	public void bindClimbControls(RobotContainer robotContainer) {
		if (!CLIMB_CONNECTED) return;

		climbModeButton.whileHeld(new ClimbJoystickCommand(codriverManualStick, robotContainer.m_climbMotorSubsystem));
		climbModeButton.whenPressed(new ClimbLiftUpCommand(robotContainer.m_climbLiftSubsystem,
				robotContainer.m_liftSubsystem, robotContainer.m_indexerMotorSubsystem));
		climbModeButton.whenReleased(new ClimbLiftDownCommand(robotContainer.m_climbLiftSubsystem));
	}

	public void bindLiftControls(RobotContainer robotContainer) {
		if (!LIFT_CONNECTED) return;

		DriverControls.LIFT_DOWN.createFrom(driverRightStick)
				.whileHeld(() -> robotContainer.m_indexerMotorSubsystem.getIndexerMotorLiftSubsystem().in())
				.whenReleased(new IndexLiftStopCommand(robotContainer.m_indexerMotorSubsystem));

		liftButton.whenPressed(new LiftUpCommand(robotContainer.m_liftSubsystem))
				.whenReleased(new LiftDownCommand(robotContainer.m_liftSubsystem));
	}

	public void bindDriverControls(RobotContainer robotContainer) {
		if (!DRIVE_BASE_CONNECTED) return;

		robotContainer.m_driveBaseSubsystem.setDefaultCommand(new DriveCommand(robotContainer.m_driveBaseSubsystem,
				driverRightStick, driverLeftStick, DriverControls.ALIGN_STICKS.createFrom(driverLeftStick)));

		shifter.whenPressed(new DriveShiftToHighGearCommand(robotContainer.m_driveBaseSubsystem));
		shifter.whenReleased(new DriveShiftToLowGearCommand(robotContainer.m_driveBaseSubsystem));
	}

	public void bindShooterControls(RobotContainer robotContainer){
		if(!SHOOTER_CONNECTED) return;

		DriverControls.START_SHOOT.createFrom(driverRightStick).whileHeld(new TurretFollowLimelightCommand(robotContainer.m_turretSubsystem, robotContainer.m_limelightSubsystem));

		((Button) DriverControls.START_SHOOT.createFrom(driverRightStick).whenPressed(new LimelightReadCommand(robotContainer.m_limelightSubsystem)).whileActiveContinuous(() -> {
			Optional<ShooterDistanceDataPoint> opPoint = robotContainer.m_limelightSubsystem.getDistanceData();
			opPoint.ifPresent(point ->{
				System.out.println(point);
				robotContainer.m_flywheelSubsystem.setSpeed((point.m_shooterPower.value()) / 5500);
				robotContainer.m_hoodSubsystem.setServo(point.m_hoodAngle.value());
			});
		})).whenReleased(() -> {
			robotContainer.m_flywheelSubsystem.setSpeed(0);
			robotContainer.m_hoodSubsystem.setServo(0);
		}).whenReleased(new TurretRotateCommand(robotContainer.m_turretSubsystem, new Rotations(0)));//.configureSetpoint(0));
	}
}