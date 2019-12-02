package protocol.communication;

import util.DataParser;

public abstract class AbstractPacket {

    public static final int MAX_SIZE = 4096;

    @Override
    public String toString() {
        return DataParser.parseObjectToStr(this) + "\r\n";
    }

}
