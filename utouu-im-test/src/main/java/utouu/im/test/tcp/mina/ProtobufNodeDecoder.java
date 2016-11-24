package utouu.im.test.tcp.mina;

import java.util.Arrays;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import utouu.im.net.tcp.mina.entity.Message;
import utouu.im.obj.ObjectPool;
import utouu.im.utils.GameUtil;

public class ProtobufNodeDecoder implements ProtocolDecoder {

	@Override
	public void decode(IoSession session, IoBuffer ioBuffer,
			ProtocolDecoderOutput out) throws Exception {
		byte[] cached = getSessionCache(session, ioBuffer);
		do {
			if (cached.length < 8) {
				session.setAttribute("sessionCache", cached);
				break;
			}

			byte[] lengthDatas = Arrays.copyOfRange(cached, 4, 8);
			GameUtil.revert(lengthDatas);
			int length = GameUtil.bytesToInt(lengthDatas, 0);

			if (cached.length < length) {
				// logger.debug("收到消息长度不足构成一个message");
				session.setAttribute("sessionCache", cached);
				break;
			}
			Message message = (Message) ObjectPool.getPool().borrow(
					Message.class);
			byte[] codeBytes = Arrays.copyOfRange(cached, 0, 4);
			GameUtil.revert(codeBytes);
			int code = GameUtil.bytesToInt(codeBytes, 0);
			byte[] datas = Arrays.copyOfRange(cached, 8, length);
			message.setCode(code);
			message.setLength(length);
			message.setData(datas);
			message.setIoSession(session);
			out.write(message);
			cached = Arrays.copyOfRange(cached, length, cached.length);
			session.setAttribute("sessionCache", cached);
		} while (true);
	}

	private byte[] getSessionCache(IoSession ioSession, IoBuffer newBuff) {
		byte[] cacheBytes = (byte[]) ioSession.getAttribute("sessionCache");
		if (cacheBytes == null) {
			int newCapacity = newBuff.remaining();
			byte[] temp = new byte[newCapacity];
			newBuff.get(temp);
			ioSession.setAttribute("sessionCache", temp);
			return temp;
		}
		int newCapacity = newBuff.remaining();
		byte[] newDatas = new byte[newCapacity];
		newBuff.get(newDatas);
		byte[] result = new byte[newDatas.length + cacheBytes.length];
		GameUtil.copyOf(result, cacheBytes, newDatas);
		ioSession.setAttribute("sessionCache", result);
		return result;
	}

	@Override
	public void dispose(IoSession arg0) throws Exception {

	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {

	}

}
