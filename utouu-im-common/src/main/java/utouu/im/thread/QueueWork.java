package utouu.im.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class QueueWork implements Work, Runnable {
	private static final long serialVersionUID = -2082817496371756569L;
	public abstract WorkQueue getWorkQueue();
	protected Logger logger = LoggerFactory.getLogger(getClass());
}
