
public class Body {
	
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	
	/**
	 * create a body from the given parameters:
	 * x position, y position, x velocity, y velocity, mass, filename 
	 */
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
		}
	
	/**
	 * allows the use of the private variable myXPos
	 */
	public double getX() {
		return myXPos;
	}
	
	/**
	 * allows the use of the private variable myYPos
	 */
	public double getY() {
		return myYPos;
	}
	
	/**
	 * allows the use of the private variable myXVel
	 */
	public double getXVel() {
		return myXVel;
	}
	
	/**
	 * allows the use of the private variable myYVel
	 */
	public double getYVel() {
		return myYVel;
	}
	
	/**
	 * allows the use of the private variable myMass
	 */
	public double getMass() {
		return myMass;
	}
	
	/**
	 * allows the use of the private variable myFileName
	 */
	public String getName() {
		return myFileName;
	}
	
	/**
	 * constructor that copies instance variables from one body 
	 * to this body
	 * the parameter is used to initialize the body
	 */
	public Body(Body b) {
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}
	
	/**
	 * returns the distance between this body and another one
	 */
	public double calcDistance(Body p) {
		double Xdist = myXPos - p.getX();
		double Ydist = myYPos - p.getY();
		double radiusSquared = (Xdist * Xdist) + (Ydist * Ydist);
		return Math.sqrt(radiusSquared);
	}
	
	/**
	 * returns the force exerted on this body 
	 * by the body specified in the parameter
	 */
	public double calcForceExertedBy(Body p) {
		double G = 6.67 * 1e-11;
		double m1 = myMass;
		double m2 = p.getMass();
		double distance = Math.pow(calcDistance(p), 2);
		double force = (G * m1 * m2) / (distance);
		return force;
	}
	
	/**
	 * used to find the force exerted in the x-direction specifically
	 */
	public double calcForceExertedByX(Body p) {
		double initialforce = calcForceExertedBy(p);
		double Xdist =  p.getX() - myXPos;
		double radius = calcDistance(p);
		double forceX = (initialforce) * (Xdist / radius);
		return forceX;
	}
	
	/**
	 * used to find the force exerted in the y-direction specifically
	 */
	public double calcForceExertedByY(Body p) {
		double initialforce = calcForceExertedBy(p);
		double Ydist =  p.getY() - myYPos;
		double radius = calcDistance(p);
		double forceY = (initialforce) * (Ydist / radius);
		return forceY;
	}
	
	
	/**
	 * sums the total number of forces in the x-direction
	 * that is being exerted on this body
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double counter = 0.00;
		for (Body b: bodies) {
			if (!b.equals(this)) {
				counter += calcForceExertedByX(b);	
			}			
		}
		return counter;
	}

	
	/**
	 * sums the total number of forces in the y-direction
	 * that is being exerted on this body
	 */
	public double calcNetForceExertedByY(Body[] bodies) {
		double counter = 0.00;
		for (Body b: bodies) {
			if (!b.equals(this)) {
				counter += calcForceExertedByY(b);	
			}			
		}
		return counter;	
	}

	/**
	 * updates the instance variables
	 * so that the position and velocity can change
	 * which will help us show the changes in motion
	 */
	public void update(double deltaT, double xforce, double yforce) {
		double ax = (xforce / myMass);
		double ay = (yforce / myMass);
		double nvx = myXVel + (deltaT * ax);
		double nvy = myYVel + (deltaT * ay);
		double nx = myXPos + (deltaT * nvx);
		double ny = myYPos + (deltaT * nvy);
		this.myXPos = nx;
		this.myYPos = ny;
		this.myXVel = nvx;
		this.myYVel = nvy;
	}
	
	/**
	 * draws the bodies using the parameters
	 * of a body's x and y position with the given image file
	 */
	public void draw () {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);	
	}
	
	
}