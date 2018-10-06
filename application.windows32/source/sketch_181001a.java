import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_181001a extends PApplet {

int a;
int b;
int i = 0;

float bigCircleR = 220;
float lilCircleR = 15;
float l = 0.9f;
float r = 0;

//Runs at startup
public void setup() {
  //Create a 1280x720 window using P2D
  
  //Set the ColorMode to HSB and make the the background black
  colorMode(HSB, 100);
  background(0, 0, 0);
  //Randomize initial values
  randomizer();
}

//Key Handling
public void keyPressed() {
  //Up Arrow   = Save Spiro
  if (keyCode == UP) {
    save("spirograph-" + l + "-" + bigCircleR + "-" + lilCircleR + ".png");
  //Down Arrow = Generate new random values
  } else if (keyCode == DOWN) {
    randomizer();
  }
}

//Randomize values to create a new Spiro
public void randomizer() {
  //Reset the background
  background(0, 0, 0);
  //Randomize large Circles radius between 50 and half the minimum value between the width and height
  bigCircleR = random(50, min(width, height)/2);
  //Randomize small circle's radius between 10 pixels and nine times the large circle's radius divided by ten
  lilCircleR = random(10, 9*bigCircleR/10);
  l = random(.1f, .9f);
  //Reset the hue to 0 and reset i to 0
  r = 0;
  i = 0;
}

public void dline() {
  float k= lilCircleR/bigCircleR;
  float x = width/2 + (bigCircleR * ((1 - k) * cos(radians(i)) + l * k * cos((1-k) * radians(i)/k)));
  float y = height/2 + (bigCircleR * ((1 - k) * sin(radians(i)) - l * k * sin((1-k) * radians(i)/k)));
  float xt = width/2 + (bigCircleR * ((1 - k) * cos((radians(i + 1))) + l * k * cos((1-k) * (radians(i + 1))/k)));
  float yt = height/2 + (bigCircleR * ((1 - k) * sin((radians(i + 1))) - l * k * sin((1-k) * (radians(i + 1))/k)));
    
  line(x, y, xt, yt);
  strokeWeight(1);
  stroke(r, 100, 100);
  
  //If the hues current value + 1 is greater than or equall to 100 reset it to 0 so our colors continue to change
  if (r+1 >= 100) {
    r = 0;
  }
  //Otherwise if i is divisable by 150 with no remander add 1 to our hue value
  else if (i % 150 == 0) {
    r += 1;
  }
  //increament i
  i++;
}

//Runs every frame
public void draw() {
  for (int x = 0; x < 30; x++) {
    dline();
  }
}
  public void settings() {  size(1280, 720, P2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_181001a" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
