package com.radulov.study;

/**
 * Created by golova on 11/2/16.
 */
public class GraphModel {

    private int startIndex, finishIndex, currentStep = -1;
    private String[][] stepsMatrix;

    public GraphModel(int startIndex, int finishIndex)
    {
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;

        stepsMatrix = new String[finishIndex - startIndex + 1][finishIndex - startIndex + 1];
    }

    public void addStep(int fromIndex, int toIndex, String onSignal)
    {
        stepsMatrix[fromIndex - startIndex][toIndex - startIndex] = onSignal;
        for (String[] array : stepsMatrix)
        {
            for (String s : array) {
                System.out.print(s == null ? "--- " : s + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int nextStep(String signal)
    {
        int result = -1;
        if (currentStep + startIndex != finishIndex) {
            if (currentStep == -1) {
                result = startIndex;
                currentStep++;
            } else {
                for (int i = 0; i < stepsMatrix[currentStep].length; i++) {
                    if (stepsMatrix[currentStep][i] != null && stepsMatrix[currentStep][i].equalsIgnoreCase(signal)) {
                        result = startIndex + i;
                        currentStep = i;
                    }
                }
            }

            if (currentStep != stepsMatrix.length && result == -1 && stepsMatrix[currentStep][currentStep + 1] == null) {
                result = startIndex + 1 + currentStep;
                currentStep++;
            }
        } else {
            result = Integer.MAX_VALUE;
        }
        return result;
    }
}
