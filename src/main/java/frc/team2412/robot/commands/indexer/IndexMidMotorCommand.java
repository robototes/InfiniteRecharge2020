package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.subsystems.LiftMotorSubsystem;

//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexMidMotorCommand extends CommandBase {

	private LiftMotorSubsystem m_indexerMotorSubsystem;

	public IndexMidMotorCommand(LiftMotorSubsystem motorSubsystem) {
		m_indexerMotorSubsystem = motorSubsystem;
	}

	public void execute() {
		m_indexerMotorSubsystem.up();
	}

	public void end() {
		m_indexerMotorSubsystem.off();
	}
}
