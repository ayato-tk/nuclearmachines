package com.mezsiah.nuclearmachines.block.entity;

import com.mezsiah.nuclearmachines.config.registerBlockEntity;
import com.mezsiah.nuclearmachines.config.registerItem;
import com.mezsiah.nuclearmachines.item.inventory.ImplementedInventory;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UraniumFurnaceEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory{

    private final DefaultedList<ItemStack> Inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    public UraniumFurnaceEntity( BlockPos pos, BlockState state) {
        super(registerBlockEntity.URANIUM_FURNACE_ENTITY, pos, state);
        //TODO Auto-generated constructor stub
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("Uranium Furnace");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return Inventory;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, Inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, Inventory);
    }

    public static void tick(World world, BlockPos pos, BlockState state, UraniumFurnaceEntity entity) {
        if(hasRecipe(entity) && hasNotReachedStackLimit(entity)) {
            craftItem(entity);

            if(!world.isClient()) {
                EntityType.LIGHTNING_BOLT.spawn((ServerWorld) world, null, null, null, pos,
                        SpawnReason.TRIGGERED, true, true);
            }
        }
    }

    private static void craftItem(UraniumFurnaceEntity entity) {
        entity.removeStack(0, 1);
        entity.removeStack(1, 1);

        entity.setStack(2, new ItemStack(registerItem.URANIUM, entity.getStack(2).getCount() + 1));
    }

    private static boolean hasRecipe(UraniumFurnaceEntity entity) {
        boolean hasItemInFirstSlot = entity.getStack(0).getItem() == registerItem.URANIUM;


        return hasItemInFirstSlot;
    }

    private static boolean hasNotReachedStackLimit(UraniumFurnaceEntity entity) {
        return entity.getStack(2).getCount() < entity.getStack(2).getMaxCount();
    }

    
    
}