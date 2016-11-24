package utouu.im.net.tcp.mina;

import utouu.im.thread.WorkQueue;
import utouu.im.utils.ConstantUtils;

public enum IMQueue implements WorkQueue{
	IM_LOGIN_QUEUE(ConstantUtils.IM_LOGIN_QUQUE), IM_ROLE_QUEUE(ConstantUtils.IM_ROLE_QUEUE), IM_NODE_QUEUE(ConstantUtils.IM_NODE_QUEUE)
	;
	private IMQueue(long id) {
		this.id = id;
	}
	private long id;
	@Override
	public long getId() {
		return id;
	}

}
