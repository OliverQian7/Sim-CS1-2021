package sim;

import java.awt.Color;
import java.awt.Graphics;

public class World {
    private final int LENGTH = 1400;
    private final int HEIGHT = 850;
    private final Color bg = Color.GRAY;
    //declare array of Blobs
    private Blob[] blobs = new Blob[2500];
    
    public World() {
        for (int i = 0; i < blobs.length; i++) {
            blobs[i] = new Blob();
        }
        calcAreas(blobs);
    }
    
    
     public double findArea(Blob b) {
        return (Math.pow((b.getDIAMETER()/2),2)*Math.PI);
    }
    
    public void draw(Graphics g) {
        System.out.println(calcAreas(blobs));
        //For each loop, enhanced for loop
        for (Blob blob : blobs) {
            if (blob.getHp() <= 0) continue;
            blob.move();
            blob.collideWorldBounds(this);
            for (Blob b2 : blobs) {
                if (blob == b2) continue;
                if (b2.getHp()<=0) continue;
                blob.collide(b2);
            }
           if (blob.getHp()>0) blob.draw(g);
           
        }
    }
    
    private int[] calcAreas(Blob[] _blobs) {
        int [] areas = new int [_blobs.length];
        for (int i = 0; i < _blobs.length; i++) {
            Blob b = _blobs[i];
             areas[i] = (int)findArea(b);   
        }
        return areas;
    }

    public int getLENGTH() {
        return LENGTH;
    }
    public int getHEIGHT() {
        return HEIGHT;
    }
    
    
}