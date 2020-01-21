package frc.team2412.robot;

import frc.team2412.robot.Subsystems.ExampleSubsystem;
import frc.team2412.robot.Subsystems.LimelightSubsystem;
import frc.team2412.robot.Subsystems.TurretSubsystem;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {
	// Subsystems
	public ExampleSubsystem m_ExampleSubsystem;
	public LimelightSubsystem m_LimelightSubsystem;
	public TurretSubsystem m_TurretSubsystem;

	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map
		m_ExampleSubsystem = new ExampleSubsystem(RobotMap.exampleID);

		m_LimelightSubsystem = new LimelightSubsystem(RobotMap.limelight);

		m_TurretSubsystem = new TurretSubsystem(RobotMap.turretMotor, m_LimelightSubsystem);
	}
}
