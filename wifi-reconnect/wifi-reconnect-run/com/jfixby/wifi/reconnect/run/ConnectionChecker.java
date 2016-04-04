package com.jfixby.wifi.reconnect.run;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.RedJson;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.red.desktop.DesktopAssembler;
import com.jfixby.wifi.reconnect.ConnectionClient;
import com.jfixby.wifi.reconnect.PingStatus;
import com.jfixby.wifi.reconnect.bat.BatClient;

public class ConnectionChecker {

	public static void main(String[] args) throws IOException {
		DesktopAssembler.setup();
		Json.installComponent(new RedJson());

		Config cfg = readConfig();

		ConnectionClient client = new BatClient(cfg.PING_COMMAND, cfg.RESET_COMMAND);

		PingStatus ping_status = client.pingServer();

		while (true) {
			if (!ping_status.isLaunched()) {
				L.d("wait");
				Sys.sleep(2000);
			} else if (ping_status.isOK()) {
				L.d("ping_status", ping_status);
				Sys.sleep(2000);
			} else {
				L.d("ping_status", ping_status);
				L.d("resetConnection()");
				client.resetConnection();
				Sys.sleep(cfg.SLEEP_AFTER_RESET);
			}
		}
	}

	private static Config readConfig() throws IOException {
		File config_file = LocalFileSystem.ApplicationHome().child("config.cfg");
		String data = config_file.readToString();
		L.d("config", data);
		Config cfg = Json.deserializeFromString(Config.class, data);
		return cfg;
	}
}
