package com.jfixby.wifi.reconnect;

public interface ConnectionClient {

	PingStatus pingServer();

	void resetConnection();

}
