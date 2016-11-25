package utouu.im.net.tcp.mina.work;

import java.util.List;

import org.apache.mina.core.session.IoSession;

import utouu.im.net.tcp.mina.SessionFilterManager;
import utouu.im.net.tcp.mina.filter.FreeSessionFilter;
import utouu.im.thread.QueueWork;
import utouu.im.thread.WorkQueue;
import utouu.im.utils.ConstantUtils;

public class SessionCloseWork extends QueueWork{
	private static final long serialVersionUID = 5306921207938314821L;
	private IoSession ioSession;
	@Override
	public void init(Object... objs) throws Exception {
		this.ioSession = (IoSession) objs[0];
	}

	@Override
	public void run() {
		List<Class<? extends FreeSessionFilter>> filterList = SessionFilterManager.getManager().getFilters();
		Object obj = new Object();
		for(Class<? extends FreeSessionFilter> clz : filterList){
			try {
				FreeSessionFilter filter = clz.newInstance();
				obj = filter.doFilter(ioSession,obj);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		ioSession.closeNow();
		ioSession = null;//等待资源回收
	}

	@Override
	public WorkQueue getWorkQueue() {
		return new WorkQueue() {
			
			@Override
			public long getId() {
				return ConstantUtils.SESSION_CLOSE_QUEUE;
			}
		};
	}

}
