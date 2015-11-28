package com.jfixby.wifi.reconnect.bat;

import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.wifi.reconnect.PingStatus;

public class BatPingStatus implements PingStatus {

	@Override
	public String toString() {
		last_output = cmd.getLastOutputLine();
		return "PingStatus[" + last_output + "] " + isOK();
	}

	private ShellCommand cmd;
	private String last_output;

	public BatPingStatus(ShellCommand cmd) {
		this.cmd = cmd;
	}

	long last_ok = 0;

	@Override
	public boolean isOK() {
		last_output = cmd.getLastOutputLine();
		boolean isOK = last_output.contains("bytes=32 time");
		if (isOK) {
			last_ok = Sys.SystemTime().currentTimeMillis();
		}
		// L.d(last_output, isOK);
		return isOK;
	}

	@Override
	public boolean isLaunched() {
		return cmd.isLaunched();
	}
}
