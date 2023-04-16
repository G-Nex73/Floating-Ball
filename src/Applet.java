import processing.core.PApplet;

import java.util.Random;

public class Applet extends PApplet {
    public static void main(String[] args) {
        Applet applet=new Applet();
        PApplet.runSketch(new String[]{""},applet);
    }
    private static final int sizeX=800;
    private static final int sizeY=600;
    private static final int rad=20;
    private final int background=color(128, 186, 36);
    private final int color1=color(74, 92, 102);
    private final int color2=color(184, 0, 64);
    Ball ball1=new Ball();

    @Override
    public void settings() {
        size(sizeX,sizeY);
    }

    @Override
    public void setup() {
        background(background);
        noStroke();
        //smooth();
    }

    @Override
    public void draw() {
        fill(background);
        rect(0,0,sizeX,sizeY);
        ball1.draw();
    }

    class Ball{
        private static final Random random=new Random();
        private float x=random(rad,sizeX-rad);
        private float y=random(rad,sizeY-rad);
        private int color=color1;
        private final int m=(int)random(0,10);
        private boolean positiveX=random.nextBoolean();
        private boolean positiveY=random.nextBoolean();
        private final float speed=random(0.1f,1);

        public Ball() {
            Thread collision=new Thread(new Runnable() {
                private void changeColor(){
                    if(get((int) x, (int) y)==color1) color=color2;
                    else color=color1;
                }
                @Override
                public void run() {
                    if(x<=rad||x>=sizeX-rad) {
                        positiveX=!positiveX;
                        changeColor();
                    }
                    if(y<=rad||y>=sizeY-rad){
                        positiveY=!positiveY;
                        changeColor();
                    }
                    /*try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            },"collision");
            collision.start();
        }

        private void move(){
            x=positiveX?(x+1)*speed:(x-1)*speed;
            y=positiveY?(y+m)*speed:(y-m)*speed;
        }
        private void draw(){
            fill(color);
            ellipse(x,y,rad,rad);
            move();
        }

    }
}
