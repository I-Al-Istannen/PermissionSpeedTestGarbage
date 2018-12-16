package me.ialistannen.simplepermissionspeedtester;

import org.bukkit.plugin.java.JavaPlugin;

public class SimplePermissionSpeedTester extends JavaPlugin {

  @Override
  public void onEnable() {
    getCommand("testPermissionSpeed").setExecutor(new TestCommand());
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
