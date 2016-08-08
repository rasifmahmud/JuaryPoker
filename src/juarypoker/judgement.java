package juarypoker;
import java.util.Arrays;
import java.lang.*;
public class judgement {
	public int numOfOccurence[] = new int[11];
	public int repeatedHand=0;
	public static int winnerPlayers[]=new int[4];

	
	public judgement(int ara[],int stay[]){
		for (int i=0;i<4;i++){
		    numOfOccurence[ara[i]]++;
		}
		
		for(int i=1;i<=10;i++){
			if(numOfOccurence[i]>1){
				int checkFlag=0;
				for(int j=0;j<stay.length;j++){
					if(ara[j]==i && stay[j]==1){
						checkFlag++;
					}
				}
				if(checkFlag>=2){
					repeatedHand=i;
					break;
				}
			}
		}
	}
	
	public int[] reverse(int array[]){
		for(int i =0; i < array.length/2; i++){
			   int temp = array[i];
			   array[i] = array[array.length-1 - i];
			   array[array.length-1 - i] = temp;
		}
		return array;
	}
	
	
	public int highCardFinder(int repDeck[][]){
		int length=0;
		for(int i=0;i<4;i++){
			if(repDeck[i][0]!=0){
				length=repDeck[i].length;
				Arrays.sort(repDeck[i]);
				repDeck[i]=this.reverse(repDeck[i]);
			}
		}
		
		int highCard=0;
		
		for(int i=0;i<4;i++){
			
			for(int j=0;j<length;j++){
				
				highCard=Math.max(repDeck[i][j], highCard);
				
			}
			//System.out.print("High card is \t"+highCard+"\n");
		}
		for(int i=0;i<4;i++){
			
			for(int j=0;j<length;j++){
				
				if(highCard==repDeck[i][j]){
					System.out.print("High card is "+highCard+"\n");
					return i;
				}
				
			}
		}
		
		return -1;
	}
	
	
	public int multipleHighCardFinder(int repDeck[][]){
		int length=0;
		for(int i=0;i<4;i++){
			if(repDeck[i][0]!=0){
				length=repDeck[i].length;
				Arrays.sort(repDeck[i]);
				repDeck[i]=this.reverse(repDeck[i]);
			}
		}
		
		int presentCard=0;
		int pastCard=1000;
		int presentCardHolder=-1;
		int repeatFlag=0;
		int count=0;
		
		while(repeatFlag!=1){
			repeatFlag=0;
			
			for(int i=0;i<4;i++){
			
				for(int j=0;j<length;j++){
					
					if(Math.max(repDeck[i][j], presentCard)<pastCard){
						presentCard=Math.max(repDeck[i][j], presentCard);
					}
					
				
				}
			//System.out.print("High card is \t"+highCard+"\n");
			}
			for(int i=0;i<4;i++){
			
				for(int j=0;j<length;j++){
				
					if(presentCard==repDeck[i][j]){
						//System.out.print("High card is "+highCard+"\n");
						repeatFlag++;
						presentCardHolder=i;
					}
				
				}
			}
			
			if(repeatFlag==1){
				break;
			}
			else{
				pastCard=presentCard;
				presentCard=0;
				presentCardHolder=-1;
			}
			count++;
			if(repDeck[0].length==count)
				break;
		}
		
		System.out.print("High card is "+presentCard+"\n");
		return presentCardHolder;
		
		
	}

	
	
	
	public int royalFlushRepeat(int repDeck[][]){
		return -2;
	}
	
	
	public int straightFlushRepeat(int repDeck[][]){
		int straightFlushCards[]=new int[4];
		int maxStraightFlushIndex=-1;
		int maxStraightFlushCard=0;
		
		for(int m=0;m<4;m++){
			if(repDeck[m][0]==0)
				continue;
			
			if(specialStraightChecker(repDeck[m])==1){
				straightFlushCards[m]=4;
				if(straightFlushCards[m]>maxStraightFlushCard){
					maxStraightFlushCard=straightFlushCards[m];
				}
				continue;
			}
			
			int remainder[]=new int[7];
			
			for(int i=0;i<7;i++){
				remainder[i]=repDeck[m][i]%100;
			}
			Arrays.sort(remainder);
			remainder=this.reverse(remainder);
	
			/*for(int i=0;i<7;i++){
				System.out.print(remainder[i]+"\t");
			}
			System.out.println();*/
			
			int count=1;
			int remainderCheck=0;
			
			for(int i=0;i<6;i++){
				if(remainder[i]-remainder[i+1]==0){
					
				}
				if(remainder[i]-remainder[i+1]==1){
					count++;
					remainderCheck=remainder[i+1];
					
					if(count==5){
						straightFlushCards[m]=remainderCheck+4;
						
						if((remainderCheck+4)>maxStraightFlushCard){
							maxStraightFlushCard=remainderCheck+4;
						}
						break;
					}
				}
				else{
					count=1;
				}
			}

		}
		//System.out.println(maxStraightFlushCard);
		int repeatFlag=0;
		for(int i=0;i<4;i++){
			if(straightFlushCards[i]==maxStraightFlushCard){
				repeatFlag++;
				maxStraightFlushIndex=i;
				//System.out.println(repeatFlag);
			}
		}
		if(repeatFlag>1){
			maxStraightFlushIndex=-2;
		
		}
		return maxStraightFlushIndex;
	}
	
	
	public int fourOfAKindRepeat(int repDeck[][]){
		int fourOfAKindCards[]=new int[4];
		int maxRemainder=0;
		int maxRemainderIndex=0;
		for(int m=0;m<4;m++){
			
			int remainder[]=new int[7];
			
			for(int i=0;i<7;i++){
				remainder[i]=repDeck[m][i]%100;
			}
			Arrays.sort(remainder);
		
			int count=1;
			for(int i=0;i<6;i++){
				if(remainder[i]==remainder[i+1]){
					count++;
				
					if(count==4){
						fourOfAKindCards[m]=remainder[i];
						if(remainder[i]>maxRemainder){
							maxRemainder=remainder[i];
							maxRemainderIndex=m;
						}
						break;
					}
				}
				else{
					count=1;
				}
			}
		}
		

		int remainderCheck[]=new int[4];
		remainderCheck=fourOfAKindCards;
		Arrays.sort(remainderCheck);
		/*for(int i=0;i<4;i++){
			System.out.println(remainderCheck[i]);
		}*/
		for(int m=0;m<3;m++){

			if(remainderCheck[m]==remainderCheck[m+1] && remainderCheck[m]!=0){
				
				if(maxRemainder==remainderCheck[m]){
					
					int restOfCards[][]=new int[4][1];
					int index;
					for(int i=0;i<4;i++){
						Arrays.sort(repDeck[i]);
						repDeck[i]=this.reverse(repDeck[i]);
					}
					for(int i=0;i<4;i++){
						index=0;
						
						for(int j=0;j<7;j++){
							if(repDeck[i][j]%100!=maxRemainder){
								restOfCards[i][index]=repDeck[i][j];
								index++;
								if(index==1)
									break;
							}
						}
					}
					/*for(int i=0;i<4;i++){
						for(int j=0;j<1;j++){
							System.out.print(restOfCards[i][j]+"\t");
						}
						System.out.println();
					}*/
					
				maxRemainderIndex=this.highCardFinder(restOfCards);
				}
				break;
			}
		}
		
		return maxRemainderIndex;


	}
	
