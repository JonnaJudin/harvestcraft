package com.pam.harvestcraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;

public class TileEntityMarket extends TileEntity implements IInventory
{
	private ItemStack paySlot = null;

	public int stockNum = 0;
        public int xCoord, yCoord, zCoord;

	private boolean isTrading = false;

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return paySlot;
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.paySlot != null)
		{
			ItemStack var3;

			if (this.paySlot.stackSize <= par2)
			{
				var3 = this.paySlot;
				this.paySlot = null;
				this.markDirty();
				return var3;
			}
			var3 = this.paySlot.splitStack(par2);

			if (this.paySlot.stackSize == 0)
			{
				this.paySlot = null;
			}

			this.markDirty();
			return var3;
		}
		return null;
	}

//	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.paySlot != null)
		{
			ItemStack var2 = this.paySlot;
			this.paySlot = null;
			return var2;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.paySlot = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	/**
	 * Returns the name of the inventory.
	 */
	public String getInvName()
	{
		return "Market";
	}

	public void takeEmeraldFromSlot(int price)
	{
		if (this.paySlot != null)
		{
			this.paySlot.stackSize -= price;
		}
		this.markDirty();
	}

	public void clearInventory()
	{
		this.paySlot = null;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		this.stockNum = par1NBTTagCompound.getInteger("StockNum");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("StockNum", stockNum);
	}

	public void setBrowsingInfo(int stockNum)
	{
		this.stockNum = stockNum;
	}

	public int getBrowsingInfo()
	{
		return stockNum;
	}

	public void setTrading(boolean isUsing)
	{
		this.isTrading = isUsing;
	}

	public boolean isTrading()
	{
		return isTrading;
	}

        @Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(new BlockPos(xCoord, yCoord, zCoord)) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void updateContainingBlockInfo()
	{
		super.updateContainingBlockInfo();
	}

//	@Override
	public void updateEntity()
	{
//		super.updateEntity();
	}

//	@Override
	public void openInventory(){}

//	@Override
	public void closeInventory()
	{
		setTrading(false);
	}

	@Override
	public void invalidate()
	{
		this.updateContainingBlockInfo();
		super.invalidate();
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return false;
	}

//	@Override
	public String getInventoryName()
	{
		return "Market";
	}

//	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

    @Override
    public ItemStack removeStackFromSlot(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void openInventory(EntityPlayer ep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeInventory(EntityPlayer ep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getField(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setField(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getFieldCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasCustomName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IChatComponent getDisplayName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}