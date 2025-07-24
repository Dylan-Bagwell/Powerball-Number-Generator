/**
 *
 * Author: Dylan Bagwell
 * Date: 28/06/2025
 * Version: 1.0.0
 * Description: This class generates random PowerNum and PowerBall numbers, ensures uniqueness, and 
 * provides methods for retrieving and checking random numbers for duplicates.
 */
import javax.swing.JPanel;
public class Random_Numbers extends JPanel {
    
    private PowerNum [] PN;
    private PowerBall [] Pb;
    private int [] winningNumbers = new int[8];

    //Constructor for generating random numbers
    public int[] randomNumber()
    {
        int min = 1, max = 35, Pmin = 1, Pmax = 20;
        
        this.PN = new PowerNum[7];
        this. Pb = new PowerBall[1];

        // Generate unique PowerNum numbers
        for (int i = 0; i < 7; i++) {
            int Num;
            boolean isDuplicate;
            do {
                Num = (min + (int) (Math.random() * ((max - min) + 1)));
                isDuplicate = false;
                for (int j = 0; j < i; j++) {
                    if (PN[j] != null && PN[j].getNumber() == Num) 
                    {
                        isDuplicate = true;
                        break;
                    }
                }
            } while (isDuplicate);
            PN[i] = new PowerNum(Num);
        }
        // Generate unique PowerBall number
        int PB = (Pmin + (int) (Math.random() * ((Pmax - Pmin) + 1)));
        PowerBall Pball = new PowerBall(PB);
        Pb[0] = Pball;

        // Append the numbers to the result string after checking for duplicates
        for (int i = 0; i < PN.length; i++)
        {
            winningNumbers[i] = PN[i].getNumber();
        }
        winningNumbers[7] = Pb[0].getNumber();

        return winningNumbers;
    }

    public int getNewRandomNumber()
    {
        int min = 1,max = 35;
        int Num = (min + (int)(Math.random() * ((max-min) +1)));
        return Num;
    }

    public PowerNum [] checkDuplicate(PowerNum [] PN) 
    {
        

        for(int i = 0; i < PN.length; i++)
        {
            if (i+1 == 8)
            {
                break;
            }
            else
            {
                for(int j = i + 1; j < PN.length; j++)
                {
                    if(PN[i].getNumber() == PN[j].getNumber())
                    {
                        System.out.println("Duplicate number found: " + PN[i].getNumber());
                        PN[j].setNumber(getNewRandomNumber());
                        checkDuplicate(PN); // Recursively check for duplicates again
                    }
                }
            }
        }
        return PN;
    }
}
