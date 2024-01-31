//Incluyes la biblioteca del Servo
#include <Servo.h>. 
//defines Trig y Echo del sensor ultrsónico 
const int trigPin = 10;
const int echoPin = 11;
int ledp=9;
//variables para la duracion y la distancia 
long duration;
int distance;
Servo myServo; //Crea un objeto servo para controlar el servomotor


void setup() {
  pinMode(trigPin, OUTPUT); 
  //Establecemos el trigPin como salida
  pinMode(echoPin, INPUT);
  //Estanblecemos el Exhopin como entrada
  Serial.begin(9600);
  myServo.attach(12);
  //Define el ping en el que esta conectado el servo
  //pruebas de distancia
  pinMode(ledp, OUTPUT);
}


void loop() {
  // Este for rota el servomotor de 15 a 165 grados 
  for(int i=15;i<=165;i++){  
  myServo.write(i);
  delay(30);
  distance = calculateDistance();
  //Serial.print("Grados: ");
  Serial.println(i);
  //Serial.print(" Distancia: ");
  Serial.println(distance);
  //Serial.println("--");
 /*explicando las cosas
  // llama a la funcion para calcular la distancia medida por el sensor ultrasonico para cada grado
  Serial.print(i); //este es X
  // Envia el grado actual 
  Serial.print(",");
  //Envia el cáracter adicional justo al lado del valor anterior necesario 
  //mas adelante en el IDE de procesamiento para la indexacion
  Serial.print(distance);//este es Y
  //Envia el envia el valor de la distancia al puerto serie
  Serial.print("."); 
  //Envia el caracter adicional justo al lado del valor anterior necesario mas adelante en el IDE
  //de procesamiento para la inexacion
  */
  }
  //Repite las lineas anteriores de 165 a 15 grados
  for(int i=165;i>15;i--){  
  myServo.write(i);
  delay(30);
  distance = calculateDistance();
 // Serial.print("Grados: ");
  Serial.println(i);
  //Serial.print(" Distancia: ");
  Serial.println(distance);
  //Serial.println("--");
  }

}

//funcion para calcular la distancia medida por el sensor ultrasonico
int calculateDistance(){ 
  digitalWrite(trigPin, LOW); 
  delayMicroseconds(2);
//Estabece el trigPin en estado Alto durante 10 microsegundos 
  digitalWrite(trigPin, HIGH); 
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH); 
//Lee el ExhoPinh y devuelve el tiempo de viaje de la onda de sonido en microsegundo
  distance= duration*0.034/2;
  return distance;
}