/**
 * 
 */
package edu.bu.met673.usermanagement.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

/**
 * 
 */
public final class PageRequestUtils {
	
	private PageRequestUtils() {}
	
	public static Sort.Direction getSortDirection(String direction) {
        if (direction == null || direction.isEmpty()) {
            return Sort.Direction.ASC; // Default to ascending if direction is null or empty
        }
        switch (direction.toLowerCase()) {
            case "desc":
                return Sort.Direction.DESC;
            case "asc":
            default:
                return Sort.Direction.ASC;
        }
    }
	
	public static PageRequest getPageRequest(int page, int size, String[] sort) {
		List<Order> orders = new ArrayList<>();

	      if (sort[0].contains(",")) {
	        for (String sortOrder : sort) {
	          String[] _sort = sortOrder.split(",");
	          orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
	        }
	      } else {
	        // sort=[field, direction]
	        orders.add(new Order(getSortDirection(sort[1]), sort[0]));
	      }

	      return PageRequest.of(page, size, Sort.by(orders));
	}
	
	

}
