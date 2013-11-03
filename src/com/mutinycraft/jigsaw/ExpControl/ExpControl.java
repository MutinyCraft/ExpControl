package com.mutinycraft.jigsaw.ExpControl;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;

/**
 * User: Jigsaw
 */
public class ExpControl extends JavaPlugin implements Listener {

	private Logger log;
	private File configFile;
	private FileConfiguration config;
	private static final String VERSION = "1.2";
	private List<String> worldsToBlockExp;
	private String netherName;

	@Override
	public void onEnable() {
		log = this.getLogger();
		;

		configFile = new File(getDataFolder(), "config.yml");
		try {
			firstRun();
		} catch (Exception e) {
			e.printStackTrace();
		}
		config = new YamlConfiguration();
		loadYamls();
		setConfigOptions();

		getServer().getPluginManager().registerEvents(this, this);
		new ExpControlEventHandler(this);

		log.info(this.getName() + " v" + VERSION + " enabled!");
	}

	@Override
	public void onDisable() {
		log.info(this.getName() + " v" + VERSION + " disabled!");
	}

	public boolean isBlockedWorld(String worldName) {
		return worldsToBlockExp.contains(worldName);
	}

	public String getNetherName() {
		return netherName;
	}

	private void setConfigOptions() {
		worldsToBlockExp = config.getStringList("Blocked-Worlds");
		netherName = config.getString("Nether-Name", "world_nether");
	}

	public void saveYamls() {
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadYamls() {
		try {
			config.load(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void firstRun() throws Exception {
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			copy(getResource("config.yml"), configFile);
		}
	}

	private void copy(InputStream in, File file) {
		try {
			OutputStream fout = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				fout.write(buf, 0, len);
			}
			fout.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
