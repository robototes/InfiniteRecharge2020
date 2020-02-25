package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

public class IntakeBothDownCommand extends ParallelCommandGroup {

	public IntakeBothDownCommand(IntakeUpDownSubsystem intakeUpDownSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem) {
		addRequirements(intakeUpDownSubsystem, intakeOnOffSubsystem);
		addCommands(new IntakeFrontDownCommand(intakeUpDownSubsystem),
				new IntakeBackDownCommand(intakeUpDownSubsystem));
	}

	public boolean isFinished() {
		return true;
	}
}
