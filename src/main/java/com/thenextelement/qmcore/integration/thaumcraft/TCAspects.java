package com.thenextelement.qmcore.integration.thaumcraft;

import net.minecraft.item.ItemStack;

import com.thenextelement.qmcore.QuaesitaMagum;
import com.thenextelement.qmcore.common.scripts.IngredientFactory;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

/**
 * Java form of ModTweaker's {@code mods.thaumcraft.Aspects}. Aspects are written exactly as in the scripts, e.g.
 * {@code "ordo 2, fames 1"}. Only reference this class when Thaumcraft is loaded.
 */
public final class TCAspects {

    private TCAspects() {}

    /** {@code Aspects.set(item, aspects)}: replaces the item's aspects. Wildcard meta applies to every meta. */
    public static void set(ItemStack stack, String aspects) {
        if (IngredientFactory.isMissing(stack)) return;
        AspectList list = parse(aspects, stack.toString());
        if (list != null) ThaumcraftApi.registerObjectTag(stack, list);
    }

    /** {@code Aspects.add(item, aspects)}: adds to the item's current (registered or generated) aspects. */
    public static void add(ItemStack stack, String aspects) {
        if (IngredientFactory.isMissing(stack)) return;
        AspectList extra = parse(aspects, stack.toString());
        if (extra == null) return;
        AspectList current = ThaumcraftApiHelper.getObjectAspects(stack);
        AspectList list = current != null ? current.copy() : new AspectList();
        ThaumcraftApi.registerObjectTag(stack, list.add(extra));
    }

    /** {@code Aspects.addEntity(name, aspects)}: adds to an entity's scan aspects, creating the entry if needed. */
    public static void addEntity(String entityName, String aspects) {
        AspectList extra = parse(aspects, entityName);
        if (extra == null) return;
        for (ThaumcraftApi.EntityTags tags : ThaumcraftApi.scanEntities) {
            if (tags.entityName.equals(entityName) && (tags.nbts == null || tags.nbts.length == 0)) {
                tags.aspects.add(extra);
                return;
            }
        }
        ThaumcraftApi.registerEntityTag(entityName, extra);
    }

    /** Parses {@code "tag amount, tag amount"}; null (and a log line) if any aspect is unknown. */
    public static AspectList parse(String aspects, String target) {
        AspectList list = new AspectList();
        for (String part : aspects.split(",")) {
            String[] bits = part.trim()
                .split("\\s+");
            if (bits.length != 2) {
                QuaesitaMagum.LOG.error("Bad aspect entry \"{}\" for {}", part.trim(), target);
                return null;
            }
            Aspect aspect = Aspect.getAspect(bits[0].toLowerCase());
            if (aspect == null) {
                QuaesitaMagum.LOG.error("Unknown aspect \"{}\" for {}", bits[0], target);
                return null;
            }
            try {
                list.add(aspect, Integer.parseInt(bits[1]));
            } catch (NumberFormatException e) {
                QuaesitaMagum.LOG.error("Bad aspect amount \"{}\" for {}", bits[1], target);
                return null;
            }
        }
        return list;
    }
}
