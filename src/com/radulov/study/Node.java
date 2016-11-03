package com.radulov.study;

/**
 * Created by golova on 11/2/16.
 */
public class Node
{
    public Node(Node leftChild, Node rightChild, String message) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.message = message;
    }

    Node leftChild, rightChild;
    String message;
}
