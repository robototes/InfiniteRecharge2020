package frc.team2412.robot;

import static frc.team2412.robot.RobotMapConstants.CLIMB_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.DRIVE_BASE_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.INDEX_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.INTAKE_CONNECTED;
import static frc.team2412.robot.RobotMapConstants.LIFT_CONNECTED;

import java.util.Optional;

import edu.wpi.first.wpilibj.Controller;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team2412.robot.autonomous.Autonomous;
import frc.team2412.robot.commands.climb.ClimbJoystickCommand;
import frc.team2412.robot.commands.climb.ClimbLiftDownCommand;
import frc.team2412.robot.commands.climb.ClimbLiftUpCommand;
import frc.team2412.robot.commands.drive.DriveCommand;
import frc.team2412.robot.commands.drive.DriveShiftToHighGearCommand;
import frc.team2412.robot.commands.drive.DriveShiftToLowGearCommand;
import frc.team2412.robot.commands.flywheel.FlywheelSetSpeedCommand;
import frc.team2412.robot.commands.hood.HoodSetAngleCommand;
import frc.team2412.robot.commands.indexer.IndexAllInCommand;
import frc.team2412.robot.commands.indexer.IndexShootCommand;
import frc.team2412.robot.commands.indexer.IndexSpitCommand;
import frc.team2412.robot.commands.indexer.shoot.IndexLiftShootCommand;
import frc.team2412.robot.commands.indexer.shoot.IndexLiftStopCommand;
import frc.team2412.robot.commands.indexer.shoot.IndexShiftBackCommand;
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
import frc.team2412.robot.commands.limelight.LimelightReadCommand;
import frc.team2412.robot.commands.turret.TurretFollowLimelightCommand;
// import frc.team2412.robot.subsystems.constants.ShooterConstants;
import frc.team2412.robot.subsystems.constants.ShooterConstants.ShooterDistanceDataPoint;

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

	public static enum Joysticks {
		DRIVER_RIGHT(0), DRIVER_LEFT(1), CODRIVER(2), CODRIVER_MANUAL(3);

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
		LIFT(8), FRONT_INTAKE_DOWN(2), BACK_INTAKE_DOWN(1), INTAKE_BACK_IN(4), INTAKE_BACK_OUT(3), INTAKE_FRONT_IN(6),
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
	//public final Button indexerSpitButton = DriverControls.SPIT.createFrom(driverLeftStick);
	// public final Button startFlywheel = DriverControls.RESET_POSITION.createFrom(driverRightStick);
	// public final Button bounceRunButton = DriverControls.BOUNCE_RUN.createFrom(driverRightStick);
	// public final Button stopFlywheel = DriverControls.STOP_SHOOTER.createFrom(driverRightStick);

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

		//bounceRunButton.whenPressed(Autonomous.getSlalomPathCommand());
		//indexerShootButton.whenPressed(Autonomous.getSquarePathCommand());
		//resetPositionButton.whenPressed(Autonomous.resetPositionCommand());
		//bounceRunButton.whenPressed(Autonomous.getBouncePathCommand());
		/*
		startFlywheel.whenPressed(new ParallelCommandGroup(
			new HoodSetAngleCommand(robotContainer.m_hoodSubsystem, 0.32),
			new FlywheelSetSpeedCommand(robotContainer.m_flywheelSubsystem, 4500)
		));
		stopFlywheel.whenPressed(new ParallelCommandGroup(
			new HoodSetAngleCommand(robotContainer.m_hoodSubsystem, 0.0),
			new FlywheelSetSpeedCommand(robotContainer.m_flywheelSubsystem, 0)
		));
		*/
		DriverControls.START_SHOOT.createFrom(driverRightStick).whileHeld(new TurretFollowLimelightCommand(robotContainer.m_turretSubsystem, robotContainer.m_limelightSubsystem));
		bindClimbControls(robotContainer);
		bindDriverControls(robotContainer);
		bindIntakeControls(robotContainer);
		bindLiftControls(robotContainer);
		bindIndexControls(robotContainer);

		((Button) DriverControls.START_SHOOT.createFrom(driverRightStick).whenPressed(new LimelightReadCommand(robotContainer.m_limelightSubsystem)).whileActiveContinuous(() -> {
			Optional<ShooterDistanceDataPoint> opPoint = robotContainer.m_limelightSubsystem.getDistanceData();
			opPoint.ifPresent(point ->{
				//System.out.println(point);
				robotContainer.m_flywheelSubsystem.setSpeed((point.m_shooterPower.value()) / 5500);
				robotContainer.m_hoodSubsystem.setServo(point.m_hoodAngle.value());
			});
		})).whenReleased(() -> {
			robotContainer.m_flywheelSubsystem.setSpeed(0);
			robotContainer.m_hoodSubsystem.setServo(0);
		});
		
        // new JoystickButton(driverRightStick, 1).whenPressed(()->s[0]+=15);
        // new JoystickButton(driverRightStick, 4).whenPressed(()->s[0]-=15);

		DriverControls.RUN_LIFT.createFrom(driverRightStick)
			.whileHeld(new IndexLiftShootCommand(robotContainer.m_indexerMotorSubsystem))
			.whenReleased(new IndexLiftStopCommand(robotContainer.m_indexerMotorSubsystem));

		DriverControls.LIFT_DOWN.createFrom(driverRightStick)
			.whileHeld(() -> robotContainer.m_indexerMotorSubsystem.getIndexerMotorLiftSubsystem().in())
			.whenReleased(new IndexLiftStopCommand(robotContainer.m_indexerMotorSubsystem));
	}
	
	public void bindIndexControls(RobotContainer robotContainer) {
		if (!INDEX_CONNECTED) {
			return;
		}

		//indexerSpitButton.whileHeld(new IndexSpitCommand(robotContainer.m_indexerMotorSubsystem,
		//		robotContainer.m_intakeMotorOnOffSubsystem, false));

		// Crashes due to intakeBothUpCommand requiring the same subsystem twice
		Command indexShootCommand = new IndexShootCommand(robotContainer.m_indexerMotorSubsystem);

		// indexerShootButton.whenPressed(indexShootCommand);

		// indexerShootButton
		// 		.whenReleased(new InstantCommand(() -> CommandScheduler.getInstance().cancel(indexShootCommand)));
	}

	public void bindIntakeControls(RobotContainer robotContainer) {
		if (!INTAKE_CONNECTED) {
			return;
		}

		frontIntakeUpDown.whenReleased(new IntakeFrontDownCommand(robotContainer.m_intakeUpDownSubsystem, false));
		frontIntakeUpDown.whenPressed(new IntakeFrontUpCommand(robotContainer.m_intakeUpDownSubsystem, false));

		backIntakeUpDown.whenReleased(new IntakeBackDownCommand(robotContainer.m_intakeUpDownSubsystem, false));
		backIntakeUpDown.whenPressed(new IntakeBackUpCommand(robotContainer.m_intakeUpDownSubsystem, false));

		intakeFrontIn.whileHeld(new IntakeFrontInCommand(robotContainer.m_intakeMotorOnOffSubsystem).alongWith(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().in())));
		intakeFrontIn.whenReleased(new IntakeFrontOffCommand(robotContainer.m_intakeMotorOnOffSubsystem).alongWith(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().stop())));

		intakeFrontOut.whileHeld(new IntakeFrontOutCommand(robotContainer.m_intakeMotorOnOffSubsystem).alongWith(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().out())));
		intakeFrontOut.whenReleased(new IntakeFrontOffCommand(robotContainer.m_intakeMotorOnOffSubsystem).alongWith(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.getIndexerMotorFrontSubsystem().stop())));

		intakeBackIn.whileHeld(new IntakeBackInCommand(robotContainer.m_intakeMotorOnOffSubsystem).alongWith(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().in())));
		intakeBackIn.whenReleased(new IntakeBackOffCommand(robotContainer.m_intakeMotorOnOffSubsystem).alongWith(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().stop())));

		intakeBackOut.whileHeld(new IntakeBackOutCommand(robotContainer.m_intakeMotorOnOffSubsystem).alongWith(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().out())));
		intakeBackOut.whenReleased(new IntakeBackOffCommand(robotContainer.m_intakeMotorOnOffSubsystem).alongWith(new InstantCommand(() -> robotContainer.m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().stop())));

		// intakeBackIn.whileHeld(new
		// IntakeBackInCommand(robotContainer.m_intakeMotorOnOffSubsystem).andThen(
		// new InstantCommand(() ->{
		// robotContainer.m_indexerMotorSubsystem.getIndexerMotorBackSubsystem().set(-1);
		// robotContainer.m_indexerMotorSubsystem.getIndexerMotorLiftSubsystem().set(-1);
		// }
		// )
		// ));

	}

	public void bindClimbControls(RobotContainer robotContainer) {
		if (!CLIMB_CONNECTED) {
			return;
		}

		climbModeButton.whileHeld(new ClimbJoystickCommand(codriverManualStick, robotContainer.m_climbMotorSubsystem));
		climbModeButton.whenPressed(new ClimbLiftUpCommand(robotContainer.m_climbLiftSubsystem,
				robotContainer.m_liftSubsystem, robotContainer.m_indexerMotorSubsystem));
		climbModeButton.whenReleased(new ClimbLiftDownCommand(robotContainer.m_climbLiftSubsystem));
	}

	public void bindLiftControls(RobotContainer robotContainer) {
		if (!LIFT_CONNECTED) {
			return;
		}

		liftButton
				.whenPressed(new LiftUpCommand(robotContainer.m_liftSubsystem, robotContainer.m_indexerMotorSubsystem));
		liftButton.whenReleased(
				new LiftDownCommand(robotContainer.m_liftSubsystem, robotContainer.m_indexerMotorSubsystem));
	}

	public void bindDriverControls(RobotContainer robotContainer) {
		if (!DRIVE_BASE_CONNECTED) {
			return;
		}

		robotContainer.m_driveBaseSubsystem.setDefaultCommand(new DriveCommand(robotContainer.m_driveBaseSubsystem,
				driverRightStick, driverLeftStick, DriverControls.ALIGN_STICKS.createFrom(driverLeftStick)));

		shifter.whenPressed(new DriveShiftToHighGearCommand(robotContainer.m_driveBaseSubsystem));
		shifter.whenReleased(new DriveShiftToLowGearCommand(robotContainer.m_driveBaseSubsystem));
	}
}