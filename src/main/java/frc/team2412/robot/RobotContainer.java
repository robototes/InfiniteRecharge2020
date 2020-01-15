package frc.team2412.robot;

import frc.team2412.robot.Subsystems.ExampleSubsystem;
import frc.team2412.robot.Subsystems.LiftSubsystem;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {
	// OI
	public OI m_OI;

	// Subsystems
	public ExampleSubsystem m_ExampleSubsystem;
	
	public LiftSubsystem LiftSubsystem;

	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map
		m_ExampleSubsystem = new ExampleSubsystem(RobotMap.exampleID);
		
		LiftSubsystem = new LiftSubsystem();

		// create an OI object
		m_OI = new OI();
	}
}
