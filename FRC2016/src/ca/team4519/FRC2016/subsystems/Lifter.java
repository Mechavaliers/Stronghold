package ca.team4519.FRC2016.subsystems;

import ca.team4519.FRC2016.Constants;
import ca.team4519.lib.Loopable;
import ca.team4519.lib.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class Lifter extends Subsystem implements Loopable {

	public Talon RightWinch = new Talon(Constants.FrontRight);
	public Talon LeftWinch = new Talon(Constants.FrontLeft);
	
	public Solenoid InitialStage = new Solenoid(1);
	public Solenoid ReleaseTop = new Solenoid(0);
	
	public boolean canLift = false;
	public boolean canRelease = false;
	
	public void WinchMovement(double Lefttrig, double rightTrig){
		RightWinch.set(Lefttrig);
		LeftWinch.set(rightTrig);
	}
	public void StartClimb(boolean raise){
		if (!raise){
			canLift = true;
		}else if (canLift){
			InitialStage.set(!InitialStage.get());
			canLift = false;
		}
	}
	
	public void TopLatch(boolean release){
		if(!release){
			canRelease = true;
		}else if (canRelease){
			ReleaseTop.set(!ReleaseTop.get());
			canRelease = false;
		}
	}
	
	public Lifter() {
		super("Lifter");
		// TODO Auto-generated constructor stub
	}


	public void update() {
		// TODO Auto-generated method stub
		
	}

}
