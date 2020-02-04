package frc.team2412.robot.Subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Subsystems.constants.ControlPanelConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class ControlPanelColorSubsystem extends SubsystemBase implements Loggable {

	private ColorSensorV3 m_colorSensor;
	private ColorMatch m_colorMatcher;
	private Talon m_wheelMotor;

	@Log
	private Color m_CurrentColor;

	private Color m_StartColor;
	private Color m_ColorUnderBar;
	private int rotationCount = 0;

	public ControlPanelColorSubsystem(ColorSensorV3 colorSensor, Talon motor, ColorMatch colorMatch) {
		this.m_colorSensor = colorSensor;
		this.m_wheelMotor = motor;
		this.m_colorMatcher = colorMatch;
	}

	public Color colorMatcher(Color detectedColor) {
		ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
		if (match.color == ControlPanelConstants.blueTarget) {
			return ControlPanelConstants.blueTarget;
		} else if (match.color == ControlPanelConstants.redTarget) {
			return ControlPanelConstants.redTarget;
		} else if (match.color == ControlPanelConstants.greenTarget) {
			return ControlPanelConstants.greenTarget;
		} else if (match.color == ControlPanelConstants.yellowTarget) {
			return ControlPanelConstants.yellowTarget;
		}

		return detectedColor;

	}

	public String colorToString(Color m_color) {
		if (m_color.equals(ControlPanelConstants.blueTarget))
			return "blue";
		if (m_color.equals(ControlPanelConstants.greenTarget))
			return "green";
		if (m_color.equals(ControlPanelConstants.redTarget))
			return "red";
		if (m_color.equals(ControlPanelConstants.yellowTarget))
			return "yellow";
		else {
			return "Unknown";
		}
	}

	public Color getColorUnderBar(Color readColor) {
		if (readColor.equals(ControlPanelConstants.blueTarget)) {
			return ControlPanelConstants.redTarget;
		} else if (readColor.equals(ControlPanelConstants.greenTarget)) {
			return ControlPanelConstants.yellowTarget;
		} else if (readColor.equals(ControlPanelConstants.redTarget)) {
			return ControlPanelConstants.blueTarget;
		} else if (readColor.equals(ControlPanelConstants.yellowTarget)) {
			return ControlPanelConstants.greenTarget;
		} else {
			return Color.kBlack;
		}
	}

	public void rotateControlPanel() {
		rotationCount = 0;
		m_StartColor = m_colorSensor.getColor();
		m_wheelMotor.set(0.5);
		while (rotationCount <= 7) {
			m_CurrentColor = m_colorSensor.getColor();
			if (m_CurrentColor.equals(m_StartColor)) {
				rotationCount++;
			}
		}
		m_wheelMotor.set(0);
	}

	public void setToTargetColor() {

		m_CurrentColor = m_colorSensor.getColor();
		m_ColorUnderBar = getColorUnderBar(m_CurrentColor);

		while (m_ColorUnderBar != ControlPanelConstants.TargetColor) {
			m_wheelMotor.set(0.25);
			m_CurrentColor = m_colorSensor.getColor();
			m_ColorUnderBar = getColorUnderBar(m_CurrentColor);
		}

		m_wheelMotor.set(0);

	}
}
