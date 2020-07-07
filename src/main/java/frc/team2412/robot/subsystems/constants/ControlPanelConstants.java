package frc.team2412.robot.subsystems.constants;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

public class ControlPanelConstants {

	public static enum Colors {

		BLUE(blueTarget, "BLUE"), GREEN(greenTarget, "GREEN"), RED(redTarget, "RED"), YELLOW(yellowTarget, "YELLOW");

		public Color color;

		public String name;

		private Colors(Color color, String name) {
			this.color = color;
			this.name = name;
		}

		public String toString() {
			return name;
		}

	}

	public static Color blueTarget = ColorMatch.makeColor(0, 0, 255);
	public static Color greenTarget = ColorMatch.makeColor(0, 255, 0);
	public static Color redTarget = ColorMatch.makeColor(255, 0, 0);
	public static Color yellowTarget = ColorMatch.makeColor(0, 255, 255);

	public static Color TargetColor = blueTarget;
}
