package org.poo.main.objects;

import org.poo.fileio.ObjectInput;

public class Input {
    private static Input instance;
    public ObjectInput inputData;

    public Input(ObjectInput inputData) {
        this.inputData = inputData;
    }
    public static Input getInstance(ObjectInput inputData) {
        if (instance == null) {
            instance = new Input(inputData);
        }
        return instance;
    }
    public static void deleteInstance() {
        instance = null;
    }
}
