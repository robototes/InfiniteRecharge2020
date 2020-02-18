package frc.team2412.robot.Commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.Subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.Subsystems.IntakeUpDownSubsystem;

public class IntakeFrontBothOffCommandGroup extends ParallelCommandGroup {

	private IntakeUpDownSubsystem m_intakeUpDownSubsystem;
	private IntakeOnOffSubsystem m_intakeOnOffSubsystem;

	public IntakeFrontBothOffCommandGroup(IntakeUpDownSubsystem intakeUpDownSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem) {
		addRequirements(intakeUpDownSubsystem, intakeOnOffSubsystem);
		m_intakeUpDownSubsystem = intakeUpDownSubsystem;
		m_intakeOnOffSubsystem = intakeOnOffSubsystem;

		addCommands(new IntakeFrontUpCommand(m_intakeUpDownSubsystem),
				new IntakeFrontOffCommand(m_intakeOnOffSubsystem));
	}

}
