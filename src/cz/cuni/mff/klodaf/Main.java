package cz.cuni.mff.klodaf;

public class Main {
    public static void main(String[] args){
        if (args.length != 1){
            return;
        }
        MyProcessor proc = new MyProcessor(args[0]);
        proc.runStatistics();
    }
}
