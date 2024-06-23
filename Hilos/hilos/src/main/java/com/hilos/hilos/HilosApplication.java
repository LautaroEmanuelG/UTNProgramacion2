package com.hilos.hilos;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HilosApplication {

	public static void main(String argv[]) throws InterruptedException{
			Animal tortuga = new Animal("Tortuga");
			tortuga.setPriority(1);
		   
			Animal koala = new Animal("Koala");
			koala.setPriority(2);
		   
			Animal liebre = new Animal("Liebre");
			liebre.setPriority(3);
			Animal rata = new Animal("Rata");
			rata.setPriority(6);
			Animal leopardo = new Animal("Leopardo");
			leopardo.setPriority(10);
			tortuga.start();
			koala.start();
			rata.start();
			liebre.start();
			leopardo.start();
			tortuga.join();
			koala.join();
			rata.join();
			liebre.join();
			leopardo.join();
			}
	}
