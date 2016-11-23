package utouu.im.net.tcp.mina.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import utouu.im.net.tcp.mina.entity.Message;
import utouu.im.obj.ObjectPool;

public class ProtobufEncoder implements ProtocolEncoder {

	public void dispose(IoSession arg0) throws Exception {

	}

	public void encode(IoSession session, Object obj, ProtocolEncoderOutput out) throws Exception {
		if (obj instanceof Message) {
			Message message = (Message) obj;
			int code = message.getCode();
			int length = message.getLength();
			byte[] data = message.getData();
			IoBuffer ioBuffer = IoBuffer.allocate(8 + data.length);
			ioBuffer.putInt(code);
			ioBuffer.putInt(length);
			ioBuffer.put(data);
			ioBuffer.flip();
			out.write(ioBuffer);
			out.flush();
			ObjectPool.getPool().back(message);
		}
	}

}
