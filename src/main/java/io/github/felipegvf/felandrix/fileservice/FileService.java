package io.github.felipegvf.felandrix.fileservice;

import com.opencsv.CSVWriter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public <T> BlaBla listToResource(List<T> list, Class<T> clazz) throws InvocationTargetException, IllegalAccessException, IOException {
        File tempFile = File.createTempFile("test", ".csv");
        tempFile.deleteOnExit();
        CSVWriter writer = new CSVWriter(new FileWriter(tempFile),';', '"', '"', "\n");

        var methods = clazz.getDeclaredMethods();
        for (T t : list) {
            List<String> stringList = new ArrayList<>();
            for (Method method : methods) {
                if (method.isAnnotationPresent(ListToResource.class)) {
                    ListToResource annotation = method.getDeclaredAnnotation(ListToResource.class);

                    if (annotation.type() == DataType.DOUBLE){
                        stringList.add(Double.toString((Double) method.invoke(t)));
                    }

                    if (annotation.type() == DataType.LONG){
                        stringList.add(Long.toString((Long) method.invoke(t)));
                    }

                    if (annotation.type() == DataType.STRING){
                        stringList.add((String) method.invoke(t));
                    }

                }
            }
            writer.writeNext(stringList.toArray(new String[0]));
        }
        writer.close();
        Resource resource = new InputStreamResource(new FileInputStream(tempFile));
        long length = tempFile.length();
        BlaBla b = new BlaBla();
        b.length = length;
        b.resource = resource;
        return b;
    }

}
