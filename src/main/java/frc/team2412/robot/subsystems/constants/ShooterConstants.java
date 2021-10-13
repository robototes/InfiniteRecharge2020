package frc.team2412.robot.subsystems.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

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

		public static ShooterDistanceDataPoint from(double m_ty, double m_hoodAngle, double m_shooterPower, double m_distance) {
			return new ShooterDistanceDataPoint(new InterpolatingDouble(m_ty), new InterpolatingDouble(m_hoodAngle), 
					new InterpolatingDouble(m_shooterPower), new InterpolatingDouble(m_distance));
		}

		public String toString() {
			return String.format("m_ty: %f, m_shooterPower: %f, m_hoodAngle: %f", m_ty.value(), m_shooterPower.value(), m_hoodAngle.value());
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

		public static ShooterSkewDataPoint from(double m_ts, double m_turretDeltaForInner, boolean innerGoalPossible, boolean m_outerGoalPossible) {
			return new ShooterSkewDataPoint(new InterpolatingDouble(m_ts), new InterpolatingDouble(m_turretDeltaForInner), 
					innerGoalPossible, m_outerGoalPossible);
		}
	}

	public static ArrayList<ShooterDistanceDataPoint> distanceData = new ArrayList<ShooterDistanceDataPoint>();
	public static ArrayList<ShooterSkewDataPoint> skewData = new ArrayList<ShooterSkewDataPoint>();

	static {
		// add in data here
		// distanceData.add(ShooterDistanceDataPoint.from(24, 0.1, -2300, 2));
		// distanceData.add(ShooterDistanceDataPoint.from(12, 0.28, -3700, 3));
		// distanceData.add(ShooterDistanceDataPoint.from(3, 0.28, -3700, 4));
		// distanceData.add(ShooterDistanceDataPoint.from(0, 0.29, -3700, 6));
		// distanceData.add(ShooterDistanceDataPoint.from(-0.85, 0.3, -3700, 8));
		// distanceData.add(ShooterDistanceDataPoint.from(-7.95, 0.31, -4000, 12));
		// distanceData.add(ShooterDistanceDataPoint.from(-12, 0.29, -5500, 14));
		// distanceData.add(ShooterDistanceDataPoint.from(-13.14, 0.35, -4420, 16));
		// distanceData.add(ShooterDistanceDataPoint.from(-15, 0.31, -5500, 18));
		// distanceData.add(ShooterDistanceDataPoint.from(-18, 0.3, -5500, 20));
		
		// distanceData.add(ShooterDistanceDataPoint.from(-20, 0.31, -6000, 28));

		distanceData.add(ShooterDistanceDataPoint.from(12.3, 0.22, -4000, 5));
		distanceData.add(ShooterDistanceDataPoint.from(-4.7, 0.29, -4500, 10));
		distanceData.add(ShooterDistanceDataPoint.from(-13.6, 0.3, -5000, 15));
		distanceData.add(ShooterDistanceDataPoint.from(-18.2, 0.29, -5800, 20));

		// sort data
		distanceData.sort((o1, o2) -> o1.m_ty.compareTo(o2.m_ty));

		skewData.sort((o1, o2) -> o1.m_ts.compareTo(o2.m_ts));
	}

	public static <T extends Interpolable<T>> @Nullable T interpolateInList(List<T> list,
			Function<T, InterpolatingDouble> fieldSupplier, double t_in) {
		InterpolatingDouble tyInter = new InterpolatingDouble(t_in);
		if (list.isEmpty()) {
			return null;
		}


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
