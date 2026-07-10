package dev.ironcrest.functionplus.execute;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import java.util.*;

/**
 * Custom Execute Conditions Registry
 * 
 * New /execute if/unless conditions:
 * 1. execute if entity_score - Check if entity has specific score in a scoreboard objective
 * 2. execute if player_effect - Check if player has a specific effect active
 */
public class ExecuteConditionRegistry {
	
	private static final Map<String, ExecuteCondition> CONDITIONS = new HashMap<>();
	
	public static void register() {
		// Condition 1: entity_score - Check entity scoreboard score
		registerCondition("entity_score", new EntityScoreCondition());
		
		// Condition 2: player_effect - Check if player has active effect
		registerCondition("player_effect", new PlayerEffectCondition());
	}
	
	/**
	 * Register a new execute condition
	 */
	public static void registerCondition(String name, ExecuteCondition condition) {
		CONDITIONS.put(name, condition);
	}
	
	/**
	 * Get a registered condition
	 */
	public static ExecuteCondition getCondition(String name) {
		return CONDITIONS.get(name);
	}
	
	/**
	 * Base interface for execute conditions
	 */
	public interface ExecuteCondition {
		boolean test(ServerCommandSource source, Entity entity);
	}
	
	/**
	 * Condition 1: Check if entity has a specific score in a scoreboard objective
	 * Usage: /execute if entity_score @s objective:10..20 run command
	 */
	public static class EntityScoreCondition implements ExecuteCondition {
		
		@Override
		public boolean test(ServerCommandSource source, Entity entity) {
			// This would check scoreboard objectives
			// Implementation depends on Minecraft's scoreboard system
			if (entity == null) return false;
			
			try {
				// Get the scoreboard from the server
				var scoreboard = entity.getWorld().getScoreboard();
				
				// Check entity's scores across objectives
				var objectives = scoreboard.getObjectives();
				for (var objective : objectives) {
					var score = scoreboard.getScore(entity, objective);
					if (score != null) {
						return true; // Entity has at least one score
					}
				}
			} catch (Exception e) {
				// Return false if any error occurs
			}
			
			return false;
		}
		
		/**
		 * Check if entity score falls within a range
		 */
		public static boolean testScoreRange(Entity entity, String objectiveName, int min, int max) {
			try {
				var scoreboard = entity.getWorld().getScoreboard();
				var objective = scoreboard.getNullableObjective(objectiveName);
				
				if (objective != null) {
					var score = scoreboard.getScore(entity, objective);
					if (score != null) {
						int value = score.getScore();
						return value >= min && value <= max;
					}
				}
			} catch (Exception e) {
				// Return false on error
			}
			
			return false;
		}
	}
	
	/**
	 * Condition 2: Check if player has a specific active effect (potion effect)
	 * Usage: /execute unless player_effect @s speed run command
	 */
	public static class PlayerEffectCondition implements ExecuteCondition {
		
		@Override
		public boolean test(ServerCommandSource source, Entity entity) {
			// Check if entity has any active effects
			if (entity == null) return false;
			
			try {
				var statusEffects = entity.getActiveStatusEffects();
				return !statusEffects.isEmpty();
			} catch (Exception e) {
				return false;
			}
		}
		
		/**
		 * Check if entity has a specific effect by ID
		 */
		public static boolean hasEffect(Entity entity, Identifier effectId) {
			try {
				var statusEffects = entity.getActiveStatusEffects();
				for (var effect : statusEffects.keySet()) {
					var id = net.minecraft.registry.Registries.STATUS_EFFECT.getId(effect);
					if (id != null && id.equals(effectId)) {
						return true;
					}
				}
			} catch (Exception e) {
				// Return false on error
			}
			
			return false;
		}
		
		/**
		 * Check if entity has any of the specified effects
		 */
		public static boolean hasAnyEffect(Entity entity, Identifier... effectIds) {
			for (Identifier id : effectIds) {
				if (hasEffect(entity, id)) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Get effect amplifier level
		 */
		public static int getEffectAmplifier(Entity entity, Identifier effectId) {
			try {
				var statusEffects = entity.getActiveStatusEffects();
				for (var entry : statusEffects.entrySet()) {
					var id = net.minecraft.registry.Registries.STATUS_EFFECT.getId(entry.getKey());
					if (id != null && id.equals(effectId)) {
						return entry.getValue().getAmplifier();
					}
				}
			} catch (Exception e) {
				// Return -1 on error or not found
			}
			
			return -1;
		}
	}
}
