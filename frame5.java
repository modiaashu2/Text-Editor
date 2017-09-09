import java.awt.*;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.*;
import javax.swing.undo.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;
import javax.swing.border.*;
import java.util.*;
import java.awt.font.TextAttribute;

class frame5
{
    public static void main(String str[])
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                FM f = new FM();
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setTitle("Hello there!!");
            }
        });
    }
}

class FM extends JFrame
{
    UndoManager undo = new UndoManager();
    JPanel p1, p2;
    JTextArea TA;
    JTextField TF;
    JPasswordField PF;
    JLabel L1, L2;
    JCheckBox b, i;
    JMenuBar JM;
    int ct;
    File fl1;
    PrintWriter out;
    String finalfont = "Serif", ffont = "Serif";
    int finalstyle = 12, ffstyle = 12;
    Integer finalsize = Font.PLAIN, ffsize = Font.PLAIN;
    public JMenuItem Newi, Openi, Savei, SaveAsi, Exiti;
    public void ssize(JMenuItem item)
    {
        item.setPreferredSize(new Dimension(150, item.getPreferredSize().height));
    }
    public FM()
    {
        ct = 0;
        setSize(800, 700);
        L1 = new JLabel("Username");
        L2 = new JLabel("Password");
        TA = new JTextArea(10, 40);
        TF = new JTextField(10);
        PF = new JPasswordField(10);
        JM = new JMenuBar();
        JMenu Filem, Editm, Helpm, Viewm;
        Filem = new JMenu("File");
        Editm = new JMenu("Edit");
        Viewm = new JMenu("View");
        Helpm = new JMenu("Help");
        TA.getDocument().addUndoableEditListener(new UndoableEditListener(){
            public void undoableEditHappened(UndoableEditEvent e)
            {
                undo.addEdit(e.getEdit());
            }
        });
        Newi = new JMenuItem("New");
        Openi = new JMenuItem("Open");
        Savei = new JMenuItem("Save");
        SaveAsi = new JMenuItem("Save As");
        Exiti = new JMenuItem("Exit");
        ssize(Newi);
        ssize(Openi);
        ssize(SaveAsi);
        ssize(Savei);
        ssize(Exiti);
        JFileChooser FL = new JFileChooser();
        FL.setCurrentDirectory(new File("."));
        Newi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("New Pressed");
                ct = 0;
                if(TA.getText() != "" && TA.getText() != null)
                {
                    int se = JOptionPane.showConfirmDialog(FM.this, "Do you want to save the changes", "Tell ME!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(se == JOptionPane.YES_OPTION)
                    {
                        Savei.doClick();
                    }
                    TA.setText(null);
                }
            }
        });
        Newi.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        Openi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Open Pressed");
                int xx = FL.showOpenDialog(FM.this);
                if(xx == JFileChooser.APPROVE_OPTION)
                {
                    System.out.println("Opening a file");
                    File fl = FL.getSelectedFile();
                    try
                    {
                        Scanner in = new Scanner(fl);
                        TA.setText(null);
                        while(in.hasNext())
                        {
                            TA.append(in.nextLine() + "\n");
                        }
                    }
                    catch(FileNotFoundException ex)
                    {
                        System.out.println("Exception " + ex);
                    }
                }
                else
                {
                    System.out.println("Cancelled by user");
                }
            }
        });
        Openi.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        SaveAsi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                ct = 1;
                System.out.println("Save As Pressed");
                int xx = FL.showSaveDialog(FM.this);
                if(xx == JFileChooser.APPROVE_OPTION)
                {
                    System.out.println("Saving a file");
                    File fl = FL.getSelectedFile();
                    fl1 = fl;
                    try
                    {
                        out = new PrintWriter(fl);
                        String str = TA.getText();
                        //System.out.println(str);
                        out.println(str);
                        out.close();
                    }
                    catch(FileNotFoundException ex)
                    {
                        System.out.println("Exception " + ex);
                    }
                }
                else
                {
                    System.out.println("Cancelled by user");
                }
            }
        });
        Savei.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Hello");
                if(ct == 0)
                SaveAsi.doClick();
                else
                {
                    try
                    {
                        out = new PrintWriter(fl1);
                        String str = TA.getText();
                        //System.out.println(str);
                        out.println(str);
                        out.close();
                    }
                    catch(FileNotFoundException ex)
                    {
                        System.out.println("Exception " + ex);
                    }
                }
            }
        });
        Savei.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        Exiti.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                int se = JOptionPane.showConfirmDialog(FM.this, "Are you sure you want to leave me!!??", "Tell ME!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(se == JOptionPane.YES_OPTION)
                {
                    int x = JOptionPane.showConfirmDialog(FM.this, "Its not you its me", "KNOW THIS!!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                    System.exit(0);
                }
            }
        });
        Exiti.setAccelerator(KeyStroke.getKeyStroke("alt X"));
        Filem.add(Newi);
        Filem.add(Openi);
        Filem.addSeparator();
        Filem.add(SaveAsi);
        Filem.add(Savei);
        Filem.addSeparator();
        Filem.add(Exiti);
        JM.add(Filem);
        JMenuItem Cuti, Copyi, Pastei, Undoi, Redoi;
        Cuti = new JMenuItem("Cut");
        Cuti.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                TA.cut();
            }
        });
        Cuti.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        Editm.add(Cuti);
        Copyi = new JMenuItem("Copy");
        Copyi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                TA.copy();
            }
        });
        Editm.add(Copyi);
        Copyi.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
        Pastei = new JMenuItem("Paste");
        Pastei.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                TA.paste();
            }
        });
        Editm.add(Pastei);
        Pastei.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
        Undoi = new JMenuItem("Undo");
        Undoi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    undo.undo();
                }
                catch(CannotRedoException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        Undoi.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
        Editm.add(Undoi);
        Redoi = new JMenuItem("Redo");
        Redoi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    undo.redo();
                }
                catch(CannotRedoException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        Redoi.setAccelerator(KeyStroke.getKeyStroke("ctrl Y"));
        ssize(Cuti);
        ssize(Copyi);
        ssize(Pastei);
        ssize(Undoi);
        ssize(Redoi);
        Editm.add(Redoi);
        JM.add(Editm);
        JMenuItem Formati = new JMenuItem("Format");
        Formati.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                finalfont = ffont;
                finalstyle = ffstyle;
                finalsize = ffsize;
                JDialog JF = new JDialog(FM.this, "Format");
                JLabel aprev = new JLabel("ABCDEF   abcdef");
                JF.setLayout(new GridBagLayout());
                String[] allfont = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
                JList<String> xx = new JList<String>(allfont);
                xx.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                xx.setLayoutOrientation(JList.VERTICAL);
                xx.setVisibleRowCount(6);
                MouseListener ml2 = new MouseAdapter(){
                    public void mouseClicked(MouseEvent e)
                    {
                        finalfont = (String)xx.getSelectedValue();
                        System.out.println(finalfont);
                        aprev.setFont(new Font(finalfont, finalstyle, 24));
                    }
                };
                xx.addMouseListener(ml2);
                JScrollPane scrl = new JScrollPane(xx);
                //xx.setPreferredSize(new Dimension(250, 100));
                Border x = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Font");
                Border y = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Size");
                Border z = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Style");
                Border zx = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Preview");
                scrl.setBorder(x);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.weightx = gbc.weighty = 100;
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(30, 30, 30, 30);
                //JF.add(afont, gbc);
                JF.add(scrl, gbc);
                ArrayList<Integer> afsize = new ArrayList<Integer>();
                for(int i = 2; i <= 128; i += 2)
                {
                    afsize.add(new Integer(i));
                }
                gbc.gridx = 1;
                JList yy = new JList(afsize.toArray());
                yy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                yy.setLayoutOrientation(JList.VERTICAL);
                yy.setVisibleRowCount(6);
                JScrollPane scrl2 = new JScrollPane(yy);
                scrl2.setBorder(y);
                MouseListener ml = new MouseAdapter(){
                    public void mouseClicked(MouseEvent e)
                    {
                        finalsize = (Integer)yy.getSelectedValue();
                        System.out.println(finalsize);
                        aprev.setFont(new Font(finalfont, finalstyle, finalsize));
                    }
                };
                yy.addMouseListener(ml);
                JF.add(scrl2, gbc);
                String[] allstyle = {"Plain", "Bold", "Italics", "Bold Italics"};
                JList<String> zzz = new JList<String>(allstyle);
                zzz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                zzz.setLayoutOrientation(JList.VERTICAL);
                zzz.setVisibleRowCount(4);
                MouseListener ml3 = new MouseAdapter(){
                    public void mouseClicked(MouseEvent e)
                    {
                        String lmn = (String)zzz.getSelectedValue();
                        if(lmn.compareTo("Bold") == 0)
                        finalstyle = Font.BOLD;
                        else if(lmn.compareTo("Italics") == 0)
                        finalstyle = Font.ITALIC;
                        else if(lmn.compareTo("Bold Italics") == 0)
                        finalstyle = Font.BOLD + Font.ITALIC;
                        else
                        finalstyle = Font.PLAIN;
                        System.out.println(finalstyle);
                        aprev.setFont(new Font(finalfont, finalstyle, finalsize));
                    }
                };
                aprev.setFont(new Font(finalfont, finalstyle, finalsize));
                zzz.addMouseListener(ml3);
                JPanel tt = new JPanel();
                tt.setLayout(new BorderLayout(5, 5));
                tt.add(zzz, BorderLayout.PAGE_START);
                JCheckBox kkk = new JCheckBox("Underline");
                tt.add(kkk, BorderLayout.PAGE_END);
                gbc.gridx = 2;
                tt.setBorder(z);
                JF.add(tt, gbc);
                kkk.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        Font fnt1 = aprev.getFont();
                        Map attributes = fnt1.getAttributes();
                        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                        aprev.setFont(fnt1.deriveFont(attributes));
                    }
                });
                gbc.gridy = 1;
                gbc.ipady = 50;
                gbc.gridx = 0;
                gbc.gridwidth = 3;
                JPanel prevpan = new JPanel();
                prevpan.add(aprev);
                prevpan.setPreferredSize(new Dimension(500, 20));
                prevpan.setBorder(zx);
                JF.add(prevpan, gbc);
                JButton b1, b2;
                b1 = new JButton("OK");
                b2 = new JButton("Cancel");
                gbc.ipady = 0;
                gbc.gridx = 2;
                gbc.gridy = 2;
                gbc.insets = new Insets(10, 10, 10, 10);
                JPanel okcancel = new JPanel();
                okcancel.setLayout(new FlowLayout());
                okcancel.add(b1);
                okcancel.add(b2);
                b1.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        JF.setVisible(false);
                        TA.setFont(new Font(finalfont, finalstyle, finalsize));
                        if(kkk.isSelected())
                        {
                            Font fnt1 = TA.getFont();
                            Map attributes = fnt1.getAttributes();
                            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                            TA.setFont(fnt1.deriveFont(attributes));
                        }
                        ffsize = finalsize;
                        ffont = finalfont;
                        ffstyle = finalstyle;
                    }
                });
                b2.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        JF.setVisible(false);
                    }
                });
                //gbc.anchor = GridBagConstraints.LAST_LINE_END;
                //gbc.fill = GridBagConstraints.HORIZONTAL;
                JF.add(okcancel, gbc);
                JF.setVisible(true);
                JF.pack();
                JF.setLocationRelativeTo(FM.this);
            }
        });
        Viewm.add(Formati);
        JM.add(Viewm);
        JM.add(Helpm);
        setJMenuBar(JM);
        p1 = new JPanel();
        p2 = new JPanel();
        p1.setLayout(new GridLayout(1, 4));
        p1.add(L1);
        p1.add(TF);
        p1.add(L2);
        p1.add(PF);
        JScrollPane JP = new JScrollPane(TA);
        add(p1, BorderLayout.NORTH);
        add(JP, BorderLayout.CENTER);
        JButton JB = new JButton("Insert");
        JB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                TA.setText("USERNAME: " + TF.getText().trim() + "  PASSWORD: " + new String(PF.getPassword()));
            }
        });
        b = new JCheckBox("Bold");
        i = new JCheckBox("Italics");
        ActionListener x = new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                int mode = 0;
                if(b.isSelected())
                mode += Font.BOLD;
                if(i.isSelected())
                mode += Font.ITALIC;
                TA.setFont(new Font("Serif", mode, 14));
            }
        };
        b.addActionListener(x);
        i.addActionListener(x);
        p2.add(JB);
        p2.add(b);
        p2.add(i);
        add(p2, BorderLayout.SOUTH);
        //pack();
    }
}
