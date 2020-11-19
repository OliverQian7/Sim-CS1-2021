package sim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blob {
    //Fields
    private int x, y, vx, vy, hp, size;
    private final static int DIAMETER = 10, MAX_SPEED = 10;
    private final static Color COLOR = new Color((int)(Math.random())*255,(int)(Math.random()*255),(int)(Math.random()*255));
    
    //Constructor
    //overloaded
    public Blob(int x, int y) {
        this.x = x;
        this.y = y;
        this.vx = (int) (Math.random()*2*MAX_SPEED) - MAX_SPEED;
        this.vy = (int) (Math.random()*2*MAX_SPEED) - MAX_SPEED;
    }
    
    //no-args constructor
    public Blob() {
        this.hp = 10;
        this.size = (int) (Math.random()*DIAMETER) +2;
        this.x = (int) (Math.random()*600);
        this.y = (int) (Math.random()*600);        
        this.vx = (int) (Math.random()*2*MAX_SPEED) - MAX_SPEED;
        this.vy = (int) (Math.random()*2*MAX_SPEED) - MAX_SPEED;
    }
    
    //Methods
    public void move() {
        x += vx;
        y += vy;
    }
    public double getArea() {
        return Math.PI*Math.pow(size/2, 2);
    }
    
    public void draw(Graphics g) {
        Color clr = new Color((int)255/(hp+1),(hp+1)*20,0);
       // g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
       g.setColor(clr); 
       g.fillOval(x, y, size, size);
    }
    
    public void collideWorldBounds(World w) {
        if (x > w.getLENGTH() || x < 0) {
            vx *= -1;
        }
        if (y > w.getHEIGHT() || y < 0) {
            vy *= -1;
        }
    }
    
    public void collide(Blob other) {
        if (circleVsCircle(this,other)) {
            if (this.hp > other.hp) {
                
                this.size++;
                other.hp--;
                other.size--;
            }
            else if (this.hp < other.hp) {
                this.hp--;
                this.size--;
                
                other.size++;
            }
            else {
                this.hp--;
                other.hp--;
                this.size--;
                other.size--;
            }
            int tempvx = this.vx;
            int tempvy = this.vy;
            this.vx = other.vx;
            this.vy = other.vy;
            other.vx = tempvx;
            other.vy = tempvy;
        }
    }
    
    private boolean circleVsCircle(Blob b1, Blob b2) {
        if (dist(b1,b2) < (int)(b1.size/2)+(int)(b2.size/2))
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    private double dist(Blob b1, Blob b2) {
        return Math.sqrt(Math.pow(b2.x-b1.x,2)+Math.pow(b2.y-b1.y,2));
    }
   
    //Getters & setters

    public int getHp() {
        return hp;
    }

    public static int getDIAMETER() {
        return DIAMETER;
    }
    
}