package frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.team2412.robot.Commands.ExampleCommand;
import frc.team2412.robot.Commands.ClimbCommands.ClimbDeployRailsCommand;
import frc.team2412.robot.Commands.ClimbCommands.ClimbExtendArmCommand;
import frc.team2412.robot.Commands.ClimbCommands.ClimbRetractRailsCommand;
import frc.team2412.robot.Commands.ClimbCommands.ClimbStopArmCommand;
import frc.team2412.robot.Commands.ControlPanelCommands.RotateControlPanelCommand;
import frc.team2412.robot.Commands.ControlPanelCommands.SetToTargetColorCommand;
import frc.team2412.robot.Commands.IndexerCommands.ProcessBallsCommandGroup;
import frc.team2412.robot.Commands.IntakeCommands.IntakeBackDownCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeBackOffCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeBackOnCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeBackUpCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeBothUpCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontOffCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontOffIntakeBackOnCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontOnCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontOnIntakeBackOffCommand;
import frc.team2412.robot.Commands.LiftCommands.LiftDownCommand;
import frc.team2412.robot.Commands.LiftCommands.LiftUpCommand;

//This is the class in charge of all the buttons and joysticks that the drivers will use to control the robot
public class OI {

	// Joystick ports
	public static final int DRIVER_STICK_PORT = 0;
	public static final int CODRIVER_STICK_PORT = 1;

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

	// CONTROL PANEL button ports
	public static final int CONTROL_PANEL_SPIN_3_TIMES_BUTTON_PORT = 1;
	public static final int CONTROL_PANEL_SET_TO_TARGET_COLOR_BUTTON_PORT = 1;

	// CLIMB button ports
	private static final int CLIMB_DEPLOY_RAILS_BUTTON_PORT = 0;
	private static final int CLIMB_EXTEND_ARM_BUTTON_PORT = 0;
	private static final int CLIMB_RETRACT_RAILS_BUTTON_PORT = 0;
	private static final int CLIMB_STOP_ARM_BUTTON_PORT = 0;
	private static final int CLIMB_RETRACT_ARM_BUTTON_PORT = 0;

	// Joysticks
	public Joystick driverStick = new Joystick(DRIVER_STICK_PORT);
	public Joystick codriverStick = new Joystick(CODRIVER_STICK_PORT);

	// Buttons
	public Button exampleSubsystemMethod = new JoystickButton(driverStick, 1);
	public Button indexerShootButton = new JoystickButton(driverStick, 2);
	public Button indexerStopButton = new JoystickButton(driverStick, 3);

	public Button liftUpButton = new JoystickButton(codriverStick, LIFT_UP_BUTTON_PORT);
	public Button liftDownButton = new JoystickButton(codriverStick, LIFT_DOWN_BUTTON_PORT);

	public Button intakeFrontOnButton = new JoystickButton(driverStick, INTAKE_FRONT_ON_BUTTON_PORT);
	public Button intakeFrontOffButton = new JoystickButton(driverStick, INTAKE_FRONT_OFF_BUTTON_PORT);
	public Button intakeBackOnButton = new JoystickButton(driverStick, INTAKE_BACK_ON_BUTTON_PORT);
	public Button intakeBackOffButton = new JoystickButton(driverStick, INTAKE_BACK_OFF_BUTTON_PORT);
	public Button intakeUpButton = new JoystickButton(driverStick, INTAKE_UP_BUTTON_PORT);
	public Button intakeDownButton = new JoystickButton(driverStick, INTAKE_DOWN_BUTTON_PORT);
	public Button intakeFrontOnBackOffButton = new JoystickButton(driverStick, INTAKE_FRONT_ON_BACK_OFF_BUTTON_PORT);
	public Button intakeFrontOffBackOnButton = new JoystickButton(driverStick, INTAKE_BACK_ON_FRONT_OFF_BUTTON_PORT);

	public Button controlPanelSpinThreeTimesButton = new JoystickButton(driverStick,
			CONTROL_PANEL_SPIN_3_TIMES_BUTTON_PORT);
	public Button controlPanelSetToTargetButton = new JoystickButton(driverStick,
			CONTROL_PANEL_SET_TO_TARGET_COLOR_BUTTON_PORT);

