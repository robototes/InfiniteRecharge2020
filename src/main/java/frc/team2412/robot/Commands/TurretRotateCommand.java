package frc.team2412.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.LimelightSubsystem;
import frc.team2412.robot.Subsystems.TurretSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class TurretRotateCommand extends CommandBase {
	TurretSubsystem m_TurretSubsystem;
	LimelightSubsystem m_LimelightSubsystem;

	public TurretRotateCommand(TurretSubsystem turretSubsystem, LimelightSubsystem limelightSubsystem) {
		m_TurretSubsystem = turretSubsystem;
		addRequirements(turretSubsystem);

		m_LimelightSubsystem = limelightSubsystem;
	}

	@Override
	public void execute() {
		// run the example method
		m_TurretSubsystem.turnBasedOnLimelightAngle(m_LimelightSubsystem.getYawFromTarget());
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}
