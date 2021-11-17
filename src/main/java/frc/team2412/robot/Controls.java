package frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team2412.robot.commands.ExampleCommand;

import static frc.team2412.robot.RobotContainer.RobotConstants.*;

//This is the class in charge of all the buttons and joysticks that the drivers will use to control the robot
public class Controls {

	public static class ControlConstants{

		public static int EXAMPLE_PORT = 1;
	}

	// Joysticks
	public final Joystick driverStick = new Joystick(0);

	// Buttons
	public final Button exampleButton = new JoystickButton(driverStick, ControlConstants.EXAMPLE_PORT);
	// the robot instance
	public final RobotContainer robot;

	// Constructor to set all of the commands and buttons
	public Controls(RobotContainer robotContainer) {
		robot = robotContainer;
		// telling the button that when its pressed to execute example command with the
		// in this case it causes the command to start running when the button is just pressed
		if(EXAMPLE_CONNECTED) bindExampleControls();
	}

	public void bindExampleControls(){
		exampleButton.whenPressed(new ExampleCommand(robot.exampleSubsystem));
	}
}
