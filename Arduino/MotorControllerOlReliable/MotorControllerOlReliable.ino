
// Arduino set up for I2c as Slave Receiver 
// Created April 3 2015
#include <Wire.h>
//Movement Constants
byte data[30];
byte returnData[30];
int returnNum = 1;
int MotPin1 = 22;
int MotPin1PWM = 8;
int MotPin2 = 24;
int MotPin2PWM = 9;
int MotPin3 = 26;
int MotPin3PWM = 10;
int MotPin4 = 28;
int MotPin4PWM = 11;
//End Movement Constants

//Relay Shield Constants
int Relay1Control = 7;
bool Relay1On = false;
int Relay2Control = 6;
bool Relay2On = false;
int Relay3Control = 5;
bool Relay3On = false;

int TopSensor = 1;
int MiddleSensor = 2;
int BottomSensor = 3;

int currentMode = 0; //0 - Off, 1 - Load, 2 - Release
bool isLoaded = false;
int numBalls = 0;
//End Relay Shield Constants



void setup()
{
  Wire.begin(4);                // join i2c bus with address #4
  Wire.onReceive(receiveEvent); // register event
  Wire.onRequest(sendData); //return data event
  returnData[0] = 0x01;//01 is the first byte of all return data just to show that the return has happened.
  Serial.begin(9600);  // start serial for output
  
  //MovementPins
  pinMode(MotPin1, OUTPUT);
  pinMode(MotPin1PWM, OUTPUT);
  pinMode(MotPin2, OUTPUT);
  pinMode(MotPin2PWM, OUTPUT);
  pinMode(MotPin3, OUTPUT);
  pinMode(MotPin3PWM, OUTPUT);
  pinMode(MotPin4, OUTPUT);
  pinMode(MotPin4PWM, OUTPUT);
  //EndMovementPins
  
  //Relay Setup
  pinMode(Relay1Control, OUTPUT);
  pinMode(Relay2Control, OUTPUT);
  pinMode(Relay3Control, OUTPUT);
  pinMode(TopSensor, INPUT_PULLUP);
  pinMode(MiddleSensor, INPUT_PULLUP);
  pinMode(BottomSensor, INPUT_PULLUP);
  //End Relay Setup
}
void loop()
{
  switch(currentMode)
  {
    delay(100);
    case 0: //Off
      setRelayMotor(false, false, false);
      break;
    case 1: //Load
      if (!isLoaded)
      {
        int top = digitalRead(TopSensor);
        int middle = digitalRead(MiddleSensor);
        int bottom = digitalRead(BottomSensor); 
        if (top == HIGH) {
          setRelayMotor(true, true, true);
        }
        else if ((top == LOW) && (middle == HIGH) && (bottom == HIGH)){
          setRelayMotor(false, true, true);
        }
        else if ((top == LOW) && (middle == LOW) && (bottom == HIGH)){
          setRelayMotor(false, false, true);
        }
        else if ((top == LOW) && (middle == LOW) && (bottom == LOW)){
          setRelayMotor(false, false, false);
          isLoaded = true;
        }
      }
      break;
    case 2: //Release
      isLoaded = false;
      break;
  }
}

// function that executes whenever data is received from master
// this function is registered as an event, see setup()
void receiveEvent(int howMany)
{
  int i = 0;
  while (0 < Wire.available()) // loop through all but the last
  {
    data[i] = Wire.read(); // receive byte as a character
    Serial.println("Incoming");
    i++;
  }
  
  switch(data[0])
  {
    case 0:
      Serial.print("Motor Command: ");
      Serial.print("Stop ");
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
        Motor1_Stop(0);
        Motor2_Stop(0);
        Motor3_Stop(0);
        Motor4_Stop(0);      
      break;
    case 1:
      Serial.print("Motor Command: ");
      Serial.print("Forward ");
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
        Motor1_Forward(data[1]);
        Motor2_Forward(data[1]);
        Motor3_Forward(data[1]);
        Motor4_Forward(data[1]);
      break;
    case 2:
      Serial.print("Motor Command: ");
      Serial.print("Reverse ");
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
        Motor1_Reverse(data[1]);
        Motor2_Reverse(data[1]);
        Motor3_Reverse(data[1]);
        Motor4_Reverse(data[1]);
      
      break;
    case 3:
      Serial.print("Motor Command: ");
      Serial.print("Left ");
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
        Motor1_Reverse(data[1]);
        Motor2_Forward(data[1]);
        Motor3_Forward(data[1]);
        Motor4_Reverse(data[1]);
      break;
    case 4:
      Serial.print("Motor Command: ");
      Serial.print("Right ");
      Serial.print("Motor Power: ");    
      Serial.println(data[1]);
        Motor1_Forward(data[1]);
        Motor2_Reverse(data[1]);
        Motor3_Reverse(data[1]);
        Motor4_Forward(data[1]);
      break;
    case 5:
      Serial.print("Motor Command: ");
      Serial.print("Turn Right ");
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
        Motor1_Forward(data[1]);
        Motor2_Reverse(data[1]);
        Motor3_Forward(data[1]);
        Motor4_Reverse(data[1]);
      break;
    case 6:  
      Serial.print("Motor Command: ");
      Serial.print("Turn Left ");
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
        Motor1_Reverse(data[1]);
        Motor2_Forward(data[1]);
        Motor3_Reverse(data[1]);
        Motor4_Forward(data[1]);
      break;
    case 7:
      Serial.println("Stopping the conveyor");
      currentMode = 0;
      break;
    case 8:
      Serial.println("Starting Conveyor Load");
      currentMode = 1;
      break;
    case 9:
      Serial.println("Releasing balls on conveyor");
      currentMode = 2;
      break;
    case 10://load the status information to go back to the pi.
      returnNum = 1;
      returnData[returnNum] = isLoaded;
      returnNum++;
      break;
    default:
      Serial.print("Motor Command: ");
      Serial.print(data[0]);
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
      break;
      
  }
  
}

