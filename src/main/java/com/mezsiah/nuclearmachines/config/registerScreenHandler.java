package com.mezsiah.nuclearmachines.config;

import com.mezsiah.nuclearmachines.NuclearMachines;
import com.mezsiah.nuclearmachines.screen.handler.UraniumFurnaceScreenHandler;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class registerScreenHandler {
    public static ScreenHandlerType<UraniumFurnaceScreenHandler> URANIUM_FURNACE =
    ScreenHandlerRegistry.registerSimple(new Identifier(NuclearMachines.MODID, "uranium_furnace"),
    UraniumFurnaceScreenHandler::new);
}
