package pt.iul.dcti.pcd.ballPool;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class Bola extends Observable implements DrawableBall, Runnable {
	private float estado=0;
	private Color color=new Color((int)(Math.random()*256), 
			(int)(Math.random()*256), (int)(Math.random()*256));




	@Override
	public void run() {
		while( !bolaAtingiuLimite() ){
			double rand = Math.random();
			double result = 0.01 + (rand * (0.1 - 0.01));
			if(estado + result >= 1)
				estado = 1;
			else
				estado += result;
			setChanged();
			notifyObservers();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public boolean bolaAtingiuLimite(){
		return estado>=1;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public float getX() {
		return estado;
	}

	@Override
	public int getSize() {
		return 10;
	}

}
