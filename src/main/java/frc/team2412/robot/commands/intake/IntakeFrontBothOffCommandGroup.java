package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

public class IntakeFrontBothOffCommandGroup extends ParallelCommandGroup {
	public IntakeFrontBothOffCommandGroup(IntakeUpDownSubsystem intakeUpDownSubsystem,
			IntakeOnOffSubsystem intakeOnOffSubsystem) {

		addCommands(new IntakeFrontDownCommand(intakeUpDownSubsystem), new IntakeFrontOffCommand(intakeOnOffSubsystem));
	}

}
