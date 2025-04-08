import java.io.*;
import java.util.*;

public class MipstoJava {

    static HashMap<String,String> str = new HashMap<>();  // for declare and print address also save register's value
    static ArrayList <String> string= new ArrayList<>();  // strings where in files comes here
    static String[] a;

    static String sum = "";

    static int cnt2 = 4194304; //400000(hex) = 4194304 (decimal) for start number of "Address"


   public static void main(String[] args) throws IOException {
       System.out.println(" Address:         Code:");
       System.out.println("-------------------------");

       registerput(); // register's value stored in hashmap

       readFile(); // for reading files

       labeladdressfix();  // strore for example "x1:" in file turns to x1 and stores.

       printInstructions(); // printing code numbers

        }

        public static void printInstructions(){

              // declaring code numbers in this section

            for (int i = 0; i< string.size();i++){       // every instruction and label names in here "string" array

                if (string.get(i).equals("add")) {

                    System.out.print("0x00"+str.get("add")+"      ");

                    sum = "";
                    sum = sum + "000000";
                    sum = sum + str.get(string.get(i+2));
                    sum = sum + str.get(string.get(i+3));
                    sum = sum + str.get(string.get(i+1));
                    sum = sum + "00000";
                    sum = sum + "100000";

                    tohex(sum);
                }

                else if  (string.get(i).equals("addi")) {
                    System.out.print("0x00"+str.get("addi")+"      ");
                    sum = "";
                    sum = sum + "001000";
                    sum = sum + str.get(string.get(i+2));
                    sum = sum + str.get(string.get(i+1));


                    if (Integer.parseInt(string.get(i+3))<0){
                        for (int a =0;16-twosCompliment(Integer.toBinaryString(Math.abs(Integer.parseInt(string.get(i+3))))).length()>a;a++){
                            sum = sum + "1";
                        }
                        sum = sum +twosCompliment(Integer.toBinaryString(Math.abs(Integer.parseInt(string.get(i+3)))));
                    }
                    else { for (int a =0;16 - Integer.toBinaryString(Integer.parseInt(string.get(i+3))).length()>a;a++){
                        sum = sum + "0";
                    }
                        sum =sum + Integer.toBinaryString(Integer.parseInt(string.get(i+3)));}

                    tohex(sum);

                }

                else if  (string.get(i).equals("sub")) {
                    System.out.print("0x00"+str.get("sub")+"      ");
                    sum = "";
                    sum = sum + "000000";
                    sum = sum + str.get(string.get(i+2));
                    sum = sum + str.get(string.get(i+3));
                    sum = sum + str.get(string.get(i+1));
                    sum = sum + "00000";
                    sum = sum + "100010";

                    tohex(sum);

                }

                else if  (string.get(i).equals("and")) {
                    System.out.print("0x00"+str.get("and")+"      ");
                    sum = "";
                    sum = sum + "000000";
                    sum = sum + str.get(string.get(i+2));
                    sum = sum + str.get(string.get(i+3));
                    sum = sum + str.get(string.get(i+1));
                    sum = sum + "00000";
                    sum = sum + "100100";

                    tohex(sum);

                }

                else if  (string.get(i).equals("andi")) {
                    System.out.print("0x00"+str.get("andi")+"      ");
                    sum = "";
                    sum = sum + "001100";
                    sum = sum + str.get(string.get(i+2));
                    sum = sum + str.get(string.get(i+1));
                    String ab = "" +Integer.toBinaryString(Integer.parseInt(string.get(i+3)));
                    while (ab.length()<16){
                        string.set(i+3,0+string.get(i+3));
                        ab = 0+ ab;
                    }
                    sum = sum + ab;

                    tohex(sum);

                }
                else if  (string.get(i).equals("or")) {
                    System.out.print("0x00"+str.get("or")+"      ");
                    sum = "";
                    sum = sum + "000000";
                    sum = sum + str.get(string.get(i+2));
                    sum = sum + str.get(string.get(i+3));
                    sum = sum + str.get(string.get(i+1));
                    sum = sum + "00000";
                    sum = sum + "100101";

                    tohex(sum);

                }
                else if  (string.get(i).equals("sllv")) {
                    System.out.print("0x00"+str.get("sllv")+"      ");
                    sum = "";
                    sum = sum + "000000";
                    sum = sum + str.get(string.get(i+3));
                    sum = sum + str.get(string.get(i+2));
                    sum = sum + str.get(string.get(i+1));
                    sum = sum + "00000";
                    sum = sum + "000100";

                    tohex(sum);

                }

                else if  (string.get(i).equals("srlv")) {
                    System.out.print("0x00"+str.get("srlv")+"      ");
                    sum = "";
                    sum = sum + "000000";
                    sum = sum + str.get(string.get(i+3));
                    sum = sum + str.get(string.get(i+2));
                    sum = sum + str.get(string.get(i+1));
                    sum = sum + "00000";
                    sum = sum + "000110";

                    tohex(sum);

                }

                else if  (string.get(i).equals("sll")) {
                    System.out.print("0x00"+str.get("sll")+"      ");
                    sum = "";
                    sum = sum + "000000";
                    sum = sum + "00000";
                    sum = sum + str.get(string.get(i+2));
                    sum = sum + str.get(string.get(i+1));
                    String ab = "" +Integer.toBinaryString(Integer.parseInt(string.get(i+3)));
                    while (ab.length()<5){
                        string.set(i+3,0+string.get(i+3));
                        ab = 0+ ab;
                    }
                    sum = sum + ab;
                    sum = sum + "000000";


                    tohex(sum);

                }

                else if  (string.get(i).equals("srl")) {
                    System.out.print("0x00"+str.get("srl")+"      ");
                    sum = "";
                    sum = sum + "000000";
                    sum = sum + "00000";
                    sum = sum + str.get(string.get(i+2));
                    sum = sum + str.get(string.get(i+1));
                    String ab = "" +Integer.toBinaryString(Integer.parseInt(string.get(i+3)));
                    while (ab.length()<5){
                        string.set(i+3,0+string.get(i+3));
                        ab = 0+ ab;
                    }
                    sum = sum + ab;
                    sum = sum + "000010";


                    tohex(sum);

                }

                else if (string.get(i).equals("j")) {
                    System.out.print("0x00"+str.get("j")+"      ");
                    sum = "";
                    sum = sum + "000010";
                    sum=sum+"000000000";
                    int decimal = Integer.parseInt(str.get(string.get(i+1)),16);
                    sum = sum + Integer.toBinaryString(decimal);
                    sum = sum.substring(0,6)+sum.substring(10,36);

                    tohex(sum);
                }

                else if  (string.get(i).equals("bne")) {
                    System.out.print("0x00"+str.get("bne")+"      ");
                    sum = "";
                    sum = sum + "000101";
                    sum = sum + str.get(string.get(i+1));
                    sum = sum + str.get(string.get(i+2));


                    int pc4 = Integer.parseInt(str.get(string.get(i)),16)+4;
                    int adrss = Integer.parseInt(str.get(string.get(i+3)),16);
                    int imm = (adrss-pc4)/4;
                    if (imm<0){

                        String sum2 = sum;
                        sum2= sum2 + twosCompliment(Integer.toBinaryString(Math.abs(imm)));
                        for (int a =0;a<32-sum2.length();a++){
                            sum = sum+"1";
                        }
                        sum= sum+twosCompliment(Integer.toBinaryString(Math.abs(imm)));
                    }

                    else {String sum2 = sum;sum2 = sum2 +Integer.toBinaryString(imm);
                        for (int a =0;a<32-sum2.length();a++){
                            sum = sum+"0";
                        }
                        sum = sum + Integer.toBinaryString(imm);}

                    tohex(sum);

                }

                else if  (string.get(i).equals("beq")) {
                    System.out.print("0x00"+str.get("beq")+"      ");
                    sum = "";
                    sum = sum + "000100";
                    sum = sum + str.get(string.get(i+1));
                    sum = sum + str.get(string.get(i+2));


                    int pc4 = Integer.parseInt(str.get(string.get(i)),16)+4;
                    int adrss = Integer.parseInt(str.get(string.get(i+3)),16);
                    int imm = (adrss-pc4)/4;
                    if (imm<0){

                        String sum2 = sum;
                        sum2= sum2 +twosCompliment(Integer.toBinaryString(Math.abs(imm)));
                        for (int a =0;a<32-sum2.length();a++){
                            sum = sum+"1";
                        }
                        sum= sum+twosCompliment(Integer.toBinaryString(Math.abs(imm)));
                    }
                    // zero extension
                    else {String sum2 = sum;sum2 = sum2 +Integer.toBinaryString(imm);
                        for (int a =0;a<32-sum2.length();a++){
                            sum = sum+"0";
                        }
                        sum = sum + Integer.toBinaryString(imm);}

                    tohex(sum);

                }

                else if  (string.get(i).equals("blez")) {
                    System.out.print("0x00"+str.get("blez")+"      ");
                    sum = "";
                    sum = sum + "000110";
                    sum = sum + str.get(string.get(i+1));
                    sum = sum + "00000";


                    int pc4 = Integer.parseInt(str.get(string.get(i)),16)+4;
                    int adrss = Integer.parseInt(str.get(string.get(i+2)),16);
                    int imm = (adrss-pc4)/4;
                    if (imm<0){
                        // zero extension
                        String sum2 = sum;
                        sum2= sum2 +twosCompliment(Integer.toBinaryString(Math.abs(imm)));
                        for (int a =0;a<32-sum2.length();a++){
                            sum = sum+"1";
                        }
                        sum= sum+twosCompliment(Integer.toBinaryString(Math.abs(imm)));
                    }
                    // zero extension
                    else {String sum2 = sum;sum2 = sum2 +Integer.toBinaryString(imm);
                        for (int a =0;a<32-sum2.length();a++){
                            sum = sum+"0";
                        }
                        sum = sum + Integer.toBinaryString(imm);}

                    tohex(sum);

                }

                else if  (string.get(i).equals("bgtz")) {
                    System.out.print("0x00"+str.get("bgtz")+"      ");
                    sum = "";
                    sum = sum + "000111";
                    sum = sum + str.get(string.get(i+1));
                    sum = sum + "00000";


                    int pc4 = Integer.parseInt(str.get(string.get(i)),16)+4;
                    int adrss = Integer.parseInt(str.get(string.get(i+2)),16);
                    int imm = (adrss-pc4)/4;

                    // zero extension
                    if (imm<0){

                        String sum2 = sum;
                        sum2= sum2 +twosCompliment(Integer.toBinaryString(Math.abs(imm)));
                        for (int a =0;a<32-sum2.length();a++){
                            sum = sum+"1";
                        }
                        sum= sum+twosCompliment(Integer.toBinaryString(Math.abs(imm)));
                    }

                    // zero extension
                    else {String sum2 = sum;sum2 = sum2 +Integer.toBinaryString(imm);
                        for (int a =0;a<32-sum2.length();a++){
                            sum = sum+"0";
                        }
                        sum = sum + Integer.toBinaryString(imm);}

                    tohex(sum);

                }

                else if  (string.get(i).equals("lw")) {
                    System.out.print("0x00"+str.get("lw")+"      ");


                    // for split imm number and $ number for example siplit 5($8)
                    // Find the position of the parentheses
                    int leftParenIndex = string.get(i+2).indexOf('(');
                    int rightParenIndex = string.get(i+2).indexOf(')');
                    String part1="";
                    String part2="";
                    // Extract the parts of the string based on the positions of the parentheses
                    if (leftParenIndex != -1 && rightParenIndex != -1) {
                        part1 = string.get(i+2).substring(0, leftParenIndex); // "5"
                        part2 = string.get(i+2).substring(leftParenIndex + 1, rightParenIndex);} // "$8"

                    sum = "";
                    sum = sum + "100011";
                    sum = sum + str.get(part2);
                    sum = sum + str.get(string.get(i+1));

                    // zero extension
                    for (int a =0;16 - Integer.toBinaryString(Integer.parseInt(part1)).length()>a;a++){
                        sum = sum + "0";
                    }
                    sum =sum + Integer.toBinaryString(Integer.parseInt(part1));


                    tohex(sum);

                }

                else if  (string.get(i).equals("sw")) {
                    System.out.print("0x00"+str.get("sw")+"      ");

                    // Find the position of the parentheses
                    int leftParenIndex = string.get(i+2).indexOf('(');
                    int rightParenIndex = string.get(i+2).indexOf(')');
                    String part1="";
                    String part2="";
                    // Extract the parts of the string based on the positions of the parentheses
                    if (leftParenIndex != -1 && rightParenIndex != -1) {
                        part1 = string.get(i+2).substring(0, leftParenIndex); // "5"
                        part2 = string.get(i+2).substring(leftParenIndex + 1, rightParenIndex);} // "$8"

                    sum = "";
                    sum = sum + "101011";
                    sum = sum + str.get(part2);
                    sum = sum + str.get(string.get(i+1));


                    // zero extension
                    for (int a =0;16 - Integer.toBinaryString(Integer.parseInt(part1)).length()>a;a++){
                        sum = sum + "0";
                    }
                    sum =sum + Integer.toBinaryString(Integer.parseInt(part1));

                    tohex(sum);

                }
            }
        }
        public static void readFile()throws IOException{

               // files is reading in this section

            File obj = new File("mycode.asm");
            Scanner read = new Scanner(obj);

            try {
                while (read.hasNextLine()) {

                    String s = read.next();

                     // for split every word
                    a = s.split(",");

                    addtoArray(a);
                }
            }
            catch (Exception e){}
        }

