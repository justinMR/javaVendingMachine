package proj4110;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 *
 * @author Paul Finnegan
 */
public class VendingMachineController implements Initializable {
   
/**
 * The data attributes required to 
 * specify the state of the vending machine.
 * 
 * For simplicity, there is no attribute for an
 * initial cash balance for dispensing change, 
 * this amount is often referred to as a "float".
 */    

    
    private boolean juice; //is the machine on or off?
    private int pepsiStock; //the max number of pepsis held by the machine
    private int spriteStock; //the number of sprites held by the machine
    private double changeInserted; 
    private double changeInSlot; //change dispensed
    private double pepsiPrice; 
    private double spritePrice;
    private int pepsiInventory; // the number of pepsis currently in the machine
    private int spriteInventory; // the number of sprites currently in the machine
    private int drinksInSlot; //total number of drinks (pepsi and sprite) dispensed
    
    //This object formats dollars and cents for display in the GUI.
    DecimalFormat df2 = new DecimalFormat("0.00");
    
    /**
     *  The controls for the GUI. 
     *  Much of this code is auto-generated in JavaFX Scene Builder
     */
    
    @FXML
    private Button power;
    @FXML
    private TextField status;
    @FXML
    private Button fiveCents;
    @FXML
    private Button tenCents;
    @FXML
    private Button cents25;
    @FXML
    private TextField moneyInserted;
    @FXML
    private TextField changeAmount;
    @FXML
    private Button takeChange;
    @FXML
    private Button cancel;
    @FXML
    private Button pepsi;
    @FXML
    private TextField slotCans;
    @FXML
    private Button takeCan;
    @FXML
    private Button sprite;
    @FXML
    private TextField pepsiStockDisplay;
    @FXML
    private TextField spriteStockDisplay;
    @FXML
    private Button restock;
    @FXML
    private Label pinLabel;
    @FXML
    private TextField pinField;
    @FXML
    private Button okPin;
   
    /**
     * 
     * The following method handles clicks (events)generated on
     * the buttons in the GUI, and passes these events to the 
     * public void initialize(URL url, ResourceBundle rb)
     * method for system processing/response. Do not remove this
     * method, even though it is blank.
     *  
     */
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
		// Do not provide an implementation for this method.
		// Leave 'as is'.
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    //initial values for system attributes.
    juice = false;
    pepsiStock = 5;
    spriteStock = 7;
    changeInserted = 0.0;
    changeInSlot = 0.0;
    pepsiPrice = 0.75;
    spritePrice = 0.80;
    pepsiInventory = 5;
    spriteInventory = 7;
    drinksInSlot = 0;
    
    df2 = new DecimalFormat("0.00");
        
        power.setOnAction((event) -> {
            
            if(juice == false){
                status.setText("ON");
                juice = true;
                pepsiStockDisplay.setText(String.valueOf(pepsiInventory));
                spriteStockDisplay.setText(String.valueOf(spriteInventory));
            } else {
                
                status.setText("OFF");
                juice = false;
                changeInSlot = changeInserted  + changeInSlot;
                changeAmount.setText(df2.format(changeInSlot));
                moneyInserted.setText("0.00");
                changeInserted=0;
                pepsiStockDisplay.setText("");
                spriteStockDisplay.setText("");
                pinLabel.setVisible(false);
                pinField.setVisible(false);
                okPin.setVisible(false);
                
                
            }
            status.setStyle("-fx-base: #ffffff;");
            
        });
        
        restock.setOnAction((event) -> {
            
            if(juice == false){
                
                status.setText("OFF : TURN IT ON!");
                status.setStyle("-fx-base: #A32BC9;");
            } else if (pepsiInventory == 5 && spriteInventory == 7) {
               
                status.setText("MACHINE IS ALREADY FULLY STOCKED");
                status.setStyle("-fx-base: #ffffff;");
            } else {
               
                pinLabel.setVisible(true);
                pinField.setVisible(true);
                okPin.setVisible(true);
                status.setText("ON");
                status.setStyle("-fx-base: #ffffff;");
            }
        });
        
        okPin.setOnAction((event) -> {
            
            String pinNumber = pinField.getText();
            
            if( pinNumber.equals("1234") ){
                
                pepsiInventory = 5;
                pepsiStockDisplay.setText(String.valueOf(pepsiInventory));
                spriteInventory = 7;
                spriteStockDisplay.setText(String.valueOf(spriteInventory));
                status.setText("MACHINE RESTOCKED");
                pinLabel.setVisible(false);
                pinField.setVisible(false);
                okPin.setVisible(false);
            } 
            pinField.setText("");
            status.setStyle("-fx-base: #ffffff;");
        });
        
        fiveCents.setOnAction((event) -> {
            
            if(juice == false){
                
                status.setText("OFF : TURN IT ON!");
                status.setStyle("-fx-base: #A32BC9;");
            } else {
                
                changeInserted += 0.05;
                moneyInserted.setText(String.valueOf(df2.format(changeInserted)));
                status.setText("COLLECTING MONEY");
                status.setStyle("-fx-base: #ffffff;");
            }
        });
        
