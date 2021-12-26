
// Billy Cen

// This class models a vibrating guitar string of a given frequency. Allows client
// to play any class that uses the default Guitar class.
import java.util.*;
 
public class GuitarString {
   public static final double ENERGY_DECAY_FACTOR = 0.996;
   private Queue<Double> ringBuffer;
   private int countTic;
   
// pre: passed frequency must be > 0 and the capacity of ringBuffer must be >=2. if not,
// throws IllegalArgumentException.
// post: constructs a guitar string of the given frequency
   public GuitarString(double frequency) {
      ringBuffer = new LinkedList<Double>();
      int N = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
      if (frequency <= 0 || N < 2) 
         throw new IllegalArgumentException();
      for (int i = 0; i < N; i++) 
         ringBuffer.add(0.0);
   }

// pre: array must have at least 2 elements. if not, throws IllegalArgumentException.
// post: constructs guitar string & initializes the contents of the ring buffer to
// the values in the array
   public GuitarString(double[] init) {
      ringBuffer = new LinkedList<Double>();
      if (init.length < 2)
         throw new IllegalArgumentException();
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   }

// Replaces the N elements in the buffer with N random values between -0.5 and 0.5
   public void pluck() {
      Random r = new Random();
      int size = ringBuffer.size(); // former size of ringBuffer
      ringBuffer.removeAll(ringBuffer);
      for (int i = 0; i < size; i++) 
         ringBuffer.add(r.nextDouble() - 0.5);     
   }

// Uses Karplus-Strong update. Deletes sample at front of buffer and adds to the 
// end of buffer the average of the first two samples * ENERGY_DECAY_FACTOR (0.996).
// Also keeps track of the amount of time the tic method was called.
   public void tic() {
      countTic++;
      double karplusStrong;
      karplusStrong = ENERGY_DECAY_FACTOR * 0.5 * (ringBuffer.remove() +
      ringBuffer.peek());
      ringBuffer.add(karplusStrong);
   }

// Returns the current sample at the front of the buffer.
   public double sample() {
      return ringBuffer.peek();
   }
   
// Returns the # of times that the tic method was called.   
   public int time() {
      return countTic;
   }
}