package pass;
import java.text.DecimalFormat; class symtab {
int index;
String name; int addr;
symtab(int i, String s, int a) { this.index = i;
this.name = s; this.addr = a;
}
}
class littab { int index; String name; int addr;

littab(int i, String s, int a) { this.index = i; this.name = s;
this.addr = a;
}
}
public class Pass2 {

public static void main(String[] args) { String ic[][] = {
{"(AD,01)", null, "(C,100)"},
 
{"(IS,04)", "(RG,01)", "(L,0)"},
{"(IS,01)", "(RG,03)", "(S,0)"},
{"(DL,01)", null, "(C,3)"},
{"(IS,04)", "(RG,01)", "(S,2)"},
{"(IS,01)", "(RG,01)", "(S,3)"},
{"(IS,05)", "(RG,01)", "(S,4)"},
{"(DL,02)", null, "(C,5)"},
{"(DL,02)", null, "(C,1)"},
{"(AD,04)", null, "(C,103)"},
{"(IS,10)", null, "(S,4)"},
{"(AD,03)", null, "(C,101)"},
{"(IS,02)", "(RG,01)", "(L,2)"},
{"(IS,03)", "(RG,03)", "(S,2)"},
{"(DL,02)", null, "(C,5)"},
{"(AD,03)", null, "(C,111)"},
{"(IS,00)", null, null},
{"(DL,02)", null, "(C,19)"},
{"(AD,02)", null, null},
{"(DL,02)", null, "(C,1)"}
};
symtab s[] = new symtab[5]; s[0] = new symtab(0, "A", 102);
s[1] = new symtab(1, "L1", 105);
s[2] = new symtab(2, "B", 112);
s[3] = new symtab(3, "C", 103);
s[4] = new symtab(4, "D", 113);
littab l[] = new littab[3];
l[0] = new littab(0, "='5'", 108);
l[1] = new littab(1, "='1'", 109);
l[2] = new littab(2, "='1'", 113);
DecimalFormat df = new DecimalFormat("000"); System.out.println("--- Generated Machine Code ---");
for (int i = 0; i < ic.length; i++) { String opcodePart = ic[i][0]; String operand1 = ic[i][1]; String operand2 = ic[i][2];
String instrClass = opcodePart.substring(1, 3);
String instrCode = opcodePart.replaceAll("[^0-9]", "");
 
if (instrClass.equals("IS")) { System.out.print(instrCode + "\t");
if (operand1 == null) { System.out.print("00\t");
} else {
System.out.print(operand1.replaceAll("[^0-9]", "") + "\t");
}
if (operand2 != null) {
char operandType = operand2.charAt(1);
int operandIndex = Integer.parseInt(operand2.replaceAll("[^0-9]", ""));
if (operandType == 'S') { System.out.print(s[operandIndex].addr);
} else if (operandType == 'L') { System.out.print(l[operandIndex].addr);
}
} else {
System.out.print("000");
}
System.out.println();
} else if (instrClass.equals("DL")) { if (instrCode.equals("01")) {
System.out.print("00\t00\t");
String constant = operand2.replaceAll("[^0-9]", ""); System.out.println(df.format(Integer.parseInt(constant)));
}
}
}
}
}
