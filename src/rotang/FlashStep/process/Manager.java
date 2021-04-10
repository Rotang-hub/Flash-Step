package rotang.FlashStep.process;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Manager 
{
	private boolean process = false;
	
	private List<Material> swords = Arrays.asList(Material.WOODEN_SWORD, Material.STONE_SWORD,
			Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD);
	
	public boolean getProcess() 
	{
		return process;
	}

	public void setProcess(boolean process) 
	{
		this.process = process;
	}
	
	//========================
	
	public boolean isSword(ItemStack item)
	{
		if(swords.contains(item.getType()))
		{
			return true;
		}
		
		return false;
	}
	
	public Entity getClosestEntity(Player player)
	{
		List<Entity> entities = player.getNearbyEntities(20.0, 20.0, 20.0);
		
		if(entities.isEmpty())
			return null;
		
		Entity closest = entities.get(0);
		
		for(Entity e : entities)
		{
			double closestDist = player.getLocation().distance(closest.getLocation());
			double eDist = player.getLocation().distance(e.getLocation());
			
			if(closestDist > eDist)
				closest = e;
		}
		
		return closest;
	}
	
	public void useFlashStep(Player player)
	{
		Entity entity = getClosestEntity(player);
		
		if(entity == null)
			return;
		
		Location eLoc = entity.getLocation();
		Location pLoc = player.getLocation();
		
		double rotY = Math.toRadians(eLoc.getYaw());
		double vecX = -Math.sin(rotY);
		double vecZ = Math.cos(rotY);
		
		Location targetPos = new Location(entity.getWorld(), eLoc.getX() - vecX, eLoc.getY(), eLoc.getZ() - vecZ, eLoc.getYaw(), eLoc.getPitch());
		
		player.teleport(targetPos);
		player.getWorld().spawnParticle(Particle.END_ROD, pLoc.getX(), pLoc.getY(), pLoc.getZ(), 10, 0.2, 0.3, 0.2);
	}
}
