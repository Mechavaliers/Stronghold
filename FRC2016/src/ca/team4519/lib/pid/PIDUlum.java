package ca.team4519.lib.pid;

/**
 * A PID controller for inverted pendulum systems (PID pendulum... get it?)
 */
public class PIDUlum extends PID {
    
    private final float offsetAngle;
    private final float torqueConstant;
    private float rampingConstant = 0.05f;
    float output = 0;
    private boolean inverted;
 
    
  
    /**
     * @param source the PIDSrc source sensor\[]
     * @param deadband filter value - do not act when current error is within this bound
     * @param kP P constant
     * @param kI I constant
     * @param kD D constant
     * @param offsetAngle the balance point of the inverted pendulum
     * @param torqueConstant "feed forward" term constant to allow the pendulum to hold position against gravity
     */
    public PIDUlum(final AnglePidSrc source, final float deadband,
            final float kP, final float kI, final float kD,
            final float offsetAngle, final float torqueConstant, final boolean inverse) {
        super(source, deadband, kP, kI, kD);
        this.offsetAngle = offsetAngle;
        this.inverted = inverse;
        this.torqueConstant = torqueConstant;
    }
    public void kIset(float in){
    	kI=in;
    }
    public void kDset(float in){
    	kD=in;
    }
    
    /**
     * Iterate the PID loop
     * @param target the desired target value. Units depend on the context of this PID
     * @param output 
     * @return the output value to set to eg a SpeedController to reach the specified target
     */
    public float pid(final float target) {
        float kErr;
        float pOut;
        float iOut;
        float dOut;
        float feedForward;
        float result;

        kErr = (float)(target - source.getValue());

        deltaError = prevError - kErr;
        prevError = kErr;
        integralError += kErr;

        pOut = kErr * kP;
        iOut = integralError * kI;
        dOut = deltaError * kD;

        if (iOut > 1) {
            iOut = 1;
        }
        
        feedForward = torqueConstant * (float)(source.getValue() - offsetAngle);
        
        if (Math.abs(kErr) < deadband) {
            atTarget = true;
           // return feedForward;
        } else {
            atTarget = false;
        }

        if(inverted){
        	  result = -(pOut + iOut + dOut  + feedForward);
              
              if(result > output && result > 0) {
          		output += rampingConstant;
             	}else if(result < output && result < 0) {
          		output -= rampingConstant;
          	}else{
          		output= result;
          	}
        }else{
        result = (pOut + iOut + dOut  + feedForward);
        
        if(result > output && result > 0) {
        	
    		output += rampingConstant;
       	}else if(result < output && result < 0) {
    		output -= rampingConstant;
    	}else{
    		output= result;
    	}
        }
        

        

        

    	if(output > 0.50f){
			return output = 0.50f;
		}
    	if (output < -0.50f){
			return output = -0.50f;
		}
		return output;
        
    }
        
       
}
