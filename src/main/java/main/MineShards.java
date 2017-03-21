package main;
import net.minecraft.server.v1_11_R1.EnumParticle;
import net.minecraft.server.v1_11_R1.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Cory on 27/02/2017.
 */
public class MineShards extends JavaPlugin implements Listener {

    private final Crates crates = new Crates(this);

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

    private final int eMaxX = 162, eMaxY = 98, eMaxZ = 446;
    private final int eMinX = 187, eMinY = 12, eMinZ = 419;

    private ItemStack common = genCustomItem(Material.PRISMARINE_SHARD, ChatColor.AQUA + "Common Shard", "Maybe you can craft this into something useful...?", new EnchantWrapper(Enchantment.DURABILITY, 1));
    private ItemStack uncommon = genCustomItem(Material.PRISMARINE_SHARD, ChatColor.AQUA + "Uncommon Shard", "Maybe you can craft this into something useful...?", new EnchantWrapper(Enchantment.DURABILITY, 1));
    private ItemStack rare = genCustomItem(Material.PRISMARINE_SHARD, ChatColor.AQUA + "Rare Shard", "Maybe you can craft this into something useful...?", new EnchantWrapper(Enchantment.DURABILITY, 1));
    private ItemStack epic = genCustomItem(Material.PRISMARINE_SHARD, ChatColor.AQUA + "Epic Shard", "Maybe you can craft this into something useful...?", new EnchantWrapper(Enchantment.DURABILITY, 1));
    private ItemStack legendary = genCustomItem(Material.PRISMARINE_SHARD, ChatColor.AQUA + "Legendary Shard", "Maybe you can craft this into something useful...?", new EnchantWrapper(Enchantment.DURABILITY, 1));

