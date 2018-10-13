/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import java.util.HashMap;
import javax.swing.JCheckBoxMenuItem;

/**
 *
 * @author german.ramirez
 */
public class CheckBoxMenuItem extends JCheckBoxMenuItem {
    
    //Properties
    private int Id;
    private HashMap<String, Object> Attributes;

    //Getters and Setters
    public HashMap<String, Object> getAttributes() {
        return Attributes;
    }

    public void setHmap(HashMap<String, Object> Attributes) {
        this.Attributes = Attributes;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    //Constructors
    public CheckBoxMenuItem()
    {
        this.Attributes = new HashMap<>();
    }
    
    public CheckBoxMenuItem(String text)
    {
        super.setText(text);
        this.Attributes = new HashMap<>();
    }
    
    public CheckBoxMenuItem(String text, int id)
    {
        super.setText(text);
        this.Id = id;
        this.Attributes = new HashMap<>();
    }
    
    //Methods
    public void addAttributes(String key, Object value)
    {
        this.Attributes.put(key, value);
    }
    
    public Object getAttribute(String key)
    {
        Object value = null;
        if(this.Attributes.containsKey(key))
        {
            value = this.Attributes.get(key);
        }        
                
        return value;
    }
}