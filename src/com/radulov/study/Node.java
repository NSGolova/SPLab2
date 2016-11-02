package com.radulov.study;

/**
 * Created by golova on 11/2/16.
 */
public class Node {
    int ndOp;	 //код типу лексеми
    Node prvNd;// зв’язок з попередником
    Node pstNd;// зв’язок з наступником
    int dataType;	// код типу даних, які повертаються
    int resLength;  //довжина результату
    int stkLength;//довжина стека обробки семантики
    int x, y, f;//координати розміщення у вхідному файлі
    Node prnNd;//зв’язок з батьківським вузлом
}
