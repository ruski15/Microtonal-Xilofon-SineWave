import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 
import ddf.minim.signals.*; 
import java.util.*; 
import controlP5.*; 
import ddf.minim.*; 
import ddf.minim.signals.*; 
import ddf.minim.*; 
import ddf.minim.signals.*; 
import ddf.minim.*; 
import ddf.minim.signals.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class glockenspiel14BUTTON_AND_FIRE_COMBINE3 extends PApplet {








//UNITS TO DEFINE THE BLOCK POSITION
int x;
int units = 12;
int dist = 54 / units;//54 is the aprox distance between A4 and B4.
int wideCount = 1000 / units;
int space = 10;
IntList xpos;
int xposDef;

//DEFINE
ControlP5 cp5;
Minim minim;
AudioOutput au_out;
Block[] myBlocks;
Position[] p = new Position[units];
Check check;
ArrayList<SineWave> tones;
SineWave current_wave;
boolean isOn = true;


public void setup() {
  size(1000, 1000);
  background(0);

  //BUTTONS
  cp5 = new ControlP5(this);

  // create a new button with name 'buttonA'
  cp5.addButton("LESS")
    .setValue(+2)
      .setPosition(10, 10)
        .setSize(100, 19)
          ;

  // and add another botton
  cp5.addButton("MORE")
    .setValue(-2)
      .setPosition(10, 30)
        .setSize(100, 19)
          ;



  // This initiates the Minim object, which takes care of playing sounds
  minim = new Minim(this);
  au_out = minim.getLineOut();

  //Initialize the check button 
  check = new Check(500, 600, "Check");

  // initiate the blocks array; positions array
  myBlocks = new Block[units];
  println(myBlocks);


  // initiate the array of tones and populate it
  tones = new ArrayList<SineWave>();
  for (int i = 0; i < units; i++ ) {
    tones.add(new SineWave(440+(i*dist), 1, 44100)); 
    SineWave wavePos = tones.get(i); 
    p[i] = new Position(wavePos, wideCount*i, 350, "A_"+i); //Initialize each position  }
  }

  // now shuffle it! :-D :-D :-D
  //Collections.shuffle(tones);

  // xpos to suffle it!

  xpos = new IntList();

  for (int i = 0; i < units; i++) {
    xpos.append(i); //* wideCount + 100);
  }
  xpos.shuffle();
  println(xpos);


  // fill blocks with several tones (and positions)
  for (int i = 0; i < units; i++) {
    SineWave wave = tones.get(i);
    au_out.addSignal(wave);
    au_out.disableSignal(wave);

    int xposDef = xpos.get(i);
    Block b = new Block(wave, xposDef * wideCount + (wideCount/2), 100, "block"+Integer.toString(i)); 
    myBlocks[i] = b;
  } 


  println(myBlocks);
}

public void draw() {
  background(0);


  for (int i = 0; i < units; i++) {
    p[i].display();
  }
  for (int i = 0; i < units; i++) {
    myBlocks[i].display();
  }
  check.display();

  if (mousePressed) {
    if (checkbox() == true) {
      everythingIsRight();
    }
  }
}

public void MORE() {

  units = units+1;
  if (units<24) {
    int dist = 54 / units;//54 is the aprox distance between A4 and B4.
    int wideCount = 1000 / units;
    int space = 10;
    IntList xpos;


    myBlocks = new Block[units];
    p = new Position[units];


    minim = new Minim(this);
    au_out = minim.getLineOut();

    // initiate the array of tones and populate it
    tones = new ArrayList<SineWave>();
    for (int i = 0; i < units; i++ ) {
      tones.add(new SineWave(440+(i*dist), 1, 44100)); 
      SineWave wavePos = tones.get(i); 
      p[i] = new Position(wavePos, wideCount*i, 350, "A_"+i); //Initialize each position  }
    }

    xpos = new IntList();
    for (int i = 0; i < units; i++) {
      xpos.append(i); //* wideCount + 100);
    }
    xpos.shuffle();
    println(xpos);

    // fill blocks with several tones (and positions)
    for (int i = 0; i < units; i++) {
      SineWave wave = tones.get(i);
      au_out.addSignal(wave);
      au_out.disableSignal(wave);

      int xposDef = xpos.get(i);
      Block b = new Block(wave, xposDef * wideCount + (wideCount/2), 100, "block"+Integer.toString(i)); 
      myBlocks[i] = b;
    }
  } else {
    units=23;
  }
}
public void LESS() {
  units = units-1;
  if (units>0) {
    int dist = 54 / units;//54 is the aprox distance between A4 and B4.
    int wideCount = 1000 / units;
    int space = 10;
    IntList xpos;

    myBlocks = new Block[units];
    p = new Position[units];
    tones = new ArrayList<SineWave>();

    minim = new Minim(this);
    au_out = minim.getLineOut();


    for (int i = 0; i < units; i++ ) {
      tones.add(new SineWave(440+(i*dist), 1, 44100)); 
      SineWave wavePos = tones.get(i); 
      p[i] = new Position(wavePos, wideCount*i, 350, "A_"+i); //Initialize each position  }
    }

    // now shuffle it! :-D :-D :-D
    //Collections.shuffle(tones);

    // xpos to suffle it!

    xpos = new IntList();

    for (int i = 0; i < units; i++) {
      xpos.append(i); //* wideCount + 100);
    }
    xpos.shuffle();

    // fill blocks with several tones (and positions)
    for (int i = 0; i < units; i++) {
      SineWave wave = tones.get(i);
      au_out.addSignal(wave);
      au_out.disableSignal(wave);

      int xposDef = xpos.get(i);
      Block b = new Block(wave, xposDef * wideCount + (wideCount/2), 100, "block"+Integer.toString(i)); 
      myBlocks[i] = b;
    }
  } else {
    units=1;
  }
}



public void mousePressed() {
  println("x, y: " + mouseX + ", " + mouseY);
  for (int z = 0; z < myBlocks.length; z++) {
    p[z].display();
    SineWave w = myBlocks[z].onMousePressed(mouseX, mouseY); 
    if (w != null) {
      au_out.enableSignal(w);
      current_wave = w;
      myBlocks[z].weAreMoving = true;
    }
  }
}

public void mouseDragged() {
  
  for (int i = 0; i < myBlocks.length; i++) {
    p[i].display();
    if (myBlocks[i].weAreMoving) {

      // first "erase it"
      fill(0);
      rect(myBlocks[i].x, myBlocks[i].y, myBlocks[i].width, myBlocks[i].height);
   

      // turn off the sound
      au_out.disableSignal(myBlocks[i].wave);
      current_wave = null;

      // change the position
      myBlocks[i].x = mouseX;
      myBlocks[i].y = mouseY;

      // redraw
      fill(255, 0, 0); 
      rect(p[i].x, p[i].y, wideCount-space, 200);
      fill(255);
      rect(myBlocks[i].x, myBlocks[i].y, myBlocks[i].width, myBlocks[i].height);
    }
  }
}

public void mouseReleased() {
  println("x, y: " + mouseX + ", " + mouseY);
  if (current_wave != null) {
    au_out.disableSignal(current_wave);
  } 
  for (int i = 0; i < myBlocks.length; i++) {
    p[i].display();
    fill(255, 0, 0); 
    rect(p[i].x, p[i].y, wideCount-space, 200);
    fill(255);
    rect(myBlocks[i].x, myBlocks[i].y, myBlocks[i].width, myBlocks[i].height);

    if (myBlocks[i].weAreMoving) {
      myBlocks[i].weAreMoving = false;
    }
  }
}


public boolean checkbox() {
  if (mouseX > check.x &&     
    mouseX < (check.x + check.width) && 
    mouseY > check.y &&
    mouseY < (check.y + check.height)) {        
    println("We are clicking on " + check.name);
    check.weAreMoving = true;
    return true;
  }  
  return false;
}

// check if blocks are inside positions

public boolean inPosition(int rect_x, int rect_y, int rect_width, int rect_height, 
int pos_x, int pos_y, int pos_width, int pos_height) {

  if (rect_x > pos_x &&
    (rect_x + rect_width) < (pos_x +pos_width) && 
    rect_y > pos_y && 
    (rect_y + rect_height) < (pos_y + pos_height)) {   
    return true;
  } 
  return false;
}


public boolean unCorrectTone(SineWave tone, SineWave pos) { 
  if (tone != pos) {
    return true;
  }
  return false;
}

public boolean everythingIsRight() {
  for (int i = 0; i < units; i++) {
    if (!inPosition (myBlocks[i].x, myBlocks[i].y, myBlocks[i].width, myBlocks[i].height, 
    p[i].x, p[i].y, p[i].width, p[i].height)) {
      println("It is not correct due to position");
      fill(255);
      textAlign(CENTER);
      textSize(15);
      stroke(19);
      text("Not quite. Try again? ;)", width/2, 250, 200);
      return false;
    } else if (inPosition (myBlocks[i].x, myBlocks[i].y, myBlocks[i].width, myBlocks[i].height, 
    p[i].x, p[i].y, p[i].width, p[i].height)) {
      if (unCorrectTone(myBlocks[i].wave, p[i].wavePos)) {     
        println("It is not correct due to tone ");
        fill(255);
        //textAlign(CENTER);
        //textSize(15);
        //stroke(19);
        text("Not quite. Try again? ;)", width/2, 250, 200);
        return false;
      }
    }
  }
  println( "It is correct");
  fill(255);
  textAlign(CENTER);
  textSize(15);
  stroke(19);
  text("Good ear", width/2, 250, 200);
  return true;
}


public void keyPressed() {
  /*  if (!isOn) {
   //au_out.unmute();
   au_out.disableSignal(wave);
   } else {
   au_out.enableSignal(wave);
   //au_out.mute();
   }
   isOn = !isOn; */
}




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
  
  public void display(){
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
  public void display() {
    fill(0,255,0);
    stroke(255, 0, 0);
    rect(check.x, check.y, check.width, check.height);
    
    fill(255, 0, 0);
    textAlign(CENTER);
    textSize(15);
    stroke(19);
    text("Check", 527, 635, height*0.35f);
    
    fill(255);
    textAlign(CENTER);
    textSize(13);
    stroke(19);
    text("Each white piece has a different pitch. They must be placed inside one of the red colors\nattending to their pitches from the lowest to the highest.\nClick on them to hear their sounds and drag them to place them in their correct positions.", 500, 30, height*0.35f);
  
    stroke(255);
    line(10, 137, 990, 137);
  }
}  
  

  
  



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
  public void display() {
      fill(255, 0, 0); 
      rect(x, y, width, height);
  }
  public void erase()  {
    fill(0);
    rect(x, y, width, height);
  }  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "glockenspiel14BUTTON_AND_FIRE_COMBINE3" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
