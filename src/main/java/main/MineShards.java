package main;
import net.minecraft.server.v1_10_R1.EnumParticle;
import net.minecraft.server.v1_10_R1.Item;
import net.minecraft.server.v1_10_R1.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Cory on 27/02/2017.
 */
public class MineShards extends JavaPlugin implements Listener {

    private Crates crates;

    private ShapedRecipe commonRecipe;
    private ShapedRecipe uncommonRecipe;
    private ShapedRecipe rareRecipe;
    private ShapedRecipe epicRecipe;
    private ShapedRecipe legendaryRecipe;

    ItemStack commonCrate = genCustomItem(Material.CHEST, ChatColor.YELLOW + "Common Crate", "What treasures...", null);
    ItemStack uncommonCrate = genCustomItem(Material.CHEST, ChatColor.YELLOW + "Uncommon Crate", "What treasures...", null);
    ItemStack rareCrate = genCustomItem(Material.CHEST, ChatColor.YELLOW + "Rare Crate", "What treasures...", null);
    ItemStack epicCrate = genCustomItem(Material.CHEST, ChatColor.YELLOW + "Epic Crate", "What treasures...", null);
    ItemStack legendaryCrate = genCustomItem(Material.CHEST, ChatColor.YELLOW + "Legendary Crate", "What treasures...", null);

    /*
    Hardcoded :>
     */
    private final int bMaxX = 376, bMaxY = 101, bMaxZ = 273;
    private final int bMinX = 340, bMinY = 19, bMinZ = 310;

    private final int cMinX = 155, cMinY = 56, cMinZ = 212;
    private final int cMaxX = 176, cMaxY = 76, cMaxZ = 191;

    private final int aMaxX = 133, aMaxY = 101, aMaxZ = 318;
    private final int aMinX = 96, aMinY = 36, aMinZ = 354;

    private final int eMaxX = 167, eMaxY = 79, eMaxZ = 397;
    private final int eMinX = 188, eMinY = 56, eMinZ = 375;

    private ItemStack common = genCustomItem(Material.PRISMARINE_SHARD, ChatColor.AQUA + "Common Shard", "Maybe you can craft this into something useful...?", new EnchantWrapper(Enchantment.DURABILITY, 1));
    private ItemStack uncommon = genCustomItem(Material.PRISMARINE_SHARD, ChatColor.AQUA + "Uncommon Shard", "Maybe you can craft this into something useful...?", new EnchantWrapper(Enchantment.DURABILITY, 1));
    private ItemStack rare = genCustomItem(Material.PRISMARINE_SHARD, ChatColor.AQUA + "Rare Shard", "Maybe you can craft this into something useful...?", new EnchantWrapper(Enchantment.DURABILITY, 1));
    private ItemStack epic = genCustomItem(Material.PRISMARINE_SHARD, ChatColor.AQUA + "Epic Shard", "Maybe you can craft this into something useful...?", new EnchantWrapper(Enchantment.DURABILITY, 1));
    private ItemStack legendary = genCustomItem(Material.PRISMARINE_SHARD, ChatColor.AQUA + "Legendary Shard", "Maybe you can craft this into something useful...?", new EnchantWrapper(Enchantment.DURABILITY, 1));

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        commonRecipe = new ShapedRecipe(commonCrate);
        uncommonRecipe = new ShapedRecipe(uncommonCrate);
        rareRecipe = new ShapedRecipe(rareCrate);
        epicRecipe = new ShapedRecipe(epicCrate);
        legendaryRecipe = new ShapedRecipe(legendaryCrate);

        commonRecipe.shape("csc", "sxs", "csc");
        commonRecipe.setIngredient('c', Material.COAL);
        commonRecipe.setIngredient('x', Material.CHEST);
        commonRecipe.setIngredient('s', common.getData());

        uncommonRecipe.shape("isi", "sxs", "isi");
        uncommonRecipe.setIngredient('i', Material.IRON_INGOT);
        uncommonRecipe.setIngredient('x', Material.CHEST);
        uncommonRecipe.setIngredient('s', uncommon.getData());

