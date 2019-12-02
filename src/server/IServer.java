package server;

import config.Configuration;
import queue.IExecutor;

public interface IServer<T> {
    T setup(Configuration configuration);

    T setExecutor(IExecutor executor);

    void start();
}
