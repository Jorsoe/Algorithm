package WorkQuestion;

import java.util.Scanner;

public class WorkQuestion {

	int n = 0;																		//表示工人人数
	static int MinPrice = 0;														//表示最少的费用
	int[] character;																//character[n]表示第n个工作的分配状态，已分配则为1，未分配为0
	int[][] price;																	//price[i][j]表示第i号工人完成第j号工作所需的费用
	int[][] record = new int[10][10];
	int[][] FinalRecord = new int[10][10];
	
	void SetWorkThing()																//用来初始化人数以及price表
	{
		System.out.print("Please input number of individual:");
		Scanner in = new Scanner(System.in);
		n = in.nextInt();															//初始化工人人数
		character = new int[n+1];													//为了与price表统一，表示工作的分配状态
		price = new int[n+1][n+1];													//给数组设置长度为n+1的原因是在调用BackTrack函数时，初始值为1，所以price表必须与工人号统一，从1开始
		System.out.println("Please input every price of every individual:");
		for(int i=1;i<=n;i++)														//初始化price表
		{
			for(int j=1;j<=n;j++)
			{
				price[i][j] = in.nextInt();
				character[j] = 0;													//给character[]数组初始化为0
			}
			MinPrice = price[i][i] + MinPrice;										//算出最大费用，以便于在BackTrack中进行比较
		}
		in.close();																	//关闭输入数据流
	}
	
	void BackTrack(int i,int Price)													//回溯递归函数，i表示第i个工人进行分配工作，Price表示目前费用
	{
		if(i>n && Price<MinPrice)													//如果已经到达解空间树的叶子节点，并且当前费用小于之前记录的最小费用，则更新记录最小费用值
		{
			MinPrice = Price;
			return;
		}
		if(Price < MinPrice)														//剪枝函数策略，在当前路径下，如果价格已经大于之前的得出的最小价格，则不必再进行递归，减少算法时间复杂度
		{
			for(int j=1;j<=n;j++)													//查看每个工人工作分配情况
			{
				if(character[j] == 0)												//找到没有分配到的工人的编号
				{
					character[j] = 1;
					BackTrack(i+1, Price+price[i][j]);								//将此路径下的活结点的值加上，即算此路径的最费用，再递归进下一个节点
					character[j] = 0;												//利用回溯思想，在工人循环结束后回到上一工人，取消此次分配的工作
				}
			}
		}
	}
	
	void DisplayRecord(int i,int Price)
	{
		if(Price == MinPrice && i>n)
		{
			for(int a=1;a<=n;a++)
			{
				for(int b=1;b<=n;b++)
				{
					FinalRecord[a][b] = record[a][b];
				}
			}
			System.out.println("Record:");
			System.out.println("Worker\\Work");
			for(int c=1;c<=n;c++)
			{
				for(int d=1;d<=n;d++)
				{
					System.out.print(FinalRecord[c][d]+" ");
				}
				System.out.println();
			}
		}
		if(i > n)
		{
			return;
		}
		for(int j=1;j<=n;j++)
		{
			if(character[j] == 0)
			{
				character[j] = 1;
				record[i][j] = 1;
				DisplayRecord(i+1, Price+price[i][j]);
				character[j] = 0;
				record[i][j] = 0;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WorkQuestion ren = new WorkQuestion();
		ren.SetWorkThing();
		ren.BackTrack(1,0);
		System.out.println("Min Price:"+MinPrice);
		System.out.println("********************");
		ren.DisplayRecord(1,0);
		System.out.println("********************");
	}
}
