package frc.team2412.robot.commands.intake.front;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;

public class IntakeFrontBothOnCommandGroup extends ParallelCommandGroup {
	public IntakeFrontBothOnCommandGroup(IntakeLiftSubsystem intakeUpDownSubsystem,
			IntakeMotorSubsystem intakeOnOffSubsystem) {

		addCommands(new IntakeFrontInCommand(intakeOnOffSubsystem), new IntakeFrontDownCommand(intakeUpDownSubsystem));
	}

}
