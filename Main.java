/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carpinteria;

/**
 *
 * @author informatica
 */
public class Main {
    public static void main(String[] args) {
        int cantMuebles=19;
        Carpinteria carpinteria = new Carpinteria(cantMuebles);
        int cantCarpinterosP1=3;
        int cantCarpinterosP2=3;
        int cantCarpinterosP3=3;
        int cantCarpinterosEnsbladores=3;
        Thread[] carpinterosP1 = new Thread[cantCarpinterosP1];
        Thread[] carpinterosP2 = new Thread[cantCarpinterosP2];
        Thread[] carpinterosP3 = new Thread[cantCarpinterosP3];
        Thread[] carpinterosEnsambladores = new Thread[cantCarpinterosEnsbladores];
        for (int i = 0; i < carpinterosP1.length; i++) {
            carpinterosP1[i] = new Thread(new Carpintero(carpinteria,1));
            carpinterosP1[i].setName("carpinterosP1 "+(i+1));
            carpinterosP1[i].start();
        }
        for (int i = 0; i < carpinterosP2.length; i++) {
            carpinterosP2[i] = new Thread(new Carpintero(carpinteria,2));
            carpinterosP2[i].setName("carpinterosP2 "+(i+1));
            carpinterosP2[i].start();
        }
        for (int i = 0; i < carpinterosP3.length; i++) {
            carpinterosP3[i] = new Thread(new Carpintero(carpinteria,3));
            carpinterosP3[i].setName("carpinterosP3 "+(i+1));
            carpinterosP3[i].start();
        }
        for (int i = 0; i < carpinterosEnsambladores.length; i++) {
            carpinterosEnsambladores[i] = new Thread(new Carpintero(carpinteria,4));
            carpinterosEnsambladores[i].setName("carpinteroEnsamblador "+(i+1));
            carpinterosEnsambladores[i].start();
        }
    }
}
