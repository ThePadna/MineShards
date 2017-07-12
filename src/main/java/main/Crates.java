package main;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

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
    ArrayList<ItemStack> voteCrate = new ArrayList<ItemStack>();

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
            case VOTE:
                return this.voteCrate;
            default: return null;
        }
    }

    public Crates(MineShards instance) {
        this.mineShards = instance;

        //*commons*//
        commonCrate.add(mineShards.getCrate(CrateType.UNCOMMON));
        commonCrate.add(createMoneyNote(500));
        commonCrate.add(createMoneyNote(1000));
        commonCrate.add(MineShards.genCustomItem(Material.BOW, ChatColor.GREEN + "'Feel the Power'", "Common Crate Treasure", new EnchantWrapper(Enchantment.ARROW_DAMAGE, 1)));
        commonCrate.add(MineShards.genCustomItem(Material.STONE_PICKAXE, ChatColor.YELLOW + "Prisoner's Fortune", "Common Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED,2 ),
                new EnchantWrapper(Enchantment.DURABILITY, 1), new EnchantWrapper(Enchantment.LOOT_BONUS_BLOCKS, 1)));
        commonCrate.add(MineShards.genCustomItem(Material.STONE_PICKAXE, ChatColor.RED + "UPGRADE!", "Common Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 3)));
        commonCrate.add(MineShards.genCustomItem(Material.IRON_PICKAXE, ChatColor.WHITE + "Iron Fiend", "Common Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 2),
                new EnchantWrapper(Enchantment.DURABILITY, 3)));
        commonCrate.add(MineShards.genCustomItem(Material.IRON_PICKAXE, ChatColor.RED + "Hastely Miner", "Common Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 3)));
        commonCrate.add(MineShards.genCustomItem(Material.STONE_AXE, ChatColor.BLUE + "Lumberjack's upgrade", "Common Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 2),
                new EnchantWrapper(Enchantment.DURABILITY, 2)));
        commonCrate.add(MineShards.genCustomItem(Material.STONE_PICKAXE, ChatColor.RED + "UPGRADE!", "Common Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 3)));
        commonCrate.add(MineShards.genCustomItem(Material.IRON_AXE, ChatColor.GREEN + "Lumberjack's precious", "Common Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 1),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        commonCrate.add(MineShards.genCustomItem(Material.STONE_SWORD, ChatColor.YELLOW + "Toothpick", "Common Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 2),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        commonCrate.add(MineShards.genCustomItem(Material.IRON_SWORD, ChatColor.DARK_RED + "Blood Lust", "Common Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 1),
                new EnchantWrapper(Enchantment.FIRE_ASPECT, 1)));
        commonCrate.add(MineShards.genCustomItem(Material.FISHING_ROD, ChatColor.DARK_GREEN + "Fisherman's Friend", "Common Crate Treasure", new EnchantWrapper(Enchantment.LURE, 1),
                new EnchantWrapper(Enchantment.DURABILITY, 2), new EnchantWrapper(Enchantment.LUCK, 1)));

        commonCrate.add(new ItemStack(Material.DIAMOND_PICKAXE, 1));
        commonCrate.add(new ItemStack(Material.DIAMOND_AXE, 1));
        commonCrate.add(new ItemStack(Material.COOKED_BEEF, 32));
        commonCrate.add(new ItemStack(Material.ARROW, 32));
        commonCrate.add(new ItemStack(Material.COAL, 48));
        commonCrate.add(new ItemStack(Material.IRON_INGOT, 16));
        commonCrate.add(new ItemStack(Material.DIAMOND, 3));

        commonCrate.add(MineShards.genCustomItem(Material.LEATHER_HELMET, ChatColor.BLUE + "Upgraded Prisoner Helmet", "Common Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 2)));
        commonCrate.add(MineShards.genCustomItem(Material.LEATHER_CHESTPLATE, ChatColor.BLUE + "Upgraded Prisoner Chestpiece", "Common Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 2)));
        commonCrate.add(MineShards.genCustomItem(Material.LEATHER_LEGGINGS, ChatColor.BLUE + "Upgraded Prisoner Leggings", "Common Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 2)));
        commonCrate.add(MineShards.genCustomItem(Material.LEATHER_BOOTS, ChatColor.BLUE + "Upgraded Prisoner Boots", "Common Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 2)));

        //*Uncommons*//
        uncommonCrate.add(mineShards.getCrate(CrateType.RARE));
        uncommonCrate.add(createMoneyNote(1000));
        uncommonCrate.add(createMoneyNote(1750));
        uncommonCrate.add(MineShards.genCustomItem(Material.BOW, ChatColor.GOLD + "'Do you like to play with Fire?'", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.ARROW_DAMAGE, 3),
                new EnchantWrapper(Enchantment.ARROW_FIRE, 1)));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_PICKAXE, ChatColor.DARK_BLUE + "Sturdy Pickaxe", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 2), new EnchantWrapper(Enchantment.LOOT_BONUS_BLOCKS, 1)));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_PICKAXE, ChatColor.DARK_GREEN + "Efficient Miner", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 4),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_PICKAXE, ChatColor.LIGHT_PURPLE + "Lucky Miner", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.LOOT_BONUS_BLOCKS, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        uncommonCrate.add(MineShards.genCustomItem(Material.DIAMOND_PICKAXE, ChatColor.AQUA + "Shiiiny...", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 1),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_SWORD, ChatColor.WHITE + "Iron Swordsman", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 3),
                new EnchantWrapper(Enchantment.KNOCKBACK, 1)));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_SWORD, ChatColor.RED + "Fire Starter", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 2),
                new EnchantWrapper(Enchantment.FIRE_ASPECT, 1)));


        uncommonCrate.add(new ItemStack(Material.EXP_BOTTLE, 16));
        uncommonCrate.add(MineShards.genCustomPotion(PotionType.REGEN, 1, 1, false, true));
        uncommonCrate.add(new ItemStack(Material.COOKED_BEEF, 32));
        uncommonCrate.add(new ItemStack(Material.ARROW, 64));
        uncommonCrate.add(new ItemStack(Material.COAL, 64));
        uncommonCrate.add(new ItemStack(Material.IRON_INGOT, 32));
        uncommonCrate.add(new ItemStack(Material.GOLD_INGOT, 32));
        uncommonCrate.add(new ItemStack(Material.DIAMOND, 9));

        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_HELMET, ChatColor.GRAY + "Hardened Prisoner's Helmet", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 2),
                new EnchantWrapper(Enchantment.THORNS, 2)));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_CHESTPLATE, ChatColor.GRAY + "Hardened Prisoner's Chestpiece", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 2),
                new EnchantWrapper(Enchantment.THORNS, 2)));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_LEGGINGS, ChatColor.GRAY + "Hardened Prisoner's Leggings", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 2),
                new EnchantWrapper(Enchantment.THORNS, 2)));
        uncommonCrate.add(MineShards.genCustomItem(Material.IRON_BOOTS, ChatColor.GRAY + "Hardened Prisoner's Boots", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 2),
                new EnchantWrapper(Enchantment.THORNS, 2)));

        uncommonCrate.add(MineShards.genCustomItem(Material.DIAMOND_CHESTPLATE, ChatColor.AQUA + "Protector", "Uncommon Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL,1),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        //*Rares*//
        rareCrate.add(mineShards.getCrate(CrateType.EPIC));
        rareCrate.add(createMoneyNote(2500));
        rareCrate.add(createMoneyNote(3250));

        rareCrate.add(MineShards.genCustomItem(Material.BOW, ChatColor.YELLOW + "Long Bow", "Rare Crate Treasure", new EnchantWrapper(Enchantment.ARROW_DAMAGE, 4),
                new EnchantWrapper(Enchantment.ARROW_FIRE, 1), new EnchantWrapper(Enchantment.DURABILITY, 2)));
        rareCrate.add(MineShards.genCustomItem(Material.DIAMOND_PICKAXE, ChatColor.DARK_GREEN + "Notched Pickaxe", "Rare Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 2),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        rareCrate.add(MineShards.genCustomItem(Material.DIAMOND_PICKAXE, ChatColor.GRAY + "Stolen Pickaxe", "Rare Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 1),
                new EnchantWrapper(Enchantment.LOOT_BONUS_BLOCKS, 1)));
        rareCrate.add(MineShards.genCustomItem(Material.DIAMOND_PICKAXE, ChatColor.GOLD + "Mine Smasher", "Rare Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 3)));
        rareCrate.add(MineShards.genCustomItem(Material.DIAMOND_AXE, ChatColor.YELLOW + "Beheader", "Rare Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 2),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        rareCrate.add(MineShards.genCustomItem(Material.DIAMOND_AXE, ChatColor.DARK_RED + "Executioner", "Rare Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 1)));
        rareCrate.add(MineShards.genCustomItem(Material.DIAMOND_AXE, ChatColor.RED + "Tim's Stolen Pickaxe", "Rare Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 4)));
        rareCrate.add(MineShards.genCustomItem(Material.IRON_SWORD, ChatColor.LIGHT_PURPLE + "Sharpened Blade", "Rare Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 4)));
        rareCrate.add(MineShards.genCustomItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Nemesis", "Rare Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 2),
                new EnchantWrapper(Enchantment.FIRE_ASPECT, 1)));

        rareCrate.add(MineShards.genCustomItem(Material.IRON_HELMET, ChatColor.DARK_GRAY + "Durable Prisoner's Helmet", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 2)));
        rareCrate.add(MineShards.genCustomItem(Material.IRON_CHESTPLATE, ChatColor.BLUE + "Durable Prisoner's Chestpiece", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 2)));
        rareCrate.add(MineShards.genCustomItem(Material.IRON_LEGGINGS, ChatColor.BLUE + "Durable Prisoner's Leggings", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 2)));
        rareCrate.add(MineShards.genCustomItem(Material.IRON_BOOTS, ChatColor.BLUE + "Durable Prisoner's Boots", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 2)));

        rareCrate.add(MineShards.genCustomItem(Material.DIAMOND_HELMET, ChatColor.AQUA + "Shiny Prisoner's Helmet", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 1),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        rareCrate.add(MineShards.genCustomItem(Material.DIAMOND_CHESTPLATE, ChatColor.AQUA + "Shiny Prisoner's Chestpiece", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 1),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        rareCrate.add(MineShards.genCustomItem(Material.DIAMOND_LEGGINGS, ChatColor.AQUA + "Shiny Prisoner's Leggings", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 1),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        rareCrate.add(MineShards.genCustomItem(Material.DIAMOND_BOOTS, ChatColor.AQUA + "Shiny Prisoner's Boots", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 1),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));

        rareCrate.add(new ItemStack(Material.COOKED_BEEF, 64));
        rareCrate.add(new ItemStack(Material.COAL, 64));
        rareCrate.add(new ItemStack(Material.IRON_INGOT, 48));
        rareCrate.add(new ItemStack(Material.GOLD_INGOT, 48));
        rareCrate.add(new ItemStack(Material.DIAMOND_BLOCK, 4));
        rareCrate.add(new ItemStack(Material.EXP_BOTTLE, 32));
        rareCrate.add(MineShards.genCustomPotion(PotionType.REGEN, 1, 2, false, true));
        rareCrate.add(MineShards.genCustomPotion(PotionType.FIRE_RESISTANCE, 1, 1, false, false));
        rareCrate.add(new ItemStack(Material.GOLDEN_APPLE, 1));

        //*Epics*//
        epicCrate.add(createMoneyNote(4000));
        epicCrate.add(createMoneyNote(5000));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_PICKAXE, ChatColor.AQUA + "Solid Pickaxe", "Epic Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 2)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_PICKAXE, ChatColor.GREEN + "Mine Looter", "Epic Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 2),
                new EnchantWrapper(Enchantment.LOOT_BONUS_BLOCKS, 2)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_PICKAXE, ChatColor.RED + "'Blessed with a Curse'", "Epic Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 4)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_AXE, ChatColor.LIGHT_PURPLE + "Head Hunter", "Epic Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 2)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_AXE, ChatColor.DARK_BLUE + "'Jack of all Trades'", "Epic Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 2),
                new EnchantWrapper(Enchantment.DURABILITY, 2), new EnchantWrapper(Enchantment.DAMAGE_ALL, 2)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_AXE, ChatColor.YELLOW + "Lumberjack's Assistant", "Epic Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 5)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_SWORD, ChatColor.GOLD + "Blade of Flames", "Epic Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 2),
                new EnchantWrapper(Enchantment.FIRE_ASPECT, 2), new EnchantWrapper(Enchantment.KNOCKBACK, 1)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_SWORD, ChatColor.DARK_RED + "Head Hunter", "Epic Crate Treasure", new EnchantWrapper(Enchantment.DAMAGE_ALL, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 2)));

        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_HELMET, ChatColor.RED + "Shiny Prisoner's Helmet", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 2),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_CHESTPLATE, ChatColor.RED + "Shiny Prisoner's Chestpiece", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 2),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_LEGGINGS, ChatColor.RED + "Shiny Prisoner's Leggings", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 2),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_BOOTS, ChatColor.RED + "Shiny Prisoner's Boots", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 2),
                new EnchantWrapper(Enchantment.DURABILITY, 1)));

        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_HELMET, ChatColor.DARK_RED + "Panzersoldat's Helmet", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_CHESTPLATE, ChatColor.DARK_RED + "Panzersoldat's Chestpiece", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_LEGGINGS, ChatColor.DARK_RED + "Panzersoldat's Leggings", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3)));
        epicCrate.add(MineShards.genCustomItem(Material.DIAMOND_BOOTS, ChatColor.DARK_RED + "Panzersoldat's Boots", "Rare Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 3)));

        epicCrate.add(new ItemStack(Material.EXP_BOTTLE, 48));
        epicCrate.add(MineShards.genCustomPotion(PotionType.REGEN, 2, 2, false, false));
        epicCrate.add(new ItemStack(Material.DIAMOND_BLOCK, 4));
        epicCrate.add(new ItemStack(Material.IRON_BLOCK, 8));
        epicCrate.add(new ItemStack(Material.GOLD_BLOCK, 6));
        epicCrate.add(new ItemStack(Material.GOLDEN_APPLE, 3));

        //*Legendary*//
        legendaryCrate.add(createMoneyNote(25000));
        legendaryCrate.add(createMoneyNote(50000));
        legendaryCrate.add(MineShards.genCustomItem(Material.BOW, ChatColor.GOLD + "Divine Bow", "Legendary Crate Treasure", new EnchantWrapper(Enchantment.ARROW_DAMAGE, 5),
                new EnchantWrapper(Enchantment.ARROW_INFINITE, 1), new EnchantWrapper(Enchantment.ARROW_KNOCKBACK, 2), new EnchantWrapper(Enchantment.ARROW_FIRE, 2),
                new EnchantWrapper(Enchantment.DURABILITY, 3)));
        legendaryCrate.add(MineShards.genCustomItem(Material.DIAMOND_PICKAXE, ChatColor.DARK_PURPLE + "Cursed Pickaxe", "Legendary Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 5),
                new EnchantWrapper(Enchantment.SILK_TOUCH, 1), new EnchantWrapper(Enchantment.LOOT_BONUS_BLOCKS, 3),
                new EnchantWrapper(Enchantment.DURABILITY, 3)));
        legendaryCrate.add(MineShards.genCustomItem(Material.DIAMOND_AXE, ChatColor.GOLD + "'Axe of the Gods'", "Legendary Crate Treasure", new EnchantWrapper(Enchantment.DIG_SPEED, 5),
                new EnchantWrapper(Enchantment.DAMAGE_ALL, 5), new EnchantWrapper(Enchantment.FIRE_ASPECT, 2),
                new EnchantWrapper(Enchantment.DURABILITY, 3)));
        legendaryCrate.add(MineShards.genCustomItem(Material.FISHING_ROD, ChatColor.DARK_AQUA + "Fisherman's Fortune", "Legendary Crate Treasure", new EnchantWrapper(Enchantment.LURE, 3),
                new EnchantWrapper(Enchantment.LUCK, 3), new EnchantWrapper(Enchantment.DURABILITY, 3)));

        legendaryCrate.add(MineShards.genCustomItem(Material.DIAMOND_HELMET, ChatColor.GOLD + "Divine Helmet", "Legendary Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 4),
                new EnchantWrapper(Enchantment.DURABILITY, 3),
                new EnchantWrapper(Enchantment.THORNS, 2)));
        legendaryCrate.add(MineShards.genCustomItem(Material.DIAMOND_CHESTPLATE, ChatColor.GOLD + "Divine Chestpiece", "Legendary Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 4),
                new EnchantWrapper(Enchantment.DURABILITY, 3),
                new EnchantWrapper(Enchantment.THORNS, 2)));
        legendaryCrate.add(MineShards.genCustomItem(Material.DIAMOND_LEGGINGS, ChatColor.GOLD + "Divine Leggings", "Legendary Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 4),
                new EnchantWrapper(Enchantment.DURABILITY, 3),
                new EnchantWrapper(Enchantment.THORNS, 2)));
        legendaryCrate.add(MineShards.genCustomItem(Material.DIAMOND_BOOTS, ChatColor.GOLD + "Divine Boots", "Legendary Crate Treasure", new EnchantWrapper(Enchantment.PROTECTION_ENVIRONMENTAL, 4),
                new EnchantWrapper(Enchantment.DURABILITY, 3),
                new EnchantWrapper(Enchantment.THORNS, 2)));

        legendaryCrate.add(new ItemStack(Material.GOLD_BLOCK, 64));
        legendaryCrate.add(new ItemStack(Material.DIAMOND_BLOCK, 64));
        legendaryCrate.add(new ItemStack(Material.IRON_BLOCK, 64));
        legendaryCrate.add(wrapWithAmt(new ItemStack(Material.COAL_BLOCK, 128), 128));
        legendaryCrate.add(wrapWithAmt(new ItemStack(Material.EXP_BOTTLE, 256), 256));
        legendaryCrate.add(MineShards.genCustomPotion(PotionType.REGEN, 2, 10, false, false));
        legendaryCrate.add(new ItemStack(Material.GOLDEN_APPLE, 15));
        legendaryCrate.add(new ItemStack(Material.GOLDEN_APPLE, 3, (short)1));


    }

    private ItemStack wrapWithAmt(ItemStack i, int amt) {
        ItemMeta itemMeta = i.getItemMeta();
        itemMeta.setDisplayName(amt + " " + i.getType().name());
        i.setItemMeta(itemMeta);
        return i;
    }

    private ItemStack createMoneyNote(int amt) {
        ItemStack i = new ItemStack(Material.PAPER, 1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "Money Note");
        im.setLore(Arrays.asList("$" + amt));
        i.setItemMeta(im);
        return i;
    }

    public void openCrate(final Player p, CrateType crateType) {
        String name = crateType.toString().charAt(0) + crateType.toString().substring(1).toLowerCase();
        final Inventory spinner = Bukkit.createInventory(null, 27,  name + " Crate");
        SchedulerTask scheduler = new SchedulerTask(p, spinner, crateType, this);
        p.openInventory(spinner);
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
    COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, VOTE;
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
            if(p.getInventory().firstEmpty() == -1) {
                p.getWorld().dropItem(p.getLocation(), i);
            } else {
                p.getInventory().addItem(i);
            }
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
        if(p.getInventory().firstEmpty() == -1) {
            p.getWorld().dropItem(p.getLocation(), i);
        } else {
            p.getInventory().addItem(i);
        }
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
