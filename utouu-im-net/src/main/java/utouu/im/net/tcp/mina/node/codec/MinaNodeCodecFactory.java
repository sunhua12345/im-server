package utouu.im.net.tcp.mina.node.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MinaNodeCodecFactory implements ProtocolCodecFactory{

	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return nodeDecoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return nodeEncoder;
	}

	private final ProtobufNodeEncoder nodeEncoder = new ProtobufNodeEncoder();
	private final ProtobufNodeDecoder nodeDecoder = new ProtobufNodeDecoder();
}
