package rotang.FlashStep;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import rotang.FlashStep.process.Events;
import rotang.FlashStep.process.Manager;

public class FlashStep extends JavaPlugin
{
	private Manager manager;
	
	@Override
	public void onEnable()
	{
		if(manager != null)
			return;
		manager = new Manager();
		
		getCommand("fs").setTabCompleter(new CommandTab());
		getServer().getPluginManager().registerEvents(new Events(manager), this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if(args.length == 0)
			return false;
		
		if(label.equalsIgnoreCase("fs"))
		{
			if(args[0].equalsIgnoreCase("on"))
			{
				manager.setProcess(true);
			}
			
			if(args[0].equalsIgnoreCase("off"))
			{
				manager.setProcess(true);
			}
		}
		
		return true;
	}
}
