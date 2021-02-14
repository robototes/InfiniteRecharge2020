package frc.team2412.robot.subsystems;

import static frc.team2412.robot.subsystems.constants.ControlPanelConstants.blueTarget;
import static frc.team2412.robot.subsystems.constants.ControlPanelConstants.greenTarget;
import static frc.team2412.robot.subsystems.constants.ControlPanelConstants.redTarget;
import static frc.team2412.robot.subsystems.constants.ControlPanelConstants.yellowTarget;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.subsystems.constants.ControlPanelConstants;

public class ControlPanelColorSubsystem extends SubsystemBase {

	private ColorSensorV3 colorSensor;
	private ColorMatch colorMatcher = new ColorMatch();
	private WPI_TalonFX wheelMotor;

	private Color currentColor;

	private Color startColor;
	private Color ColorUnderBar;
	private int rotationCount = 0;

	public ControlPanelColorSubsystem(ColorSensorV3 colorSensor, WPI_TalonFX motor) {
		this.colorSensor = colorSensor;
		this.wheelMotor = motor;

		colorMatcher.addColorMatch(redTarget);
		colorMatcher.addColorMatch(blueTarget);
		colorMatcher.addColorMatch(greenTarget);
		colorMatcher.addColorMatch(yellowTarget);
	}

	public Color colorMatch(Color detectedColor) {
		ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
		return match.color;

	}

	public String colorToString(Color color) {
		if (color.equals(blueTarget))
			return "blue";
		if (color.equals(greenTarget))
			return "green";
		if (color.equals(redTarget))
			return "red";
		if (color.equals(yellowTarget))
			return "yellow";
		else {
			return "Unknown";
		}
	}

	public Color getColorUnderBar(Color readColor) {
		if (readColor.equals(blueTarget)) {
			return ControlPanelConstants.redTarget;
		} else if (readColor.equals(greenTarget)) {
			return ControlPanelConstants.yellowTarget;
		} else if (readColor.equals(redTarget)) {
			return ControlPanelConstants.blueTarget;
		} else if (readColor.equals(yellowTarget)) {
			return ControlPanelConstants.greenTarget;
		} else {
			return Color.kBlack;
		}
	}

	public void rotateControlPanel() {
		rotationCount = 0;
		startColor = colorMatch(colorSensor.getColor());
		wheelMotor.set(0.5);
		while (rotationCount <= 7) {
			currentColor = colorMatch(colorSensor.getColor());
			if (currentColor.equals(startColor)) {
				rotationCount++;
			}
		}
		wheelMotor.set(0);
	}

	public void setToTargetColor() {

		currentColor = colorMatch(colorSensor.getColor());
		ColorUnderBar = getColorUnderBar(currentColor);

		while (ColorUnderBar != ControlPanelConstants.TargetColor) {
			wheelMotor.set(0.25);
			currentColor = colorMatch(colorSensor.getColor());
			ColorUnderBar = getColorUnderBar(currentColor);
		}

		wheelMotor.set(0);

	}
}
