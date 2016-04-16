package com.hackday.cmd;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class ExecTask {

    public static String execTask(final String path, final String name) {
        try {
            StringBuilder result = new StringBuilder();
            List<String> commands = new ArrayList<String>() {{
                add("D:");
                add("cd " + path);
                add("javac -cp . "+name+".java");
                add("java -cp "+path+". "+name );
            }};
            Process p = Runtime.getRuntime().exec("cmd /c " + String.join(" & ", commands));
            p.waitFor();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(p.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            BufferedReader readerErrors = new BufferedReader(
                    new InputStreamReader(p.getErrorStream())
            );
            String lineError;
            while ((lineError = readerErrors.readLine()) != null) {
                result.append(lineError + "\\n");
            }
            p.destroy();
            return result.toString();
        } catch (IOException | InterruptedException ignored) {
            return "ERROR";
        }
    }
}
