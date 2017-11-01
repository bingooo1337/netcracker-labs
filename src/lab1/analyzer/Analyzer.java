package lab1.analyzer;

import lab1.fillers.FillerMethod;
import lab1.sorters.Sort;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.*;
import java.util.*;

public class Analyzer {
    private String packageName;

    public Analyzer(String packageName) {
        this.packageName = packageName;
    }

    public Map<String, ArrayList<ArrayList<String>>> generateStatistics(Object[][] argumentsLists) {
        Map<String, ArrayList<ArrayList<String>>> statistics = new HashMap<>();

        Reflections reflectionsInstanse = getReflectionsInstanse();
        Map<String, ArrayList<int[]>> fillersAndArrays = generateArraysByFillers(reflectionsInstanse, argumentsLists);
        Set<Class<? extends Sort>> sorters = findSorters(reflectionsInstanse);

        ArrayList<String> tableHead = generateTableHead(argumentsLists);

        fillersAndArrays.forEach((filler, arrays) -> {
            ArrayList<ArrayList<String>> results = new ArrayList<>();
            results.add(tableHead);

            sorters.forEach(sorter -> {
                ArrayList<String> sorterResult = new ArrayList<>();
                sorterResult.add(sorter.getSimpleName());

                try {
                    Sort sorterInstance = sorter.newInstance();
                    arrays.forEach(array -> {
                        long startTime = System.nanoTime();
                        sorterInstance.sort(array);
                        long endTime = System.nanoTime() - startTime;
                        sorterResult.add(String.valueOf(endTime));
                    });
                    results.add(sorterResult);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            statistics.put(filler, results);
        });

        return statistics;
    }

    private Reflections getReflectionsInstanse() {
        return new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(new SubTypesScanner(), new MethodAnnotationsScanner()));
    }

    private Set<Class<? extends Sort>> findSorters(Reflections reflections) {
        Set<Class<? extends Sort>> subTypesOfSort = reflections.getSubTypesOf(Sort.class);
        subTypesOfSort.removeIf(aClass -> Modifier.isAbstract(aClass.getModifiers()));
        return subTypesOfSort;
    }

    private Set<Method> findFillers(Reflections reflections) {
        return reflections.getMethodsAnnotatedWith(FillerMethod.class);
    }

    private Map<String, ArrayList<int[]>> generateArraysByFillers(Reflections reflections, Object[][] argumentsLists) {
        Set<Method> methods = findFillers(reflections);

        Map<String, ArrayList<int[]>> fillersAndArrays = new HashMap<>();

        methods.forEach(method -> {
            ArrayList<int[]> arraysToSort = new ArrayList<>();
            Arrays.stream(argumentsLists).forEach(arguments -> {
                try {
                    arraysToSort.add((int[]) method.invoke(null, arguments));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            fillersAndArrays.put(method.getName(), arraysToSort);
        });

        return fillersAndArrays;
    }

    private ArrayList<String> generateTableHead(Object[][] argumentsLists) {
        ArrayList<String> tableHead = new ArrayList<>();
        tableHead.add("Sorting Type \\ Elements");
        for (Object[] arguments : argumentsLists) {
            tableHead.add(arguments[0].toString() + " elements");
        }
        return tableHead;
    }
}
