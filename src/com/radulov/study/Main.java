package com.radulov.study;

public class Main {

    public static void main(String[] args) {
	    GraphModel model = new GraphModel(2,7);
        model.addStep(3, 7, "dlm");
        model.addStep(3, 3, "cfr");
        model.addStep(2, 4, "ltr");

        String[] signals = {"dlm", "dlm", "cfr", "cfr", "ltr", "dlm", "dlm", "dlm", "dlm", "dlm"};

        for (String signal : signals) {
            System.out.println(model.nextStep(signal));
        }

        System.out.println();
        System.out.println(Reconstruction.reconstruct(new Node(new Node(null, null, "b "), new Node(new Node(null, null, "0"), null, "; "), ":= ")));
        System.out.println(Reconstruction.reconstruct(new Node(new Node(new Node(null, new Node(null, null, "n"), "for "),new Node(null, null, "n "), ":= "), new Node(new Node(null, null, "0 "), null, "do "),"down to ")));
        System.out.println(Reconstruction.reconstruct(new Node(new Node(new Node(null, null, "b "), new Node(null, null, "b "), ":= "), new Node(new Node(null, null, "a"), new Node(new Node(null, null, "n"), new Node(null, null, ";"), "]"), "["), "+ ")));

    }
}
