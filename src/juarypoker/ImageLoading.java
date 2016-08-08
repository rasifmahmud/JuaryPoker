package juarypoker;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.*;

import java.lang.*;


public class ImageLoading extends JPanel implements KeyListener,MouseListener{
	public static int introFlag=0;
	private Image cardImage[] = new Image[100];
	public static int raise[]={0,0,0,0};
	private int blindFlag=0;
	private int temp;
	private int mousePressedCounter=1;
	private int cardAra[]=new int[52];
	private int backImageIndex=53;
	public static int counter=-1;
	private int i=0;
	private int player1CardX=1350/2 - 100;
	private int player2CardX=5;
	private int player3CardX=1350/2 -100;
	private int player4CardX=1350-200;
	private int player1CardY=730-133;
	private int player2CardY=730/2 -146/2;
	private int player3CardY=10;
	private int player4CardY=730/2 -146/2;
	private int boardCardY= player2CardY;
	private int boardCardX= player1CardX-210-112/2;
	private int betFoldX=540;
	private int betFoldY= 483;

	public ImageLoading(int ara[]) {
		cardAra=ara;
		setSize(new Dimension(1350,730));
		setPreferredSize(new Dimension(1350,730));
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
	}
	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		loadImages();


		if(counter==2){
			counter=4;
		}
		if((counter==4 || counter==8) && gameControl.playerStay[0]==0){
			counter=5;
		}

		g.setColor(Color.RED);
		if(juaryPoker.gameEnd==1) {
			counter=0;

		}
		//state 1 drawing
		g.drawImage(getImage("table2.png"),0,0,1366,768,this);

		String moneyString;
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman",Font.BOLD,30));
		moneyString=Integer.toString(gameControl.playerTotalMoney[0]);
		g.drawString("PLAYER[1]    $"+moneyString,1050 , 50);
		moneyString=Integer.toString(gameControl.playerTotalMoney[1]);
		g.drawString("PLAYER[2]    $"+moneyString,1050 , 100);
		moneyString=Integer.toString(gameControl.playerTotalMoney[2]);
		g.drawString("PLAYER[3]    $"+moneyString,1050 , 150);
		moneyString=Integer.toString(gameControl.playerTotalMoney[3]);
		g.drawString("PLAYER[4]    $"+moneyString,1050 , 200);


		String betString;
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman",Font.BOLD,30));
		betString=Integer.toString(gameControl.playerBet[0]);
		g.drawString("[1]    BETS  "+betString,1050 , 550);
		betString=Integer.toString(gameControl.playerBet[1]);
		g.drawString("[2]    BETS  "+betString,1050 , 600);
		betString=Integer.toString(gameControl.playerBet[2]);
		g.drawString("[3]    BETS  "+betString,1050 , 650);
		betString=Integer.toString(gameControl.playerBet[3]);
		g.drawString("[4]    BETS  "+betString,1050 , 700);

		if(gameControl.playerStay[0]==0){
			g.drawString("'F'",1015 , 550);
		}
		if(gameControl.playerStay[1]==0){
			g.drawString("'F'",1015 , 600);
		}
		if(gameControl.playerStay[2]==0){
			g.drawString("'F'",1015 , 650);
		}
		if(gameControl.playerStay[3]==0){
			g.drawString("'F'",1015 , 700);
		}



		if (shuffle.boardCardControl==2 ) {
			if (i==0) {
				counter=4;
				i=1;
			}
		}
		if(shuffle.boardCardControl==0) {
			if (i==1) {
				counter=4;
				i=2;
			}
		}
		if (counter==-1) {
			this.drawMainMenu(g);
		}
		/*else if(counter==-2){
			this.drawRules(g);
		}*/
		
		else if(counter==-3){
			this.drawGameOver(g);
		}
		
		else if(counter==-4){
			this.drawYouWin(g);
		}

		else if(counter==-2){
			this.drawCredits(g);
		}

