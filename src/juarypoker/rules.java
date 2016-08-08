package juarypoker;
import java.util.Arrays;


public class rules {
	public int usedCards[][]=new int[4][5];
	public int pointSequenceFlag;
	
	public int[] reverse(int array[]){
		for(int i =0; i < array.length/2; i++){
			   int temp = array[i];
			   array[i] = array[array.length-1 - i];
			   array[array.length-1 - i] = temp;
		}
		return array;
	}
	
	public int royalFlush(int combAra[]){
		pointSequenceFlag=0;

		Arrays.sort(combAra);
		
		int vagfol[]=new int[7];
		for(int i=0;i<7;i++){
			vagfol[i]=combAra[i]/100;
		}	
		
		int numOfOccurence[] = new int[7];
	    for (int i=0;i<7;i++){
	        numOfOccurence[vagfol[i]]++;
	    }
	    
	    int maxOccurence=0;
	    int maxOccurenceIndex=0;
	    for (int i=0;i<7;i++){
	        if(numOfOccurence[i]>=maxOccurence){
	        	maxOccurence=numOfOccurence[i];
	        	maxOccurenceIndex=i;
	        }
	    }
	    if(maxOccurence!=5){
	    	return pointSequenceFlag;
	    }
	    else{
	    	int remainderCheck=9;
	    	int count=0;
	    	for(int i=0;i<7;i++){
	    		if(combAra[i]/100==maxOccurenceIndex){
	    			if(combAra[i]%100==remainderCheck){
	    				count++;
	    				remainderCheck++;
	    			}
	    			if(count==5){
	    				pointSequenceFlag=1;
	    			}
	    		}
	    		else{
	    			remainderCheck=9;
	    		}
	    	}
	    	return pointSequenceFlag;
	    }
		
		
	}
	
	
	public int straightFlush(int combAra[]){
		pointSequenceFlag=0;
				
		Arrays.sort(combAra);
		
		int vagfol[]=new int[7];
		for(int i=0;i<7;i++){
			vagfol[i]=combAra[i]/100;
		}	
		
		int numOfOccurence[] = new int[7];
	    for (int i=0;i<7;i++){
	        numOfOccurence[vagfol[i]]++;
	    }
	    
	    int maxOccurence=0;
	    int maxOccurenceIndex=0;
	    for (int i=0;i<7;i++){
	        if(numOfOccurence[i]>=maxOccurence){
	        	maxOccurence=numOfOccurence[i];
	        	maxOccurenceIndex=i;
	        }
	    }
	    if(maxOccurence!=5){
	    	return pointSequenceFlag;
	    }
	    else{
	    	int count=1;
	    	for(int i=0;i<6;i++){
	    		if(combAra[i]/100==maxOccurenceIndex){
	    			if(combAra[i+1]-combAra[i]==1){
	    				count++;
	    			}
	    			if(count==5){
	    				pointSequenceFlag=2;
	    			}
	    		}
	    		else{
					if(count==4 && combAra[i+1]%100==4){
						for(int j=0;j<7;j++){
							if(combAra[j]==13){
								pointSequenceFlag=2;
								break;
							}
						}
					}

	    			count=1;
	    		}
	    	}

	    	return pointSequenceFlag;
	    }
		
	}
	

	public int fourOfAKind(int combAra[]){
		pointSequenceFlag=0;
		
		int remainder[]=new int[7];
		for(int i=0;i<7;i++){
			remainder[i]=combAra[i]%100;
		}
		Arrays.sort(remainder);
		
		int count=1;
		for(int i=0;i<6;i++){
			if(remainder[i]==remainder[i+1]){
				count++;
				
				if(count==4){
					pointSequenceFlag=3;
					break;
				}
			}
			else{
				count=1;
			}
		}
		return pointSequenceFlag;
		
	}
	

	public int fullHouse(int combAra[]){
		pointSequenceFlag=0;
		
		int remainder[]=new int[7];
		for(int i=0;i<7;i++){
			remainder[i]=combAra[i]%100;
		}
		Arrays.sort(remainder);
		
		int count=1;
		int remainderCheck=0;
		int numberOfKind=0;
		for(int i=0;i<6;i++){
			if(remainder[i]==remainder[i+1]){
				count++;
				
				if(count==3){
					remainderCheck=remainder[i];
					numberOfKind++;
					break;
				}
			}
			else{
				count=1;
			}
		}
		if(numberOfKind!=1){
			return pointSequenceFlag;
		}
		count=1;
		for(int i=0;i<6;i++){
			if(remainder[i]==remainder[i+1] && remainder[i]!=remainderCheck){
				count++;
				
				if(count==2){
					numberOfKind++;
					
					if(numberOfKind==2){
						pointSequenceFlag=4;
					}
					
					break;
				}
			}
			else{
				count=1;
			}
		}
		
		return pointSequenceFlag;

	}
	

