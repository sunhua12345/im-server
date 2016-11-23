package utouu.im.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AynWork implements Work, Runnable {
	private static final long serialVersionUID = -8367701814444060112L;
	protected Logger logger = LoggerFactory.getLogger(getClass());
}
