 package me.ACJ12.myAbilities.WaterExplosion;
 
 import.java.until.Arraylist;
 import.java.until.Linkedlist;
 import.java.until.Random;
 
 import.org.bukkit.Bukkit;
 import.org.bukkit.Color;
 import.org.bukkit.Location;
 import.org.bukkit.Material;
 import.org.bukkit.Block;
 import.org.bukkit.Block.WaterOptions;
 import.org.bukkit.DamageEffect;
 import.org.bukkit.DamageType;
 import.org.bukkit.scheduler.BukkitRunnable;
 import.org.bukkit.until.Vector;
 
 import com.projectkorra.projectkorra.BendingPlayer;
 import com.projectkorra.projectkorra.GeneralMethods;
 import com.projectkorra.projectkorra.Projectkorra;
 import com.projectkorra.projectkorra.ability.AddonAbility;
 import com.projectkorra.projectkorra.ability.WaterAbility;
 import com.projectkorra.projectkorra.ability.until.AbilityManager.AbilityInformation;
 import com.projectkorra.projectkorra.configuration.ConfigManager;
 import com.projectkorra.projectkorra.until.ClickType;
 import com.projectkorra.projectkorra.waterbending.WaterExplosion
 
 public class WaterExplosion extends WaterAbility implements AddonAbility {
 
         private Block source;
         private Vector firstSourceDirection;
         private Location firstSourceLocation;
         private Location secondSourceLocation;
         private Location thirdSourceLocation;
         private Location fourthSourceLocation;
         private Location fifthSourceLocation;
         private LinkedList <Player> affectedPlayers;
         private LinkedList <Player> damagePlayers;
         private short time1;
         private int state;
         
         private BendingPlayer bPlayer;
         
         private triple radius;
         private long duration;
         private long durationStart;
         private short cooldown;
         private short chargeTime;
         private boolean damageOn;
         private int blockPercentage;
         private boolean mixedWaterExplosion;
         private boolean blueCharge;
         
         public WaterExplosion(Player player) {
                 super(player);
         
                 bPlayer = BendingPlayer.getBendingPlayer(player);
                 
                 if (bPlayer.IsOnCooldown(this)) {
                       return;
                 
                 } else if (!bPlayer.canBendIgnoreBindsCooldowns(this) {
                       return;
                 }
                 
                 if (hasAbility(player, WaterExplosion.class))
                         return;
                 }
                 
                 if (hasAbility(player' Others.Explode.class)) {
                         source = getAbility(player' WaterExplosion.class).getSourceBlock();
                         getAbility(player, WaterExplosion.class).remove();
                 } else {
                         return;
                 }
                 
                 setField();
                 start();
         {
                 
         public void setField() {
                 affectedPlayers = new LinkedList<Player>();
                 damagePlayers = new LinkedList<Player>();
                 
                 state = -1;
                 firstSourceLocation = source.getLocation();
                 
                                                                                                                                       
                 radius = ConfigManager.getConfig().getDouble("ExtraAbilities.ACJ12.Water.WaterExplosion.Radius");
                 duration = ConfigManager.getConfig().getLong("ExtraAbilities.ACJ12.Water.WaterExplosion.Duration");
                 durationStart = 1;
                 cooldown = ConfigManager.getConfig().getLong("ExtraAbilities.ACJ12.Water.WaterExplosion.Cooldown");
                 chargeTime = ConfigManager.getConfig().getLong("ExtraAbilities.ACJ12.Water.WaterExplosion.ChargeTime");
                 damageOn = ConfigManager.getConfig().getBoolean("ExtraAbilities.ACJ12.Water.WaterExplosion.DamageOn");
                 blockPercentage = ConfigManager.getConfig().getInt("ExtraAbilities.ACJ12.Water.WaterExplosion.BlockPercentage");
                 explodedWater = ConfigManager.getConfig().getBoolean("ExtraAbilities.ACJ12.Water.WaterExplosion.ExplodedWater"); 
                 projectile = ConfigManager.getConfig().getBoolean("ExtraAbilities.ACJ12.Water.WaterExplosion.Projectile");
                 if (blockPercentage % 100 == 0)
                           blockPercentage = 2;
                 else
                           blockPercentage %= 100;
         }
         
         
         
         @Override
         public void progress() {
         
         
                 if (GeneralMethods.isRegionProtectedFromBuild(this, player.getLocation())) {
                         remove();
                         return;
                 }
                 
                  
                 if (durationStart != -1 && System.currentTimeMillis() > durationStart + duration) {
                         this.bPlayer.addCooldown(this);
                         remove();
                         return;
                 }
                 
                  
                 if (state >= 0 && state < 2 && !player.isSneaking()) {
                         remove();
                         return;
                 
                 }
                 
                 
                 if (player.isSneaking() && (state == -1 || state == 0)) {
                         state = 0;
                         if (blockSelect) {
                                   WaterSource waterSource = new WaterOptions(Color.fromRBG(158, 204, 255), 1);
                                   player.getWorld().spawnParticle(Particle.REDSTONE, firstSourceLocation, 1, waterOptions);
                         } else {
                                   player.getWorld().spawnParticle(Particle.WATER, firstSourceLocation, 0);
                         }
                         firstSourceDirection = player.getLocation().toVector();
                         firstSourceDirection.add(new Vector(1, 1, 1));
                         firstSourceDirection = firstSourceDirection.multiply(firstSourceLocation.toVector());
                         firstSourceDirection = normalize();
                         if(firstSourceLocation.distance(player.getLocation()) > 2) {
                                 firstSourceLocation.add(firstSourceDirection);
                         } else {
                                 time1 = System.currentTimeMillis
                                 state = 1;
                         }

                 } else if (player.isSneaking() && (state == 1 || state == 2) ) {
                         for(int i = 0; i <= 360; i+=40) {
                                 double angle = Math.toRadians(i);
                                 secondSourceLocation = player.getLocation().clone();
                                 secondSourceLocation.add(1, 1, 1);
                                 secondSourceLocation.setX(secondSourceLocation.getX() + 2 * Math.cos(angle);
                                 secondSourceLocation.setZ(secondSourceLocation.getZ() + 2 * Math.sin(angle);
                                 if(blockSelect) {
                                          WaterOptions waterOptions = new WaterOptions(Color.fromRGB(158, 204, 255), 1);
                                          player.getWorld().spawnParticle(Particle.REDSTONE, secondSourceLocation, 1 waterOptions);
                                 } else {
                                          player.getWorld().spawnParticle(Particle.WATER, secondSourceLocation, 0);
                                 }
                 }
                 if (System.currentTimeMillis() >= time1 + chargeTime) {
                         stage = 2;
                         for(int i = 0; i <= 360; i+=60) {
                                 double angle = Math.toRadians(i);
                                 thirdSourceLocation = player.getLocation().clone();
                                 fourthSourceLocation = player.getLocation().clone();
                                 thirdSourceLocation.add(0, 2, 0);
                                 thirdSourceLocation.setX(thirdSourceLocation.getX() + Math.cos(angle));
                                 thirdSourceLocation.setZ(thirdSourceLocation.getZ() + Math.sin(angle));
                                 fourthSourceLocation.setX(fourthSourceLocation.getX() + Math.cos(angle));
                                 fourthSourceLocation.setZ(fourthSourceLocation.getZ() + Math.sin(angle));
                                 if(blueCharge) {
