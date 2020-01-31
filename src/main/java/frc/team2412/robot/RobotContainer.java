package frc.team2412.robot;

import frc.team2412.robot.Subsystems.ExampleSubsystem;
import frc.team2412.robot.Subsystems.IndexerSubsystem;
// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {
	// OI

	// Subsystems
	public ExampleSubsystem m_ExampleSubsystem;
	public IndexerSubsystem m_IndexerSubsystem;

	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map
		// m_ExampleSubsystem = new ExampleSubsystem(RobotMap.exampleID);
		m_IndexerSubsystem = new IndexerSubsystem(RobotMap.indexFrontMotor, RobotMap.indexMidMotor,
				RobotMap.indexBackMotor, RobotMap.front, RobotMap.frontMid, RobotMap.mid, RobotMap.backMid,
				RobotMap.back, RobotMap.intakeFront, RobotMap.intakeBack);
		// create an OI object
	}
}
