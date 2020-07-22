package io.github.bradnn.commands;

import io.github.bradnn.bHub;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
    private bHub plugin = bHub.getInstance();

    public CommandManager(){

    }

    public String main = "hub";
    public String help = "help";
    public String spawn = "spawn";
    public String setspawn = "setspawn";
    public String launchpad = "launchpad";

    public void setup(){
        plugin.getCommand(main).setExecutor(this);

        this.commands.add(new HelpCommand());
        this.commands.add(new SpawnCommand());
        this.commands.add(new SetspawnCommand());
        this.commands.add(new LaunchpadCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase(main)){
            if(args.length == 0){
                SubCommand target = this.get(help);
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.addAll(Arrays.asList(args));

                try{
                    target.onCommand(player,args);
                }catch (Exception e){
                    player.sendMessage(ChatColor.RED + "An error has occurred, check the console. Type /hub help for more info.");
                    e.printStackTrace();
                }
                return true;
            }

            SubCommand target = this.get(args[0]);

            if(target == null){
                player.sendMessage(ChatColor.RED + "Invalid Argument. Type /hub help for more info.");
                return true;
            }

            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.addAll(Arrays.asList(args));
            arrayList.remove(0);

            try{
                target.onCommand(player,args);
            }catch (Exception e){
                player.sendMessage(ChatColor.RED + "An error has occurred, check the console. Type /hub help for more info.");
                e.printStackTrace();
            }
        }
        return true;
    }

    private SubCommand get(String name){
        Iterator<SubCommand> subcommands = this.commands.iterator();

        while(subcommands.hasNext()){
            SubCommand scommand = (SubCommand) subcommands.next();

            if(scommand.name().equalsIgnoreCase(name)){
                return scommand;
            }

            String[] aliases;
            int length = (aliases = scommand.aliases()).length;

            for(int var5 = 0; var5 < length; ++var5){
                String alias = aliases[var5];
                if(name.equalsIgnoreCase(alias)){
                    return scommand;
                }

            }
        }
        return null;
    }
}
