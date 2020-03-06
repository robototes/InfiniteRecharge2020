package frc.team2412.robot.commands.intake.front;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IntakeFrontBothOnCommandGroup extends ParallelCommandGroup {
	public IntakeFrontBothOnCommandGroup(IntakeLiftSubsystem intakeUpDownSubsystem,
			IntakeMotorSubsystem intakeOnOffSubsystem) {

		addCommands(new IntakeFrontInCommand(intakeOnOffSubsystem, false), new IntakeFrontDownCommand(intakeUpDownSubsystem));
	}

}
