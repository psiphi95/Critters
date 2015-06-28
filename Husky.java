/*
Daniel Hsu
CSE 142 AI
TA = Rebecca Yuen
5-30-15
HW 8

Creates a mean green Husky machine that moves like a combination between a hippo & a bird
but is faster & more fearsome. Each time it changes color, it becomes vulnerable to attack.
With each successful hunt, its identification number progresses higher & higher.
The number of kills also allows each Husky to fulfill different roles w/some focusing on
consuming food by transforming into a hippo & others built to fight directly.
*/
import java.util.*;
import java.awt.*;

public class Husky extends Critter {
   
   public int mate;
   public int sleep;
   private int kill;
   private int step;
   private int compass;
   private int size;
   private boolean sleeping = false;
   private boolean mating = false;
   private Random r = new Random();
   private static final Color DEEPGREEN = new Color(40, 180, 0);
   private static final int map = 4;
   
   public Husky() {
      step = 0;
      compass = r.nextInt(map);
      size = r.nextInt(map + 1) + 1;
   }
   
   public Direction getMove() {
      step++;
      Direction path = Direction.NORTH;
      if (compass == 0) {
         if (step <= size * 2) {
            path = Direction.NORTH;
         } else if (step >= size * 2 && step <= size * 3) {
            path = Direction.EAST;
         } else if (step >= size * 3 && step <= size * 4) {
            path = Direction.SOUTH;
         } else if (step >= size * 4 && step <= size * 6) {
            path = Direction.WEST;
         }
      } else if (compass == 1) {
         if (step <= size * 2) {
            path = Direction.SOUTH;
         } else if (step >= size * 2 && step <= size * 3) {
            path = Direction.WEST;
         } else if (step >= size * 3 && step <= size * 4) {
            path = Direction.NORTH;
         } else if (step >= size * 4 && step <= size * 6) {
            path = Direction.EAST;
         }
      } else if (compass == 2) {
         if (step <= size * 2) {
            path = Direction.EAST;
         } else if (step >= size * 2 && step <= size * 3) {
            path = Direction.SOUTH;
         } else if (step >= size * 3 && step <= size * 4) {
            path = Direction.WEST;
         } else if (step >= size * 4 && step <= size * 6) {
            path = Direction.NORTH;
         }
      } else {
         if (step <= size * 2) {
            path = Direction.WEST;
         } else if (step >= size * 2 && step <= size * 3) {
            path = Direction.NORTH;
         } else if (step >= size * 3 && step <= size * 4) {
            path = Direction.EAST;
         } else if (step >= size * 4 && step <= size * 6) {
            path = Direction.SOUTH;
         }
      }
      if (step < size * 6) {
         return path;
      }
      compass = r.nextInt(map);
      size = r.nextInt(map + 1) + 1;
      step = 0;
      return path;
   }
   
   public boolean eat() {
      if (kill % 2 == 1) {
         return true;
      } else {
         return false;
      }
   }
   
   public Attack fight(String opponent) {
      kill++;
      boolean ant = opponent.equals("%");
      boolean bird = ">V<^".contains(opponent);
      boolean hippoHungry = "123456789".contains(opponent);
      boolean hippoFull = opponent.equals("0");
      boolean stone = opponent.equals("S");
      int fight = r.nextInt(3);
      if (ant) {
         return Attack.ROAR;
      } else if (bird) {
         return Attack.SCRATCH;
      } else if (hippoHungry) {
         return Attack.ROAR;
      } else if (hippoFull) {
         return Attack.SCRATCH;
      } else if (stone)  {
         return Attack.POUNCE;
      } else if (kill % 2 == 1) {
         return Attack.ROAR;
      } else if (fight == 0) {
         return Attack.SCRATCH;
      } else if (fight == 1) {
         return Attack.ROAR;
      } else {
         return Attack.POUNCE;
      }
   }
   
   public Color getColor() {
      if (mating) {
         return Color.MAGENTA;
      } if (sleeping) {
         return Color.CYAN;
      } else {
         return DEEPGREEN;
      }
   }
   
   public String toString() {
      if (sleeping || mating) {
         return "H" + kill;
      } else if (kill % 2 == 1) {
         return "0";
      } else {
         return "D" + kill;
      }
   }
   
   public void sleep() {
      sleeping = true;
   }
   
   public void wakeup() {
      sleeping = false;
   }
   
   public void mate() {
      mating = true;
   }
   
   public void mateEnd() {
      mating = false;
   }
}
