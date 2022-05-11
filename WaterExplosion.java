 package ACJ12;
 
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
                 
                 if (hasAbility(player' WaterManipulation.class)) {
                         source = getAbility(player' WaterManipulation.class).getSourceBlock();
                         getAbility(player, WaterManipulation.class) remove();



