package frc.team2412.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;
import frc.team2412.robot.subsystems.*;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer implements Loggable {
	

	public FlywheelSubsystem m_flywheelSubsystem;

	public HoodSubsystem m_hoodSubsystem;


	public RobotContainer() {
		m_flywheelSubsystem = new FlywheelSubsystem(RobotMap.flywheelLeftMotor, RobotMap.flywheelRightMotor);

		m_hoodSubsystem = new HoodSubsystem(RobotMap.hoodServo1, RobotMap.hoodServo2);

		// Add commands to the autonomous command chooser
		//m_chooser.addOption("Basic Auto", m_basicAutoCommand);
		
	}
}
