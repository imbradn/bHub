package io.github.bradnn.events;

import io.github.bradnn.bHub;

public class EventManager {
    bHub plugin;
    public EventManager(bHub instance) {
        plugin = instance;
    }

    public EventManager(){

    }
    public void load() {
        plugin.getServer().getPluginManager().registerEvents(new EnderButt(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new DoubleJump(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new JoinMessages(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new JoinSpawn(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new LaunchpadEvents(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new NoInventoryUpdate(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new NoVoid(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerVisibility(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SelectorChecks(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SelectorItem(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new WorldChecks(plugin), plugin);
    }

}
