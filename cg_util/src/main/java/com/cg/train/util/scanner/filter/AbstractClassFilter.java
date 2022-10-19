package com.cg.train.util.scanner.filter;

import com.cg.train.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName AbstractClassFilter
 * @Description 类过滤器
 * @Author craig
 * @Date 2022/10/19 23:29
 * @Version 1.0
 */
public abstract class AbstractClassFilter {
    public static final Logger log = LoggerFactory.getLogger(AbstractClassFilter.class);
    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private final String packageName;

    protected AbstractClassFilter(final String packageName) {
        this.packageName = packageName;
    }
    protected AbstractClassFilter(final String packageName, ClassLoader classLoader) {
        this.packageName = packageName;
        this.classLoader = classLoader;
    }

    public final Set<Class<?>> getClassList() {
        Set<Class<?>> classes = new HashSet<>();

        try {
            Enumeration<URL> urls = classLoader.getResources(packageName.replace(".", "/"));
            URL url;
            while (urls.hasMoreElements()) {
                url = urls.nextElement();
                if (url != null) {
                    log.debug("scan url: {}", url);
                    //获取协议名（分别是file和jar）
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        String path = url.getPath();
                        addClass(classes, path, packageName);
                    }
                }
            }
        } catch (IOException e) {
            log.error("find class error: ", e);

        }
        return classes;
    }

    private void addClass(Set<Class<?>> classes, String packagePath, String packageName) {

        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });

        for (File file : files) {
            String name = file.getName();
            if (file.isFile()) {
                //获取类名
                String className = name.substring(0, name.length() - 6);
                if (StringUtils.isNotNullOrEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classes, className);
            }
            else {
                //获取子包
                String subPackagePath = name;
                if (StringUtils.isNotNullOrEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = name;
                if (StringUtils.isNotNullOrEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }

                //递归调用
                addClass(classes, subPackagePath, subPackageName);
            }
        }
    }

    private void doAddClass(Set<Class<?>> classes, String className) {
        //加载类
        Class<?> cls = null;
        try {
            cls = classLoader.loadClass(className);
            //判断是否可以添加类
            if (filterCondition(cls)) {
                classes.add(cls);
                log.debug("add class: {}", cls);
            }
        } catch (ClassNotFoundException e) {
            log.error("load class error:" + classes, e);
        }
    }

    /**
     * 验证是否可以添加类
     * @param cls
     * @return
     */
    public abstract boolean filterCondition(Class<?> cls);
}
