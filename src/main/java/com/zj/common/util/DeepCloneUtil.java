package com.zj.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by zhangjun16 on 2018/1/15.
 */
public class DeepCloneUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeepCloneUtil.class);

    public static <T extends Serializable> T clone(T t){
        T cloned = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(t);
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            cloned = (T)ois.readObject();
        } catch (Exception e) {
            LOGGER.error("DeepCloneUtil.copy error", e);
        }finally {
            try{
                if(bos != null) bos.close();
                if(oos != null) oos.close();
                if(bis != null) bis.close();
                if(ois != null) ois.close();
            }catch(IOException e){
            }
        }
        return cloned;
    }

}
