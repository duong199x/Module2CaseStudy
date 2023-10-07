package dto;

import Account.Account;
import cart.Cart;

import java.io.*;
import java.util.List;

public class ReadAnfWriteBill {
    File file = new File("Data/Bill.csv");

    public void writeFile(Account account, Cart cart) {
        ReadAndWriteAccount readAndWriteAccount = new ReadAndWriteAccount();
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String line = "";
            String lineCart = "";
            System.out.println(readAndWriteAccount.ReadFile());
            for (int i = 0; i < readAndWriteAccount.ReadFile().size(); i++) {
                if (readAndWriteAccount.ReadFile().get(i).getEmail().equals(account.getEmail())) {
                    line += account.getEmail() + "," + account.getNameUser() + "," + account.getPhoneNumber() + "," + account.getAddress();
                }
            }
            lineCart += cart.getListProductInCart();
            String lineBill = line + lineCart + "\n";
            bufferedWriter.write(lineBill);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String ReadBill() {
        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            bufferedReader.close();
            return line;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
