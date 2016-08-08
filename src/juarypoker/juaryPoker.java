package juarypoker;
import java.io.*;
import java.net.*;
import java.util.Arrays;

import javax.swing.*;

public class juaryPoker {

	public static int gameEnd=0;
	public static int waitController=0;
	public static int newGameFlag=0;
	public static int frameFlag=0;
	public static int joinTableFlag=0;

	public static void main(String[] args) throws Exception {


		while(true) {
			

			shuffle play=new shuffle();
			preparing prep=new preparing();
			rules gameOn=new rules();
			gameControl master=new gameControl();


			play.playerCardDistribution();
			play.boardCardDistribution();

			
			
			//drawing code starts here

			ImageLoading game= new ImageLoading(play.cardIndex );
			JFrame frame= new JFrame();
			frame.setTitle("Juary Poker Game");
			frame.add(game);
			frame.pack();
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			/*if(frameFlag==0) {
				frame.setVisible(true);
				
			}*/

			//drawing code finishes here
			
			

			int playerPoint[]=new int[4];

			/*System.out.println(gameControl.choice);
			gameControl.choice=50;
			System.out.println(gameControl.choice);*/

			for(int i=0;i<4;i++){
				int playersDeck[]=new int[2];
				int boardDeck[]=new int[5];
				int combinedDeck[]=new int[7];
				playersDeck=play.playerCards[i];
				boardDeck=play.boardPlayCards();
				combinedDeck=prep.playersCombinedCards(playersDeck, boardDeck, i);
				//play.printCard(i);
				play.showPlayerCards(i);

				playerPoint[i]=gameOn.gamePoint(combinedDeck);
			}

			master.pointCollector(playerPoint);

			for(int i=0;i<3;i++){
				play.showBoardCards();
				master.play();
				System.out.println("\nBOARD CARD CONTROL:\t"+shuffle.boardCardControl);
				if(gameControl.foldCounter==3){
					break;
				}
			}
			if(newGameFlag==2){
				frameFlag=1;
				newGameFlag=1;
				//static initializer

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
					gameControl.playerTotalMoney[i]=360;
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

				waitController=0;
				frameFlag=1;
				frame.setVisible(false);
				frame.dispose();
				continue;
			}

			for(int i=0;i<4;i++){
				System.out.println("Player "+(i+1)+":\t"+playerPoint[i]);
			}

			judgement judge=new judgement(playerPoint,master.playerStay);
			System.out.println(judge.repeatedHand);
			judge.winnerSelector(playerPoint,prep.allPlayerCards,master.playerStay);

			master.moneyControl(judge.winnerPlayers);
			juaryPoker.gameEnd=1;

			for(int i=0;i<4;i++){
				System.out.println(gameControl.playerStay[i]);
			}


			Sound.APPLAUSE.play();
			Sound.TRUMPET.play();
			Sound.DRUM.play();


			Thread.sleep(10000);

			//static initializer

			//ImageLoading static initializing statrs
			for(int i=0;i<4;i++){
				ImageLoading.raise[i]=0;
			}

			if(gameControl.playerTotalMoney[0]==0){
				frameFlag=1;
				newGameFlag=1;
				joinTableFlag=0;
				ImageLoading.counter=-3;
				Sound.EVILLAUGH.loop();
				frame.setVisible(false);
				frame.dispose();
			}
			else if(gameControl.playerTotalMoney[1]==0 && gameControl.playerTotalMoney[2]==0 && gameControl.playerTotalMoney[3]==0){
				frameFlag=1;
				newGameFlag=1;
				joinTableFlag=0;
				ImageLoading.counter=-4;
				Sound.WINNERSONG.loop();
				frame.setVisible(false);
				frame.dispose();
			}
			else{
				ImageLoading.counter=1;
			}

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

			waitController=0;
			frameFlag=1;
			frame.setVisible(false);
			frame.dispose();
		}
	}


}




