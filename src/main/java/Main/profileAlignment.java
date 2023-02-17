package Main;

import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.Fasta;
import psa.PSA;

public class profileAlignment {
    private static String infile1, infile2;
    private static String outfile;

    public static void main(String[] args) throws Exception {
        // 1. 解析输入参数
        parse(args);
        // 2. 打印输入参数
        print_args();
        // 参数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("[" + sdf.format(new Date()) + "] Reading data");
        String[][] profile1 = Fasta.readFasta(infile1);
        String[][] profile2 = Fasta.readFasta(infile2);
        System.out.println("[" + sdf.format(new Date()) + "] Done.");
        System.out.println("[" + sdf.format(new Date()) + "] Aligning...");
        String[] alignedStrs = PSA.PrfAlign(profile1[1], profile2[1]);
        String[] labels = new String[alignedStrs.length];
        System.arraycopy(profile1[0], 0, labels, 0, profile1[0].length);
        System.arraycopy(profile2[0], 0, labels, profile1[0].length, profile2[0].length);
        Fasta.writeFasta(alignedStrs, labels, outfile, true);
        System.out.println("[" + sdf.format(new Date()) + "] Done.");
    }

    private static void parse(String[] args) throws Exception {
        // 比对方式选择 -m
        // 输入文件位置 -i
        // 输出文件位置 -o
        // newick文件位置 -ni
        // newick文件模式 -nm
        if (args.length != 5) {
            args_help();
            System.exit(0);
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i") && args.length > i + 2) {
                infile1 = args[++i];
                infile2 = args[++i];
            } else if (args[i].equals("-o") && args.length > i + 1) {
                outfile = args[++i];
            } else {
                args_help();
                System.exit(0);
            }
        }
        try (Writer ignored = new FileWriter(outfile)) {}
    }

    private static void args_help() {
        System.out.println("\nusage: java -jar profileAlignment.jar" + " [-i] path1 path2 [-o] path");
        System.out.println();
        System.out.println("  necessary arguments: ");
        System.out.println("    -i  Input file path (profile in fasta format)");
        System.out.println("    -o  Output file path");

        System.out.println();
    }

    private static void print_args() {
        System.out.println("\n**");
        System.out.println("** infile: " + infile1);
        System.out.println("** infile: " + infile2);
        System.out.println("** outfile: " + outfile);
        System.out.println("**");
        System.out.println();
    }
}
