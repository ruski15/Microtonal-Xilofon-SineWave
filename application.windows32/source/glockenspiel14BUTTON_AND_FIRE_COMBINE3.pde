import ddf.minim.*;
import ddf.minim.signals.*;
import java.util.*;
import controlP5.*;



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


void setup() {
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

void draw() {
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



void mousePressed() {
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

void mouseDragged() {
  
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

void mouseReleased() {
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


boolean checkbox() {
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

boolean inPosition(int rect_x, int rect_y, int rect_width, int rect_height, 
int pos_x, int pos_y, int pos_width, int pos_height) {

  if (rect_x > pos_x &&
    (rect_x + rect_width) < (pos_x +pos_width) && 
    rect_y > pos_y && 
    (rect_y + rect_height) < (pos_y + pos_height)) {   
    return true;
  } 
  return false;
}


boolean unCorrectTone(SineWave tone, SineWave pos) { 
  if (tone != pos) {
    return true;
  }
  return false;
}

boolean everythingIsRight() {
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


void keyPressed() {
  /*  if (!isOn) {
   //au_out.unmute();
   au_out.disableSignal(wave);
   } else {
   au_out.enableSignal(wave);
   //au_out.mute();
   }
   isOn = !isOn; */
}

