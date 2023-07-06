package com.phase3.global;

import java.util.ArrayList;
import java.util.List;

import com.phase3.model.Product;

public class GlobalData {
	public static List<Product> cart;
	static {
		cart =new ArrayList<Product>();
}
}
