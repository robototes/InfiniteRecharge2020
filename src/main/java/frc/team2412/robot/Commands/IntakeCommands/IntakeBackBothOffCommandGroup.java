package frc.team2412.robot.Commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.Subsystems.IntakeUpDownSubsystem;

public class IntakeBackBothOffCommandGroup extends ParallelCommandGroup {

	private IntakeUpDownSubsystem m_intakeUpDownSubsystem;
	private IntakeOnOffSubsystem m_intakeOnOffSubsystem;

	public IntakeBackBothOffCommandGroup(IntakeUpDownSubsystem intakeUpDownSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem) {
		addRequirements(intakeUpDownSubsystem, intakeOnOffSubsystem);
		m_intakeUpDownSubsystem = intakeUpDownSubsystem;
		m_intakeOnOffSubsystem = intakeOnOffSubsystem;

		addCommands(new IntakeBackUpCommand(m_intakeUpDownSubsystem), new IntakeBackOffCommand(m_intakeOnOffSubsystem));
	}

}
