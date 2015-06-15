package utils;

public class Blosum62Utils {
	private static int blosum62[][]=new int[][]{
			{4},{-1,5},{-2,0,6},{-2,-2,1,6},{0,-3,-3,-3,9},{-1,1,0,0,-3,5},{-1,0,0,2,-4,2,5},
			{0,-2,0,-1,-3,-2,-2,6},{-2,0,1,-1,-3,0,0,-2,8},
			{-1,-3,-3,-3,-1,-3,-3,-4,-3,4},{-1,-2,-3,-4,-1,-2,-3,-4,-3,2,4},
			{-1,2,0,-1,-3,1,1,-2,-1,-3,-2,5},
			{-1,-1,-2,-3,-1,0,-2,-3,-2,1,2,-1,5},
			{-2,-3,-3,-3,-2,-3,-3,-3,-1,0,0,-3,0,6},
			{-1,-2,-2,-1,-3,-1,-1,-2,-2,-3,-3,-1,-2,-4,7},
			{1,-1,1,0,-1,0,0,0,-1,-2,-2,0,-1,-2,-1,4},
			{0,-1,0,-1,-1,-1,-1,-2,-2,-1,-1,-1,-1,-2,-1,1,5},
			{-3,-3,-4,-4,-2,-2,-3,-2,-2,-3,-2,-3,-1,1,-4,-3,-2,11},
			{-2,-2,-2,-3,-2,-1,-2,-3,2,-1,-1,-2,-1,3,-3,-2,-2,2,7},
			{0,-3,-3,-3,-1,-2,-2,-3,-3,3,1,-2,1,-1,-2,-2,0,-3,-1,4}
		};
	private static char getAbbr(int n)
	{
		switch(n)
		{
			case 0:return 'A';
			case 1:return 'R';
			case 2:return 'N';
			case 3:return 'D';
			case 4:return 'C';
			case 5:return 'Q';
			case 6:return 'E';
			case 7:return 'G';
			case 8:return 'H';
			case 9:return 'I';
			case 10:return 'L';
			case 11:return 'K';
			case 12:return 'M';
			case 13:return 'F';
			case 14:return 'P';
			case 15:return 'S';
			case 16:return 'T';
			case 17:return 'W';
			case 18:return 'Y';
			case 19:return 'V';
			default: return 'X';
		}
	}

	private static int getIndex(char c)
	{
		switch(c)
		{
			case 'A':case 'a':return 0;
			case 'R':case 'r':return 1;
			case 'N':case 'n':return 2;
			case 'D':case 'd':return 3;
			case 'C':case 'c':return 4;
			case 'Q':case 'q':return 5;
			case 'E':case 'e':return 6;
			case 'G':case 'g':return 7;
			case 'H':case 'h':return 8;
			case 'I':case 'i':return 9;
			case 'L':case 'l':return 10;
			case 'K':case 'k':return 11;
			case 'M':case 'm':return 12;
			case 'F':case 'f':return 13;
			case 'P':case 'p':return 14;
			case 'S':case 's':return 15;
			case 'T':case 't':return 16;
			case 'W':case 'w':return 17;
			case 'Y':case 'y':return 18;
			case 'V':case 'v':return 19;
			default: return -1;
		}
	}
	public static double getDist(char a,char b){
		int posa=getIndex(a);
		int posb=getIndex(b);
		if(posb<=posa)
			return blosum62[posa][posb];
		else
			return blosum62[posb][posa];
	} 
}
