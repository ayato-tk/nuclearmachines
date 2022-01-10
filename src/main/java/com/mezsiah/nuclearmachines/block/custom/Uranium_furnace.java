package com.mezsiah.nuclearmachines.block.custom;

import com.mezsiah.nuclearmachines.block.entity.UraniumFurnaceEntity;
import com.mezsiah.nuclearmachines.config.registerBlockEntity;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Uranium_furnace extends BlockWithEntity implements BlockEntityProvider{

    public Uranium_furnace(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // TODO Auto-generated method stub
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
                if(!world.isClient){
                    NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

                    if(screenHandlerFactory != null){
                        player.openHandledScreen(screenHandlerFactory);
                    }

                }


        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof UraniumFurnaceEntity){
                ItemScatterer.spawn(world, pos, (UraniumFurnaceEntity)blockEntity);
                world.updateComparators(pos, this);
            }
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        // TODO Auto-generated method stub
        return new UraniumFurnaceEntity( pos, state);
    }

 @Nullable
 @Override
 public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
      return checkType(type, registerBlockEntity.URANIUM_FURNACE_ENTITY, UraniumFurnaceEntity::tick);
 }
    
}
