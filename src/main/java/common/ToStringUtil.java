package common;

import javax.accessibility.Accessible;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Objects;

public class ToStringUtil {

    private ArrayList<Object> visited = new ArrayList<>();  //保存记录过的对象

    public String toString(Object o) throws IllegalAccessException {
        if (o == null)
            return "null";
        if(visited.contains(o))
            return "...";

        visited.add(o);
        Class<?> cl = o.getClass();

        //是String类型
        if(cl == String.class)
            return (String) o;

        //是数组类型
        if(cl.isArray()){
            String res = cl.getComponentType() + "[]{";
            for(int i = 0; i < Array.getLength(o); i++){
                if(i > 0)
                    res += ",";

                Object curComponent = Array.get(o, i);
                // 当前元素是基本数据类型
                if(cl.getComponentType().isPrimitive())
                    res += curComponent;
                else
                    res += this.toString(curComponent);
            }
            return res + "}";
        }

        String res = cl.getName();
        do {
            res += "[";
            Field[] declaredFields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(declaredFields, true);  //统一设置为可访问对象
            for(Field f : declaredFields){
                //不是static字段
                if(!Modifier.isStatic(f.getModifiers())){
                    if (!res.endsWith("["))
                        res += ",";

                    res += f.getName() + "=";
                    if (f.getClass().isPrimitive())
                        res += f.get(o);
                    else
                        res += this.toString(f.get(o));
                }
            }
            res += "]";
            cl = cl.getSuperclass();
        }while (cl != null);

        return res;
    }
}
