package pass1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
public class Pass1 
{
	public static void main(String[] args)
	{
		String input=null;
		String tt;
		//M1: file input /output setup
		try
		{
			BufferedReader br=new BufferedReader (new FileReader("C:\\Users\\panka\\eclipse-workspace\\SposPract1\\src\\pass1\\input.txt"));
			File f1=new File ("C:\\Users\\panka\\eclipse-workspace\\SposPract1\\src\\pass1\\ST.txt");
			File f2= new File("C:\\Users\\panka\\eclipse-workspace\\SposPract1\\src\\pass1\\LT.txt");
			File f3 = new File("C:\\Users\\panka\\eclipse-workspace\\SposPract1\\src\\pass1\\IC.txt");
			PrintWriter P1= new PrintWriter(f1);//ST.TXT
			PrintWriter p2= new PrintWriter(f2);
			PrintWriter p3= new PrintWriter(f3);
		//m2: Read each line & tokens
			while((input=br.readLine())!=null) 
			{
			StringTokenizer st=new StringTokenizer(input,"");
			while(st.hasMoreTokens())
			{
				tt=st.nextToken();
				System.out.println("Tokens are:"+tt);
				
			}
			}
		}catch(Exception e)
		{}
	}
}



//iput file
START 100
ADD AREG, X
X DC 2
END
  //Blank 3 File are create name is 1>ST.txt,2>LT.txt,3>IC.txt
