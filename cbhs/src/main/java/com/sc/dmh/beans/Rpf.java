package com.sc.dmh.beans;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rpf extends TabRpf {
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//车站
	public String cz;
	
	//票种
	public String ticketTypeName;
	
	
	
	public String getInDateS() {
		return getMonth(super.getInDate());
		
	}




	/**
	 * 获取rpf数量合计
	 * @return
	 */
	public Integer getSumRpf() {
		if(super.getFpNumber() != null && super.getSpNumber() != null){
			
			return super.getFpNumber()  + super.getSpNumber();
		}else{
			return null;
		}
		
	}

	
	public String getTicketTypeName() {
		return ticketTypeName;
	}
	
	public void setTicketTypeName(String ticketTypeName) {
		this.ticketTypeName = ticketTypeName;
	}
	
//	/**
//	 * 获取日期字符串
//	 * @return
//	 */
//	public String getInDateS() {
//		return getDate(this.inDate);
//	}

	

	

	public String getCz() {
		return cz;
	}

	public void setCz(String cz) {
		this.cz = cz;
	}
	
	
	
	public String trim(String input)
    {
        return input==null?null:input.trim();
    }
    
    public String getDate(Date date)
    {
        if( null == date ) return "";
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    
    public String getMonth(Date date)
    {
        if( null == date ) return "";
        return new SimpleDateFormat("yyyy-MM").format(date);
    }
    
    public String getDateTime(Date date)
    {
        if( null == date ) return "";
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    public Date getDate(String date)
    {
        if( null == date || date.trim().isEmpty() ) return null;
        date = date.trim();
        Date result = null;
        try {
            if( date.length() >= 19 )
            {
                result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            }
            else if( date.length() == 10 )
            {
                result = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            }
        }
        catch (ParseException e) 
        {
            
        }
        return result;
    }
	

}
