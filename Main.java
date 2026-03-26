import java.util.*;

class Subject {
    String name;
    int difficulty;
    int deadlineDays;

    Subject(String name, int difficulty, int deadlineDays) {
        this.name = name;
        this.difficulty = difficulty;
        this.deadlineDays = deadlineDays;
    }
}

class StudyPlanner {
    public static Map<String, Integer> generatePlan(List<Subject> subjects, int hours) {
        Map<String, Integer> plan = new HashMap<>();

        int totalWeight = 0;
        Map<String, Integer> weights = new HashMap<>();

        for (Subject s : subjects) {
            int weight = s.difficulty * (10 - s.deadlineDays);
            if (weight < 1) weight = 1;
            weights.put(s.name, weight);
            totalWeight += weight;
        }

        for (Subject s : subjects) {
            int time = (weights.get(s.name) * hours) / totalWeight;
            plan.put(s.name, time);
        }

        return plan;
    }
}

public class Main {
    public static void main(String[] args) {

        List<Subject> subjects = new ArrayList<>();

        subjects.add(new Subject("Math", 5, 2));
        subjects.add(new Subject("Physics", 4, 5));
        subjects.add(new Subject("English", 2, 7));

        int studyHours = 6;

        Map<String, Integer> plan = StudyPlanner.generatePlan(subjects, studyHours);

        System.out.println("Study Plan:");
        for (String subject : plan.keySet()) {
            System.out.println(subject + ": " + plan.get(subject) + " hours/day");
        }
    }
}