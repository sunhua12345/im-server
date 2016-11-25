package utouu.im.net.tcp.mina.cache;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

import utouu.im.net.tcp.mina.work.SessionCloseWork;
import utouu.im.thread.WorkManager;
import utouu.im.utils.ConstantUtils;

public class SessionManager {
	private SessionManager() {
	}

	private static SessionManager sessionManager = new SessionManager();

	public static SessionManager getManager() {
		return sessionManager;
	}

	public final static Map<Long, IoSession> sessions = new ConcurrentHashMap<Long, IoSession>();
	private static final Object sessionLock = new Object();

	public static void putSession(IoSession session) {
		synchronized (sessionLock) {
			sessions.put(session.getId(), session);
		}
	}

	public static void removeSession(IoSession session) {
		synchronized (sessionLock) {
			if (sessions.containsKey(session.getId())) {
				sessions.remove(session.getId());
			}
		}
	}

	public static void doHeartBeatCheck(long now) {
		long heartTime = ConstantUtils.TCP_HEARBEAT_TIME;
		for (Entry<Long, IoSession> entry : sessions.entrySet()) {
			IoSession session = entry.getValue();
			long freshTime = (long) session.getAttribute(ConstantUtils.LAST_RECIVED_TIME);
			if (now - freshTime > heartTime) {
				// 心跳超时,释放连接
				closeSession(session);
			}
		}
	}

	public static void closeSession(IoSession session) {
		if (session.isConnected() || session.isActive() || !session.isClosing()) {
			removeSession(session);
			WorkManager.getManager().submit(SessionCloseWork.class,session);
		}
	}

}
