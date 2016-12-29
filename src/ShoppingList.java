import java.util.ArrayList;

public class ShoppingList implements PrintOutput
{
	private ArrayList<ItemToBuy> list;
	private static int index = 0;
	
	public ShoppingList()
	{
		list = new ArrayList<ItemToBuy>(7);
	}
	public void add (String name, int quantity, int priority, double price)
	{
		list.add(index++, new ItemToBuy(name, quantity, priority, price));
	}
	public void add(ItemToBuy itemToBuysPosition)
	{
		list.add(index++, itemToBuysPosition);
	}
	public ItemToBuy getItemToBuyInOrderOfPriority(int itemToBuysPosition)
	{
		ItemToBuy itemToBuysPriority = null;
		for (int i = 0; i < index; i++)
		{
			if (list.get(i).getPriority() == itemToBuysPosition)
			{
				itemToBuysPriority = list.get(i);
			}
		}
		return itemToBuysPriority;
	}
	public void bubbleSort()
	{
		ItemToBuy higherPriorityItemToBuy = null;
		for (int i = 0; i < (index-1); i++)
		{
			for (int j = 1; j < (index-i); j++)
			{
				if (list.get(j-1).getPriority() > list.get(j).getPriority())
				{
					higherPriorityItemToBuy = list.get(j-1);
					list.set(j-1, list.get(j));
					list.set(j, higherPriorityItemToBuy);
					
				}
			}
		}
	}
	public boolean duplicateName(String name)
	{
		for (int i = 0; i < index; i++)
		{
			if (list.get(i).equalsIgnoreCase(name))
			{
				return true;
			}
		}
		return false;
	}
	public void confirmation()
	{
		for (int i = 0; i < index; i++)
		{
			System.out.println(list.get(i));
		}
	}
	public int size()
	{
		return index;
	}
	public void goShopping()
	{
		double budget = 59.00;
		for (int i = 0; i < index; i++)
		{
			if (list.get(i).getTotalCost() < budget)
			{
				list.get(i).setPurchased(true);
				budget = budget - list.get(i).getTotalCost();
			}
		}
	}
	public void result()
	{
		System.out.println("Below are the items we can get for you today:");
		System.out.printf("%-10s %-10s %-10s %-10s\n", "Quantity", "Name", "Priority", "Price");
		for (int i = 0; i < index; i++)
		{
			if (list.get(i).isPurchased())
			{
				System.out.println(list.get(i));
				System.out.println();
			}
		}
		System.out.println("And here are the items on your list "
				+ "that you have not purchased yet:");
		System.out.printf("%-10s %-10s %-10s %-10s\n", "Quantity", "Name", "Priority", "Price");
		for (int i = 0; i < index; i++)
		{
			if (!list.get(i).isPurchased())
			{
				System.out.println(list.get(i));
			}
		}
	}

}
