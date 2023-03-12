package alloy.interactive.teaching.material.config;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class LessonConfigManager {
    // For adding lesson meta data
    private static List<LessonConfig> getConfigList() {
        return  new ArrayList<>(Arrays.asList(
                    new LessonConfig("1", "Signatures & Relations & Multiplicities"),
                    new LessonConfig("2", "Facts & Functions & Predicates"),
                    new LessonConfig("3", "All types of operators & Quantifiers")
                ));
    }

    public static boolean isValidLessonNumber(String input) {
        return getConfigList().stream().anyMatch(config -> config.lessonNumber.equals(input));
    }
}
