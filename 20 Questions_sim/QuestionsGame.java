// Billy Cen


// This class simulates the yes or no guessing game "20 Questions."
// In addition, the computer takes a lost game and prompts the user for a question
// to distinguish that object and adds the question and object it to its question tree.

import java.util.*;
import java.io.*;

public class QuestionsGame {
 private QuestionNode overallRoot; // the "root" of the question tree
    
// pre: takes in a nonempty object(i.e. "computer", "cat", etc.)
//  post: creates a QuestionNode whose data is the passed object.
    public QuestionsGame(String object) {
      overallRoot = use your other constructornew QuestionNode(object, null, null);
    }
    
//  pre: Takes in a scanner that is not null. Initializes a new game 
// by reading in a file containing a tree of questions in standard format. 
    public QuestionsGame(Scanner input) {
        overallRoot = buildTree(input);
    }
    
// Returns QuestionNodes until there is nothing left to read in the file 
//  (one QuestionNode per 2 lines of the file). A tree of questions is 
//  created so the player can use it to play a game of N-Questions. 
    private QuestionNode buildTree (Scanner input) {
       while(input.hasNextLine()) {
         String dataType = input.nextLine();
         String data = input.nextLine();
         QuestionNode root = new QuestionNode(data);
         if(dataType.equals("A:")) {
            return root;
         } else {
            root.left = buildTree(input);
            root.right = buildTree(input);
            return root;
           }   
       }
       return overallRoot;
    }
    
// pre: passed PrintStream cannot be null, otherwise throws IllegalArgumentException.
// Stores the current questions tree to an output file represented by the given
// PrintStream. Can be used to later play another game with the computer using the
//  questions from this one.
    public void saveQuestions(PrintStream output) {
        if(output == null)
            throw new IllegalArgumentException();
        saveQuestions(output, overallRoot);
    }
    
// pre: Takes in a non null PrintStream and a QuestionNode.
// post: Goes through the question tree starting at the root, and prints out the 
// contents of each QuestionNode in pre order to a new file. The first line prints 
// the node's type (Q: or A:) and the second line prints the node's question 
//  or answer.
 private void saveQuestions(PrintStream output, QuestionNode root) {
        if (root.left == null && root.right == null) { 
            output.println("A:");
            output.println(root.data);
        }
        else {
            output.println("Q:");
            output.println(root.data);
            saveQuestions(output, root.left);
            saveQuestions(output, root.right);
        } 
    }
    
// Uses the current tree of questions to play oadd that this changes the treene complete guessing game 
// with the user, asking yes/no questions until reaching an answer object 
//  to guess.
    public void play() {
        overallRoot = play(overallRoot);
    }
    
// Prints out interactions with user; keeps asking y/n questions until
//  the computer comes to an answer. If the user wins, computer
// asks for three things: the player&apos;s object, a question to distinguish
// their object from the computer&apos;s last guess, and an answer (y/n) to
// distinguish both objects. After asking these 3 questions, the computer
// returns a question node and adds it to the current tree of questions.
// If computer wins, game ends and client is prompted if they want to play again. 
    private QuestionNode play(QuestionNode root) {
        Scanner console = new Scanner(System.in);
        if(root.left == null && root.right == null) {
            System.out.println("I guess that your object is " + root.data + "!");
            System.out.print("Am I right? (y/n)? ");
            if(console.nextLine().trim().toLowerCase().startsWith("y"))
               // computer wins
                System.out.println("Awesome! I win!");
            else {// player wins
                System.out.println("Boo! I Lose. Please help me get better!");
                System.out.print("What is your object? ");
                String newObject = console.nextLine(); // player's new object
                System.out.println("Please give me a yes/no question that distinguishes between " 
                                   + newObject + " and " + root.data + ".");
                System.out.print("Q: ");
                String newQuestion = console.nextLine(); // question to distinguish object
                System.out.print("Is the answer \"yes\" for " + newObject + "? (y/n)? ");
                String distinguish = console.nextLine(); // answer to distinguish object (y/n) 
                return addObject(root, newObject, newQuestion, distinguish);
            }
        } else { // play game
            System.out.print(root.data + " (y/n)? "); // print the question
               if(console.nextLine().trim().toLowerCase().startsWith("y"))
                  root.left = play(root.left);
            else
                root.right = play(root.right);
        }
        return root;
    }
    
// pre: Takes in a question node, the client&apos;s new object, a question to distinguish
//  it, and an answer to the question (yes or no) to distinguish it.
// post: Returns a QuestionNode with the client&apos;s question with it's left child as
// the original object and the right child as the new object or vice versa. The 
// order of the left and right children depends on the client&apos;s answer that distinguishes
//  between the two objects.    
 private QuestionNode addObject(QuestionNode root, String newObject, String question, String distinguish) {
   QuestionNode newRoot = new QuestionNode(question); 
   if(distinguish.trim().toLowerCase().startsWith("y")) {
      newRoot.left = new QuestionNode(newObject);
      newRoot.right = root;           
   } else {
      newRoot.left = root;
      newRoot.right = new QuestionNode(newObject);        
   }
      return newRoot;
   }
 }   