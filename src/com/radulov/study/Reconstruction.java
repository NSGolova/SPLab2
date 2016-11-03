package com.radulov.study;

/**
 * Created by golova on 11/2/16.
 */
public class Reconstruction {

    public static String reconstruct(Node head)
    {
        String result = "";
        if (head.leftChild == null)
        {
            result += head.message;
            if (head.rightChild != null)
            {
                result += reconstruct(head.rightChild);
            }
        }
        else
        {
            result += reconstruct(head.leftChild);
            result += head.message;
            if (head.rightChild != null)
            {
                result += reconstruct(head.rightChild);
            }
        }
        return result;
    }

}
