package com.mezsiah.nuclearmachines.screen;


import com.mezsiah.nuclearmachines.config.registerScreenHandler;

import org.jetbrains.annotations.Nullable;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;

public class UraniumGeneratorScreen extends SyncedGuiDescription  {
    private static final int INVENTORY_SIZE = 1;
    private static final int PROPERTY_COUNT = 2;
    
    public UraniumGeneratorScreen( int syncId, PlayerInventory playerInventory, ScreenHandlerContext context ) {
        super(registerScreenHandler.SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context, PROPERTY_COUNT));
       
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(100, 100);
        root.setInsets(Insets.ROOT_PANEL);
        
        WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
        root.add(itemSlot, 4, 1);
        WDynamicLabel energy = new WDynamicLabel( () -> I18n.translate("text.nuclearmachines.energy", this.getPropertyDelegate().get(0)) );
        WDynamicLabel fuel = new WDynamicLabel( () -> I18n.translate("text.nuclearmachines.fuel", this.getPropertyDelegate().get(1)) );
        root.add(energy, 2, 2);
        root.add(fuel, 2, 3);
        root.add(this.createPlayerInventoryPanel(), 0, 3);

        root.validate(this);
    }

}
