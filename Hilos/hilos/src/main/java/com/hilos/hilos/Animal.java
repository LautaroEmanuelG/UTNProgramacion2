package com.hilos.hilos;


class Animal extends Thread {
    String nombre;

    public Animal(String nombre) {
        this.nombre = nombre;
    }

    public void run() {
        synchronized (nombre) {
            for (int x = 0; x < 5; x++) {
                System.out.print(this.getPriority() + "- " + nombre + "- ");
                System.out.println("hilo actual: ->" + Thread.currentThread());
                if (this.isAlive())
                    System.out.println(" En carrera");
                Thread.yield();
            }
            System.out.println("\nLlega " + nombre);
        }
    }
}