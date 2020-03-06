package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.intake.back.IntakeBackInCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontInCommand;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IntakeBothInCommandGroup extends ParallelCommandGroup {
	public IntakeBothInCommandGroup(IntakeMotorSubsystem intakeOnOffSubsystem) {
		addCommands(new IntakeFrontInCommand(intakeOnOffSubsystem, false),
				new IntakeBackInCommand(intakeOnOffSubsystem));
	}
}
