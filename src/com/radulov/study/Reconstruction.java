package com.radulov.study;

/**
 * Created by golova on 11/2/16.
 */
public class Reconstruction {

    char fpr[]= {0,0x4f,0x4f,0x4f,0x46,0x11,6,0x12,
                    0x13,0x4e,0x12,0x1, 0x4e,0x4e,0x4e,0x4e,
                    0x4e,0x4e,0x13,0x4e, 0x4e, 0x9, 0x9, 0x9, 0x4e,
                    0x4e,0x4e,0x4e,0x4e, 0x42, 0x2,
                    0x42,2,0x43,3, 0x44,4,0x45,5,
                    0x1, 0x1, 0x1, 0x2, 0x2, 0x43,
                    0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10,
                    0x10, 0x10, 0x10, 0x3C,0x3C,
                    0x1A, 0x1A, 0x1C, 0x1C, 0x1A, 0x1A,
                    0x20, 0x20, 0x30, 0x30,
                    0x3E,0x3E, 0x34,0x1E,0x1E,0x1C, 0x1C,
                    0x3C,0x3C,0x3C,0x3C, 0x16,0x18,0x17,0x17, 0x4e,
                    0x30,0x16,0x18, 0x17, 0x1E, 0x1E, 0x13, 0x15,
                    0x4e,0x4e,0x30},
        gpr[]= {0, 0x4f, 0x4f, 0x4f, 6,0x12,6,0x12,
        0x13,0x4e,0x12,0x1, 0x12,0x1,0x4e,0x4e,
        0x11,0x1,0x1,0x11, 0x11, 0x9, 0x9, 0x9, 0x11,
        0x01,0x01,0x01,0x01, 0x2, 0x2,
        2,2, 3,3, 4,4, 5,5, 1,1, 1, 2,2, 0x43,
        0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10,
        0x10, 0x10, 0x10, 0x3C,0x3C,
        0x1A, 0x1A, 0x1C, 0x1C, 0x1A, 0x1A,
        0x20, 0x20, 0x30, 0x30,
        0x3E,0x3E, 0x34,0x1E,0x1E,0x1C, 0x1C,
        0x3C,0x3C,0x3C,0x3C, 0x16,0x18,0x17,0x17, 0x4e,
        0x30,0x16,0x18,0x17, 0x1E, 0x1E, 0x13, 0x15,
        0x4e, 0x4e, 0x30};

    String oprtr[] = {"", "", "", "",
            "if", "then", "else", "elseif",
            "case", "switch", "defualt", ""/*endcase*/,
            "break", "return", "while", "while", "continue",
            "do", "while", "do", "for", ";", ";", ";",
            "while", "do", "with", "endif",
            "goto", "extern", "var", "const",
            "enum", "struct", "union", "register",//
            "unsigned", "signed", "char", "short",
            "int","long","int64","int64",//
            "float", "double", "void", "auto",
            "static", "volatile", "typedef", "sizeof",//
            "real", "array", "set", "file",
            "object", "string", "label",
            "int main()","function", "procedure",
            "","","","","","","","",			//V+8
            "","","","","","","","","","","",	//V+19
            "","","","","",				//V+24
            "var","","","","","","",			//V+31
            "","","","","","","","","",		//V+40
            "","","","","","",				//V+46
            "inline", "forward", "interrupt", "export",
            "extern", "_asm", "","","",  //Verilog|SQL+3
            "object", "constructor", "destructor",
            "property", "resP", "abstract",		//P+9
            "class", "public", "private", "protected",
            "virtual", "friend",				//C++15
            "new","delete","try","catch","throw",//C++20
            "\nfork", "join",
            "\n{", "}", "{", "}", "[", "]", "(", ")",
            ",;\n",".;\n",";\n", ",", ":", "?",
            "|=", "&=", "^=", "+=", "-=", "*=", "/=", "%=",
            "<<=", ">>=", "=", "--", "++",
            "<", "<=", "==", "!=", ">=", ">",
            "+", "-", "*", "/",
            ".", "->", "**", "<<<", ">>>", "===", "!==",
            "+", "-", "*", "&", "~|", "~&", "~^", "^~", "&",
            "%", "|", "&", "^", "<<", ">>", "||", "&&",
            "!","~","/"};

    String cprC[] = {"", "", "", "", "\1\5y", "", "", "",
            "\7switch\5\n{\1y\377z\1 y", "", "", "\4;\n\376}",
            "","\1x","\1\5y","x\1\5","","\1x","x\1(!\5)","\1\4",
            "\1\5y", "", "", "", "\1(!\4)y", "", "", "",
            "", "", "", "",	"\1\4\4", "", "", "",
        "\4\1y","\4\1y","\4\1y","\4\1y","\4\1y","\4\1y",
                "\4\1y","\4\1y",	"\4\1y","\4\1y","\4\1y",
                "", "", "", "", "", "", "", "", "", "", ""
    };

    int begOprtr = 0x50;

    void prLxTxt(Node rt) {

        Node rt0; // робочий вказівник
        char n = 0, c, bC = 0, opCnt = 0;
        if(rt.ndOp <= _cnst) {
            if(rt.ndOp != _nil) {
                if(mode == 1 && rt.ndOp < begOprtr - 8) {
                    System.out.println(" ");
                }
                System.out.println(rt.prvNd);
                mode = 1;
            }
        }
        else
            while((c = cpr[rt.ndOp][n]) != 0) {
                n++;
                switch(c) {
                    case 7:
                        if(mdCnt == 0) {
                            mdCnt =- 1;
                        } else {
                            while (c != -1) {
                                c = cpr[rt.ndOp][n++];
                            }
                            c = 0;
                        }
                        break;
                case 6: opCnt--;// повернення до першого аргументу
                case (char)-1: break;
                case (char)-2: mdCnt=0; break;
                case 1:
                    if(mode != 0 && rt.ndOp >= _if && rt.ndOp < begOprtr - 8)
                        System.out.println(" ");
                    printf("%s",oprtr[rt.ndOp]);
                    if(rt->ndOp>=begOprtr-8)mode=0;
                    else mode=1; break;
                case 'x': case 'y': case 5:case 4:
                if(opCnt)rt0=rt->pstNd;// вибір аргументу
                else rt0=rt->prvNd;//перевірка потреби обрамлення
                if(c=='y')bC=fpr[rt->ndOp]>fpr[rt0->ndOp]&&rt0;
                else if(c==5)bC=1; else if(c==4)bC=0;
                else bC=fpr[rt->ndOp]>=fpr[rt0->ndOp]&&rt0;
                if(bC)prOpBr(rt0);	// обрамлення дужками
                prLxTxt(rt0);  // рекурсивний виклик відтворення
                if(bC)prClBr(rt0);	// обрамлення дужками
                case 'z': prLxTxt(rt->pstNd); break;
                default: printf("%c",c);
            }if(c==-1&&mdCnt!=0)break; //
        }}
}
}
