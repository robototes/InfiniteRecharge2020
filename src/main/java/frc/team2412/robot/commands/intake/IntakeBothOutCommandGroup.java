package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.intake.back.IntakeBackOutCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontOutCommand;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IntakeBothOutCommandGroup extends ParallelCommandGroup {
	public IntakeBothOutCommandGroup(IntakeMotorSubsystem intakeOnOffSubsystem) {
		addCommands(new IntakeFrontOutCommand(intakeOnOffSubsystem, false),
				new IntakeBackOutCommand(intakeOnOffSubsystem, false));
	}

}
