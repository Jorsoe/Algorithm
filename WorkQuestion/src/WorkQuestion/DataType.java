package WorkQuestion;

public class DataType {
	
	int n = 0;																//表示工人人数
	int MinPrice = 0;														//表示最少的费用
	int MinPriceRecord;
	int[] character;														//character[n]表示第n个工作的分配状态，已分配则为1，未分配为0
	int[][] price;															//price[i][j]表示第i号工人完成第j号工作所需的费用
	int[][] record = new int[10][10];
	int[][] FinalRecord = new int[10][10];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
