package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.intake.back.IntakeBackUpCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontUpCommand;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;

public class IntakeBothUpCommand extends ParallelCommandGroup {

	public IntakeBothUpCommand(IntakeLiftSubsystem intakeUpDownSubsystem) {
		addRequirements(intakeUpDownSubsystem);
		addCommands(new IntakeFrontUpCommand(intakeUpDownSubsystem, false),
				new IntakeBackUpCommand(intakeUpDownSubsystem, false));
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
