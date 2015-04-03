// Wire Slave Receiver
// by Nicholas Zambetti <http://www.zambetti.com>

// Demonstrates use of the Wire library
// Receives data as an I2C/TWI slave device
// Refer to the "Wire Master Writer" example for use with this

// Created 29 March 2006

// This example code is in the public domain.


#include <Wire.h>
byte data[30];
void setup()
{
  Wire.begin(4);                // join i2c bus with address #4
  Wire.onReceive(receiveEvent); // register event
  Serial.begin(9600);           // start serial for output
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
      break;
    case 1:
      Serial.print("Motor Command: ");
      Serial.print("Forward ");
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
      break;
    case 2:
      Serial.print("Motor Command: ");
      Serial.print("Reverse ");
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
      break;
    case 3:
      Serial.print("Motor Command: ");
      Serial.print("Left ");
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
      break;
    case 4:
      Serial.print("Motor Command: ");
      Serial.print("Right ");
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
      break;
    default:
      Serial.print("Motor Command: ");
      Serial.print(data[0]);
      Serial.print("Motor Power: ");
      Serial.println(data[1]);
      break;
  }
}
