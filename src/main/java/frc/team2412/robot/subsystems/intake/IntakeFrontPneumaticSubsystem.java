package frc.team2412.robot.subsystems.intake;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.intake.IIntakePneumaticSubsystem.IntakePneumaticSubsystemType;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class IntakeFrontPneumaticSubsystem extends SubsystemBase implements IIntakePneumaticSubsystem {
    private Solenoid m_intakeSolenoid;

    public IntakeFrontPneumaticSubsystem(Solenoid solenoid) {
       m_intakeSolenoid = solenoid;
	}

    @Override
    @Log.BooleanBox(tabName = "Intake", colorWhenTrue = "red", colorWhenFalse = "green", methodName = "Get Front Solenoid Value")   
    public Solenoid getMainSolenoid() {
        return m_intakeSolenoid;
    }

    @Override
    public void set(boolean val) {
        IIntakePneumaticSubsystem.super.set(val);
    }
    @Override
    public IntakePneumaticSubsystemType getType() {
        return IntakePneumaticSubsystemType.FRONT;
    }

}