	public int fullHouseRepeat(int repDeck[][]){
		int fullHouseCards[][]=new int[4][2];
		int fullHouseIndex=-1;
		for(int m=0;m<4;m++){
			
			int remainder[]=new int[7];
			
			for(int i=0;i<7;i++){
				remainder[i]=repDeck[m][i]%100;
			}
			Arrays.sort(remainder);
		
			int count=1;
			for(int i=0;i<6;i++){
				if(remainder[i]==remainder[i+1]){
					count++;
				
					if(count==3){
						fullHouseCards[m][0]=remainder[i];

					}
				}
				else{
					count=1;
				}
			}
		}
		

		for(int m=0;m<4;m++){
			
			int remainder[]=new int[7];
			
			for(int i=0;i<7;i++){
				remainder[i]=repDeck[m][i]%100;
			}
			Arrays.sort(remainder);
		
			int count=1;
			for(int i=0;i<6;i++){
				if(remainder[i]==remainder[i+1] && remainder[i]%100!=fullHouseCards[m][0]){
					count++;
				
					if(count==2){
						fullHouseCards[m][1]=remainder[i];
						break;
					}
				}
				else{
					count=1;
				}
			}
		}
		
		/*for(int i=0;i<4;i++){
			for(int j=0;j<2;j++){
				System.out.print(fullHouseCards[i][j]+"\t");
			}
			System.out.println();
		}*/
		int repeatFlag;
		int max;
		for(int i=0;i<2;i++){
			max=0;
			repeatFlag=1;
			for(int j=0;j<4;j++){
				if(fullHouseCards[j][i]>max){
					fullHouseIndex=j;
					max=fullHouseCards[j][i];	
				}
				else if(fullHouseCards[j][i]==max && fullHouseCards[j][i]!=0){
					repeatFlag++;
				}

			}
		
			if(repeatFlag==1){
				
				break;
			}
		}
		if(fullHouseIndex==-1){
			fullHouseIndex=-2;

		}


	return fullHouseIndex;
}
	
