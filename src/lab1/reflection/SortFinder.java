package lab1.reflection;

import lab1.fillers.FillerMethod;
import lab1.sorters.Sort;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

public class SortFinder {
    private Reflections reflections;

    public SortFinder(String packageName) {
        reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(new SubTypesScanner(), new MethodAnnotationsScanner()));
    }

    /**
     * @return non-abstract subclasses of class Sort.
     */
    public Set<Class<? extends Sort>> findSorters() {
        Set<Class<? extends Sort>> subTypesOfSort = reflections.getSubTypesOf(Sort.class);
        subTypesOfSort.removeIf(aClass -> Modifier.isAbstract(aClass.getModifiers()));
        return subTypesOfSort;
    }

    /**
     * @return methods annotated with FillerMethod annotation.
     * @see FillerMethod
     */
    public Set<Method> findFillers() {
        return reflections.getMethodsAnnotatedWith(FillerMethod.class);
    }

}
