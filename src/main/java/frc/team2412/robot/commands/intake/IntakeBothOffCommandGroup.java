package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.intake.back.IntakeBackOffCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontOffCommand;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;

public class IntakeBothOffCommandGroup extends ParallelCommandGroup {
	public IntakeBothOffCommandGroup(IntakeOnOffSubsystem intakeOnOffSubsystem) {

		addCommands(new IntakeFrontOffCommand(intakeOnOffSubsystem), new IntakeBackOffCommand(intakeOnOffSubsystem));
	}

}
