package frc.team2412.robot;

import frc.team2412.robot.Subsystems.ClimbLiftSubsystem;
import frc.team2412.robot.Subsystems.constants.ClimbUpAndDown;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {
	// OI
	public OI m_OI;

	public ClimbLiftSubsystem m_ClimbLiftSubsystem;
	// Subsystems

	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map
		m_ClimbLiftSubsystem = new ClimbUpAndDown(RobotMap.leftClimbMotor, RobotMap.rightClimbMotor, RobotMap.climbMode);
		
		// create an OI object
		m_OI = new OI();
	}
}