	public int flushRepeat(int repDeck[][]){
		int flushIndex[]=new int[4];
		int flushCards[][]=new int[4][5];
		int maxFlushIndex=-1;
		for(int m=0;m<4;m++){
			int vagfol[]=new int[7];
			for(int i=0;i<7;i++){
				vagfol[i]=repDeck[m][i]/100;
			}
			Arrays.sort(vagfol);
			int count=1;
			for(int i=0;i<6;i++){
				if(vagfol[i]==vagfol[i+1]){
					count++;
					
					if(count==5){
						flushIndex[m]=vagfol[i];
						break;
					}
				}
				else{
					count=1;
				}
			}
		}
		
		

		for(int i=0;i<4;i++){
			Arrays.sort(repDeck[i]);
			repDeck[i]=this.reverse(repDeck[i]);
		}
		int pos;
		for(int i=0;i<4;i++){
			pos=0;
			for(int j=0;j<7;j++){
				if(repDeck[i][j]/100==flushIndex[i]){
					flushCards[i][pos]=repDeck[i][j]%100;
					pos++;
					if(pos==5)
						break;
				}
			}
		}
;
		for(int i=0;i<4;i++){
			Arrays.sort(flushCards[i]);
			flushCards[i]=this.reverse(flushCards[i]);
		}
		
		/*for(int i=0;i<4;i++){
			for(int j=0;j<5;j++){
				System.out.print(flushCards[i][j]+"\t");
			}
			System.out.println();
		}*/
		
		for(int i=0;i<5;i++){
			
			for(int j=0;j<3;j++){
				if(flushCards[j+1][i]==0)
					continue;
				if(flushCards[j][i]>flushCards[j+1][i]){
					maxFlushIndex=j;
					break;
				}
				else if(flushCards[j][i]<flushCards[j+1][i]){
					maxFlushIndex=j+1;
					break;
				}
			}
			if(maxFlushIndex!=-1)
				break;
		}
		
		if(maxFlushIndex==-1){
			maxFlushIndex=-2;
		}
		
		return maxFlushIndex;
	}
	
	public int specialStraightChecker(int combAra[]){
		int flag=0;
		int remainder[]=new int[7];
		
		for(int i=0;i<7;i++){
			remainder[i]=combAra[i]%100;
		}
		Arrays.sort(remainder);
		remainder=this.reverse(remainder);
		
		int specialCaseCounter=0;
		int fixedCase[]={1,2,3,4,13};
		for(int i=0;i<5;i++){
			for(int j=0;j<7;j++){
				if(fixedCase[i]==remainder[j]){
					specialCaseCounter++;
					break;
				}
			}
		}
		
		if(specialCaseCounter==5){
			flag=1;
		}			
		
		return flag;

	}
	
