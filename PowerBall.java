/**
 *
 * Author: Dylan Bagwell
 * Date: 28/06/2025
 * Version: 1.0.0
 * Description: This class represents a PowerBall number with getter and setter methods.
 */
public class PowerBall {

    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class PowerBall
     */
    public PowerBall(int Number)
    {
        this.x = Number;
    }

    public void setNumber(int Number)
    {
        // put your code here
        this.x = Number;
    }
    
    public int getNumber(){
        return x;
    }
}
