package dto;

import Account.Account;
import Model.Body;
import Model.Camera;
import Model.Lens;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteAccount {
    File file = new File("Data/Account.csv");

    public void writeFile(List<Account> list) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String line = "";
            for (Account account : list
            ) {
                line += account.getEmail() + "," + account.getPassword() + "," + account.getNameUser() + "," + account.getPhoneNumber() + "," + account.getAddress() + "," + account.getRole() + "\n";
            }
            bufferedWriter.write(line);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Account> ReadFile() {
        ArrayList<Account> list = new ArrayList<>();
        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Account account = new Account(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]));
                list.add(account);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