	public int straightRepeat(int repDeck[][]){
		int straightCards[]=new int[4];
		int maxStraightIndex=-1;
		int maxStraightCard=0;
		
		for(int m=0;m<4;m++){
			rules checker=new rules();
			int checkIfStraight=checker.straight(repDeck[m]);
			if(checkIfStraight==0)
				continue;
			if(specialStraightChecker(repDeck[m])==1){
				straightCards[m]=4;
				if(straightCards[m]>maxStraightCard){
					maxStraightCard=straightCards[m];
				}
				continue;
			}
			
			int remainder[]=new int[7];
			
			for(int i=0;i<7;i++){
				remainder[i]=repDeck[m][i]%100;
			}
			Arrays.sort(remainder);
			remainder=this.reverse(remainder);
			
			int count=1;
			int remainderCheck=0;
			
			for(int i=0;i<6;i++){
				if(remainder[i]-remainder[i+1]==0){
					
				}
				if(remainder[i]-remainder[i+1]==1){
					count++;
					remainderCheck=remainder[i+1];
					
					if(count==5){
						straightCards[m]=remainderCheck+4;
						
						if((remainderCheck+4)>maxStraightCard){
							maxStraightCard=remainderCheck+4;
						}
						break;
					}
				}
				else{
					count=1;
				}
			}

		}
		/*for(int j=0;j<4;j++){
			for(int k=0;k<7;k++){
				System.out.print(repDeck[j][k]+"\t");
			}
			System.out.println();
		}*/
		//System.out.println(maxStraightCard);
		int repeatFlag=0;
		for(int i=0;i<4;i++){
			if(straightCards[i]==maxStraightCard){
				repeatFlag++;
				maxStraightIndex=i;
			}
		}
		if(repeatFlag>1){
			maxStraightIndex=-2;
		
		}
		return maxStraightIndex;
	}
	
	public int threeOfAKindRepeat(int repDeck[][]){
		int threeOfAKindCards[]=new int[4];
		int maxRemainder=0;
		int maxRemainderIndex=0;
		for(int m=0;m<4;m++){
			
			int remainder[]=new int[7];
			
			for(int i=0;i<7;i++){
				remainder[i]=repDeck[m][i]%100;
			}
			Arrays.sort(remainder);
		
			int count=1;
			for(int i=0;i<6;i++){
				if(remainder[i]==remainder[i+1]){
					count++;
				
					if(count==3){
						threeOfAKindCards[m]=remainder[i];
						if(remainder[i]>maxRemainder){
							maxRemainder=remainder[i];
							maxRemainderIndex=m;
						}
						break;
					}
				}
				else{
					count=1;
				}
			}
		}
		

		int remainderCheck[]=new int[4];
		remainderCheck=threeOfAKindCards;
		Arrays.sort(remainderCheck);
		for(int i=0;i<3;i++){

			if(remainderCheck[i]==remainderCheck[i+1]){
				
				if(maxRemainder==remainderCheck[i]){
					int restOfCards[][]=new int[4][4];
					int index;
					
					for(i=0;i<4;i++){
						index=0;
						
						for(int j=0;j<7;j++){
							if(repDeck[i][j]%100!=maxRemainder){
								restOfCards[i][index]=repDeck[i][j];
								index++;
								if(index==4)
									break;
							}
						}
					}
					
					maxRemainderIndex=this.multipleHighCardFinder(restOfCards);
				}
			}
		}
		
		return maxRemainderIndex;


	}
	
