package ca.team4519.FRC2016.subsystems;

import ca.team4519.FRC2016.Constants;
import ca.team4519.FRC2016.Gains;
import ca.team4519.lib.Loopable;
import ca.team4519.lib.Subsystem;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Sendable;
import ca.team4519.lib.profiling.RobotPose;


public class Drivebase extends Subsystem implements Loopable {

	public VictorSP LeftDrive = new VictorSP(Constants.LeftDrive);
	public VictorSP RightDrive = new VictorSP(Constants.RightDrive);
	public double leftOut=0.0;
	public double rightOut=0.0;
	
	public RobotPose basePose = new RobotPose(0, 0, 0, 0, 0, 0);
	
	public Encoder leftDriveEncoder = new Encoder(Constants.LeftDriveEncoderA, Constants.LeftDriveEncoderB, false);
	public Encoder rightDriveEncoder = new Encoder(Constants.RightDriveEncoderB, Constants.RightDriveEncoderA, false);
	
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	//public ADXL362 Accel = new ADXL362(null);
		
	public Drivebase() {
		super("Drivebase");
	}
	

	public void drivebaseInit() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
		gyro.reset();
		System.out.println("Drivebase Initialized");
		
	}
	
	public void updatePose(){
		basePose.reset(
				(leftDriveEncoder.get() * ((Math.PI * 8)/ 2048)/ 12), 
				(rightDriveEncoder.get() * ((Math.PI * 8)/ 2048)/ 12), 
				(leftDriveEncoder.getRate() * ((Math.PI * 8)/ 2048)/ 12), 
				(rightDriveEncoder.getRate() * ((Math.PI * 8)/ 2048)/ 12), 
				gyro.getAngle(), 
				gyro.getRate());
		
		SmartDashboard.putNumber("Pose leftDistance", basePose.getLeftDistance());
		SmartDashboard.putNumber("Pose leftVelocity", basePose.getLeftVelocity());
		SmartDashboard.putNumber("Pose right distance", basePose.getRightDistance());
		SmartDashboard.putNumber("Pose right veocity", basePose.getRightVelocity());
		SmartDashboard.putNumber("Pose angle", basePose.getHeading());
		SmartDashboard.putNumber("Pose angle Rate", basePose.getHeadingVelocity());
	}
	
	public void pidTurn(boolean wantTurn, double target, double kP, double kI, double kD){
			double kErr;
	       double pOut;
	       double iOut;
	       double dOut;
	       double output;
	       double deltaError;
	       double prevError;
	       double integralError = 0;

	       if(wantTurn){
	       kErr = (float)(target - gyro.getAngle());
	       
	       prevError = kErr;
	       deltaError = prevError - kErr;
	       
	       integralError += kErr;

	       pOut = kErr * kP;
	       iOut = integralError * kI;
	       dOut = deltaError * kD;
	       
	       output = (pOut + iOut + dOut);
	       
	       if (output > 1.0) {
	           output = 1.0;
	       }
	       if (output < -1.0) {
	           output = -1.0;
	       }
	       
	       LeftDrive.set(output);
	       RightDrive.set(output);
	       }
	}
	
	public void arcadeDriveMath(double forwardAxis, double turningAxis) {
		 		
		LeftDrive.set(-(forwardAxis - turningAxis));
		RightDrive.set(forwardAxis + turningAxis);		
	}
	
	public void resetSensors(){
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
		gyro.reset();
		System.out.println("Drivebase Sensors have been cleared");
	}
	
	public void update() {
		SmartDashboard.putNumber("Left Drive Encoder", leftDriveEncoder.get());
		SmartDashboard.putNumber("Right Drive Encoder", rightDriveEncoder.get());
		SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
		SmartDashboard.putNumber("left Distgance in Inches", leftDriveEncoder.get() / Gains.DrivetrainGains.TicksPerInch);
		SmartDashboard.putNumber("Right Distance in Inches", rightDriveEncoder.get() / Gains.DrivetrainGains.TicksPerInch);
		SmartDashboard.putNumber("Right Velocity", (rightDriveEncoder.getRate() * ((Math.PI * 8)/ 2048))/ 12);
		SmartDashboard.putNumber("Left Velocity", (leftDriveEncoder.getRate() * ((Math.PI * 8)/ 2048))/12);
		SmartDashboard.putNumber("leftOut", LeftDrive.get() / Gains.DrivetrainGains.TicksPerFoot);
		SmartDashboard.putNumber("Right Out", RightDrive.get() / Gains.DrivetrainGains.TicksPerFoot);

	}

}
