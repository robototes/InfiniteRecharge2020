package frc.team2412.robot.commands.intake.back;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

public class IntakeBackBothOnCommandGroup extends ParallelCommandGroup {
	public IntakeBackBothOnCommandGroup(IntakeUpDownSubsystem intakeUpDownSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem) {
		addCommands(new IntakeBackUpCommand(intakeUpDownSubsystem), new IntakeBackInCommand(intakeOnOffSubsystem));
	}

}
