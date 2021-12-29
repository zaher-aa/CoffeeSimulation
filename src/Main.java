import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main extends JFrame implements ActionListener {
  Container pane;
  JTabbedPane tabbedPane = new JTabbedPane();
  JPanel home = new JPanel();
  JPanel admin = new JPanel();
  JLabel name, item, amountOfSugar, price;
  JTextField nameTxt, priceTxt;
  JComboBox itemSelect, sugarSelect;
  JButton order, newItem, showItems;
  public ArrayList<String> itemsWithPrices = new ArrayList<String>();
  public ArrayList<String> itemsList = new ArrayList<String>();
  public String[] items = new String[itemsList.size()];
  public DefaultListModel<String> allItems = new DefaultListModel<>();
  public JList<String> list = new JList<>(allItems);
  public ArrayList<String> itemsWithSugar = new ArrayList<String>();
  
  public Main() {
    super("Shop");
    this.setVisible(true);
    this.setSize(720, 500);
    this.setLocation(400, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pane = this.getContentPane();
    pane.setLayout(null);
    home.setLayout(null);
    admin.setLayout(null);
    
    String[] sugarAmount = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    
    // labels
    name = new JLabel("Name");
    item = new JLabel("Item");
    amountOfSugar = new JLabel("Amount Of Sugar");
    price = new JLabel("Price");
    
    // txt fields
    nameTxt = new JTextField();
    priceTxt = new JTextField();
    
    // select fields
    itemSelect = new JComboBox(items);
    sugarSelect = new JComboBox(sugarAmount);
    
    // button
    order = new JButton("Order");
    newItem = new JButton("New Item");
    showItems = new JButton("Show Item");
    
    tabbedPane.setBounds(0, 0, 700, 500);
    tabbedPane.add("Home", home);
    tabbedPane.add("Admin", admin);
    
    
    name.setBounds(10, 20, 250, 50);
    item.setBounds(10, 105, 250, 50);
    amountOfSugar.setBounds(10, 190, 250, 50);
    price.setBounds(10, 275, 250, 50);
    nameTxt.setBounds(250, 20, 390, 50);
    itemSelect.setBounds(250, 105, 390, 50);
    sugarSelect.setBounds(250, 190, 390, 50);
    priceTxt.setBounds(250, 275, 390, 50);
    order.setBounds(300, 350, 100, 50);
    newItem.setBounds(250, 100, 200, 50);
    showItems.setBounds(250, 280, 200, 50);
    
    home.add(name);
    home.add(item);
    home.add(amountOfSugar);
    home.add(price);
    home.add(nameTxt);
    home.add(priceTxt);
    home.add(itemSelect);
    home.add(sugarSelect);
    home.add(order);
    admin.add(newItem);
    admin.add(showItems);
    pane.add(tabbedPane);
    
    // eventListener for itemSelect
    itemSelect.addActionListener(event -> {
      JComboBox comboBox = (JComboBox) event.getSource();
      Object selected = comboBox.getSelectedItem();
      if (!itemsWithSugar.contains(selected)) {
        sugarSelect.setEnabled(false);
      } else {
        sugarSelect.setEnabled(true);
      }
    });
    
    // Action Events
    newItem.addActionListener(this);
    showItems.addActionListener(this);
    order.addActionListener(this);
  }
  
  class NewItem extends JFrame implements ActionListener {
    JButton save, cancel;
    JLabel name, price, hasSugar;
    JTextField nameTxt, priceTxt;
    JCheckBox hasSugarCheckmark;
    Container pane;
    
    public NewItem() {
      super("New Item");
      this.setVisible(true);
      this.setSize(500, 400);
      this.setLocation(450, 300);
      this.setLayout(null);
      pane = this.getContentPane();
      
      save = new JButton("Save");
      cancel = new JButton("Cancel");
      name = new JLabel("Item Name");
      price = new JLabel("Price");
      hasSugar = new JLabel("Has Sugar");
      nameTxt = new JTextField();
      priceTxt = new JTextField();
      hasSugarCheckmark = new JCheckBox();
      
      name.setBounds(20, 20, 150, 50);
      price.setBounds(20, 90, 150, 50);
      hasSugar.setBounds(20, 160, 150, 50);
      nameTxt.setBounds(170, 20, 250, 50);
      priceTxt.setBounds(170, 90, 250, 50);
      hasSugarCheckmark.setBounds(170, 175, 20, 20);
      save.setBounds(130, 250, 100, 50);
      cancel.setBounds(260, 250, 100, 50);
      
      pane.add(save);
      pane.add(cancel);
      pane.add(name);
      pane.add(price);
      pane.add(hasSugar);
      pane.add(nameTxt);
      pane.add(priceTxt);
      pane.add(hasSugarCheckmark);
      
      // Action Events
      cancel.addActionListener(this);
      save.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == cancel) dispose();
      else if (e.getSource() == save) {
        if (nameTxt.getText().length() > 0 && priceTxt.getText().length() > 0) {
          if (hasSugarCheckmark.isSelected()) {
            itemsWithSugar.add(nameTxt.getText());
            itemsWithPrices.add(nameTxt.getText() + " = " + priceTxt.getText() + " || Has_Sugar");
          } else {
            itemsWithPrices.add(nameTxt.getText() + " = " + priceTxt.getText() + " || No_Sugar");
          }
          itemsList.add(nameTxt.getText());
          allItems.removeAllElements();
          for (int i = 0; i < itemsWithPrices.size(); i++) {
            allItems.addElement(itemsWithPrices.get(i));
          }
          items = new String[itemsList.size()];
          for (int i = 0; i < itemsList.size(); i++) {
            items[i] = itemsList.get(i);
          }
          itemSelect.removeAllItems();
          for (int i = 0; i < items.length; i++) {
            itemSelect.addItem(items[i]);
          }
          nameTxt.setText("");
          priceTxt.setText("");
          JOptionPane.showMessageDialog(this, "Item has been added successfully");
        } else {
          JOptionPane.showMessageDialog(this, "Please fill all required fields", "Warning", JOptionPane.WARNING_MESSAGE);
        }
      }
    }
  }
  
  class ShowItems extends JFrame implements ActionListener {
    Container pane;
    JButton delete;
    
    public ShowItems() {
      super("Show Items");
      this.setVisible(true);
      this.setLayout(null);
      this.setSize(500, 500);
      this.setLocation(450, 300);
      pane = this.getContentPane();
      list = new JList<>(allItems);
      delete = new JButton("Delete");
      
      list.setBounds(30, 30, 250, 280);
      delete.setBounds(200, 350, 130, 50);
      
      
      pane.add(list);
      pane.add(delete);
      
      // Action Events
      delete.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == delete) {
        try {
          DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
          int itemToRemoveIdx = list.getSelectedIndex();
          model.remove(itemToRemoveIdx);
          itemSelect.removeAllItems();
          itemsList.remove(itemToRemoveIdx);
          items = new String[itemsList.size()];
          for (int i = 0; i < itemsList.size(); i++) {
            items[i] = itemsList.get(i);
          }
          for (int i = 0; i < items.length; i++) {
            itemSelect.addItem(items[i]);
          }
        } catch (Exception e1) {
          JOptionPane.showMessageDialog(this, "Please select an item to delete it", "Warning", JOptionPane.WARNING_MESSAGE);
        }
      }
    }
  }
  
  public static void main(String[] args) {
    new Main();
  }
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == newItem) new NewItem();
    else if (e.getSource() == showItems) new ShowItems();
    else if (e.getSource() == order) {
      if (nameTxt.getText().length() > 0 && priceTxt.getText().length() > 0 && items.length > 0) {
        String itemName = itemSelect.getSelectedItem().toString();
        int itemPrice = Integer.parseInt(priceTxt.getText());
        Pattern pattern = Pattern.compile(itemName, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        String neededStr = null;
        for (int i = 0; i < itemsWithPrices.size(); i++) {
          matcher = pattern.matcher(itemsWithPrices.get(i));
          if (matcher.find()) {
            neededStr = itemsWithPrices.get(i);
            break;
          }
        }
        int actualPrice = Integer.parseInt(neededStr.replaceAll("[^0-9]", ""));
        if (actualPrice == itemPrice) {
          JOptionPane.showMessageDialog(this, "Item purchased successfully");
        } else {
          JOptionPane.showMessageDialog(this, "Please write the correct price", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
      } else {
        JOptionPane.showMessageDialog(this, "Please fill in all the required fields", "Warning", JOptionPane.WARNING_MESSAGE);
        
      }
    }
  }
}
