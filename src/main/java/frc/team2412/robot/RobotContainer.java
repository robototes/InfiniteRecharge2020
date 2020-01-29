package frc.team2412.robot;

import frc.team2412.robot.Subsystems.ExampleSubsystem;
import frc.team2412.robot.Subsystems.LimelightSubsystem;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {
	// OI
	public OI m_OI;

	// Subsystems
	public ExampleSubsystem m_ExampleSubsystem;
	public LimelightSubsystem m_LimelightSubsystem;

	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map
		m_ExampleSubsystem = new ExampleSubsystem(RobotMap.exampleID);

		m_LimelightSubsystem = new LimelightSubsystem(RobotMap.limelight);

		// create an OI object
		m_OI = new OI();
	}
}