	public Button climbDeployRailsButton = new JoystickButton(driverStick, CLIMB_DEPLOY_RAILS_BUTTON_PORT);
	public Button climbExtendArmButton = new JoystickButton(driverStick, CLIMB_EXTEND_ARM_BUTTON_PORT);
	public Button climbRetractRailsButton = new JoystickButton(driverStick, CLIMB_RETRACT_RAILS_BUTTON_PORT);
	public Button climbRetractArmButton = new JoystickButton(driverStick, CLIMB_RETRACT_ARM_BUTTON_PORT);
	public Button climbStopArmButton = new JoystickButton(driverStick, CLIMB_STOP_ARM_BUTTON_PORT);

	// Constructor to set all of the commands and buttons
	public OI(RobotContainer robotContainer) {
		// telling the button that when its pressed to execute example command with the
		// robot container's instance of example subsystem
		indexerStopButton
				.toggleWhenPressed(new ProcessBallsCommandGroup(robotContainer.m_IndexerSubsystem, indexerShootButton));

		exampleSubsystemMethod.whenPressed(new ExampleCommand(robotContainer.m_ExampleSubsystem));

		// LIFT
		liftUpButton.whenPressed(new LiftUpCommand(robotContainer.m_liftSubsystem));
		liftDownButton.whenPressed(new LiftDownCommand(robotContainer.m_liftSubsystem));

		// INTAKE UpDown
		intakeUpButton.whenPressed(new IntakeBackUpCommand(robotContainer.m_intakeUpDownSubsystem));
		intakeDownButton.whenPressed(new IntakeBackDownCommand(robotContainer.m_intakeUpDownSubsystem));

		// INTAKE front
		intakeFrontOnButton.whenPressed(new IntakeFrontOnCommand(robotContainer.m_intakeMotorOnOffSubsystem));
		intakeFrontOffButton.whenPressed(new IntakeFrontOffCommand(robotContainer.m_intakeMotorOnOffSubsystem));

		// INTAKE back
		intakeBackOnButton.whenPressed(new IntakeBackOnCommand(robotContainer.m_intakeMotorOnOffSubsystem));
		intakeBackOffButton.whenPressed(new IntakeBackOffCommand(robotContainer.m_intakeMotorOnOffSubsystem));

		// INTAKE group on/off
		intakeFrontOnBackOffButton
				.whenPressed(new IntakeFrontOnIntakeBackOffCommand(robotContainer.m_intakeMotorOnOffSubsystem));
		intakeFrontOffBackOnButton
				.whenPressed(new IntakeFrontOffIntakeBackOnCommand(robotContainer.m_intakeMotorOnOffSubsystem));

		// CONTROL PANEL
		controlPanelSpinThreeTimesButton
				.whenPressed(new RotateControlPanelCommand(robotContainer.m_controlPanelColorSubsystem));
		controlPanelSetToTargetButton
				.whenPressed(new SetToTargetColorCommand(robotContainer.m_controlPanelColorSubsystem));

		exampleSubsystemMethod.whenPressed(new ExampleCommand(robotContainer.m_ExampleSubsystem));

		climbDeployRailsButton.whenActive(new ClimbDeployRailsCommand(robotContainer.m_ClimbLiftSubsystem));
		climbExtendArmButton.whenActive(new ClimbExtendArmCommand(robotContainer.m_ClimbMotorSubsystem));
		climbRetractArmButton.whenActive(new ClimbExtendArmCommand(robotContainer.m_ClimbMotorSubsystem));
		climbRetractRailsButton.whenActive(new ClimbRetractRailsCommand(robotContainer.m_ClimbLiftSubsystem));
		climbStopArmButton.whenActive(new ClimbStopArmCommand(robotContainer.m_ClimbMotorSubsystem));

		Trigger intakeUpWhenFiveBalls = new Trigger(RobotState::hasFiveBalls);
		intakeUpWhenFiveBalls.whenActive(new IntakeBothUpCommand(robotContainer.m_intakeUpDownSubsystem));
	}
}
