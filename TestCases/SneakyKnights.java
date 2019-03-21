//Michael Groff COP 3503C

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.lang.*;

public class SneakyKnights
{
  public static ArrayList<Double> ParseString(String knight, int boardSize)
  {
    ArrayList<Double> KeyLocations = new ArrayList<Double>();
    int j=0;
    int k=0;
    int l=-10;
    double rows=0;
    double columns=0;
    Double rowtemp, coltemp;
    int i=knight.length()-1;
    //starts the while loop at the end of the string in order to count up to avoid looping
    //through twice just to get the length so we know which character is in what place
    while (i >= 0)
    {
      char point = knight.charAt(i);
      int numval = (int)point;
      //System.out.println(numval);
      if(numval < 58) //ascii for 9 is 57 all alphabet chars are higher than this
        rows += (numval-48)*Math.pow(10,j++);//converts to a integer denoting the row position
      else
        columns += (numval-96)*Math.pow(26,k++);//copnverts to an integer denoting the column postion
      i--;
    }

    rowtemp = rows;
    coltemp = columns*Math.pow(10,l);
    //representing each key as the row everything in front of the decimal point
    //and the columns as everything after
    //to do this need to use a double to represent the key
    //System.out.println(rowtemp+coltemp);
    KeyLocations.add(rowtemp+coltemp);
    rowtemp = rows-2;
    coltemp = (columns-1)*Math.pow(10,l);
    KeyLocations.add(rowtemp+coltemp);
    coltemp = (columns+1)*Math.pow(10,l);
    KeyLocations.add(rowtemp+coltemp);
    rowtemp = rows+2;
    KeyLocations.add(rowtemp+coltemp);
    coltemp = (columns-1)*Math.pow(10,l);
    KeyLocations.add(rowtemp+coltemp);
    rowtemp = rows-1;
    coltemp = (columns-2)*Math.pow(10,l);
    KeyLocations.add(rowtemp+coltemp);
    coltemp = (columns+2)*Math.pow(10,l);
    KeyLocations.add(rowtemp+coltemp);
    rowtemp = rows+1;
    KeyLocations.add(rowtemp+coltemp);
    coltemp = (columns-2)*Math.pow(10,l);
    KeyLocations.add(rowtemp+coltemp);

    return KeyLocations;
  }
  public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
  {
    HashMap<Double, Integer> UsedKnights = new HashMap<Double, Integer>();
    //hash map where keys denote the postions that already exist in the board
    // Using integers seems does put a bound on how big the boardsize can be which is 715827882
    ArrayList<Double> KeyLocations = new ArrayList<Double>();

    //makes an arraylist to hold the keys parsestring is going to return,
    // made more sense for the function to return all 9 at the same time instead of breaking it up into cases

    for(String knights : coordinateStrings)
    {
      KeyLocations = ParseString(knights, boardSize);

      for(Double j : KeyLocations)
      {
        if(UsedKnights.get(j) != null)//sees if another knight can attack for each of the 9 keys
          return false;
        else
          UsedKnights.put(KeyLocations.get(0), 1);// if not places these keys in the map to check if other knights can attack
      }
    }
    return true;//if no knights were able to attack each other the function returns true
  }

  public static double difficultyRating()
  {
  return 1.5;//
  }

  public static double hoursSpent()
  {
  return 2.5;//
  }
}
