package frc.team2412.robot.Commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.Subsystems.AutonumousSubsystem;

public class BasicAutoCommand extends SequentialCommandGroup {

	AutonumousSubsystem m_autonumousSubsystem;

	public BasicAutoCommand(AutonumousSubsystem autonumousSubsystem) {
		addRequirements(autonumousSubsystem);
		m_autonumousSubsystem = autonumousSubsystem;
	}

	@Override
	public void execute() {
		m_autonumousSubsystem.basicAutoCommand();
		;
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
