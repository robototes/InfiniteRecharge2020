package frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.team2412.robot.commands.climb.ClimbDeployRailsCommand;
import frc.team2412.robot.commands.climb.ClimbExtendArmCommand;
import frc.team2412.robot.commands.climb.ClimbRetractRailsCommand;
import frc.team2412.robot.commands.climb.ClimbStopArmCommand;
import frc.team2412.robot.commands.controlPanel.RotateControlPanelCommand;
import frc.team2412.robot.commands.controlPanel.SetToTargetColorCommand;
import frc.team2412.robot.commands.drive.DriveCommand;
import frc.team2412.robot.commands.indexer.IndexSpitCommand;
import frc.team2412.robot.commands.intake.IntakeBackBothOffCommandGroup;
import frc.team2412.robot.commands.intake.IntakeBackBothOnCommandGroup;
import frc.team2412.robot.commands.intake.IntakeBothUpCommand;
import frc.team2412.robot.commands.intake.IntakeFrontBothOffCommandGroup;
import frc.team2412.robot.commands.intake.IntakeFrontBothOnCommandGroup;
import frc.team2412.robot.commands.lift.LiftDownCommand;
import frc.team2412.robot.commands.lift.LiftUpCommand;

//This is the class in charge of all the buttons and joysticks that the drivers will use to control the robot
public class OI {

	// Joystick ports
	public static final int DRIVER_STICK_PORT = 0;
	public static final int CODRIVER_STICK_PORT = 1;

	public static final int MOTOR_TEST_BUTTON_PORT = 1;
	public static final int JOYSTICK_TEST_BUTTON_PORT = 3;

	// LIFT button ports
	public static final int LIFT_UP_BUTTON_PORT = 1;
	public static final int LIFT_DOWN_BUTTON_PORT = 1;

	// INTAKE front on off
	public static final int INTAKE_FRONT_ON_BUTTON_PORT = 1;
	public static final int INTAKE_FRONT_OFF_BUTTON_PORT = 1;

	// INTAKE back on off
	public static final int INTAKE_BACK_ON_BUTTON_PORT = 1;
	public static final int INTAKE_BACK_OFF_BUTTON_PORT = 1;

	// INTAKE bring it up and down
	public static final int INTAKE_UP_BUTTON_PORT = 1;
	public static final int INTAKE_DOWN_BUTTON_PORT = 1;

	// INTAKE ON/OFF BUTTONS
	public static final int INTAKE_FRONT_ON_BACK_OFF_BUTTON_PORT = 1;
	public static final int INTAKE_BACK_ON_FRONT_OFF_BUTTON_PORT = 1;

	public static final int INTAKE_FRONT_BUTTON = 1;
	public static final int INTAKE_BACK_BUTTON = 1;

	// CONTROL PANEL button ports
	public static final int CONTROL_PANEL_SPIN_3_TIMES_BUTTON_PORT = 1;
	public static final int CONTROL_PANEL_SET_TO_TARGET_COLOR_BUTTON_PORT = 1;

	// CLIMB button ports
	public static final int CLIMB_DEPLOY_RAILS_BUTTON_PORT = 1;
	public static final int CLIMB_EXTEND_ARM_BUTTON_PORT = 1;
	public static final int CLIMB_RETRACT_RAILS_BUTTON_PORT = 1;
	public static final int CLIMB_STOP_ARM_BUTTON_PORT = 1;
	public static final int CLIMB_RETRACT_ARM_BUTTON_PORT = 1;

	// Joysticks
	public Joystick driverStick = new Joystick(DRIVER_STICK_PORT);
	public Joystick codriverStick = new Joystick(CODRIVER_STICK_PORT);

	// Buttons
	public Button indexerShootButton = new JoystickButton(driverStick, 2);
	public Button indexerStopButton = new JoystickButton(driverStick, 3);

	public Button liftUpButton = new JoystickButton(codriverStick, LIFT_UP_BUTTON_PORT);
	public Button liftDownButton = new JoystickButton(codriverStick, LIFT_DOWN_BUTTON_PORT);

	public Button intakeFrontOnButton = new JoystickButton(driverStick, INTAKE_FRONT_ON_BUTTON_PORT);
	public Button intakeFrontOffButton = new JoystickButton(driverStick, INTAKE_FRONT_OFF_BUTTON_PORT);
	public Button intakeBackOnButton = new JoystickButton(driverStick, INTAKE_BACK_ON_BUTTON_PORT);
	public Button intakeBackOffButton = new JoystickButton(driverStick, INTAKE_BACK_OFF_BUTTON_PORT);

	public Button indexerIntakeFrontButton = new JoystickButton(driverStick, 3);
	public Button indexerIntakeBackButton = new JoystickButton(driverStick, 4);

