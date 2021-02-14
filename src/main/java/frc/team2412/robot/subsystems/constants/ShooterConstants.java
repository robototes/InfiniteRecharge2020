package frc.team2412.robot.subsystems.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
		public boolean innerGoalPossible;
		@Log.ToString
		public boolean m_outerGoalPossible;

		public ShooterSkewDataPoint(InterpolatingDouble m_ts, InterpolatingDouble m_turretDeltaForInner,
				boolean m_innerGoalPossible, boolean m_outerGoalPossible) {
			this.m_ts = m_ts;
			this.m_turretDeltaForInner = m_turretDeltaForInner;
			this.innerGoalPossible = m_innerGoalPossible;
			this.m_outerGoalPossible = m_outerGoalPossible;
		}

		@Override
		public ShooterSkewDataPoint interpolate(ShooterSkewDataPoint other, double t) {
			return new ShooterSkewDataPoint(m_ts.interpolate(other.m_ts, t),
					m_turretDeltaForInner.interpolate(other.m_turretDeltaForInner, t),
					innerGoalPossible && other.innerGoalPossible, m_outerGoalPossible && other.m_outerGoalPossible);
		}
	}

	public static ArrayList<ShooterDistanceDataPoint> distanceData = new ArrayList<ShooterDistanceDataPoint>();
	public static ArrayList<ShooterSkewDataPoint> skewData = new ArrayList<ShooterSkewDataPoint>();

	static {
		// add in data here

		// sort data
		distanceData.sort((o1, o2) -> o1.m_ty.compareTo(o2.m_ty));

		skewData.sort((o1, o2) -> o1.m_ts.compareTo(o2.m_ts));
	}

	public static <T extends Interpolable<T>> T interpolateInList(List<T> list,
			Function<T, InterpolatingDouble> fieldSupplier, double t_in) {
		InterpolatingDouble tyInter = new InterpolatingDouble(t_in);

		T lowerQuery = list.get(0);
		T upperQuery = list.get(list.size() - 1);

		for (T point : list) {
			if (fieldSupplier.apply(point).compareTo(tyInter) <= 0
					&& fieldSupplier.apply(lowerQuery).compareTo(fieldSupplier.apply(point)) < 0) {
				lowerQuery = point;
			}

			if (fieldSupplier.apply(point).compareTo(tyInter) > 0
					&& fieldSupplier.apply(upperQuery).compareTo(fieldSupplier.apply(point)) > 0) {
				upperQuery = point;
			}
		}
		if (lowerQuery == upperQuery) {
			if (lowerQuery == list.get(0)) {
				upperQuery = list.get(1);
			} else {
				lowerQuery = list.get(list.size() - 2);
			}
		}
		double t = fieldSupplier.apply(lowerQuery).inverseInterpolate(fieldSupplier.apply(upperQuery), tyInter);

		return lowerQuery.interpolate(upperQuery, t);
	}

	public static ShooterDistanceDataPoint getDistanceDataPointFromTy(double ty) {
		return interpolateInList(distanceData, p -> p.m_ty, ty);
	}

	public static ShooterSkewDataPoint getSkewDataPointFromTs(double ts) {
		return interpolateInList(skewData, p -> p.m_ts, ts);
	}

}
