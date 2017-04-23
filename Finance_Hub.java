package com.test;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.io.*;
import java.net.URL;

//-----------------------------------------------------------------------------
class Finance_Hub extends JFrame{

   public JLabel poundJLabel;
   public JCheckBox rupeeJCheckBox, euroJCheckBox, dollarJCheckBox, yenJCheckBox;
   public JTextField poundJTextField, rupeeJTextField,euroJTextField, dollarJTextField, yenJTextField;
   public JButton convertJButton;

//-----------------------------------------------------------------------------
     private static Double findExchangeRateAndConvert(String from, String to, int amount) {
       
        try {
            //Yahoo Finance API
            URL url = new URL("http://finance.yahoo.com/d/quotes.csv?f=l1&s="+ from + to + "=X");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            if (line.length() > 0) {
                return Double.parseDouble(line) * amount;
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
   }

//-----------------------------------------------------------------------------
	public Finance_Hub(){

            creatGUI();
   }
//-----------------------------------------------------------------------------
	public void creatGUI(){

      Container contentPane = getContentPane();

      contentPane.setLayout( null );

      poundJLabel = new JLabel();
      poundJLabel.setText( "Amount £: " );
      poundJLabel.setBounds(20,20,120,30);
      contentPane.add(poundJLabel);

      rupeeJCheckBox = new JCheckBox();
      rupeeJCheckBox.setText( "Rupee" );
      rupeeJCheckBox.setBounds(20,60,100,20);
      contentPane.add(rupeeJCheckBox);

 	  euroJCheckBox = new JCheckBox();
      euroJCheckBox.setText( "Euro" );
      euroJCheckBox.setBounds(20,100,100,20);
      contentPane.add(euroJCheckBox);    

      dollarJCheckBox = new JCheckBox();
      dollarJCheckBox.setText( "Dollar" );
      dollarJCheckBox.setBounds(20,140,100,20);
      contentPane.add(dollarJCheckBox);
      
      yenJCheckBox = new JCheckBox();
      yenJCheckBox.setText( "Yen" );
      yenJCheckBox.setBounds(20,180,100,20);
      contentPane.add(yenJCheckBox);

      poundJTextField = new JTextField();
      poundJTextField.setBounds(130,20,80,30);        
      poundJTextField.setHorizontalAlignment(JTextField.RIGHT);
      poundJTextField.setText( "0" );
      contentPane.add( poundJTextField);

      poundJTextField.addKeyListener(

            new KeyAdapter(){
                  public void keyPressed(KeyEvent e){
                              poundsKeyPressed(e);
                  }
            }
      );

//-------------------------------------------------------------------------------

      rupeeJTextField = new JTextField();
      rupeeJTextField.setBounds(130,60,80,20);
                rupeeJTextField.setHorizontalAlignment(JTextField.RIGHT);
      rupeeJTextField.setText( "R 0.00" );
      rupeeJTextField.setEditable(false);
      contentPane.add(rupeeJTextField);

      euroJTextField = new JTextField();
      euroJTextField.setBounds(130,100,80,20);
     			 euroJTextField.setHorizontalAlignment(JTextField.RIGHT);
      euroJTextField.setText( "€ 0.00" );
      euroJTextField.setEditable(false);
      contentPane.add(euroJTextField);
         
      dollarJTextField = new JTextField();
      dollarJTextField.setBounds(130,140,80,20);
      			dollarJTextField.setHorizontalAlignment(JTextField.RIGHT);
      dollarJTextField.setText( "$ 0.00" );
      dollarJTextField.setEditable(false);
      contentPane.add(dollarJTextField);
      
      yenJTextField = new JTextField();
      yenJTextField.setBounds(130,180,80,20);
      			yenJTextField.setHorizontalAlignment(JTextField.RIGHT);
      yenJTextField.setText( "¥ 0.00" );
      yenJTextField.setEditable(false);
      contentPane.add(yenJTextField);
           
      convertJButton = new JButton();
      convertJButton.setText( "Calculate Foreign Currency" );
      convertJButton.setBounds(20,220,200,30);
	  contentPane.add(convertJButton);
      
      convertJButton.addActionListener(
            new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                        convertActionPerformed(e);
                  }
            }
      );

      setTitle( "Currency Converter" );
      setSize(250,300);
      setVisible(true);  
   }

//-------------------------------------------------------------------
   public void convertActionPerformed(ActionEvent e){

      String pound = poundJTextField.getText();
      double amount = Double.parseDouble(pound);
      String currency = rupeeJTextField.getText();
   
      if(rupeeJCheckBox.isSelected()){

            DecimalFormat dfr = new DecimalFormat( "R 0.00" );
            double rupeeAmount = amount * findExchangeRateAndConvert("GBP", "INR", 1);
            rupeeJTextField.setText(dfr.format(rupeeAmount));
      }    

      if(euroJCheckBox.isSelected()){

            DecimalFormat dfr = new DecimalFormat( "€ 0.00" );
            double euroAmount = amount * findExchangeRateAndConvert("GBP", "EUR", 1);
            euroJTextField.setText(dfr.format(euroAmount));
      }	  
           
      if(dollarJCheckBox.isSelected()){

            DecimalFormat dfd = new DecimalFormat( "$ 0.00" );
            double dollarAmount = amount * findExchangeRateAndConvert("GBP", "USD", 1);
			dollarJTextField.setText(dfd.format(dollarAmount));
      }
      
      if(yenJCheckBox.isSelected()){

            DecimalFormat dfd = new DecimalFormat( "¥ 0.00" );
            double yenAmount = amount * findExchangeRateAndConvert("GBP", "JPY", 1);
            yenJTextField.setText(dfd.format(yenAmount));
      }
   };
 
//---------------------------------------------------------------------------------
   public void poundsKeyPressed(KeyEvent e){

      rupeeJTextField.setText( "0" );
      euroJTextField.setText( "0" );      
	  dollarJTextField.setText( "0" );
      yenJTextField.setText( "0" );
   }

//---------------------------------------------------------------------------------
   public static void main(String [] args){

      Finance_Hub converter = new Finance_Hub();
      converter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
 }
