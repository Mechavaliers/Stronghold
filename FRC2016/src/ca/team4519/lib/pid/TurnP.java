package ca.team4519.lib.pid;

public class TurnP extends PID {

	public TurnP(PIDSrc source, float deadband, float kP, float kI, float kD) {
		super(source, deadband, kP, kI, kD);
		// TODO Auto-generated constructor stub
	   }
	   
	   /**
	    * Reset the state of this PID loop
	    */
	   public void clear() {
	       integralError = 0.0f;
	       prevError = 0.0f;
	       deltaError = 0.0f;
	   }
	   
	   /**
	    * Iterate the PID loop
	    * @param target the desired target value. Units depend on the context of this PID
	    * @return the output value to set to eg a SpeedController to reach the specified target
	    */
	   public float pid(final float target) {
	       float kErr;
	       float pOut;
	       float iOut;
	       float dOut;
	       float output;

	       kErr = (float)(target - source.getValue());

	       deltaError = prevError - kErr;
	       prevError = kErr;
	       integralError += kErr;

	       pOut = kErr * kP;
	       iOut = integralError * kI;
	       dOut = deltaError * kD;

	       if (iOut > 1.0f) {
	           iOut = 1.0f;
	       }
	       
	       if (Math.abs(kErr) < deadband) {
	           atTarget = true;
	           return 0.0f;
	       } else {
	           atTarget = false;
	       }

	       output = (pOut + iOut + dOut);

	       if (output > 1.0f) {
	           return 1.0f;
	       }
	       if (output < -1.0f) {
	           return -1.0f;
	       }
	       return output;
	   }
	   
	   /**
	    * @return the PIDSrc (PID source sensor) used by this PID loop
	    */
	   public PIDSrc getSrc() {
	       return this.source;
	   }
	   
	   /**
	    * @return whether this PID loop has reached the specified target value
	    */
	   public boolean atTarget() {
	       return atTarget;
	   }
	   
}