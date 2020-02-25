package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.intake.back.IntakeBackOutCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontOutCommand;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

public class IntakeBothOutCommandGroup extends ParallelCommandGroup {
	public IntakeBothOutCommandGroup(IntakeOnOffSubsystem intakeOnOffSubsystem) {
		addCommands(new IntakeFrontOutCommand(intakeOnOffSubsystem), new IntakeBackOutCommand(intakeOnOffSubsystem));
	}

}
