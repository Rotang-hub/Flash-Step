package rotang.FlashStep.process;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener
{
	Manager manager;
	
	public Events(Manager manager) 
	{
		this.manager = manager;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		Action action = event.getAction();
		
		if(!manager.getProcess())
			return;
		
		if(item == null || item.getType().equals(Material.AIR))
			return;
		
		if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK))
		{
			if(event.getHand().equals(EquipmentSlot.HAND))
			{
				if(manager.isSword(item))
				{
					manager.useFlashStep(player);
				}
				
				else
					return;
			}
		}
	}
}
