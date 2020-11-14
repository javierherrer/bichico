package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataCleaner {
    static final String FORMATO_DATOS = "([a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+)(\\/?.*)";
    static Pattern pattern = Pattern.compile(FORMATO_DATOS);

    public static String clean(String input){
        input = input.replace("'","");
        Matcher m = pattern.matcher(input);
        if (m.find()){
            return m.group(1);
        }
        else return "NO HAY";
    }
}
