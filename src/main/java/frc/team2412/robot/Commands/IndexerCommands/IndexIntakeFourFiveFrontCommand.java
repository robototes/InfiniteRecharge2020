package frc.team2412.robot.Commands.IndexerCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.Subsystems.IndexerSensorSubsystem;
import frc.team2412.robot.Subsystems.constants.IndexerConstants;

import static frc.team2412.robot.Subsystems.constants.IndexerConstants.numBalls;
import static frc.team2412.robot.Subsystems.constants.IndexerConstants.unbalancedSide;


//This is an example command for this year. Make sure all commands extend CommandBase and they use take all dependencies(fields) through a constructor
public class IndexIntakeFourFiveFrontCommand extends CommandBase {
    private IndexerSensorSubsystem m_indexerSensorSubsystem;


    public IndexIntakeFourFiveFrontCommand(IndexerSensorSubsystem sensorSubsystem) {
        m_indexerSensorSubsystem = sensorSubsystem;
        addRequirements(sensorSubsystem);
    }


    @Override
    public void execute() {
        //INTAKE CLOSES
    }

    @Override
    public boolean isFinished() {
        if (m_indexerSensorSubsystem.getIndexFrontSensorValue()) {
            unbalancedSide = IndexerConstants.UnbalancedSide.FRONT;
            numBalls++;
            return true;
        } else {
            return false;
        }
    }

}
