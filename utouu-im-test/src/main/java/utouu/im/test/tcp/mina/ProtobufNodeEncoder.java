package utouu.im.test.tcp.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import utouu.im.net.tcp.mina.entity.Message;

public class ProtobufNodeEncoder implements ProtocolEncoder {

	@Override
	public void dispose(IoSession arg0) throws Exception {

	}

	@Override
	public void encode(IoSession session, Object obj, ProtocolEncoderOutput out)
			throws Exception {
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
		}
	}

}
