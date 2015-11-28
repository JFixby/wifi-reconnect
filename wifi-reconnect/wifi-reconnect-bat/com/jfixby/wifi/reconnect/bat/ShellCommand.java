package com.jfixby.wifi.reconnect.bat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jfixby.cmns.api.log.L;

public class ShellCommand {

	private String command;

	public ShellCommand(String command) {
		this.command = command;
	}

	public void execute() {
		Thread t = new Thread() {
			public void run() {
				running = true;
				executeCommand(command);
				running = false;
			}
		};
		t.start();
	}

	boolean running = false;

	private String executeCommand(String command) {
		L.d("executeCommand", command);

		StringBuffer output = new StringBuffer();

		Process p;
		try {

			p = Runtime.getRuntime().exec(command);
			// p.waitFor();
			InputStream is = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader buff = new BufferedReader(reader);

			while (this.isRunning()) {
				last_line = buff.readLine();
//				L.d("", last_line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

	String last_line = "";

	public boolean isRunning() {
		return running;
	}

	public String getLastOutputLine() {
		return last_line;
	}

	public boolean isLaunched() {
		return running;
	}

}
