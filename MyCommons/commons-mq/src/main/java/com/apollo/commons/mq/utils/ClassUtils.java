package com.apollo.commons.mq.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
//import java.util.function.Predicate;
//import java.util.stream.Stream;

/**
 * com.apollo.commons.mq.utils.ClassUtils <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : 876551724@qq.com
 */
public class ClassUtils {
//    /**
//     * 获取类clazz的所有Field，包括其父类的Field，如果重名，以子类Field为准。
//     * @param clazz
//     * @return Field数组
//     */
//    public static Field[] getAllFieldsWithRoot(Class<?> clazz) {
//        return getAllFieldsWithRoot(clazz, null);
//    }

//    /**
//     * 获取类clazz的所有Field，包括其父类的Field，如果重名，以子类Field为准。无静态属性
//     * @param clazz
//     * @return Field数组
//     */
//    public static Field[] getAllFieldsWithRootNoStatic(Class<?> clazz) {
//        return getAllFieldsWithRoot(clazz, item -> !Modifier.isStatic(item.getModifiers()));
//    }
//
//    /**
//     * 获取类clazz的所有Field，包括其父类的Field，如果重名，以子类Field为准。
//     * @param clazz
//     * @return Field数组
//     */
//    public static Field[] getAllFieldsWithRoot(Class<?> clazz, Predicate<Field> predicate) {
//        List<Field> fieldList = new ArrayList<Field>();
//        Field[] dFields = clazz.getDeclaredFields();
//        if (null != dFields && dFields.length > 0) fieldList.addAll(Arrays.asList(dFields));
//
//        // 若父类是Object，则直接返回当前Field列表
//        Class<?> superClass = clazz.getSuperclass();
//        if (superClass == Object.class) return dFields;
//
//        // 递归查询父类的field列表
//        Field[] superFields = getAllFieldsWithRoot(superClass);
//        if (null != superFields && superFields.length > 0) {
//            Stream.of(superFields).filter(field -> !fieldList.contains(field))
//                    .filter(field -> predicate == null || predicate.test(field))
//                    .forEach(field -> fieldList.add(field));
//        }
//
//        Field[] result=new Field[fieldList.size()];
//        fieldList.toArray(result);
//        return result;
//    }
//
//    /**
//     * 根据field名字和目标class获取field字段
//     * @param clazz
//     * @param name
//     * @return
//     */
//    public static Field getFieldWithRoot(Class<?> clazz, String name){
//        Field[] dFields = getAllFieldsWithRoot(clazz);
//        return Stream.of(dFields).filter(field -> field.getName().equals(name)).findFirst().orElse(null);
//    }

    /**
     * 获取Class类下的所有声明的字段的filed字段
     * @param targetClass
     * @return
     */
    public static Field[] getAllFields(Class targetClass){
        return targetClass.getDeclaredFields();
    }

    /**
     * Scans all classes accessible from the context class loader which belong
     * to the given package and subpackages.
     *
     * @param packageName
     *            The network package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements())
        {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (File directory : dirs)
        {
            classes.addAll(findClasses(directory, packageName));
        }

        return classes;
    }

    /**
     * Recursive method used to find all classes in a given directory and
     * subdirs.
     *
     * @param directory
     *            The network directory
     * @param packageName
     *            The package name for classes found inside the network directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException
    {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists())
        {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files)
        {
            if (file.isDirectory())
            {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            }
            else if (file.getName().endsWith(".class"))
            {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
