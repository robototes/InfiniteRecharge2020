package frc.team2412.robot.Commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.Subsystems.DriveBaseSubsystem;
import frc.team2412.robot.Subsystems.FlywheelSubsystem;
import frc.team2412.robot.Subsystems.IndexerSubsystem;
import frc.team2412.robot.Subsystems.LiftSubsystem;
import frc.team2412.robot.Subsystems.TurretSubsystem;

public class BasicAutoCommand extends SequentialCommandGroup {

	// Drivebase, lift, Turret
	DriveBaseSubsystem m_driveBaseSubsystem;
	LiftSubsystem m_liftSubsystem;
	TurretSubsystem m_turretSubsystem;
	FlywheelSubsystem m_flywheelSubsystem;
	IndexerSubsystem m_indexerSubsystem;
	StartUpCommandGroup m_startUpCommand;

	public BasicAutoCommand(DriveBaseSubsystem driveBase, LiftSubsystem liftSubsystem, TurretSubsystem turretSubsystem,
			FlywheelSubsystem flyweheelSubsystem, IndexerSubsystem indexerSubsystem) {
		addRequirements(driveBase, liftSubsystem, turretSubsystem, flyweheelSubsystem, indexerSubsystem);
		m_driveBaseSubsystem = driveBase;
		m_liftSubsystem = liftSubsystem;
		m_turretSubsystem = turretSubsystem;
		m_flywheelSubsystem = flyweheelSubsystem;
		m_indexerSubsystem = indexerSubsystem;
		m_startUpCommand = new StartUpCommandGroup(m_driveBaseSubsystem, m_liftSubsystem, m_flywheelSubsystem);
	}

	@Override
	public void execute() {
		m_startUpCommand.execute();
		m_driveBaseSubsystem.setDriveSpeed(1, 0);
		try {
			wait(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m_driveBaseSubsystem.setDriveSpeed(0, 0);
		m_driveBaseSubsystem.angleDrive(-90);
		m_indexerSubsystem.shoot();

	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
