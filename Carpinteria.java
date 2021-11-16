/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carpinteria;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author informatica
 */
public class Carpinteria {

    private int cantidadPartes1Buffer = 0, cantidadPartes2Buffer = 0, cantidadPartes3Buffer = 0, mueblesEnsamblados = 0;
    private int tamañoBufferParte1, tamañoBufferParte2, tamañoBufferParte3, cantMuebles;
    private Lock buffer1 = new ReentrantLock();
    private Condition productoresBuffer1;
    private Condition consumidoresBuffer1;
    private Lock buffer2 = new ReentrantLock();
    private Condition productoresBuffer2;
    private Condition consumidoresBuffer2;
    private Lock buffer3 = new ReentrantLock();
    private Condition productoresBuffer3;
    private Condition consumidoresBuffer3;

    public Carpinteria(int M) {
        this.cantMuebles = M;
        this.tamañoBufferParte1 = 1;
        this.productoresBuffer1 = buffer1.newCondition();
        this.consumidoresBuffer1 = buffer1.newCondition();
        this.tamañoBufferParte2 = 1;
        this.productoresBuffer2 = buffer2.newCondition();
        this.consumidoresBuffer2 = buffer2.newCondition();
        this.tamañoBufferParte3 = 1;
        this.productoresBuffer3 = buffer3.newCondition();
        this.consumidoresBuffer3 = buffer3.newCondition();

    }

    public boolean putBuffer1() {
        boolean seguirProduciendoP1 = true;
        try {
            buffer1.lock();
            if (mueblesEnsamblados < cantMuebles) {
                while (cantidadPartes1Buffer == 1) {
                    System.out.println(Thread.currentThread().getName() + " Producer parte1 wait! ");
                    productoresBuffer1.await();
                }
                cantidadPartes1Buffer++;
                System.out.println(Thread.currentThread().getName() + ": Deposite parte1, el número de partes actual =" + cantidadPartes1Buffer);
                consumidoresBuffer1.signalAll(); // notificar a los consumidores
            } else {
                seguirProduciendoP1 = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            buffer1.unlock();
        }
        return seguirProduciendoP1;
    }

    public boolean putBuffer2() {
        boolean seguirProduciendoP2 = true;
        try {
            buffer2.lock();
            if (mueblesEnsamblados < cantMuebles) {
                while (cantidadPartes2Buffer == 1) {
                    System.out.println(Thread.currentThread().getName() + " Producer parte2 wait! ");
                    productoresBuffer2.await();
                }
                cantidadPartes2Buffer++;
                System.out.println(Thread.currentThread().getName() + ": Deposite parte2, el número de partes actual =" + cantidadPartes2Buffer);
                consumidoresBuffer2.signalAll(); // notificar a los consumidores
            } else {
                seguirProduciendoP2 = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            buffer2.unlock();
        }
        return seguirProduciendoP2;
    }

    public boolean putBuffer3() {
        boolean seguirProduciendoP3 = true;
        try {
            buffer3.lock();
            if (mueblesEnsamblados < cantMuebles) {
                while (cantidadPartes3Buffer == 1) {
                    System.out.println(Thread.currentThread().getName() + " Producer parte3 wait! ");
                    productoresBuffer3.await();
                }
                cantidadPartes3Buffer++;
                System.out.println(Thread.currentThread().getName() + ": Deposite parte3, el número de partes actual =" + cantidadPartes3Buffer);
                consumidoresBuffer3.signalAll(); // notificar a los consumidores
            } else {
                seguirProduciendoP3 = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            buffer3.unlock();
        }
        return seguirProduciendoP3;
    }

    public boolean ensamblar() {
        boolean seguirEnsamblando = true;
        buffer1.lock();// bloquear
        buffer2.lock();
        buffer3.lock();
        try {
            while (cantidadPartes1Buffer == 0) { // El búfer está vacío
                System.out.println(Thread.currentThread().getName() + " Consumer parte1 wait！");
                consumidoresBuffer1.await();
            }
            // consumir una parte1
            cantidadPartes1Buffer--;
            System.out.println(Thread.currentThread().getName() + ": Saque parte1, ahora el número de partes =" + cantidadPartes1Buffer);
            while (cantidadPartes2Buffer == 0) { // El búfer está vacío
                System.out.println(Thread.currentThread().getName() + " Consumer parte2 wait！");
                consumidoresBuffer2.await();
            }
            // consumir una parte2
            cantidadPartes2Buffer--;
            System.out.println(Thread.currentThread().getName() + ": Saque parte2, ahora el número de partes =" + cantidadPartes2Buffer);
            while (cantidadPartes3Buffer == 0) { // El búfer está vacío
                System.out.println(Thread.currentThread().getName() + " Consumer parte3 wait！");
                consumidoresBuffer3.await();
            }
            // consumir una parte3
            cantidadPartes3Buffer--;
            System.out.println(Thread.currentThread().getName() + ": Saque parte3, ahora el número de partes =" + cantidadPartes3Buffer);
            mueblesEnsamblados++;
            System.out.println("muebles ensamblados = " + mueblesEnsamblados);
            if (mueblesEnsamblados >= cantMuebles) {
                seguirEnsamblando = false;
            }   
            productoresBuffer2.signalAll();
            productoresBuffer3.signalAll();
            productoresBuffer1.signalAll();                         
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {   
            buffer1.unlock();
            buffer2.unlock();
            buffer3.unlock();
        }
        return seguirEnsamblando;
    }
}
