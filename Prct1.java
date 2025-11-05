package Assembler; 
import java.io.*;
class SymTab {
public static void main(String args[]) throws Exception 
	{ 
		FileReader FP = new FileReader(args[0]);
		BufferedReader bufferedReader = new BufferedReader(FP);
		String line = null;
		int line_count = 0, LC = 0;
		int symTabLine = 0, opTabLine = 0, litTabLine = 0, poolTabLine = 0;
		final int MAX = 100;
		String SymbolTab[][] = new String[MAX][3]; 
		String OpTab[][] = new String[MAX][3]; 
		String LitTab[][] = new String[MAX][2];
		int PoolTab[] = new int[MAX]; int litTabAddress = 0;
while ((line = bufferedReader.readLine()) != null) 
{ 
	String[] tokens = line.trim().split("\\s+");
	for (int i = 0; i < tokens.length; i++) 
	System.out.print(tokens[i] + "\t");
	System.out.println("");

	if (tokens.length > 1 && tokens[1].equalsIgnoreCase("START")) 
{ 
	LC = Integer.parseInt(tokens[2]);
	continue;
}
	if (!tokens[0].equals("")) 
{
 
	SymbolTab[symTabLine][0] = tokens[0]; 
	SymbolTab[symTabLine][1] = Integer.toString(LC); 
	SymbolTab[symTabLine][2] = Integer.toString(1); 
	symTabLine++;
}

	if (tokens.length > 1 && (tokens[1].equalsIgnoreCase("DS") || tokens[1].equalsIgnoreCase("DC")))
{
	SymbolTab[symTabLine][0] = tokens[0]; SymbolTab[symTabLine][1] = Integer.toString(LC); 
	SymbolTab[symTabLine][2] = Integer.toString(1); 
	symTabLine++;
}
	if (tokens.length == 3 && tokens[2].charAt(0) == '=') 
	{
		LitTab[litTabLine][0] = tokens[2]; 
		LitTab[litTabLine][1] = Integer.toString(LC); 
		litTabLine++;
}
if (tokens.length > 1) 
{ 
	OpTab[opTabLine][0] = tokens[1];

	if (tokens[1].equalsIgnoreCase("START") || tokens[1].equalsIgnoreCase("END") || tokens[1].equalsIgnoreCase("ORIGIN") || tokens[1].equalsIgnoreCase("EQU") || tokens[1].equalsIgnoreCase("LTORG")) {
	OpTab[opTabLine][1] = "AD"; OpTab[opTabLine][2] = "R11";
}
	else if (tokens[1].equalsIgnoreCase("DS") || tokens[1].equalsIgnoreCase("DC")) 
	{
		OpTab[opTabLine][1] = "DL";
		OpTab[opTabLine][2] = "R7";
	} 
	else 
{
	OpTab[opTabLine][1] = "IS"; OpTab[opTabLine][2] = "(04,1)";
}
	opTabLine++;
}
	line_count++; LC++;
}

		System.out.println("\n\nSYMBOL TABLE"); 
		System.out.println("SYMBOL\tADDRESS\tLENGTH");
 
for (int i = 0; i < symTabLine; i++)
	System.out.println(SymbolTab[i][0] + "\t" + SymbolTab[i][1] + "\t" + SymbolTab[i][2]);
	System.out.println("\n\nOPCODE TABLE"); 
	System.out.println("MNEMONIC\tCLASS\tINFO"); 
		for (int i = 0; i < opTabLine; i++)
	System.out.println(OpTab[i][0] + "\t\t" + OpTab[i][1] + "\t" + OpTab[i][2]);
	System.out.println("\n\nLITERAL TABLE"); 
	System.out.println("LITERAL\tADDRESS"); for (int i = 0; i < litTabLine; i++)
	System.out.println(LitTab[i][0] + "\t" + LitTab[i][1]);
for (int i = 0; i < litTabLine - 1; i++) 
{
	if (LitTab[i][0] != null && LitTab[i + 1][0] != null) 
{ 
	if (i == 0) {
	PoolTab[poolTabLine] = i + 1; poolTabLine++;
} 
	else if 
		(Integer.parseInt(LitTab[i][1]) < (Integer.parseInt(LitTab[i + 1][1]) - 1))
	{
		PoolTab[poolTabLine] = i + 2;
		poolTabLine++;
	}
}
}
System.out.println("\n\nPOOL TABLE"); 
System.out.println("LITERAL NUMBER"); 
	for (int i = 0; i < poolTabLine; i++)
		System.out.println(PoolTab[i]);
		bufferedReader.close();
}
}


//input File
START 100 READ A
LABEL MOVER	AREG, B LTORG
MOVER	AREG, ='5'
ADD	AREG, ='1' MOVEM	AREG, A
 
LTORG
MOVER	BREG, ='2' LOOP READ B
MOVEM	BREG, B
A	DS	1
B	DC	1 END

