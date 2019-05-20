package design;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public  class Rendu extends JLabel implements ListCellRenderer {

Color selectCouleur = Color.RED;

public  Rendu(){
 
}
public Component getListCellRendererComponent(JList list, 
   Object value, // valeur à afficher
   int index, // indice d'item
   boolean isSelected, // l'item est-il sélectionné
   boolean cellHasFocus) // La liste a-t-elle le focus
{
 String s = value.toString();
 if (isSelected) {
    setBackground(list.getSelectionBackground());
    setForeground(selectCouleur);
    setText(s+"  "+index);
  
 }else{
    setBackground(list.getBackground());
    setForeground(list.getForeground());
    setText(s);

 }
 setEnabled(list.isEnabled());
 setFont(list.getFont());
 setOpaque(true);
 return this;
}
public Component getListCellRendererComponent1(JList list, Object value, int index, boolean isSelected,
		boolean cellHasFocus) {
	// TODO Auto-generated method stub
	return null;
}
}
