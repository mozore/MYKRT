import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName("java.lang.String");
        System.out.println(aClass.equals(String.class));
        if (String.class.equals(aClass)) {
            System.out.println("dasdas");
        } else if (Integer.class.equals(aClass)) {
            System.out.println("11111");
        }
    }
}
