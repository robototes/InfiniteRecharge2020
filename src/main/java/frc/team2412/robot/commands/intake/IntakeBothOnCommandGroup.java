package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

public class IntakeBothOnCommandGroup extends ParallelCommandGroup {
	public IntakeBothOnCommandGroup(IntakeOnOffSubsystem intakeOnOffSubsystem) {

		addCommands(new IntakeSpitCommand(intakeOnOffSubsystem));
	}

}
