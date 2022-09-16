import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {

        String text = readFile("input.txt");
        Pattern patternIp4 = Pattern.compile("([0-9]{1,3}[\\.]){3}[0-9]{1,3}");
        Pattern patternIp6 = Pattern.compile("((^|:)([0-9a-fA-F]{0,4})){1,8}");

        Matcher matcherIp4 = patternIp4.matcher(text);
        Matcher matcherIp6 = patternIp6.matcher(text);

        List ip = new ArrayList();
        while (matcherIp4.find()) {
            int start=matcherIp4.start();
            int end=matcherIp4.end();
            ip.add(text.substring(start,end));
        }
        while (matcherIp6.find()) {
            int start=matcherIp6.start();
            int end=matcherIp6.end();
            ip.add(text.substring(start,end));
        }

        Collections.sort(ip);
        System.out.println(ip);
        writeHtml(ip, "output.txt");
    }

    public static String readFile(String filePath) {
        try (FileReader reader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String text = "";
            while(bufferedReader.ready()) {
                text = text.concat(bufferedReader.readLine());
            }
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeHtml(List list, String filePath) {
        try (FileWriter writer = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(String.valueOf(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
