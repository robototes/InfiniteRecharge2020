package frc.team2412.robot;

import frc.team2412.robot.Commands.ClimbCommand;
import frc.team2412.robot.Commands.ClimbRails;
import frc.team2412.robot.Subsystems.ClimbLiftSubsystem;
import frc.team2412.robot.Subsystems.ClimbMotorSubsystem;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {
	// OI

	public ClimbLiftSubsystem m_ClimbLiftSubsystem;
	public ClimbMotorSubsystem m_ClimbMotorSubsystem;
	public ClimbCommand m_ClimbCommand;
	public ClimbRails m_ClimbRails;
	// Subsystems
	
	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map
		m_ClimbLiftSubsystem = new ClimbLiftSubsystem(RobotMap.leftPneumatic, RobotMap.rightPneumatic);
		m_ClimbMotorSubsystem = new ClimbMotorSubsystem(RobotMap.leftClimbMotor, RobotMap.rightClimbMotor);
		m_ClimbCommand = new ClimbCommand(RobotMap.leftPneumatic, RobotMap.rightPneumatic);
		m_ClimbRails = new ClimbRails(RobotMap.leftClimbMotor, RobotMap.rightClimbMotor);

		// create an OI object
	}
}
