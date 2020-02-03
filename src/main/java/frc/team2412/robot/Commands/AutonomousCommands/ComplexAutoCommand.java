package frc.team2412.robot.Commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.Subsystems.AutonumousSubsystem;

public class ComplexAutoCommand extends ParallelCommandGroup {

	AutonumousSubsystem m_autonumousSubsystem;

	public ComplexAutoCommand(AutonumousSubsystem autonumousSubsystem) {
		addRequirements(autonumousSubsystem);
		m_autonumousSubsystem = autonumousSubsystem;

	}

	@Override
	public void execute() {
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
