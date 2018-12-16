package me.ialistannen.simplepermissionspeedtester;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (args.length != 3) {
      sender.sendMessage(ChatColor.RED + "Usage: " + label + " <length> <executions> <strategy>");
      return true;
    }
    int length;
    try {
      length = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      sender.sendMessage(ChatColor.RED + args[0] + " is no number");
      return true;
    }

    int executions;
    try {
      executions = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
      sender.sendMessage(ChatColor.RED + args[1] + " is no number");
      return true;
    }

    PermissionGenerationStrategy strategy;
    try {
      strategy = SimplePermissionGenerationStrategy
          .valueOf(args[2].toUpperCase());
    } catch (IllegalArgumentException e) {
      String valid = Arrays.stream(SimplePermissionGenerationStrategy.values())
          .map(Enum::name)
          .collect(Collectors.joining(", "));
      sender.sendMessage(
          ChatColor.RED + args[2].toUpperCase() + " is no strategy. Valid are: " + valid
      );
      return true;
    }

    long start = System.nanoTime();

    boolean allSucceded = true;
    for (int i = 0; i < executions; i++) {
      String permission = strategy.generatePermission(length);
      System.out.println(permission);
      if (!sender.hasPermission(permission)) {
        allSucceded = false;
      }
    }

    long end = System.nanoTime();

    long diff = end - start;

    sender.sendMessage(
        ChatColor.AQUA + "Took "
            + ChatColor.RED + TimeUnit.NANOSECONDS.toMillis(diff)
            + ChatColor.AQUA + "ms to process "
            + ChatColor.RED + executions
            + ChatColor.AQUA + " executions of length "
            + ChatColor.RED + length
            + ChatColor.AQUA + ". (Result: " + allSucceded + ")"
    );

    return true;
  }
}
