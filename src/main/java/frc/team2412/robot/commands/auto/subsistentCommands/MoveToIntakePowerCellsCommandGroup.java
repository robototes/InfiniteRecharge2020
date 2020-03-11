package frc.team2412.robot.commands.auto.subsistentCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team2412.robot.commands.drive.MoveToPowerCellsCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontDownCommand;
import frc.team2412.robot.commands.intake.front.IntakeFrontInCommand;
import frc.team2412.robot.subsystems.DriveBaseSubsystem;
import frc.team2412.robot.subsystems.intake.IntakeFrontMotorSubsystem;
import frc.team2412.robot.subsystems.intake.IntakeFrontPneumaticSubsystem;


public class MoveToIntakePowerCellsCommandGroup extends ParallelCommandGroup {

	public MoveToIntakePowerCellsCommandGroup(DriveBaseSubsystem driveBaseSubsystem, IntakeFrontMotorSubsystem motorSubsystem, IntakeFrontPneumaticSubsystem pneumaticSubsystem) {

		addCommands(new IntakeFrontDownCommand(pneumaticSubsystem),
				new IntakeFrontInCommand(motorSubsystem),
				new MoveToPowerCellsCommand(driveBaseSubsystem));
	}

}
