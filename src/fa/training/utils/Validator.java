package fa.training.utils;

public class Validator {
    public static boolean isID(String id){
        boolean check=true;
        int n=id.length();
        if(n!=7) check=false;
        else{
            String s=id.substring(2,7);
            try{
                int a=Integer.parseInt(s);
                check=true;
            }catch (NumberFormatException e){
                check=false;
                throw new NumberFormatException("ID is invalid");
            }
        }
        return check;
    }

    public static double parseDouble(String numberText){
        double result=0;
        try {
            result = Double.parseDouble(numberText);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException();
        }
        return result;
    }

    public static int parseInt(String numberText){
        int result=0;
        try{
            result=Integer.parseInt(numberText);
        }catch (NumberFormatException e){
            throw new NumberFormatException();
        }
        return result;
    }
}
