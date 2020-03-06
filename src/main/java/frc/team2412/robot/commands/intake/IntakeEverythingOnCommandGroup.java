package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.indexer.IndexAllOnCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackDownCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackInCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontDownCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontInCommand;
import frc.team2412.robot.subsystems.IndexerLiftMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IntakeOnOffSubsystem;
import frc.team2412.robot.subsystems.IntakeUpDownSubsystem;

public class IntakeEverythingOnCommandGroup extends ParallelCommandGroup {
	public IntakeEverythingOnCommandGroup(IntakeOnOffSubsystem intakeOnOffSubsystem,
			IntakeUpDownSubsystem intakeUpDownSubsystem, IndexerMotorSubsystem indexerMotorSubsystem, IndexerLiftMotorSubsystem indexerLiftMotorSubsystem) {

		addCommands(new IntakeFrontInCommand(intakeOnOffSubsystem), new IntakeBackInCommand(intakeOnOffSubsystem),
				new IntakeFrontDownCommand(intakeUpDownSubsystem), new IntakeBackDownCommand(intakeUpDownSubsystem),
				new IndexAllOnCommand(indexerMotorSubsystem, indexerLiftMotorSubsystem));
	}

}
