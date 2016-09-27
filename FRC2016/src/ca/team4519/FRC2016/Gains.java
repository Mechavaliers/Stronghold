package ca.team4519.FRC2016;

public class Gains {
	
	
	public static final class ShooterPositions {
		private ShooterPositions(){}
		
		public static float Intake = 102f;
		public static float Straight = 100f;
		public static float ForwardShot = 60f;
		public static float BackwardShot = 45f;
		public static float Rest = 0;
		
	}
	
	public static final class ShooterPivotPID {
		private ShooterPivotPID() {}
		
		public static float ShooterPivot_Deadband = 0.0f; 
		public static float ShooterPivot_P = -0.0285f; // -0.0285 is good
		public static float ShooterPivot_I = 0.01f;  //0.10 is working
		public static float ShooterPivot_D = 0.0025f; // 0.0025 is working
		public static float ShooterPivot_F = 0.0f;
		public static float ShooterPivot_OffsetAngle= 54.0f;
		public static float ShooterPivot_TorqueConstant = 0.012255f; // 0.02255
	}

	public static final class ElevatorSensors {
		private ElevatorSensors() {}
			public static final float LeftPot_MinVolts = 0.0f;
			public static final float LeftPot_MaxVolts = 0.0f;
			public static final float LeftPot_MinAngle = 0.0f;
			public static final float LeftPot_MaxAngle = 0.90f;
			public static final float RightPot_MinVolts = 0.0f;
			public static final float RightPot_MaxVolts = 0.0f;
			public static final float RightPot_MinAngle = 0.0f;
			public static final float RightPot_MaxAngle = 0.90f;
	}
	
	
	public static final class ShooterPivotPot {
		private ShooterPivotPot() {}
		
		public static float PivotPot_MinVolts = 0.262f; //0.262
		public static float PivotPot_MaxVolts = 0.303f; // 0.304	
		public static float PivotPot_MinAngle = 26f;
		public static float PivotPot_MaxAngle = 100f;
	}
	
	public static final class FlywheelGains {
		private FlywheelGains() {}
		
		public static double FlyWheel_IntakeSpeed = 0.5;
		public static double FlyWheel_ShotRPM = -1.0;
		
		public static final class RightWheelPID {
			private RightWheelPID() {}
			
			public static double RightWheel_P = 1.0;
			public static double RightWheel_I = 0.001;
			public static double RightWheel_D = 0.00001;
		}
		
		public static final class LeftWheelPID {
			private LeftWheelPID() {}
			
			public static double LeftWheel_P = 1.0;
			public static double LeftWheel_I = 0.001;
			public static double LeftWheel_D = 0.00001;
		}
	}

	public static final class DrivetrainGains {
		private DrivetrainGains() {}
		
		public static double TicksPerInch = 88.3888888888888;
		public static double TicksPerFoot = 6.99074074074;
		
		public static double AngleGains = 0.044444;
		
		public static final class PIDGainsLeft {
			private PIDGainsLeft() {}
				
			public static double PIDGainsLeft_P = 0.00113175;
			public static double PIDGainsLeft_I = 0.0;
			public static double PIDGainsLeft_D = 0.0;
			
		}
		
		public static final class PIDGainsRight {
			private PIDGainsRight() {}
			
			public static double PIDGainsRight_P = 0.00113175;
			public static double PIDGainsRight_I = 0.0;
			public static double PIDGainsRight_D = 0.0;
		}
	}
	
}
