package juarypoker;
import java.awt.Image;
import java.util.Scanner;
public class gameControl {

	public static int playerStay[]=new int[4];
	public static int playerBet[]=new int[4];
	public static int playerTotalMoney[]={360,360,360,360};
	public static int maxBet;
	public static int choice=0;
	public static int wait=0;
	public static int foldCounter=0;
	public static int point[]=new int[4];
	public int LETSPLAYFLAG=0;
	public int CARDSHUFFLEFLAG=0;


	public gameControl(){
		for(int i=0;i<4;i++){
			playerStay[i]=1;
		}
		maxBet=60;
	}

	public static void introPlayer(){
		Sound.INTRO.loop();
		ImageLoading.introFlag=1;
	}

	public void fold(int playerIndex){

		if(playerIndex!=0 && gameControl.foldCounter!=3 && juaryPoker.newGameFlag!=2){
			try{

				Thread.sleep(3000);


			}
			catch(Exception ex){

			}
		}

		while(ImageLoading.counter==-1 || ImageLoading.counter==-2){
			try{
				wait();
			}
			catch(Exception e){

			}
		}

		if(gameControl.playerTotalMoney[playerIndex]==gameControl.playerBet[playerIndex] && gameControl.playerBet[playerIndex]==gameControl.maxBet){
			this.stay(playerIndex);
		}
		else if(gameControl.foldCounter<3){
			gameControl.playerStay[playerIndex]=0;
			gameControl.foldCounter++;
		}
		else{
			this.stay(playerIndex);
		}
	}

	public void stay(int playerIndex){

		if((playerIndex!=0 && gameControl.foldCounter!=3) && gameControl.foldCounter<3 && juaryPoker.newGameFlag!=2){
			try{
				Thread.sleep(3000);
			}
			catch(Exception ex){

			}
		}

		while(ImageLoading.counter==-1 || ImageLoading.counter==-2){
			try{
				wait();
			}
			catch(Exception e){

			}
		}
		gameControl.playerBet[playerIndex]=gameControl.maxBet;
	}

	public void raise(int playerIndex,int amount){

		if(playerIndex!=0 && gameControl.foldCounter!=3 && juaryPoker.newGameFlag!=2){
			try{
				Thread.sleep(3000);
			}
			catch(Exception ex){

			}
		}

		while(ImageLoading.counter==-1 || ImageLoading.counter==-2){
			try{
				wait();
			}
			catch(Exception e){

			}
		}

		if(gameControl.maxBet>gameControl.playerTotalMoney[playerIndex]){
			this.fold(playerIndex);
		}
		if(gameControl.playerTotalMoney[playerIndex]<amount){
			amount=gameControl.playerTotalMoney[playerIndex];
		}
		gameControl.playerBet[playerIndex]=amount;
		if(amount>gameControl.maxBet)
			gameControl.maxBet=amount;
	}

	public void bet(int playerIndex,int amount){

		if(gameControl.playerTotalMoney[playerIndex]<amount){
			amount=gameControl.playerTotalMoney[playerIndex];
		}

		while(ImageLoading.counter==-1 || ImageLoading.counter==-2){
			try{
				wait();
			}
			catch(Exception e){

			}
		}
		gameControl.playerBet[playerIndex]=amount;

	}