	public int twoPairRepeat(int repDeck[][]){
		int twoPairCards[][]=new int[4][2];
		int maxRemainderIndex=-1;
		for(int m=0;m<4;m++){
			
			int remainder[]=new int[7];
			for(int i=0;i<7;i++){
				remainder[i]=repDeck[m][i]%100;
			}
			Arrays.sort(remainder);
		
			int count=1;
			int pairCounter=0;
			
			for(int i=0;i<6;i++){
				if(remainder[i]==remainder[i+1]){
					count++;
					
					if(count==2){
						
						
						count=1;
						twoPairCards[m][pairCounter]=remainder[i];
						pairCounter++;
						
						if(pairCounter==2){

							break;
						}
					}
				}
				else{
					count=1;
				}
			}
			

		

		
		int remainderCheck[][]=new int[4][2];
		remainderCheck=twoPairCards;
		for(int i=0;i<4;i++){
			Arrays.sort(remainderCheck[i]);
		}
		
		
		int maxRemainder=0;
		int secondMaxRemainder=0;
		int repeatFlag=0;
		for(int i=0;i<4;i++){
			for(int j=0;j<2;j++){
				
				if(remainderCheck[i][j]==maxRemainder){
					repeatFlag=1;
				}
				
				else if(remainderCheck[i][j]>maxRemainder){
					maxRemainder=remainderCheck[i][j];
					maxRemainderIndex=i;
					repeatFlag=0;
				}
			}
		}
		
		if(repeatFlag==1){
			
			for(int i=0;i<4;i++){
				
				for(int j=0;j<2;j++){
					
					if(remainderCheck[i][j]!=maxRemainder && remainderCheck[i][j]==secondMaxRemainder){
						repeatFlag=2;
					}
					
					else if(remainderCheck[i][j]!=maxRemainder && remainderCheck[i][j]>secondMaxRemainder){
						secondMaxRemainder=remainderCheck[i][j];
						maxRemainderIndex=i;
						repeatFlag=1;
					}
				}
			}
		}
			
		if(repeatFlag==2){
			
			for(int i=0;i<=0;i++){
				int restOfCards[][]=new int[4][3];
				int index;
					
					for(i=0;i<4;i++){
						index=0;
						
						for(int j=0;j<7;j++){
							if(repDeck[i][j]%100!=maxRemainder && repDeck[i][j]%100!=secondMaxRemainder){
								restOfCards[i][index]=repDeck[i][j];
								index++;
								if(index==3)
									break;
							}
						}
					}
					
					maxRemainderIndex=this.highCardFinder(restOfCards);
				}
			}
		}
		
		return maxRemainderIndex;

	}


	
	public int onePairRepeat(int repDeck[][]){
		int onePairCards[]=new int[4];
		int maxRemainder=0;
		int maxRemainderIndex=0;
		for(int m=0;m<4;m++){
			
			int remainder[]=new int[7];
			
			for(int i=0;i<7;i++){
				remainder[i]=repDeck[m][i]%100;
			}
			Arrays.sort(remainder);
		
			int count=1;
			for(int i=0;i<6;i++){
				if(remainder[i]==remainder[i+1]){
					count++;
				
					if(count==2){
						onePairCards[m]=remainder[i];
						if(remainder[i]>maxRemainder){
							maxRemainder=remainder[i];
							maxRemainderIndex=m;
						}
						break;
					}
				}
				else{
					count=1;
				}
			}
		}
		

		int remainderCheck[]=new int[4];
		remainderCheck=onePairCards;
		Arrays.sort(remainderCheck);
		for(int i=0;i<3;i++){
			
			if(remainderCheck[i]==remainderCheck[i+1]){
				
				if(maxRemainder==remainderCheck[i]){
					int restOfCards[][]=new int[4][5];
					int index;
					
					for(i=0;i<4;i++){
						index=0;
						
						for(int j=0;j<7;j++){
							if(repDeck[i][j]%100!=maxRemainder){
								restOfCards[i][index]=repDeck[i][j];
								index++;
								if(index==5)
									break;
							}
						}
					}
					
					maxRemainderIndex=this.multipleHighCardFinder(restOfCards);
				}
			}
		}
		
		return maxRemainderIndex;

	}
	
	public int highCardRepeat(int repDeck[][]){
		return this.multipleHighCardFinder(repDeck);
	}
	
