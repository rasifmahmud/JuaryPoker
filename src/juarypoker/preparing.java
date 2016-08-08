package juarypoker;
import java.util.Arrays;
public class preparing {
	public int allPlayerCards[][]=new int[4][7];
	public int index=0;
	
	
	public int[] playersCombinedCards(int fromPlayer[],int fromboard[],int playerNumber){
		for(int i=0;i<2;i++){
			allPlayerCards[playerNumber][index]=fromPlayer[i];
			index++;
		}
		for(int i=0;i<5;i++){
			allPlayerCards[playerNumber][index]=fromboard[i];
			index++;
		}
		index=0;
		Arrays.sort(allPlayerCards[playerNumber]);
		return allPlayerCards[playerNumber];
	}
	
	
	public void printCard(int playerNumber){
		for(int i=0;i<7;i++){
			System.out.print(allPlayerCards[playerNumber][i]+"\t");
		}
	}
	
	
}