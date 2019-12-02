package protocol.entity;

import util.DataParser;

public abstract class AbstractEntity {

    @Override
    public String toString() {
        return DataParser.parseObjectToStr(this);
    }

}
