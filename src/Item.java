
public abstract class Item 
{
	public String name;
	public Item()
	{
		name = "No record yet";
	}
	public Item (String initialName)
	{
		name = initialName;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

}
