package frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team2412.robot.Commands.ExampleCommand;
import frc.team2412.robot.Commands.ClimbCommand;
import frc.team2412.robot.Commands.ClimbRails;
import frc.team2412.robot.Commands.ControlPanelCommands.RotateControlPanelCommand;
import frc.team2412.robot.Commands.ControlPanelCommands.SetToTargetColorCommand;
import frc.team2412.robot.Commands.IndexerCommands.ProcessBallsCommandGroup;
import frc.team2412.robot.Commands.IntakeCommands.IntakeBackOffCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeBackOnCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeDownCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontOffCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontOffIntakeBackOnCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontOnCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeFrontOnIntakeBackOffCommand;
import frc.team2412.robot.Commands.IntakeCommands.IntakeUpCommand;
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

	// Joysticks
	public Joystick driverStick = new Joystick(DRIVER_STICK_PORT);
	public Joystick codriverStick = new Joystick(CODRIVER_STICK_PORT);

	public Button climbPneumatics = new JoystickButton(codriverStick, 1);
	public Button manualClimb = new JoystickButton(codriverStick, 2);

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
		intakeUpButton.whenPressed(new IntakeUpCommand(robotContainer.m_intakeUpDownSubsystem));
		intakeDownButton.whenPressed(new IntakeDownCommand(robotContainer.m_intakeUpDownSubsystem));

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

		// telling the button that when its pressed to execute example command with the
		// robot container's instance of example subsystem
		climbPneumatics.whenPressed(new ClimbCommand(robotContainer.m_ClimbLiftSubsystem));
		manualClimb.whileHeld(new ClimbRails(robotContainer.m_ClimbMotorSubsystem));

		// manualClimb.whenHeld((Command) new ClimbLiftSubsystem());
		// startClimb.whenPressed(new
		// ClimbDeployArmCommand(RobotMap.robotContainer.m_ClimbLiftSubsystem));
	}
}
