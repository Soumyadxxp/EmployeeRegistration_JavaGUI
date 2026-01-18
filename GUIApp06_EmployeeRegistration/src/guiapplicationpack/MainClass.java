package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class MainPanel extends JPanel
{
    private JLabel      lblCode,lblName,lblSal,lblDept;
    private JTextField  txtCode,txtName,txtSal,txtDept;
    private JTextArea   txtReport;
    private JScrollPane spnReport;
    private JButton     btnSubmit,btnCommit,btnShow,btnExit,btnBack;
    private ArrayList<Employee> empList = new ArrayList<Employee>();
    private File file = new File("Employee.xml");
    
    private JLabel makeLabel(String cap,int x,int y,int w,int h)
    {
        JLabel temp = new JLabel(cap);
        temp.setFont(new Font("Courier New", 1, 18));
        temp.setBounds(x,y,w,h);
        add(temp);
        return temp;
    }
    private JTextField makeTextField(int x,int y,int w,int h)
    {
        JTextField temp = new JTextField();
        temp.setFont(new Font("Courier New", 1, 18));
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        temp.setBounds(x,y,w,h);
        temp.setHorizontalAlignment(JTextField.CENTER);
        temp.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char ch = e.getKeyChar();
                Object ob = e.getSource();
                if(ob.equals(txtCode) || ob.equals(txtSal))
                {
                    if(ch<'0' || ch>'9') e.setKeyChar('\0');
                }
                else if(ob.equals(txtName) || ob.equals(txtDept))
                {
                    e.setKeyChar(Character.toUpperCase(ch));
                }
            }
            @Override
            public void keyPressed(KeyEvent e)
            {
            }
            @Override
            public void keyReleased(KeyEvent e)
            {
            }
        });
        add(temp);
        return temp;
    }
    private JButton makeButton(String cap,int x,int y,int w,int h)
    {
        JButton temp = new JButton(cap);
        temp.setFont(new Font("Courier New", 1, 16));
        temp.setMargin(new Insets(0,0,0,0));
        temp.setBounds(x,y,w,h);
        temp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Object ob = e.getSource();
                if(ob.equals(btnSubmit))
                {
                    Employee temp = new Employee(Integer.parseInt(txtCode.getText()),txtName.getText(),Integer.parseInt(txtSal.getText()),txtDept.getText());
                    empList.add(temp);
                    txtCode.setText("");
                    txtName.setText("");
                    txtSal.setText("");
                    txtDept.setText("");
                    txtCode.grabFocus();
                }
                else if(ob.equals(btnCommit))
                {
                    try
                    {
                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Document doc = dBuilder.newDocument();
                        Element root = doc.createElement("employee");
                        doc.appendChild(root);
                        
                        for(Employee temp:empList)
                        {
                            Element emp = doc.createElement("emp");
                            root.appendChild(emp);
                            
                            Element code = doc.createElement("code");
                            code.appendChild(doc.createTextNode(String.valueOf(temp.getCode())));
                            emp.appendChild(code);
                            
                            Element name = doc.createElement("name");
                            name.appendChild(doc.createTextNode(temp.getName()));
                            emp.appendChild(name);
                            
                            Element sal = doc.createElement("sal");
                            sal.appendChild(doc.createTextNode(String.valueOf(temp.getSal())));
                            emp.appendChild(sal);
                            
                            Element dept = doc.createElement("dept");
                            dept.appendChild(doc.createTextNode(temp.getDept()));
                            emp.appendChild(dept);
                        }
                        
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(file);
                        transformer.transform(source, result);
                        JOptionPane.showMessageDialog(null, "Employee Record Committed Successfully");
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
                else if(ob.equals(btnShow))
                {
                    txtReport.setText("");
                    for(Employee temp:empList)
                    {
                        String msg = String.format("%4d|%-20s|%7d|%s\n",temp.getCode(),temp.getName(),temp.getSal(),temp.getDept());
                        txtReport.append(msg);
                    }
                    visibilityReset();
                }
                else if(ob.equals(btnExit))
                {
                    System.exit(0);
                }
                else if(ob.equals(btnBack))
                {
                    visibilityReset();
                }
            }
        });
        add(temp);
        return temp;
    }
    private void visibilityReset()
    {
        lblCode.setVisible(!lblCode.isVisible());
        lblName.setVisible(!lblName.isVisible());
        lblSal.setVisible(!lblSal.isVisible());
        lblDept.setVisible(!lblDept.isVisible());
        
        txtCode.setVisible(!txtCode.isVisible());
        txtName.setVisible(!txtName.isVisible());
        txtSal.setVisible(!txtSal.isVisible());
        txtDept.setVisible(!txtDept.isVisible());
        
        btnSubmit.setVisible(!btnSubmit.isVisible());
        btnCommit.setVisible(!btnCommit.isVisible());
        btnShow.setVisible(!btnShow.isVisible());
        btnExit.setVisible(!btnExit.isVisible());
        
        spnReport.setVisible(!spnReport.isVisible());
        btnBack.setVisible(!btnBack.isVisible());
    }
    private void init()
    {
        try
        {
            if(file.exists() && file.length()>0)
            {
                Employee temp = null;
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                Element root = doc.getDocumentElement();
                NodeList children = root.getChildNodes();
                for(int idx=0;idx<children.getLength();idx++)
                {
                    Node child = children.item(idx);
                    if(child instanceof Element)
                    {
                        NodeList grandChildren = child.getChildNodes();
                        int code = 0;
                        String name = "";
                        int sal = 0;
                        String dept = "";
                        for(int jdx=0;jdx<grandChildren.getLength();jdx++)
                        {
                            Node grandchild = grandChildren.item(jdx);
                            if(grandchild instanceof Element)
                            {
                                if(grandchild.getNodeName().equals("code"))
                                    code = Integer.parseInt(grandchild.getFirstChild().getNodeValue());
                                else if(grandchild.getNodeName().equals("name"))
                                    name = grandchild.getFirstChild().getNodeValue();
                                else if(grandchild.getNodeName().equals("sal"))
                                    sal = Integer.parseInt(grandchild.getFirstChild().getNodeValue());
                                else if(grandchild.getNodeName().equals("dept"))
                                    dept = grandchild.getFirstChild().getNodeValue();
                            }
                        }
                        temp = new Employee(code, name, sal, dept);
                        empList.add(temp);
                    }
                }
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public MainPanel()
    {
        lblCode = makeLabel("Enter Employee Code",  10,10,250,30);
        txtCode = makeTextField(260,10,300,30);
        lblName = makeLabel("Enter Employee Name",  10,50,250,30);
        txtName = makeTextField(260,50,300,30);
        lblSal  = makeLabel("Enter Basic Salary",  10,90,250,30);
        txtSal  = makeTextField(260,90,300,30);
        lblDept = makeLabel("Enter Department Name",  10,130,250,30);
        txtDept = makeTextField(260,130,300,30);
        
        btnSubmit = makeButton("Submit", 35,180,100,30);
        btnCommit = makeButton("Commit",170,180,100,30);
        btnShow   = makeButton("Show",305,180,100,30);
        btnExit   = makeButton("Exit",440,180,100,30);
        
        txtReport = new JTextArea();
        txtReport.setFont(new Font("Courier New",1,14));
        txtReport.setEditable(false);
        spnReport = new JScrollPane(txtReport);
        spnReport.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        spnReport.setBounds(10,10,570,160);
        spnReport.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        spnReport.setVisible(false);
        add(spnReport);
        btnBack = makeButton("Back",250,180,100,30);
        btnBack.setVisible(false);
        
        init();
    }
}
class MainFrame extends JFrame
{
    private MainPanel panel;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setBackground(new Color(225,250,160));
        panel.setLayout(new BorderLayout());
        super.add(panel);
    }
}
public class MainClass
{
    public static void main(String[] args)
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 260);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Employee Registartion Form");
        frame.setIconImage(tk.getImage("icon.jpg"));
        frame.setResizable(false);
    }
}