		else if (counter==0) {
			this.drawWinner(g);

			juaryPoker.gameEnd=0;
		}
		else if (counter==1) {
			this.drawingState1(g);
		}
		/*else if (counter==2) {
			this.drawingState2(g);
		}	
		else if (counter==3 && shuffle.boardCardControl==1) {
			this.drawingState3(g);
		}
		else if (counter==3 && shuffle.boardCardControl==2) {
			this.drawingState3(g);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
		}
		else if (counter==3 && shuffle.boardCardControl==0) {
			this.drawingState3(g);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[12]],boardCardX+ 5*105,boardCardY,95,133,this);

		}*/
		else if (counter==4 && gameControl.playerStay[0]==1 && shuffle.boardCardControl==1) {

			this.drawingState4Player1BetFold(g);
		}
		else if (counter==4 && gameControl.playerStay[0]==1 && shuffle.boardCardControl==2) {

			this.drawingState4Player1BetFold(g);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
		}
		else if (counter==4 && gameControl.playerStay[0]==1 && shuffle.boardCardControl==0) {

			this.drawingState4Player1BetFold(g);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[12]],boardCardX+ 5*105,boardCardY,95,133,this);

		}




		else if (counter==5 && gameControl.playerStay[1]==1 &&shuffle.boardCardControl==1 ) {
			this.drawingState5StayRaiseFold2(g);
		}

		else if (counter==5 && gameControl.playerStay[1]==1 &&shuffle.boardCardControl==2 ) {
			this.drawingState5StayRaiseFold2(g);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
		}
		else if (counter==5 && gameControl.playerStay[1]==1 &&shuffle.boardCardControl==0  ) {
			this.drawingState5StayRaiseFold2(g);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[12]],boardCardX+ 5*105,boardCardY,95,133,this);

		}


		else if (counter==6 && gameControl.playerStay[2]==1  &&shuffle.boardCardControl==1) {
			this.drawingState6StayRaiseFold3(g);
		}
		else if (counter==6 && gameControl.playerStay[2]==1  &&shuffle.boardCardControl==2) {
			this.drawingState6StayRaiseFold3(g);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
		}
		else if (counter==6 && gameControl.playerStay[2]==1  &&shuffle.boardCardControl==0) {
			this.drawingState6StayRaiseFold3(g);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[12]],boardCardX+ 5*105,boardCardY,95,133,this);

		}



		else if (counter==7 && gameControl.playerStay[3]==1 &&shuffle.boardCardControl==1 ) {
			this.drawingState7StayRaiseFold4(g);
		}
		else if (counter==7 && gameControl.playerStay[3]==1 &&shuffle.boardCardControl==2) {
			this.drawingState7StayRaiseFold4(g);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
		}	
		else if (counter==7 && gameControl.playerStay[3]==1 &&shuffle.boardCardControl==0) {
			this.drawingState7StayRaiseFold4(g);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[12]],boardCardX+ 5*105,boardCardY,95,133,this);

		}	


		else if (counter==8 && gameControl.playerStay[0]==1 &&shuffle.boardCardControl==1) {
			this.drawingState8StayRaiseFold1(g);
		}
		else if (counter==8 && gameControl.playerStay[0]==1 &&shuffle.boardCardControl==2) {
			this.drawingState8StayRaiseFold1(g);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
		}
		else if (counter==8 && gameControl.playerStay[0]==1 &&shuffle.boardCardControl==0) {
			this.drawingState8StayRaiseFold1(g);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[12]],boardCardX+ 5*105,boardCardY,95,133,this);

		}



		else if (counter==100 || counter==200 || counter==300 || counter==400) { 
			if (counter==100) {
				this.drawingState100Amount(g);
				this.drawingState1(g);
				this.showBoardCards(g);
				//player1 card drawing on board

				g.drawImage(cardImage[cardAra[0]],player1CardX,player1CardY,95,133,this);
				g.drawImage(cardImage[cardAra[1]],player1CardX+105,player1CardY,95,133,this);

			}
			else if(counter==200) {
				this.drawingState100Amount(g);
				this.drawingState1(g);
				this.showBoardCards(g);
				g.drawImage(cardImage[cardAra[2]],player2CardX,player2CardY,95,133,this);
				g.drawImage(cardImage[cardAra[3]],player2CardX+105,player2CardY,95,133,this);

			}
			else if (counter==300) {
				this.drawingState100Amount(g);
				this.drawingState1(g);
				this.showBoardCards(g);
				g.drawImage(cardImage[cardAra[4]],player3CardX,player3CardY,95,133,this);
				g.drawImage(cardImage[cardAra[5]],player3CardX+105,player3CardY,95,133,this);

			}
			else {
				this.drawingState100Amount(g);
				this.drawingState1(g);
				this.showBoardCards(g);
				g.drawImage(cardImage[cardAra[6]],player4CardX,player4CardY,95,133,this);
				g.drawImage(cardImage[cardAra[7]],player4CardX+105,player4CardY,95,133,this);

			}
		}

		else{
			if(counter==8 || counter==4) {
				//System.out.println(counter);
				counter=5;
			}
			else if (counter==5) {
				counter=6;
			}
			else if (counter==6) {
				counter=7;
			}
			else if (counter==7) {
				counter=8;
			}
		}



		repaint();
	}
	public void showBoardCards(Graphics g) {
		if(shuffle.boardCardControl==1) {
			g.drawImage(cardImage[cardAra[8]],boardCardX+105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[9]],boardCardX+ 2*105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[10]],boardCardX+ 3*105,boardCardY,95,133,this);
		}
		else if(shuffle.boardCardControl==2) {
			g.drawImage(cardImage[cardAra[8]],boardCardX+105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[9]],boardCardX+ 2*105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[10]],boardCardX+ 3*105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
		}
		else {
			g.drawImage(cardImage[cardAra[8]],boardCardX+105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[9]],boardCardX+ 2*105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[10]],boardCardX+ 3*105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
			g.drawImage(cardImage[cardAra[12]],boardCardX+ 5*105,boardCardY,95,133,this);
		}

	}

	public void drawGameOver(Graphics g){
		g.drawImage(cardImage[77], 0, 0, 1366, 768, this);

	}
	
	public void drawYouWin(Graphics g){
		g.drawImage(cardImage[78], 0, 0, 1366, 768, this);

	}
	
	public void drawWinner(Graphics g) {
		int y=100;
		int j=0;
		int ara[]=new int[4];
		String numString;
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman",Font.BOLD,50));
		this.drawingState3(g);
		g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
		g.drawImage(cardImage[cardAra[12]],boardCardX+ 5*105,boardCardY,95,133,this);

		int playerPos=-1;
		for (int i=0;i<judgement.winnerPlayers.length;i++) {



			if (judgement.winnerPlayers[i]==1) {
				ara[j]=i+1;
				playerPos=i;
				j++;
			}

		}
		/*j=2;
		for(int m=0;m<2;m++){
			judgement.winnerPlayers[m]=1;
		}*/
		String handString;
		if (j==1) {


			if(gameControl.point[playerPos]==1){
				handString="ROYAL FLUSH";
			}

			else if(gameControl.point[playerPos]==2){
				handString="STRIAGHT FLUSH";
			}

			else if(gameControl.point[playerPos]==3){
				handString="FOUR OF A KIND";
			}

			else if(gameControl.point[playerPos]==4){
				handString="FULL HOUSE";
			}

			else if(gameControl.point[playerPos]==5){
				handString="FLUSH";
			}

			else if(gameControl.point[playerPos]==6){
				handString="STRAIGHT";
			}

			else if(gameControl.point[playerPos]==7){
				handString="THREE OF A KIND";
			}

			else if(gameControl.point[playerPos]==8){
				handString="TWO PAIR";
			}

			else if(gameControl.point[playerPos]==9){
				handString="ONE PAIR";
			}

			else{
				handString="HIGH CARD";
			}

			numString=Integer.toString(ara[0]);
			g.drawString("PLAYER "+numString+ " WINS", 50,50);
			g.drawString(handString, 50,650);
		}
		else if(j!=1) {
			g.setFont(new Font("TimesRoman",Font.BOLD,35));
			g.drawString("MONEY SPLITTED AMONG:", 50,50);
			g.setFont(new Font("TimesRoman",Font.BOLD,50));
			for (int i=0;i<4;i++) {
				if(judgement.winnerPlayers[i]==1){
					playerPos=i;
					numString=Integer.toString(i+1);
					g.drawString("PLAYER "+numString, 50,50+y);
					y+=100;
				}
			}
			if(gameControl.point[playerPos]==1){
				handString="ROYAL FLUSH";
			}

			else if(gameControl.point[playerPos]==2){
				handString="STRIAGHT FLUSH";
			}

			else if(gameControl.point[playerPos]==3){
				handString="FOUR OF A KIND";
			}

			else if(gameControl.point[playerPos]==4){
				handString="FULL HOUSE";
			}

			else if(gameControl.point[playerPos]==5){
				handString="FLUSH";
			}

			else if(gameControl.point[playerPos]==6){
				handString="STRAIGHT";
			}

			else if(gameControl.point[playerPos]==7){
				handString="THREE OF A KIND";
			}

			else if(gameControl.point[playerPos]==8){
				handString="TWO PAIR";
			}

			else if(gameControl.point[playerPos]==9){
				handString="ONE PAIR";
			}

			else{
				handString="HIGH CARD";
			}

			g.drawString(handString, 50,650);


		}	

	}


	public void drawingState1(Graphics g) {
		//player1 card drawing on board

		g.drawImage(cardImage[backImageIndex],player1CardX,player1CardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],player1CardX+105,player1CardY,95,133,this);

		//player2 card drawing on board

		g.drawImage(cardImage[backImageIndex],player2CardX,player2CardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],player2CardX+105,player2CardY,95,133,this);

		//player3 card drawing on board

		g.drawImage(cardImage[backImageIndex],player3CardX,player3CardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],player3CardX+105,player3CardY,95,133,this);

		//player4 card drawing on board

		g.drawImage(cardImage[backImageIndex],player4CardX,player4CardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],player4CardX+105,player4CardY,95,133,this);

		//drawing 5 board cards

		g.drawImage(cardImage[backImageIndex],boardCardX+105,boardCardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],boardCardX+ 2*105,boardCardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],boardCardX+ 3*105,boardCardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],boardCardX+ 4*105,boardCardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],boardCardX+ 5*105,boardCardY,95,133,this);


	}

	public void drawMainMenu(Graphics g) {
		/*drawGameOverCheck
		g.drawImage(cardImage[77], 0, 0, 1366, 768, this);
		g.drawString("NEW GAME", 201,642);*/
		//introFlag=0;
		String numString;
		g.drawImage(cardImage[68], 0, 0, 1366, 768, this);
		numString=Integer.toString(i+1);
		//g.setColor(Color.darkGray);
		g.drawImage(cardImage[69], 20, 50, 320, 82, this);
		g.drawImage(cardImage[70], 70, 125, 320, 82, this);
		g.drawImage(cardImage[71], 20, 200, 320, 82, this);
		g.drawImage(cardImage[72], 70, 275, 320, 82, this);
		//instruction for rules printing has to be written
		Sound.CARDSHUFFLE.stop();
		Sound.LETSPLAY.stop();
		Sound.APPLAUSE.stop();
		Sound.DRUM.stop();
		Sound.TRUMPET.stop();

		if(introFlag==0){
			gameControl.introPlayer();

		}


		/*g.setFont(new Font("TimesRoman",Font.BOLD,50));
		g.drawString("NEW GAME", 20,100);
		g.drawString("JOIN GAME", 70,175);
		g.drawString("CREDITS", 20,250);
		g.drawString("EXIT", 70,325);*/

	}

	/*public void drawRules(Graphics g) {
		g.drawImage(cardImage[73], 0, 0, 1366, 750, this);
	}*/

	public void drawCredits(Graphics g) {
		g.drawImage(cardImage[75], 0, 0, 1366, 750, this);
		//g.drawString("PLAYER 4 TURN", 650,660);
	}


	public void drawingState2(Graphics g) {
		//player1 card drawing on board

		g.drawImage(cardImage[cardAra[0]],player1CardX,player1CardY,95,133,this);
		g.drawImage(cardImage[cardAra[1]],player1CardX+105,player1CardY,95,133,this);

		//player2 card drawing on board

		g.drawImage(cardImage[cardAra[2]],player2CardX,player2CardY,95,133,this);
		g.drawImage(cardImage[cardAra[3]],player2CardX+105,player2CardY,95,133,this);

		//player3 card drawing on board

		g.drawImage(cardImage[cardAra[4]],player3CardX,player3CardY,95,133,this);
		g.drawImage(cardImage[cardAra[5]],player3CardX+105,player3CardY,95,133,this);

		//player4 card drawing on board

		g.drawImage(cardImage[cardAra[6]],player4CardX,player4CardY,95,133,this);
		g.drawImage(cardImage[cardAra[7]],player4CardX+105,player4CardY,95,133,this);

		//drawing 5 board cards

		g.drawImage(cardImage[backImageIndex],boardCardX+105,boardCardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],boardCardX+ 2*105,boardCardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],boardCardX+ 3*105,boardCardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],boardCardX+ 4*105,boardCardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],boardCardX+ 5*105,boardCardY,95,133,this);
	}



	public void drawingState3(Graphics g) {

		this.drawingState2(g);

		//drawing 3 board cards
		g.drawImage(cardImage[cardAra[8]],boardCardX+105,boardCardY,95,133,this);
		g.drawImage(cardImage[cardAra[9]],boardCardX+ 2*105,boardCardY,95,133,this);
		g.drawImage(cardImage[cardAra[10]],boardCardX+ 3*105,boardCardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],boardCardX+ 4*105,boardCardY,95,133,this);
		g.drawImage(cardImage[backImageIndex],boardCardX+ 5*105,boardCardY,95,133,this);
	}


	public void drawingState4Player1BetFold(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman",Font.BOLD,50));
		g.drawString("PLAYER 1 TURN", 50,50);
		this.drawingState1(g);
		this.showBoardCards(g);

		g.drawImage(cardImage[54],betFoldX,betFoldY, 95,54 , this );
		g.drawImage(cardImage[58],betFoldX+160,betFoldY, 95,54 , this );
		g.drawImage(cardImage[67],betFoldX+620,betFoldY+100,85,85, this);
		g.drawImage(cardImage[cardAra[0]],player1CardX,player1CardY,95,133,this);
		g.drawImage(cardImage[cardAra[1]],player1CardX+105,player1CardY,95,133,this);
		g.setColor(Color.decode("#A5ACD6"));
		g.drawString("Ⓟ",518,680);

		//g.drawImage(cardImage[76],player1CardX-70,player1CardY-25,50,50,this);
	}

	public void drawingState5StayRaiseFold2(Graphics g) {

		g.setColor(Color.decode("#A5ACD6"));
		g.setFont(new Font("TimesRoman",Font.BOLD,50));
		g.drawString("Ⓟ",209,370);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman",Font.BOLD,50));
		g.drawString("PLAYER 2 TURN", 50,50);
		this.drawingState1(g);
		this.showBoardCards(g);		
		//g.drawImage(cardImage[cardAra[2]],player2CardX,player2CardY,95,133,this);
		//g.drawImage(cardImage[cardAra[3]],player2CardX+105,player2CardY,95,133,this);
		g.drawImage(cardImage[cardAra[0]],player1CardX,player1CardY,95,133,this);
		g.drawImage(cardImage[cardAra[1]],player1CardX+105,player1CardY,95,133,this);

		g.drawImage(cardImage[55],player1CardX-75,betFoldY, 95,54 , this );
		g.drawImage(cardImage[56],player1CardX+50,betFoldY, 95,54 , this );
		g.drawImage(cardImage[58],player1CardX+50+125,betFoldY, 95,54 , this );


	}
	public void drawingState6StayRaiseFold3(Graphics g) {


		g.setFont(new Font("TimesRoman",Font.BOLD,50));
		g.setColor(Color.decode("#A5ACD6"));
		g.drawString("Ⓟ",780,100);

		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman",Font.BOLD,50));
		g.drawString("PLAYER 3 TURN", 50,50);
		this.drawingState1(g);
		this.showBoardCards(g);
		//g.drawImage(cardImage[cardAra[4]],player3CardX,player3CardY,95,133,this);
		//g.drawImage(cardImage[cardAra[5]],player3CardX+105,player3CardY,95,133,this);
		g.drawImage(cardImage[cardAra[0]],player1CardX,player1CardY,95,133,this);
		g.drawImage(cardImage[cardAra[1]],player1CardX+105,player1CardY,95,133,this);


		g.drawImage(cardImage[55],player1CardX-75,betFoldY, 95,54 , this );
		g.drawImage(cardImage[56],player1CardX+50,betFoldY, 95,54 , this );
		g.drawImage(cardImage[58],player1CardX+50+125,betFoldY, 95,54 , this );


	}
	public void drawingState7StayRaiseFold4(Graphics g) {
		g.setFont(new Font("TimesRoman",Font.BOLD,50));
		g.setColor(Color.decode("#A5ACD6"));
		g.drawString("Ⓟ",1091,370);

		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman",Font.BOLD,50));
		g.drawString("PLAYER 4 TURN", 50,50);
		this.drawingState1(g);
		this.showBoardCards(g);
		//g.drawImage(cardImage[cardAra[6]],player4CardX,player4CardY,95,133,this);
		//g.drawImage(cardImage[cardAra[7]],player4CardX+105,player4CardY,95,133,this);
		g.drawImage(cardImage[cardAra[0]],player1CardX,player1CardY,95,133,this);
		g.drawImage(cardImage[cardAra[1]],player1CardX+105,player1CardY,95,133,this);

		g.drawImage(cardImage[55],player1CardX-75,betFoldY, 95,54 , this );
		g.drawImage(cardImage[56],player1CardX+50,betFoldY, 95,54 , this );
		g.drawImage(cardImage[58],player1CardX+50+125,betFoldY, 95,54 , this );


	}
	public void drawingState8StayRaiseFold1(Graphics g) {
		g.setFont(new Font("TimesRoman",Font.BOLD,50));
		g.setColor(Color.decode("#A5ACD6"));
		g.drawString("Ⓟ",518,680);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman",Font.BOLD,50));
		g.drawString("PLAYER 1 TURN", 50,50);
		this.drawingState1(g);
		this.showBoardCards(g);
		g.drawImage(cardImage[cardAra[0]],player1CardX,player1CardY,95,133,this);
		g.drawImage(cardImage[cardAra[1]],player1CardX+105,player1CardY,95,133,this);

		g.drawImage(cardImage[55],player1CardX-75,betFoldY, 95,54 , this );
		g.drawImage(cardImage[56],player1CardX+50,betFoldY, 95,54 , this );
		g.drawImage(cardImage[58],player1CardX+50+125,betFoldY, 95,54 , this );


	}





	public void drawingState100Amount(Graphics g) {
		this.drawingState3(g);
		String numString;

		if (counter==100) {
			g.setFont(new Font("TimesRoman",Font.BOLD,50));
			g.setColor(Color.decode("#A5ACD6"));
			g.drawString("Ⓟ",518,680);
			raise[0]=gameControl.maxBet;
			numString=Integer.toString(raise[0]);
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman",Font.BOLD,50));
			g.drawString("PLAYER 1 TURN", 50,50);

		}
		else if (counter==200) {
			raise[1]=gameControl.maxBet;
			numString=Integer.toString(raise[1]);
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman",Font.BOLD,50));
			g.drawString("PLAYER 2 TURN", 50,50);

		}
		else if (counter==300) {
			raise[2]=gameControl.maxBet;
			numString=Integer.toString(raise[2]);
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman",Font.BOLD,50));
			g.drawString("PLAYER 3 TURN", 50,50);

		}
		else {
			raise[3]=gameControl.maxBet;
			numString=Integer.toString(raise[3]);
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman",Font.BOLD,50));
			g.drawString("PLAYER 4 TURN", 50,50);

		}



		g.drawImage(cardImage[57],betFoldX-10,betFoldY-40, 95,54 , this );
		g.drawImage(cardImage[66],player1CardX+50+95,betFoldY-40, 95,54 , this );
		g.drawImage(cardImage[64],player1CardX+50,betFoldY+20, 95,54 , this );
		g.setFont(new Font("TimesRoman",Font.BOLD,30));
		g.drawString(numString, player1CardX+50+95+18, betFoldY-3);

	}




	public void drawingState5(Graphics g) {
		//drawing 4 board cards
		this.drawingState2(g);
		g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
		g.drawImage(cardImage[cardAra[8]],boardCardX+105,boardCardY,95,133,this);
		g.drawImage(cardImage[cardAra[9]],boardCardX+ 2*105,boardCardY,95,133,this);
		g.drawImage(cardImage[cardAra[10]],boardCardX+ 3*105,boardCardY,95,133,this);


	}




	public void drawingState6(Graphics g) {
		//drawing 5 board cards
		this.drawingState3(g);
		g.drawImage(cardImage[cardAra[8]],boardCardX+105,boardCardY,95,133,this);
		g.drawImage(cardImage[cardAra[9]],boardCardX+ 2*105,boardCardY,95,133,this);
		g.drawImage(cardImage[cardAra[10]],boardCardX+ 3*105,boardCardY,95,133,this);
		g.drawImage(cardImage[cardAra[11]],boardCardX+ 4*105,boardCardY,95,133,this);
		g.drawImage(cardImage[cardAra[12]],boardCardX+ 5*105,boardCardY,95,133,this);

	}


	public Image getImage(String path) {
		Image tempImage= null;
		try {
			URL imageURL= ImageLoading.class.getResource("img/"+path);
			tempImage=Toolkit.getDefaultToolkit().getImage(imageURL);
		}
		catch (Exception e) {
			System.out.println("An Error Occured:"+e.getMessage());
		}

		return tempImage;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int c=e.getKeyCode();
		if (c== KeyEvent.VK_W) {
			counter++;

			if (counter==6) {
				counter=1;
			}
		}
		if (c==KeyEvent.VK_ESCAPE) {
			if(counter!=1){
				temp=counter;
			}
			else{
				temp=4;
			}
			counter=-1;
			introFlag=0;
		}

		if (c==KeyEvent.VK_ENTER) {
			juaryPoker.waitController=1;
		}

		if (c==KeyEvent.VK_SPACE) {
			counter=1;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public void loadImages() {
		cardImage[0]= getImage("2_of_clubs.png");
		cardImage[1]= getImage("3_of_clubs.png");
		cardImage[2]= getImage("4_of_clubs.png");
		cardImage[3]= getImage("5_of_clubs.png");
		cardImage[4]= getImage("6_of_clubs.png");
		cardImage[5]= getImage("7_of_clubs.png");
		cardImage[6]= getImage("8_of_clubs.png");
		cardImage[7]= getImage("9_of_clubs.png");
		cardImage[8]= getImage("10_of_clubs.png");
		cardImage[9]= getImage("jack_of_clubs2.png");
		cardImage[10]= getImage("queen_of_clubs2.png");
		cardImage[11]= getImage("king_of_clubs2.png");
		cardImage[12]= getImage("ace_of_clubs.png");
		cardImage[13]= getImage("2_of_diamonds.png");
		cardImage[14]= getImage("3_of_diamonds.png");
		cardImage[15]= getImage("4_of_diamonds.png");
		cardImage[16]= getImage("5_of_diamonds.png");
		cardImage[17]= getImage("6_of_diamonds.png");
		cardImage[18]= getImage("7_of_diamonds.png");
		cardImage[19]= getImage("8_of_diamonds.png");
		cardImage[20]= getImage("9_of_diamonds.png");
		cardImage[21]= getImage("10_of_diamonds.png");
		cardImage[22]= getImage("jack_of_diamonds2.png");
		cardImage[23]= getImage("queen_of_diamonds2.png");
		cardImage[24]= getImage("king_of_diamonds2.png");
		cardImage[25]= getImage("ace_of_diamonds.png");
		cardImage[26]= getImage("2_of_hearts.png");
		cardImage[27]= getImage("3_of_hearts.png");
		cardImage[28]= getImage("4_of_hearts.png");
		cardImage[29]= getImage("5_of_hearts.png");
		cardImage[30]= getImage("6_of_hearts.png");
		cardImage[31]= getImage("7_of_hearts.png");
		cardImage[32]= getImage("8_of_hearts.png");
		cardImage[33]= getImage("9_of_hearts.png");
		cardImage[34]= getImage("10_of_hearts.png");
		cardImage[35]= getImage("jack_of_hearts2.png");
		cardImage[36]= getImage("queen_of_hearts2.png");
		cardImage[37]= getImage("king_of_hearts2.png");
		cardImage[38]= getImage("ace_of_hearts.png");
		cardImage[39]= getImage("2_of_spades.png");
		cardImage[40]= getImage("3_of_spades.png");
		cardImage[41]= getImage("4_of_spades.png");
		cardImage[42]= getImage("5_of_spades.png");
		cardImage[43]= getImage("6_of_spades.png");
		cardImage[44]= getImage("7_of_spades.png");
		cardImage[45]= getImage("8_of_spades.png");
		cardImage[46]= getImage("9_of_spades.png");
		cardImage[47]= getImage("10_of_spades.png");
		cardImage[48]= getImage("jack_of_spades2.png");
		cardImage[49]= getImage("queen_of_spades2.png");
		cardImage[50]= getImage("king_of_spades2.png");
		cardImage[51]= getImage("ace_of_spades2.png");


		cardImage[52]=getImage("test.png");
		cardImage[53]=getImage("card_back.png");


		cardImage[54]=getImage("Bet.png");
		cardImage[55]=getImage("Stay.png");
		cardImage[56]=getImage("Raise.png");
		cardImage[57]=getImage("Amount.png");
		cardImage[58]=getImage("Fold.png");
		cardImage[59]=getImage("BetMouseOver.png");
		cardImage[60]=getImage("StayMouseOver.png");
		cardImage[61]=getImage("RaiseMouseOver.png");
		cardImage[62]=getImage("AmountMouseOver.png");
		cardImage[63]=getImage("FoldMouseOver.png");
		cardImage[64]=getImage("Ok.png");
		cardImage[65]=getImage("OkMouseOver.png");
		cardImage[66]=getImage("Box.png");
		cardImage[67]=getImage("All-In.png");
		cardImage[68]=getImage("MainMenu.jpg");
		cardImage[69]=getImage("NewGame.png");
		cardImage[70]=getImage("JoinTable.png");
		cardImage[71]=getImage("Credits.png");
		cardImage[72]=getImage("Exit.png");
		cardImage[73]=getImage("Rules.jpg");
		cardImage[74]=getImage("Rules_Menu.png");
		cardImage[75]=getImage("CreditsImage.jpg");
		cardImage[76]=getImage("P.png");
		cardImage[77]=getImage("GameOver.png");
		cardImage[78]=getImage("YouWin.png");
	}	
	/*	public static void main (String args[]) {
		//drawing code starts here

		ImageLoading game= new ImageLoading();
		JFrame frame= new JFrame();
		frame.setTitle("Juary Poker Game");
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		//drawing code finishes here
	} */
	@Override
	public void mouseClicked(MouseEvent e) {


	}
	@Override
	public void mouseEntered(MouseEvent e) {


	}
	@Override
	public void mouseExited(MouseEvent arg0) {

	}
	@Override
	public void mousePressed(MouseEvent e) {

		// PLACE YOUR MOUSE HANDLING CODE FOR MAIN MENU


		if (counter==-1) {
			int x=e.getX();
			int y=e.getY();

			//NEW GAME
			if (x>=35 && x<=320 && y>=72 && y<=107) {
				//gameControl.staticInitializer();
				juaryPoker.joinTableFlag=1;
				if(juaryPoker.newGameFlag==0){
					counter=1;
					Sound.INTRO.stop();
					juaryPoker.newGameFlag=1;

				}
				else{
					counter=1;
					juaryPoker.newGameFlag=2;
				}
			}

			//JOIN TABLE
			else if (x>=75 && x<=378 && y>=147 && y<=182) {
				if(juaryPoker.joinTableFlag==1){
					Sound.INTRO.stop();
					counter=temp;
				}
				else{

				}

			}

			//RULES
			//co-ordinate vul deoa ase,apatoto CREDITS er ta deoa
			/*else if(x>=60 && x<=276 && y>=222 && y<=257){
				counter=-2;
			}*/

			//CREDITS
			else if (x>=60 && x<=276 && y>=222 && y<=257) {
				counter=-2;
			}

			//EXIT
			else if (x>=170 && x<=280 && y>=295 && y<=330) {
				System.exit(0);
			}

		}// FINISHED YOUR MOUSE HANDLING CODE FOR MAIN MENU 

		//CREDITS BACK PRESS CODE
		if(counter==-2){
			int x=e.getX();
			int y=e.getY();
			if(x>=650 && x<=735 && y>=610 && y<=710){
				counter=-1;
			}

		}
		
		//GAME OVER BACK PRESS CODE
		if(counter==-3){
			int x=e.getX();
			int y=e.getY();
			if(x>=76 && x<=201 && y>=642 && y<=675){
				counter=-1;
				introFlag=0;
				Sound.EVILLAUGH.stop();
			}
		}
		
		//YOU WIN BACK PRESS CODE
		if(counter==-4){
			int x=e.getX();
			int y=e.getY();
			if(x>=76 && x<=201 && y>=642 && y<=675){
				counter=-1;
				introFlag=0;
				Sound.WINNERSONG.stop();
			}
		}
		
		
		else if (counter==4) {
			//bet clicking
			int x=e.getX();
			int y=e.getY();
			if (x >=540 && x<=540+95 && y>=483 && y<=483+54   ) {
				gameControl.choice=1;
				counter=100;
			}
			//fold clicking
			else if (x>= 540+160 && x<=540+160+95 && y>=483 && y <=483+54) {
				gameControl.choice=2;
				gameControl.wait=1;
				counter=5;

			}
		}//bet or fold finishes here

		else if (counter==5 || counter==6 ||counter==7 || counter==8) {




			//draw code for state 5

			int x=e.getX();
			int y=e.getY();

			// stay clicking
			if (x >=(player1CardX-75) && x<=(player1CardX-75+95) && y>=betFoldY && y<=betFoldY+54   ) {


				if(counter==8) {
					raise[0]=gameControl.maxBet;
					counter=5;
				}
				/*else if (counter==5) {
					raise[1]=gameControl.maxBet;
					counter=6;
				}
				else if (counter==6) {
					raise[2]=gameControl.maxBet;
					counter=7;
				}
				else if (counter==7) {
					raise[3]=gameControl.maxBet;
					counter=8;
				}*/

				gameControl.choice=1;
				gameControl.wait=1;

			}

			//raise clicking
			else if (x >=(player1CardX+50) && x<=(player1CardX+50+95) && y>=betFoldY && y<=betFoldY+54   ) {
				gameControl.choice=2;

				if(counter==8) {
					counter=100;
				}
				/*else if (counter==5) {
					counter=200;
				}
				else if (counter==6) {
					counter=300;
				}
				else if (counter==7) {
					counter=400;
				}*/
				System.out.println("enterd"+counter);
			}

			//fold clicking
			else if (x >=(player1CardX+175) && x<=(player1CardX+175+95) && y>=betFoldY && y<=betFoldY+54   ) {

				gameControl.choice=3;
				gameControl.wait=1;
				/*if (counter==5) {
					counter=6;
				}
				else if (counter==6) {
					counter=7;
				}
				else if (counter==7) {
					counter=8;
				}*/
				if(counter==8) {
					counter=5;
				}


			}





		}


		//RAISING VALUE
		else if (counter==100) {
			// ++60 clicking for player1

			int x=e.getX();
			int y=e.getY();
			if (x >=(betFoldX-10) && x<=(betFoldX-10+95) && y>=(betFoldY-40) && y<=betFoldY-40+54   ) {
				raise[0]=gameControl.maxBet;
				if(gameControl.playerTotalMoney[0]>=raise[0]+60){
					raise[0]+=60;
				}
				gameControl.maxBet=raise[0];

			}
			// clicking okay
			else if (x >=(player1CardX+50) && x<=(player1CardX+50+95) && y>=(betFoldY+20) && y<=betFoldY+20+54 ) {
				gameControl.maxBet=raise[0];
				gameControl.choice=1;
				gameControl.wait=1;
				counter=5;


			}
		}
		/*else if (counter==200) {
			// ++60 clicking for player2

			int x=e.getX();
			int y=e.getY();

			if (x >=(betFoldX-10) && x<=(betFoldX-10+95) && y>=(betFoldY-40) && y<=betFoldY-40+54   ) {
				raise[1]=gameControl.maxBet;
				raise[1]+=60;
				gameControl.maxBet=raise[1];			}
			// clicking okay
			else if (x >=(player1CardX+50) && x<=(player1CardX+50+95) && y>=(betFoldY+20) && y<=betFoldY+20+54 ) {
				gameControl.maxBet=raise[1];
				gameControl.choice=1;
				gameControl.wait=1;
				System.out.println("counter 200:"+counter);
				counter=6;
			}
		}
		else if (counter==300) {
			// ++60 clicking for player3

			int x=e.getX();
			int y=e.getY();
			if (x >=(betFoldX-10) && x<=(betFoldX-10+95) && y>=(betFoldY-40) && y<=betFoldY-40+54   ) {
				raise[2]=gameControl.maxBet;
				raise[2]+=60;
				gameControl.maxBet=raise[2];
			}
			// clicking okay
			else if (x >=(player1CardX+50) && x<=(player1CardX+50+95) && y>=(betFoldY+20) && y<=betFoldY+20+54 ) {
				gameControl.maxBet=raise[2];
				gameControl.choice=1;
				gameControl.wait=1;
				counter=7;
			}
		}
		else if (counter==400) {
			// ++60 clicking for player4

			int x=e.getX();
			int y=e.getY();
			if (x >=(betFoldX-10) && x<=(betFoldX-10+95) && y>=(betFoldY-40) && y<=betFoldY-40+54   ) {
				raise[3]=gameControl.maxBet;
				raise[3]+=60;
				gameControl.maxBet=raise[3];

			}
			// clicking okay
			else if (x >=(player1CardX+50) && x<=(player1CardX+50+95) && y>=(betFoldY+20) && y<=betFoldY+20+54 ) {
				gameControl.maxBet=raise[3];
				gameControl.choice=1;
				gameControl.wait=1;
				counter=8;
			}
		}*/

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
