
public class Body {
	
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
		
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
		
		}
		
	public double getX() {
		return myXPos;
	}

	public double getY() {
		return myYPos;
	}
	
	public double getXVel() {
		return myXVel;
	}
	
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	
	public String getName() {
		return myFileName;
	}
	public Body(Body b) {
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}
	
	public double calcDistance(Body p) {
		double Xdist = myXPos - p.getX();
		double Ydist = myYPos - p.getY();
		double radiusSquared = (Xdist * Xdist) + (Ydist * Ydist);
		return Math.sqrt(radiusSquared);
	}
	
	public double calcForceExertedBy(Body p) {
		double G = 6.67 * 1e-11;
		double m1 = myMass;
		double m2 = p.getMass();
		double distance = Math.pow(calcDistance(p), 2);
		double force = (G * m1 * m2) / (distance);
		return force;
	}
	
	public double calcForceExertedByX(Body p) {
		double initialforce = calcForceExertedBy(p);
		double Xdist =  p.getX() - myXPos;
		double radius = calcDistance(p);
		double forceX = (initialforce) * (Xdist / radius);
		return forceX;
	}
	
	public double calcForceExertedByY(Body p) {
		double initialforce = calcForceExertedBy(p);
		double Ydist =  p.getY() - myYPos;
		double radius = calcDistance(p);
		double forceY = (initialforce) * (Ydist / radius);
		return forceY;
	}
	
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
	
}