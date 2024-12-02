import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

import static java.lang.Double.parseDouble;

public class Proj4 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        ArrayList<NBAPlayer> origList = new ArrayList<>();
        int counter = 0;
        while (inputFileNameScanner.hasNextLine() && counter < numLines) {
            String line = inputFileNameScanner.nextLine();
            String[] values = line.split(",");

            //creating NBAPlayer object out of string input
            try {
                if (values.length == 17){
                    int playerID = Integer.parseInt(values[0].trim());
                    int gp = Integer.parseInt(values[1].trim());
                    int gs = Integer.parseInt(values[2].trim());
                    double mpg = parseDouble(values[3].trim());
                    double ppg = parseDouble(values[4].trim());
                    int fgm = Integer.parseInt(values[5].trim());
                    int fga = Integer.parseInt(values[6].trim());
                    double fgp = parseDouble(values[7].trim());
                    int threesMade = Integer.parseInt(values[8].trim());
                    int threesAttempted = Integer.parseInt(values[9].trim());
                    double threePercentage = parseDouble(values[10].trim());
                    int ftm = Integer.parseInt(values[11].trim());
                    int fta = Integer.parseInt(values[12].trim());
                    double ftp = parseDouble(values[13].trim());
                    String player = values[14].trim();
                    String position = values[15].trim();
                    String team = values[16].trim();

                    NBAPlayer newPlayer = new NBAPlayer(playerID, gp, gs, mpg,ppg,fgm, fga, fgp, threesMade,
                            threesAttempted, threePercentage, ftm, fta, ftp, player, position, team);

                    origList.add(newPlayer);
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
            counter++;
        }

        if (!origList.isEmpty()) {
            FileWriter writer1 = new FileWriter("analysis.txt",true);
            BufferedWriter br1 = new BufferedWriter(writer1);
            SeparateChainingHashTable<NBAPlayer> table = new SeparateChainingHashTable<>();

            // sorted
            Collections.sort(origList);
            double t1 = System.currentTimeMillis();
            for (NBAPlayer player : origList) {
                table.insert(player);
            }
            double t2 = System.currentTimeMillis();
            double sortedInsertTime = t2 - t1;

            double t3 = System.currentTimeMillis();
            for (NBAPlayer player : origList) {
                table.contains(player);
            }
            double t4 = System.currentTimeMillis();
            double sortedSearchTime = t4 - t3;

            double t5 = System.currentTimeMillis();
            for (NBAPlayer player : origList) {
                table.remove(player);
            }
            double t6 = System.currentTimeMillis();
            double sortedRemoveTime = t6 - t5;


            // shuffled
            Collections.shuffle(origList);

            double t7 = System.currentTimeMillis();
            for (NBAPlayer player : origList) {
                table.insert(player);
            }
            double t8 = System.currentTimeMillis();
            double shuffledInsertTime = t8 - t7;

            double t9 = System.currentTimeMillis();
            for (NBAPlayer player : origList) {
                table.contains(player);
            }
            double t10 = System.currentTimeMillis();
            double shuffledSearchTime = t10 - t9;

            double t11 = System.currentTimeMillis();
            for (NBAPlayer player : origList) {
                table.remove(player);
            }
            double t12 = System.currentTimeMillis();
            double shuffledRemoveTime = t12 - t11;

            //reversed
            Collections.sort(origList, Collections.reverseOrder());
            double t13 = System.currentTimeMillis();
            for (NBAPlayer player : origList) {
                table.insert(player);
            }
            double t14 = System.currentTimeMillis();
            double reversedInsertTime = t14 - t13;

            double t15 = System.currentTimeMillis();
            for (NBAPlayer player : origList) {
                table.contains(player);
            }
            double t16 = System.currentTimeMillis();
            double reversedSearchTime = t16 - t14;

            double t17 = System.currentTimeMillis();
            for (NBAPlayer player : origList) {
                table.remove(player);
            }
            double t18 = System.currentTimeMillis();
            double reversedRemoveTime = t18 - t17;

            //outputting to file
            br1.write("sorted, " + "insertion, " + numLines + ", " + sortedInsertTime);
            br1.newLine();
            br1.write("sorted, " + "search, " + numLines + ", "  + sortedSearchTime);
            br1.newLine();
            br1.write("sorted, " + "deletion, " + numLines + ", "  + sortedRemoveTime);
            br1.newLine();
            br1.write("shuffled, " + "insertion, " + numLines + ", " + shuffledInsertTime);
            br1.newLine();
            br1.write("shuffled, " + "search, " + numLines + ", "  + shuffledSearchTime);
            br1.newLine();
            br1.write("shuffled, " + "deletion, " + numLines + ", "  + shuffledRemoveTime);
            br1.newLine();
            br1.write("reversed, " + "insertion, " + numLines + ", " + reversedInsertTime);
            br1.newLine();
            br1.write("reversed, " + "search, " + numLines + ", "  + reversedSearchTime);
            br1.newLine();
            br1.write("reversed, " + "deletion, " + numLines + ", "  + reversedRemoveTime);
            br1.newLine();
            br1.close();
        }



        // FINISH ME

    }
}
