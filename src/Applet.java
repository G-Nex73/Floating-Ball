import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

public class Applet extends PApplet {
    public static void main(String[] args) {
        Applet applet=new Applet();
        PApplet.runSketch(new String[]{""},applet);
    }
    private static final int sizeX=800;
    private static final int sizeY=600;
    private final int background=color(128, 186, 36);
    private final int color1=color(74, 92, 102);
    private final int color2=color(184, 0, 64);
    ArrayList<Ball> balls=new ArrayList<>();

    public Applet() {
        balls.add(new Ball());
    }

    @Override
    public void settings() {
        size(sizeX,sizeY);
    }

    @Override
    public void setup() {
        background(background);
        noStroke();
        smooth();
    }

    @Override
    public void draw() {
        fill(background);
        rect(0,0,sizeX,sizeY);
        for (var ball:balls) {
            ball.draw();
        }
    }

    @Override
    public void mouseClicked() {
        /*boolean flag=false;
        for (var ball:balls) {
            float x=ball.x;
            float y=ball.y;
            int r=ball.r;
            if(x-r>=mouseX&&x+r<=mouseX&&y-r>=mouseY&&y+r<=mouseY) {
                ball.rand();
                flag=true;
                break;
            }
        }
        if (!flag) {
            balls.add(new Ball());
        }*/
        balls.add(new Ball());
    }

    class Ball{
        //TODO sometimes balls don't change color?
        private static final Random random=new Random();
        private final int Rad;
        private final int r;
        private float x;
        private float y;
        private int color=color1;
        private float m;
        private boolean positiveX;
        private boolean positiveY;
        private float speed;
        /*Thread collision;

        public Ball() {
            this.Rad=random.nextInt(10,50);
            this.r=Rad/2;
            this.x =random.nextFloat(r,sizeX-r);
            this.y =random.nextFloat(r,sizeY-r);
            this.m =(int)(random.nextInt(0,11));
            this.positiveX =random.nextBoolean();
            this.positiveY =random.nextBoolean();
            this.speed=random.nextFloat(0.5f,2f);
            collision =new Thread(new Runnable() {
                private void changeColor(){
                    if(get((int) x, (int) y)==color1) color=color2;
                    else if(get((int) x, (int) y)==color2)color=color1;
                }
                private void snap(boolean isX, boolean isPositive){
                    if(isX) x=isPositive?sizeX-r:r;
                    else    y=isPositive?sizeY-r:r;
                }
                @Override
                public void run() {
                    while (true) {
                        if(x<=r) {
                            positiveX=!positiveX;
                            changeColor();
                            snap(true, false);
                        } else if(x>=sizeX-r) {
                            positiveX=!positiveX;
                            changeColor();
                            snap(true, true);
                        }else if(y<=r) {
                            positiveY=!positiveY;
                            changeColor();
                            snap(false, false);
                        }else if(y>=sizeY-r) {
                            positiveY = !positiveY;
                            changeColor();
                            snap(false, true);
                        }
                    }
                }
            },"collision");
            collision.start();
        }*/

        public Ball() {
            this.Rad=random.nextInt(16,50);
            this.r=Rad/2;
            this.x =random.nextFloat(r,sizeX-r);
            this.y =random.nextFloat(r,sizeY-r);
            this.m =(random.nextFloat(0,10));
            this.positiveX =random.nextBoolean();
            this.positiveY =random.nextBoolean();
            this.speed=random.nextFloat(0.5f,2f);
        }

        private void move(){
            x=positiveX? x+1*speed : x-1*speed;
            y=positiveY? y+m*speed : y-m*speed;
            if(x<=r) {
                positiveX=!positiveX;
                changeColor();
                snap(true, false);
            } else if(x>=sizeX-r) {
                positiveX=!positiveX;
                changeColor();
                snap(true, true);
            }else if(y<=r) {
                positiveY=!positiveY;
                changeColor();
                snap(false, false);
            }else if(y>=sizeY-r) {
                positiveY = !positiveY;
                changeColor();
                snap(false, true);
            }
        }

        private void draw(){
            fill(color);
            ellipse(x,y, Rad, Rad);
            move();
        }

        private void changeColor(){
            if(get((int) x, (int) y)==color1) color=color2;
            else if(get((int) x, (int) y)==color2) color=color1;
        }

        private void snap(boolean isX, boolean isPositive){
            if(isX) x=isPositive?sizeX-r:r;
            else    y=isPositive?sizeY-r:r;
        }

        /*public void rand() {
            this.m =(random.nextFloat(0,10));
            this.positiveX =random.nextBoolean();
            this.positiveY =random.nextBoolean();
            this.speed=random.nextFloat(0.5f,2f);
        }*/
    }
}
