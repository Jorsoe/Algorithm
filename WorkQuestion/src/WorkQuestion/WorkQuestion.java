package WorkQuestion;

import java.util.Scanner;

public class WorkQuestion {

	int n = 0;																		//��ʾ��������
	static int MinPrice = 0;														//��ʾ���ٵķ���
	int[] character;																//character[n]��ʾ��n�������ķ���״̬���ѷ�����Ϊ1��δ����Ϊ0
	int[][] price;																	//price[i][j]��ʾ��i�Ź�����ɵ�j�Ź�������ķ���
	int[][] record = new int[10][10];
	int[][] FinalRecord = new int[10][10];
	
	void SetWorkThing()																//������ʼ�������Լ�price��
	{
		System.out.print("Please input number of individual:");
		Scanner in = new Scanner(System.in);
		n = in.nextInt();															//��ʼ����������
		character = new int[n+1];													//Ϊ����price��ͳһ����ʾ�����ķ���״̬
		price = new int[n+1][n+1];													//���������ó���Ϊn+1��ԭ�����ڵ���BackTrack����ʱ����ʼֵΪ1������price������빤�˺�ͳһ����1��ʼ
		System.out.println("Please input every price of every individual:");
		for(int i=1;i<=n;i++)														//��ʼ��price��
		{
			for(int j=1;j<=n;j++)
			{
				price[i][j] = in.nextInt();
				character[j] = 0;													//��character[]�����ʼ��Ϊ0
			}
			MinPrice = price[i][i] + MinPrice;										//��������ã��Ա�����BackTrack�н��бȽ�
		}
		in.close();																	//�ر�����������
	}
	
	void BackTrack(int i,int Price)													//���ݵݹ麯����i��ʾ��i�����˽��з��乤����Price��ʾĿǰ����
	{
		if(i>n && Price<MinPrice)													//����Ѿ������ռ�����Ҷ�ӽڵ㣬���ҵ�ǰ����С��֮ǰ��¼����С���ã�����¼�¼��С����ֵ
		{
			MinPrice = Price;
			return;
		}
		if(Price < MinPrice)														//��֦�������ԣ��ڵ�ǰ·���£�����۸��Ѿ�����֮ǰ�ĵó�����С�۸��򲻱��ٽ��еݹ飬�����㷨ʱ�临�Ӷ�
		{
			for(int j=1;j<=n;j++)													//�鿴ÿ�����˹����������
			{
				if(character[j] == 0)												//�ҵ�û�з��䵽�Ĺ��˵ı��
				{
					character[j] = 1;
					BackTrack(i+1, Price+price[i][j]);								//����·���µĻ����ֵ���ϣ������·��������ã��ٵݹ����һ���ڵ�
					character[j] = 0;												//���û���˼�룬�ڹ���ѭ��������ص���һ���ˣ�ȡ���˴η���Ĺ���
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
