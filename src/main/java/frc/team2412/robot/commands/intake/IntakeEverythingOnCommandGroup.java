package frc.team2412.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.indexer.IndexAllInCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackDownCommand;
import frc.team2412.robot.commands.intake.back.IntakeBackInCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontDownCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontInCommand;
import frc.team2412.robot.subsystems.IntakeLiftSubsystem;
import frc.team2412.robot.subsystems.IntakeMotorSubsystem;
import frc.team2412.robot.subsystems.index.IndexerSubsystemSuperStructure;

public class IntakeEverythingOnCommandGroup extends ParallelCommandGroup {
	public IntakeEverythingOnCommandGroup(IntakeMotorSubsystem intakeOnOffSubsystem,
										  IntakeLiftSubsystem intakeUpDownSubsystem, IndexerSubsystemSuperStructure indexerMotorSubsystem) {

		addCommands(new IntakeFrontInCommand(intakeOnOffSubsystem), new IntakeBackInCommand(intakeOnOffSubsystem),
				new IntakeFrontDownCommand(intakeUpDownSubsystem), new IntakeBackDownCommand(intakeUpDownSubsystem),
				new IndexAllInCommand(indexerMotorSubsystem));
	}

}
