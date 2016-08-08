package juarypoker;
import java.util.Random;


public class artiInt {
	public int pointAra[]=new int[4];
	public int roundAra[]=new int[4];
	
	public artiInt(int ara[]){
		pointAra=ara;
	}
	
	public int choiceFinder(int ind){
		//RAISE=1
		//STAY=2
		//FOLD=3
		int flag=0;
		Random dice=new Random();
		int random[]=new int[100];
		int index=0;
		
		while(true){
			
			int rand=dice.nextInt(20);
			
			random[index]=rand;
			index++;
			if(index==100){
				index=0;
				break;
			}
		}
		
		if(gameControl.maxBet>gameControl.playerTotalMoney[ind]){
			return 3;
		}
		
		if(this.pointAra[ind]==10){
			
			for(int i=0;i<15;i++){
				if(random[i]<5){
					flag++;
				}
			}
			if(gameControl.foldCounter==3){
				return 2;
			}
			if((flag>=5 || gameControl.maxBet>=3500)){
				if(random[15]%4==0){
					return 1;
				}
				else if(random[15]%4==3){
					return 3;
				}
				return 2;
			}
			else if(flag==3 || flag==4 || flag==2 || this.roundAra[ind]==3){
				return 2;
			}
			else{
				this.roundAra[ind]++;
				return 1;
			}
		}
		
		else if(this.pointAra[ind]==9){
			for(int i=0;i<12;i++){
				if(random[i]<5){
					flag++;
				}
			}
			if(gameControl.foldCounter==3){
				return 2;
			}
			if(flag>=8 || (gameControl.maxBet>=(4000+(60*random[12])))){
				if(random[15]%5==0 || random[15]%5==1){
					return 1;
				}
				else if(random[15]%4==4){
					return 3;
				}
				return 2;
			}
			else if((flag>=6 && flag<=7) || this.roundAra[ind]==3){
				return 2;
			}
			else{
				this.roundAra[ind]++;
				return 1;
			}
		}
		
		else if(this.pointAra[ind]==8 || this.pointAra[ind]==7){
			for(int i=0;i<12;i++){
				if(random[i]<5){
					flag++;
				}
			}
			if(gameControl.foldCounter==3){
				return 2;
			}
			if(flag>=9 || (gameControl.maxBet>=(3500+(60*random[12])))){
				return 3;
			}
			else if((flag>=7 && flag<=8) || this.roundAra[ind]==3){
				return 2;
			}
			else{
				this.roundAra[ind]++;
				return 1;
			}
		}
		
		else if(this.pointAra[ind]==5 || this.pointAra[ind]==6){
			for(int i=0;i<10;i++){
				if(random[i]<5){
					flag++;
				}
			}
			
			if(gameControl.foldCounter==3){
				return 2;
			}
			if(flag>=8 || this.roundAra[ind]==4){
				return 2;
			}
			else{
				this.roundAra[ind]++;
				return 1;
			}
		}
		
		else if(this.pointAra[ind]==4){
			for(int i=0;i<10;i++){
				if(random[i]<5){
					flag++;
				}
			}
			
			if(gameControl.foldCounter==3){
				return 2;
			}
			if(flag>=7 || this.roundAra[ind]==5){
				return 2;
			}
			else{
				this.roundAra[ind]++;
				return 1;
			}
		}
		
		else {
			for(int i=0;i<10;i++){
				if(random[i]<5){
					flag++;
				}
			}
			
			if(gameControl.foldCounter==3){
				return 2;
			}
			if(flag>=7 || this.roundAra[ind]==6){
				return 2;
			}
			else{
				this.roundAra[ind]++;
				return 1;
			}
		}
	}
	
	public int raiseAmount(int ind){
		int flag=0;
		Random dice=new Random();
		int random[]=new int[50];
		int index=0;
		
		while(true){
			
			int rand=dice.nextInt(10);
			
			random[index]=rand;
			index++;
			if(index==50){
				index=0;
				break;
			}
		}
		
		
		if(this.pointAra[ind]<=4){
			while(true){
				if(random[index]>=5){
					flag=random[index];
					break;
				}
				
			}
			if(gameControl.playerTotalMoney[ind]<=gameControl.maxBet+(60*flag)){
				return gameControl.playerTotalMoney[ind];
			}
			else{
				return gameControl.maxBet+(60*flag);
			}
			
		}
		
		else if(this.pointAra[ind]>=5 && this.pointAra[ind]<=7){
			while(true){
				if(random[index]>=3 && random[index]<=4){
					flag=random[index];
					break;
				}
				index++;
			}
			if(gameControl.playerTotalMoney[ind]<=gameControl.maxBet+(60*flag)){
				return gameControl.playerTotalMoney[ind];
			}
			else{
				return gameControl.maxBet+(60*flag);
			}
		}
		
		else if(this.pointAra[ind]>=8 && this.pointAra[ind]<=9){
			while(true){
				if(random[index]>=2 && random[index]<=3){
					flag=random[index];
					break;
				}
				index++;
			}
			if(gameControl.playerTotalMoney[ind]<=gameControl.maxBet+(60*flag)){
				return gameControl.playerTotalMoney[ind];
			}
			else{
				return gameControl.maxBet+(60*flag);
			}
		}
		
		else{
			return gameControl.maxBet+60;
		}
		
		
	}
}