package com.jadetrade.agent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import com.jadetrade.view.BuyerGui;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;

public class BuyerAgent extends Agent{
	
	private BuyerGui buyerGui;
	private String[] wantToBuy;
	
	private static final long serialVersionUID = 1L;
	public void setup(){
		

			buyerGui = new BuyerGui(this);
			
			boolean isAppleOSX = System.getProperty("os.name").equals("Mac OS X");
			if (isAppleOSX) {
				//com.apple.concurrent.Dispatch.getInstance().getNonBlockingMainQueueExecutor().execute(threadRunner);
				Class<?> comAppleConcurrentDispatch = null;
				try {
					comAppleConcurrentDispatch = Class.forName("com.apple.concurrent.Dispatch");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Method getInstance = null;
				try {
					getInstance = comAppleConcurrentDispatch.getMethod("getInstance", (Class<?>[])null);
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Object dispatchInstance = null;
				try {
					dispatchInstance = getInstance.invoke(null,(Object[])null);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Method getNonBlockingMainQueueExecutor = null;
				try {
					getNonBlockingMainQueueExecutor = dispatchInstance.getClass().getMethod("getNonBlockingMainQueueExecutor", (Class<?>[])null);
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Executor executor = null;
				try {
					executor = (Executor) getNonBlockingMainQueueExecutor.invoke(dispatchInstance, (Object[]) null);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				executor.execute(new Runnable() {
					public void run() {
						try {
							buyerGui.open();
						} catch (Throwable t) {
							// insert your error handling here
						}	
					}
				});
			} else {
				buyerGui.open();
			}
	
		
		System.out.println("Hallo! Buyer-agent "+getAID().getName()+" is ready.");
		
		
	}
	public void takeDown(){
		
		System.out.println(this.wantToBuy[0]);
		System.out.println(this.wantToBuy[1]);
		try {
			DFService.deregister(this);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}

		// Printout a dismissal message
		System.out.println("Seller-agent "+getAID().getName()+" terminating.");
		
	}
	public String[] getWantToBuy() {
		return wantToBuy;
	}
	public void setWantToBuy(String[] wantToBuy) {
		this.wantToBuy = wantToBuy;
	}

}
