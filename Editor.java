import java.io.*;//package imports
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.awt.Dimension;
import javax.swing.AbstractButton;
import javax.swing.ListModel;

public class Editor
{
    private static JTextPane inputBox;
    static String path;
    static String name;
    static JFrame window;
    static String save;
    static JTextField fName;
    byte clr;
    Choice fonts;
    Choice sizes;
    Button[] buT;
    JPanel contain;
    JPanel button;
    JLabel buT2;
    JLabel buT3;
    JButton bold;
    JButton italic;
    JButton colorB;
    JPanel TextPanel;
    JLabel bar;
    JFrame openWindow;
    JPanel box;
    JLabel desc;
    JButton enter;
    JTextField search;
    JFrame saveWindow;
    public Editor()
    {
        fonts = new Choice();
        fonts.add("Times New Roman");
        fonts.add("Arial");
        fonts.add("Verdana");
        fonts.add("Calibri");
        fonts.add("Comic Sans MS");
        fonts.add("Helvetica");
        fonts.add("Courier");
        fonts.add("Serif");
        
        sizes = new Choice();
        sizes.add("10");
        sizes.add("12");
        sizes.add("14");
        sizes.add("18");
        sizes.add("22");
        sizes.add("26");
        sizes.add("32");
        sizes.add("38");
        path = "";
        name = "";
        save = "";
        clr = 0;
    }
    private void GUI()
    {
        window = new JFrame("Text Editor");//creates main frame window of editor
        window.setSize(700,700);//window master size
        window.setFont(new Font("Calibri",Font.PLAIN,13));
        window.setLocationRelativeTo(null);//relative location is set to default
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        buT = new Button[2];
       
        contain = new JPanel();//creates main panel for editor compartments
        contain.setBackground(new Color(160,160,160));
        contain.setSize(700,700);//main panel size
        //
        
        button = new JPanel();//panel for buttons
        button.setPreferredSize(new Dimension(700,30));
        button.setBackground(new Color(160,160,160));
        
        buT[0] = new Button("Open");//creates button new
        buT[0].setSize(50,20);// button size set
        buT[0].setVisible(true);
        buT[0].setForeground(new Color(0,102,204));
        buT[0].addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                openFile();
            }
        });
        button.add(buT[0]);//adds button 1 to button panel
        
        buT[1] = new Button("Save");//creates button save
        buT[1].setSize(50,20);// button size set
        buT[1].setVisible(true);
        buT[1].setForeground(new Color(0,102,204));
        buT[1].addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                saveFile();
            }
        });
        button.add(buT[1]);//adds button 2 to button panel
        
        buT2 = new JLabel("  Font:");//creates button font(label)
        buT2.setForeground(new Color(0,102,204));
        buT2.setSize(50,20);// button size set
        buT2.setFont(new Font("Calibri",Font.PLAIN, 14));
        buT2.setVisible(true);
        
        fonts.setForeground(new Color(0,102,204));

        button.add(buT2);//adds font label to button panel
        button.add(fonts);//adds button 3 fonts to button panel
        
        buT3 = new JLabel(" Size:");//creates button font
        buT3.setSize(50,20);// button size set
        buT3.setForeground(new Color(0,102,204));
        buT3.setFont(new Font("Calibri",Font.PLAIN, 14));
        buT3.setVisible(true);
        
        
        sizes.setForeground(new Color(0,102,204));
        button.add(buT3);
        button.add(sizes);//adds button 4 to button panel
        
        fonts.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                fontChange();
            }
        });
        sizes.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                Font f = inputBox.getFont();
                Font fNew = new Font(f.getFontName(), f.getStyle(), Integer.parseInt(sizes.getSelectedItem()));
                inputBox.setFont(fNew);
            }
        });
       
        bold = new JButton(new StyledEditorKit.BoldAction());
        bold.setText("B");
        bold.setFont(new Font("Calibri",Font.PLAIN,14));
        bold.setPreferredSize(new Dimension(20,20));
        bold.setVisible(true);
        button.add(bold);

        
        italic = new JButton(new StyledEditorKit.ItalicAction());
        italic.setText("I");
        italic.setPreferredSize(new Dimension(20,20));
        italic.setFont(new Font("Arial",Font.ITALIC, 14));
        italic.setVisible(true);
        button.add(italic);

        colorB = new JButton();
        colorB.setText("A");
        colorB.setPreferredSize(new Dimension(20,20));
        colorB.setFont(new Font("Arial",Font.PLAIN,14));
        colorB.setVisible(true);
        button.add(colorB);
        
        
        colorB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               Font font = inputBox.getFont();
               inputBox.setFont(font);
               
               clr++;
               if(clr==7)
                  clr=1;
               if(clr==1)
               {              
                        inputBox.setForeground(Color.RED);
                }
                if(clr==2)
               {
                        inputBox.setForeground(Color.BLUE);
                }
                if(clr==3)
               {
                    inputBox.setForeground(Color.GREEN);
                }
                if(clr==4)
               {
                        inputBox.setForeground(Color.YELLOW);
                }
                if(clr==5)
               {              
                        inputBox.setForeground(Color.ORANGE);
                }
                if(clr==6)
               {
                        inputBox.setForeground(Color.BLACK);
                }
            }
        });
        

        contain.add(button,BorderLayout.NORTH);//adds combined button panel of buttons to main panel
        
        //
        
        TextPanel = new JPanel();
        TextPanel.setPreferredSize(new Dimension(700,700));
        TextPanel.setBackground(new Color(160,160,160));//sets text panel size
        TextPanel.setLayout(new BorderLayout());//sets layout to borderLayout style
        
        bar = new JLabel("Tasks");//creates taskbar
        TextPanel.add(bar,BorderLayout.NORTH);//taskbar location alloted
        
        inputBox = new JTextPane();
        inputBox.setBackground(new Color(250,250,250));//creates text box
        
        
        TextPanel.add(new JScrollPane(inputBox),BorderLayout.CENTER);//makes the input box scrollable, centre allign
        
        contain.add(TextPanel);//adds the text box panel to the main panel
        
        window.add(contain);//adds main panel into the window frame
        window.setVisible(true);
        window.toFront();//brings gui to front when called
    }
    private void openFile()
    {
        openWindow = new JFrame("Open File");
        openWindow.setSize(280,150);
        box = new JPanel();
        box.setLayout(new GridLayout(3,1,0,13));
        
        desc = new JLabel("Enter File Path");
        desc.setFont(new Font("Calibri",Font.PLAIN,14));
        
        enter = new JButton("Enter");
        
        search = new JTextField();
        box.add(desc);
        box.add(search);
        box.add(enter);
        
        openWindow.add(box);
        openWindow.setVisible(true);
        desc.setVisible(true);
        search.setVisible(true);
        enter.setVisible(true);
        
        enter.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    FileReader loc = new FileReader(search.getText());//reads input
                    StringBuilder content = new StringBuilder();// new stringbuilder object created
                    int check;//int to get file content
                    try
                    {
                        while((check = loc.read())!=-1)
                        {
                            content.append((char)check);
                        }
                    }
                    catch(IOException IOErr)
                    {
                        JOptionPane.showMessageDialog(null,"Unable to create file" +  path + "'.");//unable to write err
                        openWindow.dispose();
                        openFile();
                    }
                   inputBox.setText(content.toString());
                   File f = new File(path);
                   window.setTitle("Text Editor   :    "+f.getName());
                   window.setFont(new Font("Calibri",Font.PLAIN,13));
                }
                catch(FileNotFoundException err)
                {
                    JOptionPane.showMessageDialog(null,"File not found" + path + "'");//unable to find file err
                    openWindow.dispose();
                    openFile();
                }
                window.toFront();//brings main editor window to front view
                openWindow.dispose();// closes the search window
            }
        });
    }
    private void saveFile()
    {
        saveWindow = new JFrame("Open File");
        saveWindow.setSize(280,180);
        JPanel box = new JPanel();
        box.setLayout(new GridLayout(4,1,0,9));
        
        desc = new JLabel("Enter Name and File Path");
        desc.setFont(new Font("Calibri",Font.PLAIN,14));
        
        fName = new JTextField();
        
        
        enter = new JButton("Enter");
        
        search = new JTextField();
        box.add(desc);
        box.add(fName);
        box.add(search);
        box.add(enter);
        
        saveWindow.add(box);
        saveWindow.setVisible(true);
        desc.setVisible(true);
        search.setVisible(true);
        fName.setVisible(true);
        enter.setVisible(true);
        
        enter.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {   
                path = search.getText();
                name = fName.getText();
                
                try
                {
                    File file = new File(path+"/"+name);
                    if (file.createNewFile())
                    {
                        JOptionPane.showMessageDialog(null,"File Created succesfully");
                        FileWriter writer = new FileWriter(file);
                        writer.write(inputBox.getText());
                        writer.close();
                        saveWindow.dispose();
                }
                   else
                   {
                       JOptionPane.showMessageDialog(null,"Existing File will be overwritten");
                       file.delete();
                       FileWriter writer = new FileWriter(file);
                        writer.write(inputBox.getText());
                        writer.close();
                       saveWindow.dispose();
                    }
                }
                catch(IOException IOE)
                {
                    JOptionPane.showMessageDialog(null,"Unable to write file to" +  path + "'.");
                }
            }
        });
    }
    private void fontChange()
    {
        inputBox.setFont(new Font(fonts.getSelectedItem(),Font.PLAIN,Integer.parseInt(sizes.getSelectedItem())));
    }
    //
    public static void main(String args[])throws InterruptedException
    {
        printInfo();
        Thread.sleep(3);
        try
        {    optionCase();
        }
        catch(IOException ioe)
        {
        }
    }
    //
    private static void aster()
    {
        for(int i=0;i<20;i++)
        {
            System.out.print(" ");
            System.out.print('*');
        }
        System.out.print(" ");
    }
    private static void asterLen(String a,int z)
    {
        System.out.print(a);
        for(int x=0;x<=z;x++)
            System.out.print(" ");
    }
    private static void printInfo()
    {
        String a = "Name of Project: Text Editor";
        String b = "Student Name: Urvish Ramaiya";
        String c = "Roll no: 32";
        String d = "Academic Year: 2017-2018";
        for(int y=1;y<=6;y++)
        {
            System.out.print('*');
            if(y==1)
            {
                aster();
            }
            if(y==2)
            {
                asterLen(a,41-(a.length()+1));
            }
            if(y==3)
            {
                asterLen(b,41-(b.length()+1));
            }
            if(y==4)
            {
                asterLen(c,41-(c.length()+1));
            }
            if(y==5)
            {
                asterLen(d,41-(d.length()+1));
            }
            if(y==6)
            {
                aster();
            }
            System.out.println('*');
        }
    }
    private static void optionCase()throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println('\f');
        System.out.println("Text Editor\n"+"Enter your choice\n");
        System.out.println("1.Open Text Editor");
        System.out.println("2.Open Text Editor with miniature tutorial");
        System.out.println("3.Close Text Editor");
        String opt = br.readLine();
        int optChosen = Integer.parseInt(opt);
        Editor e = new Editor();
        switch(optChosen)
        {
            case 1: case 6: e.GUI();
                    break;
            case 2: try
                    {   
                        e.GUI();
                        Thread.sleep(1000);
                        JOptionPane.showMessageDialog(null,"Task Bar contains various applicable functions");
                        Thread.sleep(300);
                        new Editor().openFile();
                        Thread.sleep(400);
                        JOptionPane.showMessageDialog(null,"Open new File function"+"\nEg: /Users/Urvish/Desktop/example.txt");
                        Thread.sleep(300);
                        new Editor().saveFile();
                        Thread.sleep(400);
                        JOptionPane.showMessageDialog(null,"Save File function"+"\nSyntax Eg:"+"\nexample.txt"+"\n/Users/Urvish/Desktop");
                        break;
                    }
                    catch(InterruptedException ie)
                    {
                        
                    }
            case 3: System.exit(0);
                    break;
            default: System.out.println("Error, invalid option selected"+"\nPress enter to continue");
                    opt = br.readLine();
                    optionCase();
        }
    }
}
