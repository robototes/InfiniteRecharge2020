package frc.team2412.robot;

import frc.team2412.robot.subsystems.ExampleSubsystem;

import static frc.team2412.robot.RobotContainer.RobotConstants.*;

// this is the class for containing all the subsystems of the robot
public class RobotContainer {
	public static class RobotConstants{
		public static boolean EXAMPLE_CONNECTED = true;
	}
	// Subsystems
	public ExampleSubsystem exampleSubsystem;

	public RobotContainer() {
		// create and instance of example subsystem with the device from hardware map if the subsystem is connected
		if(EXAMPLE_CONNECTED) exampleSubsystem = new ExampleSubsystem(Hardware.EXAMPLE_MOTOR);
	}
}
