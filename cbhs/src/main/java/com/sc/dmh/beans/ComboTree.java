package com.sc.dmh.beans;
import java.util.ArrayList;
import java.util.List;

public class ComboTree
{

	private String id;
	private String text;
	private String state;
	private String pid;
	private String clickFlag;
	private List<ComboTree> children;

	public ComboTree()
	{
		super();
		//this.setState("closed");
		this.setId("00");
		this.setText(null);
		//this.setClickFlag("off");
		this.setChildren(new ArrayList<ComboTree>());
	}

	public void clear()
	{
		this.setState(null);
		this.setId(null);
		this.setText(null);
		this.setChildren(null);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{\"id\":\"");
		sb.append(this.id);
		sb.append("\",\"text\":\"");
		sb.append(this.text);
		sb.append("\"");
//		sb.append("off");
//		sb.append("\"");
		if (!this.children.isEmpty())
		{
//			sb.append(",\"state\":\"");
//			sb.append(this.state);
			sb.append(",\"children\":[");
			boolean flag = false;
			for (ComboTree c : this.children)
			{
				if (flag)
					sb.append(",");
				else
					flag = true;
				sb.append(c.toString());
			}
			sb.append("]");
		}
		sb.append("}");
		return sb.toString();
	}

	public String getPid()
	{
		return pid;
	}

	public void setPid(String pid)
	{
		this.pid = pid;
	}

	public void addChildren(ComboTree ct)
	{
		this.children.add(ct);
		// System.out.println("add");
	}
	//添加一个节点
	public boolean addNode(ComboTree ct){
		//如果需要添加的节点的PID 为当前节点的ID, 则直接
		//添加, 返回true
		if(this.id == ct.pid ||
				(this.id != null && ct.pid != null && this.id.equals(ct.pid))){
			this.children.add(ct);
			return true;
		}
		//委托给子节点
		for(ComboTree cct: this.children){
			if(cct.addNode(ct))
				return true;
		}
		//无法添加成功
		return false;
	}
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public List<ComboTree> getChildren()
	{
		return children;
	}

	public void setChildren(List<ComboTree> children)
	{
		this.children = children;
	}

	public String getClickFlag()
	{
		return clickFlag;
	}

	public void setClickFlag(String clickFlag)
	{
		this.clickFlag = clickFlag;
	}

}