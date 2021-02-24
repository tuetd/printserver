package com.pruvn.rms.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SMLFileChecker {
	static String KEY = "4160EEE1728CE26EA50EF992316324BE";

    public static void checkFile(InputStream inputStream) throws Exception {
        System.out.println("---------------------");
        System.out.println("Checking file... ");
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            String line = null;
            List<String> linearr = new ArrayList<String>();
            while ((line = in.readLine()) != null && !line.trim().equals("")) {
                linearr.add(line);
            }
            long amount = 0;
            System.out.println("Total of records: " + (linearr.size() - 1));
            StringBuilder sigBuilder;
            for (int i = 0; i < linearr.size() - 1; i++) {
                String fields[] = linearr.get(i).split(",");
                if (fields.length != 9) {
                    System.out.println("Pre-import file validation error. Line " + i + "Total of fields != 9");
                    throw new Exception("Pre-import file validation error. Line " + i + "Total of fields != 9");
                }
                sigBuilder = new StringBuilder();
                for (int j = 0; j < fields.length - 1; j++) {
                    if (j != 4) {
                        sigBuilder.append(fields[j]);
                    }
                }
                sigBuilder.append(KEY);
                if (!fields[8].equalsIgnoreCase(CommonUtils.MD5(sigBuilder.toString()))) {
                    System.out.println("Pre-import file validation error. Line " + i + " : invalid line checksum!");
                    throw new Exception("Pre-import file validation error. Line " + i + " : invalid line checksum!");
                }
                amount += Long.parseLong(fields[2]);
                System.out.println("---------------------");
            }
            System.out.println("Checksum: " + linearr.get(linearr.size() - 1));
            String checksumFields[] = linearr.get(linearr.size() - 1).split(",");
            if (checksumFields.length != 5) {
                System.out.println("Pre-import file validation error. File checksum invalid, otal of fields != 5");
                throw new Exception("Pre-import file validation error. File checksum invalid, otal of fields != 5");
            }
            if (!checksumFields[2].equals(amount + "")) {
                System.out.println("Pre-import file validation error. File checksum: Total amount is mot match");
                throw new Exception("Pre-import file validation error. File checksum: Total amount is mot match");
            }
            if (!checksumFields[1].equals((linearr.size() - 1) + "")) {
                System.out.println("Pre-import file validation error. File checksum: Total records is mot match");
                throw new Exception("Pre-import file validation error. File checksum: Total records is mot match");
            }
            sigBuilder = new StringBuilder();
            sigBuilder.append(checksumFields[0]).append(checksumFields[1]).append(checksumFields[2]).append(checksumFields[3]).append(KEY);
            if (!checksumFields[4].equalsIgnoreCase(CommonUtils.MD5(sigBuilder.toString()))) {
                System.out.println("Pre-import file validation error. File checksum: Invalid");
                throw new Exception("Pre-import file validation error. File checksum: Invalid");
            }
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
    }
}
