package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.intake.back.IntakeBackDownCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontDownCommand;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;

public class IntakeBothDownCommand extends ParallelCommandGroup {

	public IntakeBothDownCommand(IntakeLiftSubsystem intakeUpDownSubsystem,
			IntakeMotorSubsystem intakeOnOffSubsystem) {
		addRequirements(intakeUpDownSubsystem, intakeOnOffSubsystem);
		addCommands(new IntakeFrontDownCommand(intakeUpDownSubsystem),
				new IntakeBackDownCommand(intakeUpDownSubsystem));
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
