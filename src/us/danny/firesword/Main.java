package us.danny.firesword;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin {
	
	public Main() {
		//do nothing
	}
	
	private void shootFlame(Player player) {
		Location location = player.getLocation();
		Vector direction = location.getDirection();
		Vector velocity = direction.clone();
		velocity.multiply(Config.SPEED);
		velocity.setX(velocity.getX() + randomPlusMinus(Config.COMPONENT_RAND));
		velocity.setY(velocity.getY() + randomPlusMinus(Config.COMPONENT_RAND));
		velocity.setZ(velocity.getZ() + randomPlusMinus(Config.COMPONENT_RAND));
		
		SmallFireball fireball = player.launchProjectile(
				SmallFireball.class, 
				velocity
		);
		
		fireball.setOp(false);
		fireball.setBounce(false);
		fireball.setShooter(player);
		fireball.setVelocity(velocity);
	}
	
	private double randomPlusMinus(double absMax) {
		return Math.random() * 2.0d * absMax - absMax;
	}
	
	@Override
	public void onEnable() {
		EligibilityDetector eligibilityDetector = new EligibilityDetector(
				Config.TICK_PERIOD,
				Config.LORE
		);
		new BukkitRunnable(){
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(eligibilityDetector.isEligible(player)) {
						shootFlame(player);
					}
				}
				eligibilityDetector.update();
			}
		}.runTaskTimer(this, 0, 1);
		System.out.println(Config.PLUGIN_NAME + " enabled");
	}
	
	@Override
	public void onDisable() {
		System.out.println(Config.PLUGIN_NAME + " disabled");
	}
}