	public void winnerSelector(int playerPoint[],int allPlayerCards[][],int stay[]){
		
		int winner;
		int repeatedDeck[][]=new int[4][7];
		winner=-1;
		int winnerPoint=11;
		for(int i=0;i<4;i++){
			if(playerPoint[i]<winnerPoint && stay[i]==1){
				winnerPoint=playerPoint[i];
				winner=i;
			}
		}		
		if(this.repeatedHand!=winnerPoint){

			
			System.out.println("Winner among all hands is player "+(winner+1));
			this.winnerPlayers[winner]=1;
			
		}
		
		else{
			
			winner=0;
			int splitFlag=0;
			//System.out.print(judge.repeatedHand);
			for(int i=0;i<4;i++){
				if(playerPoint[i]==this.repeatedHand && stay[i]==1){
					repeatedDeck[i]=allPlayerCards[i];
				}
			}
			
			

			if(this.repeatedHand==1){
				
				winner=this.royalFlushRepeat(repeatedDeck);
				if(winner!=-2){
					System.out.println("Winner among royal flush hands is player "+ (winner+1));
					this.winnerPlayers[winner]=1;
				}
				else
					splitFlag=1;
			}
			
			else if(this.repeatedHand==2){
				
				winner=this.straightFlushRepeat(repeatedDeck);
				if(winner!=-2){
					System.out.println("Winner among straight hands is player "+ (winner+1));
					this.winnerPlayers[winner]=1;
				}
				else
					splitFlag=1;
			}
			
			else if(this.repeatedHand==3){
				
				winner=this.fourOfAKindRepeat(repeatedDeck);
				if(winner!=-2){
					System.out.println("Winner among four of a kind hands is player "+ (winner+1));
					this.winnerPlayers[winner]=1;
				}
				else
					splitFlag=1;
			}
			
			else if(this.repeatedHand==4){
				
				winner=this.fullHouseRepeat(repeatedDeck);
				if(winner!=-2){
					System.out.println("Winner among full house hands is player "+ (winner+1));
					this.winnerPlayers[winner]=1;
				}
				else
					splitFlag=1;
			}	
			
			else if(this.repeatedHand==5){
				
				winner=this.flushRepeat(repeatedDeck);
				if(winner!=-2){
					System.out.println("Winner among flush hands is player "+ (winner+1));
					this.winnerPlayers[winner]=1;
				}
				else
					splitFlag=1;
			}
			
			else if(this.repeatedHand==6){
				
				winner=this.straightRepeat(repeatedDeck);
				if(winner!=-2){
					System.out.println("Winner among straight hands is player "+ (winner+1));
					this.winnerPlayers[winner]=1;
				}
				else
					splitFlag=1;
			}
			
			else if(this.repeatedHand==7){
				
				winner=this.threeOfAKindRepeat(repeatedDeck);
				if(winner!=-2){
					System.out.println("Winner among three of a kind hands is player "+ (winner+1));
					this.winnerPlayers[winner]=1;
				}
				else
					splitFlag=1;
			}
			
			else if(this.repeatedHand==8){
				
				winner=this.twoPairRepeat(repeatedDeck);
				if(winner!=-2){
					System.out.println("Winner among two pair hands is player "+ (winner+1));
					this.winnerPlayers[winner]=1;
				}
				else
					splitFlag=1;
			}
			
			else if(this.repeatedHand==9){
				
				winner=this.onePairRepeat(repeatedDeck);
				if(winner!=-2){
					System.out.println("Winner among one pair hands is player "+ (winner+1));
					this.winnerPlayers[winner]=1;
				}
				else
					splitFlag=1;
			}	
			
			else if(this.repeatedHand==10){
				
				winner=this.highCardRepeat(repeatedDeck);
				if(winner!=-2){
					System.out.println("Winner among high card is player "+ (winner+1));
					this.winnerPlayers[winner]=1;
				}
				else
					splitFlag=1;
			}
			

			if(splitFlag==1){
				System.out.println("Money is splitted among");
				for(int i=0;i<4;i++){
					if(playerPoint[i]==this.repeatedHand){
						System.out.print((i+1)+"\t");
						this.winnerPlayers[i]=1;
					}
				}
			}
			
			if(this.repeatedHand==0){
				System.out.println("Money is splitted among all players");
				for(int i=0;i<4;i++){
					this.winnerPlayers[i]=1;
				}
			}

			 

		}
		

	}
}