package reflection;

import fillers.FillerMethod;
import sorters.Sort;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * Class for searching heirs of the Sort class in a concrete package.
 *
 * @author Kamyshanov Volodymyr bingooo1337@gmail.com
 * @see Sort
 */
public class SortFinder {
    /**
     * Instance of the Reflections class, which will perform a search.
     *
     * @see Reflections
     */
    private Reflections reflections;

    /**
     * Create sort finder with a specific package name.
     *
     * @param packageName package for searching heirs of the Sort class.
     */
    public SortFinder(String packageName) {
        reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(new SubTypesScanner(), new MethodAnnotationsScanner()));
    }

    /**
     * @return Set of non-abstract subclasses of class Sort.
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
