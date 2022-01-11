package com.mezsiah.nuclearmachines.config;

import com.mezsiah.nuclearmachines.screen.UraniumFurnaceScreen;
import com.mezsiah.nuclearmachines.screen.handler.UraniumFurnaceScreenHandler;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;


public class registerScreenHandler {
    /*public static ScreenHandlerType<UraniumFurnaceScreenHandler> URANIUM_FURNACE =
    ScreenHandlerRegistry.registerSimple(new Identifier(NuclearMachines.MODID, "uranium_furnace"),
    UraniumFurnaceScreenHandler::new);*/
    public static ScreenHandlerType SCREEN_HANDLER_TYPE;

    public registerScreenHandler(){
        SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(registerBlock.getBlockId("uranium_furnace"), (syncId, inventory) -> new UraniumFurnaceScreen(syncId, inventory, ScreenHandlerContext.EMPTY));
        ScreenRegistry.<UraniumFurnaceScreen, UraniumFurnaceScreenHandler>register(SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new UraniumFurnaceScreenHandler(gui, inventory.player, title));
    }

}
