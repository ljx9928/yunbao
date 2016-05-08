package org.m4.platform.service;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class QueueFutureTask extends FutureTask {
	private Queue queue;

	public QueueFutureTask(Callable callable, Queue queue) {
		super(callable);
		this.queue = queue;
	}

	public QueueFutureTask(Callable callable) {
		super(callable);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

	@Override
	public boolean isDone() {
		if(super.isDone()){
			//queue.
		}
		return super.isDone();
	}

	@Override
	public Object get() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return super.get();
	}

	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
	}

}
