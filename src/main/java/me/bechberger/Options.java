package me.bechberger;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Optional;

public class Options {
    /**
     * interval option
     */
    private Duration interval = Duration.ofMillis(10);
    /**
     * flamegraph option
     */
    private Optional<Path> flamePath = Optional.empty();

    /**
     * table option
     */
    private boolean printMethodTable = true;

    private void printHelp() {
        System.out.println("Usage: java -javaagent:tiny-profiler.jar=options ...\n" +
                "Options:\n" +
                "    help: Print this help message\n" +
                "    interval: The interval in milliseconds between samples (default: 10)\n" +
                "    flamegraph: The path to the output file (default: no output)\n" +
                "    table: Print the method table? (default: true)");


    }

    private void optionsError(String msg) {
        System.err.println(msg);
        printHelp();
        System.exit(1);
    }

    private void initOptions(String agentArgs) {
        if (agentArgs == null || agentArgs.isEmpty()) {
            return;
        }
        for (String part : agentArgs.split(",")) {
            String[] kv = part.split("=");
            if (kv.length != 2) {
                optionsError("Invalid argument: " + part);
            }
            String key = kv[0];
            String value = kv[1];
            switch (key) {
                case "help" : printHelp(); break;
                case "interval" : interval = Duration.ofNanos((long) (Double.parseDouble(value) * 1000000)); break;
                case "flamegraph" : flamePath = Optional.of(Path.of(value)); break;
                case "table" : printMethodTable = Boolean.parseBoolean(value); break;
                default : optionsError("Unknown argument: " + key); break;
            }
        }
    }

    public Options(String agentArgs) {
        initOptions(agentArgs);
    }

    public Duration getInterval() {
        return interval;
    }

    public Optional<Path> getFlamePath() {
        return flamePath;
    }

    public boolean printMethodTable() {
        return printMethodTable;
    }
}
