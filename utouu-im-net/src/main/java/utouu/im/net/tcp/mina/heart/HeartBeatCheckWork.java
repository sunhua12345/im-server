package utouu.im.net.tcp.mina.heart;

import utouu.im.auto.AutoAynStart;
import utouu.im.thread.TimeWork;
import utouu.im.thread.TimerCenter;

public class HeartBeatCheckWork extends AutoAynStart {
	private static final long serialVersionUID = 5029484767056663869L;

	@Override
	public long interval() {
		return TimerCenter.getCenter().getInterval(HeartBeatCheck.class);
	}

	@Override
	public Class<? extends TimeWork> getClz() {
		return HeartBeatCheck.class;
	}

}
