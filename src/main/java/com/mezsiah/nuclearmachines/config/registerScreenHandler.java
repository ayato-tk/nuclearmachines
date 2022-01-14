package com.mezsiah.nuclearmachines.config;


import com.mezsiah.nuclearmachines.screen.UraniumGeneratorScreen;
import com.mezsiah.nuclearmachines.screen.handler.UraniumGeneratorScreenHandler;

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
        SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(registerBlock.getBlockId("uranium_generator"), (syncId, inventory) -> new UraniumGeneratorScreen(syncId, inventory, ScreenHandlerContext.EMPTY ));
        ScreenRegistry.<UraniumGeneratorScreen, UraniumGeneratorScreenHandler>register(SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new UraniumGeneratorScreenHandler(gui, inventory.player, title));
    }

}
