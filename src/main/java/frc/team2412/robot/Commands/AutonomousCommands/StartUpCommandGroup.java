package frc.team2412.robot.Commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.FlywheelSubsystem;
import frc.team2412.robot.Subsystems.LiftSubsystem;

public class StartUpCommandGroup extends ParallelCommandGroup {

	DriveBaseSubsystem m_driveBaseSubsystem;
	LiftSubsystem m_liftSubsystem;
	FlywheelSubsystem m_flywheelSubsystem;

	public StartUpCommandGroup(DriveBaseSubsystem driveBase, LiftSubsystem liftSubsystem,
			FlywheelSubsystem flyweheelSubsystem) {
		addRequirements(driveBase, liftSubsystem, flyweheelSubsystem);
		m_driveBaseSubsystem = driveBase;
		m_liftSubsystem = liftSubsystem;
		m_flywheelSubsystem = flyweheelSubsystem;
	}

	@Override
	public void execute() {
		m_liftSubsystem.liftUp();
		m_flywheelSubsystem.shoot();
		m_driveBaseSubsystem.angleDrive(90);
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
