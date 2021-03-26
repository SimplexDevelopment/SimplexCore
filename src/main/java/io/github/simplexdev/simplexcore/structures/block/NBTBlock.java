package io.github.simplexdev.simplexcore.structures.block;

import io.github.simplexdev.simplexcore.structures.exception.InvalidSchematic;

import java.util.Vector;

public abstract class NBTBlock {
    private  Object nbttag;

    public Object getNbttag(){
        return nbttag;
    }

    public abstract Vector getOffset() throws InvalidSchematic;

    public abstract boolean isEmpty();


}
