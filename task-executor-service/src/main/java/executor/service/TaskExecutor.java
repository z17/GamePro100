package executor.service;


import lombok.val;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class TaskExecutor {
    final Path taskFolder;
    public TaskExecutor(final Path taskFolder) {
        this.taskFolder = taskFolder;
    }

    public TaskResult execTask() {
        // todo: refactor this
        val name = "Main";
        final TaskResult result;
        val absolutePath = taskFolder.toAbsolutePath();
        try {
            final List<String> commands = new ArrayList<String>() {{
                add("cd " + absolutePath);
                add("javac " + name + ".java");
                add("java " + name);
            }};
            final Process p = Runtime.getRuntime().exec("cmd /c " + String.join(" & ", commands));
            p.waitFor();


            final String stringError = getErrorsFromStream(p.getErrorStream());
            if (stringError.length() > 0) {
                result = TaskResult.ErrorResult(stringError);
            } else {
                result = TaskResult.SuccessResult(getFromStream(p.getInputStream()));
            }

            p.destroy();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("error create task");
        }
        return result;
    }


    private String getFromStream(final InputStream inputStream) throws IOException {

        final BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream)
        );

        final StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    private String getErrorsFromStream(final InputStream inputStream) throws IOException {

        final BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream)
        );
        final StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
