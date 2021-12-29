//Sophie Yang
//Due May 25, 2021
//Program that simulates the game "Finish the Lyric", with three song categories (love, heartbreak, empowerment)

package project_finishthelyric;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FinishTheLyricFinal {

  public static void main(String[] args) throws InterruptedException {
    //declaring variables
    Scanner in = new Scanner(System.in);
    String categoryChoiceTemp = null;
    int categoryChoice; //stores the category choice
    int randomSong = 0; //stores the random number generated, which will be used for song choice
    int randomArray [] = {-1, -1, -1}; //array to check if song choices repeat
    String answerTemp = null; 
    int answer = 0; //stores the user input
    int count; //stores points

    //MAIN MENU
    //welcome display		
    System.out.println("\u001b[36m******************************************************************************\n"); //cyan
    System.out.println("\t\t\t\t\t\t\t\tWelcome to...");
    System.out.println("\t\t\t\t\t\t\t\t🎤  FINISH THE LYRIC! 🎤\n");
    System.out.println("******************************************************************************\n\n");
    
    TimeUnit.SECONDS.sleep(2);
    
    while(true){
      //reset score
      count = 0;

      //display main menu options
      System.out.println("\u001b[33mMain menu:"); //orange
      System.out.println("1 - Songs about love 💕 ");
      System.out.println("2 - Songs about heartbreak 💔 ");
      System.out.println("3 - Songs about empowerment 👑 ");
      System.out.println("4 - Quit program 🛑\n");
      TimeUnit.SECONDS.sleep(2);

      //prompt user input to choose a category/quit
      System.out.println("\u001b[37mPlease enter a number to choose your category: "); //white

      //verify input
      while(true) {
        categoryChoiceTemp = in.nextLine();
        try {
          categoryChoice = Integer.parseInt(categoryChoiceTemp);
          if(categoryChoice == 1){
            System.out.print("Your category is: Songs about love 💕 ");
            TimeUnit.SECONDS.sleep(3);
            break;
          }
          else if(categoryChoice == 2){
            System.out.print("Your category is: Songs about heartbreak 💔 ");
            TimeUnit.SECONDS.sleep(3);
            break;
          }
          else if(categoryChoice == 3){
            System.out.print("Your category is: Songs about empowerment 👑 ");TimeUnit.SECONDS.sleep(3);
            break;
          }
          else if(categoryChoice == 4){
            System.out.print("See you again soon! 👋 ");
            in.close();
            System.exit(0);
          }
          else {
            System.out.println("Invalid choice. 🥸  Please enter the integers 1, 2, 3, or 4:");
          }
        }
        catch(Exception e) {
          System.out.println("Invalid choice. 🥸  Please enter the integer 1, 2, 3, or 4:");
        }
      }
    
      //clear screen
      System.out.print("\033[H\033[2J");  
      System.out.flush();
      
      //GAME
      //generate a random number without repetition
      for(int i = 0; i <= 2; i++) {
        randomSong = (int)(Math.random()*6);
        
        if(randomSong == randomArray[0] || randomSong == randomArray[1] || randomSong == randomArray[2]) {
          i--;
        }
        else {
          randomArray[i] = randomSong;
        }
      }

      for(int i = 0; i <= 2; i++) { //three questions per round
        randomSong = randomArray[i];
        
        //display random song choice and lyrics
        System.out.println(generateRandomLyric(categoryChoice, randomSong) + "\n");
        TimeUnit.SECONDS.sleep(3);
        
        //prompt for user to enter their answer
        System.out.println("\u001b[33mPlease fill in the missing lyric: 🎤 🎵\u001b[0m\n"); //orange
        System.out.println(answerChoices(categoryChoice, randomSong) + "\n");
        System.out.println("Your choice (please enter a number from 1-4):"); //white
        
        //verify input
        while(true) {
          answerTemp = in.nextLine();

          try {
            answer = Integer.parseInt(answerTemp);
            if(answer == 1 || answer == 2 || answer == 3 || answer == 4) {
              break;
            }
            else {
              System.out.println("Invalid choice. 🥸 Please enter the integer 1, 2, 3, or 4:");
            }
          }
          catch(Exception e) {
            System.out.println("Invalid choice. 🥸 Please enter an integer 1, 2, 3, or 4:");
          }
        
        }

        //tell user if they are correct/incorrect
        if(answer == lyricAnswers(categoryChoice, randomSong)) {
          System.out.println("");
          System.out.println("\u001b[32mPERFECT! ✅\u001b[0m\n"); //green
          count++;
          System.out.print("Please press the enter key to go to the next question ☞ ");
          in.nextLine();
        }
        else {
          System.out.println("");
          System.out.print("\u001b[31mGood try! ❌  \u001b[0m"); //red
          TimeUnit.SECONDS.sleep(1);
          System.out.println("The correct answer was actually choice number " + lyricAnswers(categoryChoice, randomSong) + ". 🙂" + "\n");
          TimeUnit.SECONDS.sleep(2);
          System.out.print("Please press the enter key to continue ☞ ");
          in.nextLine();
        }
        
        //spacer between questions
        System.out.print("\033[H\033[2J");  
        System.out.flush();
      }
      
      //display score at the end of game
      System.out.println("Awesome job! Your score is: " + count + "/3. Thanks for playing! \n");
      TimeUnit.SECONDS.sleep(1);

      //animal band
      System.out.print("🎺 🦨  ");
      TimeUnit.SECONDS.sleep(1);
      System.out.print("🎸 🦡  ");
      TimeUnit.SECONDS.sleep(1);
      System.out.print("🥁 🦝  ");
      TimeUnit.SECONDS.sleep(1);
      System.out.print("🎷 🦫  ");
      TimeUnit.SECONDS.sleep(1);
      System.out.print("🪗 🐿  ");
      TimeUnit.SECONDS.sleep(1);
      System.out.print("🪕 🐀  ");
      TimeUnit.SECONDS.sleep(1);
      System.out.println("🎤 🦔  \n");
      TimeUnit.SECONDS.sleep(2);
      
      System.out.print("Please press the enter key to return to the main menu.");
      in.nextLine();

      //clear screen
      System.out.print("\033[H\033[2J");  
      System.out.flush();

    }
  }

  /* method generateRandomLyric() generates the lyrics to a random song  
  * pre: categoryChoice is an integer between 1-3 and randomSong is an integer between 0-5
  * post: returns the lyrics to the random song generated in main
  */

  public static String generateRandomLyric(int categoryChoice, int randomSong) {
    String lyricChoice = null;
    
    //Love songs lyrics
    String [] loveLyrics = new String [6];
    
    loveLyrics[0] = "\u001b[36mLover by Taylor Swift\n\n"
    + "Ladies and gentlemen, will you please stand?\n"
    + "With every guitar string scar on my hand\n"
    + "__________________________\n\n\u001b[0m";
    
    loveLyrics[1] = "\u001b[36mWillow by Taylor Swift\n\n"
    + "Life was a willow and it bent right to your wind (oh)\n"
    + "Head on the pillow, I could feel you sneaking in\n"
    + "As if you were a mythical thing\n"
    + "Like you were a trophy or a champion ring\n"
    + "__________________________\n\n\u001b[0m";
    
    loveLyrics[2] = "\u001b[36mPerfect by Ed Sheeran\n\n"
    + "'Cause we were just kids when we fell in love\n"
    + "Not knowing what it was\n"
    + "__________________________\n\n\u001b[0m";
    
    loveLyrics[3] = "\u001b[36mPerfect by One Direction\n\n"
    + "And if you like midnight driving with the windows down\n"
    + "And if you like going places we can't even pronounce\n"
    + "__________________________\n\n\u001b[0m";
    
    loveLyrics[4] = "\u001b[36mLine By Line by JP Saxe ft. Maren Morris\n\n"
    + "There are things that I sing that I'll never have the confidence to say\n"
    + "Like I'm still not convinced that I won't be too much for you someday\n"
    + "__________________________\n\n\u001b[0m";
    
    loveLyrics[5] = "\u001b[36mOcean Eyes by Billie Eilish\n\n"
    + "I've been watchin' you for some time\n"
    + "Can't stop starin' at those ocean eyes\n"
    + "__________________________\n\n\u001b[0m";
    
    //Heartbreak songs lyrics
    String [] heartbreakLyrics = new String [6];
    
    heartbreakLyrics[0] = "\u001b[36mDriver's License by Olivia Rodrigo\n\n"
    + "And I know we weren't perfect\n"
    + "But I've never felt this way for no one, oh\n"
    + "And I just can't imagine how you could be so okay, now that I'm gone\n"
    + "I guess you didn't mean what you wrote in that song about me\n"
    + "__________________________\n\n\u001b[0m";
    
    heartbreakLyrics[1] = "\u001b[36mFalling by Harry Styles\n\n"
    + "You said you cared\n"
    + "And you missed me too\n"
    + "And I'm well aware I write too many songs about you\n"
    + "And the coffee's out\n"
    + "At the Beachwood Cafe\n"
    + "__________________________\n\n\u001b[0m";
    
    heartbreakLyrics[2] = "\u001b[36mSomeone You Loved by Lewis Capaldi\n\n"
    + "Now the day bleeds\n"
    + "Into nightfall\n"
    + "And you're not here\n"
    + "To get me through it all\n"
    + "I let my guard down\n"
    + "__________________________\n\n\u001b[0m";
    
    heartbreakLyrics[3] = "\u001b[36mWithout Me by Halsey\n\n"
    + "Tell me how's it feel sittin' up there\n"
    + "Feeling so high but too far away to hold me\n"
    + "__________________________\n\n\u001b[0m";
    
    heartbreakLyrics[4] = "\u001b[36mDancing With A Stranger by Sam Smith ft. Normani\n\n"
    + "I don't wanna be alone tonight \n"
    + "It's pretty clear that I'm not over you\n"
    + "__________________________\n\n\u001b[0m";
        
    heartbreakLyrics[5] = "\u001b[36mInfinity by One Direction\n\n"
    + "How many nights does it take to count the stars?\n"
    + "That's the time it would take to fix my heart\n"
    + "Oh, baby, I was there for you\n"
    + "__________________________\n\n\u001b[0m";
    
    //Empowerment song lyrics
    String [] empowermentLyrics = new String [6];
    
    empowermentLyrics[0] = "\u001b[36mThank U, Next by Ariana Grande\n\n"
    + "One taught me love\n"
    + "One taught me patience\n"
    + "__________________________\n\n\u001b[0m";
    
    empowermentLyrics[1] = "\u001b[36mScars To Your Beautiful by Alessia Cara\n\n"
    + "But there's a hope that's waiting for you in the dark\n"
    + "You should know you're beautiful just the way you are\n"
    + "And you don't have to change a thing\n"
    + "__________________________\n\n\u001b[0m";
    
    empowermentLyrics[2] = "\u001b[36mGood As Hell by Lizzo\n\n"
    + "Come now, come dry your eyes\n"
    + "You know you a star, you can touch the sky\n"
    + "I know that it's hard but you have to try\n"
    + "__________________________\n\n\u001b[0m";
    
    empowermentLyrics[3] = "\u001b[36mJuice by Lizzo\n\n"
    + "If I'm shining, everybody gonna shine\n"
    + "I was born like this, don't even gotta try\n"
    + "__________________________\n\n\u001b[0m";
    
    empowermentLyrics[4] = "\u001b[36mShake It Off by Taylor Swift\n\n"
    + "But I keep cruising\n"
    + "Can't stop, won't stop grooving\n"
    + "It's like I got this music in my mind\n"
    + "__________________________\n\n\u001b[0m";
    
    empowermentLyrics[5] = "\u001b[36mLook At Her Now by Selena Gomez\n\n"
    + "Of course she was sad\n"
    + "But now she's glad she dodged a bullet\n"
    + "__________________________\n\n\u001b[0m";

    if(categoryChoice == 1) {
      lyricChoice = loveLyrics [randomSong];
    }
    else if(categoryChoice == 2) {
      lyricChoice = heartbreakLyrics [randomSong];
    }
    else if(categoryChoice == 3) {
      lyricChoice = empowermentLyrics [randomSong];
    }
    
    return lyricChoice;
  }



  /* method answerChoices() generates the answer choices for finishing the lyrics   
  * pre: categoryChoice is an integer between 1-3 and randomSong is an integer between 0-5
  * post: returns answer choices for the random song generated in main
  */

  public static String answerChoices(int categoryChoice, int randomSong) {
    String choice = null; 
    
    //Love lyric answers
    String [] loveAnswerChoices = new String [6];

    loveAnswerChoices [0] = "\u001b[33m1 - All's well that ends well to end up with you\n"
    + "2 - Swear to be overdramatic and true to my lover\n"
    + "3 - Can we always be this close forever and ever?\n"
    + "4 - I take this magnetic force of a man to be my lover\n\u001b[0m";

    loveAnswerChoices [1] = "\u001b[33m1 - Lost in your current like a priceless wine\n"
    + "2 - And there was one prize I'd cheat to win\n"
    + "3 - I'm begging for you to take my hand\n"
    + "4 - Life was a willow and it bent right to your wind\n\u001b[0m";
  
    loveAnswerChoices [2] = "\u001b[33m1 - I'm dancing in the dark with you between my arms\n"
    + "2 - Oh, I never knew you were the someone waiting for me\n"
    + "3 - I will not give you up this time\n"
    + "4 - Fighting against all odds\n\u001b[0m";
    
    loveAnswerChoices [3] = "\u001b[33m1 - If you like to do whatever you've been dreaming about\n"
    + "2 - If you like causing trouble up in hotel rooms\n"
    + "3 - If you like having secret little rendezvous\n"
    + "4 - Then baby, you're perfect\n\u001b[0m";
    loveAnswerChoices [4] = "\u001b[33m1 - Yeah, we both know the way it goes, hear my fears all on the radio\n"
    + "2 - The truth don't scare me in a melody\n"
    + "3 - Immortalizing my sincerity\n"
    + "4 - So I just take you line by line\n\u001b[0m";
    
    loveAnswerChoices [5] = "\u001b[33m1 - Fifteen flares inside those ocean eyes\n"
    + "2 - I've never fallen from quite this high\n"
    + "3 - Burning cities and napalm skies\n"
    + "4 - You really know how to make me cry\n\u001b[0m";
    
    //Heartbreak lyric answers
    String [] heartbreakAnswerChoices = new String [6];
    
    heartbreakAnswerChoices [0] = "\u001b[33m1 - How could I ever love someone else?\n"
    + "2 - 'Cause you said forever, now I drive alone past your street\n"
    + "3 - Can't drive past the places we used to go to\n"
    + "4 - Pictured I was driving home to you\n\u001b[0m";
    
    heartbreakAnswerChoices [1] = "\u001b[33m1 - I can't unpack the baggage you left\n"
    + "2 - And I get the feeling that you'll never need me again\n"
    + "3 - I'm falling again, I'm falling again, I'm falling\n"
    + "4 - And it kills me 'cause I know we've run out of things we can say\n\u001b[0m";
    
    heartbreakAnswerChoices [2] = "\u001b[33m1 - I guess I kinda liked the way you numbed all the pain\n"
    + "2 - Now, I need somebody to know\n"
    + "3 - And then you pulled the rug\n"
    + "4 - I was getting kinda used to being someone you loved\n\u001b[0m";
    
    heartbreakAnswerChoices [3] = "\u001b[33m1 - You know I'm the one who put you up there\n"
    + "2 - I was afraid to leave you on your own\n"
    + "3 - Does it ever get lonely?\n"
    + "4 - Name in the sky\n\u001b[0m";
    
    heartbreakAnswerChoices [4] = "\u001b[33m1 - Can you light the fire? \n"
    + "2 - I know exactly what I need to do\n"
    + "3 - I'm still thinking 'bout the things you do\n"
    + "4 - Look what you made me do, I'm with somebody new\n\u001b[0m";
    
    heartbreakAnswerChoices [5] = "\u001b[33m1 - All I ever wanted was the truth\n"
    + "2 - But everybody wants you\n"
    + "3 - How many nights have you wished someone would stay?\n"
    + "4 - I feel myself runnin' out of time\n\u001b[0m";
    
    //Empowerment lyric answers
    String [] empowermentAnswerChoices = new String [6];
    
    empowermentAnswerChoices [0] = "\u001b[33m1 - Now, I'm so amazing\n"
    + "2 - Say I've loved and I've lost\n"
    + "3 - And one taught me pain\n"
    + "4 - Thank you, next\n\u001b[0m";
    
    empowermentAnswerChoices [1] = "\u001b[33m1 - No scars to your beautiful\n"
    + "2 - The world could change its heart\n"
    + "3 - We're stars and we're beautiful\n"
    + "4 - 'Cause covergirls don't cry\n\u001b[0m";
    
    empowermentAnswerChoices [2] = "\u001b[33m1 - Go on dust your shoulders off, keep it moving\n"
    + "2 - Baby how you feelin'?\n"
    + "3 - Feeling good as hell\n"
    + "4 - If you need advice, let me simplify\n\u001b[0m";
    
    empowermentAnswerChoices [3] = "\u001b[33m1 - Don't say it, 'cause I know I'm cute \n"
    + "2 - It ain't my fault that I'm out here making news\n"
    + "3 - Gotta blame it on my juice\n"
    + "4 - I'm like Chardonnay, get better over time\n\u001b[0m";
    
    empowermentAnswerChoices [4] = "\u001b[33m1 - Saying it's gonna be alright\n"
    + "2 - I shake it off, I shake it off \n"
    + "3 - I'm dancing on my own \n"
    + "4 - 'Cause the players gonna play, play, play, play, play\n\u001b[0m";
    
    empowermentAnswerChoices [5] = "\u001b[33m1 - Look at her now, watch her go\n"
    + "2 - Not saying she was perfect\n"
    + "3 - Took a few years to soak up the tears\n"
    + "4 - It was her first real lover\n\u001b[0m";
    
    if(categoryChoice == 1) {
      choice = loveAnswerChoices [randomSong];
    }
    else if(categoryChoice == 2) {
      choice = heartbreakAnswerChoices [randomSong];
    }
    else if(categoryChoice == 3) {
      choice = empowermentAnswerChoices [randomSong];
    }
    
    return choice;
  }



  /* method lyricAnswers() stores the correct answer to each finish the lyric  
  * pre: categoryChoice is an integer between 1-3 and randomSong is an integer between 0-5
  * post: returns the correct answer choice
  */

  public static int lyricAnswers(int categoryChoice, int randomSong) {
    
    int lyricAnswer = 0; 
    
    //Love lyric answers
    int [] loveLyricAnswers = new int [6];
    loveLyricAnswers [0] = 4;
    loveLyricAnswers [1] = 2;
    loveLyricAnswers [2] = 3;
    loveLyricAnswers [3] = 1;
    loveLyricAnswers [4] = 1;
    loveLyricAnswers [5] = 3;
    
    //Heartbreak lyric answers
    int [] heartbreakLyricAnswers = new int [6];
    heartbreakLyricAnswers [0] = 2;
    heartbreakLyricAnswers [1] = 4;
    heartbreakLyricAnswers [2] = 3;
    heartbreakLyricAnswers [3] = 1;
    heartbreakLyricAnswers [4] = 3;
    heartbreakLyricAnswers [5] = 1;
    
    //Empowerment lyric answers
    int [] empowermentLyricAnswers = new int [6];
    empowermentLyricAnswers [0] = 3;
    empowermentLyricAnswers [1] = 2;
    empowermentLyricAnswers [2] = 4;
    empowermentLyricAnswers [3] = 4;
    empowermentLyricAnswers [4] = 1;
    empowermentLyricAnswers [5] = 3;
    
    if(categoryChoice == 1) {
      lyricAnswer = loveLyricAnswers [randomSong];
    }
    else if(categoryChoice == 2) {
      lyricAnswer = heartbreakLyricAnswers [randomSong];
    }
    else if(categoryChoice == 3) {
      lyricAnswer = empowermentLyricAnswers [randomSong];
    }
    
    return lyricAnswer;
  }

}

	


