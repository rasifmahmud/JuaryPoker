package juarypoker;
import java.util.Random;
public class shuffle {
	public int deck[]={101,102,103,104,105,106,107,108,109,110,111,112,113,201,202,203,204,205,206,207,208,209,210,211,212,213,301,302,303,304,305,306,307,308,309,310,311,312,313,401,402,403,404,405,406,407,408,409,410,411,412,413};
	public int cardIndex[]=new int[52];
	public int playerCards[][]=new int[4][2];
	public int boardCards[]=new int[5];
	public int index=0;
	public static int boardCardControl=0;
	
	
	public shuffle(){
		Random dice=new Random();
		int index=0;
		int rand;
		int flag;
		while(1==1){
			flag=0;
			rand=dice.nextInt(52);
			for(int j=0;j<index;j++){
				if(rand==cardIndex[j]){
					flag=1;
					break;
				}
			}
			if(flag==0){
				cardIndex[index]=rand;
				index++;
			}
			if(index==52){
				index=0;
				break;
			}
		}
	}
	
	/*public void printCardIndex(){
		for(int i=0;i<52;i++){
			System.out.println(cardIndex[i]);
		}
	}*/
	
	public void printCard(int playerNumber){
		System.out.print("Player Cards:\t");
		for(int i=0;i<2;i++){
			System.out.print(playerCards[playerNumber][i]+"\t");
		}
		System.out.print("Board Cards:\t");
		for(int i=0;i<5;i++){
			System.out.print(boardCards[i]+"\t");
		}		
		System.out.println();
	}
	
	
	public void playerCardDistribution(){
		for(int i=0;i<4;i++){
			for(int j=0;j<2;j++){
				playerCards[i][j]=deck[cardIndex[index]];
				index++;
				
			}
			
		}
	}
	
	
	public void boardCardDistribution(){
		for(int i=0;i<5;i++){
			boardCards[i]=deck[cardIndex[index]];
			index++;
		}
	}
	
	
	public int[] player1Cards(){
		return playerCards[0];
	}
	
	
	public int[] player2Cards(){
		return playerCards[1];
	}
	
	
	public int[] player3Cards(){
		return playerCards[2];
	}

	
	public int[] player4Cards(){
		return playerCards[3];
	}
	
	
	public int[] boardPlayCards(){
		return boardCards;
	}
	
	public void showBoardCards(){
		if(this.boardCardControl==0){
			System.out.print("Board Cards: ");
			for(int i=0;i<3;i++)
				System.out.print(this.boardCards[i]+"\t");
			System.out.println();
			this.boardCardControl++;
		}
		else if(this.boardCardControl==1){
			System.out.println(this.boardCards[3]+"\t");
			this.boardCardControl++;
		}
		else if(this.boardCardControl==2){
			System.out.println(this.boardCards[4]+"\t");
			this.boardCardControl=0;
		}

	}
	
	public void showPlayerCards(int playerIndex){
		
		System.out.print("Player "+(playerIndex+1)+" Cards :  ");
		for(int i=0;i<2;i++){
			System.out.print(this.playerCards[playerIndex][i]+"\t");
		}
		System.out.println();
	}
}