    @Override
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
    }

    public ItemStack getCrate(CrateType ct) {
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
        }
        return null;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        ItemMeta itemMeta = p.getInventory().getItemInMainHand().getItemMeta();
        if(!itemMeta.hasLore()) return;
        if(itemMeta.getLore().get(0).toString().equals("What treasures...")) {
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
            int amount = p.getInventory().getItemInMainHand().getAmount();
            ItemStack item = p.getInventory().getItemInMainHand();
            item.setAmount(amount - 1);
            p.getInventory().setItemInMainHand(item);
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
        if(inven[2].getType() == Material.PRISMARINE_SHARD && inven[4].getType() == Material.PRISMARINE_SHARD && inven[6].getType() == Material.PRISMARINE_SHARD && inven[8].getType() == Material.PRISMARINE_SHARD
                && isOre(inven[1]) && isOre(inven[3]) && isOre(inven[7]) && isOre(inven[9]) && inven[5].getType() == Material.CHEST) {
            ItemStack[] shards = {inven[2], inven[4], inven[6], inven[8]};
            boolean isLegitimate = true;

            for(ItemStack i : shards) {
                if(i.getItemMeta().getDisplayName() == null) {
                    isLegitimate = false;
                }
            }

            if(isLegitimate) {
                Material[] ores = {inven[1].getType(), inven[3].getType(), inven[7].getType(), inven[9].getType()};
                String name = shards[0].getItemMeta().getDisplayName();
                if(name.contains("Common")) {
                    for(Material m : ores) {
                        if(m != Material.COAL) isLegitimate = false;
                    }
                } else if(name.contains("Uncommon")) {
                    for(Material m : ores) {
                        if(m != Material.IRON_INGOT) isLegitimate = false;
                    }
                } else if(name.contains("Rare")) {
                    for(Material m : ores) {
                        if(m != Material.GOLD_INGOT) isLegitimate = false;
                    }
                } else if(name.contains("Epic")) {
                    for(Material m : ores) {
                        if(m != Material.DIAMOND) isLegitimate = false;
                    }
                } else if(name.contains("Legendary")) {
                    for(Material m : ores) {
                        if(m != Material.EMERALD) isLegitimate = false;
                    }
                }
            }


            if(!isLegitimate) e.getInventory().setResult(new ItemStack(Material.AIR));
            }
        }

    @EventHandler
    public void onItemDrag(InventoryClickEvent e) {
        if(e.getInventory().getName().contains("Crate")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onServerLeave(PlayerQuitEvent e) {
        for(SchedulerTask sc : Crates.opening) {
            if(sc.getPlayer().getName().equals(e.getPlayer().getName())) sc.forceEnd();
        }
    }

    @EventHandler
    public void onServerKicked(PlayerKickEvent e) {
        for(SchedulerTask sc : Crates.opening) {
            if(sc.getPlayer().getName().equals(e.getPlayer().getName())) sc.forceEnd();
        }
    }

    @EventHandler
    public void onInvQuit(InventoryCloseEvent e) {
        for(SchedulerTask sc : Crates.opening) {
            if(sc.getPlayer().getName().equals(e.getPlayer().getName())) sc.forceEnd();
        }
    }

    public void createHelix(Player player, EnumParticle type) {
        Location loc = player.getLocation();
        int radius = 2;
        for(double y = 0; y <= 50; y+=0.05) {
            double x = radius * Math.cos(y);
            double z = radius * Math.sin(y);
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type, true, (float) (loc.getX() + x), (float) (loc.getY() + y), (float) (loc.getZ() + z), 0F, 0F, 0F, 0F, 1);
            for(Player online : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer)online).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();

        int x = player.getLocation().getBlockX(), y = player.getLocation().getBlockY(), z = player.getLocation().getBlockZ();

        Random random = new Random();
        if(x >= cMinX && x <= cMaxX && y <= cMaxY && y >= cMinY && z <= cMinZ && z >= cMaxZ) {
            int r = random.nextInt(75) + 1;
            if (r == 1) {
                player.sendMessage(ChatColor.AQUA + "You unearthed a Common Shard!");
                player.getInventory().addItem(common);
                createHelix(player, EnumParticle.FIREWORKS_SPARK);
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 60*20, 1));
            }
        } else if(x >= bMinX && x <= bMaxX && y <= bMaxY && y >= bMinY && z <= bMinZ && z >= bMaxZ) {
            int r = random.nextInt(125) + 1;
            if (r == 1) {
                player.sendMessage(ChatColor.AQUA + "You unearthed an Uncommon Shard!");
                player.getInventory().addItem(uncommon);
                createHelix(player, EnumParticle.DRIP_LAVA);
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20*20, 1));
            }
        } else if(x >= aMinX && x <= aMaxX && y <= aMaxY && y >= aMinY && z <= aMinZ && z >= aMaxZ) {
            int r = random.nextInt(175) + 1;
            if(r == 1) {
                player.sendMessage(ChatColor.AQUA + "You unearthed a Rare Shard!");
                player.getInventory().addItem(rare);
                createHelix(player, EnumParticle.FLAME);
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 35*20, 0));
            }
        } else if(x <= eMinX && x >= eMaxX && y <= eMaxY && y >= eMinY && z >= eMinZ && z <= eMaxZ) {
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.SNOW_BLOCK, 1));
            int r = random.nextInt(300) + 1;
            if(r == 1) {
                player.sendMessage(ChatColor.AQUA + "You unearthed an Epic Shard!");
                player.getInventory().addItem(rare);
                createHelix(player, EnumParticle.SMOKE_LARGE);
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 20, 0));
            }
        }
    }

    public static ItemStack genCustomItem(Material material, String customName, String lore, EnchantWrapper... enchant) {
        ItemStack s = new ItemStack(material);
        ItemMeta im = s.getItemMeta();
        im.setDisplayName(customName);
        im.setLore(Arrays.asList(lore));
        if(enchant != null) {
            for(EnchantWrapper ew : enchant) {
                im.addEnchant(ew.getEnchantment(), ew.getLevel(), true);
            }
        }
        s.setItemMeta(im);
        return s;
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
