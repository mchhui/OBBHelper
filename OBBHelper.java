package mchhui.he.util;

import javax.vecmath.Vector3d;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
/**
 * MIT Licence
 * @author Hueihuea
 * */
public class OBBHelper {
    public static boolean testCollision(OBB obb1, OBB obb2) {
        OBB[] obbs = new OBB[] { obb1, obb2 };
        Vec3d[] axis = new Vec3d[3];
        axis[0] = new Vec3d(1, 0, 0);
        axis[1] = new Vec3d(0, 1, 0);
        axis[2] = new Vec3d(0, 0, 1);
        Vector3d obb1VecX = rotate(axis[0].scale(obb1.size.x), obb1.rotation.x, obb1.rotation.y, obb1.rotation.z);
        Vector3d obb1VecY = rotate(axis[1].scale(obb1.size.y), obb1.rotation.x, obb1.rotation.y, obb1.rotation.z);
        Vector3d obb1VecZ = rotate(axis[2].scale(obb1.size.z), obb1.rotation.x, obb1.rotation.y, obb1.rotation.z);
        Vector3d obb2VecX = rotate(axis[0].scale(obb2.size.x), obb2.rotation.x, obb2.rotation.y, obb2.rotation.z);
        Vector3d obb2VecY = rotate(axis[1].scale(obb2.size.y), obb2.rotation.x, obb2.rotation.y, obb2.rotation.z);
        Vector3d obb2VecZ = rotate(axis[2].scale(obb2.size.z), obb2.rotation.x, obb2.rotation.y, obb2.rotation.z);
        Vector3d axiVec;
        double proj1;
        double proj2;
        double projAB;
        for (OBB obb : obbs) {
            for (Vec3d axi : axis) {
                axiVec= rotate(axi, obb.rotation.x, obb.rotation.y, obb.rotation.z);
                proj1= projectionFast(obb1VecX, axiVec) + projectionFast(obb1VecY, axiVec)
                        + projectionFast(obb1VecZ, axiVec);
                proj2= projectionFast(obb2VecX, axiVec) + projectionFast(obb2VecY, axiVec)
                        + projectionFast(obb2VecZ, axiVec);
                obb2.pos.sub(obb1.pos);
                projAB= projectionFast(obb2.pos, axiVec);
                if (projAB > proj1 + proj2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Vector3d rotate(Vec3d vec, double yaw, double pitch, double roll) {
        float x = (float) vec.x;
        float y = (float) vec.y;
        float z = (float) vec.z;
        yaw=Math.toRadians(yaw);
        pitch=Math.toRadians(pitch);
        roll=Math.toRadians(roll);

        float cos = MathHelper.cos((float) (yaw));
        float sin = MathHelper.sin((float) (yaw));
        float px = z;
        float py = x;
        float x0 = px * cos - py * sin;
        float y0 = px * sin + py * cos;
        z = x0;
        x = y0;

        cos = MathHelper.cos((float) (pitch));
        sin = MathHelper.sin((float) (pitch));
        px = z;
        py = y;
        x0 = px * cos - py * sin;
        y0 = px * sin + py * cos;
        z = x0;
        y = y0;

        cos = MathHelper.cos((float) (roll));
        sin = MathHelper.sin((float) (roll));
        px = x;
        py = y;
        x0 = px * cos - py * sin;
        y0 = px * sin + py * cos;
        x = x0;
        y = y0;

        return new Vector3d(x,y,z);
    }

    public static double projectionFast(Vector3d vec1, Vector3d vec2) {
        double delta = vec1.dot(vec2);
        return Math.abs(delta);
    }
    
    public static double projection(Vector3d vec1, Vector3d vec2) {
        double delta = vec1.dot(vec2) / (vec1.length() * vec2.length());
        if (Double.isNaN(delta)) {
            return 0;
        }
        return Math.abs(vec1.length() * delta);
    }
}
