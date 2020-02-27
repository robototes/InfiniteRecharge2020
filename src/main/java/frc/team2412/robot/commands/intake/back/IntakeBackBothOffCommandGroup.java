package frc.team2412.robot.commands.intake.back;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;

public class IntakeBackBothOffCommandGroup extends ParallelCommandGroup {
	public IntakeBackBothOffCommandGroup(IntakeLiftSubsystem intakeUpDownSubsystem,
			IntakeMotorSubsystem intakeOnOffSubsystem) {
		addCommands(new IntakeBackDownCommand(intakeUpDownSubsystem), new IntakeBackOffCommand(intakeOnOffSubsystem));
	}

}
