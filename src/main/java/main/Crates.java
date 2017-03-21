package main;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.sun.org.apache.bcel.internal.generic.NEW;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_11_R1.EnumParticle;
import net.minecraft.server.v1_11_R1.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

/**
 * Created by Cory on 28/02/2017.
 */
public class Crates {

    private final MineShards mineShards;
    public final static HashSet<SchedulerTask> opening = new HashSet<SchedulerTask>();
    private int uniqueTask;

    ArrayList<ItemStack> commonCrate = new ArrayList<ItemStack>();
    ArrayList<ItemStack> uncommonCrate = new ArrayList<ItemStack>();
    ArrayList<ItemStack> rareCrate = new ArrayList<ItemStack>();
    ArrayList<ItemStack> epicCrate = new ArrayList<ItemStack>();
    ArrayList<ItemStack> legendaryCrate = new ArrayList<ItemStack>();

    public ArrayList<ItemStack> getCrateAsList(CrateType ct) {
        switch(ct) {
            case COMMON:
                return this.commonCrate;
            case UNCOMMON:
                return this.uncommonCrate;
            case RARE:
                return this.rareCrate;
            case EPIC:
                return this.epicCrate;
            case LEGENDARY:
                return this.legendaryCrate;
            default: return null;
        }
    }

    public Crates(MineShards instance) {
        this.mineShards = instance;

        //*commons*//
        commonCrate.add(MineShards.genCustomItem(Material.STONE_SWORD, ChatColor.DARK_GRAY + "Hand Crafted Stone Dagger", "Common Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 2),  new EnchantWrapper(Enchantment.DURABILITY, 1)));
        commonCrate.add(MineShards.genCustomItem(Material.IRON_PICKAXE, ChatColor.GRAY + "Notched Pickaxe", "Common Crate Treasure", new EnchantWrapper(Enchantment.LOOT_BONUS_BLOCKS, 1)));
        commonCrate.add(new ItemStack(Material.COBBLESTONE, 64));
        commonCrate.add(MineShards.genCustomItem(Material.STONE_PICKAXE, ChatColor.DARK_GREEN + "Poor Tim's Pickaxe", "Common Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 3), new EnchantWrapper(Enchantment.DURABILITY, 3)));
        commonCrate.add(MineShards.genCustomItem(Material.STONE_AXE, ChatColor.DARK_GREEN + "Poor Tim's Axe", "Common Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 3), new EnchantWrapper(Enchantment.DURABILITY, 3)));
        commonCrate.add(new ItemStack(Material.COOKED_BEEF, 16));
        commonCrate.add(MineShards.genCustomItem(Material.LEATHER_LEGGINGS, ChatColor.YELLOW + "Forduz's Leggings", "Common Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3)));
        commonCrate.add(MineShards.genCustomItem(Material.LEATHER_HELMET, ChatColor.YELLOW + "Forduz's Helmet", "Common Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3)));
        commonCrate.add(MineShards.genCustomItem(Material.LEATHER_CHESTPLATE, ChatColor.YELLOW + "Forduz's Chestpiece", "Common Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3)));
        commonCrate.add(MineShards.genCustomItem(Material.LEATHER_BOOTS, ChatColor.YELLOW + "Forduz's Boots", "Common Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3)));
        commonCrate.add(MineShards.genCustomItem(Material.IRON_SWORD, ChatColor.YELLOW + "Shiny Backstabber", "Common Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 1)));
        commonCrate.add(new ItemStack(Material.BOW, 1));
        commonCrate.add(new ItemStack(Material.ARROW, 12));
        commonCrate.add(MineShards.genCustomItem(Material.STONE_SWORD, ChatColor.DARK_AQUA + "Polish Blade", "Common Crate Treasure", new EnchantWrapper(Enchantment.LOOT_BONUS_MOBS, 1)));
        commonCrate.add(MineShards.genCustomItem(Material.STONE_SWORD, ChatColor.RED + "Afterburner", "Common Crate Treasure", new EnchantWrapper(Enchantment.FIRE_ASPECT, 1)));
        ItemStack ganker = MineShards.genCustomItem(Material.DIAMOND_SWORD, ChatColor.RED + "Ganker", "Common Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 5));
        ganker.setDurability((short)1561);
        commonCrate.add(ganker);
        commonCrate.add(MineShards.genCustomItem(Material.FISHING_ROD, ChatColor.YELLOW + "Catcha", "Common Crate Treasure", new EnchantWrapper(Enchantment.LURE, 3)));

        //*Uncommons*//
        uncommonCrate.add(new ItemStack(Material.LAPIS_BLOCK, 10));
        uncommonCrate.add(new ItemStack(Material.DIAMOND, 3));
        uncommonCrate.add(new ItemStack(Material.REDSTONE, 64));
        uncommonCrate.add(MineShards.genCustomItem(Material.BOW, ChatColor.DARK_GREEN + "Long Shot", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.ARROW_DAMAGE, 1)));
        uncommonCrate.add(new ItemStack(Material.COOKED_BEEF, 32));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_SWORD, ChatColor.YELLOW + "Blood Lust", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 3)));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_CHESTPLATE, ChatColor.AQUA + "Lightweight", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_FALL, 4)));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_LEGGINGS, ChatColor.RED + "Spiker", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.THORNS, 2)));
        uncommonCrate.add(new ItemStack(Material.ARROW, 32));
        uncommonCrate.add(getTippedArrow(16, PotionType.SLOWNESS));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_SWORD, ChatColor.DARK_PURPLE + "'Do you like to play with fire?'", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.FIRE_ASPECT, 1)));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_PICKAXE, ChatColor.YELLOW + "Lucky Miner", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.LOOT_BONUS_BLOCKS, 2)));
        uncommonCrate.add(new ItemStack(Material.IRON_BLOCK, 3));
        uncommonCrate.add(new ItemStack(Material.GOLD_ORE, 16));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_PICKAXE, ChatColor.LIGHT_PURPLE + "Hastely Miner", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 4)));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_PICKAXE, ChatColor.RED + "Trusty Pickaxe", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 2), new EnchantWrapper(Enchantment.DURABILITY, 2)));

        //*Rares*//
        /*Copyright Unnyman*/
        rareCrate.add(MineShards.genCustomItem(Material.BOW, ChatColor.GREEN + "Zachary", "Rare Crate Treasure", new EnchantWrapper(Enchantment.ARROW_KNOCKBACK, 1), new EnchantWrapper(Enchantment.ARROW_DAMAGE, 2)));
        rareCrate.add(MineShards.genCustomItem(Material.IRON_SWORD, ChatColor.LIGHT_PURPLE + "Rend", "Rare Crate Treasure", new EnchantWrapper(Enchantment.SWEEPING_EDGE, 2), new EnchantWrapper(Enchantment.DAMAGE_ALL, 3)));
        rareCrate.add(MineShards.genCustomItem(Material.IRON_CHESTPLATE, ChatColor.GREEN + "Hora's Chestpiece", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 2)));
        rareCrate.add(new ItemStack(Material.GOLD_BLOCK, 5));
        rareCrate.add(new ItemStack(Material.DIAMOND_BLOCK, 3));
        rareCrate.add(MineShards.genCustomItem(Material.IRON_SWORD, ChatColor.RED + "Chugas", "Rare Crate Treasure", new EnchantWrapper(Enchantment.FIRE_ASPECT, 2)));
        rareCrate.add(MineShards.genCustomItem(Material.IRON_LEGGINGS, ChatColor.GREEN + "Hora's Leggings", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 2)));


        //*Epics*//
        epicCrate.add(MineShards.genCustomItem(Material.IRON_SWORD, ChatColor.RED + "Chugas", "Epic Crate Treasure", new EnchantWrapper(Enchantment.FIRE_ASPECT, 2)));

        //*Legendary*//
        legendaryCrate.add(MineShards.genCustomItem(Material.IRON_SWORD, ChatColor.RED + "Chugas", "Legendary Crate Treasure", new EnchantWrapper(Enchantment.FIRE_ASPECT, 2)));
    }

    private ItemStack getTippedArrow(int amt, PotionType pt) {
        ItemStack i = new ItemStack(Material.TIPPED_ARROW, amt);
        ItemMeta im = i.getItemMeta();
        PotionMeta pm = (PotionMeta) im;
        pm.setBasePotionData(new PotionData(pt));
        i.setItemMeta(im);
        return i;
    }

    public void openCrate(final Player p, CrateType crateType) {
        String name = crateType.toString().charAt(0) + crateType.toString().substring(1).toLowerCase();
        final Inventory spinner = Bukkit.createInventory(null, 27,  name + " Crate");
        p.openInventory(spinner);
        SchedulerTask scheduler = new SchedulerTask(p, spinner, crateType, this);
        scheduler.setId(Bukkit.getScheduler().scheduleSyncRepeatingTask(mineShards, scheduler, 0L, 1L));
        this.opening.add(scheduler);
    }

    public static ItemStack[] push(Inventory i, ItemStack newItem) {
        ItemStack[] contents = i.getContents();
        for(int o = 10; o <= 17; o++) {
            if (o == 17) {
                contents[o - 1] = contents[o];
                contents[o] = newItem;
            }
                contents[o-1] = contents[o];
        }
        return contents;
    }

}

