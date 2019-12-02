package session;

import java.nio.ByteBuffer;

public interface ISession {

    void read(ByteBuffer buffer);

    void write(ByteBuffer buffer);

    void close();

}
