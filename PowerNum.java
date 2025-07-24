/**
 *
 * Author: Dylan Bagwell
 * Date: 28/06/2025
 * Version: 1.0.0
 * Description: This class stores an integer and provides methods to set and get its value.
 */
public class PowerNum {

    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class PowerNum
     */
    public PowerNum(int Number)
    {
        // initialise instance variables
        this.x = Number;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setNumber(int number)
    {
        
        this.x = number;
    }
    
    public int getNumber(){
        return x;
    }
}
