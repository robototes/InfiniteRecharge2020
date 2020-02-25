package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.indexer.IndexAllOnCommand;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

public class IntakeEverythingOnCommandGroup extends ParallelCommandGroup {
	public IntakeEverythingOnCommandGroup(IntakeOnOffSubsystem intakeOnOffSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem, IndexerMotorSubsystem indexerMotorSubsystem) {

		addCommands(new IntakeFrontOnCommand(intakeOnOffSubsystem), new IntakeBackOnCommand(intakeOnOffSubsystem),
				new IntakeFrontDownCommand(intakeUpDownSubsystem), new IntakeBackDownCommand(intakeUpDownSubsystem),
				new IndexAllOnCommand(indexerMotorSubsystem));
	}

}
