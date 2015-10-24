import ddf.minim.*;
import ddf.minim.signals.*;

class Position {
  SineWave wavePos;
  int wideCount=1000/units;
  int x;
  int y;
  int width = wideCount-space;
  int height = 200;
  String name;
  boolean weAreMoving = false;
  
  Position (SineWave wavePos, int x, int y, String name) {
    this.wavePos = wavePos;
    this.x = x;
    this.y = y;
    this.name = name;
  }
  void display() {
      fill(255, 0, 0); 
      rect(x, y, width, height);
  }
  void erase()  {
    fill(0);
    rect(x, y, width, height);
  }  
}
