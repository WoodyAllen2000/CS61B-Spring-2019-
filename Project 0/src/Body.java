public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFilename;
    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFilename = img;
    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFilename = b.imgFilename;
    }
    public double calcDistance(Body e){
        return Math.sqrt((xxPos - e.xxPos) * (xxPos - e.xxPos) + (yyPos - e.yyPos) * (yyPos - e.yyPos));
    }
    public double calcForceExertedBy(Body e){
        double distance = this.calcDistance(e);
        double G = 6.67 * Math.pow(10, -11);
        return (G * mass * e.mass) / (distance * distance);
    }
    public double calcForceExertedByX(Body e){
        double dx = e.xxPos - xxPos;
        double distance = this.calcDistance(e);
        double theForce = this.calcForceExertedBy(e);
        return theForce * dx / distance;
    }
    public double calcForceExertedByY(Body e){
        double dy = e.yyPos - yyPos;
        double distance = this.calcDistance(e);
        double theForce = this.calcForceExertedBy(e);
        return theForce * dy / distance;
    }
    public double calcNetForceExertedByX(Body[] allBodies){
        double theNetForce = 0;
        for (Body allBody : allBodies) {
            if (this.equals(allBody)) {
                continue;
            } else {
                theNetForce += this.calcForceExertedByX(allBody);
            }
        }
        return theNetForce;
    }
    public double calcNetForceExertedByY(Body[] allBodies){
        double theNetForce = 0;
        for (Body allBody : allBodies) {
            if (this.equals(allBody)) {
                continue;
            } else {
                theNetForce += this.calcForceExertedByY(allBody);
            }
        }
        return theNetForce;
    }
    public void update(double time, double theNetForceX, double theNetForceY){
        double accelerationX = theNetForceX / mass;
        double accelerationY = theNetForceY / mass;
        xxVel += accelerationX * time;
        yyVel += accelerationY * time;
        xxPos += xxVel * time;
        yyPos += yyVel * time;
    }
    public void draw(){
        String planetLocation = "images/" + imgFilename;
        StdDraw.picture(xxPos, yyPos, planetLocation);
    }
}
