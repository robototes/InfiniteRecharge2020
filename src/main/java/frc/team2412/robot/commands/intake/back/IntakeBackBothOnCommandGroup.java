package frc.team2412.robot.commands.intake.back;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;

public class IntakeBackBothOnCommandGroup extends ParallelCommandGroup {
	public IntakeBackBothOnCommandGroup(IntakeLiftSubsystem intakeUpDownSubsystem,
			IntakeMotorSubsystem intakeOnOffSubsystem) {
		addCommands(new IntakeBackUpCommand(intakeUpDownSubsystem), new IntakeBackInCommand(intakeOnOffSubsystem));
	}

}
