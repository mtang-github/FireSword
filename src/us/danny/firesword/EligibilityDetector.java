package us.danny.firesword;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EligibilityDetector {
	private final String lore;
	private final Ticker ticker;
	
	public EligibilityDetector(int maxTick, String lore) {
		ticker = new Ticker(maxTick);
		this.lore = lore;
	}
	
	public void update() {
		ticker.tick();
	}
	
	public boolean isEligible(Player player) {
		if(ticker.peekIsTime()) {
			ItemStack item = player.getItemInHand();
			return isValidItem(item) && player.isBlocking();
		}
		return false;
	}
	
	private boolean isValidItem(ItemStack item) {
		if(item.hasItemMeta()) {
    		ItemMeta itemMeta = item.getItemMeta();
    		if(itemMeta.hasLore()) {
    			List<String> loreList = itemMeta.getLore();
    			return loreList.contains(lore);
    		}
    	}
    	return false;
	}
}
