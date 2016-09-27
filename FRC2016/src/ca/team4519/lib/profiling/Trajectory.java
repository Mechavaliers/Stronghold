package ca.team4519.lib.profiling;

public class Trajectory {

	
	 public static class Waypoint {

	        public double pos, vel, acc, jerk, heading, dt, x, y;

	        public Waypoint() {
	        }

	        public Waypoint(double pos, double vel, double acc, double jerk,
	                       double heading, double dt, double x, double y) {
	            this.pos = pos;
	            this.vel = vel;
	            this.acc = acc;
	            this.jerk = jerk;
	            this.heading = heading;
	            this.dt = dt;
	            this.x = x;
	            this.y = y;
	        }

	        public Waypoint(Waypoint to_copy) {
	            pos = to_copy.pos;
	            vel = to_copy.vel;
	            acc = to_copy.acc;
	            jerk = to_copy.jerk;
	            heading = to_copy.heading;
	            dt = to_copy.dt;
	            x = to_copy.x;
	            y = to_copy.y;
	        }

	        public String toString() {
	            return "pos: " + pos + "; vel: " + vel + "; acc: " + acc + "; jerk: "
	                    + jerk + "; heading: " + heading;
	        }
	    }
}
