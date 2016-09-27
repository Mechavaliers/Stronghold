package ca.team4519.lib.profiling;

public class RobotPose {

	    public RobotPose(double leftDistance, double rightDistance, double leftVelocity,
	                double rightVelocity, double heading, double headingVelocity) {
	        this.leftDistance = leftDistance;
	        this.rightDistance = rightDistance;
	        this.leftVelocity = leftVelocity;
	        this.rightVelocity = rightVelocity;
	        this.heading = heading;
	        this.headingVelocity = headingVelocity;
	    }

	    public double leftDistance;
	    public double rightDistance;
	    public double leftVelocity;
	    public double rightVelocity;
	    public double heading;
	    public double headingVelocity;

	    public void reset(double leftDistance, double rightDistance, double leftVelocity,
	                      double rightVelocity, double heading, double headingVelocity) {
	        this.leftDistance = leftDistance;
	        this.rightDistance = rightDistance;
	        this.leftVelocity = leftVelocity;
	        this.rightVelocity = rightVelocity;
	        this.heading = heading;
	        this.headingVelocity = headingVelocity;
	    }

	    public double getLeftDistance() {
	        return leftDistance;
	    }

	    public double getHeading() {
	        return heading;
	    }

	    public double getRightDistance() {
	        return rightDistance;
	    }

	    public double getLeftVelocity() {
	        return leftVelocity;
	    }

	    public double getRightVelocity() {
	        return rightVelocity;
	    }

	    public double getHeadingVelocity() {
	        return headingVelocity;
	    }

	    public class RelativePoseGenerator {
	        private RobotPose m_base_pose;

	        public RelativePoseGenerator() {
	            m_base_pose = RobotPose.this;
	        }

	        public RobotPose get(RobotPose pose) {
	            return new RobotPose(
	                    pose.getLeftDistance() - m_base_pose.getLeftDistance(),
	                    pose.getRightDistance() - m_base_pose.getRightDistance(),
	                    m_base_pose.getLeftVelocity() - pose.getLeftVelocity(),
	                    m_base_pose.getRightVelocity() - pose.getRightVelocity(),
	                    pose.getHeading() - m_base_pose.getHeading(),
	                    m_base_pose.getHeadingVelocity()
	                            - pose.getHeadingVelocity());
	        }
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (!(obj instanceof RobotPose))
	            return false;
	        if (obj == this)
	            return true;
	        RobotPose other_pose = (RobotPose) obj;
	        return other_pose.getLeftDistance() == getLeftDistance()
	                && other_pose.getRightDistance() == getRightDistance()
	                && other_pose.getLeftVelocity() == getLeftVelocity()
	                && other_pose.getRightVelocity() == getRightVelocity()
	                && other_pose.getHeading() == getHeading()
	                && other_pose.getHeadingVelocity() == getHeadingVelocity();
	    }
}
