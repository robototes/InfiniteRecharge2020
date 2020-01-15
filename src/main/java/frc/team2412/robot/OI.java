package frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team2412.robot.Commands.ExampleCommand;
import frc.team2412.robot.Commands.LiftCommands.LiftDown;
import frc.team2412.robot.Commands.LiftCommands.LiftUp;
import frc.team2412.robot.Subsystems.LiftSubsystem;

//This is the class in charge of all the buttons and joysticks that the drivers will use to control the robot
public class OI {

	// Joysticks
	public Joystick driverStick = new Joystick(0);
	public Joystick codriverStick = new Joystick(1);

	// Buttons
	public Button exampleSubsystemMethod = new JoystickButton(driverStick, 1);

	public Button liftUpButton = new JoystickButton(codriverStick, RobotMap.LIFT_UP_BUTTON_PORT);
	public Button liftDownButton = new JoystickButton(codriverStick, RobotMap.LIFT_DOWN_BUTTON_PORT);

	// Constructor to set all of the commands and buttons
	public OI() {
		// telling the button that when its pressed to execute example command with the
		// robot container's instance of example subsystem

		liftUpButton.whenPressed(new LiftUp(RobotMap.robotContainer.liftSubsystem));
		liftDownButton.whenPressed(new LiftDown(RobotMap.robotContainer.liftSubsystem));

		exampleSubsystemMethod.whenPressed(new ExampleCommand(RobotMap.robotContainer.m_ExampleSubsystem));

	}
}
