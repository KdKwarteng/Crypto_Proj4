import java.util.*;
// Cryptography Project 4, Part 1.
// By Kd Kwarteng, Ximena Coloma, Alyza Keo, and Mohammed Alsabty

public class Main {
    //S-box
    int[] input_num = {0, 1, 2, 3, 4, 5, 6, 7};
    static ArrayList<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
    int[] output_num = {6, 5, 1, 0, 3, 2, 7, 4};
    static ArrayList<Integer> output = new ArrayList<>(Arrays.asList(6, 5, 1, 0, 3, 2, 7, 4));
    static ArrayList<Integer>  DD_output = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0 ,0 ,0 ,0));


    public static void main(String[] args) {

        //from 0-15 Hex
        System.out.println("\nDifference Distribution Table: Nd(a', b') for S-box given in project 3 \n");
        System.out.print(String.format("%40s'\n\ta'%2s", "b", "|"));
        for(int t = 0; t < 8; t++){
            System.out.print(String.format("  %2d   |", t));
        }
        System.out.print("\n");
        for(int i = 0; i <= 7; i++){
            //i, represents the input difference because we need to run through every possible value
            //Nd(x', y')
            DD_output = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0 ,0 ,0 ,0));
            System.out.println(String.format("\t----------------------------------------------------------------------"));
            System.out.print(String.format("\t%1d %2s", i, "|"));
            for(int j = 0; j <= 7; j++){
                outputDD(input, output, i, j);
            }
            for(Integer num : DD_output){
                System.out.print(String.format("%4d%4s", num, "|"));
            }
            System.out.println("");
        }
    }

    /**
     * performs exclusive or operation on two binary strings
     * @param x param 1
     * @param x_prime   param 2
     * @return  return param1 XOR param2 in decimal form
     */
    public static int find_bits(int x, int x_prime){

        String xToBin =  String.format("%" + 3 + "s",
                Integer.toBinaryString(x)).replaceAll(" ", "0");
        String x_primeToBin = String.format("%" + 3 + "s",
                Integer.toBinaryString(x_prime)).replaceAll(" ", "0");

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < xToBin.length(); i++) {
            sb.append(xToBin.charAt(i)^x_primeToBin.charAt(i));
        }
        String bin = sb.toString();
        int ret = Integer.parseInt(bin, 2);
        return ret;
    }

    /**
     * formulates the output difference distribution
     * check to see how many y' outputs equal j_prime
     * @param sBoxInput Input parameter to the S-Box
     * @param sBoxOutput Output parameter to the S-Box
     * @param inputDifference  Represents X'
     * @param j_prime  The output difference
     */
    public static void outputDD(ArrayList<Integer> sBoxInput, ArrayList<Integer> sBoxOutput, int inputDifference, int j_prime){
        //first get the outputDD information
        int x = input.get(j_prime); //x
        int x_star = find_bits(x, inputDifference);
        int y = output.get(j_prime); //y
        int y_star =  output.get(input.indexOf(x_star)); //y* = sub(x*)
        int y_prime = find_bits(y, y_star);
        DD_output.set(y_prime, DD_output.get(y_prime) + 1);
    }
}