        rareRecipe.shape("gsg", "sxs", "gsg");
        rareRecipe.setIngredient('g', Material.GOLD_INGOT);
        rareRecipe.setIngredient('x', Material.CHEST);
        rareRecipe.setIngredient('s', rare.getData());

        epicRecipe.shape("dsd", "sxs", "dsd");
        epicRecipe.setIngredient('d', Material.DIAMOND);
        epicRecipe.setIngredient('x', Material.CHEST);
        epicRecipe.setIngredient('s', epic.getData());

        legendaryRecipe.shape("ese", "sxs", "ese");
        legendaryRecipe.setIngredient('e', Material.EMERALD);
        legendaryRecipe.setIngredient('x', Material.CHEST);
        legendaryRecipe.setIngredient('s', legendary.getData());

        this.getServer().addRecipe(commonRecipe);
        this.getServer().addRecipe(uncommonRecipe);
        this.getServer().addRecipe(rareRecipe);
        this.getServer().addRecipe(epicRecipe);
        this.getServer().addRecipe(legendaryRecipe);

        crates = new Crates(this);
    }

    public ItemStack getCrate(CrateType ct) {
        if (ct == CrateType.COMMON) {
            return this.commonCrate;
        } else if (ct == CrateType.UNCOMMON) {
            return this.uncommonCrate;
        } else if (ct == CrateType.RARE) {
            return this.rareCrate;
        } else if (ct == CrateType.EPIC) {
            return this.epicCrate;
        } else if (ct == CrateType.LEGENDARY) {
            return this.legendaryCrate;
        } else return null;
    }

    public static void giveItem(Player p, ItemStack itemStack) {
        if(p.getInventory().firstEmpty() == -1) {
            p.getWorld().dropItem(p.getLocation(), itemStack);
        } else {
            p.getInventory().addItem(itemStack);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        ItemMeta itemMeta = p.getInventory().getItemInHand().getItemMeta();
        if (!itemMeta.hasLore()) return;
        if (itemMeta.getLore().get(0).toString().equals("What treasures...")) {
            if (itemMeta.getDisplayName().toString().equals(ChatColor.YELLOW + "Common Crate")) {
                crates.openCrate(p, CrateType.COMMON);
                e.setCancelled(true);
            } else if (itemMeta.getDisplayName().toString().equals(ChatColor.YELLOW + "Uncommon Crate")) {
                crates.openCrate(p, CrateType.UNCOMMON);
                e.setCancelled(true);
            } else if (itemMeta.getDisplayName().toString().equals(ChatColor.YELLOW + "Rare Crate")) {
                crates.openCrate(p, CrateType.RARE);
                e.setCancelled(true);
            } else if (itemMeta.getDisplayName().toString().equals(ChatColor.YELLOW + "Epic Crate")) {
                crates.openCrate(p, CrateType.EPIC);
                e.setCancelled(true);
            } else if (itemMeta.getDisplayName().toString().equals(ChatColor.YELLOW + "Legendary Crate")) {
                crates.openCrate(p, CrateType.LEGENDARY);
                e.setCancelled(true);
            }
            int amount = p.getInventory().getItemInHand().getAmount();
            ItemStack item = p.getInventory().getItemInHand();
            item.setAmount(amount - 1);
            p.getInventory().setItemInHand(item);
        }
    }

    private boolean isOre(ItemStack i) {
        return (i.getType() == Material.COAL || i.getType() == Material.IRON_INGOT
                || i.getType() == Material.GOLD_INGOT || i.getType() == Material.DIAMOND
                || i.getType() == Material.EMERALD);
    }

    private boolean isShard(ItemStack i) {
        return i.getType() == Material.PRISMARINE_SHARD;
    }

    @EventHandler
    public void onItemCraft(PrepareItemCraftEvent e) {
        ItemStack[] inven = e.getInventory().getContents();
        if (inven[2].getType() == Material.PRISMARINE_SHARD && inven[4].getType() == Material.PRISMARINE_SHARD && inven[6].getType() == Material.PRISMARINE_SHARD && inven[8].getType() == Material.PRISMARINE_SHARD
                && isOre(inven[1]) && isOre(inven[3]) && isOre(inven[7]) && isOre(inven[9]) && inven[5].getType() == Material.CHEST) {
            ItemStack[] shards = {inven[2], inven[4], inven[6], inven[8]};
            boolean isLegitimate = true;

            for (ItemStack i : shards) {
                if (i.getItemMeta().getDisplayName() == null) {
                    isLegitimate = false;
                }
            }

            if (isLegitimate) {
                Material[] ores = {inven[1].getType(), inven[3].getType(), inven[7].getType(), inven[9].getType()};
                String name = shards[0].getItemMeta().getDisplayName();
                if (name.contains("Common")) {
                    for (Material m : ores) {
                        if (m != Material.COAL) isLegitimate = false;
                    }
                } else if (name.contains("Uncommon")) {
                    for (Material m : ores) {
                        if (m != Material.IRON_INGOT) isLegitimate = false;
                    }
                } else if (name.contains("Rare")) {
                    for (Material m : ores) {
                        if (m != Material.GOLD_INGOT) isLegitimate = false;
                    }
                } else if (name.contains("Epic")) {
                    for (Material m : ores) {
                        if (m != Material.DIAMOND) isLegitimate = false;
                    }
                } else if (name.contains("Legendary")) {
                    for (Material m : ores) {
                        if (m != Material.EMERALD) isLegitimate = false;
                    }
                }
            }


            if (!isLegitimate) e.getInventory().setResult(new ItemStack(Material.AIR));
        }
    }


    @EventHandler
    public void rightClickItem(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = e.getPlayer();
            ItemStack holding = player.getItemInHand();
            if (holding.getType() == Material.PAPER && e.getItem().hasItemMeta()) {
                ItemMeta itemMeta = holding.getItemMeta();
                if (itemMeta.getLore().get(0).contains("$")) {
                    int amt = Integer.valueOf(itemMeta.getLore().get(0).substring(1));
                    player.sendMessage(ChatColor.BLUE + "Redeeming your Money Note.");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + amt);
                    int amtItem = player.getInventory().getItemInHand().getAmount();
                    if (amtItem > 1) {
                        player.getInventory().getItemInHand().setAmount(amtItem - 1);
                    } else {
                        player.getInventory().setItem(player.getInventory().getHeldItemSlot(), null);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onItemDrag(InventoryClickEvent e) {
        if (e.getInventory().getName().contains("Crate")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onServerLeave(PlayerQuitEvent e) {
        for (SchedulerTask sc : Crates.opening) {
            if (sc.getPlayer().getName().equals(e.getPlayer().getName())) sc.forceEnd();
        }
    }

    @EventHandler
    public void onServerKicked(PlayerKickEvent e) {
        for (SchedulerTask sc : Crates.opening) {
            if (sc.getPlayer().getName().equals(e.getPlayer().getName())) sc.forceEnd();
        }
    }

    @EventHandler
    public void onInvQuit(InventoryCloseEvent e) {
        for (SchedulerTask sc : Crates.opening) {
            if (sc.getPlayer().getName().equals(e.getPlayer().getName())) sc.forceEnd();
        }
    }

    public void createHelix(Player player, EnumParticle type) {
        Location loc = player.getLocation();
        int radius = 2;
        for (double y = 0; y <= 50; y += 0.05) {
            double x = radius * Math.cos(y);
            double z = radius * Math.sin(y);
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type, true, (float) (loc.getX() + x), (float) (loc.getY() + y), (float) (loc.getZ() + z), 0F, 0F, 0F, 0F, 1);
            for (Player online : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) online).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.isCancelled()) return;
        Player player = e.getPlayer();

        int x = player.getLocation().getBlockX(), y = player.getLocation().getBlockY(), z = player.getLocation().getBlockZ();

        Random random = new Random();
        Material type = e.getBlock().getType();
        int r;
        if(e.getPlayer().getWorld().getName().contains("Prison")) {
            if(type == Material.STONE) {
                r = random.nextInt(600);
                if(r == 1 || r == 2) {
                    giveItem(player, this.common);
                    player.sendMessage(ChatColor.RED + "You unearthed a Common Shard!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 10*20, 0));
                    createHelix(player, EnumParticle.HEART);
                } else if(r == 3) {
                    giveItem(player, this.uncommon);
                    player.sendMessage(ChatColor.RED + "You unearthed an Uncommon Shard!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 15*20, 0));
                    createHelix(player, EnumParticle.WATER_BUBBLE);
                }
            } else if(type == Material.SANDSTONE) {
                r = random.nextInt(1500);
                if(r <= 5) {
                    giveItem(player, this.uncommon);
                    player.sendMessage(ChatColor.RED + "You unearthed an Uncommon Shard!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 15*20, 0));
                    createHelix(player, EnumParticle.WATER_BUBBLE);
                } else if(r >= 5 && r <= 8) {
                    giveItem(player, this.rare);
                    player.sendMessage(ChatColor.RED + "You unearthed a Rare Shard!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20*20, 0));
                    createHelix(player, EnumParticle.FLAME);
                }
            } else if(type == Material.NETHERRACK) {
                r = random.nextInt(3000);
                if(r <= 10) {
                    giveItem(player, this.rare);
                    player.sendMessage(ChatColor.RED + "You unearthed a Rare Shard!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20*20, 0));
                    createHelix(player, EnumParticle.FLAME);
                } else if(r >= 10 && r <= 15) {
                    giveItem(player, this.epic);
                    player.sendMessage(ChatColor.RED + "You unearthed an Epic Shard!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 25*20, 0));
                    createHelix(player, EnumParticle.EXPLOSION_LARGE);
                }
            } else if(type == Material.QUARTZ_BLOCK) {
                r = random.nextInt(3000);
                if(r <= 10) {
                    giveItem(player, this.epic);
                    player.sendMessage(ChatColor.RED + "You unearthed an Epic Shard!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 25*20, 0));
                    createHelix(player, EnumParticle.EXPLOSION_LARGE);
                }
            }
        } else if (e.getPlayer().getWorld().getName().contains("Ex-Con")) {
            if (type == Material.ENDER_STONE) {
                r = random.nextInt(1750) + 1;
            } else {
                r = random.nextInt(2000) + 1;
            }
            if (r == 1) {
                switch (type) {
                    case STONE:
                    case COAL_ORE:
                    case REDSTONE_ORE:
                    case GOLD_ORE:
                    case DIAMOND_ORE:
                    case IRON_ORE:
                    case LAPIS_ORE:
                    case OBSIDIAN:
                    case ENDER_STONE:
                        player.sendMessage(ChatColor.RED + "You unearthed a Legendary Shard!");
                        giveItem(player, this.legendary);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20*60, 0));
                        createHelix(player, EnumParticle.FIREWORKS_SPARK);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static ItemStack genCustomItem(Material material, String customName, String lore, EnchantWrapper... enchant) {
        ItemStack s = new ItemStack(material);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(customName);
        im.setLore(Arrays.asList(lore));
        if (enchant != null) {
            for (EnchantWrapper ew : enchant) {
                im.addEnchant(ew.getEnchantment(), ew.getLevel(), true);
            }
        }
        s.setItemMeta(im);
        return s;
    }

    public static ItemStack genCustomPotion(PotionType potionType, int lvl, int amt, boolean splash, boolean extended) {
        Potion potion = new Potion(potionType);
        if(splash) {
            potion.setSplash(splash);
        }
        potion.setLevel(lvl);
        if(!splash && extended) {
            potion.setHasExtendedDuration(extended);
        }
        return potion.toItemStack(amt);
    }
}
class EnchantWrapper {

    private final Enchantment enchantment;
    private final int level;

    public EnchantWrapper(Enchantment enchant, int lvl) {
         this.enchantment = enchant;
         this.level = lvl;
    }

    public Enchantment getEnchantment() {
        return this.enchantment;
    }

    public int getLevel() {
        return this.level;
    }
}
