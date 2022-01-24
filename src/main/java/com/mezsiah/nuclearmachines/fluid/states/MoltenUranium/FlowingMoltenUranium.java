package com.mezsiah.nuclearmachines.fluid.states.MoltenUranium;

import com.mezsiah.nuclearmachines.fluid.MoltenUranium;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.StateManager.Builder;

public class FlowingMoltenUranium extends MoltenUranium{

    @Override
    protected void appendProperties(Builder<Fluid, FluidState> builder) {
        super.appendProperties(builder);
        builder.add(LEVEL);
    }

    @Override
    public int getLevel(FluidState state) { return state.get(LEVEL); }
    
    @Override
    public boolean isStill(FluidState state) { return false; }

}
