package ca.team4519.FRC2016.subsystems;

import ca.team4519.FRC2016.Constants;
import ca.team4519.FRC2016.Gains;
import ca.team4519.lib.Loopable;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.pid.PIDUlum;
import ca.team4519.lib.pid.PotentiometerPidSrc;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShoulderPivot extends Subsystem implements Loopable {

	public boolean fire = false;
	public boolean angleToggle = true;
		
	public double shotAngle = Gains.ShooterPositions.BackwardShot;
	
	public Victor pivot = new Victor(Constants.FlywheelPivot);
	
	public AnalogPotentiometer positionPot = new AnalogPotentiometer(Constants.ShooterPivotPot);

	public PotentiometerPidSrc rotatPid = new PotentiometerPidSrc(positionPot, 
			Gains.ShooterPivotPot.PivotPot_MinVolts, 
			Gains.ShooterPivotPot.PivotPot_MaxVolts, 
			Gains.ShooterPivotPot.PivotPot_MinAngle, 
			Gains.ShooterPivotPot.PivotPot_MaxAngle);
	
	public PIDUlum illuminatedAngles = new PIDUlum(rotatPid, 
			Gains.ShooterPivotPID.ShooterPivot_Deadband, 
			Gains.ShooterPivotPID.ShooterPivot_P, 
			Gains.ShooterPivotPID.ShooterPivot_I, 
			Gains.ShooterPivotPID.ShooterPivot_D, 
			Gains.ShooterPivotPID.ShooterPivot_OffsetAngle, 
			Gains.ShooterPivotPID.ShooterPivot_TorqueConstant,
			true);

	public float setPos(boolean ramp, boolean forward, boolean intake, boolean straight, boolean gamepadIntake, boolean gamepadSafe){
		
		float angle = (float) shotAngle;
		
		if ((ramp || gamepadSafe) && angleToggle){
			angle = Gains.ShooterPositions.BackwardShot;
			illuminatedAngles.clear();
			angleToggle = false;
		}
		else if (forward  && angleToggle){
			angle = Gains.ShooterPositions.ForwardShot;
			illuminatedAngles.clear();
			angleToggle = false;
		}
		else if ((straight || gamepadIntake)&& angleToggle){
			angle = Gains.ShooterPositions.Straight;
			illuminatedAngles.clear();
			angleToggle = false;
		}
		else if (intake && angleToggle){
			angle = Gains.ShooterPositions.Intake;
			illuminatedAngles.clear();
			angleToggle = false;
		}else if (!ramp && !forward && !straight && !intake){
			angleToggle = true;
		}
		shotAngle = angle;
		return angle; 
	}
	
	
	public void changePos(float angle){

		illuminatedAngles.clear();
		pivot.set(-illuminatedAngles.pid(angle));

	}
	
	public ShoulderPivot() {
		super("Shoulder Pivot");
	}

	public void update(){		
		SmartDashboard.putNumber("Potentiometer angle", rotatPid.getValue());
		SmartDashboard.putNumber("Pot voltage", positionPot.get());	
	}
	
}
