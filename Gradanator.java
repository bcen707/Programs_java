

import java.util.*;


//Billy Cen

// This program asks the client for their midterm, final, and homework scores. Also asks
// for the weight of the work type and can take into account a shift amount for the
// midterm and final scores when calculating them. Based on the sum of the client's weighted 
// midterm, final, and homework scores, prints out an appropriate GPA and message.

public class Gradanator {
 public static final int SECTION_MAX = 20; 
 // Possible max section score
   
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      introText();
      double midtermScore = calcMidtermScore(console);
      double finalScore = calcFinalScore(console);
      double homeworkScore = calcHomeworkScore(console);
      getGrade(midtermScore, finalScore, homeworkScore);
   }

// Displays to the userwhat Gradanator does. 
   public static void introText() {
     System.out.println("This program reads exam/homeworkscores"); 
     System.out.println("and reports your overall course grade."); 
   }

// Returns the client's weighted midterm score as a number rounded to the nearest tenth.
 public static double calcMidtermScore(Scanner console) {
  System.out.println();
  System.out.println("Midterm:");
  double midScore = getScores(console);
  return midScore; 
 }

// Returns the client's weighted final score as a number rounded to the nearest tenth.
 public static double calcFinalScore(Scanner console) {
  System.out.println();
  System.out.println("Final:");
  double finScore = getScores(console);
  return finScore;
 }

// General method for calculating the weighted scores of the midterm
// and final, using a weight value provided by the user. Stores and returns it as
// a double to the nearest tenth.
   public static double getScores(Scanner console) {
     System.out.print("Weight (0-100)?");
     double weightedScore = 0;
     int weight = console.nextInt();
     System.out.print("Score earned? ");
     int score = console.nextInt();
     System.out.print("Were scores shifted (1=yes, 2=no)? ");
     if(!console.nextInt() == 1) {
         System.out.print("Shift amount? ");
         int shift = console.nextInt();
         score = score + shift;
      }
      if (score > 100) {
         score = 100;
      }

 System.out.println("Total points = " + score + "/ 100");
weightedScore = Math.round(score / 100.0 * weight * 10) / 10.0;
 System.out.println("Weighted score = " +
weightedScore + " / " + weight);
      return weightedScore;
   }
   
// Calculates and return's the client's weighted homework score as a
double 
// using a weight value provided by the user.
   public static double calcHomeworkScore(Scanner console) {
     System.out.println();
 
     double weightedHomework = 0;
     System.out.println("Homework:");
     System.out.print("Weight (0-100)? ");

      int workWeight = console.nextInt();
      System.out.print("Number of assignments? ");
      int assignments = console.nextInt();
      int totalScore = 0;
      int totalMax = 0;

      for(int i = 1; i <= assignments; i++) {
          System.out.print("Assignment " + i + " score and max?");
          int assignScore = console.nextInt();
          int maxScore = console.nextInt();
          totalScore += assignScore;
          totalMax += maxScore;
      }

 System.out.print("How many sections did you attend? ");
      int sectionScore = 3 * console.nextInt();
      if(sectionScore > SECTION_MAX){
         sectionScore = SECTION_MAX;
      }

      totalScore += sectionScore;
      totalMax += SECTION_MAX;
      if(totalScore > totalMax) {
         totalScore = totalMax;
      }
      
 System.out.println("Section points = " + sectionScore + " / " + SECTION_MAX);
 System.out.println("Total points = " + totalScore + " / " + totalMax);;
 weightedHomework = (totalScore / (double) totalMax) * workWeight;
 System.out.printf("Weighted score = %.1f ", weightedHomework);
      System.out.print("/ " + workWeight);
      System.out.println();
      return weightedHomework;
   }
   
// Reports the client's overall course grade using the weighted midterm, final, and homeework 
// scores. Then prints out an appropriate GPA and message for the calculated course grade.
 public static void getGrade(double midtermScore, double finalScore,
double homeworkScore) {
 System.out.println();
      double overallGrade = midtermScore + finalScore + homeworkScore;
 System.out.printf("Overall percentage = %.1f\n", overallGrade);
      System.out.print("Your grade will be at least: ");
      if(overallGrade >= 85) { 
 System.out.println("3.0");
 System.out.print("Great job, keep up the good work!");
      } else if(overallGrade >= 75) {
         System.out.println("2.0");
 System.out.print("Not bad, but you can do better!");
      } else if(overallGrade >= 60) {
         System.out.println("0.7");
         System.out.print("Are you slacking? Try harder!");
      } else { // overallGrade < 60
         System.out.println("0.0");
         System.out.print("EPIC FAIL. D:");
      }
   }
}