package frc.team2412.robot;

import infiniteRecharge2020.ClimbLiftSubsystem;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {
	// OI
	public OI m_OI;

	// Subsystems
	public ClimbLiftSubsystem m_ClimbLiftSubsystem;

	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map
		m_ClimbLiftSubsystem = new ClimbLiftSubsystem(RobotMap.deployArmPneumatic1, RobotMap.deployArmPneumatic2);
		// create an OI object
		m_OI = new OI();
	}
}
