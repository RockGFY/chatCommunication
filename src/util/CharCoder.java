package util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

public class CharCoder {

    private static Charset charset = StandardCharsets.UTF_16;
    private static CharsetEncoder encoder = charset.newEncoder();
    private static CharsetDecoder decoder = charset.newDecoder();

    public static ByteBuffer getBufferFromString(String str) throws CharacterCodingException {
        return encoder.encode(CharBuffer.wrap(str));
    }

    public static String getStringFromBuffer(ByteBuffer buffer) throws CharacterCodingException {
        return decoder.decode(buffer).toString();
    }
}