	public Button controlPanelSpinThreeTimesButton = new JoystickButton(driverStick,
			CONTROL_PANEL_SPIN_3_TIMES_BUTTON_PORT);
	public Button controlPanelSetToTargetButton = new JoystickButton(driverStick,
			CONTROL_PANEL_SET_TO_TARGET_COLOR_BUTTON_PORT);

	public Button climbDeployRailsButton = new JoystickButton(driverStick, CLIMB_DEPLOY_RAILS_BUTTON_PORT);
	public Button climbExtendArmButton = new JoystickButton(driverStick, CLIMB_EXTEND_ARM_BUTTON_PORT);
	public Button climbRetractRailsButton = new JoystickButton(driverStick, CLIMB_RETRACT_RAILS_BUTTON_PORT);
	public Button climbRetractArmButton = new JoystickButton(driverStick, CLIMB_RETRACT_ARM_BUTTON_PORT);
	public Button climbStopArmButton = new JoystickButton(driverStick, CLIMB_STOP_ARM_BUTTON_PORT);

	public Button MotorTestButton = new JoystickButton(driverStick, MOTOR_TEST_BUTTON_PORT);

	public Button JoystickEqualizerButton = new JoystickButton(codriverStick, JOYSTICK_TEST_BUTTON_PORT);

	// Constructor to set all of the commands and buttons
	public OI(RobotContainer robotContainer) {

		if (RobotMap.INTAKE_CONNECTED) {
			// INTAKE front
			intakeFrontOnButton.whenPressed(new IntakeFrontBothOnCommandGroup(robotContainer.m_intakeUpDownSubsystem,
					robotContainer.m_intakeMotorOnOffSubsystem));
			intakeFrontOffButton.whenPressed(new IntakeFrontBothOffCommandGroup(robotContainer.m_intakeUpDownSubsystem,
					robotContainer.m_intakeMotorOnOffSubsystem));

			// INTAKE back
			intakeBackOnButton.whenPressed(new IntakeBackBothOnCommandGroup(robotContainer.m_intakeUpDownSubsystem,
					robotContainer.m_intakeMotorOnOffSubsystem));
			intakeBackOffButton.whenPressed(new IntakeBackBothOffCommandGroup(robotContainer.m_intakeUpDownSubsystem,
					robotContainer.m_intakeMotorOnOffSubsystem));
		}

		if (RobotMap.CONTROL_PANEL_CONNECTED) {
			// CONTROL PANEL
			controlPanelSpinThreeTimesButton
					.whenPressed(new RotateControlPanelCommand(robotContainer.m_controlPanelColorSubsystem));
			controlPanelSetToTargetButton
					.whenPressed(new SetToTargetColorCommand(robotContainer.m_controlPanelColorSubsystem));
		}

		if (RobotMap.CLIMB_CONNECTED) {
			climbDeployRailsButton.whenActive(new ClimbDeployRailsCommand(robotContainer.m_climbLiftSubsystem));
			climbExtendArmButton.whenActive(new ClimbExtendArmCommand(robotContainer.m_climbMotorSubsystem));
			climbRetractArmButton.whenActive(new ClimbExtendArmCommand(robotContainer.m_climbMotorSubsystem));
			climbRetractRailsButton.whenActive(new ClimbRetractRailsCommand(robotContainer.m_climbLiftSubsystem));
			climbStopArmButton.whenActive(new ClimbStopArmCommand(robotContainer.m_climbMotorSubsystem));
		}

		if (RobotMap.INDEX_CONNECTED) {
			indexerStopButton.whenPressed(new IndexSpitCommand(robotContainer.m_indexerSensorSubsystem,
					robotContainer.m_indexerMotorSubsystem, robotContainer.m_intakeMotorOnOffSubsystem));
		}

		if (RobotMap.DRIVE_BASE_CONNECTED) {
			MotorTestButton.whenPressed(new DriveCommand(robotContainer.m_driveBaseSubsystem, driverStick,
					codriverStick, JoystickEqualizerButton));
		}

		// LIFT
		if (RobotMap.LIFT_CONNECTED) {
			liftUpButton.whenPressed(new LiftUpCommand(robotContainer.m_liftSubsystem));
			liftDownButton.whenPressed(new LiftDownCommand(robotContainer.m_liftSubsystem));
		}

		Trigger intakeUpWhenFiveBalls = new Trigger(RobotState::hasFiveBalls);
		if (RobotMap.INTAKE_CONNECTED) {
			intakeUpWhenFiveBalls.whenActive(new IntakeBothUpCommand(robotContainer.m_intakeUpDownSubsystem,
					robotContainer.m_intakeMotorOnOffSubsystem));
		}

	}
}
