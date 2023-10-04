package Account;

import dto.ReadAndWriteAccount;

import java.util.ArrayList;
import java.util.List;

public class ManagerAccount implements IAccountManager<Account> {
    ReadAndWriteAccount readAndWriteAccount;
    List<Account> listAccount;

    public ManagerAccount() {

        readAndWriteAccount = new ReadAndWriteAccount();
        if (readAndWriteAccount.ReadFile() != null) {
            listAccount = readAndWriteAccount.ReadFile();
        } else {
            listAccount = new ArrayList<>();
        }
    }

    @Override
    public void register(Account account) {
        listAccount.add(account);
        readAndWriteAccount.writeFile(listAccount);
    }

    @Override
    public List<Account> showAllUser() {
        return this.listAccount;
    }

    public boolean checkUser(String email, String password) {
        for (Account account : listAccount
        ) {
            if (email.equals(account.getEmail()) && password.equals(account.getPassword()) && account.getRole() == 2) {
                return true;
            }
        }
        return false;
    }
    public boolean checkAdmin(String email, String password){
        for (Account account : listAccount
        ) {
            if (email.equals(account.getEmail()) && password.equals(account.getPassword()) && account.getRole() == 1) {
                return true;
            }
        }
        return false;
    }
}
