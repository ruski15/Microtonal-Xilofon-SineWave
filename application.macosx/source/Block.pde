import ddf.minim.*;
import ddf.minim.signals.*;

class Block {
  SineWave wave;
  int x;
  int y;
  int width = 20;
  int height = 70;
  String name;
  boolean weAreMoving = false;
 
  // This is a constructor 
  Block(SineWave wave, int x, int y, String name) {
    this.wave = wave;
    this.x = x;
    this.y = y;
    this.name = name;
  } 
  
  // This is called "overloading the constructor"
  // Lets you make a block without having to give it a name  
  Block(SineWave wave, int x, int y) {
    this(wave, x, y, "default");
  }
  
  void display(){
   fill(255);
   rect(x, y, width, height); 
    
  }
  // @return wave is mouse_x and mouse y_ in the block, null otherwise 
  public SineWave onMousePressed(int mouse_x, int mouse_y) {
    if (mouse_x > this.x && mouse_x < (this.x + this.width) && 
      mouse_y > this.y && mouse_y < (this.y + this.height)) {
        println("block: " + name);
        return wave;
     }
     return null;
  }
}