        tenCents.setOnAction((event) -> {
            
            if(juice == false){
                
                status.setText("OFF : TURN IT ON!");
                status.setStyle("-fx-base: #A32BC9;");
            } else {
                
                changeInserted += 0.10;
                moneyInserted.setText(String.valueOf(df2.format(changeInserted)));
                status.setText("COLLECTING MONEY");
                status.setStyle("-fx-base: #ffffff;");
            }
        });
        
        cents25.setOnAction((event) -> {
            
             if(juice == false){
                
                status.setText("OFF : TURN IT ON!");
                status.setStyle("-fx-base: #A32BC9;");
            } else {
                
                changeInserted += 0.25;
                moneyInserted.setText(String.valueOf(df2.format(changeInserted)));
                status.setText("COLLECTING MONEY");
                status.setStyle("-fx-base: #ffffff;");
            } 
        });
        
        takeChange.setOnAction((event) -> {
            
            changeInSlot = 0;
            changeAmount.setText(String.valueOf(df2.format(changeInSlot)));
            
            if(juice == true){
                
                status.setText("BABY, YOU GOT YOUR MONEY");
                status.setStyle("-fx-base: #ffffff;");
            } else if (juice == false) {
                
                status.setText("OFF");
                status.setStyle("-fx-base: #ffffff;");
            }
        });
        
        takeCan.setOnAction((event) -> {
            
            if (drinksInSlot > 0) {
                
                drinksInSlot -= 1;
                slotCans.setText(String.valueOf(drinksInSlot));
            }
            
            if(juice == true){
                
                status.setText("DRINK UP!");
                status.setStyle("-fx-base: #ffffff;");
            } else if (juice == false) {
                    
                status.setText("OFF");
                status.setStyle("-fx-base: #ffffff;");
            } 
        });
        
        cancel.setOnAction((event) -> {
            
            if(juice == false ){
                
                status.setText("OFF : TURN IT ON!");
                status.setStyle("-fx-base: #A32BC9;");
            } else if (changeInserted > 0) {    
                           
                status.setText("TRANSACTION CANCELLED: TAKE YOUR CHANGE.");
                changeInSlot = changeInserted  + changeInSlot;
                changeAmount.setText(String.valueOf(df2.format(changeInSlot)));
                moneyInserted.setText("0.00");
                status.setStyle("-fx-base: #ffffff;");
                changeInserted=0;
            }
        });
        
        pepsi.setOnAction((ActionEvent event) -> {
            
            if(juice == false){
                
                status.setText("OFF : TURN IT ON!");
                status.setStyle("-fx-base: #A32BC9;");
            } else if (changeInserted > 0.74) {
                
                if (pepsiInventory > 0) {   
                
                    pepsiInventory -= 1;
                    pepsiStockDisplay.setText(String.valueOf(pepsiInventory));
                    drinksInSlot += 1;
                    slotCans.setText(String.valueOf(drinksInSlot));
                    
                    if(changeInserted > pepsiPrice) {
                        
                        changeInSlot += changeInserted - pepsiPrice;
                    }
                    changeAmount.setText(String.valueOf(df2.format(changeInSlot)));
                    changeInserted = 0;
                    moneyInserted.setText(String.valueOf(df2.format(changeInserted)));
                    status.setText("PEPSI DISPENSED");
                    status.setStyle("-fx-base: #ffffff;");
                } else {
                    
                    status.setText("INSUFFICIENT PEPSI STOCK");
                    status.setStyle("-fx-base: #ffffff;");
                }
            } else {
                
                status.setText("INSERT MORE MONEY!");
                status.setStyle("-fx-base: #ffffff;");
            }
        });
        
         sprite.setOnAction((ActionEvent event) -> {
             
            if(juice == false){
                
                status.setText("OFF : TURN IT ON!");
                status.setStyle("-fx-base: #A32BC9;");
            } else if(changeInserted > 0.79) {
                
                if (spriteInventory > 0) {
                                   
                    spriteInventory -= 1;
                    spriteStockDisplay.setText(String.valueOf(spriteInventory));
                    drinksInSlot += 1;
                    slotCans.setText(String.valueOf(drinksInSlot));
                    
                    if(changeInserted > spritePrice) {
                        
                        changeInSlot += changeInserted - spritePrice;
                    }
                    changeAmount.setText(String.valueOf(df2.format(changeInSlot)));
                    changeInserted = 0;
                    moneyInserted.setText(String.valueOf(df2.format(changeInserted)));
                    status.setText("SPRITE DISPENSED");
                    status.setStyle("-fx-base: #ffffff;");
                } else {
                    
                    status.setText("INSUFFICIENT SPRITE STOCK");
                    status.setStyle("-fx-base: #ffffff;");
                }
            } else {
                
                status.setText("INSERT MORE MONEY!");
                status.setStyle("-fx-base: #ffffff;");
            }
        }); 
    }     
}