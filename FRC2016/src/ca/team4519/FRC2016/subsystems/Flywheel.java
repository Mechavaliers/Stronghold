package ca.team4519.FRC2016.subsystems;

import ca.team4519.FRC2016.Constants;
import ca.team4519.FRC2016.Gains;
import ca.team4519.lib.Loopable;
import ca.team4519.lib.Subsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;


public class Flywheel extends Subsystem implements Loopable {

	public Solenoid bumper = new Solenoid(3);
	public CANTalon leftWheel = new CANTalon(Constants.LeftFly_CAN);
	public CANTalon rightWheel = new CANTalon(Constants.RightFly_CAN);
	public Talon LeftFlywheel = new Talon(Constants.LeftFly_PWM);
	public Talon RightFlywheel = new Talon(Constants.RightFly_PWM);
	
	
	public void shooterInit() {

		rightWheel.enableBrakeMode(false);
		rightWheel.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightWheel.setPID(
				Gains.FlywheelGains.RightWheelPID.RightWheel_P, 
				Gains.FlywheelGains.RightWheelPID.RightWheel_I,
				Gains.FlywheelGains.RightWheelPID.RightWheel_D);
		
		leftWheel.enableBrakeMode(false);
		leftWheel.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		leftWheel.reverseOutput(false);
		leftWheel.setPID(
				Gains.FlywheelGains.LeftWheelPID.LeftWheel_P,
				Gains.FlywheelGains.LeftWheelPID.LeftWheel_I,
				Gains.FlywheelGains.LeftWheelPID.LeftWheel_D);
		
		bumper.set(false);
		System.out.println("Shooter Initialized");
				
	}
	
	public void shooterControl(boolean wantShoot, boolean wantIntake, boolean fire) {
		bumper.set(fire);
		
		if(wantShoot){
			
			LeftFlywheel.set(Gains.FlywheelGains.FlyWheel_ShotRPM);
			RightFlywheel.set(Gains.FlywheelGains.FlyWheel_ShotRPM);
			
			rightWheel.enableControl();
			rightWheel.changeControlMode(TalonControlMode.PercentVbus);
			rightWheel.set(Gains.FlywheelGains.FlyWheel_ShotRPM);
		
			leftWheel.enableControl();
			leftWheel.changeControlMode(TalonControlMode.PercentVbus);
			leftWheel.set(Gains.FlywheelGains.FlyWheel_ShotRPM);
		}else if(wantIntake){
			
			LeftFlywheel.set(Gains.FlywheelGains.FlyWheel_IntakeSpeed);
			RightFlywheel.set(Gains.FlywheelGains.FlyWheel_IntakeSpeed);
			
			rightWheel.enableControl();
			rightWheel.changeControlMode(TalonControlMode.PercentVbus);
			rightWheel.set(Gains.FlywheelGains.FlyWheel_IntakeSpeed);
		
			leftWheel.enableControl();
			leftWheel.changeControlMode(TalonControlMode.PercentVbus);
			leftWheel.set(Gains.FlywheelGains.FlyWheel_IntakeSpeed);
		}else{
			
			LeftFlywheel.set(0.0);
			RightFlywheel.set(0.0);
			
			chillFam();
		}

	}
	
	public void chillFam(){
		leftWheel.disableControl();
		rightWheel.disableControl();
	}
	
	public Flywheel() {
		super("Flywheel");
	}

	public void update(){
		
	}
	
}
