package guiapplicationpack;
public class Employee
{
    private int     code;
    private String  name;
    private int     sal;
    private String  dept;
    public Employee()
    {
        code = 0;
        name = "";
        sal = 0;
        dept = "";
    }
    public Employee(int code,String name,int sal,String dept)
    {
        this.code = code;
        this.name = name;
        this.sal = sal;
        this.dept = dept;
    }
    public int getCode()
    {
        return code;
    }
    public String getName()
    {
        return name;
    }
    public int getSal()
    {
        return sal;
    }
    public String getDept()
    {
        return dept;
    }
}
