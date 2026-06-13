package com.dimsteams.sodiumpp.modules.automation;

import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.common.events.BeforeAttackEvent;
import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.phys.HitResult;

public class BreachSwap implements Module {
    public static final BreachSwap instance = new BreachSwap();
    private final Minecraft mc = Minecraft.getInstance();
    private Inventory inventory;

    private int prevSelectedSlot = -1;

    private BreachSwap() {
        Events.AttackEventHandler(this::onBeforeAttack, this::onAfterAttack, 2);
    }

    private void onBeforeAttack(BeforeAttackEvent event) {

        if (!ConfigStore.instance.getConfig().breachSwapConfig.enabled) {
            return;
        }
        if (mc.player == null) {
            return;
        }

        if (mc.hitResult == null) {
            return;
        }

        if (mc.hitResult.getType() != HitResult.Type.ENTITY) {
            return;
        }

        int mace = -1;
        inventory = mc.player.getInventory();


        for (int i = 0; i < 9; i++) {
            ItemStack item = inventory.getItem(i);

            if (item.getEnchantments().keySet().stream().anyMatch(enchantment ->
                    enchantment.value().effects().keySet().contains(EnchantmentEffectComponents.ARMOR_EFFECTIVENESS)))
                mace = i;
        }

        if (mace == -1) {
            return;
        }
        prevSelectedSlot = inventory.getSelectedSlot();
        inventory.setSelectedSlot(mace);

    }

    private void onAfterAttack() {
        if (!ConfigStore.instance.getConfig().breachSwapConfig.enabled) return;
        if (prevSelectedSlot == -1) return;
        inventory.setSelectedSlot(prevSelectedSlot);
        prevSelectedSlot = -1;
    }

}