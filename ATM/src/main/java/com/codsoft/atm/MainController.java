package com.codsoft.atm;

import com.codsoft.atm.models.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TextField txtBalance;
    @FXML
    private TextField txtAmount;

    //the account object declaration
    private Account account;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize controls
        account = new Account();
        txtAmount.addEventFilter(KeyEvent.KEY_TYPED,keyEvent -> {

            String character = keyEvent.getCharacter();
            String tempStr = "";
            if(keyEvent.getSource() instanceof
                    TextField textField){
                tempStr = textField.getText().concat(character);

            }
            //ensure the amount entered is a valid 'money' format
            if(!tempStr.matches("^\\d+(?:\\.[0-9]{0,2})?$")){
                keyEvent.consume();
            }
        });

    }

    @FXML
    protected void onCheckBalanceClick(ActionEvent actionEvent) {
        //show the balance on a textfield
        txtBalance.setText(String.valueOf(account.checkBalance()));
    }


    @FXML
    protected void onDepositClick(ActionEvent actionEvent) {
        if(txtAmount.getText().isEmpty()){
            CustomDialog.show("Deposit","You must enter an amount to deposit.");
            return;
        }

        double amount = Double.parseDouble(txtAmount.getText());
        double depositResult = account.deposit(amount);

        if(amount > 0) {
            //indicate success to the user
            CustomDialog.show("Deposit", String.format("Successfully deposited R%s.", amount));
            txtBalance.setText(String.valueOf(depositResult));

        }else {
            CustomDialog.show("Deposit", "You must enter an amount more that R0 to deposit.");
        }
    }


    @FXML
    protected void onWithdrawClick(ActionEvent actionEvent) {
        if(txtAmount.getText().isEmpty()){
            CustomDialog.show("Withdraw","You must enter an amount to withdraw.");
            return;
        }

        double amount = Double.parseDouble(txtAmount.getText());
        double withdrawResult = account.withdraw(amount);

        //make sure that the user enters a value more than R0
        if(amount > 0 ) {
            if (withdrawResult == -1)
                CustomDialog.show("Withdraw", "You must enter an amount less than or equal to the balance.");
            else {
                CustomDialog.show("Withdraw", String.format("Successfully withdrawn R%s.", amount));
                txtBalance.setText(String.valueOf(account.checkBalance()));
            }
        }else{
            CustomDialog.show("Withdraw", "You must enter an amount more that R0 to withdraw.");

        }
    }
}