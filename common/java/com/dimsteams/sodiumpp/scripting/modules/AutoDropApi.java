package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.scripting.ApiType;
import com.dimsteams.sodiumpp.scripting.ApiVisibility;
import com.dimsteams.sodiumpp.configs.AutoDropConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;
import com.dimsteams.sodiumpp.utils.InventorySlot;
import com.dimsteams.sodiumpp.utils.InventoryUtils;
import com.dimsteams.scripting.MethodDescription;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AutoDropApi {

    @MethodDescription("""
            Drops configured items on the ground
            """)
    @ApiVisibility(ApiType.ACTION)
    public void dropItems() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            AutoDropConfig config = ConfigStore.instance.getConfig().autoDropConfig;
            List<InventorySlot> slots = new ArrayList<>();
            for (int i = 0; i < 36; i++) {
                ItemStack itemStack = mc.player.getInventory().getItem(i);
                if (!itemStack.isEmpty() && config.items.contains(itemStack.getItem())) {
                    slots.add(new InventorySlot(i));
                }
            }
            InventoryUtils.dropItemStacks(slots);
        }
    }
}