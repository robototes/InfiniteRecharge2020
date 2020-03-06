package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.intake.back.IntakeBackUpCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontUpCommand;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IntakeBothUpCommand extends ParallelCommandGroup {

	public IntakeBothUpCommand(IntakeLiftSubsystem intakeUpDownSubsystem, IntakeMotorSubsystem intakeOnOffSubsystem) {
		addRequirements(intakeUpDownSubsystem, intakeOnOffSubsystem);
		addCommands(new IntakeFrontUpCommand(intakeUpDownSubsystem), new IntakeBackUpCommand(intakeUpDownSubsystem));
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
