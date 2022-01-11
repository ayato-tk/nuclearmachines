package com.mezsiah.nuclearmachines.screen;

import com.mezsiah.nuclearmachines.block.entity.UraniumFurnaceEntity;
import com.mezsiah.nuclearmachines.config.registerScreenHandler;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.TranslatableText;

public class UraniumFurnaceScreen extends SyncedGuiDescription{
    private static final int INVENTORY_SIZE = 1;

    public UraniumFurnaceScreen( int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(registerScreenHandler.SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
        root.add(itemSlot, 4, 1);

       // WLabel energyStorage = new WLabel(new TranslatableText())

        root.add(this.createPlayerInventoryPanel(), 0, 3);

        root.validate(this);
    }
    
}
