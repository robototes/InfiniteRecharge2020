package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.intake.back.IntakeBackInCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontInCommand;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

public class IntakeBothInCommandGroup extends ParallelCommandGroup {
	public IntakeBothInCommandGroup(IntakeOnOffSubsystem intakeOnOffSubsystem) {
		addCommands(new IntakeFrontInCommand(intakeOnOffSubsystem), new IntakeBackInCommand(intakeOnOffSubsystem));
	}

}
