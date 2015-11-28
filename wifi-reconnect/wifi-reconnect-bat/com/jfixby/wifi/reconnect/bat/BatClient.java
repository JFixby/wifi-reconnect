package com.jfixby.wifi.reconnect.bat;

import java.io.IOException;

import com.jfixby.cmns.api.log.L;
import com.jfixby.wifi.reconnect.ConnectionClient;
import com.jfixby.wifi.reconnect.PingStatus;

public class BatClient implements ConnectionClient {

	private String reset_connection_command;
	private String ping_command;

	public BatClient(String ping_command, String reset_command) {
		this.ping_command = ping_command;
		this.reset_connection_command = reset_command;
	}

	@Override
	public PingStatus pingServer() {

		String command_string = ping_command;
		ShellCommand cmd = new ShellCommand(command_string);
		BatPingStatus status = new BatPingStatus(cmd);
		cmd.execute();
		return status;

	}

	@Override
	public void resetConnection() {
		try {
			L.d("reset", reset_connection_command);
			Process process = Runtime.getRuntime().exec(reset_connection_command);
			int result = process.waitFor();
			L.d("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
