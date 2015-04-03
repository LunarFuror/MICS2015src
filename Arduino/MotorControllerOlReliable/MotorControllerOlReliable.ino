
// Arduino set up for I2c as Slave Receiver 
// Created April 3 2015




#include <Wire.h>
byte data[30];
int MotPin1 = 22;
int MotPin1PWM = 8;
int MotPin2 = 24;
int MotPin2PWM = 9;
int MotPin3 = 26;
int MotPin3PWM = 10;
int MotPin4 = 28;
int MotPin4PWM = 11;

void setup()
{
  Wire.begin(4);                // join i2c bus with address #4
  Wire.onReceive(receiveEvent); // register event
  Serial.begin(9600);           // start serial for output
  pinMode(MotPin1, OUTPUT);
  pinMode(MotPin1PWM, OUTPUT);
  pinMode(MotPin2, OUTPUT);
  pinMode(MotPin2PWM, OUTPUT);
  pinMode(MotPin3, OUTPUT);
  pinMode(MotPin3PWM, OUTPUT);
  pinMode(MotPin4, OUTPUT);
  pinMode(MotPin4PWM, OUTPUT);
  
}

void loop()
{
  delay(100);
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
      break;
    case 8:
      Serial.println("Starting Conveyor Load");
      break;
    case 9:
      Serial.println("Releasing balls on conveyor");
      break;
      
      
      
    default:
      Serial.print("Motor Command: ");
      Serial.print(data[0]);
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
      break;
      
  }
  
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








