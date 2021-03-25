package io.github.simplexdev.simplexcore.structures.block;

import net.minecraft.server.v1_16_R3.NBTTagCompound;

public abstract class NBTBlock {
    private final NBTTagCompound nbttag;

    public NBTBlock(NBTTagCompound nbttag){
        this.nbttag = nbttag;
    }

    public NBTTagCompound getNbttag(){
        return nbttag;
    }
}
