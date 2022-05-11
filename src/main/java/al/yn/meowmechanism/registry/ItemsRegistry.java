package al.yn.meowmechanism.registry;

import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsRegistry {
    protected Map<Identifier, Entry> ITEMS = new HashMap<>();

    protected static ItemsRegistry INSTANCE = null;

    public ItemsRegistry() {
        INSTANCE = this;
    }

    public static ItemsRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemsRegistry();
        }

        return INSTANCE;
    }

    protected Map<Identifier, Entry> getRegistries() {
        return ITEMS;
    }

    public boolean hasEntry(Identifier id) {
        return ITEMS.containsKey(id);
    }

    public Entry getEntry(Identifier id) {
        if (ITEMS.containsKey(id)) {
            return ITEMS.get(id);
        } else {
            throw new RuntimeException("Registry ID " + id + " is not present.");
        }
    }

    public void register(Identifier id, Entry entry) {
        ITEMS.put(id, entry);
    }

    public static class Entry {
        protected Identifier itemId;
        protected Identifier fromItem;
        protected boolean removeVanillaCrafting;
        protected List<Recipe<?>> craftRecipe;
        protected List<Recipe<?>> useRecipe;

        public Entry(Identifier id, Identifier origin, boolean isRemoveVanillaCrafting) {
            this(id, origin, isRemoveVanillaCrafting, new ArrayList<>(), new ArrayList<>());
        }

        public Entry(Identifier id, Identifier origin, boolean isRemoveVanillaCrafting,
                     List<Recipe<?>> recipe, List<Recipe<?>> use) {
            itemId = id;
            fromItem = origin;
            removeVanillaCrafting = isRemoveVanillaCrafting;

            craftRecipe = recipe;
            useRecipe = use;
        }

        public Identifier getId() {
            return itemId;
        }

        public Identifier getFromItem() {
            return fromItem;
        }

        public boolean isRemoveVanillaCrafting() {
            return removeVanillaCrafting;
        }
    }
}
