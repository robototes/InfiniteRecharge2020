package frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team2412.robot.Commands.ClimbDeployArmCommand;
import frc.team2412.robot.Subsystems.constants.ClimbUp;

//This is the class in charge of all the buttons and joysticks that the drivers will use to control the robot
public class OI {

	// Joysticks
	public Joystick driverStick = new Joystick(0);
	public Joystick coDriver = new Joystick(1);
	// Buttons
	public Button startClimb = new JoystickButton(coDriver, 1);
	public Button climbUp = new JoystickButton(coDriver, 2);
	// Constructor to set all of the commands and buttons
	public OI() {
		// telling the button that when its pressed to execute example command with the
		// robot container's instance of example subsystem
		startClimb.whenPressed(new ClimbDeployArmCommand(RobotMap.robotContainer.m_ClimbLiftSubsystem));
		climbUp.whenHeld((Command) new ClimbUp());
	}
}
