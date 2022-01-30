

import net.minecraft.util.math.Vec3d;

public class OBB {
    /*pos(x,y,z)*/
    public Vec3d pos;
    /*euler(yaw,pitch,roll)*/
    public Vec3d rotation;
    /*size(x,y,z)*/
    public Vec3d size;
    
    public OBB(Vec3d pos,Vec3d rotation,Vec3d size) {
        this.pos=pos;
        this.rotation=rotation;
        this.size=size;
    }
}
