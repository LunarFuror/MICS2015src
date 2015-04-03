

 //Digital 4 – controls RELAY4’s COM4 pin (located in J4) 
 // Digital 5 – controls RELAY3’s COM3 pin (located in J3) 
 //Digital 6 – controls RELAY2’s COM2 pin (located in J2) 
 //Digital 7 – controls RELAY1’s COM1 pin (located in J1) 



int Motor1Control = 7; 
int Motor2Control = 6;
int Motor3Control = 5;


int TopSensor = 0;
int SecondSensor = 1;
int ThirdSensor = 2;



// the setup routine runs once when you press reset:
void setup()  { 
  
 
 
  pinMode(Motor1Control, OUTPUT);
  pinMode(Motor2Control, OUTPUT);
  pinMode(Motor3Control, OUTPUT);

  pinMode(TopSensor, INPUT_PULLUP);
  pinMode(SecondSensor, INPUT_PULLUP);
  pinMode(ThirdSensor, INPUT_PULLUP);
 
  
}
  
void loop()  { 
 int TopSensor = digitalRead(0);
 int SecondSensor = digitalRead(1);
 int ThirdSensor = digitalRead(2); 
 if (TopSensor == HIGH) {
   digitalWrite(Motor1Control,HIGH);// NO1 and COM3 Connected (the motor is running)
   digitalWrite(Motor2Control,HIGH);// NO2 and COM3 Connected (the motor is running)
   digitalWrite(Motor3Control,HIGH);// NO3 and COM3 Connected (the motor is running)

 }
 else if ((TopSensor == LOW) && (SecondSensor == HIGH) && (ThirdSensor == HIGH)){
   digitalWrite(Motor1Control,LOW);// NO1 and COM3 Connected (the motor is running)  
   digitalWrite(Motor2Control,HIGH);// NO2 and COM3 Connected (the motor is running)
   digitalWrite(Motor3Control,HIGH);// NO3 and COM3 Connected (the motor is running)
 
 }
 else if ((TopSensor == LOW) && (SecondSensor == LOW) && (ThirdSensor == HIGH)){
 
   digitalWrite(Motor1Control,LOW);// NO1 and COM3 Connected (the motor is running)
   digitalWrite(Motor2Control,LOW);// NO2 and COM3 Connected (the motor is running)
   digitalWrite(Motor3Control,HIGH);// NO3 and COM3 Connected (the motor is running)

 }
 
 else if ((TopSensor == LOW) && (SecondSensor == LOW) && (ThirdSensor == LOW)){
 
   digitalWrite(Motor1Control,LOW);// NO1 and COM3 Connected (the motor is running)
   digitalWrite(Motor2Control,LOW);// NO2 and COM3 Connected (the motor is running)
   digitalWrite(Motor3Control,LOW);// NO3 and COM3 Connected (the motor is running)
 
 }}
 
 
 
 
 
 
 
 
 
 
 

