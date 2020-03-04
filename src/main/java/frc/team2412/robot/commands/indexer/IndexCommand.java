package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.RobotState.UnbalancedSide;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

public class IndexCommand extends SequentialCommandGroup{
    private IndexerMotorSubsystem m_indexerMotorSubsystem;
 private IndexerSensorSubsystem m_indexerSensorSubsystem;
 
 private IndexFrontCommand m_indexFrontCommand;
 private IndexBackCommand m_indexBackCommand;
 public IndexCommand(IndexerMotorSubsystem indexerMotorSubsystem, IndexerSensorSubsystem indexerSensorSubsystem) {
  m_indexerMotorSubsystem = indexerMotorSubsystem;
  m_indexerSensorSubsystem = indexerSensorSubsystem;
  m_indexFrontCommand = new IndexFrontCommand(indexerMotorSubsystem, indexerSensorSubsystem);
  m_indexBackCommand = new IndexBackCommand(indexerMotorSubsystem, indexerSensorSubsystem);
  //addRequirements(indexerMotorSubsystem);
  addCommands(new ConditionalCommand(m_indexFrontCommand, m_indexBackCommand, ()-> RobotState.m_unbalancedSide == UnbalancedSide.FRONT));

 }

}