enum CrateType {
    COMMON, UNCOMMON, RARE, EPIC, LEGENDARY;
}

class SchedulerTask implements Runnable {
    private final Player p;
    private final Inventory spinner;
    private final ArrayList<ItemStack> crate;
    private final String crateName;
    boolean running = true;
    private int id;
    private final Random random = new Random();
    int counter = 0;
    int tick = 0;

    public SchedulerTask(Player player, Inventory inventory, CrateType crateType, Crates inst) {
        spinner = inventory;
        ItemStack white = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.WHITE.getDyeData());
        ItemStack yellow = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GREEN.getDyeData());
        for(int i = 0; i <= 8; i++) {
            if(i == 4) continue;
            spinner.setItem(i, white);
        }
        for(int i = 17; i <= 26; i++) {
            if(i == 22) continue;
            spinner.setItem(i, white);
        }
        spinner.setItem(4, yellow);
        spinner.setItem(22, yellow);
        p = player;
        this.crate = inst.getCrateAsList(crateType);
        this.crateName = crateType.toString().charAt(0) + crateType.toString().substring(1).toLowerCase();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return p;
    }

    public void run() {
        if(counter == 150) {
            ItemStack i = spinner.getItem(13);
            p.getInventory().addItem(i);
            String unboxed;
            if(i.getItemMeta().getDisplayName() != null) {
                unboxed = i.getItemMeta().getDisplayName();
            } else {
                unboxed = i.getAmount() + " " + i.getType().name();
            }
            Bukkit.broadcastMessage(p.getDisplayName() + ChatColor.GOLD + " unboxed " + ChatColor.YELLOW +  unboxed + ChatColor.GOLD + " from their " + ChatColor.YELLOW + this.crateName + " Crate" + ChatColor.GOLD + "!");
            Bukkit.getScheduler().cancelTask(getId());
            Crates.opening.remove(this);
            p.closeInventory();
        }
        counter++;
        tick++;
        if(counter >= 25 && counter < 50) {
            if(tick < 2) {
                return;
            } else tick = 0;
        } else if(counter >= 50 && counter < 100) {
            if(tick < 4) {
                return;
            } else tick = 0;
        } else if(counter >= 100 && counter < 140) {
            if(tick < 6) {
                return;
            } else tick = 0;
        } else if(counter >= 140) {
            if(tick < 8) {
                return;
            } else tick = 0;
        }
        int rdm = random.nextInt(crate.size());
        ItemStack i = crate.get(rdm);
        spinner.setContents(Crates.push(spinner, i));
    }

    public void forceEnd() {
        Bukkit.getServer().getScheduler().cancelTask(getId());
        int rdm = random.nextInt(crate.size());
        ItemStack i = crate.get(rdm);
        p.getInventory().addItem(i);
        String unboxed;
        if(i.getItemMeta().getDisplayName() != null) {
            unboxed = i.getItemMeta().getDisplayName();
        } else {
            unboxed = i.getAmount() + " " + i.getType().name();
        }
        Bukkit.broadcastMessage(p.getDisplayName() + ChatColor.GOLD + " unboxed " + ChatColor.YELLOW +  unboxed + ChatColor.GOLD + " from their " + ChatColor.YELLOW + this.crateName + " Crate" + ChatColor.GOLD + "!");
        Crates.opening.remove(this);
    }
}
