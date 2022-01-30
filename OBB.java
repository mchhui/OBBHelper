package mchhui.he.util;

import javax.vecmath.Vector3d;
/**
 * MIT Licence
 * @author Hueihuea
 * */
public class OBB {
    /*pos(x,y,z)*/
    public Vector3d pos;
    /*euler(yaw,pitch,roll)*/
    public Vector3d rotation;
    /*size(x,y,z)*/
    public Vector3d size;
    
    public OBB(Vector3d pos,Vector3d rotation,Vector3d size) {
        this.pos=pos;
        this.rotation=rotation;
        this.size=size;
    }
}
