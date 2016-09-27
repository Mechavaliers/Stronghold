package ca.team4519.FRC2016.subsystems;

import ca.team4519.FRC2016.Constants;
import ca.team4519.FRC2016.Gains;
import ca.team4519.lib.Loopable;
import ca.team4519.lib.Subsystem;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;

public class Drivebase extends Subsystem implements Loopable {

	public VictorSP LeftDrive = new VictorSP(Constants.LeftDrive);
	public VictorSP RightDrive = new VictorSP(Constants.RightDrive);
	public double leftOut=0.0;
	public double rightOut=0.0;
	
	public Encoder leftDriveEncoder = new Encoder(Constants.LeftDriveEncoderA, Constants.LeftDriveEncoderB, false);
	public Encoder rightDriveEncoder = new Encoder(Constants.RightDriveEncoderB, Constants.RightDriveEncoderA, false);
	
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
		
	public Drivebase() {
		super("Drivebase");
	}

	public void drivebaseInit() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
		gyro.reset();
		System.out.println("Drivebase Initialized");
		
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
		SmartDashboard.putNumber("left Out", LeftDrive.get() / Gains.DrivetrainGains.TicksPerFoot);
		SmartDashboard.putNumber("Right Out", RightDrive.get() / Gains.DrivetrainGains.TicksPerFoot);

	}
}
