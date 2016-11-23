package utouu.im.utils;



public class GameUtil {
	public static byte[] intToBytes(int value) {
		byte[] src = new byte[4];
		src[3] = (byte) ((value >> 24) & 0xFF);
		src[2] = (byte) ((value >> 16) & 0xFF);
		src[1] = (byte) ((value >> 8) & 0xFF);
		src[0] = (byte) (value & 0xFF);
		return src;
	}

	public static byte[] intToBytes2(int value) {
		byte[] src = new byte[4];
		src[0] = (byte) ((value >> 24) & 0xFF);
		src[1] = (byte) ((value >> 16) & 0xFF);
		src[2] = (byte) ((value >> 8) & 0xFF);
		src[3] = (byte) (value & 0xFF);
		return src;
	}

	public static int bytesToInt(byte[] src, int offset) {
		int value;
		value = ((src[offset] & 0xFF) | ((src[offset + 1] & 0xFF) << 8)
				| ((src[offset + 2] & 0xFF) << 16) | ((src[offset + 3] & 0xFF) << 24));
		return value;
	}

	public static int bytesToInt2(byte[] src, int offset) {
		int value;
		value = (((src[offset] & 0xFF) << 24)
				| ((src[offset + 1] & 0xFF) << 16)
				| ((src[offset + 2] & 0xFF) << 8) | (src[offset + 3] & 0xFF));
		return value;
	}

	public static void revert(byte[] tempBytes) {
		for (int i = 0; i < tempBytes.length / 2; i++) {
			byte temp = tempBytes[i];
			tempBytes[i] = tempBytes[tempBytes.length - i - 1];
			tempBytes[tempBytes.length - i - 1] = temp;
		}
	}

	public static void copyOf(byte[] result, byte[] newDatas, byte[] oldDatas) {
		System.arraycopy(newDatas, 0, result, 0, newDatas.length);
		System.arraycopy(oldDatas, 0, result, newDatas.length, oldDatas.length);
	}
}
