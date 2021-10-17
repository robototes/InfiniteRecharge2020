package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.intake.back.IntakeBackInCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontInCommand;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;

public class IntakeBothInCommandGroup extends CommandBase {
	public IntakeMotorSubsystem subsystem; 
	public IntakeBothInCommandGroup(IntakeMotorSubsystem intakeOnOffSubsystem) {
		addRequirements(intakeOnOffSubsystem);
		subsystem = intakeOnOffSubsystem;
	}

	@Override
	public void execute() {
		subsystem.setIntake(0.3);
	}

	@Override
	public boolean isFinished() {
		return true;
	}

}