//returns data over i2c to the pi.  return num is num of bytes to return.
//Registered in setup()
void sendData(){
 Wire.write(returnData, returnNum);
}


void Motor1_Forward(int x){
  // function for motor 1 Forward
  digitalWrite(MotPin1, HIGH);
  analogWrite(MotPin1PWM, x);  
}

void Motor1_Reverse(int x){
  // function for motor 1 reverse
  digitalWrite(MotPin1, LOW);
  analogWrite(MotPin1PWM, x);  
}
void Motor1_Stop(int x) {
  //function for motor 1 Stop
   digitalWrite(MotPin1, LOW);
   analogWrite(MotPin1PWM, 0);  
}


void Motor2_Forward(int x){
  // function for motor 2 Forward

  digitalWrite(MotPin2, HIGH);
  analogWrite(MotPin2PWM, x);  
}

void Motor2_Reverse(int x){
  // function for motor 2 reverse
  digitalWrite(MotPin2, LOW);
  analogWrite(MotPin2PWM, x);  
}
void Motor2_Stop(int x) {
  //function for motor 1 Stop
   digitalWrite(MotPin2, LOW);
   analogWrite(MotPin2PWM, 0);  
}

void Motor3_Forward(int x){
  // function for motor 3 Forward

  digitalWrite(MotPin3, HIGH);
  analogWrite(MotPin3PWM, x);  
}
void Motor3_Reverse(int x){
  // function for motor 3 reverse

  digitalWrite(MotPin3, LOW);
  analogWrite(MotPin3PWM, x);  
}
void Motor3_Stop(int x) {
  //function for motor 3 Stop
   digitalWrite(MotPin3, LOW);
   analogWrite(MotPin3PWM, 0);  
}



void Motor4_Forward(int x){
  // function for motor 4 Forward

  digitalWrite(MotPin4, HIGH);
  analogWrite(MotPin4PWM, x);  
}

void Motor4_Reverse(int x){
  // function for motor 4 reverse

  digitalWrite(MotPin4, LOW);
  analogWrite(MotPin4PWM, x);  
}


void Motor4_Stop(int x) {
  //function for motor 4 Stop
   digitalWrite(MotPin4, LOW);
   analogWrite(MotPin4PWM, 0);  
}

//set the motors on or off and also set their bools if need be
void setRelayMotor(bool relay1, bool relay2, bool relay3)
{
   if (Relay1On && !relay1)//want it off but it is currently on
   {
     digitalWrite(Relay1Control,LOW);// NO1 and COM3 Connected (the motor is running)
     Relay1On = false;
   }
   else if (!Relay1On && relay1) //Want it on but it is currently off
   {
     digitalWrite(Relay1Control,HIGH);// NO1 and COM3 Connected (the motor is running)
     Relay1On = true;
   }
   
  if (Relay2On && !relay2)//want it off but it is currently on
   {
     digitalWrite(Relay2Control,LOW);// NO1 and COM3 Connected (the motor is running)
     Relay2On = false;
   }
   else if (!Relay2On && relay2) //Want it on but it is currently off
   {
     digitalWrite(Relay2Control,HIGH);// NO1 and COM3 Connected (the motor is running)
     Relay2On = true;
   }
   
   if (Relay3On && !relay3)//want it off but it is currently on
   {
     digitalWrite(Relay3Control,LOW);// NO1 and COM3 Connected (the motor is running)
     Relay3On = false;
   }
   else if (!Relay3On && relay3) //Want it on but it is currently off
   {
     digitalWrite(Relay3Control,HIGH);// NO1 and COM3 Connected (the motor is running)
     Relay3On = true;
   }
}