	public int flush(int combAra[]){
		pointSequenceFlag=0;
		
		int vagfol[]=new int[7];
		for(int i=0;i<7;i++){
			vagfol[i]=combAra[i]/100;
		}
		Arrays.sort(vagfol);
		
		int count=1;
		for(int i=0;i<6;i++){
			if(vagfol[i]==vagfol[i+1]){
				count++;
				
				if(count==5){
					pointSequenceFlag=5;
					break;
				}
			}
			else{
				count=1;
			}
		}
		return pointSequenceFlag;
	}
	

	public int straight(int combAra[]){
		pointSequenceFlag=0;
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
			return 6;
		}
		
		/*for(int i=0;i<7;i++){
			System.out.print(remainder[i]+"\t");
		}*/
		
		//System.out.println();
		int count=1;
		int remainderCheck=0;
		for(int i=0;i<6;i++){
			if(remainder[i]-remainder[i+1]==0){
				
			}
			
			if(remainder[i]-remainder[i+1]==1){
				count++;
				remainderCheck=remainder[i+1];
				//System.out.print(remainderCheck+"\t");
				if(count==5){
					
					pointSequenceFlag=6;
					break;
				}
			}
	
			else{
				
				count=1;
			}
		}
		
		return pointSequenceFlag;

	}
	

	public int threeOfAKind(int combAra[]){
		pointSequenceFlag=0;
		
		int remainder[]=new int[7];
		for(int i=0;i<7;i++){
			remainder[i]=combAra[i]%100;
		}
		Arrays.sort(remainder);
		
		int count=1;
		
		for(int i=0;i<6;i++){
			if(remainder[i]==remainder[i+1]){
				count++;
				
				if(count==3){
					//remainderCheck=remainder[i];
					pointSequenceFlag=7;
					break;
				}
			}
			else{
				count=1;
			}
		}
		
		return pointSequenceFlag;		
	}
	

	public int twoPair(int combAra[]){
		pointSequenceFlag=0;
		
		int remainder[]=new int[7];
		for(int i=0;i<7;i++){
			remainder[i]=combAra[i]%100;
		}
		Arrays.sort(remainder);
		
		int count=1;
		int pairCounter=0;
		for(int i=0;i<6;i++){
			if(remainder[i]==remainder[i+1]){
				count++;
				
				if(count==2){
					
					pairCounter++;
					count=1;
					
					if(pairCounter==2){
						
						pointSequenceFlag=8;
					}
				}
			}
			else{
				count=1;
			}
		}
		

		
		return pointSequenceFlag;
	}
	

	public int onePair(int combAra[]){
		pointSequenceFlag=0;
		
		int remainder[]=new int[7];
		for(int i=0;i<7;i++){
			remainder[i]=combAra[i]%100;
		}
		Arrays.sort(remainder);
		
		int count=1;
		for(int i=0;i<6;i++){
			if(remainder[i]==remainder[i+1]){
				count++;
				
				if(count==2){
					pointSequenceFlag=9;
					break;
				}
			}
			else{
				count=1;
			}
		}
		

		
		return pointSequenceFlag;
	}
	

	public int highCard(int combAra[]){
		pointSequenceFlag=10;
		Arrays.sort(combAra);
		
		return pointSequenceFlag;
	}
	
	public int gamePoint(int combinedDeck[]){
		if(this.royalFlush(combinedDeck)!=0){
			return this.royalFlush(combinedDeck);
		}
		
		else if(this.straightFlush(combinedDeck)!=0){
			return this.straightFlush(combinedDeck);
		}
		
		else if(this.fourOfAKind(combinedDeck)!=0){
			return this.fourOfAKind(combinedDeck);
		}
		
		else if(this.fullHouse(combinedDeck)!=0){
			return this.fullHouse(combinedDeck);
		}
		
		else if(this.flush(combinedDeck)!=0){
			return this.flush(combinedDeck);
		}
		
		else if(this.straight(combinedDeck)!=0){
			return this.straight(combinedDeck);
		}
		
		else if(this.threeOfAKind(combinedDeck)!=0){
			return this.threeOfAKind(combinedDeck);
		}
		
		else if(this.twoPair(combinedDeck)!=0){
			return this.twoPair(combinedDeck);
		}
		
		else if(this.onePair(combinedDeck)!=0){
			return this.onePair(combinedDeck);
		}
		
		else{
			return this.highCard(combinedDeck);
		}
	}

}