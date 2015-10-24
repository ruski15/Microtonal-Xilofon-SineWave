import ddf.minim.*;
import ddf.minim.signals.*;

class Check {
  int x;
  int y;
  int width = 60;
  int height = 60;
  String name;
  boolean weAreMoving = false;
    
  
  Check(int x, int y, String name) {
    this.x = x;
    this.y = y;
    this.name = name;
  }
  void display() {
    fill(0,255,0);
    stroke(255, 0, 0);
    rect(check.x, check.y, check.width, check.height);
    
    fill(255, 0, 0);
    textAlign(CENTER);
    textSize(15);
    stroke(19);
    text("Check", 527, 635, height*0.35);
    
    fill(255);
    textAlign(CENTER);
    textSize(13);
    stroke(19);
    text("Each white piece has a different pitch. They must be placed inside one of the red colors\nattending to their pitches from the lowest to the highest.\nClick on them to hear their sounds and drag them to place them in their correct positions.", 500, 30, height*0.35);
  
    stroke(255);
    line(10, 137, 990, 137);
  }
}  
  

  
  
