package utouu.im.net.tcp.mina.utils;

import java.util.Collection;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.GeneratedMessage;

import utouu.im.config.Config;
import utouu.im.net.tcp.mina.SessionClient;
import utouu.im.net.tcp.mina.codec.work.SendHearBeatWork;
import utouu.im.net.tcp.mina.codec.work.SendMsgWork;
import utouu.im.protobuf.pb.MsgCode.AlertType;
import utouu.im.protobuf.pb.MsgCode.GameCode;
import utouu.im.protobuf.pb.MsgCode.Param;
import utouu.im.protobuf.pb.MsgCode.ResAlertMsg;
import utouu.im.protobuf.pb.MsgCode.ResHeartBeat;
import utouu.im.thread.WorkManager;
import utouu.im.utils.ConstantUtils;

public class IoSender {
	private static Logger logger = LoggerFactory.getLogger(IoSender.class);

	private static void sendMsg(SessionClient sessionClient, int code, GeneratedMessage.Builder<?> builder) {
		sendMsg(sessionClient.getIoSession(), code, builder.build().toByteArray());
	}

	public static void sendAlert(SessionClient sessionClient, AlertType alertType, Object... objs) {
		ResAlertMsg.Builder builder = ResAlertMsg.newBuilder();
		builder.setAlertType(alertType);
		for (Object obj : objs) {
			if (obj instanceof Integer) {
				builder.addParams(Param.newBuilder().setIntNum((Integer) obj));
			}
			if (obj instanceof Long) {
				builder.addParams(Param.newBuilder().setLongNum((Long) obj));
			}
		}
		sendMsg(sessionClient, GameCode.RES_ALERT_MSG, builder);
	}

	public static void sendMsg(SessionClient sessionClient, GameCode code, GeneratedMessage.Builder<?> builder) {
		if (code != GameCode.REQ_HEART_BEAT && Config.getConfig().LOG_OPEN) {
			logger.info(
					"\n----------------------------SEND--------------------------------\nTO:{}\nCODE:{}[{}]\n\n"
							+ "-----------------------------------------------------------------\n\n\n\n\n\n{}",
					sessionClient.getIoSession(), code, code, builder.build());
		}
		sendMsg(sessionClient, code.getNumber(), builder);
	}

	public static void sendMsg(Collection<? extends SessionClient> sessionClients, GameCode code,
			GeneratedMessage.Builder<?> builder) {
		for (SessionClient sessionClient : sessionClients) {
			sendMsg(sessionClient, code, builder);
		}
	}

	public static void sendAlert(IoSession session, AlertType alertType, Object... objs) {
		ResAlertMsg.Builder builder = ResAlertMsg.newBuilder();
		builder.setAlertType(alertType);
		for (Object obj : objs) {
			if (obj instanceof Integer) {
				builder.addParams(Param.newBuilder().setIntNum((Integer) obj));
			}
			if (obj instanceof Long) {
				builder.addParams(Param.newBuilder().setLongNum((Long) obj));
			}
		}
		sendMsg(session, GameCode.RES_ALERT_MSG, builder);
	}

	public static void sendMsg(IoSession session, GameCode code, GeneratedMessage.Builder<?> builder) {
		SessionClient sessionClient = (SessionClient) session.getAttribute(ConstantUtils.UNIQUE_CLIENT_ID);
		if (sessionClient != null) {
			sendMsg(sessionClient, code, builder);
			return;
		}
		if (code != GameCode.REQ_HEART_BEAT && Config.getConfig().LOG_OPEN) {
			logger.info(
					"\n----------------------------SEND--------------------------------\nTO:{}\nCODE:{}[{}]\n\n"
							+ "-----------------------------------------------------------------\n\n\n\n\n\n{}",
					session, code, code.getNumber(), builder.build());
		}
		sendMsg(session, code.getNumber(), builder.build().toByteArray());
	}

	private static void sendMsg(IoSession ioSession, int code, byte[] bytes) {
		WorkManager.getManager().submit(SendMsgWork.class, code, ioSession, bytes);
	}

	public static void sendHeartBeatMsg(IoSession session, ResHeartBeat.Builder builder) {
		WorkManager.getManager().submit(SendHearBeatWork.class, GameCode.RES_HEART_BEAT_VALUE, session,
				builder.build().toByteArray());
	}

}