        public static void registerput(){

       // register numbers stored in binary

            str.put("$0","00000");
            str.put("$1","00001");
            str.put("$2","00010");
            str.put("$3","00011");
            str.put("$4","00100");
            str.put("$5","00101");
            str.put("$6","00110");
            str.put("$7","00111");
            str.put("$8","01000");
            str.put("$9","01001");
            str.put("$10","01010");
            str.put("$11","01011");
            str.put("$12","01100");
            str.put("$13","01101");
            str.put("$14","01110");
            str.put("$15","01111");
            str.put("$16","10000");
            str.put("$17","10001");
            str.put("$18","10010");
            str.put("$19","10011");
            str.put("$20","10100");
            str.put("$21","10101");
            str.put("$22","10110");
            str.put("$23","10111");
            str.put("$24","11000");
            str.put("$25","11001");
            str.put("$26","11010");
            str.put("$27","11011");
            str.put("$28","11100");
            str.put("$29","11101");
            str.put("$30","11110");
            str.put("$31","11111");

        }
    public static String twosCompliment(String binary) {

       // take for 2's complement for negative numbers in this section
        // Inverting the bits
        StringBuilder invertedBinary = new StringBuilder();
        for (char bit : binary.toCharArray()) {
            invertedBinary.append(bit == '0' ? '1' : '0');
        }

        //  Add 1 to the inverted binary string
        StringBuilder twosComplement = new StringBuilder(invertedBinary);
        int carry = 1;
        for (int i = invertedBinary.length() - 1; i >= 0; i--) {
            if (invertedBinary.charAt(i) == '1' && carry == 1) {
                twosComplement.setCharAt(i, '0');
            } else if (invertedBinary.charAt(i) == '0' && carry == 1) {
                twosComplement.setCharAt(i, '1');
                carry = 0;
            } else {
                twosComplement.setCharAt(i, invertedBinary.charAt(i));
            }
        }
        // If there's still a carry left, prepend '1'
        if (carry == 1) {
            twosComplement.insert(0, '1');
        }
        return twosComplement.toString();
    }

