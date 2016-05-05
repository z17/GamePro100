package com.hackday.cmd;


import com.hackday.results.TaskResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class ExecTask {

    public static TaskResult execTask(final String path, final String name) {
        TaskResult result = new TaskResult();
        try {
            StringBuilder buffer = new StringBuilder();
            List<String> commands = new ArrayList<String>() {{
                add("cd " + path);
                add("javac -cp . "+name+".java");
                add("java -cp "+path+". "+name );
            }};
            Process p = Runtime.getRuntime().exec("cmd /c " + String.join(" & ", commands));
            p.waitFor();

            result.text = getFromStream(p.getInputStream());

            result.status = TaskResult.Status.COMPLETED;

            String stringError = getErrorsFromStream(p.getErrorStream());
            if (stringError.length() > 0) {
                result.text = stringError;
                result.status = TaskResult.Status.ERROR;
            }

            p.destroy();
        } catch (IOException | InterruptedException ignored) {
        }
        return result;
    }


    private static String getFromStream(InputStream inputStream) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream)
        );

        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    private static String getErrorsFromStream(InputStream inputStream) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream)
        );
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
