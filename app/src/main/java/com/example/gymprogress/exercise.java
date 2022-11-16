package com.example.gymprogress;

public class exercise {
    private long _id;
    private String exercise_name;
    private String exercise_weight;
    private String exercise_type;

    public exercise(long _id, String exercise_name, String exercise_weight, String exercise_type) {
        this._id = _id;
        this.exercise_name = exercise_name;
        this.exercise_weight = exercise_weight;
        this.exercise_type = exercise_type;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public String getExercise_weight() {
        return exercise_weight;
    }

    public void setExercise_weight(String exercise_weight) {
        this.exercise_weight = exercise_weight;
    }

    public String getExercise_type() {
        return exercise_type;
    }

    public void setExercise_type(String exercise_type) {
        this.exercise_type = exercise_type;
    }

    @Override
    public String toString() {
        return "\n" + exercise_name + ": "
                + exercise_weight + " Kg";
    }
}