    // Returns '0' for '1' and '1' for '0'
    public static char flip(char c) {
        return (c == '0') ? '1' : '0';
    }

    static void tobinary(int n)
    {
        // array to store binary number
        int[] binaryNum = new int[1000];

        // counter for binary array
        int i = 0;
        while (n > 0)
        {
            // storing remainder in binary array
            binaryNum[i] = n % 2;
            n = n / 2;
            i++;
        }
        // printing binary array in reverse order
        for (int j = i - 1; j >= 0; j--)
            sum =sum + (binaryNum[j]);
    }
        public static void tohex(String a ){

               // converting binary to hex number in this section

            System.out.print("0x");
            int digitNumber = 1;
            int sum2 = 0;
            String binary_ = a;
            for(int i = 0; i < binary_.length(); i++){
                if(digitNumber == 1)
                    sum2+=Integer.parseInt(binary_.charAt(i) + "")*8;
                else if(digitNumber == 2)
                    sum2+=Integer.parseInt(binary_.charAt(i) + "")*4;
                else if(digitNumber == 3)
                    sum2+=Integer.parseInt(binary_.charAt(i) + "")*2;
                else if(digitNumber == 4 || i < binary_.length()+1){
                    sum2+=Integer.parseInt(binary_.charAt(i) + "")*1;
                    digitNumber = 0;
                    if(sum2 < 10)
                        System.out.print(sum2);
                    else if(sum2 == 10)
                        System.out.print("A");
                    else if(sum2 == 11)
                        System.out.print("B");
                    else if(sum2 == 12)
                        System.out.print("C");
                    else if(sum2 == 13)
                        System.out.print("D");
                    else if(sum2 == 14)
                        System.out.print("E");
                    else if(sum2== 15)
                        System.out.print("F");
                    sum2=0;
                }
                digitNumber++;
            }

            System.out.println();
        }

        public static void  labeladdressfix(){

           // for example in file x1: string turns to x1 for clarify declare address

             for (int i =1;string.size()>i;i++){

                     if(string.get(i-1).contains(":")){

                         String a = string.get(i-1).replace(":","");

                         str.put(a,str.get(string.get(i)));

                     }
             }
        }
        public static void addtoArray(String[] a){

       // which read file in this strings stored in wtih addresses. for example "and" instruction declared with 400000 in other part it will added other part (for example:0x00) in result it will 0x00400000

               for (int i = 0; i < a.length; i++) {

                   String cnt = Integer.toHexString(cnt2);
                   if (a[i].equals("add")||a[i].equals("j")||a[i].equals("lw")||a[i].equals("sw")||a[i].equals("bne")||a[i].equals("blez")||a[i].equals("bgtz")||a[i].equals("beq")||a[i].equals("sub")||a[i].equals("and")||a[i].equals("or")||a[i].equals("sll")||a[i].equals("srl")||a[i].equals("sllv")||a[i].equals("srlv")||a[i].equals("addi")||a[i].equals("andi")){

                       str.put(a[i],cnt);
                       cnt2 +=4;

                   }

                   string.add(a[i]);

               }

   }

}



