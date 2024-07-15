public class Body {
    public double xxPos; // the x position
    public double yyPos; // the y position
    public double xxVel; //velocity in the x direction
    public double yyVel; //velocity in the y direction
    public double mass; //its mass
    public String imgFileName; //the name of the file that corresponds to the image that depicts the body

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        //initialize an instance of the Body class
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        //initialize an instance of the Body class
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        //calculate the distance between two bodies
        double dx = b.xxPos - xxPos;
        double dy = b.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Body b) {
        //calculate the force exerted by another body
        double G = 6.67e-11;
        double r = calcDistance(b);
        return G * mass * b.mass / (r * r);
    }

    public double calcForceExertedByX(Body b) {
        //calculate the force exerted by another body in the x direction
        double F = calcForceExertedBy(b);
        double dx = b.xxPos - xxPos;
        double r = calcDistance(b);
        return F * dx / r;
    }

    public double calcForceExertedByY(Body b) {
        //calculate the force exerted by another body in the y direction
        double F = calcForceExertedBy(b);
        double dy = b.yyPos - yyPos;
        double r = calcDistance(b);
        return F * dy / r;
    }

    public double calcNetForceExertedByX(Body[] allBodys) {
        //calculate the net force exerted by all bodies in the x direction
        double netForceX = 0;
        for (Body b : allBodys) {
            if (!this.equals(b)) 
                netForceX += calcForceExertedByX(b);
        }

        return netForceX;
    }

    public double calcNetForceExertedByY(Body[] allBodys) {
        //calculate the net force exerted by all bodies in the y direction
        double netForceY = 0;
        for (Body b : allBodys) {
            if (!this.equals(b))
                netForceY += calcForceExertedByY(b);
        }

        return netForceY;
    }

    public void update(double dt, double fx, double fy) {
        //update the position and velocity of the body
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt; 
    }
}