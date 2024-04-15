package com.mjc.school.controller.utils;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

import static com.mjc.school.controller.utils.Constant.*;

public class Utils {

    private static List<Class> classes = null;

    public static List<Class> getClasses() {
        if (classes == null) {
            classes = getProjectClasses();
        }
        return classes;
    }

    public static List<Class> getControllerPackage() {
        return getClasses().stream()
                .filter(
                        clazz ->
                                clazz.getName().contains(MODULE_DIRECTORY_PATH)
                                        && clazz.getName().contains(CONTROLLER_DIRECTORY_PATH))
                .collect(Collectors.toList());
    }

    public static List<Class> getControllerImplementationPackageClasses() {
        List<Class> classes = new ArrayList<>();
        List<Class> ControllerClasses = getControllerPackage();
        for (Class controllerClass : ControllerClasses) {
            if (isExistImplementDirectoryNameInClassPathName(controllerClass)) {
                classes.add(controllerClass);
            }
        }
        return classes;
    }

    private static boolean isExistImplementDirectoryNameInClassPathName(Class clazz) {
        String[] splittedClassName = clazz.getName().split("\\.");
        return Arrays.stream(splittedClassName)
                .anyMatch(x -> x.equals(IMPLEMENTATION_DIRECTORY) || x.equals(IMPL_DIRECTORY));
    }

    private static List<Class> getProjectClasses() {
        classes = new ArrayList<>();
        String jarFilePath =
                "jar:file:".concat(BUILD_PATH).concat(getProjectName().concat(JAR_EXTENSION).concat("!/"));
        try (URLClassLoader cl = URLClassLoader.newInstance(new URL[] {new URL(jarFilePath)})) {
            for (String name : getJarClassesName()) {
                if (name.contains(SERVICE_MODULE_DIRECTORY)
                        || name.contains(REPOSITORY_MODULE_DIRECTORY)
                        || name.contains(WEB_MODULE_DIRECTORY)) {
                    classes.add(cl.loadClass(name));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return classes;
    }

    private static List<String> getJarClassesName() {
        File jarFilePath = new File(BUILD_PATH.concat(getProjectName().concat(JAR_EXTENSION)));
        List<String> classNames = new ArrayList<>();
        try (JarFile jarFile = new JarFile(jarFilePath)) {
            Enumeration<JarEntry> e = jarFile.entries();
            while (e.hasMoreElements()) {
                JarEntry jarEntry = e.nextElement();
                if (jarEntry.getName().endsWith(JAVA_CLASSES_EXTENSION)) {
                    String className =
                            jarEntry.getName().replace("/", ".").replace(JAVA_CLASSES_EXTENSION, "");
                    classNames.add(className);
                }
            }
            return classNames;
        } catch (Exception ex) {
            System.out.println("Can not run test suites. Project build is missing");
            ex.printStackTrace();
        }
        return classNames;
    }

    private static String getProjectName() {
        String userDir = System.getProperty(USER_DIRECTORY);
        Path path = Paths.get(userDir);
        return path.getFileName().toString();
    }
}