package com.runtoolkit.datalib.features;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for storing and managing command aliases
 */
public class CommandAliasRegistry {
    private final Map<String, String> aliases = new HashMap<>();

    public void registerAlias(String alias, String command) {
        aliases.put(alias.toLowerCase(), command);
    }

    public String getCommand(String alias) {
        return aliases.get(alias.toLowerCase());
    }

    public boolean hasAlias(String alias) {
        return aliases.containsKey(alias.toLowerCase());
    }

    public void removeAlias(String alias) {
        aliases.remove(alias.toLowerCase());
    }

    public Map<String, String> getAllAliases() {
        return new HashMap<>(aliases);
    }
}
