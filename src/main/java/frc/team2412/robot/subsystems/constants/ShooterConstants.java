package frc.team2412.robot.subsystems.constants;

import java.util.ArrayList;
import java.util.Comparator;

import com.robototes.math.Interpolable;
import com.robototes.math.InterpolatingDouble;

import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class ShooterConstants {
	public static class ShooterDistanceDataPoint implements Interpolable<ShooterDistanceDataPoint>, Loggable {
		@Log.ToString
		public InterpolatingDouble m_ty;
		@Log.ToString
		public InterpolatingDouble m_hoodAngle;
		@Log.ToString
		public InterpolatingDouble m_shooterPower;
		@Log.ToString
		public InterpolatingDouble m_distance;

		public ShooterDistanceDataPoint(InterpolatingDouble m_ty, InterpolatingDouble m_hoodAngle,
				InterpolatingDouble m_shooterPower, InterpolatingDouble m_distance) {
			this.m_ty = m_ty;
			this.m_hoodAngle = m_hoodAngle;
			this.m_shooterPower = m_shooterPower;
			this.m_distance = m_distance;
		}

		@Override
		public ShooterDistanceDataPoint interpolate(ShooterDistanceDataPoint other, double t) {
			return new ShooterDistanceDataPoint(m_ty.interpolate(other.m_ty, t),
					m_hoodAngle.interpolate(other.m_hoodAngle, t), m_shooterPower.interpolate(other.m_shooterPower, t),
					m_distance.interpolate(other.m_distance, t));
		}
	}

	public static class ShooterSkewDataPoint implements Interpolable<ShooterSkewDataPoint>, Loggable {
		@Log.ToString
		public InterpolatingDouble m_ts;
		@Log.ToString
		public InterpolatingDouble m_turretDeltaForInner;
		@Log.ToString
		public boolean m_innerGoalPossible;
		@Log.ToString
		public boolean m_outerGoalPossible;

		public ShooterSkewDataPoint(InterpolatingDouble m_ts, InterpolatingDouble m_turretDeltaForInner,
				boolean m_innerGoalPossible, boolean m_outerGoalPossible) {
			this.m_ts = m_ts;
			this.m_turretDeltaForInner = m_turretDeltaForInner;
			this.m_innerGoalPossible = m_innerGoalPossible;
			this.m_outerGoalPossible = m_outerGoalPossible;
		}

		@Override
		public ShooterSkewDataPoint interpolate(ShooterSkewDataPoint other, double t) {
			return new ShooterSkewDataPoint(m_ts.interpolate(other.m_ts, t),
					m_turretDeltaForInner.interpolate(other.m_turretDeltaForInner, t),
					m_innerGoalPossible && other.m_innerGoalPossible, m_outerGoalPossible && other.m_outerGoalPossible);
		}
	}

	public static ArrayList<ShooterDistanceDataPoint> distanceData = new ArrayList<ShooterDistanceDataPoint>();
	public static ArrayList<ShooterSkewDataPoint> skewData = new ArrayList<ShooterSkewDataPoint>();

	static {
		// add in data here

		// sort data
		distanceData.sort(new Comparator<ShooterDistanceDataPoint>() {
			@Override
			public int compare(ShooterDistanceDataPoint o1, ShooterDistanceDataPoint o2) {
				return o1.m_ty.compareTo(o2.m_ty);
			}
		});

		skewData.sort(new Comparator<ShooterSkewDataPoint>() {
			@Override
			public int compare(ShooterSkewDataPoint o1, ShooterSkewDataPoint o2) {
				return o1.m_ts.compareTo(o2.m_ts);
			}
		});
	}

	public static ShooterDistanceDataPoint getDistanceDataPointFromTy(double ty) {
		InterpolatingDouble tyInter = new InterpolatingDouble(ty);

		ShooterDistanceDataPoint lowerQuery = distanceData.get(0);
		ShooterDistanceDataPoint upperQuery = distanceData.get(distanceData.size() - 1);

		for (ShooterDistanceDataPoint point : distanceData) {
			if (point.m_ty.compareTo(tyInter) <= 0 && lowerQuery.m_ty.compareTo(point.m_ty) < 0) {
				lowerQuery = point;
			}

			if (point.m_ty.compareTo(tyInter) > 0 && upperQuery.m_ty.compareTo(point.m_ty) > 0) {
				upperQuery = point;
			}
		}
		if (lowerQuery == upperQuery) {
			if (lowerQuery == distanceData.get(0)) {
				upperQuery = distanceData.get(1);
			} else {
				lowerQuery = distanceData.get(distanceData.size() - 2);
			}
		}
		double t = lowerQuery.m_ty.inverseInterpolate(upperQuery.m_ty, tyInter);

		return lowerQuery.interpolate(upperQuery, t);
	}

	public static ShooterSkewDataPoint getSkewDataPointFromTs(double ts) {
		InterpolatingDouble tsInter = new InterpolatingDouble(ts);

		ShooterSkewDataPoint lowerQuery = skewData.get(0);
		ShooterSkewDataPoint upperQuery = skewData.get(skewData.size() - 1);

		for (ShooterSkewDataPoint point : skewData) {
			if (point.m_ts.compareTo(tsInter) <= 0 && lowerQuery.m_ts.compareTo(point.m_ts) < 0) {
				lowerQuery = point;
			}

			if (point.m_ts.compareTo(tsInter) > 0 && upperQuery.m_ts.compareTo(point.m_ts) > 0) {
				upperQuery = point;
			}
		}
		if (lowerQuery == upperQuery) {
			if (lowerQuery == skewData.get(0)) {
				upperQuery = skewData.get(1);
			} else {
				lowerQuery = skewData.get(skewData.size() - 2);
			}
		}
		double t = lowerQuery.m_ts.inverseInterpolate(upperQuery.m_ts, tsInter);

		return lowerQuery.interpolate(upperQuery, t);
	}

}
