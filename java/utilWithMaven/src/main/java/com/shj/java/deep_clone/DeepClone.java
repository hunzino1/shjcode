package main.java.com.shj.java.deep_clone;

import java.io.*;

/**
 * @create 2018/9/10
 */
public class DeepClone {
    public static void main(String[] args) {
        CloneObject object = new CloneObject();

        try {
            //深拷贝
            CloneObject cloneObject = (CloneObject) object.deepClone();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

class CloneObject implements Serializable {
    public Object deepClone() throws Exception {
        //将对象写入流中
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);

        //从流中读出
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);

        return oi.readObject();
    }
}
