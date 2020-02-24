package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

public class IntakeBackBothOffCommandGroup extends ParallelCommandGroup {
	public IntakeBackBothOffCommandGroup(IntakeUpDownSubsystem intakeUpDownSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem) {
		addCommands(new IntakeBackDownCommand(intakeUpDownSubsystem), new IntakeBackOffCommand(intakeOnOffSubsystem));
	}

}