	public void play(){


		/*if(ImageLoading.counter==-1 || ImageLoading.counter==-2){
			Sound.INTRO.loop();
		}*/
		artiInt robot=new artiInt(point);
		int blindFlag=0;

		while(true){


			if(gameControl.foldCounter==3){
				System.out.println("\n3 players have folded");
				break;
			}
			int allEqualFlag=0;
			for(int i=0;i<4;i++){
				System.out.println("\nCOUNTER:\t"+ImageLoading.counter );
				if(gameControl.playerStay[i]==0)
					continue;
				if(ImageLoading.counter>=4 && ImageLoading.counter<=8){
					Sound.DING.play();
				}
				if(i==0){


					gameControl.wait=0;

					if(blindFlag==0){
						System.out.println("Minimum Bet Tk.60\\-");
						System.out.println("\nPlayer "+(i+1));
						System.out.print("1.Bet\n2.Fold");
						if (i==0) {
							while(gameControl.wait!=1 && gameControl.playerStay[0]==1 && juaryPoker.newGameFlag!=2){
								try{
									if(ImageLoading.counter==1){
										Sound.INTRO.stop();
									}
									if(this.CARDSHUFFLEFLAG==0 && ImageLoading.counter==1){
										Sound.CARDSHUFFLE.play();

										this.CARDSHUFFLEFLAG=1;


									}
									if(this.CARDSHUFFLEFLAG==1 && this.LETSPLAYFLAG==0 && ImageLoading.counter==1 && juaryPoker.newGameFlag!=2){
										try{
											Thread.sleep(4000);
										}
										catch(Exception exc){

										}
										Sound.LETSPLAY.play();
										this.LETSPLAYFLAG=1;
										if(juaryPoker.newGameFlag!=2 || (ImageLoading.counter!=-1 && ImageLoading.counter!=-2)){
											try{
												Thread.sleep(1010);
											}
											catch(Exception e1){

											}
										}
										
										if(ImageLoading.counter!=-1 && ImageLoading.counter!=-2){
											ImageLoading.counter=4;
										}
									}



									wait();
								}
								catch(Exception e){

								}
							}



						}
						//this.choice=input.nextInt();

						if(gameControl.choice==1){
							//System.out.print("Amount:");
							//betAmount=input.nextInt();
							this.bet(i, ImageLoading.raise[i]);
						}

						else{
							//System.out.println("YOOOOOOOOOO");
							this.fold(i);
						}
						blindFlag=1;
						//System.out.println("YOOOOOOOOOO");
					}

					else{
						//System.out.println("YOOOOOOOOOO");
						System.out.println("\nPlayer "+(i+1));
						System.out.print("1.Raise\n2.Stay\n3.Fold");
						while(gameControl.wait!=1 && gameControl.playerStay[0]==1 && juaryPoker.newGameFlag!=2){
							try{
								wait();
							}
							catch(Exception e){

							}
						}
						//choice=input.nextInt();

						if(gameControl.choice==1){
							//System.out.print("Amount:");
							//betAmount=input.nextInt();
							this.raise(i, ImageLoading.raise[i]);
						}

						else if(gameControl.choice==2){
							this.stay(i);
						}

						else{
							//System.out.println("ME");
							this.fold(i);
						}
					}
					gameControl.wait=0;
				}

				else{
					//System.out.println("YOOOOOOOOOO");

					System.out.println("\nPlayer "+(i+1));
					System.out.print("1.Raise\n2.Stay\n3.Fold");
					if ((ImageLoading.counter==5 || ImageLoading.counter==6 || ImageLoading.counter==7)) {



					}

					choice=robot.choiceFinder(i);

					if(choice==1){
						//System.out.print("Amount:");
						//betAmount=input.nextInt();
						gameControl.playerBet[i]=robot.raiseAmount(i);

						this.raise(i, gameControl.playerBet[i]);
					}

					else if(choice==2){

						this.stay(i);
					}

					else{
						//System.out.println("ME");
						this.fold(i);
					}

					if(ImageLoading.counter==5){
						ImageLoading.counter=6;
					}
					else if(ImageLoading.counter==6){
						ImageLoading.counter=7;
					}
					else if(ImageLoading.counter==7){
						ImageLoading.counter=8;
					}
				}

				gameControl.wait=0;

			}
			
			//checking if all bets are equal
			int checkBet=-1;
			for(int k=0;k<4;k++){
				if(gameControl.playerStay[k]==1){
					checkBet=gameControl.playerBet[k];
					break;
				}
			}
			if(checkBet==-1){
				break;
			}
			
			for(int i=0;i<4;i++){
				if(gameControl.playerStay[i]==1){
					if(gameControl.playerBet[i]!=checkBet){
						allEqualFlag=1;
						break;
					}
				}
			}
			/*for(int i=0;i<3;i++){
				if(gameControl.playerBet[i]!=gameControl.playerBet[i+1] && gameControl.playerStay[i]==1 && gameControl.playerStay[i+1]==1){
					allEqualFlag=1;
					break;
				}
			}*/

			for(int j=0;j<4;j++){
				System.out.println("PLAYER "+(j+1)+"\t:"+gameControl.playerBet[j]+"STAY:\t"+"\t"+gameControl.playerStay[j]);
			}



			if(allEqualFlag==0){
				System.out.println("MAX BET:\t"+maxBet);
				break;
			}
		}

	}

	public static void staticInitializer(){
		//ImageLoading static initializing statrs
		for(int i=0;i<4;i++){
			ImageLoading.raise[i]=0;
		}

		ImageLoading.counter=1;

		//ImageLoading static initializing ends

		//gameControl static initializing starts
		for(int i=0;i<4;i++){
			gameControl.playerStay[i]=1;
			gameControl.playerBet[i]=0;
			gameControl.point[i]=0;
		}

		gameControl.maxBet=60;
		gameControl.choice=0;
		gameControl.wait=0;
		gameControl.foldCounter=0;


		//gameControl static initializing ends

		//shuffle static initializing starts

		shuffle.boardCardControl=0;

		//shuffle static initializing ends

		//judgement static initializing starts

		for(int i=0;i<4;i++){
			judgement.winnerPlayers[i]=0;
		}

		//judgement static initializing ends

		//preparing static initializing starts

		//preparing static initializing ends

		//rules static initializing starts

		//rules static initializing starts

		juaryPoker.waitController=0;
	}


	public void pointCollector(int ara[]){
		point=ara;
	}


	public void moneyControl(int winner[]){
		int totalBet=0;
		int totalWinner=0;
		int moneyPerWinner;
		for(int i=0;i<4;i++){
			totalBet+=gameControl.playerBet[i];
			this.playerTotalMoney[i]-=gameControl.playerBet[i];
			gameControl.playerBet[i]=0;
			if(winner[i]==1)
				totalWinner++;
		}

		moneyPerWinner=(totalBet/totalWinner);

		for(int i=0;i<4;i++){
			if(winner[i]==1){
				this.playerTotalMoney[i]+=moneyPerWinner;
			}
		}

		for(int i=0;i<4;i++){
			System.out.println("Player "+(i+1)+"\t: "+this.playerTotalMoney[i]);
		}
	}

}
