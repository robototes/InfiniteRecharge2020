package frc.team2412.robot;

import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class Logging implements Loggable {

	@Log
	private String allianceName;
	@Log
	private int teamNumber = 2412;
	@Log
	private boolean testMatch;
	@Log
	private boolean officialMatch;

	public Logging() {

	}

	@Config
	public void setMatchType(boolean isTestMatch) {
		if (isTestMatch = true) {
			testMatch = true;
			officialMatch = false;
		} else {
			officialMatch = true;
			testMatch = false;
		}
	}

	@Config
	public void setAllianceName(String name) {
		allianceName = name;
	}

	@Config
	public void setTeanNumber(int teamNum) {
		teamNumber = teamNum;
	}
}
