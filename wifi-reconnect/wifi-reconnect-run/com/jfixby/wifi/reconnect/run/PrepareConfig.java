package com.jfixby.wifi.reconnect.run;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.RedJson;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.red.desktop.DesktopSetup;

public class PrepareConfig {

	public static void main(String[] args) throws IOException {
		DesktopSetup.deploy();
		Json.installComponent(new RedJson());

		Config cfg = new Config();

		cfg.SLEEP_AFTER_RESET = 20000;
		cfg.RESET_COMMAND = "cmd /c start run.bat";
		cfg.PING_COMMAND = "ping 127.0.0.1 -t";

		File config_file = LocalFileSystem.ApplicationHome().child("config.cfg");
		String data = Json.serializeToString(cfg).toString();
		config_file.writeString(data);

		L.d(data);
	}
}
