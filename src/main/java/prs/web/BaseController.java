package prs.web;

import java.util.ArrayList;
import java.util.List;

public class BaseController {

	public static <T> List<T> getReturnArray (T obj){
        ArrayList<T> uList = new ArrayList<T>();
        if (obj!=null)
        	uList.add(obj);
        return uList;
	}
}
